package com.pinguela.thegoldenbook.ui.desktop.renderer;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import com.pinguela.thegoldenbook.model.Pedido;
import com.pinguela.thegoldenbook.ui.desktop.view.PedidoActionsView;
import com.pinguela.thegoldenbook.ui.desktop.view.PedidoSearchView;

public class PedidoActionsCellEditor extends AbstractCellEditor implements TableCellEditor{

	private Pedido pedido = null;
	private PedidoSearchView searchView;
	
	public PedidoActionsCellEditor(PedidoSearchView searchView) {
		setView(searchView);
	}
	
	@Override
	public Object getCellEditorValue() {
		
		return pedido;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		
		this.pedido = (Pedido) value;
		PedidoActionsView view = new PedidoActionsView(searchView);
		view.setModel(pedido);
		
		return view;
	}
	
	private void setView(PedidoSearchView searchView) {
		this.searchView = searchView;
	}

}
