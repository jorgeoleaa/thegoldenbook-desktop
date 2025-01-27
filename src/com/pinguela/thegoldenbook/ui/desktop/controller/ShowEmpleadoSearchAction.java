package com.pinguela.thegoldenbook.ui.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.model.EmpleadoDTO;
import com.pinguela.thegoldenbook.model.Results;
import com.pinguela.thegoldenbook.service.EmpleadoService;
import com.pinguela.thegoldenbook.service.impl.EmpleadoServiceImpl;
import com.pinguela.thegoldenbook.ui.desktop.TheGoldenBookWindow;
import com.pinguela.thegoldenbook.ui.desktop.model.EmpleadoTableModel;
import com.pinguela.thegoldenbook.ui.desktop.view.EmpleadoSearchView;

public class ShowEmpleadoSearchAction extends AbstractAction{
	
	private EmpleadoService empleadoService = null;
	private static Logger logger = LogManager.getLogger(ShowEmpleadoSearchAction.class);
	
	public ShowEmpleadoSearchAction(String text) {
		super(text);
		initServices();
	}
	
	public ShowEmpleadoSearchAction(String text, Icon icon) {
		super(text, icon);
		initServices();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			EmpleadoSearchView empleadoSearchView = new EmpleadoSearchView();
			Results<EmpleadoDTO> empleados = empleadoService.findAll(1, Integer.MAX_VALUE);
			empleadoSearchView.setModel(new EmpleadoTableModel(empleados.getPage()));
			TheGoldenBookWindow.getInstance().addClosableTab("Búsqueda de empleados", empleadoSearchView);
			
		}catch(Exception ex) {
			logger.warn(ex.getMessage(), ex);
		}
		
		
	}
	
	private void initServices() {
		empleadoService = new EmpleadoServiceImpl();
	}

}
