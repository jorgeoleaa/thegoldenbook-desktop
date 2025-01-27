package com.pinguela.thegoldenbook.ui.desktop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.model.Autor;
import com.pinguela.thegoldenbook.service.AutorService;
import com.pinguela.thegoldenbook.service.impl.AutorServiceImpl;
import com.pinguela.thegoldenbook.ui.desktop.dialog.AutorDetailDialog;
import com.pinguela.thegoldenbook.ui.desktop.model.AutorTableModel;
import com.pinguela.thegoldenbook.ui.desktop.view.AutorSearchView;

public class UpdateAutorAction extends BaseAction{
	
	private AutorDetailDialog autorDetailView;
	private AutorService autorService = null;
	private AutorSearchView view;
	
	private static Logger logger = LogManager.getLogger(UpdateAutorAction.class);
	
	public UpdateAutorAction(AutorSearchView autorSearchView, AutorDetailDialog view, String text) {
		super(text);
		setView(view);
		setAutorSearchView(autorSearchView);
		initServices();
	}
	
	public UpdateAutorAction(AutorSearchView autorSearchView, AutorDetailDialog view, String text, Icon icon) {
		super(text, icon);
		setView(view);
		setAutorSearchView(autorSearchView);
		initServices();
	}
	
	@Override
	public void doAction() {
		
		try {
			
			if(autorService.update(autorDetailView.getUpdateModel())) {
				if(view.getValueIdTextField().equals("")) {
					view.setAutorTableModel(new AutorTableModel(autorService.findAll(1, Integer.MAX_VALUE).getPage()));
				}else {
					
					List<Autor> autores = new ArrayList<Autor>();
					Autor autor = autorService.findByAutor(Long.valueOf(view.getValueIdTextField()));
					autores.add(autor);
					view.setAutorTableModel(new AutorTableModel(autores));
					autores.clear();
				}
				
				JOptionPane.showMessageDialog(null, "Autor actualizado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null, "Fallo al actualizar el autor", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}catch(Exception ex) {
			logger.warn(ex.getMessage(), ex);
		}
		
		
	}
	
	private void setView(AutorDetailDialog view) {
		this.autorDetailView = view;
	}
	
	private void setAutorSearchView(AutorSearchView view) {
		this.view = view;
	}
	
	private void initServices() {
		autorService = new AutorServiceImpl();
	}

}
