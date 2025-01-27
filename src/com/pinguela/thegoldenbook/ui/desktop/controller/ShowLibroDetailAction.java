package com.pinguela.thegoldenbook.ui.desktop.controller;

import java.awt.Dimension;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.model.ClasificacionEdad;
import com.pinguela.thegoldenbook.model.Formato;
import com.pinguela.thegoldenbook.model.GeneroLiterario;
import com.pinguela.thegoldenbook.model.Idioma;
import com.pinguela.thegoldenbook.model.LibroDTO;
import com.pinguela.thegoldenbook.model.Results;
import com.pinguela.thegoldenbook.model.ValoracionDTO;
import com.pinguela.thegoldenbook.service.ClasificacionEdadService;
import com.pinguela.thegoldenbook.service.FormatoService;
import com.pinguela.thegoldenbook.service.GeneroLiterarioService;
import com.pinguela.thegoldenbook.service.IdiomaService;
import com.pinguela.thegoldenbook.service.ValoracionService;
import com.pinguela.thegoldenbook.service.impl.ClasificacionEdadServiceImpl;
import com.pinguela.thegoldenbook.service.impl.FormatoServiceImpl;
import com.pinguela.thegoldenbook.service.impl.GeneroLiterarioServiceImpl;
import com.pinguela.thegoldenbook.service.impl.IdiomaServiceImpl;
import com.pinguela.thegoldenbook.service.impl.ValoracionServiceImpl;
import com.pinguela.thegoldenbook.ui.desktop.model.ValoracionTableModel;
import com.pinguela.thegoldenbook.ui.desktop.utils.SwingUtils;
import com.pinguela.thegoldenbook.ui.desktop.view.LibroSearchActionsView;
import com.pinguela.thegoldenbook.ui.desktop.view.LibroDetailView;
import com.pinguela.thegoldenbook.ui.desktop.view.LibroSearchView;

public class ShowLibroDetailAction extends BaseAction{
	
	private LibroSearchActionsView libroActionsView;
	private LibroSearchView searchView;
	private ValoracionService valoracionService = null;
	private GeneroLiterarioService generoLiterarioService = null;
	private IdiomaService idiomaService = null;
	private FormatoService formatoService = null;
	private ClasificacionEdadService clasificacionService = null;
	
	private static Logger logger = LogManager.getLogger(ShowLibroDetailAction.class);
	
	public ShowLibroDetailAction(LibroSearchView searchView, LibroSearchActionsView view, String text ) {
		super(text);
		initServices();
		setView(view);
		setSearchView(searchView);
	}
	
	@Override
	public void doAction() {
					
				try {
					
					LibroDTO libro = libroActionsView.getModel();
					
					List<GeneroLiterario> generos = generoLiterarioService.findAll();
					List<ClasificacionEdad> clasificaciones = clasificacionService.findAll();
					List<Idioma> idiomas = idiomaService.findAll();
					List<Formato> formatos = formatoService.findAll();
					
					LibroDetailView ldv = new LibroDetailView(searchView,libro);
					Results<ValoracionDTO> valoraciones = valoracionService.findByLibro(libro.getId(), 1, Integer.MAX_VALUE);
					ldv.setValoracionModel(new ValoracionTableModel(valoraciones.getPage()));
					
					
//					Double media = valoracionService.calcularMedia(valoraciones.getPage());
//					int mediaInt = media.intValue();
//					ldv.setProgressBarValue(mediaInt);
					
					DefaultComboBoxModel<GeneroLiterario> generoComboModel =
							new DefaultComboBoxModel<GeneroLiterario>(generos.toArray(new GeneroLiterario[generos.size()]));
					
					DefaultComboBoxModel<Formato> formatoComboModel =
							new DefaultComboBoxModel<Formato>(formatos.toArray(new Formato[formatos.size()]));
					
					DefaultComboBoxModel<ClasificacionEdad> clasificacionComboModel =
							new DefaultComboBoxModel<ClasificacionEdad>(clasificaciones.toArray(new ClasificacionEdad[clasificaciones.size()]));
					
					DefaultComboBoxModel<Idioma> idiomaComboModel = 
							new DefaultComboBoxModel<Idioma>(idiomas.toArray(new Idioma[idiomas.size()]));
					
					ldv.setComboModel(clasificacionComboModel, formatoComboModel, idiomaComboModel, generoComboModel);
					
					int index = 0;
					for(GeneroLiterario genero : generos) {
						if(genero.getId() == libro.getGeneroLiterarioId()) {
							ldv.setGeneroLiterarioComboBoxSelectedIndex(index);
							break;
						}
						index++;
					}
						
					index = 0;
					for(ClasificacionEdad clasificacion : clasificaciones) {
						if(clasificacion.getId() == libro.getClasificacionEdadId()) {
							ldv.setClasificacionEdadComboBoxSelectedIndex(index);;
							break;
						}
						index++;
					}
					
					index = 0;
					for(Idioma idioma : idiomas) {
						if (idioma.getId() == libro.getIdiomaId()) {
							ldv.setIdiomaComboBoxSelectedItem(index);;
						}
						index++;
					}
					
					
					
					index = 0;
					for (Formato formato : formatos) {
						if(formato.getId() == libro.getFormatoId()) {
							ldv.setFormatoComboBoxSelectedItem(index);;
						}
						index++;
					}
					ldv.setPreferredSize(new Dimension(1500, 750));
					SwingUtils.setCenterView(ldv, true, null, null);
					
					
				}catch(Exception ex) {
					logger.error(ex.getMessage(), ex);
				}
			
				
			
		
	}
	
	private void initServices() {
		valoracionService = new ValoracionServiceImpl();
		generoLiterarioService = new GeneroLiterarioServiceImpl();
		idiomaService = new IdiomaServiceImpl();
		formatoService = new FormatoServiceImpl();
		clasificacionService = new ClasificacionEdadServiceImpl();
	}
	
	private void setSearchView(LibroSearchView view) {
		this.searchView = view;
	}
	
	private void setView(LibroSearchActionsView view) {
		this.libroActionsView = view;
	}

}
