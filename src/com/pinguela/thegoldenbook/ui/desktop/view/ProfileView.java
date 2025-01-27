package com.pinguela.thegoldenbook.ui.desktop.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.model.DireccionDTO;
import com.pinguela.thegoldenbook.model.EmpleadoDTO;
import com.pinguela.thegoldenbook.model.Localidad;
import com.pinguela.thegoldenbook.model.Pais;
import com.pinguela.thegoldenbook.model.Provincia;
import com.pinguela.thegoldenbook.ui.desktop.TheGoldenBookWindow;
import com.pinguela.thegoldenbook.ui.desktop.controller.ShowChangeContraseñaAction;
import com.pinguela.thegoldenbook.ui.desktop.controller.UpdateProfileAction;
import com.pinguela.thegoldenbook.ui.desktop.renderer.LocalidadListCellRenderer;
import com.pinguela.thegoldenbook.ui.desktop.renderer.PaisListCellRenderer;
import com.pinguela.thegoldenbook.ui.desktop.renderer.ProvinciaListCellRenderer;

public class ProfileView extends View{

	private Logger logger = LogManager.getLogger(ProfileView.class);

	private JTextField dniNieValueTextField;
	private JTextField apellido1ValueTextField;
	private JTextField apellido2ValueTextField;
	private JTextField telefonoValueTextField;
	private JTextField mailValueTextField;
	private JPasswordField passwordField;
	private JTextField nombreValueTextField;
	private JTextField nombreViaValueTextField;
	private JComboBox paisValueComboBox;
	private JComboBox provinciaValueComboBox;
	private JTextField codigoPostalValueTextField;
	private JTextField dirViaValueTextField;
	private JComboBox localidadValueComboBox;
	private JButton confirmButton;
	private JLabel idValueLabel;

	//Servicios de negocio
	private EmpleadoDTO empleadoAutenticado = null;
	private Long id = null;
	private JButton editPasswordButton;


	/**
	 * Create the panel.
	 */
	public ProfileView() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 260, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 120, 0, 281, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		JLabel myDataLabel = new JLabel("Mis datos");
		GridBagConstraints gbc_myDataLabel = new GridBagConstraints();
		gbc_myDataLabel.insets = new Insets(0, 0, 5, 5);
		gbc_myDataLabel.gridx = 3;
		gbc_myDataLabel.gridy = 1;
		panel.add(myDataLabel, gbc_myDataLabel);

		JLabel idLabel = new JLabel("ID:");
		GridBagConstraints gbc_idLabel = new GridBagConstraints();
		gbc_idLabel.insets = new Insets(0, 0, 5, 5);
		gbc_idLabel.gridx = 1;
		gbc_idLabel.gridy = 2;
		panel.add(idLabel, gbc_idLabel);

		JLabel dniNieLabel = new JLabel("DNI/Nie");
		GridBagConstraints gbc_dniNieLabel = new GridBagConstraints();
		gbc_dniNieLabel.insets = new Insets(0, 0, 5, 5);
		gbc_dniNieLabel.gridx = 5;
		gbc_dniNieLabel.gridy = 2;
		panel.add(dniNieLabel, gbc_dniNieLabel);

		idValueLabel = new JLabel("");
		GridBagConstraints gbc_idValueLabel = new GridBagConstraints();
		gbc_idValueLabel.insets = new Insets(0, 0, 5, 5);
		gbc_idValueLabel.gridx = 1;
		gbc_idValueLabel.gridy = 3;
		panel.add(idValueLabel, gbc_idValueLabel);

		dniNieValueTextField = new JTextField();
		GridBagConstraints gbc_dniNieValueTextField = new GridBagConstraints();
		gbc_dniNieValueTextField.insets = new Insets(0, 0, 5, 5);
		gbc_dniNieValueTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_dniNieValueTextField.gridx = 5;
		gbc_dniNieValueTextField.gridy = 3;
		panel.add(dniNieValueTextField, gbc_dniNieValueTextField);
		dniNieValueTextField.setColumns(10);

