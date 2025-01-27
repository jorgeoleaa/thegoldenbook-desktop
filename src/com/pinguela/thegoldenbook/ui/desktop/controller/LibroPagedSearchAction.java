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
import com.pinguela.thegoldenbook.ui.desktop.view.PaginatedSearchView;

public class LibroPagedSearchAction extends PagedSearchAction<LibroDTO> {
	
	private LibroService libroService = null;
	
	public LibroPagedSearchAction(int action, PaginatedSearchView view) {
		this(action, view, null, null);
		initServices();
	}

	public LibroPagedSearchAction(int action, PaginatedSearchView view, String name) {
		this(action, view, name, null);
		initServices();
	}
	
	public LibroPagedSearchAction(int action, PaginatedSearchView view, String name, Icon icon) {
		super(action,view,name,icon);
		initServices();
	}
	
	private void initServices() {
		libroService = new LibroServiceImpl();
	}
	


	@Override
	public Results<LibroDTO> doSearch(AbstractCriteria criteria) throws PinguelaException {
		LibroCriteria libroCriteria = (LibroCriteria) getView().getCriteria();
		Results<LibroDTO> results = libroService.findByCriteria(libroCriteria, 
				getCurrentPosition(), 
				PaginatedSearchView.PAGE_SIZE);
		

		return results;
	}

	@Override
	public TableModel getResultsTableModel() {
		Results<LibroDTO> results = (Results<LibroDTO>) getView().getResults();
		LibroSearchTableModel model = new LibroSearchTableModel(results.getPage());
		
		return model;
	}


}
