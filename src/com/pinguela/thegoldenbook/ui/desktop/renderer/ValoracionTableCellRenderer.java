package com.pinguela.thegoldenbook.ui.desktop.renderer;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.pinguela.thegoldenbook.model.ValoracionDTO;

public class ValoracionTableCellRenderer extends DefaultTableCellRenderer{
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	public ValoracionTableCellRenderer() {
		
	}
	
	public Component getTableCellRendererComponent(
			JTable table, 
			Object value,
			boolean isSelected,
			boolean hasFocus,
			int row,
			int column) {
		
		Component c = this;
		ValoracionDTO valoracion = (ValoracionDTO) value;
		
	     if(column == 0) {
	            setText(valoracion.getClienteId().toString());
	        } else if (column == 1) {
	            setText(valoracion.getNickname());
	        } else if (column == 2) {
	            setText(valoracion.getNumeroEstrellas().toString());
	        } else if (column == 3) {
	            setText(valoracion.getAsunto());
	        } else if (column == 4) {
	            setText(valoracion.getCuerpo());
	        } else if (column == 5) {
	            Date fechaPublicacion = valoracion.getFechaPublicacion();
	            setText(dateFormat.format(fechaPublicacion));
	        }

	        return c;
	    }

}