		JLabel nombreLabel = new JLabel("Nombre:");
		GridBagConstraints gbc_nombreLabel = new GridBagConstraints();
		gbc_nombreLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nombreLabel.gridx = 1;
		gbc_nombreLabel.gridy = 4;
		panel.add(nombreLabel, gbc_nombreLabel);

		JLabel phoneLabel = new JLabel("Telefono");
		GridBagConstraints gbc_phoneLabel = new GridBagConstraints();
		gbc_phoneLabel.insets = new Insets(0, 0, 5, 5);
		gbc_phoneLabel.gridx = 5;
		gbc_phoneLabel.gridy = 4;
		panel.add(phoneLabel, gbc_phoneLabel);

		nombreValueTextField = new JTextField();
		GridBagConstraints gbc_nombreValueTextField = new GridBagConstraints();
		gbc_nombreValueTextField.insets = new Insets(0, 0, 5, 5);
		gbc_nombreValueTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nombreValueTextField.gridx = 1;
		gbc_nombreValueTextField.gridy = 5;
		panel.add(nombreValueTextField, gbc_nombreValueTextField);
		nombreValueTextField.setColumns(10);

		telefonoValueTextField = new JTextField();
		GridBagConstraints gbc_telefonoValueTextField = new GridBagConstraints();
		gbc_telefonoValueTextField.insets = new Insets(0, 0, 5, 5);
		gbc_telefonoValueTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_telefonoValueTextField.gridx = 5;
		gbc_telefonoValueTextField.gridy = 5;
		panel.add(telefonoValueTextField, gbc_telefonoValueTextField);
		telefonoValueTextField.setColumns(10);

		JLabel apellido1Label = new JLabel("Primer apellido");
		GridBagConstraints gbc_apellido1Label = new GridBagConstraints();
		gbc_apellido1Label.insets = new Insets(0, 0, 5, 5);
		gbc_apellido1Label.gridx = 1;
		gbc_apellido1Label.gridy = 6;
		panel.add(apellido1Label, gbc_apellido1Label);

		JLabel mailLabel = new JLabel("E-mail");
		GridBagConstraints gbc_mailLabel = new GridBagConstraints();
		gbc_mailLabel.insets = new Insets(0, 0, 5, 5);
		gbc_mailLabel.gridx = 5;
		gbc_mailLabel.gridy = 6;
		panel.add(mailLabel, gbc_mailLabel);

		apellido1ValueTextField = new JTextField();
		GridBagConstraints gbc_apellido1ValueTextField = new GridBagConstraints();
		gbc_apellido1ValueTextField.insets = new Insets(0, 0, 5, 5);
		gbc_apellido1ValueTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_apellido1ValueTextField.gridx = 1;
		gbc_apellido1ValueTextField.gridy = 7;
		panel.add(apellido1ValueTextField, gbc_apellido1ValueTextField);
		apellido1ValueTextField.setColumns(10);

		mailValueTextField = new JTextField();
		GridBagConstraints gbc_mailValueTextField = new GridBagConstraints();
		gbc_mailValueTextField.insets = new Insets(0, 0, 5, 5);
		gbc_mailValueTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_mailValueTextField.gridx = 5;
		gbc_mailValueTextField.gridy = 7;
		panel.add(mailValueTextField, gbc_mailValueTextField);
		mailValueTextField.setColumns(10);

		JLabel apellido2Label = new JLabel("Segundo apellido");
		GridBagConstraints gbc_apellido2Label = new GridBagConstraints();
		gbc_apellido2Label.insets = new Insets(0, 0, 5, 5);
		gbc_apellido2Label.gridx = 1;
		gbc_apellido2Label.gridy = 8;
		panel.add(apellido2Label, gbc_apellido2Label);

		apellido2ValueTextField = new JTextField();
		GridBagConstraints gbc_apellido2ValueTextField = new GridBagConstraints();
		gbc_apellido2ValueTextField.insets = new Insets(0, 0, 5, 5);
		gbc_apellido2ValueTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_apellido2ValueTextField.gridx = 1;
		gbc_apellido2ValueTextField.gridy = 9;
		panel.add(apellido2ValueTextField, gbc_apellido2ValueTextField);
		apellido2ValueTextField.setColumns(10);

