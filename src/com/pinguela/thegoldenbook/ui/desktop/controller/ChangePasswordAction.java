package com.pinguela.thegoldenbook.ui.desktop.controller;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.service.EmpleadoService;
import com.pinguela.thegoldenbook.service.impl.EmpleadoServiceImpl;
import com.pinguela.thegoldenbook.ui.desktop.TheGoldenBookWindow;
import com.pinguela.thegoldenbook.ui.desktop.dialog.ChangeContraseñaDialog;

public class ChangePasswordAction extends BaseAction{

	private ChangeContraseñaDialog dialog;
	private EmpleadoService empleadoService;
	
	private static Logger logger = LogManager.getLogger(ChangePasswordAction.class);
	
	public ChangePasswordAction(ChangeContraseñaDialog dialog, String text) {
		super(text);
		initServices();
		setDialog(dialog);
	}
	
	public ChangePasswordAction(ChangeContraseñaDialog dialog, String text, Icon icon) {
		super(text, icon);
		initServices();
		setDialog(dialog);
	}
	
	@Override
	public void doAction() {
		
		try {
			
			if(dialog.getNewPasswordValue().compareTo(dialog.getConfirmPasswordValue()) == 0) {
				
				if(empleadoService.updatePassword(dialog.getConfirmPasswordValue().toString(), TheGoldenBookWindow.getInstance().getUsuarioAutenticado().getId())) {
					JOptionPane.showMessageDialog(null, "Contraseña actualizada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "Error en el método de actualizar la contraseña", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}else {
				JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}catch(Exception ex) {
			logger.warn(ex.getMessage(),ex);
		}
		
	}
	
	private void setDialog(ChangeContraseñaDialog dialog) {
		this.dialog = dialog;
	}
	
	private void initServices() {
		empleadoService = new EmpleadoServiceImpl();
	}

}
