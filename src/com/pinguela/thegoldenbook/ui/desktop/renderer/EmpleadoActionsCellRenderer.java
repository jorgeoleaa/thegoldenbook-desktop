package com.pinguela.thegoldenbook.ui.desktop.renderer;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import com.pinguela.thegoldenbook.model.EmpleadoDTO;
import com.pinguela.thegoldenbook.ui.desktop.view.EmpleadoActionsView;
import com.pinguela.thegoldenbook.ui.desktop.view.EmpleadoSearchView;

public class EmpleadoActionsCellRenderer implements TableCellRenderer {
	
	private EmpleadoSearchView searchView = null;

	public EmpleadoActionsCellRenderer(EmpleadoSearchView view) {
		setView(view);
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		
		EmpleadoDTO empleado = (EmpleadoDTO) value;
		EmpleadoActionsView view = new EmpleadoActionsView(searchView);
		view.setModel(empleado);
		
		return view;
	}
	
	private void setView(EmpleadoSearchView view) {
		this.searchView = view;
	}

}
