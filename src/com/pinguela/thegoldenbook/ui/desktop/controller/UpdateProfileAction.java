package com.pinguela.thegoldenbook.ui.desktop.controller;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.model.EmpleadoDTO;
import com.pinguela.thegoldenbook.service.DireccionService;
import com.pinguela.thegoldenbook.service.EmpleadoService;
import com.pinguela.thegoldenbook.service.impl.DireccionServiceImpl;
import com.pinguela.thegoldenbook.service.impl.EmpleadoServiceImpl;
import com.pinguela.thegoldenbook.ui.desktop.view.ProfileView;

public class UpdateProfileAction extends BaseAction{
	
	private ProfileView view = null;
	private EmpleadoDTO empleado = null;
	private EmpleadoService empleadoService = null;
	private DireccionService direccionService = null;
	
	private static Logger logger = LogManager.getLogger();
	
	public UpdateProfileAction(ProfileView view, String text) {
		super(text);
		initServices();
		setView(view);
	}
	
	public UpdateProfileAction(ProfileView view, String text, Icon icon) {
		super(text, icon);
		initServices();
		setView(view);
	}
	
	@Override
	public void doAction() {
		
		try {
			
			empleado = new EmpleadoDTO();
			view.setEmpleadoActualizadoModel(empleado);
			
			if (empleadoService.update(empleado) && direccionService.update(empleado.getDireccion())) {
				JOptionPane.showMessageDialog(null, "Tus datos han sido actualizados correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null, "Error al actualizar tus datos", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}catch (Exception ex) {
			logger.warn(ex.getMessage(), ex);
		}
		
	}
	
	private void initServices() {
		empleadoService = new EmpleadoServiceImpl();
		direccionService = new DireccionServiceImpl();
	}
	
	private void setView(ProfileView view) {
		this.view = view;
	}

}
