package com.pinguela.thegoldenbook.ui.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.service.AutorService;
import com.pinguela.thegoldenbook.service.impl.AutorServiceImpl;
import com.pinguela.thegoldenbook.ui.desktop.TheGoldenBookWindow;
import com.pinguela.thegoldenbook.ui.desktop.model.AutorTableModel;
import com.pinguela.thegoldenbook.ui.desktop.view.AutorSearchView;

public class ShowAutorSearchAction extends AbstractAction{
	
	private AutorService autorService = null;
	private static Logger logger = LogManager.getLogger(ShowAutorSearchAction.class);
	
	public ShowAutorSearchAction(String text) {
		super(text);
		initServices();
	}
	
	public ShowAutorSearchAction(String text, Icon icon) {
		super(text, icon);
		initServices();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			AutorSearchView autorSearchView = new AutorSearchView();
			AutorTableModel autorTableModel = new AutorTableModel(autorService.findAll(1, Integer.MAX_VALUE).getPage());
			autorSearchView.setAutorTableModel(autorTableModel);
			TheGoldenBookWindow.getInstance().addClosableTab("Busqueda de Autor", autorSearchView);
			
		}catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		
	
		
	}
	
	private void initServices() {
		autorService = new AutorServiceImpl();
	}
	
}
