package com.pinguela.thegoldenbook.ui.desktop.renderer;

import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.pinguela.thegoldenbook.model.Provincia;

public class ProvinciaListCellRenderer extends DefaultListCellRenderer{
	
	Font selectedFont = null;
	Font nonSelectedFont = null;
	
	public ProvinciaListCellRenderer() {
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
		
		Provincia provincia = (Provincia) value;
		setText(provincia.getNombre());
		setFont(isSelected?selectedFont:nonSelectedFont);
		
		return this;
		
	}
}
