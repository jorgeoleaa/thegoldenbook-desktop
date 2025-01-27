package com.pinguela.thegoldenbook.ui.desktop.model;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.pinguela.thegoldenbook.model.LibroDTO;

public class LibroSearchTableModel extends DefaultTableModel{

	public static final String[] COLUMN_NAMES = {
			"ID", "TITULO", "ISBN", "AUTOR", "PRECIO", "UNIDADES", "ACCIONES"
	};
	
	public LibroSearchTableModel(List<LibroDTO> libros) {
		super(COLUMN_NAMES, libros.size());
		LibroDTO l = null;
		for(int row = 0; row<libros.size(); row++) {
			l = libros.get(row);
			for(int col = 0; col<COLUMN_NAMES.length; col++) {
				setValueAt(l, row, col);
			}
		}
	}
	
	
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex == 6;
	}

}
