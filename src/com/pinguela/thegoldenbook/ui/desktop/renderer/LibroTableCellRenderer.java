package com.pinguela.thegoldenbook.ui.desktop.renderer;

import java.awt.Component;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.pinguela.thegoldenbook.model.Autor;
import com.pinguela.thegoldenbook.model.LibroDTO;

public class LibroTableCellRenderer extends DefaultTableCellRenderer{
	
	public LibroTableCellRenderer() {
		
	}
	
	@Override
	public Component getTableCellRendererComponent(
			JTable table,
			Object value,
			boolean isSelected,
			boolean hasFocus,
			int row,
			int column) {
		
		Component c = this;
		LibroDTO libro = (LibroDTO) value;
		
		if (libro == null) {
			return c;
		}
		if(column==0) {
			setText(libro.getId().toString());
		}else if (column == 1){
			setText(libro.getNombre());
		}else if (column ==2) {
			setText(libro.getIsbn());
		}else if (column == 3) {
			List<Autor> autores = libro.getAutores();
			for (Autor a : autores) {
				setText(a.getNombre()+" "+a.getApellido1());
			}
		}else if(column == 4) {
			setText(libro.getPrecio().toString()+"€");
		}else if(column == 5) {
			setText(libro.getUnidades().toString());
		} else {
			setText("?");
		}
		
		return c;
	}

}
