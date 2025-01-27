package com.pinguela.thegoldenbook.ui.desktop.renderer;

import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.pinguela.thegoldenbook.model.Idioma;

public class IdiomaListCellRenderer extends DefaultListCellRenderer{
	
	private Font selectedFont = null;
	private Font nonSelectedFont = null;
	
	public IdiomaListCellRenderer() {
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
		        boolean cellHasFocus) {
		Idioma idioma = (Idioma) value;
		if (idioma != null) {
			setText(idioma.getNombre());
			setFont(isSelected ? selectedFont : nonSelectedFont);
		}
		return this;
	 }

}
