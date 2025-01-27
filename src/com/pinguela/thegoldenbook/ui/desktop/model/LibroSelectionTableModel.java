package com.pinguela.thegoldenbook.ui.desktop.model;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.pinguela.thegoldenbook.model.LibroDTO;

public class LibroSelectionTableModel extends DefaultTableModel{

	public static final String[] COLUMN_NAMES = {
			"ID", "TITULO", "ISBN", "AUTOR", "PRECIO", "UNIDADES", "SELECCIÓN"
	};
	
	public LibroSelectionTableModel(List<LibroDTO> libros) {
		super(COLUMN_NAMES, libros.size());
		LibroDTO l = null;
		for(int row = 0; row<libros.size(); row++) {
			l = libros.get(row);
			for(int col = 0; col<COLUMN_NAMES.length; col++) {
				if(col != 6) {
					setValueAt(l, row, col);
				}else {
					setValueAt(Boolean.FALSE, row, col);
				}
			}
			
		}
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if (columnIndex == 6) {
			return Boolean.class;
		}
		return super.getColumnClass(columnIndex);
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex == 6;
	}

}
