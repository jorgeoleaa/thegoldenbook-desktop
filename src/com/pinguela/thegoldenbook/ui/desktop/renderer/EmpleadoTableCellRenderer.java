package com.pinguela.thegoldenbook.ui.desktop.renderer;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.pinguela.thegoldenbook.model.EmpleadoDTO;

public class EmpleadoTableCellRenderer extends DefaultTableCellRenderer{

	public EmpleadoTableCellRenderer() {
		
	}
	
	public Component getTableCellRendererComponent(
			JTable table,
			Object value,
			boolean isSelected,
			boolean hasFocus,
			int row,
			int column) {
		
		Component c = this;
		EmpleadoDTO empleado = (EmpleadoDTO) value;
		
		if(column == 0) {
			setText(empleado.getId().toString());
		}else if(column == 1) {
			setText(empleado.getNombre());
		}else if (column == 2) {
			setText(empleado.getApellido1());
		}else if (column == 4) {
			setText(empleado.getApellido2());
		}else if (column == 5) {
			setText(empleado.getDniNie());
		}else if (column == 6) {
			setText(empleado.getTelefono());
		}else if (column == 7) {
			setText(empleado.getEmail());
		}
		return c;
	}
	
	
}
