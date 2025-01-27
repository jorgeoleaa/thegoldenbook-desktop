package com.pinguela.thegoldenbook.ui.desktop.renderer;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import com.pinguela.thegoldenbook.model.ClienteDTO;
import com.pinguela.thegoldenbook.ui.desktop.view.ClienteActionsView;
import com.pinguela.thegoldenbook.ui.desktop.view.ClienteSearchView;
import com.pinguela.thegoldenbook.ui.desktop.view.ClienteSelectionView;

public class ClienteActionsCellEditor extends AbstractCellEditor implements TableCellEditor{
	
	private ClienteSearchView clienteSelectionView = null;
	private ClienteDTO cliente = null;
	
	public ClienteActionsCellEditor() {
		
	}
	
	public ClienteActionsCellEditor(ClienteSearchView view) {
		setView(view);
	}
	
	@Override
	public Object getCellEditorValue() {
		return cliente;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		
		this.cliente = (ClienteDTO) value;
		ClienteActionsView view = new ClienteActionsView(clienteSelectionView);
		view.setModel(cliente);
		return view;
	}
	
	private void setView(ClienteSearchView view) {
		this.clienteSelectionView = view;
	}

}
