package com.pinguela.thegoldenbook.ui.desktop.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import com.pinguela.thegoldenbook.model.LibroDTO;

public class LibroSelectionActionsView extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private LibroDTO model = null;
	
	/**
	 * Create the panel.
	 */
	public LibroSelectionActionsView() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JCheckBox libroCheckBox = new JCheckBox("");
		GridBagConstraints gbc_libroCheckBox = new GridBagConstraints();
		gbc_libroCheckBox.gridx = 0;
		gbc_libroCheckBox.gridy = 0;
		add(libroCheckBox, gbc_libroCheckBox);

	}
	
	public LibroSelectionActionsView(LibroDTO libro) {
		setModel(model);
	}
	
	public LibroDTO getModel() {
		return model;
	}
	
	public void setModel(LibroDTO libro) {
		this.model = libro;
	}

}
