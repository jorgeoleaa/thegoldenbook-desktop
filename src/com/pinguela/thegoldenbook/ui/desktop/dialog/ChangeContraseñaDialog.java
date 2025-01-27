package com.pinguela.thegoldenbook.ui.desktop.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.pinguela.thegoldenbook.ui.desktop.controller.ChangePasswordAction;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JPasswordField;
import java.awt.Insets;

public class ChangeContraseñaDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPasswordField newPasswordField;
	private JPasswordField confirmPasswordField;
	
	/**
	 * Create the dialog.
	 */
	public ChangeContraseñaDialog() {
		setBounds(100, 100, 359, 284);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel newPasswordLabel = new JLabel("Nueva contraseña:");
			GridBagConstraints gbc_newPasswordLabel = new GridBagConstraints();
			gbc_newPasswordLabel.insets = new Insets(0, 0, 5, 0);
			gbc_newPasswordLabel.gridx = 10;
			gbc_newPasswordLabel.gridy = 3;
			contentPanel.add(newPasswordLabel, gbc_newPasswordLabel);
		}
		{
			newPasswordField = new JPasswordField();
			GridBagConstraints gbc_newPasswordField = new GridBagConstraints();
			gbc_newPasswordField.insets = new Insets(0, 0, 5, 0);
			gbc_newPasswordField.fill = GridBagConstraints.HORIZONTAL;
			gbc_newPasswordField.gridx = 10;
			gbc_newPasswordField.gridy = 4;
			contentPanel.add(newPasswordField, gbc_newPasswordField);
		}
		{
			JLabel confirmPasswordLabel = new JLabel("Confirmar contraseña");
			GridBagConstraints gbc_confirmPasswordLabel = new GridBagConstraints();
			gbc_confirmPasswordLabel.insets = new Insets(0, 0, 5, 0);
			gbc_confirmPasswordLabel.gridx = 10;
			gbc_confirmPasswordLabel.gridy = 6;
			contentPanel.add(confirmPasswordLabel, gbc_confirmPasswordLabel);
		}
		{
			confirmPasswordField = new JPasswordField();
			GridBagConstraints gbc_confirmPasswordField = new GridBagConstraints();
			gbc_confirmPasswordField.fill = GridBagConstraints.HORIZONTAL;
			gbc_confirmPasswordField.gridx = 10;
			gbc_confirmPasswordField.gridy = 7;
			contentPanel.add(confirmPasswordField, gbc_confirmPasswordField);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton confirmButton = new JButton("Confirmar");
				confirmButton.setAction(new ChangePasswordAction(this, "Confirmar"));
				buttonPane.add(confirmButton);
				getRootPane().setDefaultButton(confirmButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public String getNewPasswordValue() {
		return new String(newPasswordField.getPassword());
	}
	
	public String getConfirmPasswordValue() {
		return new String(confirmPasswordField.getPassword());
	}

}
