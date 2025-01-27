package com.pinguela.thegoldenbook.ui.desktop.renderer;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import com.pinguela.thegoldenbook.model.LibroDTO;
import com.pinguela.thegoldenbook.ui.desktop.view.LibroSearchActionsView;
import com.pinguela.thegoldenbook.ui.desktop.view.LibroSearchView;

public class LibroActionsCellEditor extends AbstractCellEditor implements TableCellEditor{

	private LibroDTO libro = null;
	private LibroSearchView view;
	
	public LibroActionsCellEditor(LibroSearchView view) {
		setView(view);
	}
	
	@Override
	public Object getCellEditorValue() {
		
		return libro;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		
		this.libro = (LibroDTO) value;
		LibroSearchActionsView view = new LibroSearchActionsView(this.view);
		view.setModel(libro);
		
		return view;
	}
	
	private void setView(LibroSearchView view) {
		this.view = view;
	}

}
