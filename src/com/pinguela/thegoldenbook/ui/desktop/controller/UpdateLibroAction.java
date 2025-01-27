package com.pinguela.thegoldenbook.ui.desktop.controller;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.model.LibroDTO;
import com.pinguela.thegoldenbook.service.LibroCriteria;
import com.pinguela.thegoldenbook.service.LibroService;
import com.pinguela.thegoldenbook.service.impl.LibroServiceImpl;
import com.pinguela.thegoldenbook.ui.desktop.TheGoldenBookWindow;
import com.pinguela.thegoldenbook.ui.desktop.view.LibroDetailView;
import com.pinguela.thegoldenbook.ui.desktop.view.LibroSearchView;

public class UpdateLibroAction extends BaseAction{

	private LibroDetailView dialog;
	private LibroSearchView searchView;
	private LibroService libroService = null;	
	private LibroDTO libro = null;
	
	private static Logger logger = LogManager.getLogger(UpdateLibroAction.class);
	
	public UpdateLibroAction(LibroSearchView view,LibroDetailView dialog, String text) {
		super(text);
		initServices();
		setView(dialog);
		setSearchView(view);
	}
	
	public UpdateLibroAction(LibroSearchView view, LibroDetailView dialog, String text, Icon icon) {
		super(text, icon);
		initServices();
		setView(dialog);
		setSearchView(view);
	}
	
	@Override
	public void doAction() {
		
		try {
			
			libro = dialog.getLibro();
			dialog.setUpdateModel(libro);
			
			if(libroService.update(libro)) {
				
				searchView.setStart(new LibroPagedSearchAction(PagedSearchAction.START, null, "Buscar",new ImageIcon(TheGoldenBookWindow.class.getResource("/nuvola/32x32/1826_player_rev_player_rev.png"))));
				dialog.setEditable(false);
				dialog.setEditButtonVisible();
				JOptionPane.showMessageDialog(null, "Libro actualizado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null, "Fallo al actualizar el libro", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}catch(Exception ex) {
			
		}
		
	}
	
	private void setSearchView(LibroSearchView view) {
		this.searchView = view;
	}
	
	private void setView(LibroDetailView view) {
		this.dialog = view;
	}
	
	private void initServices() {
		libroService = new LibroServiceImpl();
	}
}
