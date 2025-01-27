package com.pinguela.thegoldenbook.ui.desktop.renderer;

import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.pinguela.thegoldenbook.model.Localidad;

public class LocalidadListCellRenderer extends DefaultListCellRenderer{

	private Font nonSelectedFont = null;
	private Font selectedFont = null;
	
	public LocalidadListCellRenderer () {
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
		
		Localidad localidad = (Localidad) value;
		setText(localidad.getNombre());
		setFont(isSelected?selectedFont:nonSelectedFont);
		
		return this;
	}
	
}
