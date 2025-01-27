package com.pinguela.thegoldenbook.ui.desktop.view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.model.Pedido;
import com.pinguela.thegoldenbook.service.PedidoCriteria;
import com.pinguela.thegoldenbook.ui.desktop.controller.SearchPedidoAction;
import com.pinguela.thegoldenbook.ui.desktop.model.PedidoTableModel;
import com.pinguela.thegoldenbook.ui.desktop.renderer.PedidoActionsCellEditor;
import com.pinguela.thegoldenbook.ui.desktop.renderer.PedidoActionsCellRenderer;
import com.pinguela.thegoldenbook.ui.desktop.renderer.PedidoTableCellRenderer;
import com.pinguela.thegoldenbook.ui.desktop.utils.SwingUtils;
import com.toedter.calendar.IDateEditor;
import com.toedter.calendar.JDateChooser;

public class PedidoSearchView extends SearchView{

	private JTable pedidosResultsTable;
	private JDateChooser fechaDesdeDateChooser;
	private JSpinner idValueSpinner;
	private JDateChooser fechaHastaDateChooser;
	private JScrollPane pedidoResultsScrollPanel;
	private JSlider precioDesdeSlider;
	private JSlider precioHastaSlider;
	private JLabel precioHastaValueLabel;
	private JLabel precioDesdeValueLabel;
	private JSpinner clienteIdValueSpinner;
	private ButtonGroup estadosButtonGroup;
	private JRadioButton enProcesoRadioButton;
	private JRadioButton enviadoRadioButton;
	private JRadioButton enPreparacionRadioButton;
	private JRadioButton enRepartoRadioButton;

	private PedidoCriteria criteria = null;

	private Logger logger = LogManager.getLogger(PedidoSearchView.class);
	private JRadioButton ventaFísicaRadioButton;
	private JRadioButton entregadoRadioButton;

	public PedidoSearchView() {
		setLayout(new BorderLayout(0, 0));

		JPanel searchFieldsPanel = new JPanel();
		add(searchFieldsPanel, BorderLayout.NORTH);
		GridBagLayout gbl_searchFieldsPanel = new GridBagLayout();
		gbl_searchFieldsPanel.columnWidths = new int[]{134, -25, 0, 0, 0, 0, 116, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_searchFieldsPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_searchFieldsPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_searchFieldsPanel.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		searchFieldsPanel.setLayout(gbl_searchFieldsPanel);

		JLabel idLabel = new JLabel("ID:");
		GridBagConstraints gbc_idLabel = new GridBagConstraints();
		gbc_idLabel.insets = new Insets(0, 0, 5, 5);
		gbc_idLabel.gridx = 0;
		gbc_idLabel.gridy = 1;
		searchFieldsPanel.add(idLabel, gbc_idLabel);

		idValueSpinner = new JSpinner();
		GridBagConstraints gbc_idValueSpinner = new GridBagConstraints();
		gbc_idValueSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_idValueSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_idValueSpinner.gridx = 1;
		gbc_idValueSpinner.gridy = 1;
		searchFieldsPanel.add(idValueSpinner, gbc_idValueSpinner);

		JLabel clienteIdLabel = new JLabel("Cliente ID");
		GridBagConstraints gbc_clienteIdLabel = new GridBagConstraints();
		gbc_clienteIdLabel.insets = new Insets(0, 0, 5, 5);
		gbc_clienteIdLabel.gridx = 5;
		gbc_clienteIdLabel.gridy = 1;
		searchFieldsPanel.add(clienteIdLabel, gbc_clienteIdLabel);

		clienteIdValueSpinner = new JSpinner();
		GridBagConstraints gbc_clienteIdValueSpinner = new GridBagConstraints();
		gbc_clienteIdValueSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_clienteIdValueSpinner.gridx = 6;
		gbc_clienteIdValueSpinner.gridy = 1;
		searchFieldsPanel.add(clienteIdValueSpinner, gbc_clienteIdValueSpinner);

		JLabel estadoPedidoLabel = new JLabel("Estado pedido:");
		GridBagConstraints gbc_estadoPedidoLabel = new GridBagConstraints();
		gbc_estadoPedidoLabel.anchor = GridBagConstraints.WEST;
		gbc_estadoPedidoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_estadoPedidoLabel.gridx = 13;
		gbc_estadoPedidoLabel.gridy = 1;
		searchFieldsPanel.add(estadoPedidoLabel, gbc_estadoPedidoLabel);

		JLabel fechaDesdeLabel = new JLabel("Fecha desde");
		GridBagConstraints gbc_fechaDesdeLabel = new GridBagConstraints();
		gbc_fechaDesdeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_fechaDesdeLabel.gridx = 0;
		gbc_fechaDesdeLabel.gridy = 2;
		searchFieldsPanel.add(fechaDesdeLabel, gbc_fechaDesdeLabel);

		fechaDesdeDateChooser = new JDateChooser();
		fechaDesdeDateChooser.addPropertyChangeListener("date", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				SearchPedidoAction action = new SearchPedidoAction(PedidoSearchView.this, null);
				action.doAction();
			}
		});
		GridBagConstraints gbc_fechaDesdeDateChooser = new GridBagConstraints();
		gbc_fechaDesdeDateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_fechaDesdeDateChooser.fill = GridBagConstraints.BOTH;
		gbc_fechaDesdeDateChooser.gridx = 1;
		gbc_fechaDesdeDateChooser.gridy = 2;
		searchFieldsPanel.add(fechaDesdeDateChooser, gbc_fechaDesdeDateChooser);

