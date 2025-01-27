package com.pinguela.thegoldenbook.ui.desktop.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.pinguela.thegoldenbook.model.LibroDTO;

public class LineaPedidoLibroTableModel  extends DefaultTableModel{

	private List<LibroDTO> libros = new ArrayList<LibroDTO>();
	
	public static final String [] COLUMN_NAMES = {
			"ID", "Nombre", "Autor", "Precio"
	};

	public LineaPedidoLibroTableModel(List<LibroDTO> lineas) {
		super(COLUMN_NAMES, lineas.size());
		LibroDTO libro = null;
		for(int row = 0; row<lineas.size(); row++) {
			libro = lineas.get(row);
			for(int col = 0; col<COLUMN_NAMES.length; col++) {
				libros.add(libro);
				setValueAt(libro, row, col);
			}
		}
	}
	
	public List<LibroDTO> getLibros(){
		return this.libros;
	}
}


