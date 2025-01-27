package com.pinguela.thegoldenbook.ui.desktop.view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.model.ClienteDTO;
import com.pinguela.thegoldenbook.model.EstadoPedido;
import com.pinguela.thegoldenbook.model.LineaPedido;
import com.pinguela.thegoldenbook.model.Pedido;
import com.pinguela.thegoldenbook.ui.desktop.controller.UpdatePedidoAction;
import com.pinguela.thegoldenbook.ui.desktop.model.LineaPedidoTableModel;
import com.pinguela.thegoldenbook.ui.desktop.renderer.EstadoPedidoListCellRenderer;
import com.pinguela.thegoldenbook.ui.desktop.renderer.LineaPedidoTableCellRenderer;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PedidoDetailView extends View {

	private static final long serialVersionUID = 1L;
	private JTable resultsTable;
	private JScrollPane lineasPedidoScrollPanel;
	private JDateChooser fechaRealizacionDateChooser;
	private JLabel lblPrecioTotal;
	private JTextField precioValueTextField;
	private JLabel idValueLabel;
	private JComboBox estadoPedidoValueComboBox;
	private JLabel estadoPedidoLabel;
	private JLabel clienteLabel;
	private JLabel idClienteLabel;
	private JTextField idClienteValueTextField;
	private JLabel lblNombreUsuario;
	private JTextField nombreUsuarioValueTextField;
	private JLabel lblDireccionDeEntrega;
	private JTextField nombreViaValueTextField;
	private JTextField dirViaValueTextField;
	private JTextField localidadNombreValueTextField;
	private JTextField provinciaNombreValueTextField;
	private JTextField paisNombreValueTextField;
	private JLabel nombreViaLabel;
	private JLabel otrosDatosLabel;
	private JLabel localidadLabel;
	private JLabel provinciaLabel;
	private JLabel paisLabel;

	private Pedido pedido = null;
	private PedidoSearchView view;

	private static Logger logger = LogManager.getLogger(PedidoDetailView.class);
	private JPanel buttonPanel;
	private JButton editButton;
	private JButton saveButton;

	/**
	 * Create the panel.
	 */
	public PedidoDetailView(PedidoSearchView view, Pedido p) {
		this.pedido = p;
		this.view = view;
		setLayout(new BorderLayout(0, 0));

		lineasPedidoScrollPanel = new JScrollPane();
		add(lineasPedidoScrollPanel, BorderLayout.CENTER);



		JPanel headDataPanel = new JPanel();
		add(headDataPanel, BorderLayout.NORTH);
		GridBagLayout gbl_headDataPanel = new GridBagLayout();
		gbl_headDataPanel.columnWidths = new int[]{0, 79, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_headDataPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_headDataPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_headDataPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		headDataPanel.setLayout(gbl_headDataPanel);

		JLabel tituloLabel = new JLabel("Datos del pedido:");
		GridBagConstraints gbc_tituloLabel = new GridBagConstraints();
		gbc_tituloLabel.insets = new Insets(0, 0, 5, 5);
		gbc_tituloLabel.gridx = 2;
		gbc_tituloLabel.gridy = 1;
		headDataPanel.add(tituloLabel, gbc_tituloLabel);

		clienteLabel = new JLabel("Cliente:");
		GridBagConstraints gbc_clienteLabel = new GridBagConstraints();
		gbc_clienteLabel.insets = new Insets(0, 0, 5, 5);
		gbc_clienteLabel.gridx = 9;
		gbc_clienteLabel.gridy = 1;
		headDataPanel.add(clienteLabel, gbc_clienteLabel);

		JLabel idLabel = new JLabel("Identificador:");
		GridBagConstraints gbc_idLabel = new GridBagConstraints();
		gbc_idLabel.insets = new Insets(0, 0, 5, 5);
		gbc_idLabel.gridx = 1;
		gbc_idLabel.gridy = 3;
		headDataPanel.add(idLabel, gbc_idLabel);

		JLabel fechaLabel = new JLabel("Fecha Realización");
		GridBagConstraints gbc_fechaLabel = new GridBagConstraints();
		gbc_fechaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_fechaLabel.gridx = 3;
		gbc_fechaLabel.gridy = 3;
		headDataPanel.add(fechaLabel, gbc_fechaLabel);

		idClienteLabel = new JLabel("ID Cliente");
		GridBagConstraints gbc_idClienteLabel = new GridBagConstraints();
		gbc_idClienteLabel.insets = new Insets(0, 0, 5, 5);
		gbc_idClienteLabel.gridx = 9;
		gbc_idClienteLabel.gridy = 3;
		headDataPanel.add(idClienteLabel, gbc_idClienteLabel);

		idValueLabel = new JLabel("");
		GridBagConstraints gbc_idValueLabel = new GridBagConstraints();
		gbc_idValueLabel.insets = new Insets(0, 0, 5, 5);
		gbc_idValueLabel.gridx = 1;
		gbc_idValueLabel.gridy = 4;
		headDataPanel.add(idValueLabel, gbc_idValueLabel);

		fechaRealizacionDateChooser = new JDateChooser();
		GridBagConstraints gbc_fechaRealizacionDateChooser = new GridBagConstraints();
		gbc_fechaRealizacionDateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_fechaRealizacionDateChooser.fill = GridBagConstraints.BOTH;
		gbc_fechaRealizacionDateChooser.gridx = 3;
		gbc_fechaRealizacionDateChooser.gridy = 4;
		headDataPanel.add(fechaRealizacionDateChooser, gbc_fechaRealizacionDateChooser);

		idClienteValueTextField = new JTextField();
		idClienteValueTextField.setEditable(false);
		GridBagConstraints gbc_idClienteValueTextField = new GridBagConstraints();
		gbc_idClienteValueTextField.insets = new Insets(0, 0, 5, 5);
		gbc_idClienteValueTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_idClienteValueTextField.gridx = 9;
		gbc_idClienteValueTextField.gridy = 4;
		headDataPanel.add(idClienteValueTextField, gbc_idClienteValueTextField);
		idClienteValueTextField.setColumns(10);

		estadoPedidoLabel = new JLabel("Estado del pedido");
		GridBagConstraints gbc_estadoPedidoLabel = new GridBagConstraints();
		gbc_estadoPedidoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_estadoPedidoLabel.gridx = 1;
		gbc_estadoPedidoLabel.gridy = 7;
		headDataPanel.add(estadoPedidoLabel, gbc_estadoPedidoLabel);

		lblPrecioTotal = new JLabel("Precio total:");
		GridBagConstraints gbc_lblPrecioTotal = new GridBagConstraints();
		gbc_lblPrecioTotal.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrecioTotal.gridx = 3;
		gbc_lblPrecioTotal.gridy = 7;
		headDataPanel.add(lblPrecioTotal, gbc_lblPrecioTotal);

		lblNombreUsuario = new JLabel("Nombre usuario:");
		GridBagConstraints gbc_lblNombreUsuario = new GridBagConstraints();
		gbc_lblNombreUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreUsuario.gridx = 9;
		gbc_lblNombreUsuario.gridy = 7;
		headDataPanel.add(lblNombreUsuario, gbc_lblNombreUsuario);

		estadoPedidoValueComboBox = new JComboBox();
		estadoPedidoValueComboBox.setEnabled(false);
		GridBagConstraints gbc_estadoPedidoValueComboBox = new GridBagConstraints();
		gbc_estadoPedidoValueComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_estadoPedidoValueComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_estadoPedidoValueComboBox.gridx = 1;
		gbc_estadoPedidoValueComboBox.gridy = 8;
		headDataPanel.add(estadoPedidoValueComboBox, gbc_estadoPedidoValueComboBox);

		precioValueTextField = new JTextField();
		precioValueTextField.setEditable(false);
		GridBagConstraints gbc_precioValueTextField = new GridBagConstraints();
		gbc_precioValueTextField.insets = new Insets(0, 0, 5, 5);
		gbc_precioValueTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_precioValueTextField.gridx = 3;
		gbc_precioValueTextField.gridy = 8;
		headDataPanel.add(precioValueTextField, gbc_precioValueTextField);
		precioValueTextField.setColumns(10);

		nombreUsuarioValueTextField = new JTextField();
		nombreUsuarioValueTextField.setEditable(false);
		GridBagConstraints gbc_nombreUsuarioValueTextField = new GridBagConstraints();
		gbc_nombreUsuarioValueTextField.insets = new Insets(0, 0, 5, 5);
		gbc_nombreUsuarioValueTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nombreUsuarioValueTextField.gridx = 9;
		gbc_nombreUsuarioValueTextField.gridy = 8;
		headDataPanel.add(nombreUsuarioValueTextField, gbc_nombreUsuarioValueTextField);
		nombreUsuarioValueTextField.setColumns(10);

		lblDireccionDeEntrega = new JLabel("Direccion de entrega:");
		GridBagConstraints gbc_lblDireccionDeEntrega = new GridBagConstraints();
		gbc_lblDireccionDeEntrega.insets = new Insets(0, 0, 5, 5);
		gbc_lblDireccionDeEntrega.gridx = 6;
		gbc_lblDireccionDeEntrega.gridy = 10;
		headDataPanel.add(lblDireccionDeEntrega, gbc_lblDireccionDeEntrega);

		nombreViaLabel = new JLabel("Nombre vía:");
		GridBagConstraints gbc_nombreViaLabel = new GridBagConstraints();
		gbc_nombreViaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nombreViaLabel.gridx = 1;
		gbc_nombreViaLabel.gridy = 11;
		headDataPanel.add(nombreViaLabel, gbc_nombreViaLabel);

		otrosDatosLabel = new JLabel("Otros datos:");
		GridBagConstraints gbc_otrosDatosLabel = new GridBagConstraints();
		gbc_otrosDatosLabel.insets = new Insets(0, 0, 5, 5);
		gbc_otrosDatosLabel.gridx = 3;
		gbc_otrosDatosLabel.gridy = 11;
		headDataPanel.add(otrosDatosLabel, gbc_otrosDatosLabel);

		localidadLabel = new JLabel("Localidad:");
		GridBagConstraints gbc_localidadLabel = new GridBagConstraints();
		gbc_localidadLabel.insets = new Insets(0, 0, 5, 5);
		gbc_localidadLabel.gridx = 6;
		gbc_localidadLabel.gridy = 11;
		headDataPanel.add(localidadLabel, gbc_localidadLabel);

		provinciaLabel = new JLabel("Provincia:");
		GridBagConstraints gbc_provinciaLabel = new GridBagConstraints();
		gbc_provinciaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_provinciaLabel.gridx = 9;
		gbc_provinciaLabel.gridy = 11;
		headDataPanel.add(provinciaLabel, gbc_provinciaLabel);

		paisLabel = new JLabel("País:");
		GridBagConstraints gbc_paisLabel = new GridBagConstraints();
		gbc_paisLabel.insets = new Insets(0, 0, 5, 5);
		gbc_paisLabel.gridx = 11;
		gbc_paisLabel.gridy = 11;
		headDataPanel.add(paisLabel, gbc_paisLabel);

		nombreViaValueTextField = new JTextField();
		nombreViaValueTextField.setEditable(false);
		GridBagConstraints gbc_nombreViaValueTextField = new GridBagConstraints();
		gbc_nombreViaValueTextField.insets = new Insets(0, 0, 0, 5);
		gbc_nombreViaValueTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nombreViaValueTextField.gridx = 1;
		gbc_nombreViaValueTextField.gridy = 12;
		headDataPanel.add(nombreViaValueTextField, gbc_nombreViaValueTextField);
		nombreViaValueTextField.setColumns(10);

		dirViaValueTextField = new JTextField();
		dirViaValueTextField.setEditable(false);
		GridBagConstraints gbc_dirViaValueTextField = new GridBagConstraints();
		gbc_dirViaValueTextField.insets = new Insets(0, 0, 0, 5);
		gbc_dirViaValueTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_dirViaValueTextField.gridx = 3;
		gbc_dirViaValueTextField.gridy = 12;
		headDataPanel.add(dirViaValueTextField, gbc_dirViaValueTextField);
		dirViaValueTextField.setColumns(10);

		localidadNombreValueTextField = new JTextField();
		localidadNombreValueTextField.setEditable(false);
		GridBagConstraints gbc_localidadNombreValueTextField = new GridBagConstraints();
		gbc_localidadNombreValueTextField.insets = new Insets(0, 0, 0, 5);
		gbc_localidadNombreValueTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_localidadNombreValueTextField.gridx = 6;
		gbc_localidadNombreValueTextField.gridy = 12;
		headDataPanel.add(localidadNombreValueTextField, gbc_localidadNombreValueTextField);
		localidadNombreValueTextField.setColumns(10);

		provinciaNombreValueTextField = new JTextField();
		provinciaNombreValueTextField.setEditable(false);
		GridBagConstraints gbc_provinciaNombreValueTextField = new GridBagConstraints();
		gbc_provinciaNombreValueTextField.insets = new Insets(0, 0, 0, 5);
		gbc_provinciaNombreValueTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_provinciaNombreValueTextField.gridx = 9;
		gbc_provinciaNombreValueTextField.gridy = 12;
		headDataPanel.add(provinciaNombreValueTextField, gbc_provinciaNombreValueTextField);
		provinciaNombreValueTextField.setColumns(10);

		paisNombreValueTextField = new JTextField();
		paisNombreValueTextField.setEditable(false);
		GridBagConstraints gbc_paisNombreValueTextField = new GridBagConstraints();
		gbc_paisNombreValueTextField.insets = new Insets(0, 0, 0, 5);
		gbc_paisNombreValueTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_paisNombreValueTextField.gridx = 11;
		gbc_paisNombreValueTextField.gridy = 12;
		headDataPanel.add(paisNombreValueTextField, gbc_paisNombreValueTextField);
		paisNombreValueTextField.setColumns(10);
		
		buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.SOUTH);
		GridBagLayout gbl_buttonPanel = new GridBagLayout();
		gbl_buttonPanel.columnWidths = new int[]{494, 61, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_buttonPanel.rowHeights = new int[]{23, 0};
		gbl_buttonPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_buttonPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		buttonPanel.setLayout(gbl_buttonPanel);
		
		editButton = new JButton("Editar");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				estadoPedidoValueComboBox.setEnabled(true);
			}
		});
		GridBagConstraints gbc_editButton = new GridBagConstraints();
		gbc_editButton.insets = new Insets(0, 0, 0, 5);
		gbc_editButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_editButton.gridx = 1;
		gbc_editButton.gridy = 0;
		buttonPanel.add(editButton, gbc_editButton);
		
		saveButton = new JButton();
		saveButton.setAction(new UpdatePedidoAction(view, this, "Gurdar"));
		GridBagConstraints gbc_saveButton = new GridBagConstraints();
		gbc_saveButton.gridx = 15;
		gbc_saveButton.gridy = 0;
		buttonPanel.add(saveButton, gbc_saveButton);

		postInitialize();

	}

	private void postInitialize() {
		
		resultsTable = new JTable();
		lineasPedidoScrollPanel.setViewportView(resultsTable);  
		
		fechaRealizacionDateChooser.setEnabled(false);
	
		
		estadoPedidoValueComboBox.setRenderer(new EstadoPedidoListCellRenderer());
		
		resultsTable.setDefaultRenderer(Object.class, new LineaPedidoTableCellRenderer());
		
	}

	public void setResultsTableModel(LineaPedidoTableModel model) {
		resultsTable.setModel(model);
	}
	
	public void setComboModel(DefaultComboBoxModel<EstadoPedido> estados) {
		estadoPedidoValueComboBox.setModel(estados);

	}

	public void setComboEstadoSelectedValue(int index) {
		this.estadoPedidoValueComboBox.setSelectedIndex(index);
	}

	public void setPedidoDataFields(Pedido pedido) {

		resultsTable.setModel(new LineaPedidoTableModel(pedido.getLineas()));
		resultsTable.setDefaultRenderer(Object.class, new LineaPedidoTableCellRenderer());

		idValueLabel.setText(pedido.getId().toString());
		fechaRealizacionDateChooser.setDate(pedido.getFechaRealizacion());

		precioValueTextField.setText(pedido.getPrecio().toString());
		idClienteValueTextField.setText(pedido.getClienteId().toString());
		nombreUsuarioValueTextField.setText(pedido.getNickname());

	}

	public void setClienteDireccionFields(ClienteDTO cliente) {
		
		if(cliente.getDirecciones().isEmpty()) {
			
		}else {
			nombreViaValueTextField.setText(cliente.getDirecciones().get(0).getNombreVia());
			dirViaValueTextField.setText(cliente.getDirecciones().get(0).getDirVia());
			localidadNombreValueTextField.setText(cliente.getDirecciones().get(0).getLocalidadNombre());
			provinciaNombreValueTextField.setText(cliente.getDirecciones().get(0).getProvinciaNombre());
			paisNombreValueTextField.setText(cliente.getDirecciones().get(0).getPaisNombre());
		}
		
	}
	
	public Pedido getPedido() {
		EstadoPedido estado = (EstadoPedido) estadoPedidoValueComboBox.getSelectedItem();
		this.pedido.setTipoEstadoPedidoId(estado.getId());
		return this.pedido;
	}

}
