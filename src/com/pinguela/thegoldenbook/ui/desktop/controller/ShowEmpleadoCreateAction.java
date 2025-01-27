package com.pinguela.thegoldenbook.ui.desktop.controller;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.model.Localidad;
import com.pinguela.thegoldenbook.model.Pais;
import com.pinguela.thegoldenbook.model.Provincia;
import com.pinguela.thegoldenbook.service.LocalidadService;
import com.pinguela.thegoldenbook.service.PaisService;
import com.pinguela.thegoldenbook.service.ProvinciaService;
import com.pinguela.thegoldenbook.service.impl.LocalidadServiceImpl;
import com.pinguela.thegoldenbook.service.impl.PaisServiceImpl;
import com.pinguela.thegoldenbook.service.impl.ProvinciaServiceImpl;
import com.pinguela.thegoldenbook.ui.desktop.dialog.EmpleadoInsertDialog;

public class ShowEmpleadoCreateAction extends BaseAction{
	
	private LocalidadService localidadService = null;
	private ProvinciaService provinciaService = null;
	private PaisService paisService = null;
	
	private static Logger logger = LogManager.getLogger(ShowEmpleadoCreateAction.class);

	public ShowEmpleadoCreateAction(String text) {
		super(text);
		initServices();
	}
	
	public ShowEmpleadoCreateAction(String text, Icon icon) {
		super(text, icon);
		initServices();
	}
	
	@Override
	public void doAction() {
		
		try {
			
			EmpleadoInsertDialog dialog = new EmpleadoInsertDialog();
			
			List<Localidad> localidades = localidadService.findAll();
			DefaultComboBoxModel<Localidad> localidadComboModel = 
					new DefaultComboBoxModel<Localidad>(localidades.toArray(new Localidad[localidades.size()]));
			
			List<Provincia> provincias = provinciaService.findAll();
			DefaultComboBoxModel<Provincia> provinciaComboModel = 
					new DefaultComboBoxModel<Provincia>(provincias.toArray(new Provincia[provincias.size()]));
			
			List<Pais> paises = paisService.findAll();
			DefaultComboBoxModel<Pais> paisComboModel =
					new DefaultComboBoxModel<Pais>(paises.toArray(new Pais[paises.size()]));
			
			dialog.setComboModel(localidadComboModel, provinciaComboModel, paisComboModel);
			
			dialog.setVisible(true);
			dialog.repaint();
			dialog.revalidate();
			
		}catch(Exception ex) {
			logger.warn(ex.getMessage(), ex);
		}
		
	}
	
	private void initServices() {
		localidadService = new LocalidadServiceImpl();
		provinciaService = new ProvinciaServiceImpl();
		paisService = new PaisServiceImpl();
	}

}
