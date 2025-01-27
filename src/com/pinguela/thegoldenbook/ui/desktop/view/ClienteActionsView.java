package com.pinguela.thegoldenbook.ui.desktop.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.pinguela.thegoldenbook.model.ClienteDTO;
import com.pinguela.thegoldenbook.ui.desktop.controller.DeleteClienteAction;
import com.pinguela.thegoldenbook.ui.desktop.controller.ShowClienteDetailAction;

public class ClienteActionsView extends JPanel {

	private ClienteDTO model = null;
	private JButton deleteButton;

	/**
	 * Create the panel.
	 */
	public ClienteActionsView(ClienteSearchView view) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton seeButton = new JButton();
		seeButton.setAction(new ShowClienteDetailAction(this, "Ver"));
		GridBagConstraints gbc_seeButton = new GridBagConstraints();
		gbc_seeButton.insets = new Insets(0, 0, 0, 5);
		gbc_seeButton.gridx = 0;
		gbc_seeButton.gridy = 0;
		add(seeButton, gbc_seeButton);
		
		deleteButton = new JButton();
		deleteButton.setAction(new DeleteClienteAction(this, view, "Eliminar"));
		GridBagConstraints gbc_deleteButton = new GridBagConstraints();
		gbc_deleteButton.gridx = 1;
		gbc_deleteButton.gridy = 0;
		add(deleteButton, gbc_deleteButton);

	}
	
	public ClienteActionsView(ClienteDTO cliente) {
		setModel(cliente);
	}
	
	public ClienteDTO getModel() {
		return model;
	}
	
	public void setModel(ClienteDTO cliente) {
		this.model = cliente;
	}

}
