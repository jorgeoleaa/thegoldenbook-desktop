package com.pinguela.thegoldenbook.ui.desktop.controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.model.EmpleadoDTO;
import com.pinguela.thegoldenbook.service.EmpleadoService;
import com.pinguela.thegoldenbook.service.impl.EmpleadoServiceImpl;
import com.pinguela.thegoldenbook.ui.desktop.TheGoldenBookWindow;
import com.pinguela.thegoldenbook.ui.desktop.model.EmpleadoTableModel;
import com.pinguela.thegoldenbook.ui.desktop.view.EmpleadoSearchView;

public class EmpleadoSearchAction extends AbstractAction{

	private EmpleadoService empleadoService = null;
	private EmpleadoDTO empleado = null;
	private EmpleadoSearchView view = null;
	private List<EmpleadoDTO> empleados = null;
	
	private static Logger logger = LogManager.getLogger(ShowEmpleadoSearchAction.class);
	
	public EmpleadoSearchAction(EmpleadoSearchView empleadoSearchView, String text) {
		super(text);
		initServices();
		setView(empleadoSearchView);
	}
	
	public EmpleadoSearchAction(EmpleadoSearchView empleadoSearchView, String text, Icon icon) {
		super(text, icon);
		initServices();
		setView(empleadoSearchView);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		empleados = new ArrayList<EmpleadoDTO>();
		
		try {
			empleados.clear();
			
			if(view.getValueTextField() != null) {
				empleado = empleadoService.findBy(Long.valueOf(view.getValueTextField()));
				empleados.add(empleado);
				view.setModel(new EmpleadoTableModel(empleados));
			}else {
				view.setModel(new EmpleadoTableModel(empleadoService.findAll(1, Integer.MAX_VALUE).getPage()));
			}
			
			TheGoldenBookWindow.getInstance().addClosableTab("Búsqueda de empleados", view);
			
		}catch(Exception ex) {
			logger.warn(ex.getMessage(), ex);
		}
		
		
	}
	
	private void initServices() {
		empleadoService = new EmpleadoServiceImpl();
	}
	
	private void setView(EmpleadoSearchView searchView) {
		this.view = searchView;
	}

}
