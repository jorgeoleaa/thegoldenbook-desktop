package com.pinguela.thegoldenbook.ui.desktop.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.TableColumn;

import com.pinguela.thegoldenbook.model.ClasificacionEdad;
import com.pinguela.thegoldenbook.model.Formato;
import com.pinguela.thegoldenbook.model.GeneroLiterario;
import com.pinguela.thegoldenbook.model.Idioma;
import com.pinguela.thegoldenbook.model.LibroDTO;
import com.pinguela.thegoldenbook.service.FileService;
import com.pinguela.thegoldenbook.service.impl.FileServiceImpl;
import com.pinguela.thegoldenbook.ui.desktop.controller.UpdateLibroAction;
import com.pinguela.thegoldenbook.ui.desktop.model.ValoracionTableModel;
import com.pinguela.thegoldenbook.ui.desktop.renderer.ClasificacionEdadListCellRenderer;
import com.pinguela.thegoldenbook.ui.desktop.renderer.FormatoListCellRenderer;
import com.pinguela.thegoldenbook.ui.desktop.renderer.GeneroListCellRenderer;
import com.pinguela.thegoldenbook.ui.desktop.renderer.IdiomaListCellRenderer;
import com.pinguela.thegoldenbook.ui.desktop.renderer.ValoracionActionsCellEditor;
import com.pinguela.thegoldenbook.ui.desktop.renderer.ValoracionActionsCellRenderer;
import com.pinguela.thegoldenbook.ui.desktop.renderer.ValoracionTableCellRenderer;
import com.toedter.calendar.JDateChooser;

public class LibroDetailView extends View {

	private JButton editButton;
	private JPanel datePanel;
	private JPanel parentImagenesPanel;
	private JScrollPane imagesScrollPane;
	private JPanel singleImagePanel;
	private JLabel mainImageLabel;
	private JPanel panel;
	private JLabel imagen1Label;
	private JLabel imagen2Label;
	private JLabel imagen3Label;
	private JScrollPane scrollPane;
	private JScrollPane valoracionesScrollPanel;
	private JTable valoracionesTable;
	private JScrollPane dataScrollPanel;
	private JPanel dataPanel;
	private JLabel tituloLabel;
	private JTextField tituloValueTextField;
	private JLabel isbnLabel;
	private JTextField isbnValueTextField;
	private JLabel fechaPublicacionLabel;
	private JLabel unidadesLabel;
	private JDateChooser fechaPublicacionDateChooser;
	private JLabel precioLabel;
	private JTextField precioValueTextField;
	private JSpinner unidadesSpinner;
	private JLabel idiomaLabel;
	private JComboBox idiomaValueComboBox;
	private JLabel formatoLabel;
	private JComboBox formatoValueComboBox;
	private JLabel generoLiterarioLabel;
	private JComboBox generoLiterarioValueComboBox;
	private JLabel clasificacionEdadLabel;
	private JComboBox clasificacionEdadValueComboBox;
	private JButton saveButton;
	private JButton deleteButton;

	private LibroDTO libro = null;
	private FileService fileService = null;
	private JScrollPane sinopsisScrollPanel;
	private JLabel sinopsisLabel;

	private List<BufferedImage> loadedImages = new ArrayList<>();
	private JTextArea textArea;

	public LibroDetailView(LibroSearchView view, LibroDTO l) {
		this.libro = l;
		initServices();
		setLayout(new BorderLayout(0, 0));

		datePanel = new JPanel();
		datePanel.setPreferredSize(new Dimension(1024, 768));

		JPanel buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.SOUTH);

