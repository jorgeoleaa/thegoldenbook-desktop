package com.pinguela.thegoldenbook.ui.desktop.view;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import com.pinguela.thegoldenbook.model.AbstractCriteria;
import com.pinguela.thegoldenbook.ui.desktop.model.LibroSearchTableModel;

public abstract class SearchView extends View{
	
	private JTable tableResults;
	private JPanel searchFieldPanel;
	private JScrollPane resultsScrollPane;
	
	
	
	public SearchView() {
		initialize();
	}
	
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		searchFieldPanel = new JPanel();
		add(searchFieldPanel, BorderLayout.NORTH);
		
		resultsScrollPane = new JScrollPane();
		add(resultsScrollPane, BorderLayout.CENTER);
		
		tableResults = new JTable();
		resultsScrollPane.setViewportView(tableResults);
		
	}
	
	
	
	protected JTable getResultsTable() {
		return tableResults;
	}
	
	
	protected JPanel getSearchFieldPanel() {
		return searchFieldPanel;
	}
	

	public TableModel getTableModel() {
		return this.tableResults.getModel();
	}
	
	
	public void setTableModel(TableModel model) {
		this.tableResults.setModel(model);
		setTableColumnRenderer();
		setTableColumnEditor();
	}
	
	
	protected abstract void setTableColumnEditor();
	
	protected abstract void setTableColumnRenderer();
	
	public abstract AbstractCriteria getCriteria();

}
