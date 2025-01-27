package com.pinguela.thegoldenbook.ui.desktop.controller;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.model.EstadoPedido;
import com.pinguela.thegoldenbook.model.Localidad;
import com.pinguela.thegoldenbook.model.Pais;
import com.pinguela.thegoldenbook.model.Provincia;
import com.pinguela.thegoldenbook.model.TipoEmpleado;
import com.pinguela.thegoldenbook.service.LocalidadService;
import com.pinguela.thegoldenbook.service.PaisService;
import com.pinguela.thegoldenbook.service.ProvinciaService;
import com.pinguela.thegoldenbook.service.TipoEmpleadoService;
import com.pinguela.thegoldenbook.service.impl.LocalidadServiceImpl;
import com.pinguela.thegoldenbook.service.impl.PaisServiceImpl;
import com.pinguela.thegoldenbook.service.impl.ProvinciaServiceImpl;
import com.pinguela.thegoldenbook.service.impl.TipoEmpleadoServiceImpl;
import com.pinguela.thegoldenbook.ui.desktop.dialog.EmpleadoDetailDialog;
import com.pinguela.thegoldenbook.ui.desktop.view.EmpleadoActionsView;

public class ShowEmpleadoDetailAction extends BaseAction{

	private EmpleadoActionsView view = null;
	private LocalidadService localidadService = null;
	private ProvinciaService provinciaService = null;
	private PaisService paisService = null;
	private TipoEmpleadoService tipoEmpleadoService = null;
	
	private static Logger logger = LogManager.getLogger(ShowEmpleadoDetailAction.class);
	
	public ShowEmpleadoDetailAction(EmpleadoActionsView view, String text) {
		super(text);
		initServices();
		this.view = view;
	}
	
	public ShowEmpleadoDetailAction(EmpleadoActionsView view, String text, Icon icon) {
		super(text, icon);
		initServices();
		this.view = view;
	}
	
	@Override
	public void doAction() {
		
		try {
			
			EmpleadoDetailDialog dialog = new EmpleadoDetailDialog(view.getModel());
			
			List<Localidad> localidades = localidadService.findAll();
			List<Provincia> provincias = provinciaService.findAll();
			List<Pais> paises = paisService.findAll();
			List<TipoEmpleado> tipos = tipoEmpleadoService.findAll();
			
			DefaultComboBoxModel<Localidad> localidadComboModel =
					new DefaultComboBoxModel<Localidad>(localidades.toArray(new Localidad[localidades.size()]));
			
			DefaultComboBoxModel<Provincia> provinciaComboModel = 
					new DefaultComboBoxModel<Provincia>(provincias.toArray(new Provincia[provincias.size()]));
			
			DefaultComboBoxModel<Pais> paisComboModel = 
					new DefaultComboBoxModel<Pais>(paises.toArray(new Pais[paises.size()]));
			
			DefaultComboBoxModel<TipoEmpleado> tiposComboModel = 
					new DefaultComboBoxModel<TipoEmpleado>(tipos.toArray(new TipoEmpleado[tipos.size()]));
			
			dialog.setComboBoxModel(localidadComboModel, provinciaComboModel, paisComboModel, tiposComboModel);
			
			
			int index = 0;
			for(Localidad localidad : localidades) {
				if(localidad.getId() == view.getModel().getDireccion().getLocalidadId()) {
					dialog.setLocalidadIndex(index);
					break;
				}
				index++;
			}

			index = 0;
			for(Provincia provincia: provincias) {
				if(provincia.getId() == view.getModel().getDireccion().getProvinciaId()) {
					dialog.setProvinciaIndex(index);
					break;
				}
				index++;
			}
			
			index = 0;
			for(Pais pais : paises) {
				if(pais.getId() == view.getModel().getDireccion().getPaisId()) {
					dialog.setPaisIndex(index);
					break;
				}
				index++;
			}
			
			index = 0;
			for(TipoEmpleado tipo: tipos) {
				if(tipo.getId() == view.getModel().getTipo_empleado_id()) {
					dialog.setTipoEmpleadoIndex(index);
					break;
				}
				index++;
			}

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
		tipoEmpleadoService = new TipoEmpleadoServiceImpl();
	}

}
