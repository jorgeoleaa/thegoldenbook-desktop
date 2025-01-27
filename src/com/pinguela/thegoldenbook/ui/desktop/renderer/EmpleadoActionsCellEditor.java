package com.pinguela.thegoldenbook.ui.desktop.renderer;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import com.pinguela.thegoldenbook.model.EmpleadoDTO;
import com.pinguela.thegoldenbook.ui.desktop.view.EmpleadoActionsView;
import com.pinguela.thegoldenbook.ui.desktop.view.EmpleadoSearchView;

public class EmpleadoActionsCellEditor extends AbstractCellEditor implements TableCellEditor{
	
	private EmpleadoSearchView searchView = null;
	
	public EmpleadoActionsCellEditor(EmpleadoSearchView view) {
		setView(view);
	}
	
	@Override
	public Object getCellEditorValue() {
		return null;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		
		EmpleadoDTO empleado = (EmpleadoDTO) value;
		EmpleadoActionsView view = new EmpleadoActionsView(searchView);
		view.setModel(empleado);
		
		return view;
	}
	
	private void setView(EmpleadoSearchView view) {
		this.searchView = view;
	}

}
