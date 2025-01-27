package com.pinguela.thegoldenbook.ui.desktop.view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.model.Autor;
import com.pinguela.thegoldenbook.model.ClasificacionEdad;
import com.pinguela.thegoldenbook.model.GeneroLiterario;
import com.pinguela.thegoldenbook.model.Idioma;
import com.pinguela.thegoldenbook.model.LibroDTO;
import com.pinguela.thegoldenbook.model.Results;
import com.pinguela.thegoldenbook.model.Tematica;
import com.pinguela.thegoldenbook.service.AutorService;
import com.pinguela.thegoldenbook.service.ClasificacionEdadService;
import com.pinguela.thegoldenbook.service.FileService;
import com.pinguela.thegoldenbook.service.GeneroLiterarioService;
import com.pinguela.thegoldenbook.service.IdiomaService;
import com.pinguela.thegoldenbook.service.TematicaService;
import com.pinguela.thegoldenbook.service.impl.AutorServiceImpl;
import com.pinguela.thegoldenbook.service.impl.ClasificacionEdadServiceImpl;
import com.pinguela.thegoldenbook.service.impl.FileServiceImpl;
import com.pinguela.thegoldenbook.service.impl.GeneroLiterarioServiceImpl;
import com.pinguela.thegoldenbook.service.impl.IdiomaServiceImpl;
import com.pinguela.thegoldenbook.service.impl.TematicaServiceImpl;
import com.pinguela.thegoldenbook.ui.desktop.controller.CreateLibroAction;
import com.pinguela.thegoldenbook.ui.desktop.controller.ShowUploadImageAction;
import com.pinguela.thegoldenbook.ui.desktop.renderer.AutorListCellRenderer;
import com.pinguela.thegoldenbook.ui.desktop.renderer.ClasificacionEdadListCellRenderer;
import com.pinguela.thegoldenbook.ui.desktop.renderer.GeneroListCellRenderer;
import com.pinguela.thegoldenbook.ui.desktop.renderer.IdiomaListCellRenderer;
import com.pinguela.thegoldenbook.ui.desktop.renderer.TematicaListCellRenderer;
import com.pinguela.thegoldenbook.ui.desktop.utils.SwingUtils;
import com.toedter.calendar.IDateEditor;
import com.toedter.calendar.JDateChooser;
import java.awt.Component;

public class LibroCreateView extends View {

	private static final long serialVersionUID = 1L;
	private JRadioButton tapaDuraRadioButton;
	private JRadioButton tapaBlandaRadioButton;
	private JRadioButton bolsilloRadioButton;
	private JRadioButton ebookRadioButton;
	private ButtonGroup formatosButtonGroup = null;
	private JTextField isbnTextField;
	private JTextField tituloTextField;
	private JDateChooser fechaPublicacionDateChooser;
	private JSpinner unidadesValueSpinner;
	private JSlider precioValueSlider;
	private JComboBox generoLiterarioComboBox;
	private JComboBox clasificacionEdadComboBox;
	private JComboBox idiomasComboBox;
	private JComboBox tematicaComboBox;
	private JComboBox autoresComboBox;
	private JLabel precioValueLabel;

	private Logger logger = LogManager.getLogger(LibroCreateView.class);
	private IdiomaService idiomaService = null;
	private GeneroLiterarioService generoLiterarioService = null;
	private ClasificacionEdadService clasificacionEdadService = null;
	private TematicaService tematicaService = null;
	private AutorService autorService = null;
	private FileService fileService = null;


	private LibroDTO libroCreado = null;
	private JLabel image1Label;
	private JLabel image2Label;
	private JLabel image3Label;

	private ArrayList<JLabel> labels = new ArrayList<JLabel>();
	private List<File> fileList = new ArrayList<File>();
	private JButton addImageButton;
	private JScrollPane sinopsisScrollPanel;
	private JTextArea sinopsisTextArea;


	public LibroCreateView() {
		setLayout(new BorderLayout(0, 0));

		initServices();

		JPanel buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.SOUTH);
		GridBagLayout gbl_buttonPanel = new GridBagLayout();
		gbl_buttonPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_buttonPanel.rowHeights = new int[]{0, 0};
		gbl_buttonPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_buttonPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		buttonPanel.setLayout(gbl_buttonPanel);

