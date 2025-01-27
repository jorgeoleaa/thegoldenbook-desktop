package com.pinguela.thegoldenbook.ui.desktop.renderer;

import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.pinguela.thegoldenbook.model.Tematica;

public class TematicaListCellRenderer extends DefaultListCellRenderer{
	
	private Font nonSelectedFont;
	private Font selectedFont;
	
	
	public TematicaListCellRenderer() {
		Font defaultFont = getFont();
		nonSelectedFont = new Font(defaultFont.getName(), Font.PLAIN, defaultFont.getSize());
		selectedFont = new Font(defaultFont.getName(), Font.BOLD, defaultFont.getSize());
	}
	
	@Override
	public Component getListCellRendererComponent(
			JList<?> list,
			Object value,
			int index,
			boolean isSelected,
			boolean cellHasFocus) {
		
		Tematica tematica = (Tematica) value;
		setText(tematica.getNombre());
		setFont(isSelected?selectedFont:nonSelectedFont);
		
		return this;
	}
}
