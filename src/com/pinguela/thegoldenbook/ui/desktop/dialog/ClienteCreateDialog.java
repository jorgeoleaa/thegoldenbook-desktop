package com.pinguela.thegoldenbook.ui.desktop.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.pinguela.thegoldenbook.model.ClienteDTO;
import com.pinguela.thegoldenbook.ui.desktop.controller.CreateClienteAction;
import com.pinguela.thegoldenbook.ui.desktop.controller.ShowCreateDireccionAction;
import com.pinguela.thegoldenbook.ui.desktop.utils.PasswordGenerator;

public class ClienteCreateDialog extends TheGoldenBookDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nicknameValueTextField;
	private JTextField dniValueTextField;
	private JTextField nombreValueTextField;
	private JTextField primerApellidoValueTextField;
	private JTextField segundoApellidoValueTextField;
	private JTextField mailValueTextField;
	private JTextField telefonoValueTextField;
	private JPasswordField passwordField;
	private JButton addDireccionButton;
	private JButton cancelButton;
	private JButton generatePasswordButton;
	
	private Long clienteId = null;
	

	public ClienteCreateDialog() {
		initialize();
	}
	
	/**
	 * Create the dialog.
	 */
	public void initialize() {
		setBounds(100, 100, 794, 463);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel clienteDataPanel = new JPanel();
			contentPanel.add(clienteDataPanel, BorderLayout.CENTER);
			GridBagLayout gbl_clienteDataPanel = new GridBagLayout();
			gbl_clienteDataPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 116, 121, 128, 0, 0, 0, 0, 0, 0, 0};
			gbl_clienteDataPanel.rowHeights = new int[]{0, 0, 0, 30, 0, 0, 0, 0, 0, 22, 0, 0, 0, 0, 0, 0};
			gbl_clienteDataPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_clienteDataPanel.rowWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
			clienteDataPanel.setLayout(gbl_clienteDataPanel);
			{
				JLabel tituloLabel = new JLabel("Creación de cliente:");
				GridBagConstraints gbc_tituloLabel = new GridBagConstraints();
				gbc_tituloLabel.insets = new Insets(0, 0, 5, 5);
				gbc_tituloLabel.gridx = 8;
				gbc_tituloLabel.gridy = 1;
				clienteDataPanel.add(tituloLabel, gbc_tituloLabel);
			}
			{
				JLabel iconLabel = new JLabel("");
				iconLabel.setIcon(new ImageIcon(ClienteCreateDialog.class.getResource("/icons/icons8-male-user-96.png")));
				GridBagConstraints gbc_iconLabel = new GridBagConstraints();
				gbc_iconLabel.insets = new Insets(0, 0, 5, 5);
				gbc_iconLabel.gridx = 8;
				gbc_iconLabel.gridy = 2;
				clienteDataPanel.add(iconLabel, gbc_iconLabel);
			}
			{
				JLabel nombreUsuarioLabel = new JLabel("Nombre de usuario:");
				GridBagConstraints gbc_nombreUsuarioLabel = new GridBagConstraints();
				gbc_nombreUsuarioLabel.insets = new Insets(0, 0, 5, 5);
				gbc_nombreUsuarioLabel.gridx = 7;
				gbc_nombreUsuarioLabel.gridy = 3;
				clienteDataPanel.add(nombreUsuarioLabel, gbc_nombreUsuarioLabel);
			}
			{
				JLabel lblNewLabel = new JLabel("DNI/NIE:");
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel.gridx = 9;
				gbc_lblNewLabel.gridy = 3;
				clienteDataPanel.add(lblNewLabel, gbc_lblNewLabel);
			}
			{
				nicknameValueTextField = new JTextField();
				GridBagConstraints gbc_nicknameValueTextField = new GridBagConstraints();
				gbc_nicknameValueTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_nicknameValueTextField.insets = new Insets(0, 0, 5, 5);
				gbc_nicknameValueTextField.gridx = 7;
				gbc_nicknameValueTextField.gridy = 4;
				clienteDataPanel.add(nicknameValueTextField, gbc_nicknameValueTextField);
				nicknameValueTextField.setColumns(10);
			}
			{
				dniValueTextField = new JTextField();
				GridBagConstraints gbc_dniValueTextField = new GridBagConstraints();
				gbc_dniValueTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_dniValueTextField.insets = new Insets(0, 0, 5, 5);
				gbc_dniValueTextField.gridx = 9;
				gbc_dniValueTextField.gridy = 4;
				clienteDataPanel.add(dniValueTextField, gbc_dniValueTextField);
				dniValueTextField.setColumns(10);
			}
			{
				JLabel nombreLabel = new JLabel("Nombre:");
				GridBagConstraints gbc_nombreLabel = new GridBagConstraints();
				gbc_nombreLabel.insets = new Insets(0, 0, 5, 5);
				gbc_nombreLabel.gridx = 7;
				gbc_nombreLabel.gridy = 6;
				clienteDataPanel.add(nombreLabel, gbc_nombreLabel);
			}
			{
				JLabel mailLabel = new JLabel("Correo electrónico");
				GridBagConstraints gbc_mailLabel = new GridBagConstraints();
				gbc_mailLabel.insets = new Insets(0, 0, 5, 5);
				gbc_mailLabel.gridx = 9;
				gbc_mailLabel.gridy = 6;
				clienteDataPanel.add(mailLabel, gbc_mailLabel);
			}
			{
				nombreValueTextField = new JTextField();
				GridBagConstraints gbc_nombreValueTextField = new GridBagConstraints();
				gbc_nombreValueTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_nombreValueTextField.insets = new Insets(0, 0, 5, 5);
				gbc_nombreValueTextField.gridx = 7;
				gbc_nombreValueTextField.gridy = 7;
				clienteDataPanel.add(nombreValueTextField, gbc_nombreValueTextField);
				nombreValueTextField.setColumns(10);
			}
			{
				mailValueTextField = new JTextField();
				GridBagConstraints gbc_mailValueTextField = new GridBagConstraints();
				gbc_mailValueTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_mailValueTextField.insets = new Insets(0, 0, 5, 5);
				gbc_mailValueTextField.gridx = 9;
				gbc_mailValueTextField.gridy = 7;
				clienteDataPanel.add(mailValueTextField, gbc_mailValueTextField);
				mailValueTextField.setColumns(10);
			}
			{
				JLabel apellido1Label = new JLabel("Primer apellido:");
				GridBagConstraints gbc_apellido1Label = new GridBagConstraints();
				gbc_apellido1Label.insets = new Insets(0, 0, 5, 5);
				gbc_apellido1Label.gridx = 7;
				gbc_apellido1Label.gridy = 9;
				clienteDataPanel.add(apellido1Label, gbc_apellido1Label);
			}
			{
				JLabel telefonoLabel = new JLabel("Teléfono");
				GridBagConstraints gbc_telefonoLabel = new GridBagConstraints();
				gbc_telefonoLabel.insets = new Insets(0, 0, 5, 5);
				gbc_telefonoLabel.gridx = 9;
				gbc_telefonoLabel.gridy = 9;
				clienteDataPanel.add(telefonoLabel, gbc_telefonoLabel);
			}
			{
				primerApellidoValueTextField = new JTextField();
				GridBagConstraints gbc_primerApellidoValueTextField = new GridBagConstraints();
				gbc_primerApellidoValueTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_primerApellidoValueTextField.insets = new Insets(0, 0, 5, 5);
				gbc_primerApellidoValueTextField.gridx = 7;
				gbc_primerApellidoValueTextField.gridy = 10;
				clienteDataPanel.add(primerApellidoValueTextField, gbc_primerApellidoValueTextField);
				primerApellidoValueTextField.setColumns(10);
			}
			{
				telefonoValueTextField = new JTextField();
				GridBagConstraints gbc_telefonoValueTextField = new GridBagConstraints();
				gbc_telefonoValueTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_telefonoValueTextField.insets = new Insets(0, 0, 5, 5);
				gbc_telefonoValueTextField.gridx = 9;
				gbc_telefonoValueTextField.gridy = 10;
				clienteDataPanel.add(telefonoValueTextField, gbc_telefonoValueTextField);
				telefonoValueTextField.setColumns(10);
			}
			{
				JLabel apellido2Label = new JLabel("Segundo apellido:");
				GridBagConstraints gbc_apellido2Label = new GridBagConstraints();
				gbc_apellido2Label.insets = new Insets(0, 0, 5, 5);
				gbc_apellido2Label.gridx = 7;
				gbc_apellido2Label.gridy = 12;
				clienteDataPanel.add(apellido2Label, gbc_apellido2Label);
			}
			{
				JLabel contraseñaLabel = new JLabel("Contraseña:");
				GridBagConstraints gbc_contraseñaLabel = new GridBagConstraints();
				gbc_contraseñaLabel.insets = new Insets(0, 0, 5, 5);
				gbc_contraseñaLabel.gridx = 9;
				gbc_contraseñaLabel.gridy = 12;
				clienteDataPanel.add(contraseñaLabel, gbc_contraseñaLabel);
			}
			{
				segundoApellidoValueTextField = new JTextField();
				GridBagConstraints gbc_segundoApellidoValueTextField = new GridBagConstraints();
				gbc_segundoApellidoValueTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_segundoApellidoValueTextField.insets = new Insets(0, 0, 5, 5);
				gbc_segundoApellidoValueTextField.gridx = 7;
				gbc_segundoApellidoValueTextField.gridy = 13;
				clienteDataPanel.add(segundoApellidoValueTextField, gbc_segundoApellidoValueTextField);
				segundoApellidoValueTextField.setColumns(10);
			}
			{
				passwordField = new JPasswordField();
				GridBagConstraints gbc_passwordField = new GridBagConstraints();
				gbc_passwordField.insets = new Insets(0, 0, 5, 5);
				gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
				gbc_passwordField.gridx = 9;
				gbc_passwordField.gridy = 13;
				clienteDataPanel.add(passwordField, gbc_passwordField);
			}
			{
				addDireccionButton = new JButton();
				addDireccionButton.setAction(new ShowCreateDireccionAction(this, "Añadir dirección"));
				GridBagConstraints gbc_addDireccionButton = new GridBagConstraints();
				gbc_addDireccionButton.insets = new Insets(0, 0, 0, 5);
				gbc_addDireccionButton.gridx = 7;
				gbc_addDireccionButton.gridy = 14;
				clienteDataPanel.add(addDireccionButton, gbc_addDireccionButton);
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
				gbc_generatePasswordButton.insets = new Insets(0, 0, 0, 5);
				gbc_generatePasswordButton.gridx = 9;
				gbc_generatePasswordButton.gridy = 14;
				clienteDataPanel.add(generatePasswordButton, gbc_generatePasswordButton);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton createButton = new JButton();
				createButton.setAction( new CreateClienteAction(this, "Guardar"));
				createButton.setIcon(new ImageIcon(ClienteCreateDialog.class.getResource("/nuvola/32x32/1715_colour_pen_colour_color_pencil_pen_line_color_pencil_line.png")));
				createButton.setActionCommand("OK");
				buttonPane.add(createButton);
				getRootPane().setDefaultButton(createButton);
			}
			{
				cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setEmptModel();
					}
				});
				cancelButton.setIcon(new ImageIcon(ClienteCreateDialog.class.getResource("/nuvola/32x32/1250_delete_delete.png")));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		postInitialize();
	}

	private void postInitialize() {
		addDireccionButton.setEnabled(false);
	}

	public void setClienteData(ClienteDTO cliente) {
		cliente.setNickname(nicknameValueTextField.getText());
		cliente.setNombre(nombreValueTextField.getText());
		cliente.setApellido1(primerApellidoValueTextField.getText());
		cliente.setApellido2(segundoApellidoValueTextField.getText());
		cliente.setDniNie(dniValueTextField.getText());
		cliente.setEmail(mailValueTextField.getText());
		cliente.setTelefono(telefonoValueTextField.getText());
		cliente.setPassword(passwordField.getPassword().toString());

	}

	public void setEmptModel() {
		nicknameValueTextField.setText(null);
		nombreValueTextField.setText(null);
		primerApellidoValueTextField.setText(null);
		segundoApellidoValueTextField.setText(null);
		dniValueTextField.setText(null);
		mailValueTextField.setText(null);
		telefonoValueTextField.setText(null);
		passwordField.setText(null);
	}

	public JButton getDirectionButton() {
		return addDireccionButton;
	}

	public void setClienteCreadoId(Long id) {
		this.clienteId = id;
	}

	public Long getClienteCreadoId() {
		return this.clienteId;
	}

}
