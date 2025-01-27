package com.pinguela.thegoldenbook.ui.desktop.controller;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.model.DireccionDTO;
import com.pinguela.thegoldenbook.model.EmpleadoDTO;
import com.pinguela.thegoldenbook.service.EmpleadoService;
import com.pinguela.thegoldenbook.service.impl.EmpleadoServiceImpl;
import com.pinguela.thegoldenbook.ui.desktop.dialog.EmpleadoInsertDialog;
import com.pinguela.thegoldenbook.ui.desktop.utils.SwingUtils;

public class CreateEmpleadoAction extends BaseAction{

	private EmpleadoInsertDialog empleadoInsertDialog;
	private EmpleadoService empleadoService = null;
	
	private static Logger logger = LogManager.getLogger(CreateEmpleadoAction.class);
	
	public CreateEmpleadoAction(EmpleadoInsertDialog dialog, String text) {
		super(text);
		initServices();
		setDialog(dialog);
	}
	
	public CreateEmpleadoAction(EmpleadoInsertDialog dialog, String text, Icon icon) {
		super(text, icon);
		initServices();
		setDialog(dialog);
	}
	
	@Override
	public void doAction() {
		
		try {
			EmpleadoDTO empleado = new EmpleadoDTO();
			DireccionDTO direccion = new DireccionDTO();
			empleadoInsertDialog.setModel(empleado, direccion);
			
			Set<String> fieldsToCheckDireccion = new LinkedHashSet<String>(Arrays.asList(
					"nombreVia", "dirVia", "codigoPostal"
					));
			
			Set<String> fieldsToChekEmpleado = new LinkedHashSet<String>(Arrays.asList(
					"nombre", "apellido1", "apellido2", "dniNie", "telefono", "email", "password"
					));
			
			if(SwingUtils.comprobarCamposVacios(empleado, fieldsToChekEmpleado) && SwingUtils.comprobarCamposVacios(direccion, fieldsToCheckDireccion)) {
				JOptionPane.showMessageDialog(empleadoInsertDialog, "Tienes que rellenar los datos que faltan", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
				
			}else if(SwingUtils.comprobarCamposVacios(direccion, fieldsToCheckDireccion)) {
				JOptionPane.showMessageDialog(empleadoInsertDialog, "Tienes que rellenar todos los campos de la dirección", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
			}else if(SwingUtils.comprobarCamposVacios(empleado, fieldsToChekEmpleado)) {
				JOptionPane.showMessageDialog(empleadoInsertDialog, "Tienes que rellenar todos los campos del empleado", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
			}else {
				if(empleadoService.registrar(empleado) != null) {
					JOptionPane.showMessageDialog(null, "El empleado se registró correctamente", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "El empleado no se registró correctamente", "Registro Erróneo", JOptionPane.ERROR_MESSAGE);
				}
			}
			
			
			
		}catch(Exception ex) {
			logger.warn(ex.getMessage(), ex);
		}
		
	
		
	}
	
	private void setDialog(EmpleadoInsertDialog dialog) {
		this.empleadoInsertDialog = dialog;
	}
	
	private void initServices() {
		empleadoService = new EmpleadoServiceImpl();
	}

}
