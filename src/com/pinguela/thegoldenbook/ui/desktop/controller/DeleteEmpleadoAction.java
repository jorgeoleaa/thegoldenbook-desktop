package com.pinguela.thegoldenbook.ui.desktop.controller;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.service.EmpleadoService;
import com.pinguela.thegoldenbook.service.impl.EmpleadoServiceImpl;
import com.pinguela.thegoldenbook.ui.desktop.model.EmpleadoTableModel;
import com.pinguela.thegoldenbook.ui.desktop.view.EmpleadoActionsView;
import com.pinguela.thegoldenbook.ui.desktop.view.EmpleadoSearchView;

public class DeleteEmpleadoAction extends BaseAction{
	
	private EmpleadoActionsView view = null;
	private EmpleadoService empleadoService = null;
	private EmpleadoSearchView searchView = null;
	
	private static Logger logger = LogManager.getLogger(DeleteEmpleadoAction.class);

	public DeleteEmpleadoAction(EmpleadoSearchView searchView, EmpleadoActionsView view, String text) {
		super(text);
		initServices();
		setActionsView(view);
		setSearchView(searchView);
	}
	
	public DeleteEmpleadoAction(EmpleadoSearchView searchView, EmpleadoActionsView view, String text, Icon icon) {
		super(text, icon);
		initServices();
		setActionsView(view);
		setSearchView(searchView);

	}
	
	@Override
	public void doAction() {
		
		try {
			
			if(empleadoService.delete(view.getModel().getId())) {
				searchView.setModel(new EmpleadoTableModel(empleadoService.findAll(1, Integer.MAX_VALUE).getPage()));
				JOptionPane.showMessageDialog(null, "Empleado eliminado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null, "Error al eliminar el empleado", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
		}

	}
	
	private void setSearchView(EmpleadoSearchView view) {
		this.searchView = view;
	}
	
	private void setActionsView(EmpleadoActionsView view) {
		this.view = view;
	}
	

	private void initServices() {
		empleadoService = new EmpleadoServiceImpl();
	}

}
