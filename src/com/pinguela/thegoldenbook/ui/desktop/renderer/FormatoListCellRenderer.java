package com.pinguela.thegoldenbook.ui.desktop.renderer;

import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.pinguela.thegoldenbook.model.Formato;

public class FormatoListCellRenderer extends DefaultListCellRenderer{

	private Font selectedFont = null;
	private Font nonSelectedFont = null;
	
	public FormatoListCellRenderer() {
		Font defaultFont = getFont();
		selectedFont = new Font(defaultFont.getName(), Font.BOLD, defaultFont.getSize());
		nonSelectedFont = new Font(defaultFont.getName(), Font.PLAIN, defaultFont.getSize());
	}
	
	public Component getListCellRendererComponent(
			JList<?> list,
			Object value,
			int index,
			boolean isSelected,
			boolean hasFocus) {
		
		Formato formato = (Formato) value;
		setText(formato.getNombre());
		setFont(isSelected?selectedFont:nonSelectedFont);
		
		return this;
		
	}
}
