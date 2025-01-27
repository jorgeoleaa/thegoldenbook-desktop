package com.pinguela.thegoldenbook.ui.desktop.renderer;

import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.pinguela.thegoldenbook.model.GeneroLiterario;

public class GeneroListCellRenderer extends DefaultListCellRenderer{
	
	private Font selectedFont;
	private Font nonSelectedFont;
	
	public GeneroListCellRenderer() {
		Font defaultFont = getFont();
		selectedFont = new Font(defaultFont.getName(), Font.BOLD, defaultFont.getSize());
		nonSelectedFont = new Font(defaultFont.getName(), Font.PLAIN, defaultFont.getSize());
	}
	
	@Override
	public Component getListCellRendererComponent(
			JList<?> list,
			Object value,
			int index,
			boolean isSelected,
			boolean cellHashFocus) {
		
		GeneroLiterario genero = (GeneroLiterario) value;
		if (genero != null) {
			setText(genero.getNombre());
			setFont(isSelected ? selectedFont : nonSelectedFont);
		}
		return this;
	}
}
