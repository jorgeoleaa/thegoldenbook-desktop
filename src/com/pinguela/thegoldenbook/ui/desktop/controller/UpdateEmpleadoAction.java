package com.pinguela.thegoldenbook.ui.desktop.controller;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.model.EmpleadoDTO;
import com.pinguela.thegoldenbook.service.EmpleadoService;
import com.pinguela.thegoldenbook.service.impl.EmpleadoServiceImpl;
import com.pinguela.thegoldenbook.ui.desktop.dialog.EmpleadoDetailDialog;

public class UpdateEmpleadoAction extends BaseAction{

	private EmpleadoService empleadoService = null;
	private EmpleadoDetailDialog dialog = null;
	
	private static Logger logger = LogManager.getLogger(UpdateEmpleadoAction.class);
	
	public UpdateEmpleadoAction(EmpleadoDetailDialog dialog, String text) {
		super(text);
		initServices();
		setDialog(dialog);
	}
	
	public UpdateEmpleadoAction(EmpleadoDetailDialog dialog, String text, Icon icon) {
		super(text, icon);
		initServices();
		setDialog(dialog);
	}
	
	@Override
	public void doAction() {
		
		try {
			
			EmpleadoDTO empleado = empleadoService.findBy(dialog.getEmpleadoId());
			dialog.setModel(empleado);
			
			if(empleadoService.update(empleado)) {
				JOptionPane.showMessageDialog(null, "Empleado actualizado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null, "Actualización fallida", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}catch(Exception ex) {
			logger.warn(ex.getMessage(), ex);
		}
		
		
	}
	
	private void initServices() {
		empleadoService = new EmpleadoServiceImpl();
	}
	
	private void setDialog(EmpleadoDetailDialog dialog) {
		this.dialog = dialog;
	}

}
