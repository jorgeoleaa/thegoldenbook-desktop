package com.pinguela.thegoldenbook.ui.desktop.view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.pinguela.thegoldenbook.model.AbstractCriteria;
import com.pinguela.thegoldenbook.model.ClienteDTO;
import com.pinguela.thegoldenbook.ui.desktop.controller.BaseAction;
import com.pinguela.thegoldenbook.ui.desktop.controller.ClienteSearchAction;
import com.pinguela.thegoldenbook.ui.desktop.model.ClienteTableModel;
import com.pinguela.thegoldenbook.ui.desktop.renderer.ClienteActionsCellEditor;
import com.pinguela.thegoldenbook.ui.desktop.renderer.ClienteActionsCellRenderer;
import com.pinguela.thegoldenbook.ui.desktop.renderer.ClienteTableCellRenderer;

public class ClienteSearchView extends SearchView {
	private JRadioButton idRadioButton;
	private JRadioButton nicknameRadioButton;
	private JRadioButton mailRadioButton;
	private JTextField valueTextField;
	private ButtonGroup group;
	private JScrollPane resultsScrollPanel;
	
	private static ClienteSearchView instance;
	private TableCellRenderer actionsColumnCellRenderer;
	private TableCellEditor actionsColumnCellEditor;
	private JButton searchButton;

	public ClienteSearchView() {
		this.setLayout(new BorderLayout(0, 0));

		this.add(getSearchFieldPanel(), BorderLayout.WEST);
		GridBagLayout gbl_searchFieldsPanel = new GridBagLayout();
		gbl_searchFieldsPanel.columnWidths = new int[]{0, 131, 0, 0};
		gbl_searchFieldsPanel.rowHeights = new int[]{17, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_searchFieldsPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_searchFieldsPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getSearchFieldPanel().setLayout(gbl_searchFieldsPanel);

		JLabel busquedaClientesLabel = new JLabel("Búsqueda de clientes:");
		GridBagConstraints gbc_busquedaClientesLabel = new GridBagConstraints();
		gbc_busquedaClientesLabel.insets = new Insets(0, 0, 5, 5);
		gbc_busquedaClientesLabel.anchor = GridBagConstraints.NORTHWEST;
		gbc_busquedaClientesLabel.gridx = 1;
		gbc_busquedaClientesLabel.gridy = 1;
		getSearchFieldPanel().add(busquedaClientesLabel, gbc_busquedaClientesLabel);

		idRadioButton = new JRadioButton("ID");
		GridBagConstraints gbc_idRadioButton = new GridBagConstraints();
		gbc_idRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_idRadioButton.gridx = 1;
		gbc_idRadioButton.gridy = 3;
		getSearchFieldPanel().add(idRadioButton, gbc_idRadioButton);

		nicknameRadioButton = new JRadioButton("Nickname");
		GridBagConstraints gbc_nicknameRadioButton = new GridBagConstraints();
		gbc_nicknameRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_nicknameRadioButton.gridx = 1;
		gbc_nicknameRadioButton.gridy = 5;
		getSearchFieldPanel().add(nicknameRadioButton, gbc_nicknameRadioButton);

		mailRadioButton = new JRadioButton(" Correo electrónico");
		GridBagConstraints gbc_mailRadioButton = new GridBagConstraints();
		gbc_mailRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_mailRadioButton.gridx = 1;
		gbc_mailRadioButton.gridy = 7;
		getSearchFieldPanel().add(mailRadioButton, gbc_mailRadioButton);

		valueTextField = new JTextField();
		GridBagConstraints gbc_valueTextField = new GridBagConstraints();
		gbc_valueTextField.insets = new Insets(0, 0, 5, 5);
		gbc_valueTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_valueTextField.gridx = 1;
		gbc_valueTextField.gridy = 10;
		getSearchFieldPanel().add(valueTextField, gbc_valueTextField);
		valueTextField.setColumns(10);
		valueTextField.setVisible(true);

		searchButton = new JButton();
		setSearchAction(new ClienteSearchAction(this, "Buscar"));

		GridBagConstraints gbc_searchButton = new GridBagConstraints();
		gbc_searchButton.insets = new Insets(0, 0, 0, 5);
		gbc_searchButton.gridx = 1;
		gbc_searchButton.gridy = 17;
		getSearchFieldPanel().add(searchButton, gbc_searchButton);

		JPanel paginationPanel = new JPanel();
		this.add(paginationPanel, BorderLayout.SOUTH);

		resultsScrollPanel = new JScrollPane();
		this.add(resultsScrollPanel, BorderLayout.CENTER);
		resultsScrollPanel.setViewportView(getResultsTable());

		postInitialize();
	}

	private void postInitialize() {

		group = new ButtonGroup();

		group.add(idRadioButton);
		idRadioButton.setSelected(true);
		idRadioButton.setActionCommand("1");
		group.add(nicknameRadioButton);
		nicknameRadioButton.setActionCommand("2");
		group.add(mailRadioButton);
		mailRadioButton.setActionCommand("3");
		
		setTableModel(new ClienteTableModel(new ArrayList<ClienteDTO>()));
		
		getResultsTable().setDefaultRenderer(Object.class, new ClienteTableCellRenderer());
		


	}

	public Integer getSelectedSearchMethod() {
		Integer  selectedSearchMethod = null;
		AbstractButton rb = null;
		for (Enumeration<AbstractButton> rbs = group.getElements(); rbs.hasMoreElements(); ) {
			rb = rbs.nextElement();
			if (rb.isSelected()) {
				selectedSearchMethod = Integer.valueOf(rb.getActionCommand()); 								
			}
		}
		return selectedSearchMethod;
	}

	public String getTextFieldValue() {
		return valueTextField.getText();
	}
	
	public static ClienteSearchView getInstance() {
		if(instance == null) {
			instance = new ClienteSearchView();
		}
		return instance;
	}
	
	protected void setSearchAction(BaseAction action) {
		searchButton.setAction(action);
	}

	@Override
	public AbstractCriteria getCriteria() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setTableColumnEditor() {
		getResultsTable().getColumnModel().getColumn(8).setCellEditor(new ClienteActionsCellEditor(this));
		
	}

	@Override
	protected void setTableColumnRenderer() {
		getResultsTable().getColumnModel().getColumn(8).setCellRenderer(new ClienteActionsCellRenderer(this));
		
	}
}
