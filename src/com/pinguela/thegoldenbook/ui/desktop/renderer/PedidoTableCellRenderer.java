package com.pinguela.thegoldenbook.ui.desktop.renderer;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.pinguela.thegoldenbook.model.Pedido;

public class PedidoTableCellRenderer extends DefaultTableCellRenderer{
	
	public PedidoTableCellRenderer() {
		
	}
	
	@Override
	public Component getTableCellRendererComponent(
			JTable table,
			Object value,
			boolean isSelected,
			boolean hasFocus,
			int row,
			int column) {
		
		Component c = this;
		Pedido p = (Pedido) value;
		
		if(column == 0) {
			setText(p.getId().toString());
		}else if (column == 1) {
			setText(p.getFechaRealizacion().toString());
		}else if(column == 2) {
			setText(p.getPrecio().toString());
		}else if(column == 3) {
			setText(p.getClienteId().toString());
		}else if(column == 4) {
			setText(p.getNickname());
		}else if (column == 5) {
			setText(p.getTipoEstadoPedidoNombre());
		}else {
			setText("?");
		}
		return c;
	}
	
}
