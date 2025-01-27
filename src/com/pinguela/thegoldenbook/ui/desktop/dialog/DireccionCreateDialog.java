package com.pinguela.thegoldenbook.ui.desktop.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.pinguela.thegoldenbook.model.DireccionDTO;
import com.pinguela.thegoldenbook.model.Localidad;
import com.pinguela.thegoldenbook.model.Pais;
import com.pinguela.thegoldenbook.model.Provincia;
import com.pinguela.thegoldenbook.ui.desktop.controller.BaseAction;
import com.pinguela.thegoldenbook.ui.desktop.controller.CodigoPostalUpdateFields;
import com.pinguela.thegoldenbook.ui.desktop.controller.CreateDireccionAction;
import com.pinguela.thegoldenbook.ui.desktop.renderer.LocalidadListCellRenderer;
import com.pinguela.thegoldenbook.ui.desktop.renderer.PaisListCellRenderer;
import com.pinguela.thegoldenbook.ui.desktop.renderer.ProvinciaListCellRenderer;

public class DireccionCreateDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nombreCalleValueTextField;
	private JTextField dirViaValueTextField;
	private JTextField codigoPostalValueTextField;
	private JComboBox localidadValueComboBox;
	private JComboBox provinciaValueComboBox;
	private JComboBox paisValueComboBox;
	
	private Long clienteId;
	private JLabel errorMessage;

	/**
	 * Create the dialog.
	 */
	public DireccionCreateDialog(Long id) {
		this.clienteId = id;
		setBounds(100, 100, 856, 462);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel tituloLabel = new JLabel("Nueva dirección:");
			GridBagConstraints gbc_tituloLabel = new GridBagConstraints();
			gbc_tituloLabel.insets = new Insets(0, 0, 5, 5);
			gbc_tituloLabel.gridx = 10;
			gbc_tituloLabel.gridy = 1;
			contentPanel.add(tituloLabel, gbc_tituloLabel);
		}
		{
			errorMessage = new JLabel("Tienes que llenar todos los campos");
			GridBagConstraints gbc_errorMessage = new GridBagConstraints();
			gbc_errorMessage.insets = new Insets(0, 0, 5, 5);
			gbc_errorMessage.gridx = 10;
			gbc_errorMessage.gridy = 3;
			contentPanel.add(errorMessage, gbc_errorMessage);
		}
		{
			JLabel nombreViaLabel = new JLabel("Nombre de la calle:");
			GridBagConstraints gbc_nombreViaLabel = new GridBagConstraints();
			gbc_nombreViaLabel.insets = new Insets(0, 0, 5, 5);
			gbc_nombreViaLabel.gridx = 7;
			gbc_nombreViaLabel.gridy = 4;
			contentPanel.add(nombreViaLabel, gbc_nombreViaLabel);
		}
		{
			JLabel dirViaLabel = new JLabel("Número, puerta, piso...");
			GridBagConstraints gbc_dirViaLabel = new GridBagConstraints();
			gbc_dirViaLabel.insets = new Insets(0, 0, 5, 5);
			gbc_dirViaLabel.gridx = 13;
			gbc_dirViaLabel.gridy = 4;
			contentPanel.add(dirViaLabel, gbc_dirViaLabel);
		}
		{
			nombreCalleValueTextField = new JTextField();
			GridBagConstraints gbc_nombreCalleValueTextField = new GridBagConstraints();
			gbc_nombreCalleValueTextField.insets = new Insets(0, 0, 5, 5);
			gbc_nombreCalleValueTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_nombreCalleValueTextField.gridx = 7;
			gbc_nombreCalleValueTextField.gridy = 5;
			contentPanel.add(nombreCalleValueTextField, gbc_nombreCalleValueTextField);
			nombreCalleValueTextField.setColumns(10);
		}
		{
			dirViaValueTextField = new JTextField();
			GridBagConstraints gbc_dirViaValueTextField = new GridBagConstraints();
			gbc_dirViaValueTextField.insets = new Insets(0, 0, 5, 5);
			gbc_dirViaValueTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_dirViaValueTextField.gridx = 13;
			gbc_dirViaValueTextField.gridy = 5;
			contentPanel.add(dirViaValueTextField, gbc_dirViaValueTextField);
			dirViaValueTextField.setColumns(10);
		}
		{
			JLabel codigoPostalLabel = new JLabel("Código postal:");
			GridBagConstraints gbc_codigoPostalLabel = new GridBagConstraints();
			gbc_codigoPostalLabel.insets = new Insets(0, 0, 5, 5);
			gbc_codigoPostalLabel.gridx = 7;
			gbc_codigoPostalLabel.gridy = 7;
			contentPanel.add(codigoPostalLabel, gbc_codigoPostalLabel);
		}
		{
			JLabel localidadLabel = new JLabel("Localidad:");
			GridBagConstraints gbc_localidadLabel = new GridBagConstraints();
			gbc_localidadLabel.insets = new Insets(0, 0, 5, 5);
			gbc_localidadLabel.gridx = 13;
			gbc_localidadLabel.gridy = 7;
			contentPanel.add(localidadLabel, gbc_localidadLabel);
		}
		{
			codigoPostalValueTextField = new JTextField();
			codigoPostalValueTextField.setAction(new CodigoPostalUpdateFields(this));
			GridBagConstraints gbc_codigoPostalValueTextField = new GridBagConstraints();
			gbc_codigoPostalValueTextField.insets = new Insets(0, 0, 5, 5);
			gbc_codigoPostalValueTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_codigoPostalValueTextField.gridx = 7;
			gbc_codigoPostalValueTextField.gridy = 8;
			contentPanel.add(codigoPostalValueTextField, gbc_codigoPostalValueTextField);
			codigoPostalValueTextField.setColumns(10);
		}
		{
			localidadValueComboBox = new JComboBox();
			GridBagConstraints gbc_localidadValueComboBox = new GridBagConstraints();
			gbc_localidadValueComboBox.insets = new Insets(0, 0, 5, 5);
			gbc_localidadValueComboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_localidadValueComboBox.gridx = 13;
			gbc_localidadValueComboBox.gridy = 8;
			contentPanel.add(localidadValueComboBox, gbc_localidadValueComboBox);
		}
		{
			JLabel provinciaLabel = new JLabel("Provincia:");
			GridBagConstraints gbc_provinciaLabel = new GridBagConstraints();
			gbc_provinciaLabel.insets = new Insets(0, 0, 5, 5);
			gbc_provinciaLabel.gridx = 7;
			gbc_provinciaLabel.gridy = 10;
			contentPanel.add(provinciaLabel, gbc_provinciaLabel);
		}
		{
			JLabel paisLabel = new JLabel("País");
			GridBagConstraints gbc_paisLabel = new GridBagConstraints();
			gbc_paisLabel.insets = new Insets(0, 0, 5, 5);
			gbc_paisLabel.gridx = 13;
			gbc_paisLabel.gridy = 10;
			contentPanel.add(paisLabel, gbc_paisLabel);
		}
		{
			provinciaValueComboBox = new JComboBox();
			GridBagConstraints gbc_provinciaValueComboBox = new GridBagConstraints();
			gbc_provinciaValueComboBox.insets = new Insets(0, 0, 5, 5);
			gbc_provinciaValueComboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_provinciaValueComboBox.gridx = 7;
			gbc_provinciaValueComboBox.gridy = 11;
			contentPanel.add(provinciaValueComboBox, gbc_provinciaValueComboBox);
		}
		{
			paisValueComboBox = new JComboBox();
			GridBagConstraints gbc_paisValueComboBox = new GridBagConstraints();
			gbc_paisValueComboBox.insets = new Insets(0, 0, 5, 5);
			gbc_paisValueComboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_paisValueComboBox.gridx = 13;
			gbc_paisValueComboBox.gridy = 11;
			contentPanel.add(paisValueComboBox, gbc_paisValueComboBox);
		}
		{
			JLabel iconLabel = new JLabel("");
			iconLabel.setIcon(new ImageIcon(DireccionCreateDialog.class.getResource("/icons/icons8-location-94.png")));
			GridBagConstraints gbc_iconLabel = new GridBagConstraints();
			gbc_iconLabel.insets = new Insets(0, 0, 0, 5);
			gbc_iconLabel.gridx = 10;
			gbc_iconLabel.gridy = 12;
			contentPanel.add(iconLabel, gbc_iconLabel);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton saveButton = new JButton("Guardar");
				saveButton.setAction(new CreateDireccionAction(this, "Guardar"));
				saveButton.setActionCommand("OK");
				buttonPane.add(saveButton);
				getRootPane().setDefaultButton(saveButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		postInitialize();
	}
	
	private void postInitialize() {
		
		localidadValueComboBox.setRenderer(new LocalidadListCellRenderer());
		provinciaValueComboBox.setRenderer(new ProvinciaListCellRenderer());
		paisValueComboBox.setRenderer(new PaisListCellRenderer());
		
		errorMessage.setVisible(false);
	}
	
	public String getPostalCode() {
		return codigoPostalValueTextField.getText();
	}
	
	public void setLocation(Localidad l, Provincia p, Pais pais) {
		this.localidadValueComboBox.setSelectedItem(l);
		this.provinciaValueComboBox.setSelectedItem(p);
		this.paisValueComboBox.setSelectedItem(pais);
	}
	
	public void setModel(DefaultComboBoxModel<Pais> pais, DefaultComboBoxModel<Provincia> provincia, DefaultComboBoxModel<Localidad> localidad) {
		localidadValueComboBox.setModel(localidad);
		provinciaValueComboBox.setModel(provincia);
		paisValueComboBox.setModel(pais);
	}
	
	public void setDireccion(DireccionDTO direccion) {
		direccion.setNombreVia(nombreCalleValueTextField.getText());
		direccion.setDirVia(dirViaValueTextField.getText());
		direccion.setClienteId(clienteId);
		direccion.setEmpleadoId(null);
		Localidad localidad = (Localidad) localidadValueComboBox.getSelectedItem();
		direccion.setLocalidadId(localidad.getId());
	}
	
	public JLabel getErrorLabel() {
		return errorMessage;
	}
	

}
