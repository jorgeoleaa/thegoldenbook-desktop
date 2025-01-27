package com.pinguela.thegoldenbook.ui.desktop.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.pinguela.thegoldenbook.model.ClienteDTO;
import com.pinguela.thegoldenbook.ui.desktop.controller.ShowCreateDireccionAction;
import com.pinguela.thegoldenbook.ui.desktop.controller.UpdateClienteAction;
import com.pinguela.thegoldenbook.ui.desktop.model.DireccionTableModel;
import com.pinguela.thegoldenbook.ui.desktop.renderer.DireccionTableCellRenderer;
import com.pinguela.thegoldenbook.ui.desktop.utils.SwingUtils;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClienteDetailDialog extends TheGoldenBookDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable direccionesTable;
	private JTextField idValueTextField;
	private JTextField nombreUsuarioValueTextField;
	private JTextField nombreValueTextField;
	private JTextField apellido1ValueTextField;
	private JTextField apellido2ValueTextField;
	private JTextField dniNieValueTextField;
	private JTextField emailValueTextField;
	private JTextField telefonoValueTextField;

	private ClienteDTO cliente = null;
	private JButton saveButton;
	private JButton editButton;
	private JButton cancelButton;
	private JButton addDireccionButton;
	/**
	 * Create the dialog.
	 */
	public ClienteDetailDialog(ClienteDTO c) {
		this.cliente = c;
		setBounds(100, 100, 725, 466);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel clienteDataPanel = new JPanel();
			contentPanel.add(clienteDataPanel, BorderLayout.CENTER);
			GridBagLayout gbl_clienteDataPanel = new GridBagLayout();
			gbl_clienteDataPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
			gbl_clienteDataPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			gbl_clienteDataPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
			gbl_clienteDataPanel.rowWeights = new double[]{0.0, 1.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
			clienteDataPanel.setLayout(gbl_clienteDataPanel);
			{
				JLabel tituloLabel = new JLabel("Datos del cliente:");
				GridBagConstraints gbc_tituloLabel = new GridBagConstraints();
				gbc_tituloLabel.insets = new Insets(0, 0, 5, 5);
				gbc_tituloLabel.gridx = 3;
				gbc_tituloLabel.gridy = 1;
				clienteDataPanel.add(tituloLabel, gbc_tituloLabel);
			}
			{
				JLabel idLabel = new JLabel("Identificador:");
				GridBagConstraints gbc_idLabel = new GridBagConstraints();
				gbc_idLabel.insets = new Insets(0, 0, 5, 5);
				gbc_idLabel.gridx = 1;
				gbc_idLabel.gridy = 3;
				clienteDataPanel.add(idLabel, gbc_idLabel);
			}
			{
				JLabel nicknameLabel = new JLabel("Nombre usuario:");
				GridBagConstraints gbc_nicknameLabel = new GridBagConstraints();
				gbc_nicknameLabel.insets = new Insets(0, 0, 5, 5);
				gbc_nicknameLabel.gridx = 5;
				gbc_nicknameLabel.gridy = 3;
				clienteDataPanel.add(nicknameLabel, gbc_nicknameLabel);
			}
			{
				idValueTextField = new JTextField();
				idValueTextField.setEnabled(false);
				idValueTextField.setEditable(false);
				GridBagConstraints gbc_idValueTextField = new GridBagConstraints();
				gbc_idValueTextField.insets = new Insets(0, 0, 5, 5);
				gbc_idValueTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_idValueTextField.gridx = 1;
				gbc_idValueTextField.gridy = 4;
				clienteDataPanel.add(idValueTextField, gbc_idValueTextField);
				idValueTextField.setColumns(10);
			}
			{
				nombreUsuarioValueTextField = new JTextField();
				nombreUsuarioValueTextField.setEnabled(false);
				nombreUsuarioValueTextField.setEditable(false);
				GridBagConstraints gbc_nombreUsuarioValueTextField = new GridBagConstraints();
				gbc_nombreUsuarioValueTextField.insets = new Insets(0, 0, 5, 5);
				gbc_nombreUsuarioValueTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_nombreUsuarioValueTextField.gridx = 5;
				gbc_nombreUsuarioValueTextField.gridy = 4;
				clienteDataPanel.add(nombreUsuarioValueTextField, gbc_nombreUsuarioValueTextField);
				nombreUsuarioValueTextField.setColumns(10);
			}
			{
				JLabel nombreLabel = new JLabel("Nombre:");
				GridBagConstraints gbc_nombreLabel = new GridBagConstraints();
				gbc_nombreLabel.insets = new Insets(0, 0, 5, 5);
				gbc_nombreLabel.gridx = 1;
				gbc_nombreLabel.gridy = 6;
				clienteDataPanel.add(nombreLabel, gbc_nombreLabel);
			}
			{
				JLabel apellido1Label = new JLabel("Primer apellido:");
				GridBagConstraints gbc_apellido1Label = new GridBagConstraints();
				gbc_apellido1Label.insets = new Insets(0, 0, 5, 5);
				gbc_apellido1Label.gridx = 5;
				gbc_apellido1Label.gridy = 6;
				clienteDataPanel.add(apellido1Label, gbc_apellido1Label);
			}
			{
				nombreValueTextField = new JTextField();
				nombreValueTextField.setEnabled(false);
				nombreValueTextField.setEditable(false);
				GridBagConstraints gbc_nombreValueTextField = new GridBagConstraints();
				gbc_nombreValueTextField.insets = new Insets(0, 0, 5, 5);
				gbc_nombreValueTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_nombreValueTextField.gridx = 1;
				gbc_nombreValueTextField.gridy = 7;
				clienteDataPanel.add(nombreValueTextField, gbc_nombreValueTextField);
				nombreValueTextField.setColumns(10);
			}
			{
				apellido1ValueTextField = new JTextField();
				apellido1ValueTextField.setEnabled(false);
				apellido1ValueTextField.setEditable(false);
				GridBagConstraints gbc_apellido1ValueTextField = new GridBagConstraints();
				gbc_apellido1ValueTextField.insets = new Insets(0, 0, 5, 5);
				gbc_apellido1ValueTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_apellido1ValueTextField.gridx = 5;
				gbc_apellido1ValueTextField.gridy = 7;
				clienteDataPanel.add(apellido1ValueTextField, gbc_apellido1ValueTextField);
				apellido1ValueTextField.setColumns(10);
			}
			{
				JLabel apellido2Label = new JLabel("Segundo apellido");
				GridBagConstraints gbc_apellido2Label = new GridBagConstraints();
				gbc_apellido2Label.insets = new Insets(0, 0, 5, 5);
				gbc_apellido2Label.gridx = 1;
				gbc_apellido2Label.gridy = 9;
				clienteDataPanel.add(apellido2Label, gbc_apellido2Label);
			}
			{
				JLabel dniNieLabel = new JLabel("DNI/NIE:");
				GridBagConstraints gbc_dniNieLabel = new GridBagConstraints();
				gbc_dniNieLabel.insets = new Insets(0, 0, 5, 5);
				gbc_dniNieLabel.gridx = 5;
				gbc_dniNieLabel.gridy = 9;
				clienteDataPanel.add(dniNieLabel, gbc_dniNieLabel);
			}
			{
				apellido2ValueTextField = new JTextField();
				apellido2ValueTextField.setEnabled(false);
				apellido2ValueTextField.setEditable(false);
				GridBagConstraints gbc_apellido2ValueTextField = new GridBagConstraints();
				gbc_apellido2ValueTextField.insets = new Insets(0, 0, 5, 5);
				gbc_apellido2ValueTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_apellido2ValueTextField.gridx = 1;
				gbc_apellido2ValueTextField.gridy = 10;
				clienteDataPanel.add(apellido2ValueTextField, gbc_apellido2ValueTextField);
				apellido2ValueTextField.setColumns(10);
			}
			{
				dniNieValueTextField = new JTextField();
				dniNieValueTextField.setEnabled(false);
				dniNieValueTextField.setEditable(false);
				GridBagConstraints gbc_dniNieValueTextField = new GridBagConstraints();
				gbc_dniNieValueTextField.insets = new Insets(0, 0, 5, 5);
				gbc_dniNieValueTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_dniNieValueTextField.gridx = 5;
				gbc_dniNieValueTextField.gridy = 10;
				clienteDataPanel.add(dniNieValueTextField, gbc_dniNieValueTextField);
				dniNieValueTextField.setColumns(10);
			}
			{
				JLabel emailLabel = new JLabel("Correo electrónico:");
				GridBagConstraints gbc_emailLabel = new GridBagConstraints();
				gbc_emailLabel.insets = new Insets(0, 0, 5, 5);
				gbc_emailLabel.gridx = 1;
				gbc_emailLabel.gridy = 12;
				clienteDataPanel.add(emailLabel, gbc_emailLabel);
			}
			{
				JLabel telefonoLabel = new JLabel("Teléfono:");
				GridBagConstraints gbc_telefonoLabel = new GridBagConstraints();
				gbc_telefonoLabel.insets = new Insets(0, 0, 5, 5);
				gbc_telefonoLabel.gridx = 5;
				gbc_telefonoLabel.gridy = 12;
				clienteDataPanel.add(telefonoLabel, gbc_telefonoLabel);
			}
			{
				emailValueTextField = new JTextField();
				emailValueTextField.setEnabled(false);
				emailValueTextField.setEditable(false);
				GridBagConstraints gbc_emailValueTextField = new GridBagConstraints();
				gbc_emailValueTextField.insets = new Insets(0, 0, 0, 5);
				gbc_emailValueTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_emailValueTextField.gridx = 1;
				gbc_emailValueTextField.gridy = 13;
				clienteDataPanel.add(emailValueTextField, gbc_emailValueTextField);
				emailValueTextField.setColumns(10);
			}
			{
				telefonoValueTextField = new JTextField();
				telefonoValueTextField.setEnabled(false);
				telefonoValueTextField.setEditable(false);
				GridBagConstraints gbc_telefonoValueTextField = new GridBagConstraints();
				gbc_telefonoValueTextField.insets = new Insets(0, 0, 0, 5);
				gbc_telefonoValueTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_telefonoValueTextField.gridx = 5;
				gbc_telefonoValueTextField.gridy = 13;
				clienteDataPanel.add(telefonoValueTextField, gbc_telefonoValueTextField);
				telefonoValueTextField.setColumns(10);
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.EAST);
			{
				direccionesTable = new JTable();
				scrollPane.setViewportView(direccionesTable);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			GridBagLayout gbl_buttonPane = new GridBagLayout();
			gbl_buttonPane.columnWidths = new int[]{0, 0, 0, 227, 0, 0, 0, 0, 0, 45, 63, 0, 0, 0, 0};
			gbl_buttonPane.rowHeights = new int[]{21, 0};
			gbl_buttonPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_buttonPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
			buttonPane.setLayout(gbl_buttonPane);
			{
				editButton = new JButton("Editar");
				editButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setEditable(true);
					}
				});
				editButton.setIcon(new ImageIcon(ClienteDetailDialog.class.getResource("/nuvola/32x32/1819_pencil_pencil.png")));
				GridBagConstraints gbc_editButton = new GridBagConstraints();
				gbc_editButton.insets = new Insets(0, 0, 0, 5);
				gbc_editButton.gridx = 3;
				gbc_editButton.gridy = 0;
				buttonPane.add(editButton, gbc_editButton);
			}
			{
				saveButton = new JButton("Guardar");
				saveButton.setAction(new UpdateClienteAction(this, "Guardar", new ImageIcon("/icons/icons8-save-48.png")));
				saveButton.setActionCommand("OK");
				GridBagConstraints gbc_saveButton = new GridBagConstraints();
				gbc_saveButton.anchor = GridBagConstraints.NORTHWEST;
				gbc_saveButton.insets = new Insets(0, 0, 0, 5);
				gbc_saveButton.gridx = 12;
				gbc_saveButton.gridy = 0;
				buttonPane.add(saveButton, gbc_saveButton);
				getRootPane().setDefaultButton(saveButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setModel();
						setEditable(false);					
					}
				});
				cancelButton.setIcon(new ImageIcon(ClienteDetailDialog.class.getResource("/nuvola/32x32/1250_delete_delete.png")));
				cancelButton.setActionCommand("Cancelar");
				GridBagConstraints gbc_cancelButton = new GridBagConstraints();
				gbc_cancelButton.anchor = GridBagConstraints.WEST;
				gbc_cancelButton.gridx = 13;
				gbc_cancelButton.gridy = 0;
				buttonPane.add(cancelButton, gbc_cancelButton);
			}
		}
		postInitialize();
	}

	private void postInitialize() {
		setModel();
		saveButton.setVisible(false);
		cancelButton.setVisible(false);
		direccionesTable.setDefaultRenderer(Object.class, new DireccionTableCellRenderer());

	}

	private void setEditable(boolean flag) {
		saveButton.setVisible(flag);
		cancelButton.setVisible(flag);
		nombreValueTextField.setEnabled(flag);
		nombreValueTextField.setEditable(flag);
		nombreUsuarioValueTextField.setEnabled(flag);
		nombreUsuarioValueTextField.setEditable(flag);
		apellido1ValueTextField.setEnabled(flag);
		apellido1ValueTextField.setEditable(flag);
		apellido2ValueTextField.setEnabled(flag);
		apellido2ValueTextField.setEditable(flag);
		dniNieValueTextField.setEnabled(flag);
		dniNieValueTextField.setEditable(flag);
		emailValueTextField.setEnabled(flag);
		emailValueTextField.setEditable(flag);
		telefonoValueTextField.setEnabled(flag);
		telefonoValueTextField.setEditable(flag);
	}

	private void setModel() {
		idValueTextField.setText(cliente.getId().toString());
		nombreValueTextField.setText(cliente.getNombre());
		apellido1ValueTextField.setText(cliente.getApellido1());
		apellido2ValueTextField.setText(cliente.getApellido2());
		nombreUsuarioValueTextField.setText(cliente.getNickname());
		telefonoValueTextField.setText(cliente.getTelefono());
		emailValueTextField.setText(cliente.getEmail());
		dniNieValueTextField.setText(cliente.getDniNie());
		direccionesTable.setModel(new DireccionTableModel(cliente.getDirecciones()));
	}

	public ClienteDTO getUpdateModel() {

		cliente.setNombre(nombreValueTextField.getText());
		cliente.setNickname(nombreUsuarioValueTextField.getText());
		cliente.setApellido1(apellido1ValueTextField.getText());
		cliente.setApellido2(SwingUtils.getTextFieldValueOrNull(apellido2ValueTextField));
		cliente.setDniNie(dniNieValueTextField.getText());
		cliente.setTelefono(telefonoValueTextField.getText());
		cliente.setEmail(emailValueTextField.getText());

		return cliente;

	}
	
	public void setResultsModel(DireccionTableModel model) {
		this.direccionesTable.setModel(model);
	}

	public Long getClienteId() {
		return Long.valueOf(idValueTextField.getText());
	}

}
