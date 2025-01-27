package com.pinguela.thegoldenbook.ui.desktop.renderer;

import java.awt.Component;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.pinguela.thegoldenbook.model.Autor;
import com.pinguela.thegoldenbook.model.LibroDTO;

public class LineaPedidoLibroTableCellRenderer extends DefaultTableCellRenderer{


	public Component getTableCellRendererComponent(
			JTable table,
			Object value,
			boolean isSelected,
			boolean hasFocus,
			int row,
			int column) {

		Component c = this;
		LibroDTO libro = (LibroDTO) value;

		if(column == 0) {
			setText(libro.getId().toString());
		}else if(column == 1) {
			setText(libro.getNombre());
		}else if(column == 2) {
			List<Autor> autores = libro.getAutores();
			for (Autor a : autores) {
				setText(a.getNombre()+" "+a.getApellido1());
			}
		}else if(column ==3) {
			setText(libro.getPrecio().toString());
		}
		return c;

	}

}


