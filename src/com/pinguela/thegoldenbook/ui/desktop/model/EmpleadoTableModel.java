package com.pinguela.thegoldenbook.ui.desktop.model;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.pinguela.thegoldenbook.model.EmpleadoDTO;

public class EmpleadoTableModel extends DefaultTableModel{

	public static final String [] COLUMN_NAMES = {
			"ID", "NOMBRE", "APELLIDO 1", "APELLIDO2", "DNI/NIE", "CORREO ELECTRÓNICO", "TELÉFONO", "ACCIONES"
	};
	
	public EmpleadoTableModel(List<EmpleadoDTO> empleados) {
		super(COLUMN_NAMES, empleados.size());
		EmpleadoDTO empleado = null;
		for(int row = 0; row<empleados.size(); row++) {
			empleado = empleados.get(row);
			for(int col = 0; col<COLUMN_NAMES.length; col++) {
				setValueAt(empleado, row, col);
			}
		}
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex == 1 || columnIndex==2 || columnIndex==3 || columnIndex ==4 || columnIndex == 5 || columnIndex == 6 || columnIndex == 7; 
	}
	
}
