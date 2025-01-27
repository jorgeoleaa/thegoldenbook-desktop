package com.pinguela.thegoldenbook.ui.desktop.renderer;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import com.pinguela.thegoldenbook.model.Autor;
import com.pinguela.thegoldenbook.ui.desktop.view.AutorActionsView;
import com.pinguela.thegoldenbook.ui.desktop.view.AutorSearchView;

public class AutorActionsCellEditor extends AbstractCellEditor implements TableCellEditor{

	private AutorSearchView autorSearchView;
	private Autor autor = null;
	
	public AutorActionsCellEditor(AutorSearchView view) {
		setView(view);
	}
	
	@Override
	public Object getCellEditorValue() {
		return autor;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		this.autor = (Autor) value;
		
		AutorActionsView view = new AutorActionsView(autorSearchView);
		view.setModel(autor);
		return view;
	}
	
	private void setView(AutorSearchView view) {
		this.autorSearchView = view;
	}

}