		JLabel passwordLabel = new JLabel("Contraseña");
		GridBagConstraints gbc_passwordLabel = new GridBagConstraints();
		gbc_passwordLabel.insets = new Insets(0, 0, 5, 5);
		gbc_passwordLabel.gridx = 3;
		gbc_passwordLabel.gridy = 10;
		panel.add(passwordLabel, gbc_passwordLabel);

		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 3;
		gbc_passwordField.gridy = 11;
		panel.add(passwordField, gbc_passwordField);

		editPasswordButton = new JButton();
		editPasswordButton.setAction(new ShowChangeContraseñaAction(id, "Editar contraseña"));
		GridBagConstraints gbc_editPasswordButton = new GridBagConstraints();
		gbc_editPasswordButton.insets = new Insets(0, 0, 5, 5);
		gbc_editPasswordButton.gridx = 3;
		gbc_editPasswordButton.gridy = 12;
		panel.add(editPasswordButton, gbc_editPasswordButton);

		JLabel lblDireccion = new JLabel("Direccion");
		GridBagConstraints gbc_lblDireccion = new GridBagConstraints();
		gbc_lblDireccion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDireccion.gridx = 3;
		gbc_lblDireccion.gridy = 14;
		panel.add(lblDireccion, gbc_lblDireccion);

		JLabel nombreViaLabel = new JLabel("Nombre de la calle:");
		GridBagConstraints gbc_nombreViaLabel = new GridBagConstraints();
		gbc_nombreViaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nombreViaLabel.gridx = 1;
		gbc_nombreViaLabel.gridy = 15;
		panel.add(nombreViaLabel, gbc_nombreViaLabel);

		JLabel dirViaLabel = new JLabel("Número, Piso, Suite:");
		GridBagConstraints gbc_dirViaLabel = new GridBagConstraints();
		gbc_dirViaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_dirViaLabel.gridx = 5;
		gbc_dirViaLabel.gridy = 15;
		panel.add(dirViaLabel, gbc_dirViaLabel);

		nombreViaValueTextField = new JTextField();
		GridBagConstraints gbc_nombreViaValueTextField = new GridBagConstraints();
		gbc_nombreViaValueTextField.insets = new Insets(0, 0, 5, 5);
		gbc_nombreViaValueTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nombreViaValueTextField.gridx = 1;
		gbc_nombreViaValueTextField.gridy = 16;
		panel.add(nombreViaValueTextField, gbc_nombreViaValueTextField);
		nombreViaValueTextField.setColumns(10);

		dirViaValueTextField = new JTextField();
		GridBagConstraints gbc_dirViaValueTextField = new GridBagConstraints();
		gbc_dirViaValueTextField.insets = new Insets(0, 0, 5, 5);
		gbc_dirViaValueTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_dirViaValueTextField.gridx = 5;
		gbc_dirViaValueTextField.gridy = 16;
		panel.add(dirViaValueTextField, gbc_dirViaValueTextField);
		dirViaValueTextField.setColumns(10);

		JLabel localidadLabel = new JLabel("Localidad:");
		GridBagConstraints gbc_localidadLabel = new GridBagConstraints();
		gbc_localidadLabel.insets = new Insets(0, 0, 5, 5);
		gbc_localidadLabel.gridx = 1;
		gbc_localidadLabel.gridy = 17;
		panel.add(localidadLabel, gbc_localidadLabel);

		JLabel codigoPostalLabel = new JLabel("Código Postal:");
		GridBagConstraints gbc_codigoPostalLabel = new GridBagConstraints();
		gbc_codigoPostalLabel.insets = new Insets(0, 0, 5, 5);
		gbc_codigoPostalLabel.gridx = 5;
		gbc_codigoPostalLabel.gridy = 17;
		panel.add(codigoPostalLabel, gbc_codigoPostalLabel);

