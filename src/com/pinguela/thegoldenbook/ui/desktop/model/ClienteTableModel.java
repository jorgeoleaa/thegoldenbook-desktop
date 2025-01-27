package com.pinguela.thegoldenbook.ui.desktop.model;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.pinguela.thegoldenbook.model.ClienteDTO;

public class ClienteTableModel extends DefaultTableModel{
	
	public static final String [] COLUMN_NAMES = {
			"ID", "NICKNAME", "NOMBRE", "APELLIDO 1", "APELLIDO 2", "DNI/NIE", "CORREO ELECTRÓNICO", "TELÉFONO", "ACCIONES"
	};
	
	public ClienteTableModel(List<ClienteDTO> clientes) {
		super(COLUMN_NAMES, clientes.size());
		ClienteDTO cliente = null;
		for(int row = 0; row<clientes.size(); row++) {
			cliente = clientes.get(row);
			for(int col = 0; col<COLUMN_NAMES.length; col++) {
				setValueAt(cliente, row, col);
			}
		}
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex == 8;
	}

}
