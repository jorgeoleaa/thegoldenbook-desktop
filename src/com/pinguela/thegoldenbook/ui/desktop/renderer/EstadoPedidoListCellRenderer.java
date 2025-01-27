package com.pinguela.thegoldenbook.ui.desktop.renderer;

import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.pinguela.thegoldenbook.model.EstadoPedido;

public class EstadoPedidoListCellRenderer extends DefaultListCellRenderer{
	
	private Font selectedFont;
	private Font nonSelectedFont;
	
	public EstadoPedidoListCellRenderer() {
		Font defaultFont = getFont();
		selectedFont = new Font(defaultFont.getName(), Font.BOLD, defaultFont.getSize());
		nonSelectedFont = new Font(defaultFont.getName(), Font.PLAIN, defaultFont.getSize());
	}
	
	public Component getListCellRendererComponent(
			JList<?> list,
			Object value,
			int index,
			boolean isSelected,
			boolean cellHasFocus) {
		
		EstadoPedido estadoPedido = (EstadoPedido) value;
		setText(estadoPedido.getNombre());
		setFont(isSelected?selectedFont:nonSelectedFont);
		
		return this;
	}
}
