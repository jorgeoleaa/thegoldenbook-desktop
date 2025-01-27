package com.pinguela.thegoldenbook.ui.desktop.controller;

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
import com.pinguela.thegoldenbook.ui.desktop.dialog.DireccionCreateDialog;

public class CodigoPostalUpdateFields extends BaseAction{

	
	private DireccionCreateDialog direccionCreateDialog = null;
	private LocalidadService localidadService = null;
	private ProvinciaService provinciaService = null;
	private PaisService paisService = null;
	
	private static Logger logger = LogManager.getLogger(CodigoPostalUpdateFields.class);
	
	public CodigoPostalUpdateFields(DireccionCreateDialog dialog) {
		initServices();
		setDialog(dialog);
	}
	
	@Override
	public void doAction() {
		
		try {
			int codigoPostal = Integer.valueOf(direccionCreateDialog.getPostalCode());
			Localidad localidad = localidadService.findByCodigoPostal(codigoPostal);
			Provincia provincia = provinciaService.findById(localidad.getProvinciaId());
			Pais pais = paisService.findById(provincia.getPaisId());
			direccionCreateDialog.setLocation(localidad, provincia, pais);
			
		}catch(Exception ex) {
			logger.warn(ex.getMessage(), ex);
		}
		
		
	}
	
	private void setDialog(DireccionCreateDialog dialog) {
		this.direccionCreateDialog = dialog;
	}
	
	private void initServices() {
		localidadService = new LocalidadServiceImpl();
		provinciaService = new ProvinciaServiceImpl();
		paisService = new PaisServiceImpl();
	}

}
