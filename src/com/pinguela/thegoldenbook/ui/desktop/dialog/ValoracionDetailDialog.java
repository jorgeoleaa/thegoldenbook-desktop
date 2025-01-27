package com.pinguela.thegoldenbook.ui.desktop.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.pinguela.thegoldenbook.model.ValoracionDTO;
import com.pinguela.thegoldenbook.ui.desktop.view.View;

public class ValoracionDetailDialog extends View {
		
	private  JPanel dataPanel = null;
	private JLabel idValueLabel;
	private JLabel nicknameValueLabel;
	private JLabel nombreApellidosLabel;
	private JLabel fechaPublicacionLabel;
	private JLabel asuntoLabel;
	private JTextArea cuerpoTextArea;
	private JProgressBar valoracionProgressBar;
	
	private ValoracionDTO valoracion = null;
	/**
	 * Create the dialog.
	 */
	public ValoracionDetailDialog(ValoracionDTO valoracion) {
		
		
		dataPanel =  new JPanel();
		setBounds(100, 100, 719, 496);
		setLayout(new BorderLayout());
		dataPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(dataPanel, BorderLayout.CENTER);
		GridBagLayout gbl_dataPanel = new GridBagLayout();
		gbl_dataPanel.columnWidths = new int[]{0, 0, 0, 325, 172, 0, 0};
		gbl_dataPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_dataPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0};
		gbl_dataPanel.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		dataPanel.setLayout(gbl_dataPanel);
		{
			idValueLabel = new JLabel();
			GridBagConstraints gbc_idValueLabel = new GridBagConstraints();
			gbc_idValueLabel.insets = new Insets(0, 0, 5, 5);
			gbc_idValueLabel.gridx = 3;
			gbc_idValueLabel.gridy = 2;
			dataPanel.add(idValueLabel, gbc_idValueLabel);
		}
		{
			nicknameValueLabel = new JLabel("");
			GridBagConstraints gbc_nicknameValueLabel = new GridBagConstraints();
			gbc_nicknameValueLabel.fill = GridBagConstraints.HORIZONTAL;
			gbc_nicknameValueLabel.insets = new Insets(0, 0, 5, 5);
			gbc_nicknameValueLabel.gridx = 4;
			gbc_nicknameValueLabel.gridy = 2;
			dataPanel.add(nicknameValueLabel, gbc_nicknameValueLabel);
		}
		{
			nombreApellidosLabel = new JLabel("");
			GridBagConstraints gbc_nombreApellidosLabel = new GridBagConstraints();
			gbc_nombreApellidosLabel.insets = new Insets(0, 0, 5, 5);
			gbc_nombreApellidosLabel.gridx = 3;
			gbc_nombreApellidosLabel.gridy = 3;
			dataPanel.add(nombreApellidosLabel, gbc_nombreApellidosLabel);
		}
		{
			fechaPublicacionLabel = new JLabel("");
			GridBagConstraints gbc_fechaPublicacionLabel = new GridBagConstraints();
			gbc_fechaPublicacionLabel.fill = GridBagConstraints.HORIZONTAL;
			gbc_fechaPublicacionLabel.insets = new Insets(0, 0, 5, 5);
			gbc_fechaPublicacionLabel.gridx = 4;
			gbc_fechaPublicacionLabel.gridy = 3;
			dataPanel.add(fechaPublicacionLabel, gbc_fechaPublicacionLabel);
		}
		{
			valoracionProgressBar = new JProgressBar();
			GridBagConstraints gbc_valoracionProgressBar = new GridBagConstraints();
			gbc_valoracionProgressBar.insets = new Insets(0, 0, 5, 5);
			gbc_valoracionProgressBar.gridx = 3;
			gbc_valoracionProgressBar.gridy = 4;
			dataPanel.add(valoracionProgressBar, gbc_valoracionProgressBar);
		}
		{
			asuntoLabel = new JLabel("");
			GridBagConstraints gbc_asuntoLabel = new GridBagConstraints();
			gbc_asuntoLabel.insets = new Insets(0, 0, 5, 5);
			gbc_asuntoLabel.gridx = 3;
			gbc_asuntoLabel.gridy = 5;
			dataPanel.add(asuntoLabel, gbc_asuntoLabel);
		}
		{
			cuerpoTextArea = new JTextArea();
			GridBagConstraints gbc_cuerpoTextArea = new GridBagConstraints();
			gbc_cuerpoTextArea.insets = new Insets(0, 0, 5, 5);
			gbc_cuerpoTextArea.fill = GridBagConstraints.VERTICAL;
			gbc_cuerpoTextArea.gridx = 3;
			gbc_cuerpoTextArea.gridy = 6;
			dataPanel.add(cuerpoTextArea, gbc_cuerpoTextArea);
		}
		
		postIntialize();
		
		setModel(valoracion);
	}

	private void postIntialize() {
		valoracionProgressBar.setMaximum(5);
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		cuerpoTextArea.setBorder(border);
	}

	public void setModel(ValoracionDTO valoracion) {
		this.valoracion = valoracion;
		updateView();
	}

	public void updateView() {
		nombreApellidosLabel.setText(valoracion.getNombreCliente()+" "+valoracion.getApellido1Cliente()+" "+valoracion.getApellido2Cliente());
		idValueLabel.setText(valoracion.getLibroId().toString());
		valoracionProgressBar.setValue(valoracion.getNumeroEstrellas().intValue());
		asuntoLabel.setText(valoracion.getAsunto());
		cuerpoTextArea.setText(valoracion.getCuerpo());
		nicknameValueLabel.setText(valoracion.getNickname());
		fechaPublicacionLabel.setText(valoracion.getFechaPublicacion().toString());
	}
}
