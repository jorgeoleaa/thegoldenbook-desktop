package com.pinguela.thegoldenbook.ui.desktop.renderer;

import java.awt.Component;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.pinguela.thegoldenbook.model.Autor;
import com.pinguela.thegoldenbook.model.ClienteDTO;

public class ClienteTableCellRenderer extends DefaultTableCellRenderer{
	
	public ClienteTableCellRenderer() {
		
	}
	
	@Override
	public Component getTableCellRendererComponent(
			JTable table,
			Object value,
			boolean isSelected,
			boolean hasFocus,
			int row,
			int column) {
		
		Component c = this;
		ClienteDTO cliente = (ClienteDTO) value;
		if(column==0) {
			setText(String.valueOf(cliente.getId()));
		}else if (column == 1){
			setText(cliente.getNickname());
		}else if (column ==2) {
			setText(cliente.getNombre());
		}else if (column == 3) {
			setText(cliente.getApellido1());
		}else if(column == 4) {
			setText(cliente.getApellido2());
		}else if (column == 5) {
			setText(cliente.getDniNie());
		}else if (column == 6) {
			setText(cliente.getEmail());
		}else if (column == 7) {
			setText(cliente.getTelefono());
		}else if(column == 8){
			setText("?");
		}
		
		return c;
	}
	
	
	
	
	
}
