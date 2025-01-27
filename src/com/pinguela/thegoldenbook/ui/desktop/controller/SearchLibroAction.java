package com.pinguela.thegoldenbook.ui.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.model.LibroDTO;
import com.pinguela.thegoldenbook.model.Results;
import com.pinguela.thegoldenbook.service.LibroCriteria;
import com.pinguela.thegoldenbook.service.LibroService;
import com.pinguela.thegoldenbook.service.impl.LibroServiceImpl;
import com.pinguela.thegoldenbook.ui.desktop.model.LibroSearchTableModel;
import com.pinguela.thegoldenbook.ui.desktop.view.LibroSearchView;

public class SearchLibroAction extends AbstractAction{
	
	private LibroSearchView libroSearchView;
	private LibroService libroService = null;
	
	private static Logger logger = LogManager.getLogger(SearchLibroAction.class);
	
	public SearchLibroAction(LibroSearchView view, String nombre) {
		super(nombre);
		initSevices();
		setView(view);
	}
	
	public SearchLibroAction(LibroSearchView view, String nombre, Icon icon) {
		super(nombre, icon);
		initSevices();
		setView(view);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		try {
			
			LibroCriteria criteria = new LibroCriteria();
			libroSearchView.setCriteria(criteria);
			Results<LibroDTO> libroResults = libroService.findByCriteria(criteria, 1, Integer.MAX_VALUE);
			LibroSearchTableModel ltm = new LibroSearchTableModel(libroResults.getPage());
			libroSearchView.setTableModel(ltm);
			
		}catch(Exception da) {
			logger.error(da.getMessage(), da);
		}

		
	}
	
	private void setView(LibroSearchView view) {
		this.libroSearchView = view;
	}
	
	private void initSevices() {
		libroService = new LibroServiceImpl(); 
	}

}
