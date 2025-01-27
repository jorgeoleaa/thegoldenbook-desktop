package com.pinguela.thegoldenbook.ui.desktop.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jdesktop.swingx.JXLabel;

import com.pinguela.thegoldenbook.model.AbstractCriteria;
import com.pinguela.thegoldenbook.model.ClasificacionEdad;
import com.pinguela.thegoldenbook.model.GeneroLiterario;
import com.pinguela.thegoldenbook.model.Idioma;
import com.pinguela.thegoldenbook.model.LibroDTO;
import com.pinguela.thegoldenbook.service.ClasificacionEdadService;
import com.pinguela.thegoldenbook.service.GeneroLiterarioService;
import com.pinguela.thegoldenbook.service.IdiomaService;
import com.pinguela.thegoldenbook.service.LibroCriteria;
import com.pinguela.thegoldenbook.service.impl.ClasificacionEdadServiceImpl;
import com.pinguela.thegoldenbook.service.impl.GeneroLiterarioServiceImpl;
import com.pinguela.thegoldenbook.service.impl.IdiomaServiceImpl;
import com.pinguela.thegoldenbook.ui.desktop.TheGoldenBookWindow;
import com.pinguela.thegoldenbook.ui.desktop.controller.BaseAction;
import com.pinguela.thegoldenbook.ui.desktop.controller.LibroPagedSearchAction;
import com.pinguela.thegoldenbook.ui.desktop.controller.PagedSearchAction;
import com.pinguela.thegoldenbook.ui.desktop.model.LibroSearchTableModel;
import com.pinguela.thegoldenbook.ui.desktop.renderer.ClasificacionEdadListCellRenderer;
import com.pinguela.thegoldenbook.ui.desktop.renderer.GeneroListCellRenderer;
import com.pinguela.thegoldenbook.ui.desktop.renderer.IdiomaListCellRenderer;
import com.pinguela.thegoldenbook.ui.desktop.renderer.LibroActionsCellEditor;
import com.pinguela.thegoldenbook.ui.desktop.renderer.LibroActionsCellRenderer;
import com.pinguela.thegoldenbook.ui.desktop.renderer.LibroTableCellRenderer;
import com.pinguela.thegoldenbook.ui.desktop.utils.SwingUtils;
import com.toedter.calendar.IDateEditor;
import com.toedter.calendar.JDateChooser;

public class LibroSearchView extends PaginatedSearchView<LibroDTO>{

	private JTextField isbnValueTextField;
	private JTextField nombreValueTextField;
	private ButtonGroup formatosButtonGroup;
	private JRadioButton tapaBlandaRadioButton;
	private JRadioButton bolsilloRadioButton;
	private JRadioButton tapaDuraRadioButton;
	private JRadioButton ebookRadioButton;
	private JComboBox idiomasValuesComboBox;
	private JSpinner idValueSpinner;
	private JDateChooser fechaPublicacionDesdeDateChooser;
	private JDateChooser fechaPublicacionHastaDateChooser;
	private JSlider precioDesdeSlider;
	private JSlider precioHastaSlider;
	private JComboBox generoValueComboBox;
	private JLabel generoLabel;
	private JLabel edadRecomendadaLabel;
	private JComboBox edadRecomendadaValueComboBox;
	private JLabel precioDesdeValueLabel;
	private JLabel precioHastaValueLabel;
	private JButton buttonBuscar;

	private IdiomaService idiomaService = null;
	private ClasificacionEdadService clasificacionEdadService = null;
	private GeneroLiterarioService generoLiterarioService = null;

	private Logger logger = LogManager.getLogger(LibroSearchView.class);


