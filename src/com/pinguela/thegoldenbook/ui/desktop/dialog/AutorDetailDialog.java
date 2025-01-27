package com.pinguela.thegoldenbook.ui.desktop.dialog;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.pinguela.thegoldenbook.model.Autor;
import com.pinguela.thegoldenbook.ui.desktop.controller.UpdateAutorAction;
import com.pinguela.thegoldenbook.ui.desktop.utils.SwingUtils;
import com.pinguela.thegoldenbook.ui.desktop.view.AutorSearchView;
import com.toedter.calendar.JDateChooser;

public class AutorDetailDialog extends TheGoldenBookDialog {

	private JTextField nombreValueTextField;
	private JTextField apellido1ValueTextField;
	private JTextField apellido2ValueTextField;
	private JDateChooser fechaNacimientoDateChooser;
	private JButton guardarButton;
	private AutorSearchView view;
	
	private Autor autor = null;
	
	/**
	 * Create the panel.
	 */
	public AutorDetailDialog(AutorSearchView view, Autor a) {
		this.autor = a;
		this.view = view;
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel dataPanel = new JPanel();
		getContentPane().add(dataPanel);
		GridBagLayout gbl_dataPanel = new GridBagLayout();
		gbl_dataPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_dataPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_dataPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_dataPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		dataPanel.setLayout(gbl_dataPanel);

		JLabel tituloLabel = new JLabel("Detalle del aut@r:");
		GridBagConstraints gbc_tituloLabel = new GridBagConstraints();
		gbc_tituloLabel.insets = new Insets(0, 0, 5, 5);
		gbc_tituloLabel.gridx = 7;
		gbc_tituloLabel.gridy = 3;
		dataPanel.add(tituloLabel, gbc_tituloLabel);

		JLabel iconLabel = new JLabel("");
		iconLabel.setIcon(new ImageIcon(AutorDetailDialog.class.getResource("/icons/icons8-escritor-masculino-94.png")));
		GridBagConstraints gbc_iconLabel = new GridBagConstraints();
		gbc_iconLabel.insets = new Insets(0, 0, 5, 5);
		gbc_iconLabel.gridx = 7;
		gbc_iconLabel.gridy = 4;
		dataPanel.add(iconLabel, gbc_iconLabel);

		JLabel nombreLabel = new JLabel("Nombrer:");
		GridBagConstraints gbc_nombreLabel = new GridBagConstraints();
		gbc_nombreLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nombreLabel.gridx = 4;
		gbc_nombreLabel.gridy = 6;
		dataPanel.add(nombreLabel, gbc_nombreLabel);

		JLabel apellido1Label = new JLabel("Primer apellido");
		GridBagConstraints gbc_apellido1Label = new GridBagConstraints();
		gbc_apellido1Label.insets = new Insets(0, 0, 5, 0);
		gbc_apellido1Label.gridx = 11;
		gbc_apellido1Label.gridy = 6;
		dataPanel.add(apellido1Label, gbc_apellido1Label);

		nombreValueTextField = new JTextField();
		nombreValueTextField.setEnabled(false);
		GridBagConstraints gbc_nombreValueTextField = new GridBagConstraints();
		gbc_nombreValueTextField.insets = new Insets(0, 0, 5, 5);
		gbc_nombreValueTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nombreValueTextField.gridx = 4;
		gbc_nombreValueTextField.gridy = 7;
		dataPanel.add(nombreValueTextField, gbc_nombreValueTextField);
		nombreValueTextField.setColumns(10);

		apellido1ValueTextField = new JTextField();
		apellido1ValueTextField.setEnabled(false);
		GridBagConstraints gbc_apellido1ValueTextField = new GridBagConstraints();
		gbc_apellido1ValueTextField.insets = new Insets(0, 0, 5, 0);
		gbc_apellido1ValueTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_apellido1ValueTextField.gridx = 11;
		gbc_apellido1ValueTextField.gridy = 7;
		dataPanel.add(apellido1ValueTextField, gbc_apellido1ValueTextField);
		apellido1ValueTextField.setColumns(10);

		JLabel apellido2Label = new JLabel("Segundo apellido");
		GridBagConstraints gbc_apellido2Label = new GridBagConstraints();
		gbc_apellido2Label.insets = new Insets(0, 0, 5, 5);
		gbc_apellido2Label.gridx = 4;
		gbc_apellido2Label.gridy = 10;
		dataPanel.add(apellido2Label, gbc_apellido2Label);

		JLabel fechaNacimientoLabel = new JLabel("Fecha de nacimiento:");
		GridBagConstraints gbc_fechaNacimientoLabel = new GridBagConstraints();
		gbc_fechaNacimientoLabel.insets = new Insets(0, 0, 5, 0);
		gbc_fechaNacimientoLabel.gridx = 11;
		gbc_fechaNacimientoLabel.gridy = 10;
		dataPanel.add(fechaNacimientoLabel, gbc_fechaNacimientoLabel);

		apellido2ValueTextField = new JTextField();
		apellido2ValueTextField.setEnabled(false);
		GridBagConstraints gbc_apellido2ValueTextField = new GridBagConstraints();
		gbc_apellido2ValueTextField.insets = new Insets(0, 0, 5, 5);
		gbc_apellido2ValueTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_apellido2ValueTextField.gridx = 4;
		gbc_apellido2ValueTextField.gridy = 11;
		dataPanel.add(apellido2ValueTextField, gbc_apellido2ValueTextField);
		apellido2ValueTextField.setColumns(10);

		fechaNacimientoDateChooser = new JDateChooser();
		GridBagConstraints gbc_fechaNacimientoDateChooser = new GridBagConstraints();
		gbc_fechaNacimientoDateChooser.insets = new Insets(0, 0, 5, 0);
		gbc_fechaNacimientoDateChooser.fill = GridBagConstraints.BOTH;
		gbc_fechaNacimientoDateChooser.gridx = 11;
		gbc_fechaNacimientoDateChooser.gridy = 11;
		dataPanel.add(fechaNacimientoDateChooser, gbc_fechaNacimientoDateChooser);

		guardarButton = new JButton("Guardar");
		guardarButton.setAction(new UpdateAutorAction(view ,this, "Guardar"));
		GridBagConstraints gbc_guardarButton = new GridBagConstraints();
		gbc_guardarButton.gridx = 11;
		gbc_guardarButton.gridy = 19;
		dataPanel.add(guardarButton, gbc_guardarButton);

		JPanel buttonPanel = new JPanel();
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		GridBagLayout gbl_buttonPanel = new GridBagLayout();
		gbl_buttonPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_buttonPanel.rowHeights = new int[]{0, 0};
		gbl_buttonPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_buttonPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		buttonPanel.setLayout(gbl_buttonPanel);

		JButton editButton = new JButton("Editar");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setEditable();
			}
		});
		editButton.setIcon(new ImageIcon(AutorDetailDialog.class.getResource("/nuvola/32x32/1819_pencil_pencil.png")));
		GridBagConstraints gbc_editButton = new GridBagConstraints();
		gbc_editButton.insets = new Insets(0, 0, 0, 5);
		gbc_editButton.gridx = 33;
		gbc_editButton.gridy = 0;
		buttonPanel.add(editButton, gbc_editButton);

		JButton closeButton = new JButton("Cerrar");
		closeButton.setIcon(new ImageIcon(AutorDetailDialog.class.getResource("/nuvola/32x32/1250_delete_delete.png")));
		GridBagConstraints gbc_closeButton = new GridBagConstraints();
		gbc_closeButton.gridx = 34;
		gbc_closeButton.gridy = 0;
		buttonPanel.add(closeButton, gbc_closeButton);

		postInitialize();
	}

	private void postInitialize() {
		fechaNacimientoDateChooser.setEnabled(false);
		guardarButton.setVisible(false);
		setModel();
	}

	private void setModel() {
		nombreValueTextField.setText(autor.getNombre());
		apellido1ValueTextField.setText(autor.getApellido1());
		apellido2ValueTextField.setText(autor.getApellido2());
		fechaNacimientoDateChooser.setDate(autor.getFechaNacimiento());
	}

	private void setEditable() {
		nombreValueTextField.setEnabled(true);
		apellido1ValueTextField.setEnabled(true);
		apellido2ValueTextField.setEnabled(true);
		fechaNacimientoDateChooser.setEnabled(true);
		guardarButton.setVisible(true);
	}

	public Long getIdValue() {
		return autor.getId();
	}

	public Autor getUpdateModel() {

		autor.setNombre(nombreValueTextField.getText());
		autor.setFechaNacimiento(fechaNacimientoDateChooser.getDate());
		autor.setApellido1(apellido1ValueTextField.getText());
		autor.setApellido2(SwingUtils.getTextFieldValueOrNull(apellido2ValueTextField));


		return autor;

	}
}
