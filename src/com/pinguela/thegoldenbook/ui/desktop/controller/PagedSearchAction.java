package com.pinguela.thegoldenbook.ui.desktop.controller;

import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.PinguelaException;
import com.pinguela.thegoldenbook.model.AbstractCriteria;
import com.pinguela.thegoldenbook.model.LibroDTO;
import com.pinguela.thegoldenbook.model.Results;
import com.pinguela.thegoldenbook.ui.desktop.view.PaginatedSearchView;

public abstract class PagedSearchAction<T>
		extends BaseAction {
	
	public static final int START = 1;
	public static final int PREVIOUS = 5;
	public static final int NEXT = 10;
	public static final int END = 15;
	
	private static Logger logger = LogManager.getLogger(PagedSearchAction.class); 
	
	private PaginatedSearchView view = null;
	
	private int action = START;
	

	
	public PagedSearchAction(int action, PaginatedSearchView view) {
		this(action, view, null, null);
	}

	public PagedSearchAction(int action, PaginatedSearchView view, String name) {
		this(action, view, name, null);
	}
	
	public PagedSearchAction(int action, PaginatedSearchView view, String name, Icon icon) {
		super(name, icon);
		setAction(action);
		setView(view);
	}
	
	
	/**
	 * Template method.
	 * Common paging behaviour.
	 */
	public void doAction() {
		try {		
			
			AbstractCriteria criteria = view.getCriteria();
			Results<T> results = doSearch(criteria);
			
			logger.info("Found "+results.getTotal()+" for: "+criteria);
			view.setResults(results);
			view.setTableModel(getResultsTableModel());
			view.setCurrentPosition(getCurrentPosition());
			view.updateView();
			
		} catch(PinguelaException ex) {
			logger.error(ex.getMessage(), ex);
			JOptionPane.showMessageDialog(view,"Se ha producido un error: "+ex.getMessage(),"Error en la búsqueda", JOptionPane.ERROR_MESSAGE);
		}
	}

	protected PaginatedSearchView getView() {
		return this.view;
	}
	
	protected void setView(PaginatedSearchView view) {
		this.view = view;
	}
	
	protected int getAction() {
		return action;
	}

	protected void setAction(int action) {
		this.action = action;
	}


	public int getCurrentPosition() {
		int position = 1;
		switch (action) {
		case START:
			position = 1;
			break;
		case PREVIOUS:
			if (view.getCurrentPosition()>PaginatedSearchView.PAGE_SIZE) {
				position = view.getCurrentPosition()-PaginatedSearchView.PAGE_SIZE;
			} else {
				position = 1;
			}
			break;
		case NEXT:
			position = view.getCurrentPosition()+PaginatedSearchView.PAGE_SIZE;
			break;
		case END:
			int total = view.getResults().getTotal();
			int sobrante = total%10;
			position =  total-sobrante+1;	
			break;
		}
		System.out.println("currentPosticion = "+position);
		return position;
	}
	
	
	public abstract Results<T> doSearch(AbstractCriteria criteria) throws PinguelaException;
	
	public abstract TableModel getResultsTableModel();

	
}

