package com.pinguela.thegoldenbook.ui.desktop.renderer;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.pinguela.thegoldenbook.model.Autor;

public class AutorTableCellRenderer extends DefaultTableCellRenderer{

	public AutorTableCellRenderer() {
		
	}
	
	public Component getTableCellRendererComponent(
			JTable table,
			Object value,
			boolean isSelected,
			boolean hasFocus,
			int row,
			int column) {
		
		Component c = this;
		Autor autor = (Autor) value;
		
		if(column == 0) {
			setText(String.valueOf(autor.getId()));
		}else if(column == 1 ) {
			setText(autor.getNombre());
		}else if(column == 2) {
			setText(autor.getApellido1());
		}else if(column == 3) {
			setText(autor.getApellido2());
		}else if(column == 4) {
			setText(String.valueOf(autor.getFechaNacimiento()));
		}
		return c;
	}
}
