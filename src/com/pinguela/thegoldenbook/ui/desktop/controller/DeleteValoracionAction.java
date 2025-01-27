package com.pinguela.thegoldenbook.ui.desktop.controller;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.service.ValoracionService;
import com.pinguela.thegoldenbook.service.impl.ValoracionServiceImpl;
import com.pinguela.thegoldenbook.ui.desktop.model.ValoracionTableModel;
import com.pinguela.thegoldenbook.ui.desktop.view.LibroDetailView;
import com.pinguela.thegoldenbook.ui.desktop.view.ValoracionActionsView;

public class DeleteValoracionAction extends BaseAction{

	private LibroDetailView detailView;
	private ValoracionActionsView view;
	private ValoracionService valoracionService = null;
	
	private static Logger logger = LogManager.getLogger(DeleteValoracionAction.class);
	
	public DeleteValoracionAction(LibroDetailView detailView, ValoracionActionsView view, String text) {
		super(text);
		initServices();
		setView(view, detailView);
	}
	
	public DeleteValoracionAction(LibroDetailView detailView, ValoracionActionsView view, String text, Icon icon) {
		super(text, icon);
		initServices();
		setView(view, detailView);
	}	
	
	@Override
	public void doAction() {
		
		try {
			
			if (valoracionService.delete(view.getModel().getClienteId(), view.getModel().getLibroId())) {
				ValoracionTableModel vtm = new ValoracionTableModel(valoracionService.findByLibro(view.getModel().getLibroId(), 1, Integer.MAX_VALUE).getPage());
				detailView.setValoracionModel(vtm);
				JOptionPane.showMessageDialog(null, "Valoración eliminada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Error al eliminar la valoración", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			
		}catch(Exception ex) {
			logger.error(ex.getMessage(),ex);
		}
		
	}
	
	private void setView(ValoracionActionsView view, LibroDetailView detailView) {
		this.view = view;
		this.detailView = detailView;
	}
	
	private void initServices() {
		valoracionService = new ValoracionServiceImpl();
	}

}
