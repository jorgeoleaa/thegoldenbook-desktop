package com.pinguela.thegoldenbook.ui.desktop.renderer;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class ClienteSelectionTableCellEditor extends DefaultCellEditor{

	public ClienteSelectionTableCellEditor() {
		super(new JCheckBox());
		
	}
	
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected,
            int row, int column) {	
		
		JCheckBox selectionCheckBox = (JCheckBox) super.getComponent();
		selectionCheckBox.setText("Seleccionar");
		selectionCheckBox.setSelected((Boolean) value);
		return selectionCheckBox;
	}
	
	@Override
	public Object getCellEditorValue() {
		return ((JCheckBox) super.getComponent()).isSelected();
	}

}
