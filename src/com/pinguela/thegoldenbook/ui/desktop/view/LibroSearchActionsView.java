package com.pinguela.thegoldenbook.ui.desktop.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.pinguela.thegoldenbook.model.ClienteDTO;
import com.pinguela.thegoldenbook.model.LibroDTO;
import com.pinguela.thegoldenbook.ui.desktop.controller.ShowLibroDetailAction;

public class LibroSearchActionsView extends JPanel {
	
	private JButton verButton;
	
	private LibroDTO model = null;
	/**
	 * Create the panel.
	 */
	public LibroSearchActionsView(LibroSearchView view) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		verButton = new JButton();
		verButton.setAction(new ShowLibroDetailAction(view, this, "Ver"));
		GridBagConstraints gbc_verButton = new GridBagConstraints();
		gbc_verButton.gridx = 0;
		gbc_verButton.gridy = 0;
		add(verButton, gbc_verButton);

	}
	
	public LibroSearchActionsView(LibroDTO libro) {
		setModel(libro);
	}
	
	public LibroDTO getModel() {
		return model;
	}
	
	public void setModel(LibroDTO libro) {
		this.model = libro;
	}

}