	public LibroSearchView() {

		GridBagLayout gbl_searchFieldsPanel = new GridBagLayout();
		gbl_searchFieldsPanel.columnWidths = new int[]{117, 192, 92, 200, 200, 0, 0, 200, 105, 0, 17, 0, 102, 54, 25, 0};
		gbl_searchFieldsPanel.rowHeights = new int[]{22, 0, 0, 0, 0, 0};
		gbl_searchFieldsPanel.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_searchFieldsPanel.rowWeights = new double[]{1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getSearchFieldPanel().setLayout(gbl_searchFieldsPanel);

		JLabel labelBusquedaDeLibros = new JLabel("Búsqueda de libros: ");
		GridBagConstraints gbc_labelBusquedaDeLibros = new GridBagConstraints();
		gbc_labelBusquedaDeLibros.insets = new Insets(0, 0, 5, 5);
		gbc_labelBusquedaDeLibros.gridx = 0;
		gbc_labelBusquedaDeLibros.gridy = 0;
		getSearchFieldPanel().add(labelBusquedaDeLibros, gbc_labelBusquedaDeLibros);

		JXLabel idLabel = new JXLabel();
		idLabel.setText("ID");
		GridBagConstraints gbc_idLabel = new GridBagConstraints();
		gbc_idLabel.insets = new Insets(0, 0, 5, 5);
		gbc_idLabel.gridx = 0;
		gbc_idLabel.gridy = 1;
		getSearchFieldPanel().add(idLabel, gbc_idLabel);

		idValueSpinner = new JSpinner();
		GridBagConstraints gbc_idValueSpinner = new GridBagConstraints();
		gbc_idValueSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_idValueSpinner.gridx = 1;
		gbc_idValueSpinner.gridy = 1;
		getSearchFieldPanel().add(idValueSpinner, gbc_idValueSpinner);

		JLabel formatosLabel = new JLabel("Formatos");
		GridBagConstraints gbc_formatosLabel = new GridBagConstraints();
		gbc_formatosLabel.insets = new Insets(0, 0, 5, 5);
		gbc_formatosLabel.gridx = 8;
		gbc_formatosLabel.gridy = 1;
		getSearchFieldPanel().add(formatosLabel, gbc_formatosLabel);

		JLabel labelName = new JLabel("Nombre");
		GridBagConstraints gbc_labelName = new GridBagConstraints();
		gbc_labelName.insets = new Insets(0, 0, 5, 5);
		gbc_labelName.gridx = 0;
		gbc_labelName.gridy = 2;
		getSearchFieldPanel().add(labelName, gbc_labelName);

		nombreValueTextField = new JTextField();
		nombreValueTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				handleNameKeyReleased();
			}
		});
		nombreValueTextField.setColumns(10);
		GridBagConstraints gbc_nombreValueTextField = new GridBagConstraints();
		gbc_nombreValueTextField.insets = new Insets(0, 0, 5, 5);
		gbc_nombreValueTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nombreValueTextField.gridx = 1;
		gbc_nombreValueTextField.gridy = 2;
		getSearchFieldPanel().add(nombreValueTextField, gbc_nombreValueTextField);

		JLabel fechaPublicacionDesdeJLabel = new JLabel("Fecha desde");
		GridBagConstraints gbc_fechaPublicacionDesdeJLabel = new GridBagConstraints();
		gbc_fechaPublicacionDesdeJLabel.insets = new Insets(0, 0, 5, 5);
		gbc_fechaPublicacionDesdeJLabel.gridx = 3;
		gbc_fechaPublicacionDesdeJLabel.gridy = 2;
		getSearchFieldPanel().add(fechaPublicacionDesdeJLabel, gbc_fechaPublicacionDesdeJLabel);

		fechaPublicacionDesdeDateChooser = new JDateChooser();
		fechaPublicacionDesdeDateChooser.addPropertyChangeListener("date", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				LibroPagedSearchAction action = new LibroPagedSearchAction(PagedSearchAction.START, LibroSearchView.this);
				action.doAction();
			}
		});
		fechaPublicacionDesdeDateChooser.setDateFormatString("dd/MM/yyyy");
		GridBagConstraints gbc_fechaPublicacionDesdeDateChooser = new GridBagConstraints();
		gbc_fechaPublicacionDesdeDateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_fechaPublicacionDesdeDateChooser.fill = GridBagConstraints.BOTH;
		gbc_fechaPublicacionDesdeDateChooser.gridx = 4;
		gbc_fechaPublicacionDesdeDateChooser.gridy = 2;
		getSearchFieldPanel().add(fechaPublicacionDesdeDateChooser, gbc_fechaPublicacionDesdeDateChooser);

		bolsilloRadioButton = new JRadioButton("Bolsillo");
		bolsilloRadioButton.setActionCommand("3");
		GridBagConstraints gbc_bolsilloRadioButton = new GridBagConstraints();
		gbc_bolsilloRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_bolsilloRadioButton.gridx = 7;
		gbc_bolsilloRadioButton.gridy = 2;
		getSearchFieldPanel().add(bolsilloRadioButton, gbc_bolsilloRadioButton);

		tapaDuraRadioButton = new JRadioButton("Tapa dura");
		GridBagConstraints gbc_tapaDuraRadioButton = new GridBagConstraints();
		gbc_tapaDuraRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_tapaDuraRadioButton.gridx = 9;
		gbc_tapaDuraRadioButton.gridy = 2;
		getSearchFieldPanel().add(tapaDuraRadioButton, gbc_tapaDuraRadioButton);

		generoLabel = new JLabel("Género");
		GridBagConstraints gbc_generoLabel = new GridBagConstraints();
		gbc_generoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_generoLabel.gridx = 11;
		gbc_generoLabel.gridy = 2;
		getSearchFieldPanel().add(generoLabel, gbc_generoLabel);

		generoValueComboBox = new JComboBox();
		generoValueComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LibroPagedSearchAction action = new LibroPagedSearchAction(PagedSearchAction.START, LibroSearchView.this);
				action.doAction();
			}
		});
		GridBagConstraints gbc_generoValueComboBox = new GridBagConstraints();
		gbc_generoValueComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_generoValueComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_generoValueComboBox.gridx = 12;
		gbc_generoValueComboBox.gridy = 2;
		getSearchFieldPanel().add(generoValueComboBox, gbc_generoValueComboBox);


		JLabel lblIsbn = new JLabel("ISBN");
		GridBagConstraints gbc_lblIsbn = new GridBagConstraints();
		gbc_lblIsbn.insets = new Insets(0, 0, 5, 5);
		gbc_lblIsbn.gridx = 0;
		gbc_lblIsbn.gridy = 3;
		getSearchFieldPanel().add(lblIsbn, gbc_lblIsbn);

		isbnValueTextField = new JTextField();
		LibroSearchView view = this;
		isbnValueTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				handleIsbnKeyReleased();
			}
		});

		isbnValueTextField.setColumns(10);
		GridBagConstraints gbc_isbnValueTextField = new GridBagConstraints();
		gbc_isbnValueTextField.insets = new Insets(0, 0, 5, 5);
		gbc_isbnValueTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_isbnValueTextField.gridx = 1;
		gbc_isbnValueTextField.gridy = 3;
		getSearchFieldPanel().add(isbnValueTextField, gbc_isbnValueTextField);

		JLabel fechaPublicacionHastaJLabel = new JLabel("Fecha hasta");
		GridBagConstraints gbc_fechaPublicacionHastaJLabel = new GridBagConstraints();
		gbc_fechaPublicacionHastaJLabel.insets = new Insets(0, 0, 5, 5);
		gbc_fechaPublicacionHastaJLabel.gridx = 3;
		gbc_fechaPublicacionHastaJLabel.gridy = 3;
		getSearchFieldPanel().add(fechaPublicacionHastaJLabel, gbc_fechaPublicacionHastaJLabel);

		fechaPublicacionHastaDateChooser = new JDateChooser();
		fechaPublicacionHastaDateChooser.addPropertyChangeListener("date", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				setSearchActionComponent();
			}
		});
		GridBagConstraints gbc_fechaPublicacionHastaDateChooser = new GridBagConstraints();
		gbc_fechaPublicacionHastaDateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_fechaPublicacionHastaDateChooser.fill = GridBagConstraints.BOTH;
		gbc_fechaPublicacionHastaDateChooser.gridx = 4;
		gbc_fechaPublicacionHastaDateChooser.gridy = 3;
		getSearchFieldPanel().add(fechaPublicacionHastaDateChooser, gbc_fechaPublicacionHastaDateChooser);

		tapaBlandaRadioButton = new JRadioButton("Tapa blanda");
		tapaBlandaRadioButton.setActionCommand("1");
		GridBagConstraints gbc_tapaBlandaRadioButton = new GridBagConstraints();
		gbc_tapaBlandaRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_tapaBlandaRadioButton.gridx = 7;
		gbc_tapaBlandaRadioButton.gridy = 3;
		getSearchFieldPanel().add(tapaBlandaRadioButton, gbc_tapaBlandaRadioButton);

		ebookRadioButton = new JRadioButton("Ebook");
		GridBagConstraints gbc_ebookRadioButton = new GridBagConstraints();
		gbc_ebookRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_ebookRadioButton.gridx = 9;
		gbc_ebookRadioButton.gridy = 3;
		getSearchFieldPanel().add(ebookRadioButton, gbc_ebookRadioButton);

		edadRecomendadaLabel = new JLabel("Edad recomendada");
		GridBagConstraints gbc_edadRecomendadaLabel = new GridBagConstraints();
		gbc_edadRecomendadaLabel.anchor = GridBagConstraints.EAST;
		gbc_edadRecomendadaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_edadRecomendadaLabel.gridx = 11;
		gbc_edadRecomendadaLabel.gridy = 3;
		getSearchFieldPanel().add(edadRecomendadaLabel, gbc_edadRecomendadaLabel);

		edadRecomendadaValueComboBox = new JComboBox();
		edadRecomendadaValueComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setSearchActionComponent();
			}
		});
		GridBagConstraints gbc_edadRecomendadaValueComboBox = new GridBagConstraints();
		gbc_edadRecomendadaValueComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_edadRecomendadaValueComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_edadRecomendadaValueComboBox.gridx = 12;
		gbc_edadRecomendadaValueComboBox.gridy = 3;
		getSearchFieldPanel().add(edadRecomendadaValueComboBox, gbc_edadRecomendadaValueComboBox);

		JLabel idiomasLabel = new JLabel("Idiomas");
		GridBagConstraints gbc_idiomasLabel = new GridBagConstraints();
		gbc_idiomasLabel.insets = new Insets(0, 0, 0, 5);
		gbc_idiomasLabel.gridx = 0;
		gbc_idiomasLabel.gridy = 4;
		getSearchFieldPanel().add(idiomasLabel, gbc_idiomasLabel);

		idiomasValuesComboBox = new JComboBox();
		idiomasValuesComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setSearchActionComponent();
			}
		});
		GridBagConstraints gbc_idiomasValuesComboBox = new GridBagConstraints();
		gbc_idiomasValuesComboBox.insets = new Insets(0, 0, 0, 5);
		gbc_idiomasValuesComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_idiomasValuesComboBox.gridx = 1;
		gbc_idiomasValuesComboBox.gridy = 4;
		getSearchFieldPanel().add(idiomasValuesComboBox, gbc_idiomasValuesComboBox);

		JLabel precioDesdeLabel = new JLabel("Precio desde");
		GridBagConstraints gbc_precioDesdeLabel = new GridBagConstraints();
		gbc_precioDesdeLabel.insets = new Insets(0, 0, 0, 5);
		gbc_precioDesdeLabel.gridx = 3;
		gbc_precioDesdeLabel.gridy = 4;
		getSearchFieldPanel().add(precioDesdeLabel, gbc_precioDesdeLabel);

		precioDesdeSlider = new JSlider();
		precioDesdeSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				handleDesdeSliderChange();
			}
		});
		GridBagConstraints gbc_precioDesdeSlider = new GridBagConstraints();
		gbc_precioDesdeSlider.fill = GridBagConstraints.HORIZONTAL;
		gbc_precioDesdeSlider.insets = new Insets(0, 0, 0, 5);
		gbc_precioDesdeSlider.gridx = 4;
		gbc_precioDesdeSlider.gridy = 4;
		getSearchFieldPanel().add(precioDesdeSlider, gbc_precioDesdeSlider);

		buttonBuscar = new JButton();
		setSearchAction(new LibroPagedSearchAction(PagedSearchAction.START, this, "Buscar", new ImageIcon(TheGoldenBookWindow.class.getResource("/nuvola/32x32/1877_viewmag_viewmag.png"))));

		precioDesdeValueLabel = new JLabel("");
		GridBagConstraints gbc_precioDesdeValueLabel = new GridBagConstraints();
		gbc_precioDesdeValueLabel.insets = new Insets(0, 0, 0, 5);
		gbc_precioDesdeValueLabel.gridx = 6;
		gbc_precioDesdeValueLabel.gridy = 4;
		getSearchFieldPanel().add(precioDesdeValueLabel, gbc_precioDesdeValueLabel);

		JLabel precioHastaLabel = new JLabel("Precio hasta");
		GridBagConstraints gbc_precioHastaLabel = new GridBagConstraints();
		gbc_precioHastaLabel.insets = new Insets(0, 0, 0, 5);
		gbc_precioHastaLabel.gridx = 7;
		gbc_precioHastaLabel.gridy = 4;
		getSearchFieldPanel().add(precioHastaLabel, gbc_precioHastaLabel);

		precioHastaSlider = new JSlider();
		precioHastaSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				handleHastaSliderChange();
			}
		});
		GridBagConstraints gbc_precioHastaSlider = new GridBagConstraints();
		gbc_precioHastaSlider.fill = GridBagConstraints.HORIZONTAL;
		gbc_precioHastaSlider.insets = new Insets(0, 0, 0, 5);
		gbc_precioHastaSlider.gridx = 8;
		gbc_precioHastaSlider.gridy = 4;
		getSearchFieldPanel().add(precioHastaSlider, gbc_precioHastaSlider);

		precioHastaValueLabel = new JLabel("");
		GridBagConstraints gbc_precioHastaValueLabel = new GridBagConstraints();
		gbc_precioHastaValueLabel.insets = new Insets(0, 0, 0, 5);
		gbc_precioHastaValueLabel.gridx = 10;
		gbc_precioHastaValueLabel.gridy = 4;
		getSearchFieldPanel().add(precioHastaValueLabel, gbc_precioHastaValueLabel);
		GridBagConstraints gbc_buttonBuscar = new GridBagConstraints();
		gbc_buttonBuscar.insets = new Insets(0, 0, 0, 5);
		gbc_buttonBuscar.gridx = 13;
		gbc_buttonBuscar.gridy = 4;
		getSearchFieldPanel().add(buttonBuscar, gbc_buttonBuscar);

		postInitialize();
	}

	private void postInitialize() {

		try {

			formatosButtonGroup = new ButtonGroup();

			formatosButtonGroup.add(bolsilloRadioButton);
			bolsilloRadioButton.setActionCommand("3");
			formatosButtonGroup.add(tapaBlandaRadioButton);
			tapaBlandaRadioButton.setActionCommand("1");
			formatosButtonGroup.add(tapaDuraRadioButton);
			tapaDuraRadioButton.setActionCommand("2");
			formatosButtonGroup.add(ebookRadioButton);	
			ebookRadioButton.setActionCommand("4");

			ActionListener radioButtonActionListener = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					setSearchActionComponent();
				}
			};

			// Añadir el ActionListener a cada botón de radio.
			bolsilloRadioButton.addActionListener(radioButtonActionListener);
			tapaBlandaRadioButton.addActionListener(radioButtonActionListener);
			tapaDuraRadioButton.addActionListener(radioButtonActionListener);
			ebookRadioButton.addActionListener(radioButtonActionListener);


			idiomasValuesComboBox.setRenderer(new IdiomaListCellRenderer());

			generoValueComboBox.setRenderer(new GeneroListCellRenderer());

			edadRecomendadaValueComboBox.setRenderer(new ClasificacionEdadListCellRenderer());

			IDateEditor dateEditorFechaPublicacionDesde = fechaPublicacionDesdeDateChooser.getDateEditor();
			SwingUtils.paintDateText(dateEditorFechaPublicacionDesde);

			IDateEditor dateEditorFechaPublicacionHasta = fechaPublicacionHastaDateChooser.getDateEditor();
			SwingUtils.paintDateText(dateEditorFechaPublicacionHasta);


			initServices();

			List<GeneroLiterario> generos = generoLiterarioService.findAll();
			List<Idioma>idiomas = idiomaService.findAll();
			List<ClasificacionEdad> clasificaciones = clasificacionEdadService.findAll();

			DefaultComboBoxModel<ClasificacionEdad>clasificacionEdadComboModel = 
					new DefaultComboBoxModel<ClasificacionEdad>(clasificaciones.toArray(new ClasificacionEdad[clasificaciones.size()]));

			DefaultComboBoxModel<GeneroLiterario> generoComboModel = 
					new DefaultComboBoxModel<GeneroLiterario>(generos.toArray(new GeneroLiterario[generos.size()]));

			DefaultComboBoxModel<Idioma> idiomaComboModel = 
					new DefaultComboBoxModel<Idioma>(idiomas.toArray(new Idioma[idiomas.size()]));

			setComboModel(clasificacionEdadComboModel, generoComboModel, idiomaComboModel);

			setTableModel(new LibroSearchTableModel(new ArrayList<LibroDTO>()));

			getResultsTable().setDefaultRenderer(Object.class, new LibroTableCellRenderer()); 

			setStart(new LibroPagedSearchAction(PagedSearchAction.START, this, null , new ImageIcon(TheGoldenBookWindow.class.getResource("/nuvola/32x32/1826_player_rev_player_rev.png"))));
			setPrevious(new LibroPagedSearchAction(PagedSearchAction.PREVIOUS, this, null ,new ImageIcon(TheGoldenBookWindow.class.getResource("/nuvola/32x32/1687_1leftarrow_1leftarrow.png"))));
			setNext(new LibroPagedSearchAction(PagedSearchAction.NEXT, this, null, new ImageIcon(TheGoldenBookWindow.class.getResource("/nuvola/32x32/1814_next_arrow_left_left_next_arrow.png"))));
			setEnd(new LibroPagedSearchAction(PagedSearchAction.END, this, null ,new ImageIcon(TheGoldenBookWindow.class.getResource("/nuvola/32x32/1823_fwd_fwd_player_player.png"))));

			idiomasValuesComboBox.setSelectedIndex(1);
			edadRecomendadaValueComboBox.setSelectedIndex(2);
			precioDesdeSlider.setValue(0);
			precioHastaSlider.setValue(100);

		} catch(Exception e) {
			logger.fatal(e.getMessage(), e);
		}


	}


	private void initServices() {

		idiomaService = new IdiomaServiceImpl();
		clasificacionEdadService = new ClasificacionEdadServiceImpl();
		generoLiterarioService = new GeneroLiterarioServiceImpl();
	}

	protected void handleDesdeSliderChange() {
		getPrecioDesdeValueSlider().setText(String.valueOf(getPrecioDesdeSlider().getValue()));
		LibroPagedSearchAction action = new LibroPagedSearchAction(PagedSearchAction.START, LibroSearchView.this);
		action.doAction();

	}

	protected void handleHastaSliderChange() {
		getPrecioHastaValueSlider().setText(String.valueOf(getPrecioHastaSlider().getValue()));
		LibroPagedSearchAction action = new LibroPagedSearchAction(PagedSearchAction.START, LibroSearchView.this);
		action.doAction();

	}

	protected void setSearchActionComponent() {
		LibroPagedSearchAction action = new LibroPagedSearchAction(PagedSearchAction.START, LibroSearchView.this);
		action.doAction();
	}

	protected void handleNameKeyReleased() {
		String nombre = getNombreTextField().getText().trim();
		if (nombre.length() > 1 || nombre.isEmpty()) {
			LibroPagedSearchAction action = new LibroPagedSearchAction(PagedSearchAction.START, LibroSearchView.this);
			action.doAction();
		}
	}

	protected void handleIsbnKeyReleased() {
		String isbn = getIsbnTextField().getText().trim();
		if (isbn.length() > 2 || isbn.isEmpty()) {
			LibroPagedSearchAction action = new LibroPagedSearchAction(PagedSearchAction.START, LibroSearchView.this);
			action.doAction();
		}
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

	public JPanel getSearchFieldsPanel() {
		return  getSearchFieldPanel();
	}



	public void setComboModel(DefaultComboBoxModel<ClasificacionEdad> modelClasificacion,
			DefaultComboBoxModel<GeneroLiterario>modelGenero,
			DefaultComboBoxModel<Idioma> modelIdioma) {

		idiomasValuesComboBox.setModel(modelIdioma);
		generoValueComboBox.setModel(modelGenero);
		edadRecomendadaValueComboBox.setModel(modelClasificacion);

	}

	public LibroCriteria setCriteria(LibroCriteria criteria) {

		if(Long.valueOf((Integer)idValueSpinner.getValue()) > 0 ) {
			criteria.setId(Long.valueOf((Integer)idValueSpinner.getValue()));
		}

		criteria.setIsbn(SwingUtils.getTextFieldValueOrNull(isbnValueTextField));

		criteria.setNombre(SwingUtils.getTextFieldValueOrNull(nombreValueTextField));
		Idioma idioma = (Idioma) idiomasValuesComboBox.getSelectedItem();
		criteria.setIdiomaId(idioma.getId());
		GeneroLiterario genero = (GeneroLiterario) generoValueComboBox.getSelectedItem();
		criteria.setGeneroLiterarioId(genero.getId());
		ClasificacionEdad clasificacionEdad = (ClasificacionEdad) edadRecomendadaValueComboBox.getSelectedItem();
		criteria.setClasificacionEdadId(clasificacionEdad.getId());
		criteria.setFormatoId(getSelectedFormato());
		criteria.setDesdeFecha(fechaPublicacionDesdeDateChooser.getDate());
		criteria.setHastaFecha(fechaPublicacionHastaDateChooser.getDate());
		criteria.setDesdePrecio(Double.valueOf(precioDesdeSlider.getValue()));
		criteria.setHastaPrecio(Double.valueOf(precioHastaSlider.getValue()));

		return criteria;
	}

	@Override
	public AbstractCriteria getCriteria() {

		LibroCriteria criteria = new LibroCriteria();
		if(Long.valueOf((Integer)idValueSpinner.getValue()) > 0 ) {
			criteria.setId(Long.valueOf((Integer)idValueSpinner.getValue()));
		}

		criteria.setIsbn(SwingUtils.getTextFieldValueOrNull(isbnValueTextField));
		criteria.setNombre(SwingUtils.getTextFieldValueOrNull(nombreValueTextField));

		Idioma idioma = (Idioma) idiomasValuesComboBox.getSelectedItem();
		criteria.setIdiomaId(idioma.getId());

		GeneroLiterario genero = (GeneroLiterario) generoValueComboBox.getSelectedItem();
		criteria.setGeneroLiterarioId(genero.getId());

		ClasificacionEdad clasificacionEdad = (ClasificacionEdad) edadRecomendadaValueComboBox.getSelectedItem();
		criteria.setClasificacionEdadId(clasificacionEdad.getId());
		criteria.setFormatoId(getSelectedFormato());

		criteria.setDesdeFecha(fechaPublicacionDesdeDateChooser.getDate());
		criteria.setHastaFecha(fechaPublicacionHastaDateChooser.getDate());
		criteria.setDesdePrecio(Double.valueOf(precioDesdeSlider.getValue()));
		criteria.setHastaPrecio(Double.valueOf(precioHastaSlider.getValue()));

		return criteria;
	}

	protected void setSearchAction(BaseAction action) {
		buttonBuscar.setAction(action);
	}
	@Override
	protected void setTableColumnEditor() {
		getResultsTable().getColumnModel().getColumn(6).setCellEditor(new LibroActionsCellEditor(this));

	}

	@Override
	protected void setTableColumnRenderer() {
		getResultsTable().getColumnModel().getColumn(6).setCellRenderer(new LibroActionsCellRenderer(this));

	}

	protected JLabel getPrecioDesdeValueSlider() {
		return precioDesdeValueLabel;
	}

	protected JTextField getIsbnTextField() {
		return isbnValueTextField;
	}

	protected JTextField getNombreTextField() {
		return nombreValueTextField;
	}

	protected JLabel getPrecioHastaValueSlider() {
		return precioHastaValueLabel;
	}

	protected JSlider getPrecioDesdeSlider() {
		return precioDesdeSlider;
	}

	protected JSlider getPrecioHastaSlider() {
		return precioHastaSlider;
	}
	
	  public Window getTopLevelWindow() {
	        return SwingUtilities.getWindowAncestor(this);
	    }

}
