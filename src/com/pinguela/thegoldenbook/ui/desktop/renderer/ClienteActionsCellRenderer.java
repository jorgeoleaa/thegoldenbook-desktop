package com.pinguela.thegoldenbook.ui.desktop.renderer;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import com.pinguela.thegoldenbook.model.ClienteDTO;
import com.pinguela.thegoldenbook.ui.desktop.view.ClienteActionsView;
import com.pinguela.thegoldenbook.ui.desktop.view.ClienteSearchView;

public class ClienteActionsCellRenderer implements TableCellRenderer{

	private ClienteSearchView clienteSearchView = null;
	
	public ClienteActionsCellRenderer() {
		
	}
	
	public ClienteActionsCellRenderer(ClienteSearchView view) {
		setView(view);
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		
		ClienteDTO cliente = (ClienteDTO) value;
		ClienteActionsView view = new ClienteActionsView(clienteSearchView);
		view.setModel(cliente);
		return view;
	}
	
	private void setView(ClienteSearchView view) {
		this.clienteSearchView = view;
	}

}
