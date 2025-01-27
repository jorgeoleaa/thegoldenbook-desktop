package com.pinguela.thegoldenbook.ui.desktop.renderer;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import com.pinguela.thegoldenbook.model.ValoracionDTO;
import com.pinguela.thegoldenbook.ui.desktop.view.LibroDetailView;
import com.pinguela.thegoldenbook.ui.desktop.view.ValoracionActionsView;

public class ValoracionActionsCellRenderer implements TableCellRenderer{
	
	private LibroDetailView detailView = null;
	
	public ValoracionActionsCellRenderer(LibroDetailView view) {
		setView(view);
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		
		ValoracionDTO valoracion = (ValoracionDTO) value;
		ValoracionActionsView view = new ValoracionActionsView(detailView);
		view.setModel(valoracion);
		return view;
	}
	
	private void setView(LibroDetailView view) {
		this.detailView = view;
	}

}
