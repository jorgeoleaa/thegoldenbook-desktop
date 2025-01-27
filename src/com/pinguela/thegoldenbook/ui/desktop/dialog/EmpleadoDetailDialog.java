package com.pinguela.thegoldenbook.ui.desktop.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.pinguela.thegoldenbook.model.EmpleadoDTO;
import com.pinguela.thegoldenbook.model.Localidad;
import com.pinguela.thegoldenbook.model.Pais;
import com.pinguela.thegoldenbook.model.Provincia;
import com.pinguela.thegoldenbook.model.TipoEmpleado;
import com.pinguela.thegoldenbook.ui.desktop.controller.UpdateEmpleadoAction;
import com.pinguela.thegoldenbook.ui.desktop.renderer.LocalidadListCellRenderer;
import com.pinguela.thegoldenbook.ui.desktop.renderer.PaisListCellRenderer;
import com.pinguela.thegoldenbook.ui.desktop.renderer.ProvinciaListCellRenderer;
import com.pinguela.thegoldenbook.ui.desktop.renderer.TipoEmpleadoListCellRenderer;

public class EmpleadoDetailDialog extends TheGoldenBookDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField idValueTextField;
	private JTextField nombreValueTextFIeld;
	private JTextField apellido1ValueTextField;
	private JTextField apellido2ValueTextField;
	private JTextField dniNieValueTextField;
	private JTextField telefonoValueTextField;
	private JTextField mailValueTextField;

	private EmpleadoDTO empleado = null;
	private JTextField nombreViaValueTextField;
	private JTextField dirViaValueTextFIeld;
	private JComboBox localidadValueComboBox;
	private JComboBox provinicaValueComboBox;
	private JComboBox paisValueComboBox;
	private JComboBox tipoEmpleadoComboBox;
	private JPanel empleadoDataPanel;
	private JButton editButton;
	private JButton saveButton;

	/**
	 * Create the dialog.
	 */
	public EmpleadoDetailDialog(EmpleadoDTO model) {
		this.empleado = model;
		setBounds(100, 100, 721, 478);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			empleadoDataPanel = new JPanel();
			contentPanel.add(empleadoDataPanel, BorderLayout.CENTER);
			GridBagLayout gbl_empleadoDataPanel = new GridBagLayout();
			gbl_empleadoDataPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
			gbl_empleadoDataPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			gbl_empleadoDataPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_empleadoDataPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			empleadoDataPanel.setLayout(gbl_empleadoDataPanel);
			{
				JLabel tituuloEmlpleadoLabel = new JLabel("Datos del empleado:");
				GridBagConstraints gbc_tituuloEmlpleadoLabel = new GridBagConstraints();
				gbc_tituuloEmlpleadoLabel.insets = new Insets(0, 0, 5, 5);
				gbc_tituuloEmlpleadoLabel.gridx = 4;
				gbc_tituuloEmlpleadoLabel.gridy = 1;
				empleadoDataPanel.add(tituuloEmlpleadoLabel, gbc_tituuloEmlpleadoLabel);
			}
			{
				JLabel iconLabel = new JLabel("");
				iconLabel.setIcon(new ImageIcon(EmpleadoDetailDialog.class.getResource("/icons/icons8-oficinista-96.png")));
				GridBagConstraints gbc_iconLabel = new GridBagConstraints();
				gbc_iconLabel.insets = new Insets(0, 0, 5, 5);
				gbc_iconLabel.gridx = 4;
				gbc_iconLabel.gridy = 2;
				empleadoDataPanel.add(iconLabel, gbc_iconLabel);
			}
			{
				JLabel identificadorLabel = new JLabel("Identificador:");
				GridBagConstraints gbc_identificadorLabel = new GridBagConstraints();
				gbc_identificadorLabel.insets = new Insets(0, 0, 5, 5);
				gbc_identificadorLabel.gridx = 2;
				gbc_identificadorLabel.gridy = 3;
				empleadoDataPanel.add(identificadorLabel, gbc_identificadorLabel);
			}
			{
				JLabel dniNielLabel = new JLabel("DNI/NIE:");
				GridBagConstraints gbc_dniNielLabel = new GridBagConstraints();
				gbc_dniNielLabel.insets = new Insets(0, 0, 5, 5);
				gbc_dniNielLabel.gridx = 6;
				gbc_dniNielLabel.gridy = 3;
				empleadoDataPanel.add(dniNielLabel, gbc_dniNielLabel);
			}
			{
				idValueTextField = new JTextField();
				idValueTextField.setEnabled(false);
				GridBagConstraints gbc_idValueTextField = new GridBagConstraints();
				gbc_idValueTextField.insets = new Insets(0, 0, 5, 5);
				gbc_idValueTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_idValueTextField.gridx = 2;
				gbc_idValueTextField.gridy = 4;
				empleadoDataPanel.add(idValueTextField, gbc_idValueTextField);
				idValueTextField.setColumns(10);
			}
			{
				dniNieValueTextField = new JTextField();
				dniNieValueTextField.setEnabled(false);
				GridBagConstraints gbc_dniNieValueTextField = new GridBagConstraints();
				gbc_dniNieValueTextField.insets = new Insets(0, 0, 5, 5);
				gbc_dniNieValueTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_dniNieValueTextField.gridx = 6;
				gbc_dniNieValueTextField.gridy = 4;
				empleadoDataPanel.add(dniNieValueTextField, gbc_dniNieValueTextField);
				dniNieValueTextField.setColumns(10);
			}
			{
				JLabel nombreLabel = new JLabel("Nombre:");
				GridBagConstraints gbc_nombreLabel = new GridBagConstraints();
				gbc_nombreLabel.insets = new Insets(0, 0, 5, 5);
				gbc_nombreLabel.gridx = 2;
				gbc_nombreLabel.gridy = 6;
				empleadoDataPanel.add(nombreLabel, gbc_nombreLabel);
			}
			{
				JLabel telefonoLabel = new JLabel("Télefono:");
				GridBagConstraints gbc_telefonoLabel = new GridBagConstraints();
				gbc_telefonoLabel.insets = new Insets(0, 0, 5, 5);
				gbc_telefonoLabel.gridx = 6;
				gbc_telefonoLabel.gridy = 6;
				empleadoDataPanel.add(telefonoLabel, gbc_telefonoLabel);
			}
			{
				nombreValueTextFIeld = new JTextField();
				nombreValueTextFIeld.setEnabled(false);
				GridBagConstraints gbc_nombreValueTextFIeld = new GridBagConstraints();
				gbc_nombreValueTextFIeld.insets = new Insets(0, 0, 5, 5);
				gbc_nombreValueTextFIeld.fill = GridBagConstraints.HORIZONTAL;
				gbc_nombreValueTextFIeld.gridx = 2;
				gbc_nombreValueTextFIeld.gridy = 7;
				empleadoDataPanel.add(nombreValueTextFIeld, gbc_nombreValueTextFIeld);
				nombreValueTextFIeld.setColumns(10);
			}
			{
				telefonoValueTextField = new JTextField();
				telefonoValueTextField.setEnabled(false);
				GridBagConstraints gbc_telefonoValueTextField = new GridBagConstraints();
				gbc_telefonoValueTextField.insets = new Insets(0, 0, 5, 5);
				gbc_telefonoValueTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_telefonoValueTextField.gridx = 6;
				gbc_telefonoValueTextField.gridy = 7;
				empleadoDataPanel.add(telefonoValueTextField, gbc_telefonoValueTextField);
				telefonoValueTextField.setColumns(10);
			}
			{
				JLabel apellido1Label = new JLabel("Primer apellido:");
				GridBagConstraints gbc_apellido1Label = new GridBagConstraints();
				gbc_apellido1Label.insets = new Insets(0, 0, 5, 5);
				gbc_apellido1Label.gridx = 2;
				gbc_apellido1Label.gridy = 9;
				empleadoDataPanel.add(apellido1Label, gbc_apellido1Label);
			}
			{
				JLabel mailLabel = new JLabel("Correo electrónico:");
				GridBagConstraints gbc_mailLabel = new GridBagConstraints();
				gbc_mailLabel.insets = new Insets(0, 0, 5, 5);
				gbc_mailLabel.gridx = 6;
				gbc_mailLabel.gridy = 9;
				empleadoDataPanel.add(mailLabel, gbc_mailLabel);
			}
			{
				apellido1ValueTextField = new JTextField();
				apellido1ValueTextField.setEnabled(false);
				GridBagConstraints gbc_apellido1ValueTextField = new GridBagConstraints();
				gbc_apellido1ValueTextField.insets = new Insets(0, 0, 5, 5);
				gbc_apellido1ValueTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_apellido1ValueTextField.gridx = 2;
				gbc_apellido1ValueTextField.gridy = 10;
				empleadoDataPanel.add(apellido1ValueTextField, gbc_apellido1ValueTextField);
				apellido1ValueTextField.setColumns(10);
			}
			{
				mailValueTextField = new JTextField();
				mailValueTextField.setEnabled(false);
				GridBagConstraints gbc_mailValueTextField = new GridBagConstraints();
				gbc_mailValueTextField.insets = new Insets(0, 0, 5, 5);
				gbc_mailValueTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_mailValueTextField.gridx = 6;
				gbc_mailValueTextField.gridy = 10;
				empleadoDataPanel.add(mailValueTextField, gbc_mailValueTextField);
				mailValueTextField.setColumns(10);
			}
			{
				JLabel apellido2Label = new JLabel("Segundo apellido:");
				GridBagConstraints gbc_apellido2Label = new GridBagConstraints();
				gbc_apellido2Label.insets = new Insets(0, 0, 5, 5);
				gbc_apellido2Label.gridx = 2;
				gbc_apellido2Label.gridy = 12;
				empleadoDataPanel.add(apellido2Label, gbc_apellido2Label);
			}
			{
				JLabel tipoEmpleadoLabel = new JLabel("Tipo de empleado:");
				GridBagConstraints gbc_tipoEmpleadoLabel = new GridBagConstraints();
				gbc_tipoEmpleadoLabel.insets = new Insets(0, 0, 5, 5);
				gbc_tipoEmpleadoLabel.gridx = 6;
				gbc_tipoEmpleadoLabel.gridy = 12;
				empleadoDataPanel.add(tipoEmpleadoLabel, gbc_tipoEmpleadoLabel);
			}
			{
				apellido2ValueTextField = new JTextField();
				apellido2ValueTextField.setEnabled(false);
				GridBagConstraints gbc_apellido2ValueTextField = new GridBagConstraints();
				gbc_apellido2ValueTextField.insets = new Insets(0, 0, 5, 5);
				gbc_apellido2ValueTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_apellido2ValueTextField.gridx = 2;
				gbc_apellido2ValueTextField.gridy = 13;
				empleadoDataPanel.add(apellido2ValueTextField, gbc_apellido2ValueTextField);
				apellido2ValueTextField.setColumns(10);
			}
			{
				tipoEmpleadoComboBox = new JComboBox();
				tipoEmpleadoComboBox.setEnabled(false);
				GridBagConstraints gbc_tipoEmpleadoComboBox = new GridBagConstraints();
				gbc_tipoEmpleadoComboBox.insets = new Insets(0, 0, 5, 5);
				gbc_tipoEmpleadoComboBox.fill = GridBagConstraints.HORIZONTAL;
				gbc_tipoEmpleadoComboBox.gridx = 6;
				gbc_tipoEmpleadoComboBox.gridy = 13;
				empleadoDataPanel.add(tipoEmpleadoComboBox, gbc_tipoEmpleadoComboBox);
			}
			{
				editButton = new JButton("Editar");
				editButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setEditable();
					}
				});
				editButton.setIcon(new ImageIcon(EmpleadoDetailDialog.class.getResource("/nuvola/32x32/1819_pencil_pencil.png")));
				GridBagConstraints gbc_editButton = new GridBagConstraints();
				gbc_editButton.gridx = 7;
				gbc_editButton.gridy = 16;
				empleadoDataPanel.add(editButton, gbc_editButton);
			}
		}
		{
			JPanel direccionDataPanel = new JPanel();
			contentPanel.add(direccionDataPanel, BorderLayout.EAST);
			GridBagLayout gbl_direccionDataPanel = new GridBagLayout();
			gbl_direccionDataPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
			gbl_direccionDataPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			gbl_direccionDataPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_direccionDataPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			direccionDataPanel.setLayout(gbl_direccionDataPanel);
			{
				JLabel direccionTitleLabel = new JLabel("Dirección asociada:");
				GridBagConstraints gbc_direccionTitleLabel = new GridBagConstraints();
				gbc_direccionTitleLabel.insets = new Insets(0, 0, 5, 5);
				gbc_direccionTitleLabel.gridx = 2;
				gbc_direccionTitleLabel.gridy = 1;
				direccionDataPanel.add(direccionTitleLabel, gbc_direccionTitleLabel);
			}
			{
				JLabel iconDireccionLabel = new JLabel("");
				iconDireccionLabel.setIcon(new ImageIcon(EmpleadoDetailDialog.class.getResource("/icons/icons8-dirección-100.png")));
				GridBagConstraints gbc_iconDireccionLabel = new GridBagConstraints();
				gbc_iconDireccionLabel.insets = new Insets(0, 0, 5, 5);
				gbc_iconDireccionLabel.gridx = 2;
				gbc_iconDireccionLabel.gridy = 2;
				direccionDataPanel.add(iconDireccionLabel, gbc_iconDireccionLabel);
			}
			{
				JLabel nombreLabel = new JLabel("Calle:");
				GridBagConstraints gbc_nombreLabel = new GridBagConstraints();
				gbc_nombreLabel.insets = new Insets(0, 0, 5, 5);
				gbc_nombreLabel.gridx = 2;
				gbc_nombreLabel.gridy = 3;
				direccionDataPanel.add(nombreLabel, gbc_nombreLabel);
			}
			{
				nombreViaValueTextField = new JTextField();
				nombreViaValueTextField.setEditable(false);
				nombreViaValueTextField.setEnabled(false);
				GridBagConstraints gbc_nombreViaValueTextField = new GridBagConstraints();
				gbc_nombreViaValueTextField.insets = new Insets(0, 0, 5, 5);
				gbc_nombreViaValueTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_nombreViaValueTextField.gridx = 2;
				gbc_nombreViaValueTextField.gridy = 4;
				direccionDataPanel.add(nombreViaValueTextField, gbc_nombreViaValueTextField);
				nombreViaValueTextField.setColumns(10);
			}
			{
				JLabel dirViaLabel = new JLabel("Número, piso...");
				GridBagConstraints gbc_dirViaLabel = new GridBagConstraints();
				gbc_dirViaLabel.insets = new Insets(0, 0, 5, 5);
				gbc_dirViaLabel.gridx = 2;
				gbc_dirViaLabel.gridy = 6;
				direccionDataPanel.add(dirViaLabel, gbc_dirViaLabel);
			}
			{
				dirViaValueTextFIeld = new JTextField();
				dirViaValueTextFIeld.setEditable(false);
				dirViaValueTextFIeld.setEnabled(false);
				GridBagConstraints gbc_dirViaValueTextFIeld = new GridBagConstraints();
				gbc_dirViaValueTextFIeld.insets = new Insets(0, 0, 5, 5);
				gbc_dirViaValueTextFIeld.fill = GridBagConstraints.HORIZONTAL;
				gbc_dirViaValueTextFIeld.gridx = 2;
				gbc_dirViaValueTextFIeld.gridy = 7;
				direccionDataPanel.add(dirViaValueTextFIeld, gbc_dirViaValueTextFIeld);
				dirViaValueTextFIeld.setColumns(10);
			}
			{
				JLabel localidadLabel = new JLabel("Localidad:");
				GridBagConstraints gbc_localidadLabel = new GridBagConstraints();
				gbc_localidadLabel.insets = new Insets(0, 0, 5, 5);
				gbc_localidadLabel.gridx = 2;
				gbc_localidadLabel.gridy = 9;
				direccionDataPanel.add(localidadLabel, gbc_localidadLabel);
			}
			{
				localidadValueComboBox = new JComboBox();
				localidadValueComboBox.setEnabled(false);
				GridBagConstraints gbc_localidadValueComboBox = new GridBagConstraints();
				gbc_localidadValueComboBox.insets = new Insets(0, 0, 5, 5);
				gbc_localidadValueComboBox.fill = GridBagConstraints.HORIZONTAL;
				gbc_localidadValueComboBox.gridx = 2;
				gbc_localidadValueComboBox.gridy = 10;
				direccionDataPanel.add(localidadValueComboBox, gbc_localidadValueComboBox);
			}
			{
				JLabel provinciaLabel = new JLabel("Provincia:");
				GridBagConstraints gbc_provinciaLabel = new GridBagConstraints();
				gbc_provinciaLabel.insets = new Insets(0, 0, 5, 5);
				gbc_provinciaLabel.gridx = 2;
				gbc_provinciaLabel.gridy = 12;
				direccionDataPanel.add(provinciaLabel, gbc_provinciaLabel);
			}
			{
				provinicaValueComboBox = new JComboBox();
				provinicaValueComboBox.setEnabled(false);
				GridBagConstraints gbc_provinicaValueComboBox = new GridBagConstraints();
				gbc_provinicaValueComboBox.insets = new Insets(0, 0, 5, 5);
				gbc_provinicaValueComboBox.fill = GridBagConstraints.HORIZONTAL;
				gbc_provinicaValueComboBox.gridx = 2;
				gbc_provinicaValueComboBox.gridy = 13;
				direccionDataPanel.add(provinicaValueComboBox, gbc_provinicaValueComboBox);
			}
			{
				JLabel paisLabel = new JLabel("País:");
				GridBagConstraints gbc_paisLabel = new GridBagConstraints();
				gbc_paisLabel.insets = new Insets(0, 0, 5, 5);
				gbc_paisLabel.gridx = 2;
				gbc_paisLabel.gridy = 15;
				direccionDataPanel.add(paisLabel, gbc_paisLabel);
			}
			{
				paisValueComboBox = new JComboBox();
				paisValueComboBox.setEnabled(false);
				GridBagConstraints gbc_paisValueComboBox = new GridBagConstraints();
				gbc_paisValueComboBox.insets = new Insets(0, 0, 0, 5);
				gbc_paisValueComboBox.fill = GridBagConstraints.HORIZONTAL;
				gbc_paisValueComboBox.gridx = 2;
				gbc_paisValueComboBox.gridy = 16;
				direccionDataPanel.add(paisValueComboBox, gbc_paisValueComboBox);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				saveButton = new JButton("Gurdar");
				saveButton.setAction(new UpdateEmpleadoAction(this, "Gurdar", new ImageIcon("/icons/icons8-save-48.png")));
				saveButton.setActionCommand("OK");
				buttonPane.add(saveButton);
				getRootPane().setDefaultButton(saveButton);
			}
		}

		postInitialize();
	}

	private void postInitialize() {
		
		saveButton.setVisible(false);

		idValueTextField.setText(empleado.getId().toString());
		nombreValueTextFIeld.setText(empleado.getNombre());
		apellido1ValueTextField.setText(empleado.getApellido1());
		apellido2ValueTextField.setText(empleado.getApellido2());
		telefonoValueTextField.setText(empleado.getTelefono());
		mailValueTextField.setText(empleado.getEmail());
		dniNieValueTextField.setText(empleado.getDniNie());

		nombreViaValueTextField.setText(empleado.getDireccion().getNombreVia());
		dirViaValueTextFIeld.setText(empleado.getDireccion().getDirVia());

		
		
		localidadValueComboBox.setRenderer(new LocalidadListCellRenderer());
		provinicaValueComboBox.setRenderer(new ProvinciaListCellRenderer());
		paisValueComboBox.setRenderer(new PaisListCellRenderer());
		tipoEmpleadoComboBox.setRenderer(new TipoEmpleadoListCellRenderer());

	}
	
	private void setEditable() {
		tipoEmpleadoComboBox.setEnabled(true);
		saveButton.setVisible(true);
		editButton.setVisible(false);
	}

	public void setComboBoxModel(DefaultComboBoxModel<Localidad> localidadModel, DefaultComboBoxModel<Provincia> provinciaModel,
			DefaultComboBoxModel<Pais> paisModel, DefaultComboBoxModel<TipoEmpleado>estadoModel ) {

		localidadValueComboBox.setModel(localidadModel);
		provinicaValueComboBox.setModel(provinciaModel);
		paisValueComboBox.setModel(paisModel);
		tipoEmpleadoComboBox.setModel(estadoModel);
	}
	
	public void setModel(EmpleadoDTO empleado) {
		TipoEmpleado tipo = (TipoEmpleado) tipoEmpleadoComboBox.getSelectedItem();  
		empleado.setTipo_empleado_id(tipo.getId());
	}
	
	public Long getEmpleadoId() {
		return Long.valueOf(idValueTextField.getText());
		 
	}
	
	public void setLocalidadIndex(int index) {
		localidadValueComboBox.setSelectedIndex(index);
	}
	
	public void setProvinciaIndex(int index) {
		provinicaValueComboBox.setSelectedIndex(index);
	}
	
	public void setPaisIndex(int index) {
		paisValueComboBox.setSelectedIndex(index);
	}
	
	public void setTipoEmpleadoIndex(int index) {
		tipoEmpleadoComboBox.setSelectedIndex(index);
	}

}
