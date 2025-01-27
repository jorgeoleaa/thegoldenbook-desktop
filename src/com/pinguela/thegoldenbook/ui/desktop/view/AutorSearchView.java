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
import com.pinguela.thegoldenbook.ui.desktop.controller.AutorSearchAction;
import com.pinguela.thegoldenbook.ui.desktop.model.AutorTableModel;
import com.pinguela.thegoldenbook.ui.desktop.renderer.AutorActionsCellEditor;
import com.pinguela.thegoldenbook.ui.desktop.renderer.AutorActionsCellRenderer;
import com.pinguela.thegoldenbook.ui.desktop.renderer.AutorTableCellRenderer;

public class AutorSearchView extends SearchView {
	
	private JTextField idValueTextField;
	private JScrollPane resultsScrollPanel;
	private JTable autoresResultsTable;

	public AutorSearchView() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel searchFieldsPanel = new JPanel();
		add(searchFieldsPanel, BorderLayout.WEST);
		GridBagLayout gbl_searchFieldsPanel = new GridBagLayout();
		gbl_searchFieldsPanel.columnWidths = new int[]{0, 60, 0, 0};
		gbl_searchFieldsPanel.rowHeights = new int[]{17, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_searchFieldsPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_searchFieldsPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		searchFieldsPanel.setLayout(gbl_searchFieldsPanel);
		
		JLabel tituloLabel = new JLabel("Búsqueda de Autores:");
		GridBagConstraints gbc_tituloLabel = new GridBagConstraints();
		gbc_tituloLabel.insets = new Insets(0, 0, 5, 5);
		gbc_tituloLabel.anchor = GridBagConstraints.NORTHWEST;
		gbc_tituloLabel.gridx = 1;
		gbc_tituloLabel.gridy = 1;
		searchFieldsPanel.add(tituloLabel, gbc_tituloLabel);
		
		JLabel idLabel = new JLabel("Identificador:");
		GridBagConstraints gbc_idLabel = new GridBagConstraints();
		gbc_idLabel.insets = new Insets(0, 0, 5, 5);
		gbc_idLabel.gridx = 1;
		gbc_idLabel.gridy = 4;
		searchFieldsPanel.add(idLabel, gbc_idLabel);
		
		idValueTextField = new JTextField();
		GridBagConstraints gbc_idValueTextField = new GridBagConstraints();
		gbc_idValueTextField.insets = new Insets(0, 0, 5, 5);
		gbc_idValueTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_idValueTextField.gridx = 1;
		gbc_idValueTextField.gridy = 5;
		searchFieldsPanel.add(idValueTextField, gbc_idValueTextField);
		idValueTextField.setColumns(10);
		
		JButton btnBuscar = new JButton();
		btnBuscar.setAction(new AutorSearchAction(this, "Buscar"));
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.insets = new Insets(0, 0, 0, 5);
		gbc_btnBuscar.gridx = 1;
		gbc_btnBuscar.gridy = 9;
		searchFieldsPanel.add(btnBuscar, gbc_btnBuscar);
		
		resultsScrollPanel = new JScrollPane();
		add(resultsScrollPanel, BorderLayout.CENTER);
		
		autoresResultsTable = new JTable();
		resultsScrollPanel.setViewportView(autoresResultsTable);
		
		postInitialize();
	}
	
	private void postInitialize() {

	}
	
	public void setAutorTableModel(AutorTableModel atm) {
		this.autoresResultsTable.setModel(atm);
		
		autoresResultsTable.getColumnModel().getColumn(0).setCellRenderer(new AutorTableCellRenderer());
		autoresResultsTable.getColumnModel().getColumn(1).setCellRenderer(new AutorTableCellRenderer());
		autoresResultsTable.getColumnModel().getColumn(2).setCellRenderer(new AutorTableCellRenderer());
		autoresResultsTable.getColumnModel().getColumn(3).setCellRenderer(new AutorTableCellRenderer());
		autoresResultsTable.getColumnModel().getColumn(4).setCellRenderer(new AutorTableCellRenderer());
		
		TableColumn actionsColumn = autoresResultsTable.getColumnModel().getColumn(5);
		actionsColumn.setMinWidth(180);
		actionsColumn.setCellRenderer(new AutorActionsCellRenderer(this));
		actionsColumn.setCellEditor(new AutorActionsCellEditor(this));
	}
	
	public String getValueIdTextField() {
		return idValueTextField.getText();
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
