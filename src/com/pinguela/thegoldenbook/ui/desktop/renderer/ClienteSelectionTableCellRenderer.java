package com.pinguela.thegoldenbook.ui.desktop.renderer;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ClienteSelectionTableCellRenderer implements TableCellRenderer{

	public ClienteSelectionTableCellRenderer() {
		super();
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		
		JCheckBox selectionCheckBox = new JCheckBox();
		selectionCheckBox.setText("Seleccionar");
		selectionCheckBox.setSelected((Boolean) value);
		return selectionCheckBox;
	}
}
