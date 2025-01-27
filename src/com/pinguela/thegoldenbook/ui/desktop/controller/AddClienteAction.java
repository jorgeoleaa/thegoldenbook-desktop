package com.pinguela.thegoldenbook.ui.desktop.controller;

import java.awt.Window;
import java.util.List;

import javax.swing.Icon;

import com.pinguela.thegoldenbook.model.ClienteDTO;
import com.pinguela.thegoldenbook.ui.desktop.dialog.CreatePedidoDialog;
import com.pinguela.thegoldenbook.ui.desktop.view.ClienteSelectionView;

public class AddClienteAction extends BaseAction{
	
	private ClienteSelectionView view;
	private ClienteDTO cliente = null;
	private CreatePedidoDialog dialog;
	
	public AddClienteAction(CreatePedidoDialog dialog, ClienteSelectionView view, ClienteDTO cliente, String text) {
		super(text);
		this.view = view;
		this.dialog = dialog;
		this.cliente = cliente;
	}
	
	public AddClienteAction(CreatePedidoDialog dialog ,ClienteSelectionView view, ClienteDTO cliente, String text, Icon icon) {
		super(text, icon);
		this.cliente = cliente;
		setView(view);
	}
	
	@Override
	public void doAction() {
		
		List<ClienteDTO> selectedClientes = view.getSelectedClientes();
		dialog.setClienteFields(selectedClientes.get(0));
		dialog.repaint();
		dialog.revalidate();
		
		 Window window = view.getTopLevelWindow();
	        if (window != null) {
	            window.dispose();
	        }
	}

	private void setView(ClienteSelectionView view) {
		this.view = view;
	}
	
}
