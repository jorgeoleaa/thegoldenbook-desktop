package com.pinguela.thegoldenbook.ui.desktop.controller;

import javax.swing.Icon;
import javax.swing.table.TableModel;

import com.pinguela.PinguelaException;
import com.pinguela.thegoldenbook.model.AbstractCriteria;
import com.pinguela.thegoldenbook.model.LibroDTO;
import com.pinguela.thegoldenbook.model.Results;
import com.pinguela.thegoldenbook.service.LibroCriteria;
import com.pinguela.thegoldenbook.service.LibroService;
import com.pinguela.thegoldenbook.service.impl.LibroServiceImpl;
import com.pinguela.thegoldenbook.ui.desktop.model.LibroSearchTableModel;
import com.pinguela.thegoldenbook.ui.desktop.model.LibroSelectionTableModel;
import com.pinguela.thegoldenbook.ui.desktop.view.PaginatedSearchView;

public class LibroPagedSelectionAction extends LibroPagedSearchAction {
	
	
	
	public LibroPagedSelectionAction(int action, PaginatedSearchView view) {
		this(action, view, null, null);
	}

	public LibroPagedSelectionAction(int action, PaginatedSearchView view, String name) {
		this(action, view, name, null);
	}
	
	public LibroPagedSelectionAction(int action, PaginatedSearchView view, String name, Icon icon) {
		super(action,view,name,icon);
	}
	

	@Override
	public TableModel getResultsTableModel() {
		Results<LibroDTO> results = (Results<LibroDTO>) getView().getResults();
		LibroSelectionTableModel model = new LibroSelectionTableModel(results.getPage());
		return model;
	}


}
