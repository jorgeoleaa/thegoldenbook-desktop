package com.pinguela.thegoldenbook.ui.desktop.renderer;

import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.pinguela.thegoldenbook.model.Autor;

public class AutorListCellRenderer extends DefaultListCellRenderer{
	
	private Font selectedFont;
	private Font nonSelectedFont;
	
	public AutorListCellRenderer() {
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
		
		Autor autor = (Autor) value;
		setText(autor.getNombre()+" "+autor.getApellido1());
		setFont(isSelected?selectedFont:nonSelectedFont);
	
		return this;
	}
}
