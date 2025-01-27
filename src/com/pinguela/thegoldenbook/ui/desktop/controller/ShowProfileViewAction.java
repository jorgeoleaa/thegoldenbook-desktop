package com.pinguela.thegoldenbook.ui.desktop.controller;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.model.DireccionDTO;
import com.pinguela.thegoldenbook.model.EmpleadoDTO;
import com.pinguela.thegoldenbook.model.Localidad;
import com.pinguela.thegoldenbook.model.Pais;
import com.pinguela.thegoldenbook.model.Provincia;
import com.pinguela.thegoldenbook.service.LocalidadService;
import com.pinguela.thegoldenbook.service.PaisService;
import com.pinguela.thegoldenbook.service.ProvinciaService;
import com.pinguela.thegoldenbook.service.impl.LocalidadServiceImpl;
import com.pinguela.thegoldenbook.service.impl.PaisServiceImpl;
import com.pinguela.thegoldenbook.service.impl.ProvinciaServiceImpl;
import com.pinguela.thegoldenbook.ui.desktop.TheGoldenBookWindow;
import com.pinguela.thegoldenbook.ui.desktop.view.ProfileView;

public class ShowProfileViewAction extends BaseAction{
	
	private EmpleadoDTO empleadoAutenticado = null;
	private DireccionDTO direccion = null;
	private LocalidadService localidadService = null;
	private ProvinciaService provinciaService = null;
	private PaisService paisService = null;
	
	private static Logger logger = LogManager.getLogger(ShowProfileViewAction.class);
	
	public ShowProfileViewAction(String text) {
		super(text);
		initServices();
	}
	
	public ShowProfileViewAction(String text, Icon icon) {
		super(text, icon);
		initServices();
	}
	
	@Override
	public void doAction() {
		
		try {
			
			ProfileView pv = new ProfileView();
			
			TheGoldenBookWindow window = TheGoldenBookWindow.getInstance();
			empleadoAutenticado = window.getUsuarioAutenticado();
			direccion = new DireccionDTO();

			List<Localidad> localidades = localidadService.findAll();
			List<Pais> paises = paisService.findAll();
			List<Provincia> provincias = provinciaService.findAll();
			
			DefaultComboBoxModel<Localidad> localidadComboModel = 
					new DefaultComboBoxModel<Localidad>(localidades.toArray(new Localidad[localidades.size()]));
			
			DefaultComboBoxModel<Provincia> provinciaModel =
					new DefaultComboBoxModel<Provincia>(provincias.toArray(new Provincia[provincias.size()]));
			
			DefaultComboBoxModel<Pais> paisComboModel = 
					new DefaultComboBoxModel<Pais>(paises.toArray(new Pais[paises.size()]));
			
			pv.setComboModel(localidadComboModel, provinciaModel, paisComboModel);
			
			int index = 0;
			Localidad localidad = null;
			for(Localidad l : localidades) {
				if(l.getId() == empleadoAutenticado.getDireccion().getLocalidadId()) {
					localidad = l;
					pv.setLocalidadSelectedItem(index);
					pv.setCodigoPostalValue(String.valueOf(l.getCodigoPostal()));
					break;
				}
				index++;
			}
			
			index = 0;
			Provincia provincia = null;
			for(Provincia p : provincias) {
				if(localidad.getProvinciaId() ==  p.getId()) {
					provincia = p;
					pv.setProvinciaSelectedItem(index);
				}
				index++;
			}
			
			index = 0;
			Pais pais = null;
			for(Pais p : paises) {
				if(p.getId() == provincia.getPaisId()) {
					pais = p;
					pv.setPaisSelectedItem(index);
				}
				index++;
			}
			
			TheGoldenBookWindow.getInstance().addClosableTab("Mi perfil", pv);
			
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
