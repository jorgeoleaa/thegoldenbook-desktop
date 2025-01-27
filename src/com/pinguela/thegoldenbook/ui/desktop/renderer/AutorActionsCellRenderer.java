package com.pinguela.thegoldenbook.ui.desktop.renderer;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import com.pinguela.thegoldenbook.model.Autor;
import com.pinguela.thegoldenbook.ui.desktop.view.AutorActionsView;
import com.pinguela.thegoldenbook.ui.desktop.view.AutorSearchView;

public class AutorActionsCellRenderer implements TableCellRenderer{

	private AutorSearchView autorSearchView;
	
	public AutorActionsCellRenderer(AutorSearchView view) {
		setView(view);
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		
		Autor autor = (Autor) value;
		AutorActionsView view = new AutorActionsView(autorSearchView);
		view.setModel(autor);
		return view;
		
	}
	
	private void setView(AutorSearchView view) {
		this.autorSearchView = view;
	}
}
