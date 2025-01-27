package com.pinguela.thegoldenbook.ui.desktop.renderer;

import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.pinguela.thegoldenbook.model.ClasificacionEdad;

public class ClasificacionEdadListCellRenderer extends DefaultListCellRenderer{
	
	private Font selectedFont = null;
	private Font nonSelectedFont = null;
	
	public ClasificacionEdadListCellRenderer() {
		Font defaultFont = getFont();
		selectedFont = new Font(defaultFont.getName(),Font.BOLD, defaultFont.getSize());
		nonSelectedFont = new Font(defaultFont.getName(), Font.PLAIN, defaultFont.getSize());
	}
	
	@Override
	public Component getListCellRendererComponent(
			JList<?> list,
			Object value,
			int index,
			boolean isSelected,
			boolean cellHasFocus) {
		
		ClasificacionEdad clasificacionEdad = (ClasificacionEdad) value;
		if (clasificacionEdad != null) {
			setText(clasificacionEdad.getNombre());
			setFont(isSelected ? selectedFont : nonSelectedFont);
		}
		return this;
	}
}
