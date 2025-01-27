package com.pinguela.thegoldenbook.ui.desktop.controller;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JDialog;

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
import com.pinguela.thegoldenbook.ui.desktop.dialog.ClienteCreateDialog;
import com.pinguela.thegoldenbook.ui.desktop.dialog.ClienteDetailDialog;
import com.pinguela.thegoldenbook.ui.desktop.dialog.DireccionCreateDialog;

public class ShowCreateDireccionAction extends BaseAction{

	private LocalidadService localidadService = null;
	private ProvinciaService provinciaService = null;
	private PaisService paisService = null;
	private JDialog clienteDialog = null;

	private static Logger logger = LogManager.getLogger(ShowCreateDireccionAction.class);

	public ShowCreateDireccionAction(JDialog dialog, String text) {
		super(text);
		setDialog(dialog);
		initServices();

	}  

	public ShowCreateDireccionAction(JDialog dialog, String text, Icon icon) {
		super(text, icon);
		setDialog(dialog);
		initServices();
	}

	@Override
	public void doAction() {

		try {

	        DireccionCreateDialog dialog = null;

	        if(clienteDialog instanceof ClienteCreateDialog) {
	            dialog = new DireccionCreateDialog(((ClienteCreateDialog) clienteDialog).getClienteCreadoId());
	            List<Localidad> localidades = localidadService.findAll();
	            DefaultComboBoxModel<Localidad> localidadComboModel =
	                    new DefaultComboBoxModel<>(localidades.toArray(new Localidad[localidades.size()]));

	            List<Provincia> provincias = provinciaService.findAll();
	            DefaultComboBoxModel<Provincia> provinciaComboModel =
	                    new DefaultComboBoxModel<>(provincias.toArray(new Provincia[localidades.size()]));

	            List<Pais> paises = paisService.findAll();
	            DefaultComboBoxModel<Pais> paisesComboModel = 
	                    new DefaultComboBoxModel<>(paises.toArray(new Pais[paises.size()]));

	            dialog.setModel(paisesComboModel, provinciaComboModel, localidadComboModel);

	        } else if (clienteDialog instanceof ClienteDetailDialog) {
	            dialog = new DireccionCreateDialog(((ClienteDetailDialog) clienteDialog).getClienteId());
	            
	            List<Localidad> localidades = localidadService.findAll();
	            DefaultComboBoxModel<Localidad> localidadComboModel =
	                    new DefaultComboBoxModel<>(localidades.toArray(new Localidad[localidades.size()]));

	            List<Provincia> provincias = provinciaService.findAll();
	            DefaultComboBoxModel<Provincia> provinciaComboModel =
	                    new DefaultComboBoxModel<>(provincias.toArray(new Provincia[localidades.size()]));

	            List<Pais> paises = paisService.findAll();
	            DefaultComboBoxModel<Pais> paisesComboModel = 
	                    new DefaultComboBoxModel<>(paises.toArray(new Pais[paises.size()]));
	            
	            dialog.setModel(paisesComboModel, provinciaComboModel, localidadComboModel);
	        }

	        dialog.setVisible(true);
	        dialog.repaint();
	        dialog.revalidate();

	    }catch(Exception ex) {
	        logger.error(ex.getMessage(), ex);
	    }
	}

	private void initServices() {
		localidadService = new LocalidadServiceImpl();
		provinciaService = new ProvinciaServiceImpl();
		paisService = new PaisServiceImpl();
	}

	private void setDialog(JDialog dialog) {
		this.clienteDialog = dialog;
	}

}
