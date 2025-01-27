package com.pinguela.thegoldenbook.ui.desktop.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.pinguela.thegoldenbook.model.Pedido;
import com.pinguela.thegoldenbook.ui.desktop.controller.DeletePedidoAction;
import com.pinguela.thegoldenbook.ui.desktop.controller.ShowPedidoDetailAction;
import java.awt.Insets;

public class PedidoActionsView extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Pedido model = null;
	private JButton deleteButton;
	
	/**
	 * Create the panel.
	 * @wbp.parser.constructor
	 */
	public PedidoActionsView(PedidoSearchView view) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton verButton = new JButton();
		verButton.setAction(new ShowPedidoDetailAction(view, this, "Ver"));
		GridBagConstraints gbc_verButton = new GridBagConstraints();
		gbc_verButton.insets = new Insets(0, 0, 0, 5);
		gbc_verButton.gridx = 0;
		gbc_verButton.gridy = 0;
		add(verButton, gbc_verButton);
		
		deleteButton = new JButton("Eliminar");
		deleteButton.setAction(new DeletePedidoAction(view, this, "Eliminar"));
		GridBagConstraints gbc_deleteButton = new GridBagConstraints();
		gbc_deleteButton.gridx = 1;
		gbc_deleteButton.gridy = 0;
		add(deleteButton, gbc_deleteButton);

	}
	
	public PedidoActionsView(Pedido pedido) {
		setModel(pedido);
	}
	
	public Pedido getModel() {
		return model;
	}
	
	public void setModel(Pedido pedido) {
		this.model = pedido;
	}

}
