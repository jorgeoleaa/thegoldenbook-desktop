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
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.pinguela.thegoldenbook.model.DireccionDTO;
import com.pinguela.thegoldenbook.model.EmpleadoDTO;
import com.pinguela.thegoldenbook.model.Localidad;
import com.pinguela.thegoldenbook.model.Pais;
import com.pinguela.thegoldenbook.model.Provincia;
import com.pinguela.thegoldenbook.ui.desktop.controller.CreateEmpleadoAction;
import com.pinguela.thegoldenbook.ui.desktop.renderer.LocalidadListCellRenderer;
import com.pinguela.thegoldenbook.ui.desktop.renderer.PaisListCellRenderer;
import com.pinguela.thegoldenbook.ui.desktop.renderer.ProvinciaListCellRenderer;
import com.pinguela.thegoldenbook.ui.desktop.utils.PasswordGenerator;
import com.pinguela.thegoldenbook.ui.desktop.utils.SwingUtils;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EmpleadoInsertDialog extends TheGoldenBookDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton saveButton;
	private JTextField nombreValueTextField;
	private JTextField telefonoValueTextField;
	private JTextField apellido1ValueTextField;
	private JTextField mailValueTextField;
	private JTextField apellido2ValueTextField;
	private JPasswordField passwordField;
	private JTextField dniNieValueTextField;
	private JTextField nombreCalleValueTextField;
	private JTextField dirViaValueTextField;
	private JTextField codigoPostalValueTextField;
	private JComboBox paisValueComboBox;
	private JComboBox provinciaValueComboBox;
	private JComboBox localidadValueComboBox;
	private JSpinner tipoEmpleadoIdSpinner;
	private JButton cancelButton;
	private JButton generatePasswordButton;



	/**
	 * Create the dialog.
	 */
	public EmpleadoInsertDialog() {
		setBounds(100, 100, 1284, 893);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				JPanel registerPanel = new JPanel();
				scrollPane.setViewportView(registerPanel);
				GridBagLayout gbl_registerPanel = new GridBagLayout();
				gbl_registerPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
				gbl_registerPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
				gbl_registerPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
				gbl_registerPanel.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
				registerPanel.setLayout(gbl_registerPanel);
				{
					JLabel titleLabel = new JLabel("Formulario de registro");
					GridBagConstraints gbc_titleLabel = new GridBagConstraints();
					gbc_titleLabel.insets = new Insets(0, 0, 5, 5);
					gbc_titleLabel.gridx = 6;
					gbc_titleLabel.gridy = 1;
					registerPanel.add(titleLabel, gbc_titleLabel);
				}
				{
					JLabel iconLabel = new JLabel("");
					iconLabel.setIcon(new ImageIcon(EmpleadoInsertDialog.class.getResource("/icons/icons8-usuario-masculino-en-círculo-94.png")));
					GridBagConstraints gbc_iconLabel = new GridBagConstraints();
					gbc_iconLabel.insets = new Insets(0, 0, 5, 5);
					gbc_iconLabel.gridx = 6;
					gbc_iconLabel.gridy = 2;
					registerPanel.add(iconLabel, gbc_iconLabel);
				}
				{
					JLabel nombreLabel = new JLabel("Nombre:");
					GridBagConstraints gbc_nombreLabel = new GridBagConstraints();
					gbc_nombreLabel.insets = new Insets(0, 0, 5, 5);
					gbc_nombreLabel.gridx = 2;
					gbc_nombreLabel.gridy = 3;
					registerPanel.add(nombreLabel, gbc_nombreLabel);
				}
				{
					JLabel telefonoLabel = new JLabel("Telefono:");
					GridBagConstraints gbc_telefonoLabel = new GridBagConstraints();
					gbc_telefonoLabel.insets = new Insets(0, 0, 5, 0);
					gbc_telefonoLabel.gridx = 10;
					gbc_telefonoLabel.gridy = 3;
					registerPanel.add(telefonoLabel, gbc_telefonoLabel);
				}
				{
					nombreValueTextField = new JTextField();
					nombreValueTextField.setColumns(10);
					GridBagConstraints gbc_nombreValueTextField = new GridBagConstraints();
					gbc_nombreValueTextField.fill = GridBagConstraints.HORIZONTAL;
					gbc_nombreValueTextField.insets = new Insets(0, 0, 5, 5);
					gbc_nombreValueTextField.gridx = 2;
					gbc_nombreValueTextField.gridy = 4;
					registerPanel.add(nombreValueTextField, gbc_nombreValueTextField);
				}
				{
					telefonoValueTextField = new JTextField();
					telefonoValueTextField.setColumns(10);
					GridBagConstraints gbc_telefonoValueTextField = new GridBagConstraints();
					gbc_telefonoValueTextField.fill = GridBagConstraints.HORIZONTAL;
					gbc_telefonoValueTextField.insets = new Insets(0, 0, 5, 0);
					gbc_telefonoValueTextField.gridx = 10;
					gbc_telefonoValueTextField.gridy = 4;
					registerPanel.add(telefonoValueTextField, gbc_telefonoValueTextField);
				}
				{
					JLabel primerApellidoLabel = new JLabel("Primer apellido:");
					GridBagConstraints gbc_primerApellidoLabel = new GridBagConstraints();
					gbc_primerApellidoLabel.insets = new Insets(0, 0, 5, 5);
					gbc_primerApellidoLabel.gridx = 2;
					gbc_primerApellidoLabel.gridy = 6;
					registerPanel.add(primerApellidoLabel, gbc_primerApellidoLabel);
				}
				{
					JLabel correoElectronicoLabel = new JLabel("Correo electronico:");
					GridBagConstraints gbc_correoElectronicoLabel = new GridBagConstraints();
					gbc_correoElectronicoLabel.insets = new Insets(0, 0, 5, 0);
					gbc_correoElectronicoLabel.gridx = 10;
					gbc_correoElectronicoLabel.gridy = 6;
					registerPanel.add(correoElectronicoLabel, gbc_correoElectronicoLabel);
				}
				{
					apellido1ValueTextField = new JTextField();
					apellido1ValueTextField.setColumns(10);
					GridBagConstraints gbc_apellido1ValueTextField = new GridBagConstraints();
					gbc_apellido1ValueTextField.fill = GridBagConstraints.HORIZONTAL;
					gbc_apellido1ValueTextField.insets = new Insets(0, 0, 5, 5);
					gbc_apellido1ValueTextField.gridx = 2;
					gbc_apellido1ValueTextField.gridy = 7;
					registerPanel.add(apellido1ValueTextField, gbc_apellido1ValueTextField);
				}
				{
					mailValueTextField = new JTextField();
					mailValueTextField.setColumns(10);
					GridBagConstraints gbc_mailValueTextField = new GridBagConstraints();
					gbc_mailValueTextField.fill = GridBagConstraints.HORIZONTAL;
					gbc_mailValueTextField.insets = new Insets(0, 0, 5, 0);
					gbc_mailValueTextField.gridx = 10;
					gbc_mailValueTextField.gridy = 7;
					registerPanel.add(mailValueTextField, gbc_mailValueTextField);
				}
				{
					JLabel segundoApellidoLabel = new JLabel("Segundo apellido:");
					GridBagConstraints gbc_segundoApellidoLabel = new GridBagConstraints();
					gbc_segundoApellidoLabel.insets = new Insets(0, 0, 5, 5);
					gbc_segundoApellidoLabel.gridx = 2;
					gbc_segundoApellidoLabel.gridy = 9;
					registerPanel.add(segundoApellidoLabel, gbc_segundoApellidoLabel);
				}
				{
					JLabel passwordLabel = new JLabel("Password");
					GridBagConstraints gbc_passwordLabel = new GridBagConstraints();
					gbc_passwordLabel.insets = new Insets(0, 0, 5, 0);
					gbc_passwordLabel.gridx = 10;
					gbc_passwordLabel.gridy = 9;
					registerPanel.add(passwordLabel, gbc_passwordLabel);
				}
				{
					apellido2ValueTextField = new JTextField();
					apellido2ValueTextField.setColumns(10);
					GridBagConstraints gbc_apellido2ValueTextField = new GridBagConstraints();
					gbc_apellido2ValueTextField.fill = GridBagConstraints.HORIZONTAL;
					gbc_apellido2ValueTextField.insets = new Insets(0, 0, 5, 5);
					gbc_apellido2ValueTextField.gridx = 2;
					gbc_apellido2ValueTextField.gridy = 10;
					registerPanel.add(apellido2ValueTextField, gbc_apellido2ValueTextField);
				}
				{
					passwordField = new JPasswordField();
					GridBagConstraints gbc_passwordField = new GridBagConstraints();
					gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
					gbc_passwordField.insets = new Insets(0, 0, 5, 0);
					gbc_passwordField.gridx = 10;
					gbc_passwordField.gridy = 10;
					registerPanel.add(passwordField, gbc_passwordField);
				}
				{
					JLabel dniNieLabel = new JLabel("DNI/NIE");
					GridBagConstraints gbc_dniNieLabel = new GridBagConstraints();
					gbc_dniNieLabel.insets = new Insets(0, 0, 5, 5);
					gbc_dniNieLabel.gridx = 2;
					gbc_dniNieLabel.gridy = 12;
					registerPanel.add(dniNieLabel, gbc_dniNieLabel);
				}
				{
					JLabel tipoEmpleadoLabel = new JLabel("Tipo de empleado:");
					GridBagConstraints gbc_tipoEmpleadoLabel = new GridBagConstraints();
					gbc_tipoEmpleadoLabel.insets = new Insets(0, 0, 5, 0);
					gbc_tipoEmpleadoLabel.gridx = 10;
					gbc_tipoEmpleadoLabel.gridy = 12;
					registerPanel.add(tipoEmpleadoLabel, gbc_tipoEmpleadoLabel);
				}
				{
					dniNieValueTextField = new JTextField();
					dniNieValueTextField.setColumns(10);
					GridBagConstraints gbc_dniNieValueTextField = new GridBagConstraints();
					gbc_dniNieValueTextField.fill = GridBagConstraints.HORIZONTAL;
					gbc_dniNieValueTextField.insets = new Insets(0, 0, 5, 5);
					gbc_dniNieValueTextField.gridx = 2;
					gbc_dniNieValueTextField.gridy = 13;
					registerPanel.add(dniNieValueTextField, gbc_dniNieValueTextField);
				}
				{
					tipoEmpleadoIdSpinner = new JSpinner();
					GridBagConstraints gbc_tipoEmpleadoIdSpinner = new GridBagConstraints();
					gbc_tipoEmpleadoIdSpinner.insets = new Insets(0, 0, 5, 0);
					gbc_tipoEmpleadoIdSpinner.gridx = 10;
					gbc_tipoEmpleadoIdSpinner.gridy = 13;
					registerPanel.add(tipoEmpleadoIdSpinner, gbc_tipoEmpleadoIdSpinner);
				}
				{
					generatePasswordButton = new JButton("Generar contraseña");
					generatePasswordButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							passwordField.setText(PasswordGenerator.generateRandomPassword());
							passwordField.setEnabled(false);
							generatePasswordButton.setEnabled(false);
						}
					});
					GridBagConstraints gbc_generatePasswordButton = new GridBagConstraints();
					gbc_generatePasswordButton.insets = new Insets(0, 0, 5, 5);
					gbc_generatePasswordButton.gridx = 6;
					gbc_generatePasswordButton.gridy = 14;
					registerPanel.add(generatePasswordButton, gbc_generatePasswordButton);
				}
				{
					JLabel direccionTitleLabel = new JLabel("Direccion:");
					GridBagConstraints gbc_direccionTitleLabel = new GridBagConstraints();
					gbc_direccionTitleLabel.insets = new Insets(0, 0, 5, 5);
					gbc_direccionTitleLabel.gridx = 6;
					gbc_direccionTitleLabel.gridy = 15;
					registerPanel.add(direccionTitleLabel, gbc_direccionTitleLabel);
				}
				{
					JLabel direccionIconLabel = new JLabel("");
					direccionIconLabel.setIcon(new ImageIcon(EmpleadoInsertDialog.class.getResource("/icons/icons8-dirección-100.png")));
					GridBagConstraints gbc_direccionIconLabel = new GridBagConstraints();
					gbc_direccionIconLabel.insets = new Insets(0, 0, 5, 5);
					gbc_direccionIconLabel.gridx = 6;
					gbc_direccionIconLabel.gridy = 16;
					registerPanel.add(direccionIconLabel, gbc_direccionIconLabel);
				}
				{
					JLabel nombreViaLabel = new JLabel("Nombre de la calle:");
					GridBagConstraints gbc_nombreViaLabel = new GridBagConstraints();
					gbc_nombreViaLabel.insets = new Insets(0, 0, 5, 5);
					gbc_nombreViaLabel.gridx = 2;
					gbc_nombreViaLabel.gridy = 17;
					registerPanel.add(nombreViaLabel, gbc_nombreViaLabel);
				}
				{
					JLabel dirViaLabel = new JLabel("Número, piso, puerta...");
					GridBagConstraints gbc_dirViaLabel = new GridBagConstraints();
					gbc_dirViaLabel.insets = new Insets(0, 0, 5, 0);
					gbc_dirViaLabel.gridx = 10;
					gbc_dirViaLabel.gridy = 17;
					registerPanel.add(dirViaLabel, gbc_dirViaLabel);
				}
				{
					nombreCalleValueTextField = new JTextField();
					GridBagConstraints gbc_nombreCalleValueTextField = new GridBagConstraints();
					gbc_nombreCalleValueTextField.insets = new Insets(0, 0, 5, 5);
					gbc_nombreCalleValueTextField.fill = GridBagConstraints.HORIZONTAL;
					gbc_nombreCalleValueTextField.gridx = 2;
					gbc_nombreCalleValueTextField.gridy = 18;
					registerPanel.add(nombreCalleValueTextField, gbc_nombreCalleValueTextField);
					nombreCalleValueTextField.setColumns(10);
				}
				{
					dirViaValueTextField = new JTextField();
					GridBagConstraints gbc_dirViaValueTextField = new GridBagConstraints();
					gbc_dirViaValueTextField.insets = new Insets(0, 0, 5, 0);
					gbc_dirViaValueTextField.fill = GridBagConstraints.HORIZONTAL;
					gbc_dirViaValueTextField.gridx = 10;
					gbc_dirViaValueTextField.gridy = 18;
					registerPanel.add(dirViaValueTextField, gbc_dirViaValueTextField);
					dirViaValueTextField.setColumns(10);
				}
				{
					JLabel codigoPostalLabel = new JLabel("Código Postal:");
					GridBagConstraints gbc_codigoPostalLabel = new GridBagConstraints();
					gbc_codigoPostalLabel.insets = new Insets(0, 0, 5, 5);
					gbc_codigoPostalLabel.gridx = 2;
					gbc_codigoPostalLabel.gridy = 20;
					registerPanel.add(codigoPostalLabel, gbc_codigoPostalLabel);
				}
				{
					JLabel localidadLabel = new JLabel("Localidad:");
					GridBagConstraints gbc_localidadLabel = new GridBagConstraints();
					gbc_localidadLabel.insets = new Insets(0, 0, 5, 0);
					gbc_localidadLabel.gridx = 10;
					gbc_localidadLabel.gridy = 20;
					registerPanel.add(localidadLabel, gbc_localidadLabel);
				}
				{
					codigoPostalValueTextField = new JTextField();
					GridBagConstraints gbc_codigoPostalValueTextField = new GridBagConstraints();
					gbc_codigoPostalValueTextField.insets = new Insets(0, 0, 5, 5);
					gbc_codigoPostalValueTextField.fill = GridBagConstraints.HORIZONTAL;
					gbc_codigoPostalValueTextField.gridx = 2;
					gbc_codigoPostalValueTextField.gridy = 21;
					registerPanel.add(codigoPostalValueTextField, gbc_codigoPostalValueTextField);
					codigoPostalValueTextField.setColumns(10);
				}
				{
					localidadValueComboBox = new JComboBox();
					GridBagConstraints gbc_localidadValueComboBox = new GridBagConstraints();
					gbc_localidadValueComboBox.insets = new Insets(0, 0, 5, 0);
					gbc_localidadValueComboBox.fill = GridBagConstraints.HORIZONTAL;
					gbc_localidadValueComboBox.gridx = 10;
					gbc_localidadValueComboBox.gridy = 21;
					registerPanel.add(localidadValueComboBox, gbc_localidadValueComboBox);
				}
				{
					JLabel provinciaLabel = new JLabel("Provincia:");
					GridBagConstraints gbc_provinciaLabel = new GridBagConstraints();
					gbc_provinciaLabel.insets = new Insets(0, 0, 5, 5);
					gbc_provinciaLabel.gridx = 2;
					gbc_provinciaLabel.gridy = 23;
					registerPanel.add(provinciaLabel, gbc_provinciaLabel);
				}
				{
					JLabel paisLabel = new JLabel("País:");
					GridBagConstraints gbc_paisLabel = new GridBagConstraints();
					gbc_paisLabel.insets = new Insets(0, 0, 5, 0);
					gbc_paisLabel.gridx = 10;
					gbc_paisLabel.gridy = 23;
					registerPanel.add(paisLabel, gbc_paisLabel);
				}
				{
					provinciaValueComboBox = new JComboBox();
					GridBagConstraints gbc_provinciaValueComboBox = new GridBagConstraints();
					gbc_provinciaValueComboBox.insets = new Insets(0, 0, 0, 5);
					gbc_provinciaValueComboBox.fill = GridBagConstraints.HORIZONTAL;
					gbc_provinciaValueComboBox.gridx = 2;
					gbc_provinciaValueComboBox.gridy = 24;
					registerPanel.add(provinciaValueComboBox, gbc_provinciaValueComboBox);
				}
				{
					paisValueComboBox = new JComboBox();
					GridBagConstraints gbc_paisValueComboBox = new GridBagConstraints();
					gbc_paisValueComboBox.fill = GridBagConstraints.HORIZONTAL;
					gbc_paisValueComboBox.gridx = 10;
					gbc_paisValueComboBox.gridy = 24;
					registerPanel.add(paisValueComboBox, gbc_paisValueComboBox);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				saveButton = new JButton();
				saveButton.setAction(new CreateEmpleadoAction(this, "Guardar"));
				buttonPane.add(saveButton);
				getRootPane().setDefaultButton(saveButton);
			}


		}

		postInitialize();
	}

	private void postInitialize() {

				localidadValueComboBox.setRenderer(new LocalidadListCellRenderer());
				
				provinciaValueComboBox.setRenderer(new ProvinciaListCellRenderer());
				
				paisValueComboBox.setRenderer(new PaisListCellRenderer());
	}

	public void setComboModel(DefaultComboBoxModel<Localidad> localidadModel,
			DefaultComboBoxModel<Provincia> provinciaModel, DefaultComboBoxModel<Pais> paisModel) {

		localidadValueComboBox.setModel(localidadModel);
		provinciaValueComboBox.setModel(provinciaModel);
		paisValueComboBox.setModel(paisModel);
	}

	public void setModel(EmpleadoDTO empleado, DireccionDTO direccion) {
		empleado.setNombre(nombreValueTextField.getText());
		empleado.setApellido1(apellido1ValueTextField.getText());
		empleado.setApellido2(SwingUtils.getTextFieldValueOrNull(apellido2ValueTextField));
		empleado.setDniNie(dniNieValueTextField.getText());
		empleado.setEmail(mailValueTextField.getText());
		empleado.setTelefono(telefonoValueTextField.getText());
		empleado.setTipo_empleado_id((Integer)tipoEmpleadoIdSpinner.getValue());
		empleado.setPassword(passwordField.getPassword().toString());

		direccion.setNombreVia(nombreCalleValueTextField.getText());
		direccion.setDirVia(dirViaValueTextField.getText());

		Localidad localidad = (Localidad) localidadValueComboBox.getSelectedItem();
		direccion.setLocalidadId(localidad.getId());

		empleado.setDireccion(direccion);
	}
}
