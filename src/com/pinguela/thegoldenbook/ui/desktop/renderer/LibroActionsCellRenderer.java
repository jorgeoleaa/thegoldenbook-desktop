package com.pinguela.thegoldenbook.ui.desktop.renderer;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import com.pinguela.thegoldenbook.model.LibroDTO;
import com.pinguela.thegoldenbook.ui.desktop.view.LibroSearchActionsView;
import com.pinguela.thegoldenbook.ui.desktop.view.LibroSearchView;

public class LibroActionsCellRenderer implements TableCellRenderer{

	private LibroSearchView view;
	
	public LibroActionsCellRenderer(LibroSearchView view) {
		setView(view);
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		
		LibroDTO libro = (LibroDTO) value;
		LibroSearchActionsView view = new LibroSearchActionsView(this.view);
		view.setModel(libro);
		
		return view;
	}
	
	private void setView(LibroSearchView view) {
		this.view = view;
	}

}
