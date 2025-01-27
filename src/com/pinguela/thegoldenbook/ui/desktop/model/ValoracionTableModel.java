package com.pinguela.thegoldenbook.ui.desktop.model;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.pinguela.thegoldenbook.model.ValoracionDTO;

public class ValoracionTableModel extends DefaultTableModel{

	public static final String[] COLUMN_NAMES = {
			"CLIENTE ID","NICKNAME","VALORACIÓN MEDIA", "ASUNTO", "CUERPO", "FECHA DE PUBLICACIÓN", "ACCIONES" 
	};

	public ValoracionTableModel(List<ValoracionDTO> valoracion) {
		super(COLUMN_NAMES, valoracion.size());
		ValoracionDTO v = null;
		for(int row = 0; row<valoracion.size(); row++) {
			v = valoracion.get(row);
			for(int col = 0; col<COLUMN_NAMES.length; col++) {
				super.setValueAt(v, row, col);
			}
		}
	}

	@Override
	public void setValueAt(Object value, int row, int col) {		
		if (col==1) {
			ValoracionDTO valoracion = (ValoracionDTO) super.getValueAt(row, col);
			valoracion.setCuerpo((String) value);
		} else if (col==3) {
			super.setValueAt(value, row, col);
		} 
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex == 1 || columnIndex==2 || columnIndex==3 || columnIndex ==4 || columnIndex == 5 || columnIndex == 6;
	}

}