		JLabel precioDesdeLabel = new JLabel("Precio desde");
		GridBagConstraints gbc_precioDesdeLabel = new GridBagConstraints();
		gbc_precioDesdeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_precioDesdeLabel.gridx = 5;
		gbc_precioDesdeLabel.gridy = 2;
		searchFieldsPanel.add(precioDesdeLabel, gbc_precioDesdeLabel);

		precioDesdeSlider = new JSlider();
		precioDesdeSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				precioDesdeValueLabel.setText(String.valueOf(precioDesdeSlider.getValue()));
				SearchPedidoAction action = new SearchPedidoAction(PedidoSearchView.this, null);
				action.doAction();
			}
		});
		GridBagConstraints gbc_precioDesdeSlider = new GridBagConstraints();
		gbc_precioDesdeSlider.fill = GridBagConstraints.HORIZONTAL;
		gbc_precioDesdeSlider.insets = new Insets(0, 0, 5, 5);
		gbc_precioDesdeSlider.gridx = 6;
		gbc_precioDesdeSlider.gridy = 2;
		searchFieldsPanel.add(precioDesdeSlider, gbc_precioDesdeSlider);

		precioDesdeValueLabel = new JLabel("");
		GridBagConstraints gbc_precioDesdeValueLabel = new GridBagConstraints();
		gbc_precioDesdeValueLabel.insets = new Insets(0, 0, 5, 5);
		gbc_precioDesdeValueLabel.gridx = 7;
		gbc_precioDesdeValueLabel.gridy = 2;
		searchFieldsPanel.add(precioDesdeValueLabel, gbc_precioDesdeValueLabel);

		enProcesoRadioButton = new JRadioButton("En proceso");
		GridBagConstraints gbc_enProcesoRadioButton = new GridBagConstraints();
		gbc_enProcesoRadioButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_enProcesoRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_enProcesoRadioButton.gridx = 11;
		gbc_enProcesoRadioButton.gridy = 2;
		searchFieldsPanel.add(enProcesoRadioButton, gbc_enProcesoRadioButton);

		enPreparacionRadioButton = new JRadioButton("En preparación");
		GridBagConstraints gbc_enPreparacionRadioButton = new GridBagConstraints();
		gbc_enPreparacionRadioButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_enPreparacionRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_enPreparacionRadioButton.gridx = 13;
		gbc_enPreparacionRadioButton.gridy = 2;
		searchFieldsPanel.add(enPreparacionRadioButton, gbc_enPreparacionRadioButton);

		ventaFísicaRadioButton = new JRadioButton("Venta física");
		GridBagConstraints gbc_ventaFísicaRadioButton = new GridBagConstraints();
		gbc_ventaFísicaRadioButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_ventaFísicaRadioButton.insets = new Insets(0, 0, 5, 0);
		gbc_ventaFísicaRadioButton.gridx = 15;
		gbc_ventaFísicaRadioButton.gridy = 2;
		searchFieldsPanel.add(ventaFísicaRadioButton, gbc_ventaFísicaRadioButton);

		JLabel fechaHastaLabel = new JLabel("Fecha hasta");
		GridBagConstraints gbc_fechaHastaLabel = new GridBagConstraints();
		gbc_fechaHastaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_fechaHastaLabel.gridx = 0;
		gbc_fechaHastaLabel.gridy = 3;
		searchFieldsPanel.add(fechaHastaLabel, gbc_fechaHastaLabel);

		fechaHastaDateChooser = new JDateChooser();
		fechaHastaDateChooser.addPropertyChangeListener("date", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				SearchPedidoAction action = new SearchPedidoAction(PedidoSearchView.this, null);
				action.doAction();
			}
		});
		GridBagConstraints gbc_fechaHastaDateChooser = new GridBagConstraints();
		gbc_fechaHastaDateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_fechaHastaDateChooser.fill = GridBagConstraints.BOTH;
		gbc_fechaHastaDateChooser.gridx = 1;
		gbc_fechaHastaDateChooser.gridy = 3;
		searchFieldsPanel.add(fechaHastaDateChooser, gbc_fechaHastaDateChooser);

		JLabel precioHastaLabel = new JLabel("Precio hasta");
		GridBagConstraints gbc_precioHastaLabel = new GridBagConstraints();
		gbc_precioHastaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_precioHastaLabel.gridx = 5;
		gbc_precioHastaLabel.gridy = 3;
		searchFieldsPanel.add(precioHastaLabel, gbc_precioHastaLabel);

		precioHastaSlider = new JSlider();
		precioHastaSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				precioHastaValueLabel.setText(String.valueOf(precioHastaSlider.getValue()));
				SearchPedidoAction action = new SearchPedidoAction(PedidoSearchView.this, null);
				action.doAction();
			}
		});
		GridBagConstraints gbc_precioHastaSlider = new GridBagConstraints();
		gbc_precioHastaSlider.fill = GridBagConstraints.HORIZONTAL;
		gbc_precioHastaSlider.insets = new Insets(0, 0, 5, 5);
		gbc_precioHastaSlider.gridx = 6;
		gbc_precioHastaSlider.gridy = 3;
		searchFieldsPanel.add(precioHastaSlider, gbc_precioHastaSlider);

		precioHastaValueLabel = new JLabel("");
		GridBagConstraints gbc_precioHastaValueLabel = new GridBagConstraints();
		gbc_precioHastaValueLabel.insets = new Insets(0, 0, 5, 5);
		gbc_precioHastaValueLabel.gridx = 7;
		gbc_precioHastaValueLabel.gridy = 3;
		searchFieldsPanel.add(precioHastaValueLabel, gbc_precioHastaValueLabel);

		enviadoRadioButton = new JRadioButton("Enviado");
		GridBagConstraints gbc_enviadoRadioButton = new GridBagConstraints();
		gbc_enviadoRadioButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_enviadoRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_enviadoRadioButton.gridx = 11;
		gbc_enviadoRadioButton.gridy = 3;
		searchFieldsPanel.add(enviadoRadioButton, gbc_enviadoRadioButton);

		enRepartoRadioButton = new JRadioButton("En reparto");
		GridBagConstraints gbc_enRepartoRadioButton = new GridBagConstraints();
		gbc_enRepartoRadioButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_enRepartoRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_enRepartoRadioButton.gridx = 13;
		gbc_enRepartoRadioButton.gridy = 3;
		searchFieldsPanel.add(enRepartoRadioButton, gbc_enRepartoRadioButton);

		entregadoRadioButton = new JRadioButton("Entregado");
		GridBagConstraints gbc_entregadoRadioButton = new GridBagConstraints();
		gbc_entregadoRadioButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_entregadoRadioButton.insets = new Insets(0, 0, 5, 0);
		gbc_entregadoRadioButton.gridx = 15;
		gbc_entregadoRadioButton.gridy = 3;
		searchFieldsPanel.add(entregadoRadioButton, gbc_entregadoRadioButton);

		JButton buscarButton = new JButton("Buscar");
		buscarButton.setAction(new SearchPedidoAction(this, "Buscar"));
		GridBagConstraints gbc_buscarButton = new GridBagConstraints();
		gbc_buscarButton.gridx = 15;        
		gbc_buscarButton.gridy = 4;
		searchFieldsPanel.add(buscarButton, gbc_buscarButton);

		pedidoResultsScrollPanel = new JScrollPane();
		add(pedidoResultsScrollPanel, BorderLayout.CENTER);


		pedidosResultsTable = new JTable();

		pedidosResultsTable.setRowSelectionAllowed(true);
		pedidosResultsTable.setFocusable(false);
		JPanel paginationPanel = new JPanel();
		add(paginationPanel, BorderLayout.SOUTH);

		postInitialize();

	}

	public void postInitialize() {

		try {
			criteria = new PedidoCriteria();

			pedidoResultsScrollPanel.setViewportView(pedidosResultsTable);

			pedidosResultsTable.setDefaultRenderer(Object.class, new PedidoTableCellRenderer());

			estadosButtonGroup = new ButtonGroup();
			
			estadosButtonGroup.add(ventaFísicaRadioButton);
			ventaFísicaRadioButton.setActionCommand("6");
			estadosButtonGroup.add(entregadoRadioButton);
			entregadoRadioButton.setActionCommand("5");
			estadosButtonGroup.add(enProcesoRadioButton);
			enProcesoRadioButton.setActionCommand("4");
			estadosButtonGroup.add(enPreparacionRadioButton);
			enPreparacionRadioButton.setActionCommand("1");
			estadosButtonGroup.add(enviadoRadioButton);
			enviadoRadioButton.setActionCommand("2");
			estadosButtonGroup.add(enRepartoRadioButton);
			enRepartoRadioButton.setActionCommand("3");
			
			ActionListener radioButtonActionListener = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					SearchPedidoAction action = new SearchPedidoAction(PedidoSearchView.this, null);
					action.doAction();
				}
			};

			// Añadir el ActionListener a cada botón de radio.
			ventaFísicaRadioButton.addActionListener(radioButtonActionListener);
			entregadoRadioButton.addActionListener(radioButtonActionListener);
			enProcesoRadioButton.addActionListener(radioButtonActionListener);
			enPreparacionRadioButton.addActionListener(radioButtonActionListener);
			enviadoRadioButton.addActionListener(radioButtonActionListener);
			enRepartoRadioButton.addActionListener(radioButtonActionListener);

			IDateEditor fechaRealizacionDesdeDateEditor = fechaDesdeDateChooser.getDateEditor();
			SwingUtils.paintDateText(fechaRealizacionDesdeDateEditor);

			IDateEditor fechaRealizacionHastaDateEditor = fechaHastaDateChooser.getDateEditor();
			SwingUtils.paintDateText(fechaRealizacionHastaDateEditor);


		}catch(Exception e) {
			logger.fatal(e.getMessage(), e);
		}
	}

	public void setResultsModel(PedidoTableModel pedidoTableModel) {
		this.pedidosResultsTable.setModel(pedidoTableModel);

		pedidosResultsTable.getColumnModel().getColumn(6).setCellRenderer(new PedidoActionsCellRenderer(this));
		pedidosResultsTable.getColumnModel().getColumn(6).setCellEditor(new PedidoActionsCellEditor(this));

	}

	public void setCriteriaModel(PedidoCriteria criteria) {


		if(Long.valueOf((Integer)idValueSpinner.getValue()) > 0) {
			criteria.setId(Long.valueOf((Integer)idValueSpinner.getValue()));
		}


		criteria.setFechaDesde(fechaDesdeDateChooser.getDate());

		criteria.setFechaHasta(fechaHastaDateChooser.getDate());

		criteria.setPrecioDesde(Double.valueOf(precioDesdeSlider.getValue()));

		criteria.setPrecioHasta(Double.valueOf(precioHastaSlider.getValue()));

		if(Long.valueOf((Integer)clienteIdValueSpinner.getValue()) > 0) {
			criteria.setClienteId(Long.valueOf((Integer)clienteIdValueSpinner.getValue()));
		}


		criteria.setTipoEstadoPedidoId(getSelectedEstado());
	}

	protected Integer getSelectedEstado() {
		Integer estadoId = null;
		AbstractButton rb = null;
		for (Enumeration<AbstractButton> rbs = estadosButtonGroup.getElements(); rbs.hasMoreElements(); ) {
			rb = rbs.nextElement();
			if (rb.isSelected()) {
				estadoId = Integer.valueOf(rb.getActionCommand()); 								
			}
		}
		return estadoId;
	}

	public PedidoCriteria getCriteria() {
		setCriteriaModel(criteria);

		if(criteria.getPrecioDesde() == 50 && criteria.getPrecioHasta() == 50) {
			criteria.setPrecioDesde(null);
			criteria.setPrecioHasta(null);
		}
		return this.criteria;
	}

	@Override
	protected void setTableColumnEditor() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setTableColumnRenderer() {
		// TODO Auto-generated method stub
		
	}

}
