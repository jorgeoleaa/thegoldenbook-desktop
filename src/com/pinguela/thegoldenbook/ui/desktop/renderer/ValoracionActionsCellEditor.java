package com.pinguela.thegoldenbook.ui.desktop.renderer;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import com.pinguela.thegoldenbook.model.ValoracionDTO;
import com.pinguela.thegoldenbook.ui.desktop.view.LibroDetailView;
import com.pinguela.thegoldenbook.ui.desktop.view.ValoracionActionsView;

public class ValoracionActionsCellEditor extends AbstractCellEditor implements TableCellEditor{
	
	private LibroDetailView detailView = null;
	
	public ValoracionActionsCellEditor(LibroDetailView view) {
		setView(view);
	}
	
	@Override
	public Object getCellEditorValue() {
		return null;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table,
			Object value,
			boolean isSelected,
			int row,
			int column) {
		
		ValoracionDTO valoracion = (ValoracionDTO) value;
		ValoracionActionsView view = new ValoracionActionsView(detailView);
		view.setModel(valoracion);
		return view;
	}
	
	private void setView(LibroDetailView view) {
		this.detailView = view;
	}

}
