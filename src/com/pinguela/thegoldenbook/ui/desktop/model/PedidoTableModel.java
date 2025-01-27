package com.pinguela.thegoldenbook.ui.desktop.model;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.pinguela.thegoldenbook.model.Pedido;


public class PedidoTableModel extends DefaultTableModel{
	
	public static final String[] COLUMN_NAMES = {
				"ID", "FECHA REALIZACIÓN", "PRECIO", "CLIENTE ID",
				"NICKNAME", "ESTADO", "ACCIONES" 
	};
	
	public PedidoTableModel (List<Pedido> pedidos) {
		super(COLUMN_NAMES, pedidos.size());
		Pedido p = null;
		for(int row = 0; row<pedidos.size(); row++) {
			p = pedidos.get(row);
			for(int col = 0; col<COLUMN_NAMES.length; col++) {
				setValueAt(p, row, col);
			}
		}
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex == 6;
	}
}
