package com.pinguela.thegoldenbook.ui.desktop.view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;

import com.pinguela.thegoldenbook.model.AbstractCriteria;
import com.pinguela.thegoldenbook.ui.desktop.controller.EmpleadoSearchAction;
import com.pinguela.thegoldenbook.ui.desktop.model.EmpleadoTableModel;
import com.pinguela.thegoldenbook.ui.desktop.renderer.EmpleadoActionsCellEditor;
import com.pinguela.thegoldenbook.ui.desktop.renderer.EmpleadoActionsCellRenderer;
import com.pinguela.thegoldenbook.ui.desktop.renderer.EmpleadoTableCellRenderer;
import com.pinguela.thegoldenbook.ui.desktop.utils.SwingUtils;

public class EmpleadoSearchView extends SearchView {

	private static final long serialVersionUID = 1L;
	private JTextField idValueTextField;
	private JButton searchButton;
	private JTable empleadoResultsTable;;

	/**
	 * Create the panel.
	 */
	public EmpleadoSearchView() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel searchFieldsPanel = new JPanel();
		add(searchFieldsPanel, BorderLayout.WEST);
		GridBagLayout gbl_searchFieldsPanel = new GridBagLayout();
		gbl_searchFieldsPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_searchFieldsPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_searchFieldsPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_searchFieldsPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		searchFieldsPanel.setLayout(gbl_searchFieldsPanel);
		
		JLabel tituloLabel = new JLabel("Búsqueda de Empleados:");
		GridBagConstraints gbc_tituloLabel = new GridBagConstraints();
		gbc_tituloLabel.insets = new Insets(0, 0, 5, 5);
		gbc_tituloLabel.gridx = 1;
		gbc_tituloLabel.gridy = 1;
		searchFieldsPanel.add(tituloLabel, gbc_tituloLabel);
		
		JLabel idLabel = new JLabel("Identificador:");
		GridBagConstraints gbc_idLabel = new GridBagConstraints();
		gbc_idLabel.insets = new Insets(0, 0, 5, 5);
		gbc_idLabel.gridx = 1;
		gbc_idLabel.gridy = 6;
		searchFieldsPanel.add(idLabel, gbc_idLabel);
		
		idValueTextField = new JTextField();
		GridBagConstraints gbc_idValueTextField = new GridBagConstraints();
		gbc_idValueTextField.insets = new Insets(0, 0, 5, 5);
		gbc_idValueTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_idValueTextField.gridx = 1;   
		gbc_idValueTextField.gridy = 7;
		searchFieldsPanel.add(idValueTextField, gbc_idValueTextField);
		idValueTextField.setColumns(10);
		
		searchButton = new JButton();
		searchButton.setAction(new EmpleadoSearchAction(this, "Buscar"));
		GridBagConstraints gbc_searchButton = new GridBagConstraints();
		gbc_searchButton.insets = new Insets(0, 0, 0, 5);
		gbc_searchButton.gridx = 1;
		gbc_searchButton.gridy = 14;
		searchFieldsPanel.add(searchButton, gbc_searchButton);
		
		JScrollPane resultsScrollPane = new JScrollPane();
		add(resultsScrollPane, BorderLayout.CENTER);
		
		empleadoResultsTable = new JTable();
		resultsScrollPane.setViewportView(empleadoResultsTable);
		
		postInitialize();

	}
	
	private void postInitialize() {
		
		
	}
	
	public void setModel(EmpleadoTableModel empleadoTableModel) {
		empleadoResultsTable.setModel(empleadoTableModel);
		
		empleadoResultsTable.getColumnModel().getColumn(0).setCellRenderer(new EmpleadoTableCellRenderer());
		empleadoResultsTable.getColumnModel().getColumn(1).setCellRenderer(new EmpleadoTableCellRenderer());
		empleadoResultsTable.getColumnModel().getColumn(2).setCellRenderer(new EmpleadoTableCellRenderer());
		empleadoResultsTable.getColumnModel().getColumn(3).setCellRenderer(new EmpleadoTableCellRenderer());
		empleadoResultsTable.getColumnModel().getColumn(4).setCellRenderer(new EmpleadoTableCellRenderer());
		empleadoResultsTable.getColumnModel().getColumn(5).setCellRenderer(new EmpleadoTableCellRenderer());
		empleadoResultsTable.getColumnModel().getColumn(6).setCellRenderer(new EmpleadoTableCellRenderer());
		
		TableColumn actionsColumn = empleadoResultsTable.getColumnModel().getColumn(7);
		actionsColumn.setMinWidth(180);
		actionsColumn.setCellRenderer(new EmpleadoActionsCellRenderer(this));
		actionsColumn.setCellEditor(new EmpleadoActionsCellEditor(this));
	}
	
	public String getValueTextField() {
		return SwingUtils.getTextFieldValueOrNull(idValueTextField);
	}

	@Override
	protected void setTableColumnEditor() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setTableColumnRenderer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AbstractCriteria getCriteria() {
		// TODO Auto-generated method stub
		return null;
	}

}
