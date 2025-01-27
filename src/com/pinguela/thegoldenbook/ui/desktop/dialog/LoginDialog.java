package com.pinguela.thegoldenbook.ui.desktop.dialog;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.ui.desktop.controller.LoginAction;
import com.pinguela.thegoldenbook.ui.desktop.utils.I18n;

import java.awt.Color;

public class LoginDialog extends JDialog {


	private Logger logger = LogManager.getLogger(LoginDialog.class);

	private JTextField idValueTextField;
	private JPasswordField passwordField;
	private JLabel logoLabel;

	public static final String EMPLEADO_PROPERTY = "empleado";
	private JLabel lblNewLabel;


	/**
	 * Create the dialog.
	 */
	public LoginDialog() {

		initServices();

		setBounds(100, 100, 450, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 450, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{226, 37, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		{
			JPanel centerPanel = new JPanel();
			GridBagConstraints gbc_centerPanel = new GridBagConstraints();
			gbc_centerPanel.insets = new Insets(0, 0, 5, 5);
			gbc_centerPanel.gridx = 2;
			gbc_centerPanel.gridy = 0;
			getContentPane().add(centerPanel, gbc_centerPanel);
			GridBagLayout gbl_centerPanel = new GridBagLayout();
			gbl_centerPanel.columnWidths = new int[]{0, 0};
			gbl_centerPanel.rowHeights = new int[]{0, 0};
			gbl_centerPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gbl_centerPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
			centerPanel.setLayout(gbl_centerPanel);
			{
				JPanel loginPanel = new JPanel();
				GridBagConstraints gbc_loginPanel = new GridBagConstraints();
				gbc_loginPanel.fill = GridBagConstraints.BOTH;
				gbc_loginPanel.gridx = 0;
				gbc_loginPanel.gridy = 0;
				centerPanel.add(loginPanel, gbc_loginPanel);
				GridBagLayout gbl_loginPanel = new GridBagLayout();
				gbl_loginPanel.columnWidths = new int[]{0, 0, 0, 167, 0, 0, 0};
				gbl_loginPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
				gbl_loginPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
				gbl_loginPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
				loginPanel.setLayout(gbl_loginPanel);
				{
					JLabel imageLabel = new JLabel("");
					imageLabel.setIcon(new ImageIcon(LoginDialog.class.getResource("/icons/logotiendalibrospequeño.jpg")));
					GridBagConstraints gbc_imageLabel = new GridBagConstraints();
					gbc_imageLabel.insets = new Insets(0, 0, 5, 5);
					gbc_imageLabel.gridx = 3;
					gbc_imageLabel.gridy = 0;
					loginPanel.add(imageLabel, gbc_imageLabel);
				}
				{
					lblNewLabel = new JLabel("Identificador o contraseña icorrectas");
					lblNewLabel.setForeground(new Color(0, 0, 0));
					GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
					gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
					gbc_lblNewLabel.gridx = 3;
					gbc_lblNewLabel.gridy = 1;
					loginPanel.add(lblNewLabel, gbc_lblNewLabel);
				}
				{
					logoLabel = new JLabel("");
					GridBagConstraints gbc_logoLabel = new GridBagConstraints();
					gbc_logoLabel.insets = new Insets(0, 0, 5, 5);
					gbc_logoLabel.gridx = 3;
					gbc_logoLabel.gridy = 2;
					loginPanel.add(logoLabel, gbc_logoLabel);
				}
				{
					JLabel idLabel = new JLabel("Identificador:");
					GridBagConstraints gbc_idLabel = new GridBagConstraints();
					gbc_idLabel.insets = new Insets(0, 0, 5, 5);
					gbc_idLabel.gridx = 3;
					gbc_idLabel.gridy = 3;
					loginPanel.add(idLabel, gbc_idLabel);
				}
				{
					idValueTextField = new JTextField();
					GridBagConstraints gbc_idValueTextField = new GridBagConstraints();
					gbc_idValueTextField.fill = GridBagConstraints.HORIZONTAL;
					gbc_idValueTextField.insets = new Insets(0, 0, 5, 5);
					gbc_idValueTextField.gridx = 3;
					gbc_idValueTextField.gridy = 4;
					loginPanel.add(idValueTextField, gbc_idValueTextField);
					idValueTextField.setColumns(10);
				}
				{
					JLabel passwordLabel = new JLabel("Password:");
					GridBagConstraints gbc_passwordLabel = new GridBagConstraints();
					gbc_passwordLabel.insets = new Insets(0, 0, 5, 5);
					gbc_passwordLabel.gridx = 3;
					gbc_passwordLabel.gridy = 6;
					loginPanel.add(passwordLabel, gbc_passwordLabel);
				}
				{
					passwordField = new JPasswordField();
					GridBagConstraints gbc_passwordField = new GridBagConstraints();
					gbc_passwordField.insets = new Insets(0, 0, 0, 5);
					gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
					gbc_passwordField.gridx = 3;
					gbc_passwordField.gridy = 7;
					loginPanel.add(passwordField, gbc_passwordField);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			GridBagConstraints gbc_buttonPane = new GridBagConstraints();
			gbc_buttonPane.insets = new Insets(0, 0, 0, 5);
			gbc_buttonPane.anchor = GridBagConstraints.NORTH;
			gbc_buttonPane.fill = GridBagConstraints.HORIZONTAL;
			gbc_buttonPane.gridx = 2;
			gbc_buttonPane.gridy = 1;
			getContentPane().add(buttonPane, gbc_buttonPane);
			{
				JButton confirmButton = new JButton();
				confirmButton.setAction(new LoginAction(this, I18n.getString("entrar")));
				buttonPane.add(confirmButton);
				getRootPane().setDefaultButton(confirmButton);
			}
			{
				JButton cancelButton = new JButton(I18n.getString("cancelar"));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		postInitialize();
	}

	private void postInitialize() {
		lblNewLabel.setVisible(false);
		lblNewLabel.setBackground(Color.RED);

	}

	public JLabel getErrorLabel() {
		return lblNewLabel;
	}

	private void initServices() {


	}

	public Long getIdValue() {
		return Long.valueOf(idValueTextField.getText());
	}

	public String getPasswordValue() {
		return String.valueOf(passwordField.getPassword());
	}

}
