package com.pinguela.thegoldenbook.ui.desktop.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.dao.DataException;
import com.pinguela.thegoldenbook.model.ClienteDTO;
import com.pinguela.thegoldenbook.model.EstadoPedido;
import com.pinguela.thegoldenbook.model.LibroDTO;
import com.pinguela.thegoldenbook.service.EstadoPedidoService;
import com.pinguela.thegoldenbook.service.impl.EstadoPedidoServiceImpl;
import com.pinguela.thegoldenbook.ui.desktop.TheGoldenBookWindow;
import com.pinguela.thegoldenbook.ui.desktop.controller.CreatePedidoAction;
import com.pinguela.thegoldenbook.ui.desktop.controller.ShowClienteSelectionAction;
import com.pinguela.thegoldenbook.ui.desktop.controller.ShowLibroSelectionAction;
import com.pinguela.thegoldenbook.ui.desktop.model.LineaPedidoLibroTableModel;
import com.pinguela.thegoldenbook.ui.desktop.renderer.EstadoPedidoListCellRenderer;
import com.pinguela.thegoldenbook.ui.desktop.renderer.LineaPedidoLibroTableCellRenderer;
import com.toedter.calendar.JDateChooser;

public class CreatePedidoDialog extends TheGoldenBookDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton addLibroButton;
	private JButton addClienteButton;
	private JButton createButton;
	private JButton cancelButton;
	private JTable lineasResultsTable;
	private JPanel dataPanel;
	private JLabel tituloLabel;
	private JLabel idClienteLabel;
	private JLabel nombreClienteLabel;
	private JTextField idValueTextField;
	private JTextField nombreValueTextField;
	private JPanel buttonPanel;
	private JPanel panel;
	private JTextField precioTotalTextField;
	private JLabel precioTotalTitleLabel;
	private List<LibroDTO> selectedLibros;
	private ClienteDTO clienteSeleccionado = null;
	private JLabel correoElectronicoLabel;
	private JTextField correoElectronicoValueTextField;
	private JLabel fechaRealizacionValueLabel;
	private JDateChooser dateChooser;
	
	private EstadoPedidoService tipoEstadoPedidoService = null;
	
	private static Logger logger = LogManager.getLogger();
	private JLabel estadoPedidoLabel;
	private JComboBox comboBoxValueEstadoPedido;

	/**
	 * Create the dialog.
	 */
	public CreatePedidoDialog() {
		initServices();
		setBounds(100, 100, 1111, 606);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				lineasResultsTable = new JTable();
				scrollPane.setViewportView(lineasResultsTable);
			}
		}
		{
			dataPanel = new JPanel();
			contentPanel.add(dataPanel, BorderLayout.NORTH);
			GridBagLayout gbl_dataPanel = new GridBagLayout();
			gbl_dataPanel.columnWidths = new int[]{110, 46, 0, 40, 96, 84, 141, 0, 0, 0};
			gbl_dataPanel.rowHeights = new int[]{24, 0, 0, 0};
			gbl_dataPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_dataPanel.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
			dataPanel.setLayout(gbl_dataPanel);
			{
				tituloLabel = new JLabel("Creación de pedido:");
				tituloLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
				GridBagConstraints gbc_tituloLabel = new GridBagConstraints();
				gbc_tituloLabel.gridwidth = 9;
				gbc_tituloLabel.anchor = GridBagConstraints.NORTH;
				gbc_tituloLabel.insets = new Insets(0, 0, 5, 0);
				gbc_tituloLabel.gridx = 0;
				gbc_tituloLabel.gridy = 0;
				dataPanel.add(tituloLabel, gbc_tituloLabel);
			}
			{
				fechaRealizacionValueLabel = new JLabel("Fecha de realización");
				GridBagConstraints gbc_fechaRealizacionValueLabel = new GridBagConstraints();
				gbc_fechaRealizacionValueLabel.insets = new Insets(0, 0, 5, 5);
				gbc_fechaRealizacionValueLabel.gridx = 0;
				gbc_fechaRealizacionValueLabel.gridy = 1;
				dataPanel.add(fechaRealizacionValueLabel, gbc_fechaRealizacionValueLabel);
			}
			{
				dateChooser = new JDateChooser();
				GridBagConstraints gbc_dateChooser = new GridBagConstraints();
				gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
				gbc_dateChooser.fill = GridBagConstraints.BOTH;
				gbc_dateChooser.gridx = 1;
				gbc_dateChooser.gridy = 1;
				dataPanel.add(dateChooser, gbc_dateChooser);
			}
			{
				estadoPedidoLabel = new JLabel("Estado:");
				GridBagConstraints gbc_estadoPedidoLabel = new GridBagConstraints();
				gbc_estadoPedidoLabel.anchor = GridBagConstraints.EAST;
				gbc_estadoPedidoLabel.insets = new Insets(0, 0, 5, 5);
				gbc_estadoPedidoLabel.gridx = 2;
				gbc_estadoPedidoLabel.gridy = 1;
				dataPanel.add(estadoPedidoLabel, gbc_estadoPedidoLabel);
			}
			{
				comboBoxValueEstadoPedido = new JComboBox();
				GridBagConstraints gbc_comboBoxValueEstadoPedido = new GridBagConstraints();
				gbc_comboBoxValueEstadoPedido.insets = new Insets(0, 0, 5, 5);
				gbc_comboBoxValueEstadoPedido.fill = GridBagConstraints.HORIZONTAL;
				gbc_comboBoxValueEstadoPedido.gridx = 3;
				gbc_comboBoxValueEstadoPedido.gridy = 1;
				dataPanel.add(comboBoxValueEstadoPedido, gbc_comboBoxValueEstadoPedido);
			}
			{
				idClienteLabel = new JLabel("ID cliente:");
				idClienteLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
				GridBagConstraints gbc_idClienteLabel = new GridBagConstraints();
				gbc_idClienteLabel.insets = new Insets(0, 0, 0, 5);
				gbc_idClienteLabel.gridx = 0;
				gbc_idClienteLabel.gridy = 2;
				dataPanel.add(idClienteLabel, gbc_idClienteLabel);
			}
			{
				idValueTextField = new JTextField();
				idValueTextField.setEnabled(false);
				GridBagConstraints gbc_idValueTextField = new GridBagConstraints();
				gbc_idValueTextField.anchor = GridBagConstraints.WEST;
				gbc_idValueTextField.insets = new Insets(0, 0, 0, 5);
				gbc_idValueTextField.gridx = 1;
				gbc_idValueTextField.gridy = 2;
				dataPanel.add(idValueTextField, gbc_idValueTextField);
				idValueTextField.setColumns(10);
			}
			{
				nombreClienteLabel = new JLabel("Nombre:");
				nombreClienteLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
				GridBagConstraints gbc_nombreClienteLabel = new GridBagConstraints();
				gbc_nombreClienteLabel.anchor = GridBagConstraints.WEST;
				gbc_nombreClienteLabel.insets = new Insets(0, 0, 0, 5);
				gbc_nombreClienteLabel.gridx = 2;
				gbc_nombreClienteLabel.gridy = 2;
				dataPanel.add(nombreClienteLabel, gbc_nombreClienteLabel);
			}
			{
				nombreValueTextField = new JTextField();
				nombreValueTextField.setEnabled(false);
				GridBagConstraints gbc_nombreValueTextField = new GridBagConstraints();
				gbc_nombreValueTextField.anchor = GridBagConstraints.WEST;
				gbc_nombreValueTextField.insets = new Insets(0, 0, 0, 5);
				gbc_nombreValueTextField.gridx = 3;
				gbc_nombreValueTextField.gridy = 2;
				dataPanel.add(nombreValueTextField, gbc_nombreValueTextField);
				nombreValueTextField.setColumns(10);
			}
			{
				addClienteButton = new JButton();
				addClienteButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				{
					correoElectronicoLabel = new JLabel("Corrreo electrónico");
					GridBagConstraints gbc_correoElectronicoLabel = new GridBagConstraints();
					gbc_correoElectronicoLabel.anchor = GridBagConstraints.EAST;
					gbc_correoElectronicoLabel.insets = new Insets(0, 0, 0, 5);
					gbc_correoElectronicoLabel.gridx = 4;
					gbc_correoElectronicoLabel.gridy = 2;
					dataPanel.add(correoElectronicoLabel, gbc_correoElectronicoLabel);
				}
				{
					correoElectronicoValueTextField = new JTextField();
					GridBagConstraints gbc_correoElectronicoValueTextField = new GridBagConstraints();
					gbc_correoElectronicoValueTextField.insets = new Insets(0, 0, 0, 5);
					gbc_correoElectronicoValueTextField.gridx = 5;
					gbc_correoElectronicoValueTextField.gridy = 2;
					dataPanel.add(correoElectronicoValueTextField, gbc_correoElectronicoValueTextField);
					correoElectronicoValueTextField.setColumns(10);
				}
				GridBagConstraints gbc_addClienteButton = new GridBagConstraints();
				gbc_addClienteButton.insets = new Insets(0, 0, 0, 5);
				gbc_addClienteButton.anchor = GridBagConstraints.WEST;
				gbc_addClienteButton.gridx = 7;
				gbc_addClienteButton.gridy = 2;
				dataPanel.add(addClienteButton, gbc_addClienteButton);
				addClienteButton.setAction(new ShowClienteSelectionAction(this, "Añadir cliente", new ImageIcon(TheGoldenBookWindow.class.getResource("/nuvola/32x32/1294_doctor_medical_dentist_health_health_medical_dentist_doctor.png"))));
			}
			{
				addLibroButton = new JButton();
				GridBagConstraints gbc_addLibroButton = new GridBagConstraints();
				gbc_addLibroButton.gridx = 8;
				gbc_addLibroButton.gridy = 2;
				dataPanel.add(addLibroButton, gbc_addLibroButton);
				addLibroButton.setAction(new ShowLibroSelectionAction(this, "Añadir libro", new ImageIcon(TheGoldenBookWindow.class.getResource("/nuvola/32x32/1727_add_add.png"))));
			}
		}
		{
			JPanel southPanel = new JPanel();
			getContentPane().add(southPanel, BorderLayout.SOUTH);
			southPanel.setLayout(new BorderLayout(0, 0));
			{

				{
					buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
					southPanel.add(buttonPanel, BorderLayout.SOUTH);
					buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
					{
					}
					{
						createButton = new JButton("Crear");
						createButton.setAction(new CreatePedidoAction(this, "Crear"));
						createButton.setPreferredSize(new Dimension(200,25));
						createButton.setActionCommand("OK");
						buttonPanel.add(createButton);
						createButton.setVisible(true);
					}
					{
						cancelButton = new JButton("Cancelar");
						cancelButton.setPreferredSize(new Dimension(200,25));
						cancelButton.setActionCommand("Cancel");
						buttonPanel.add(cancelButton);
					}
					panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
					southPanel.add(panel, BorderLayout.NORTH);
					panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
					{
						precioTotalTitleLabel = new JLabel("Precio total:");
						precioTotalTitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
						panel.add(precioTotalTitleLabel);
					}
					{
						precioTotalTextField = new JTextField("");
						precioTotalTextField.setPreferredSize(new Dimension(200,25));
						panel.add(precioTotalTextField);
					}
				}
			}
		}
			postInitialize();
	}

	private void postInitialize() {
		
		try {
			List<EstadoPedido> estados = tipoEstadoPedidoService.findAll();
			DefaultComboBoxModel<EstadoPedido> model = 
					new DefaultComboBoxModel<EstadoPedido>(estados.toArray(new EstadoPedido[estados.size()]));
			
			comboBoxValueEstadoPedido.setModel(model);
			comboBoxValueEstadoPedido.setRenderer(new EstadoPedidoListCellRenderer());
			
			dateChooser.setDate(new Date());			
		}catch(DataException de) {
			logger.error(de.getMessage(), de);
		}
		
	}

	public void setClienteFields(ClienteDTO cliente) {
		idValueTextField.setText(cliente.getId().toString());
		nombreValueTextField.setText(cliente.getNombre());
		correoElectronicoValueTextField.setText(cliente.getEmail());
		this.clienteSeleccionado = cliente;
	}

	public void setSelectedBooksModel(List<LibroDTO> libros) {
		this.lineasResultsTable.setModel(new LineaPedidoLibroTableModel(libros));
		this.lineasResultsTable.setDefaultRenderer(Object.class, new LineaPedidoLibroTableCellRenderer());
	}

	public ClienteDTO getClienteSeleccionado() {
		return this.clienteSeleccionado;
	}

	public void setPrecioTotal(Double precio) {
		precioTotalTextField.setEnabled(false);
		precioTotalTextField.setText(precio.toString()+" €");
	}

	public List<LibroDTO> getSelectedLibros() {
		return selectedLibros;
	}

	public void setSelectedLibros(List<LibroDTO> selectedLibros) {
		this.selectedLibros = selectedLibros;
	}
	
	private void initServices() {
		tipoEstadoPedidoService = new EstadoPedidoServiceImpl();
	}
	
	public Object getSelectedEstado() {
		return comboBoxValueEstadoPedido.getSelectedItem();
	}

}
