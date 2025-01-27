package com.pinguela.thegoldenbook.ui.desktop.controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.model.Autor;
import com.pinguela.thegoldenbook.service.AutorService;
import com.pinguela.thegoldenbook.service.impl.AutorServiceImpl;
import com.pinguela.thegoldenbook.ui.desktop.model.AutorTableModel;
import com.pinguela.thegoldenbook.ui.desktop.view.AutorSearchView;

public class AutorSearchAction extends BaseAction{

	private AutorService autorService = null;
	private Autor autor = null;
	private List<Autor> autores = new ArrayList<Autor>();
	
	private AutorSearchView autorSearchView = null;
	
	private static Logger logger = LogManager.getLogger(AutorSearchAction.class);
	
	public AutorSearchAction(AutorSearchView view, String text) {
		super(text);
		setView(view);
		initServices();
		
	}
	
	public AutorSearchAction(AutorSearchView view ,String text, Icon icon) {
		super(text, icon);
		setView(view);
		initServices();
		
	}
	
	@Override
	public void doAction() {
		
		try {
			
			if(autorSearchView.getValueIdTextField().equals("")) {
				autores.clear();
				autores = autorService.findAll(1, Integer.MAX_VALUE).getPage();
				autorSearchView.setAutorTableModel(new AutorTableModel(autores));
				
			}else {
				autores.clear();
				autor = autorService.findByAutor(Long.valueOf(autorSearchView.getValueIdTextField()));
				autores.add(autor);
				autorSearchView.setAutorTableModel(new AutorTableModel(autores));
			}
			
			
		}catch(Exception ex) {
			logger.fatal(ex.getMessage(), ex);
		}

		
	}
	
	private void initServices() {
		autorService = new AutorServiceImpl();
	}
	
	private void setView(AutorSearchView view) {
		this.autorSearchView = view;
	}

}
