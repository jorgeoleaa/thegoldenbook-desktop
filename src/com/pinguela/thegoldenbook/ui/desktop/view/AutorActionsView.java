package com.pinguela.thegoldenbook.ui.desktop.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.pinguela.thegoldenbook.model.Autor;
import com.pinguela.thegoldenbook.ui.desktop.controller.ShowAutorDetailAction;

public class AutorActionsView extends JPanel {

	private Autor model = null;
	private AutorSearchView view;
	/**
	 * Create the panel.
	 */
	public AutorActionsView(AutorSearchView view) {
		this.view = view;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton verButton = new JButton("");
		verButton.setAction(new ShowAutorDetailAction(view, this, null));
		verButton.setIcon(new ImageIcon(AutorActionsView.class.getResource("/nuvola/16x16/1339_kmag_kmag.png")));
		GridBagConstraints gbc_verButton = new GridBagConstraints();
		gbc_verButton.insets = new Insets(0, 0, 0, 5);
		gbc_verButton.gridx = 0;
		gbc_verButton.gridy = 0;
		add(verButton, gbc_verButton);

	}
	
	public AutorActionsView(Autor model) {
		setModel(model);
	}
	
	public Autor getModel() {
		return model;
	}
	
	public void setModel(Autor model) {
		this.model = model;
	}

}
