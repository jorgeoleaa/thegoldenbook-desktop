package com.pinguela.thegoldenbook.ui.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.model.Autor;
import com.pinguela.thegoldenbook.service.AutorService;
import com.pinguela.thegoldenbook.service.impl.AutorServiceImpl;
import com.pinguela.thegoldenbook.ui.desktop.dialog.AutorCreateDialog;

public class CreateAutorAction extends AbstractAction{
	
	private Logger logger = org.apache.logging.log4j.LogManager.getLogger(CreateAutorAction.class);
	
	private AutorCreateDialog autorCreateDialog = null;
	private AutorService autorService = null;
	
	public CreateAutorAction(AutorCreateDialog dialog, String text) {
		super(text);
		setDialog(dialog);
		initServices();
	}
	
	public CreateAutorAction(AutorCreateDialog dialog, String text, Icon icon) {
		super(text, icon);
		setDialog(dialog);
		initServices();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			
			Autor autor = new Autor();
			autor.setNombre(autorCreateDialog.getAutorNombreValue());
			autor.setApellido1(autorCreateDialog.getAutorApellido1Value());
			autor.setApellido2(autorCreateDialog.getAutorApellido2Value());
			autor.setFechaNacimiento(autorCreateDialog.getAutorFechaNacimiento());
			
			if(autor.getNombre() == "" || autor.getApellido1() == "" || autor.getFechaNacimiento() != null) {
				if(autorService.create(autor) != null) {
					JOptionPane.showMessageDialog(null, "El autor ha sido creado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "No se pudo crear el autor. Por favor, inténtalo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}else {
				JOptionPane.showMessageDialog(autorCreateDialog, "Tienen que estar los campos nombre, primer apellido y fecha de nacimiento rellenos", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			
			
			
		}catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		
	}
	
	private void setDialog(AutorCreateDialog dialog) {
		this.autorCreateDialog = dialog;
	}
	
	private void initServices() {
		autorService = new AutorServiceImpl();
	}

}
