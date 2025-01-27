package com.pinguela.thegoldenbook.ui.desktop.model;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.pinguela.thegoldenbook.model.Autor;
import com.pinguela.thegoldenbook.model.ValoracionDTO;

public class AutorTableModel extends DefaultTableModel{
	
	
	public static final String [] COLUMN_NAMES = {
			"ID", "NOMBRE", "PRIMER APELLIDO", "SEGUNDO APELLIDO", "FECHA DE NACIMIENTO", "ACCIONES"
	};
	
	public AutorTableModel(List<Autor> autores) {
		super(COLUMN_NAMES, autores.size());
		Autor autor = null;
		for(int row = 0; row<autores.size(); row++) {
			autor = autores.get(row);
			for(int col = 0; col<COLUMN_NAMES.length; col++) {
				setValueAt(autor, row, col);
			}
		}
	}
	
//	@Override
//	public void setValueAt(Object value, int row, int col) {		
//		if (col==1) {
//			Autor autor = (Autor) super.getValueAt(row, col);
//			autor.setCuerpo((String) value);
//		} else if (col==3) {
//			super.setValueAt(value, row, col);
//		} 
//	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex == 1 || columnIndex==2 || columnIndex==3 || columnIndex ==4 || columnIndex == 5;
	}
}