		localidadValueComboBox = new JComboBox();
		GridBagConstraints gbc_localidadValueComboBox = new GridBagConstraints();
		gbc_localidadValueComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_localidadValueComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_localidadValueComboBox.gridx = 1;
		gbc_localidadValueComboBox.gridy = 18;
		panel.add(localidadValueComboBox, gbc_localidadValueComboBox);

		codigoPostalValueTextField = new JTextField();
		GridBagConstraints gbc_codigoPostalValueTextField = new GridBagConstraints();
		gbc_codigoPostalValueTextField.insets = new Insets(0, 0, 5, 5);
		gbc_codigoPostalValueTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_codigoPostalValueTextField.gridx = 5;
		gbc_codigoPostalValueTextField.gridy = 18;
		panel.add(codigoPostalValueTextField, gbc_codigoPostalValueTextField);
		codigoPostalValueTextField.setColumns(10);

		JLabel provinciaLabel = new JLabel("Provincia:");
		GridBagConstraints gbc_provinciaLabel = new GridBagConstraints();
		gbc_provinciaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_provinciaLabel.gridx = 1;
		gbc_provinciaLabel.gridy = 19;
		panel.add(provinciaLabel, gbc_provinciaLabel);

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setEditable();
			}
		});
		JLabel paisLabel = new JLabel("País:");
		GridBagConstraints gbc_paisLabel = new GridBagConstraints();
		gbc_paisLabel.insets = new Insets(0, 0, 5, 5);
		gbc_paisLabel.gridx = 5;
		gbc_paisLabel.gridy = 19;
		panel.add(paisLabel, gbc_paisLabel);

		provinciaValueComboBox = new JComboBox();
		GridBagConstraints gbc_provinciaValueComboBox = new GridBagConstraints();
		gbc_provinciaValueComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_provinciaValueComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_provinciaValueComboBox.gridx = 1;
		gbc_provinciaValueComboBox.gridy = 20;
		panel.add(provinciaValueComboBox, gbc_provinciaValueComboBox);

		paisValueComboBox = new JComboBox();
		GridBagConstraints gbc_paisValueComboBox = new GridBagConstraints();
		gbc_paisValueComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_paisValueComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_paisValueComboBox.gridx = 5;
		gbc_paisValueComboBox.gridy = 20;
		panel.add(paisValueComboBox, gbc_paisValueComboBox);
		GridBagConstraints gbc_btnEditar = new GridBagConstraints();
		gbc_btnEditar.insets = new Insets(0, 0, 0, 5);
		gbc_btnEditar.gridx = 3;
		gbc_btnEditar.gridy = 21;
		panel.add(btnEditar, gbc_btnEditar);

		confirmButton = new JButton();
		confirmButton.setAction(new UpdateProfileAction(this, "Confirmar"));
		confirmButton.setEnabled(false);
		GridBagConstraints gbc_confirmButton = new GridBagConstraints();
		gbc_confirmButton.insets = new Insets(0, 0, 0, 5);
		gbc_confirmButton.gridx = 5;
		gbc_confirmButton.gridy = 21;
		panel.add(confirmButton, gbc_confirmButton);

		postInitialize();

	}

	private void postInitialize() {

		try {
			TheGoldenBookWindow window = TheGoldenBookWindow.getInstance();
			empleadoAutenticado = window.getUsuarioAutenticado();
			
			id = empleadoAutenticado.getId();

			setDataFields();

			setNotEditable();

			localidadValueComboBox.setRenderer(new LocalidadListCellRenderer());

			provinciaValueComboBox.setRenderer(new ProvinciaListCellRenderer());

			paisValueComboBox.setRenderer(new PaisListCellRenderer());

		}catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
	}

	private void setEditable() {
		confirmButton.setEnabled(true);
		nombreValueTextField.setEditable(true);
		apellido1ValueTextField.setEditable(true);
		apellido2ValueTextField.setEditable(true);
		dniNieValueTextField.setEditable(true);
		mailValueTextField.setEditable(true);
		telefonoValueTextField.setEditable(true);
		passwordField.setEditable(true);
		nombreViaValueTextField.setEditable(true);
		dirViaValueTextField.setEditable(true);
		paisValueComboBox.setEnabled(false);
		codigoPostalValueTextField.setEditable(true);
		localidadValueComboBox.setEnabled(true);
		provinciaValueComboBox.setEnabled(false);
		paisValueComboBox.setEnabled(false);
	}

	public void setEmpleadoActualizadoModel(EmpleadoDTO empleado) {

		empleado.setId(empleadoAutenticado.getId());
		empleado.setNombre(nombreValueTextField.getText());
		empleado.setApellido1(apellido1ValueTextField.getText());
		empleado.setApellido2(apellido2ValueTextField.getText());
		empleado.setDniNie(dniNieValueTextField.getText());
		empleado.setEmail(mailValueTextField.getText());
		empleado.setTelefono(telefonoValueTextField.getText());
		empleado.setTipo_empleado_id(empleadoAutenticado.getTipo_empleado_id());

		DireccionDTO direccion = new DireccionDTO();

		direccion.setId(empleadoAutenticado.getDireccion().getId());
		direccion.setNombreVia(nombreViaValueTextField.getText());
		direccion.setDirVia(dirViaValueTextField.getText());
		direccion.setEmpleadoId(empleado.getId());

		Localidad localidadSeleccionada = (Localidad)localidadValueComboBox.getSelectedItem();
		direccion.setLocalidadId(localidadSeleccionada.getId());

		Provincia provinciaSeleccionada = (Provincia)provinciaValueComboBox.getSelectedItem();
		direccion.setProvinciaId(provinciaSeleccionada.getId());

		Pais paisSeleccionado = (Pais) paisValueComboBox.getSelectedItem();
		direccion.setPaisId(paisSeleccionado.getId());

		empleado.setDireccion(direccion);
	}

	private void setNotEditable() {
		nombreValueTextField.setEditable(false);
		apellido1ValueTextField.setEditable(false);
		apellido2ValueTextField.setEditable(false);
		dniNieValueTextField.setEditable(false);
		mailValueTextField.setEditable(false);
		telefonoValueTextField.setEditable(false);
		passwordField.setEditable(false);
		nombreViaValueTextField.setEditable(false);
		dirViaValueTextField.setEditable(false);
		paisValueComboBox.setEnabled(false);
		codigoPostalValueTextField.setEditable(false);
		provinciaValueComboBox.setEnabled(false);
		paisValueComboBox.setEnabled(false);
		localidadValueComboBox.setEnabled(false);
	}

	public void setComboModel(DefaultComboBoxModel<Localidad> localidadComboModel, DefaultComboBoxModel<Provincia>provinciaComboModel, DefaultComboBoxModel<Pais> paisComboModel) {
		localidadValueComboBox.setModel(localidadComboModel);
		provinciaValueComboBox.setModel(provinciaComboModel);
		paisValueComboBox.setModel(paisComboModel);
	}

	public void setLocalidadSelectedItem(int index) {
		localidadValueComboBox.setSelectedIndex(index);
	}

	public void setCodigoPostalValue(String text) {
		codigoPostalValueTextField.setText(text);
	}

	public void setProvinciaSelectedItem(int index) {
		provinciaValueComboBox.setSelectedIndex(index);
	}

	public void setPaisSelectedItem(int index) {
		paisValueComboBox.setSelectedIndex(index);
	}

	public void setDataFields() {

		idValueLabel.setText(String.valueOf(empleadoAutenticado.getId()));
		nombreValueTextField.setText(empleadoAutenticado.getNombre());
		apellido1ValueTextField.setText(empleadoAutenticado.getApellido1());
		apellido2ValueTextField.setText(empleadoAutenticado.getApellido2());
		dniNieValueTextField.setText(empleadoAutenticado.getDniNie());
		mailValueTextField.setText(empleadoAutenticado.getEmail());
		telefonoValueTextField.setText(empleadoAutenticado.getTelefono());
		passwordField.setText(empleadoAutenticado.getPassword());
		nombreViaValueTextField.setText(empleadoAutenticado.getDireccion().getNombreVia());
		dirViaValueTextField.setText(empleadoAutenticado.getDireccion().getDirVia());

	}


}




