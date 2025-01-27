package com.pinguela.thegoldenbook.ui.desktop.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.pinguela.thegoldenbook.service.AutorService;
import com.pinguela.thegoldenbook.service.impl.AutorServiceImpl;
import com.pinguela.thegoldenbook.ui.desktop.controller.CreateAutorAction;
import com.pinguela.thegoldenbook.ui.desktop.utils.SwingUtils;
import com.toedter.calendar.JDateChooser;
import java.util.ResourceBundle;

public class AutorCreateDialog extends TheGoldenBookDialog {
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("i18n.Messages"); //$NON-NLS-1$

	private final JPanel contentPanel = new JPanel();
	private JTextField nombreValueTextField;
	private JTextField apellido1ValueTextField;
	private JTextField segundoApellidoValueTextField;
	private JDateChooser dateChooser;


	public AutorCreateDialog() {
		initialize();
		postInitialize();
	}
	/**
	 * Create the dialog.
	 */
	public void initialize() {
		setBounds(100, 100, 726, 458);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 179, 161, 139, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel creacionAutorLabel = new JLabel("Creación de Autor:");
			GridBagConstraints gbc_creacionAutorLabel = new GridBagConstraints();
			gbc_creacionAutorLabel.insets = new Insets(0, 0, 5, 5);
			gbc_creacionAutorLabel.gridx = 4;
			gbc_creacionAutorLabel.gridy = 1;
			contentPanel.add(creacionAutorLabel, gbc_creacionAutorLabel);
		}
		{
			JLabel nombreLabel = new JLabel("Nombre:");
			GridBagConstraints gbc_nombreLabel = new GridBagConstraints();
			gbc_nombreLabel.insets = new Insets(0, 0, 5, 5);
			gbc_nombreLabel.gridx = 3;
			gbc_nombreLabel.gridy = 3;
			contentPanel.add(nombreLabel, gbc_nombreLabel);
		}
		{
			JLabel lblSegundoApellido = new JLabel("Segundo Apellido");
			GridBagConstraints gbc_lblSegundoApellido = new GridBagConstraints();
			gbc_lblSegundoApellido.insets = new Insets(0, 0, 5, 5);
			gbc_lblSegundoApellido.gridx = 5;
			gbc_lblSegundoApellido.gridy = 3;
			contentPanel.add(lblSegundoApellido, gbc_lblSegundoApellido);
		}
		{
			nombreValueTextField = new JTextField();
			GridBagConstraints gbc_nombreValueTextField = new GridBagConstraints();
			gbc_nombreValueTextField.insets = new Insets(0, 0, 5, 5);
			gbc_nombreValueTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_nombreValueTextField.gridx = 3;
			gbc_nombreValueTextField.gridy = 4;
			contentPanel.add(nombreValueTextField, gbc_nombreValueTextField);
			nombreValueTextField.setColumns(10);
		}
		{
			segundoApellidoValueTextField = new JTextField();
			GridBagConstraints gbc_segundoApellidoValueTextField = new GridBagConstraints();
			gbc_segundoApellidoValueTextField.insets = new Insets(0, 0, 5, 5);
			gbc_segundoApellidoValueTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_segundoApellidoValueTextField.gridx = 5;
			gbc_segundoApellidoValueTextField.gridy = 4;
			contentPanel.add(segundoApellidoValueTextField, gbc_segundoApellidoValueTextField);
			segundoApellidoValueTextField.setColumns(10);
		}
		{
			JLabel apellido1Label = new JLabel("Primer Apellido");
			GridBagConstraints gbc_apellido1Label = new GridBagConstraints();
			gbc_apellido1Label.insets = new Insets(0, 0, 5, 5);
			gbc_apellido1Label.gridx = 3;
			gbc_apellido1Label.gridy = 6;
			contentPanel.add(apellido1Label, gbc_apellido1Label);
		}
		{
			JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento:");
			GridBagConstraints gbc_lblFechaDeNacimiento = new GridBagConstraints();
			gbc_lblFechaDeNacimiento.insets = new Insets(0, 0, 5, 5);
			gbc_lblFechaDeNacimiento.gridx = 5;
			gbc_lblFechaDeNacimiento.gridy = 6;
			contentPanel.add(lblFechaDeNacimiento, gbc_lblFechaDeNacimiento);
		}
		{
			apellido1ValueTextField = new JTextField();
			GridBagConstraints gbc_apellido1ValueTextField = new GridBagConstraints();
			gbc_apellido1ValueTextField.insets = new Insets(0, 0, 5, 5);
			gbc_apellido1ValueTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_apellido1ValueTextField.gridx = 3;
			gbc_apellido1ValueTextField.gridy = 7;
			contentPanel.add(apellido1ValueTextField, gbc_apellido1ValueTextField);
			apellido1ValueTextField.setColumns(10);
		}
		{
			dateChooser = new JDateChooser();
			GridBagConstraints gbc_dateChooser = new GridBagConstraints();
			gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
			gbc_dateChooser.fill = GridBagConstraints.HORIZONTAL;
			gbc_dateChooser.gridx = 5;
			gbc_dateChooser.gridy = 7;
			contentPanel.add(dateChooser, gbc_dateChooser);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton saveButton = new JButton();
				saveButton.setAction(new CreateAutorAction(this, "Guardar"));
				buttonPane.add(saveButton);
				getRootPane().setDefaultButton(saveButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private void postInitialize() {
	}


	public String getAutorNombreValue() {
		return nombreValueTextField.getText();
	}

	public String getAutorApellido1Value() {
		return apellido1ValueTextField.getText();
	}

	public String getAutorApellido2Value() {
		return SwingUtils.getTextFieldValueOrNull(segundoApellidoValueTextField);
	}

	public Date getAutorFechaNacimiento() {
		return dateChooser.getDate();
	}

}