		JButton confirmarButton = new JButton();
		confirmarButton.setAction(new CreateLibroAction(this, "Confirmar"));
		GridBagConstraints gbc_confirmarButton = new GridBagConstraints();
		gbc_confirmarButton.gridx = 19;
		gbc_confirmarButton.gridy = 0;
		buttonPanel.add(confirmarButton, gbc_confirmarButton);

		JPanel libroCreationPanel = new JPanel();
		add(libroCreationPanel, BorderLayout.CENTER);
		GridBagLayout gbl_libroCreationPanel = new GridBagLayout();
		gbl_libroCreationPanel.columnWidths = new int[]{0, 160, 150, 0, 0, 0, 0, 0};
		gbl_libroCreationPanel.rowHeights = new int[]{0, 0, 27, 47, 52, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_libroCreationPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_libroCreationPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		libroCreationPanel.setLayout(gbl_libroCreationPanel);

		JLabel formularioDeCreacionLabel = new JLabel("Formulario de creación");
		GridBagConstraints gbc_formularioDeCreacionLabel = new GridBagConstraints();
		gbc_formularioDeCreacionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_formularioDeCreacionLabel.gridx = 2;
		gbc_formularioDeCreacionLabel.gridy = 0;
		libroCreationPanel.add(formularioDeCreacionLabel, gbc_formularioDeCreacionLabel);

		JLabel tituloLabel = new JLabel("Título:");
		GridBagConstraints gbc_tituloLabel = new GridBagConstraints();
		gbc_tituloLabel.insets = new Insets(0, 0, 5, 5);
		gbc_tituloLabel.gridx = 0;
		gbc_tituloLabel.gridy = 1;
		libroCreationPanel.add(tituloLabel, gbc_tituloLabel);

		tituloTextField = new JTextField();
		GridBagConstraints gbc_tituloTextField = new GridBagConstraints();
		gbc_tituloTextField.anchor = GridBagConstraints.NORTH;
		gbc_tituloTextField.insets = new Insets(0, 0, 5, 5);
		gbc_tituloTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_tituloTextField.gridx = 1;
		gbc_tituloTextField.gridy = 1;
		libroCreationPanel.add(tituloTextField, gbc_tituloTextField);
		tituloTextField.setColumns(10);

		JLabel isbnLabel = new JLabel("ISBN:");
		GridBagConstraints gbc_isbnLabel = new GridBagConstraints();
		gbc_isbnLabel.insets = new Insets(0, 0, 5, 5);
		gbc_isbnLabel.gridx = 0;
		gbc_isbnLabel.gridy = 2;
		libroCreationPanel.add(isbnLabel, gbc_isbnLabel);

		isbnTextField = new JTextField();
		GridBagConstraints gbc_isbnTextField = new GridBagConstraints();
		gbc_isbnTextField.insets = new Insets(0, 0, 5, 5);
		gbc_isbnTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_isbnTextField.gridx = 1;
		gbc_isbnTextField.gridy = 2;
		libroCreationPanel.add(isbnTextField, gbc_isbnTextField);
		isbnTextField.setColumns(10);

		JLabel sinopsisLabel = new JLabel("Sinopsis");
		GridBagConstraints gbc_sinopsisLabel = new GridBagConstraints();
		gbc_sinopsisLabel.insets = new Insets(0, 0, 5, 5);
		gbc_sinopsisLabel.gridx = 0;
		gbc_sinopsisLabel.gridy = 3;
		libroCreationPanel.add(sinopsisLabel, gbc_sinopsisLabel);

		JScrollPane imagesScrollPanel = new JScrollPane();
		GridBagConstraints gbc_imagesScrollPanel = new GridBagConstraints();
		gbc_imagesScrollPanel.gridwidth = 2;
		gbc_imagesScrollPanel.gridheight = 3;
		gbc_imagesScrollPanel.insets = new Insets(0, 0, 5, 5);
		gbc_imagesScrollPanel.fill = GridBagConstraints.BOTH;
		gbc_imagesScrollPanel.gridx = 3;
		gbc_imagesScrollPanel.gridy = 1;
		libroCreationPanel.add(imagesScrollPanel, gbc_imagesScrollPanel);

		JPanel panel = new JPanel();
		imagesScrollPanel.setViewportView(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		image1Label = new JLabel("");
		GridBagConstraints gbc_image1Label = new GridBagConstraints();
		gbc_image1Label.insets = new Insets(0, 0, 0, 5);
		gbc_image1Label.gridx = 2;
		gbc_image1Label.gridy = 1;
		panel.add(image1Label, gbc_image1Label);

		image2Label = new JLabel("");
		GridBagConstraints gbc_image2Label = new GridBagConstraints();
		gbc_image2Label.insets = new Insets(0, 0, 0, 5);
		gbc_image2Label.gridx = 3;
		gbc_image2Label.gridy = 1;
		panel.add(image2Label, gbc_image2Label);

		image3Label = new JLabel("");
		GridBagConstraints gbc_image3Label = new GridBagConstraints();
		gbc_image3Label.gridx = 4;
		gbc_image3Label.gridy = 1;
		panel.add(image3Label, gbc_image3Label);

		sinopsisScrollPanel = new JScrollPane();
		GridBagConstraints gbc_sinopsisScrollPanel = new GridBagConstraints();
		gbc_sinopsisScrollPanel.insets = new Insets(0, 0, 5, 5);
		gbc_sinopsisScrollPanel.fill = GridBagConstraints.BOTH;
		gbc_sinopsisScrollPanel.gridx = 1;
		gbc_sinopsisScrollPanel.gridy = 3;
		libroCreationPanel.add(sinopsisScrollPanel, gbc_sinopsisScrollPanel);

		sinopsisTextArea = new JTextArea();
		sinopsisTextArea.setLineWrap(true);
		sinopsisTextArea.setWrapStyleWord(true);
		
		sinopsisScrollPanel.setViewportView(sinopsisTextArea);

		JLabel unidadesLabel = new JLabel("Unidades:");
		GridBagConstraints gbc_unidadesLabel = new GridBagConstraints();
		gbc_unidadesLabel.insets = new Insets(0, 0, 5, 5);
		gbc_unidadesLabel.gridx = 0;
		gbc_unidadesLabel.gridy = 4;
		libroCreationPanel.add(unidadesLabel, gbc_unidadesLabel);

		unidadesValueSpinner = new JSpinner();
		GridBagConstraints gbc_unidadesValueSpinner = new GridBagConstraints();
		gbc_unidadesValueSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_unidadesValueSpinner.gridx = 1;
		gbc_unidadesValueSpinner.gridy = 4;
		libroCreationPanel.add(unidadesValueSpinner, gbc_unidadesValueSpinner);

		addImageButton = new JButton("Subir imágen");
		addImageButton.setEnabled(true);
		addImageButton.setAction(new ShowUploadImageAction(this, "Subir imágen"));
		GridBagConstraints gbc_addImageButton = new GridBagConstraints();
		gbc_addImageButton.insets = new Insets(0, 0, 5, 5);
		gbc_addImageButton.gridx = 3;
		gbc_addImageButton.gridy = 4;
		libroCreationPanel.add(addImageButton, gbc_addImageButton);

		JLabel precioLabel = new JLabel("Precio:");
		GridBagConstraints gbc_precioLabel = new GridBagConstraints();
		gbc_precioLabel.insets = new Insets(0, 0, 5, 5);
		gbc_precioLabel.gridx = 0;
		gbc_precioLabel.gridy = 5;
		libroCreationPanel.add(precioLabel, gbc_precioLabel);

		precioValueSlider = new JSlider();
		precioValueSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				precioValueLabel.setText(String.valueOf(precioValueSlider.getValue()));
			}
		});
		GridBagConstraints gbc_precioValueSlider = new GridBagConstraints();
		gbc_precioValueSlider.fill = GridBagConstraints.HORIZONTAL;
		gbc_precioValueSlider.insets = new Insets(0, 0, 5, 5);
		gbc_precioValueSlider.gridx = 1;
		gbc_precioValueSlider.gridy = 5;
		libroCreationPanel.add(precioValueSlider, gbc_precioValueSlider);

		precioValueLabel = new JLabel("");
		GridBagConstraints gbc_precioValueLabel = new GridBagConstraints();
		gbc_precioValueLabel.insets = new Insets(0, 0, 5, 5);
		gbc_precioValueLabel.gridx = 2;
		gbc_precioValueLabel.gridy = 5;
		libroCreationPanel.add(precioValueLabel, gbc_precioValueLabel);

		JLabel lblFormato = new JLabel("Formato:");
		GridBagConstraints gbc_lblFormato = new GridBagConstraints();
		gbc_lblFormato.insets = new Insets(0, 0, 5, 5);
		gbc_lblFormato.gridx = 3;
		gbc_lblFormato.gridy = 5;
		libroCreationPanel.add(lblFormato, gbc_lblFormato);

		tapaBlandaRadioButton = new JRadioButton("Tapa blanda");
		tapaBlandaRadioButton.setActionCommand("");
		GridBagConstraints gbc_tapaBlandaRadioButton = new GridBagConstraints();
		gbc_tapaBlandaRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_tapaBlandaRadioButton.gridx = 2;
		gbc_tapaBlandaRadioButton.gridy = 6;
		libroCreationPanel.add(tapaBlandaRadioButton, gbc_tapaBlandaRadioButton);

		tapaDuraRadioButton = new JRadioButton("Tapa dura");
		GridBagConstraints gbc_tapaDuraRadioButton = new GridBagConstraints();
		gbc_tapaDuraRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_tapaDuraRadioButton.gridx = 4;
		gbc_tapaDuraRadioButton.gridy = 6;
		libroCreationPanel.add(tapaDuraRadioButton, gbc_tapaDuraRadioButton);

		JLabel fechaPublicacionLabel = new JLabel("Fecha publicación:");
		GridBagConstraints gbc_fechaPublicacionLabel = new GridBagConstraints();
		gbc_fechaPublicacionLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_fechaPublicacionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_fechaPublicacionLabel.gridx = 0;
		gbc_fechaPublicacionLabel.gridy = 8;
		libroCreationPanel.add(fechaPublicacionLabel, gbc_fechaPublicacionLabel);

		fechaPublicacionDateChooser = new JDateChooser();
		GridBagConstraints gbc_fechaPublicacionDateChooser = new GridBagConstraints();
		gbc_fechaPublicacionDateChooser.fill = GridBagConstraints.BOTH;
		gbc_fechaPublicacionDateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_fechaPublicacionDateChooser.gridx = 1;
		gbc_fechaPublicacionDateChooser.gridy = 8;
		libroCreationPanel.add(fechaPublicacionDateChooser, gbc_fechaPublicacionDateChooser);


		bolsilloRadioButton = new JRadioButton("Bolsillo");
		GridBagConstraints gbc_bolsilloRadioButton = new GridBagConstraints();
		gbc_bolsilloRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_bolsilloRadioButton.gridx = 2;
		gbc_bolsilloRadioButton.gridy = 8;
		libroCreationPanel.add(bolsilloRadioButton, gbc_bolsilloRadioButton);

		ebookRadioButton = new JRadioButton("Ebook");
		GridBagConstraints gbc_ebookRadioButton = new GridBagConstraints();
		gbc_ebookRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_ebookRadioButton.gridx = 4;
		gbc_ebookRadioButton.gridy = 8;
		libroCreationPanel.add(ebookRadioButton, gbc_ebookRadioButton);

		JLabel generoLiterarioLabel = new JLabel("Género literario:");
		GridBagConstraints gbc_generoLiterarioLabel = new GridBagConstraints();
		gbc_generoLiterarioLabel.insets = new Insets(0, 0, 5, 5);
		gbc_generoLiterarioLabel.gridx = 0;
		gbc_generoLiterarioLabel.gridy = 11;
		libroCreationPanel.add(generoLiterarioLabel, gbc_generoLiterarioLabel);

		generoLiterarioComboBox = new JComboBox();
		GridBagConstraints gbc_generoLiterarioComboBox = new GridBagConstraints();
		gbc_generoLiterarioComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_generoLiterarioComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_generoLiterarioComboBox.gridx = 1;
		gbc_generoLiterarioComboBox.gridy = 11;
		libroCreationPanel.add(generoLiterarioComboBox, gbc_generoLiterarioComboBox);

		JLabel autorLabel = new JLabel("Autor:");
		GridBagConstraints gbc_autorLabel = new GridBagConstraints();
		gbc_autorLabel.anchor = GridBagConstraints.EAST;
		gbc_autorLabel.insets = new Insets(0, 0, 5, 5);
		gbc_autorLabel.gridx = 2;
		gbc_autorLabel.gridy = 11;
		libroCreationPanel.add(autorLabel, gbc_autorLabel);

		autoresComboBox = new JComboBox();
		GridBagConstraints gbc_autoresComboBox = new GridBagConstraints();
		gbc_autoresComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_autoresComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_autoresComboBox.gridx = 3;
		gbc_autoresComboBox.gridy = 11;
		libroCreationPanel.add(autoresComboBox, gbc_autoresComboBox);

		JLabel lblClasificacinEdad = new JLabel("Clasificación edad:");
		GridBagConstraints gbc_lblClasificacinEdad = new GridBagConstraints();
		gbc_lblClasificacinEdad.insets = new Insets(0, 0, 5, 5);
		gbc_lblClasificacinEdad.gridx = 0;
		gbc_lblClasificacinEdad.gridy = 12;
		libroCreationPanel.add(lblClasificacinEdad, gbc_lblClasificacinEdad);

		clasificacionEdadComboBox = new JComboBox();
		GridBagConstraints gbc_clasificacionEdadComboBox = new GridBagConstraints();
		gbc_clasificacionEdadComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_clasificacionEdadComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_clasificacionEdadComboBox.gridx = 1;
		gbc_clasificacionEdadComboBox.gridy = 12;
		libroCreationPanel.add(clasificacionEdadComboBox, gbc_clasificacionEdadComboBox);

		JLabel formatoLabel = new JLabel("Idiomas:");
		GridBagConstraints gbc_formatoLabel = new GridBagConstraints();
		gbc_formatoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_formatoLabel.gridx = 0;
		gbc_formatoLabel.gridy = 13;
		libroCreationPanel.add(formatoLabel, gbc_formatoLabel);

		idiomasComboBox = new JComboBox();
		GridBagConstraints gbc_idiomasComboBox = new GridBagConstraints();
		gbc_idiomasComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_idiomasComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_idiomasComboBox.gridx = 1;
		gbc_idiomasComboBox.gridy = 13;
		libroCreationPanel.add(idiomasComboBox, gbc_idiomasComboBox);

		JLabel tematicaLabel = new JLabel("Temática:");
		GridBagConstraints gbc_tematicaLabel = new GridBagConstraints();
		gbc_tematicaLabel.insets = new Insets(0, 0, 0, 5);
		gbc_tematicaLabel.gridx = 0;
		gbc_tematicaLabel.gridy = 14;
		libroCreationPanel.add(tematicaLabel, gbc_tematicaLabel);

		tematicaComboBox = new JComboBox();
		GridBagConstraints gbc_tematicaComboBox = new GridBagConstraints();
		gbc_tematicaComboBox.insets = new Insets(0, 0, 0, 5);
		gbc_tematicaComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_tematicaComboBox.gridx = 1;
		gbc_tematicaComboBox.gridy = 14;
		libroCreationPanel.add(tematicaComboBox, gbc_tematicaComboBox);

		JLabel[] labels = {image1Label, image2Label, image3Label};

		postInitialize();
	}

	public void initServices() {
		idiomaService = new IdiomaServiceImpl();
		generoLiterarioService = new GeneroLiterarioServiceImpl();
		clasificacionEdadService = new ClasificacionEdadServiceImpl();
		tematicaService = new TematicaServiceImpl();
		autorService = new AutorServiceImpl();
		fileService = new FileServiceImpl();
	}

	private void postInitialize() {

		try {

			List<Idioma> idiomas = idiomaService.findAll();
			List<GeneroLiterario> generos = generoLiterarioService.findAll();
			List<ClasificacionEdad> clasificaciones = clasificacionEdadService.findAll();
			List<Tematica> tematicas = tematicaService.findAll();
			Results<Autor> autores = autorService.findAll(1, Integer.MAX_VALUE);

			formatosButtonGroup = new ButtonGroup();
			formatosButtonGroup.add(tapaBlandaRadioButton);
			tapaBlandaRadioButton.setActionCommand("1");
			formatosButtonGroup.add(tapaDuraRadioButton);
			tapaDuraRadioButton.setActionCommand("2");
			formatosButtonGroup.add(bolsilloRadioButton);
			bolsilloRadioButton.setActionCommand("3");
			formatosButtonGroup.add(ebookRadioButton);
			ebookRadioButton.setActionCommand("4");

			DefaultComboBoxModel<Idioma> idiomaComboModel =
					new DefaultComboBoxModel<Idioma>(idiomas.toArray(new Idioma[idiomas.size()]));
			idiomasComboBox.setModel(idiomaComboModel);
			idiomasComboBox.setRenderer(new IdiomaListCellRenderer());

			DefaultComboBoxModel<GeneroLiterario> generoLiterarioComboModel =
					new DefaultComboBoxModel<GeneroLiterario>(generos.toArray(new GeneroLiterario[generos.size()]));
			generoLiterarioComboBox.setModel(generoLiterarioComboModel);
			generoLiterarioComboBox.setRenderer(new GeneroListCellRenderer());

			DefaultComboBoxModel<ClasificacionEdad> clasificacionEdadComboModel =
					new DefaultComboBoxModel<ClasificacionEdad>(clasificaciones.toArray(new ClasificacionEdad[clasificaciones.size()]));
			clasificacionEdadComboBox.setModel(clasificacionEdadComboModel);
			clasificacionEdadComboBox.setRenderer(new ClasificacionEdadListCellRenderer());


			DefaultComboBoxModel<Tematica> tematicaComboModel = 
					new DefaultComboBoxModel<Tematica>(tematicas.toArray(new Tematica[tematicas.size()]));
			tematicaComboBox.setModel(tematicaComboModel);
			tematicaComboBox.setRenderer(new TematicaListCellRenderer());


			DefaultComboBoxModel<Autor> autoresComboModel = 
					new DefaultComboBoxModel<Autor>(autores.getPage().toArray(new Autor[autores.getPage().size()]));
			autoresComboBox.setModel(autoresComboModel);
			autoresComboBox.setRenderer(new AutorListCellRenderer());

			IDateEditor fechaLanzamientoDateEditor = fechaPublicacionDateChooser.getDateEditor();
			SwingUtils.paintDateText(fechaLanzamientoDateEditor);


			labels.add(image1Label);
			labels.add(image2Label);
			labels.add(image3Label);


			addImageButton.setEnabled(true);


		}catch(Exception e) {
			logger.fatal(e.getMessage(), e);
		}


	}

	public void setLibroCreadoModel(LibroDTO libro) {
		this.libroCreado = libro;
	}

	public LibroDTO getLibroCreado() {
		return libroCreado;
	}

	protected Integer getSelectedFormato() {
		Integer formatoId = null;
		AbstractButton rb = null;
		for (Enumeration<AbstractButton> rbs = formatosButtonGroup.getElements(); rbs.hasMoreElements(); ) {
			rb = rbs.nextElement();
			if (rb.isSelected()) {
				formatoId = Integer.valueOf(rb.getActionCommand()); 								
			}
		}
		return formatoId;
	}

	public void setModel(LibroDTO libro) {

		libro.setNombre(tituloTextField.getText().trim());
		libro.setIsbn(isbnTextField.getText().trim());
		libro.setUnidades((Integer)unidadesValueSpinner.getValue());
		libro.setFormatoId(getSelectedFormato());
		libro.setPrecio(Double.valueOf(precioValueSlider.getValue()));
		libro.setSinopsis(sinopsisTextArea.getText());

		libro.setFechaPublicacion(fechaPublicacionDateChooser.getDate());

		Idioma idioma = (Idioma) idiomasComboBox.getSelectedItem();
		libro.setIdiomaId(idioma.getId());

		ClasificacionEdad clasificacionEdad = (ClasificacionEdad) clasificacionEdadComboBox.getSelectedItem();
		libro.setClasificacionEdadId(clasificacionEdad.getId());

		GeneroLiterario generoLiterario = (GeneroLiterario) generoLiterarioComboBox.getSelectedItem();
		libro.setGeneroLiterarioId(generoLiterario.getId());

		Tematica tematica = (Tematica) tematicaComboBox.getSelectedItem();
		List<Tematica> tematicas = new ArrayList<Tematica>();
		tematicas.add(tematica);
		libro.setTematicas(tematicas);

		Autor autor = (Autor) autoresComboBox.getSelectedItem();
		List<Autor> autores = new ArrayList<Autor>();
		autores.add(autor);
		libro.setAutores(autores);
	}

	public void showImagesInLabels(List<File> imageFiles, ArrayList<JLabel> labels) {
		try {
			for (int i = 0; i < labels.size() && i < imageFiles.size(); i++) {
				File imageFile = imageFiles.get(i);
				if (imageFile.exists() && imageFile.isFile()) {
					BufferedImage scaledImage = fileService.scaleImage(imageFile, 75, 100);
					ImageIcon icon = new ImageIcon(scaledImage);
					labels.get(i).setIcon(icon); 
				} else {
					labels.get(i).setIcon(null);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setFileList(List<File> fileList) {
		for(File file : fileList) {
			this.fileList.add(file);
		}
	}
	
	public List<File> getFileList(){
		return this.fileList;
	}
	public ArrayList<JLabel> getLabels(){
		return labels;
	}

	public void setJButtonState(boolean flag) {
		this.addImageButton.setEnabled(flag);
	}

}
