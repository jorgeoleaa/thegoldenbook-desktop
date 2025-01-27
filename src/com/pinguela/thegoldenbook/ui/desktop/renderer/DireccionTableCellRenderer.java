package com.pinguela.thegoldenbook.ui.desktop.renderer;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.pinguela.thegoldenbook.model.DireccionDTO;

public class DireccionTableCellRenderer extends DefaultTableCellRenderer{
	
	public DireccionTableCellRenderer() {
		
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		Component c = this;
		DireccionDTO direccion = (DireccionDTO) value;
		
		if(column == 0) {
			setText(direccion.getNombreVia());
		}else if(column == 1) {
			setText(direccion.getDirVia());
		}else if(column == 2) {
			setText(direccion.getLocalidadNombre());
		}else if(column == 3) {
			setText(direccion.getProvinciaNombre());
		}else if(column == 4) {
			setText(direccion.getPaisNombre());
		}
		
		return c;
	}

}