		editButton = new JButton("Editar");
		editButton.setIcon(new ImageIcon(LibroDetailView.class.getResource("/nuvola/32x32/1819_pencil_pencil.png")));
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveButton.setVisible(true);
				editButton.setVisible(false);
				deleteButton.setVisible(true);
				setEditable(true);
			}
		});
		buttonPanel.add(editButton);

		deleteButton = new JButton("Cancelar");
		deleteButton.setIcon(new ImageIcon(LibroDetailView.class.getResource("/nuvola/32x32/1250_delete_delete.png")));
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setModel();
				setEditable(false);
				deleteButton.setVisible(false);
				editButton.setVisible(true);
				saveButton.setVisible(false);
			}
		});
		buttonPanel.add(deleteButton);

		parentImagenesPanel = new JPanel();
		add(parentImagenesPanel, BorderLayout.CENTER);
		parentImagenesPanel.setLayout(new BorderLayout(0, 0));

		imagesScrollPane = new JScrollPane();
		imagesScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		parentImagenesPanel.add(imagesScrollPane, BorderLayout.SOUTH);

		panel = new JPanel();
		imagesScrollPane.setViewportView(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		imagen1Label = new JLabel("");
		imagen1Label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 1) {
					mainImageLabel.setIcon(new ImageIcon(loadedImages.get(0).getScaledInstance(400, 500, Image.SCALE_SMOOTH)));
				}
			}
		});
		GridBagConstraints gbc_imagen1Label = new GridBagConstraints();
		gbc_imagen1Label.insets = new Insets(0, 0, 0, 5);
		gbc_imagen1Label.gridx = 2;
		gbc_imagen1Label.gridy = 5;
		panel.add(imagen1Label, gbc_imagen1Label);

		imagen2Label = new JLabel("");
		imagen2Label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 1) {
					mainImageLabel.setIcon(new ImageIcon(loadedImages.get(1).getScaledInstance(400, 500, Image.SCALE_SMOOTH)));
				}
			}
		});
		GridBagConstraints gbc_imagen2Label = new GridBagConstraints();
		gbc_imagen2Label.insets = new Insets(0, 0, 0, 5);
		gbc_imagen2Label.gridx = 5;
		gbc_imagen2Label.gridy = 5;
		panel.add(imagen2Label, gbc_imagen2Label);

		imagen3Label = new JLabel("");
		imagen3Label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 1) {
					mainImageLabel.setIcon(new ImageIcon(loadedImages.get(2).getScaledInstance(400, 500, Image.SCALE_SMOOTH)));
				}
			}
		});
		GridBagConstraints gbc_imagen3Label = new GridBagConstraints();
		gbc_imagen3Label.insets = new Insets(0, 0, 0, 5);
		gbc_imagen3Label.gridx = 8;
		gbc_imagen3Label.gridy = 5;
		panel.add(imagen3Label, gbc_imagen3Label);

		singleImagePanel = new JPanel();
		parentImagenesPanel.add(singleImagePanel, BorderLayout.CENTER);
		GridBagLayout gbl_singleImagePanel = new GridBagLayout();
		gbl_singleImagePanel.columnWidths = new int[]{60, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_singleImagePanel.rowHeights = new int[]{17, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_singleImagePanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_singleImagePanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		singleImagePanel.setLayout(gbl_singleImagePanel);

		mainImageLabel = new JLabel("");
		GridBagConstraints gbc_mainImageLabel = new GridBagConstraints();
		gbc_mainImageLabel.insets = new Insets(0, 0, 0, 5);
		gbc_mainImageLabel.anchor = GridBagConstraints.NORTHWEST;
		gbc_mainImageLabel.gridx = 3;
		gbc_mainImageLabel.gridy = 7;
		singleImagePanel.add(mainImageLabel, gbc_mainImageLabel);

		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.EAST);
		scrollPane.setViewportView(datePanel);
		datePanel.setLayout(new BorderLayout(0, 0));

		valoracionesScrollPanel = new JScrollPane();
		valoracionesScrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		valoracionesScrollPanel.setPreferredSize(new Dimension(valoracionesScrollPanel.getPreferredSize().width, 350));
		datePanel.add(valoracionesScrollPanel, BorderLayout.SOUTH);

		valoracionesTable = new JTable();
		valoracionesScrollPanel.setViewportView(valoracionesTable);

		dataScrollPanel = new JScrollPane();


		dataPanel = new JPanel();
		GridBagLayout gbl_dataPanel = new GridBagLayout();
		gbl_dataPanel.columnWidths = new int[]{0, 0, 0, 422, 0, 0, 0, 304, 0, 0, 0};
		gbl_dataPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 146, 0, 32, 0, 0, 0, 0};
		gbl_dataPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_dataPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		dataPanel.setLayout(gbl_dataPanel);

		datePanel.add(dataPanel, BorderLayout.CENTER);

		tituloLabel = new JLabel("Título:");
		GridBagConstraints gbc_tituloLabel = new GridBagConstraints();
		gbc_tituloLabel.insets = new Insets(0, 0, 5, 5);
		gbc_tituloLabel.gridx = 3;
		gbc_tituloLabel.gridy = 1;
		dataPanel.add(tituloLabel, gbc_tituloLabel);

		isbnLabel = new JLabel("ISBN:");
		GridBagConstraints gbc_isbnLabel = new GridBagConstraints();
		gbc_isbnLabel.insets = new Insets(0, 0, 5, 5);
		gbc_isbnLabel.gridx = 7;
		gbc_isbnLabel.gridy = 1;
		dataPanel.add(isbnLabel, gbc_isbnLabel);

		tituloValueTextField = new JTextField();
		GridBagConstraints gbc_tituloValueTextField = new GridBagConstraints();
		gbc_tituloValueTextField.insets = new Insets(0, 0, 5, 5);
		gbc_tituloValueTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_tituloValueTextField.gridx = 3;
		gbc_tituloValueTextField.gridy = 2;
		dataPanel.add(tituloValueTextField, gbc_tituloValueTextField);
		tituloValueTextField.setColumns(10);

		isbnValueTextField = new JTextField();
		GridBagConstraints gbc_isbnValueTextField = new GridBagConstraints();
		gbc_isbnValueTextField.insets = new Insets(0, 0, 5, 5);
		gbc_isbnValueTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_isbnValueTextField.gridx = 7;
		gbc_isbnValueTextField.gridy = 2;
		dataPanel.add(isbnValueTextField, gbc_isbnValueTextField);
		isbnValueTextField.setColumns(10);

		unidadesLabel = new JLabel("Unidades:");
		GridBagConstraints gbc_unidadesLabel = new GridBagConstraints();
		gbc_unidadesLabel.insets = new Insets(0, 0, 5, 5);
		gbc_unidadesLabel.gridx = 3;
		gbc_unidadesLabel.gridy = 4;
		dataPanel.add(unidadesLabel, gbc_unidadesLabel);

		fechaPublicacionLabel = new JLabel("Fecha de publicación:");
		GridBagConstraints gbc_fechaPublicacionLabel = new GridBagConstraints();
		gbc_fechaPublicacionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_fechaPublicacionLabel.gridx = 7;
		gbc_fechaPublicacionLabel.gridy = 4;
		dataPanel.add(fechaPublicacionLabel, gbc_fechaPublicacionLabel);

		unidadesSpinner = new JSpinner();
		GridBagConstraints gbc_unidadesSpinner = new GridBagConstraints();
		gbc_unidadesSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_unidadesSpinner.gridx = 3;
		gbc_unidadesSpinner.gridy = 5;
		dataPanel.add(unidadesSpinner, gbc_unidadesSpinner);

		fechaPublicacionDateChooser = new JDateChooser();
		GridBagConstraints gbc_fechaPublicacionDateChooser = new GridBagConstraints();
		gbc_fechaPublicacionDateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_fechaPublicacionDateChooser.fill = GridBagConstraints.BOTH;
		gbc_fechaPublicacionDateChooser.gridx = 7;
		gbc_fechaPublicacionDateChooser.gridy = 5;
		dataPanel.add(fechaPublicacionDateChooser, gbc_fechaPublicacionDateChooser);

		idiomaLabel = new JLabel("Idioma:");
		GridBagConstraints gbc_idiomaLabel = new GridBagConstraints();
		gbc_idiomaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_idiomaLabel.gridx = 3;
		gbc_idiomaLabel.gridy = 6;
		dataPanel.add(idiomaLabel, gbc_idiomaLabel);

		precioLabel = new JLabel("Precio:");
		GridBagConstraints gbc_precioLabel = new GridBagConstraints();
		gbc_precioLabel.insets = new Insets(0, 0, 5, 5);
		gbc_precioLabel.gridx = 7;
		gbc_precioLabel.gridy = 6;
		dataPanel.add(precioLabel, gbc_precioLabel);

		idiomaValueComboBox = new JComboBox();
		GridBagConstraints gbc_idiomaValueComboBox = new GridBagConstraints();
		gbc_idiomaValueComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_idiomaValueComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_idiomaValueComboBox.gridx = 3;
		gbc_idiomaValueComboBox.gridy = 7;
		dataPanel.add(idiomaValueComboBox, gbc_idiomaValueComboBox);

		precioValueTextField = new JTextField();
		GridBagConstraints gbc_precioValueTextField = new GridBagConstraints();
		gbc_precioValueTextField.insets = new Insets(0, 0, 5, 5);
		gbc_precioValueTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_precioValueTextField.gridx = 7;
		gbc_precioValueTextField.gridy = 7;
		dataPanel.add(precioValueTextField, gbc_precioValueTextField);
		precioValueTextField.setColumns(10);

		sinopsisLabel = new JLabel("Sinopsis");
		GridBagConstraints gbc_sinopsisLabel = new GridBagConstraints();
		gbc_sinopsisLabel.insets = new Insets(0, 0, 5, 5);
		gbc_sinopsisLabel.gridx = 3;
		gbc_sinopsisLabel.gridy = 8;
		dataPanel.add(sinopsisLabel, gbc_sinopsisLabel);

		formatoLabel = new JLabel("Formato");
		GridBagConstraints gbc_formatoLabel = new GridBagConstraints();
		gbc_formatoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_formatoLabel.gridx = 7;
		gbc_formatoLabel.gridy = 8;
		dataPanel.add(formatoLabel, gbc_formatoLabel);

		textArea = new JTextArea();
		
		sinopsisScrollPanel = new JScrollPane(textArea);
		GridBagConstraints gbc_sinopsisScrollPanel = new GridBagConstraints();
		gbc_sinopsisScrollPanel.insets = new Insets(0, 0, 5, 5);
		gbc_sinopsisScrollPanel.fill = GridBagConstraints.BOTH;
		gbc_sinopsisScrollPanel.gridx = 3;
		gbc_sinopsisScrollPanel.gridy = 9;
		dataPanel.add(sinopsisScrollPanel, gbc_sinopsisScrollPanel);

		
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setPreferredSize(new Dimension(400, 100));  
		textArea.setText(libro.getSinopsis());
		sinopsisScrollPanel.setViewportView(textArea);
		sinopsisScrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		sinopsisScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		formatoValueComboBox = new JComboBox();
		GridBagConstraints gbc_formatoValueComboBox = new GridBagConstraints();
		gbc_formatoValueComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_formatoValueComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_formatoValueComboBox.gridx = 7;
		gbc_formatoValueComboBox.gridy = 9;
		dataPanel.add(formatoValueComboBox, gbc_formatoValueComboBox);

		generoLiterarioLabel = new JLabel("Genero Literario:");
		GridBagConstraints gbc_generoLiterarioLabel = new GridBagConstraints();
		gbc_generoLiterarioLabel.insets = new Insets(0, 0, 5, 5);
		gbc_generoLiterarioLabel.gridx = 3;
		gbc_generoLiterarioLabel.gridy = 11;
		dataPanel.add(generoLiterarioLabel, gbc_generoLiterarioLabel);

		clasificacionEdadLabel = new JLabel("Clasificacion Edad:");
		GridBagConstraints gbc_clasificacionEdadLabel = new GridBagConstraints();
		gbc_clasificacionEdadLabel.insets = new Insets(0, 0, 5, 5);
		gbc_clasificacionEdadLabel.gridx = 7;
		gbc_clasificacionEdadLabel.gridy = 11;
		dataPanel.add(clasificacionEdadLabel, gbc_clasificacionEdadLabel);

		generoLiterarioValueComboBox = new JComboBox();
		GridBagConstraints gbc_generoLiterarioValueComboBox = new GridBagConstraints();
		gbc_generoLiterarioValueComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_generoLiterarioValueComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_generoLiterarioValueComboBox.gridx = 3;
		gbc_generoLiterarioValueComboBox.gridy = 12;
		dataPanel.add(generoLiterarioValueComboBox, gbc_generoLiterarioValueComboBox);

		clasificacionEdadValueComboBox = new JComboBox();
		GridBagConstraints gbc_clasificacionEdadValueComboBox = new GridBagConstraints();
		gbc_clasificacionEdadValueComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_clasificacionEdadValueComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_clasificacionEdadValueComboBox.gridx = 7;
		gbc_clasificacionEdadValueComboBox.gridy = 12;
		dataPanel.add(clasificacionEdadValueComboBox, gbc_clasificacionEdadValueComboBox);

		saveButton = new JButton("Guardar");
		saveButton.setAction(new UpdateLibroAction(view, this, "Guardar"));
		GridBagConstraints gbc_saveButton = new GridBagConstraints();
		gbc_saveButton.insets = new Insets(0, 0, 0, 5);
		gbc_saveButton.gridx = 8;
		gbc_saveButton.gridy = 14;
		dataPanel.add(saveButton, gbc_saveButton);

		postInitialize();

	}

	private void postInitialize() {
		
		deleteButton.setVisible(false);

		Double width = valoracionesTable.getPreferredSize().getWidth();
		valoracionesTable.setPreferredSize(new Dimension(width.intValue(), 300));
		saveButton.setVisible(false);

		idiomaValueComboBox.setRenderer(new IdiomaListCellRenderer());
		formatoValueComboBox.setRenderer(new FormatoListCellRenderer());
		clasificacionEdadValueComboBox.setRenderer(new ClasificacionEdadListCellRenderer());
		generoLiterarioValueComboBox.setRenderer(new GeneroListCellRenderer());

		setNotEditable();

		setModel();

		JLabel[] labels = {imagen1Label, imagen2Label, imagen3Label};
		List<File> imageFiles = fileService.getImagesByBookId(libro.getId());
		if (!imageFiles.isEmpty()) {
			loadedImages.clear();
			for (int i = 0; i < imageFiles.size(); i++) {
				File imageFile = imageFiles.get(i);
				try {
					if (imageFile.exists() && imageFile.isFile()) {
						BufferedImage originalImage = ImageIO.read(imageFile);
						loadedImages.add(originalImage);
						ImageIcon icon = new ImageIcon(originalImage.getScaledInstance(75, 100, Image.SCALE_SMOOTH));
						labels[i].setIcon(icon);
					} else {
						labels[i].setIcon(null);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			mainImageLabel.setIcon(new ImageIcon(loadedImages.get(0).getScaledInstance(400, 500, Image.SCALE_SMOOTH)));
		}

	}


	private void initServices() {
		fileService = new FileServiceImpl();
	}

	public void setComboModel(DefaultComboBoxModel<ClasificacionEdad> clasificacionComboModel,
			DefaultComboBoxModel<Formato> formatoComboModel,
			DefaultComboBoxModel<Idioma> idiomaComboModel,
			DefaultComboBoxModel<GeneroLiterario> defaultComboModel) {

		generoLiterarioValueComboBox.setModel(defaultComboModel);
		clasificacionEdadValueComboBox.setModel(clasificacionComboModel);
		idiomaValueComboBox.setModel(idiomaComboModel);
		formatoValueComboBox.setModel(formatoComboModel);

	}

	//	public void setProgressBarValue(int value) {
	//		valoracionMediaProgressBar.setValue(value);
	//	}

	public void setGeneroLiterarioComboBoxSelectedIndex(int index) {
		this.generoLiterarioValueComboBox.setSelectedIndex(index);
	}

	public void setFormatoComboBoxSelectedItem(int index){
		this.formatoValueComboBox.setSelectedIndex(index);
	}

	public void setIdiomaComboBoxSelectedItem(int index){
		this.idiomaValueComboBox.setSelectedIndex(index);
	}

	public void setClasificacionEdadComboBoxSelectedIndex(int index){
		this.clasificacionEdadValueComboBox.setSelectedIndex(index);
	}

	private void setModel() {

		tituloValueTextField.setText(libro.getNombre());
		isbnValueTextField.setText(libro.getIsbn());
		fechaPublicacionDateChooser.setDate(libro.getFechaPublicacion());
		precioValueTextField.setText(libro.getPrecio().toString());
		unidadesSpinner.setValue(libro.getUnidades());
		textArea.setText(libro.getSinopsis());


	}

	private void setNotEditable() {
		tituloValueTextField.setEnabled(false);
		tituloValueTextField.setEditable(false);
		isbnValueTextField.setEnabled(false);
		isbnValueTextField.setEditable(false);
		precioValueTextField.setEnabled(false);
		precioValueTextField.setEditable(false);
		fechaPublicacionDateChooser.setEnabled(false);
		unidadesSpinner.setEnabled(false);
		generoLiterarioValueComboBox.setEnabled(false);
		idiomaValueComboBox.setEnabled(false);
		clasificacionEdadValueComboBox.setEnabled(false);
		formatoValueComboBox.setEnabled(false);
		textArea.setEnabled(false);
		saveButton.setVisible(false);
		deleteButton.setVisible(false);
		editButton.setVisible(true);
	}

	public void setValoracionModel(ValoracionTableModel valoraciones) {
		valoracionesTable.setModel(valoraciones);

		valoracionesTable.getColumnModel().getColumn(0).setCellRenderer(
				new ValoracionTableCellRenderer());

		valoracionesTable.getColumnModel().getColumn(1).setCellRenderer(
				new ValoracionTableCellRenderer());

		valoracionesTable.getColumnModel().getColumn(2)
		.setCellRenderer(new ValoracionTableCellRenderer());	

		valoracionesTable.getColumnModel().getColumn(3)
		.setCellRenderer(new ValoracionTableCellRenderer());

		valoracionesTable.getColumnModel().getColumn(4).setCellRenderer(new ValoracionTableCellRenderer());
		valoracionesTable.getColumnModel().getColumn(5).setCellRenderer(new ValoracionTableCellRenderer());

		TableColumn actionsColumn = valoracionesTable.getColumnModel().getColumn(6);
		actionsColumn.setMinWidth(180);
		actionsColumn.setCellRenderer(new ValoracionActionsCellRenderer(this));
		actionsColumn.setCellEditor(new ValoracionActionsCellEditor(this));

	}

	public LibroDTO getLibro() {
		return libro;
	}

	public void setUpdateModel(LibroDTO libro) {
		libro.setNombre(tituloValueTextField.getText());
		libro.setIsbn(isbnValueTextField.getText());
		libro.setUnidades((Integer)unidadesSpinner.getValue());
		libro.setPrecio(Double.valueOf(precioValueTextField.getText()));
		libro.setFechaPublicacion(fechaPublicacionDateChooser.getDate());

		Idioma idioma = (Idioma) idiomaValueComboBox.getSelectedItem();
		libro.setIdiomaId(idioma.getId());

		ClasificacionEdad clasificacion = (ClasificacionEdad) clasificacionEdadValueComboBox.getSelectedItem();
		libro.setClasificacionEdadId(clasificacion.getId());

		GeneroLiterario genero = (GeneroLiterario) generoLiterarioValueComboBox.getSelectedItem();
		libro.setGeneroLiterarioId(genero.getId());

		Formato formato = (Formato) formatoValueComboBox.getSelectedItem();
		libro.setFormatoId(formato.getId());
		
		libro.setSinopsis(textArea.getText());
	}


	public void showImagesInLabels(List<File> imageFiles, JLabel[] labels) {
		try {
			for (int i = 0; i < labels.length && i < imageFiles.size(); i++) {
				File imageFile = imageFiles.get(i);
				if (imageFile.exists() && imageFile.isFile()) {
					BufferedImage originalImage = ImageIO.read(imageFile);
					loadedImages.add(originalImage);
					ImageIcon icon = new ImageIcon(originalImage.getScaledInstance(75, 100, Image.SCALE_SMOOTH));
					labels[i].setIcon(icon);
				} else {
					labels[i].setIcon(null);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setEditable(boolean flag) {
		tituloValueTextField.setEditable(flag);
		tituloValueTextField.setEnabled(flag);
		isbnValueTextField.setEditable(flag);
		isbnValueTextField.setEnabled(flag);
		fechaPublicacionDateChooser.setEnabled(flag);
		unidadesSpinner.setEnabled(flag);
		generoLiterarioValueComboBox.setEnabled(flag);
		clasificacionEdadValueComboBox.setEnabled(flag);
		idiomaValueComboBox.setEnabled(flag);
		formatoValueComboBox.setEnabled(flag);
		precioValueTextField.setEditable(flag);
		saveButton.setVisible(flag);
		precioValueTextField.setEnabled(flag);;
		textArea.setEnabled(flag);
		deleteButton.setVisible(flag);
		saveButton.setVisible(flag);
		editButton.setVisible(false);
	}

	public void setEditButtonVisible() {
		this.editButton.setVisible(true);
	}


}
