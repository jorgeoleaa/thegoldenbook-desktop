package com.pinguela.thegoldenbook.ui.desktop.model;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.pinguela.thegoldenbook.model.DireccionDTO;

public class DireccionTableModel extends DefaultTableModel{
	
	public static final String [] COLUMN_NAMES = {
			"ID", "NOMBRE CALLE", "NÚMERO, PISO, PUERTA", "LOCALIDAD", "PROVINCIA", "PAÍS", "ACCIONES"
	};
	
	public DireccionTableModel(List<DireccionDTO> direcciones) {
		super(COLUMN_NAMES, direcciones.size());
		DireccionDTO direccion = null;
		for(int row = 0; row<direcciones.size(); row++) {
			direccion = direcciones.get(row);
			for(int col = 0; col<COLUMN_NAMES.length; col++) {
				setValueAt(direccion, row, col);
			}
		}
	}
}
