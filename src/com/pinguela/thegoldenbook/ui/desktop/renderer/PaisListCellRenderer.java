package com.pinguela.thegoldenbook.ui.desktop.renderer;

import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.pinguela.thegoldenbook.model.Pais;

public class PaisListCellRenderer extends DefaultListCellRenderer{
	
	private Font selectedFont = null;
	private Font nonSelectedFont = null;
	
	public PaisListCellRenderer() {
		Font defaultFont = getFont();
		selectedFont = new Font(defaultFont.getName(), Font.BOLD, defaultFont.getSize());
		selectedFont = new Font(defaultFont.getName(), Font.PLAIN, defaultFont.getSize());
	}
	
	public Component getListCellRendererComponent(
			JList<?> list,
			Object value,
			int index,
			boolean isSelected,
			boolean hasFocus) {
		
		Pais pais = (Pais) value;
		setText(pais.getNombre());
		setFont(isSelected?selectedFont:nonSelectedFont);
		
		return this;
	}
	
}
