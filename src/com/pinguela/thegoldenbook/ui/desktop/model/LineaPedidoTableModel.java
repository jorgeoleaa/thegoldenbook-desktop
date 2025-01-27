package com.pinguela.thegoldenbook.ui.desktop.model;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.pinguela.thegoldenbook.model.LineaPedido;

public class LineaPedidoTableModel extends DefaultTableModel{

	public static final String [] COLUMN_NAMES = {
			"ID LINEA", "PRECIO","ID LIBRO", "TÍTULO", "AUTOR", "ID PEDIDO"
	};
	
	public LineaPedidoTableModel(List<LineaPedido> lineas) {
		super(COLUMN_NAMES, lineas.size());
		LineaPedido linea= null;
		for(int row = 0; row<lineas.size(); row++) {
			linea = lineas.get(row);
			for(int col = 0; col<COLUMN_NAMES.length; col++) {
				setValueAt(linea, row, col);
			}
		}
	}
}
