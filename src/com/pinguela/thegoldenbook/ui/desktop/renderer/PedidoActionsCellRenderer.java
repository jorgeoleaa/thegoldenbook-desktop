package com.pinguela.thegoldenbook.ui.desktop.renderer;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import com.pinguela.thegoldenbook.model.Pedido;
import com.pinguela.thegoldenbook.ui.desktop.view.PedidoActionsView;
import com.pinguela.thegoldenbook.ui.desktop.view.PedidoSearchView;

public class PedidoActionsCellRenderer implements TableCellRenderer{
	
	private PedidoSearchView pedidoSearchView;
	
	public PedidoActionsCellRenderer(PedidoSearchView searchView) {
		setView(searchView);
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		
		Pedido pedido = (Pedido) value;
		PedidoActionsView view = new PedidoActionsView(pedidoSearchView);
		view.setModel(pedido);
		
		return view;
		
	}
	
	private void setView(PedidoSearchView view) {
		this.pedidoSearchView = view;
	}

}
