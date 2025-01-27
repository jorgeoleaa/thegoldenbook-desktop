package com.pinguela.thegoldenbook.ui.desktop.controller;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.model.LibroDTO;
import com.pinguela.thegoldenbook.service.FileService;
import com.pinguela.thegoldenbook.service.LibroService;
import com.pinguela.thegoldenbook.service.impl.FileServiceImpl;
import com.pinguela.thegoldenbook.service.impl.LibroServiceImpl;
import com.pinguela.thegoldenbook.ui.desktop.utils.SwingUtils;
import com.pinguela.thegoldenbook.ui.desktop.view.LibroCreateView;

public class CreateLibroAction extends BaseAction{

	private LibroCreateView libroCreateView;
	private LibroService libroService = null;
	private FileService fileService = null;
	private static Logger logger = LogManager.getLogger(CreateLibroAction.class);

	public CreateLibroAction(LibroCreateView view, String text, Icon icon) {
		super(text, icon);
		initServices();
		setView(view);
	}

	public CreateLibroAction(LibroCreateView view, String text) {
		super(text);
		initServices();
		setView(view);
	}

	@Override
	public void doAction() {

		try {

			LibroDTO libro = new LibroDTO();
			libroCreateView.setModel(libro); 
			

			Set<String> libroCheckFields = new LinkedHashSet<String>(Arrays.asList(
					"nombre", "isbn", "sinopsis"
					));
			
			if(SwingUtils.comprobarCamposVacios(libro, libroCheckFields)) {
				JOptionPane.showMessageDialog(libroCreateView, "Tienes que rellenar todos los campos", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE );
			}else {
				Long id = libroService.create(libro);
				if(id != null) {
					libroCreateView.setLibroCreadoModel(libro);
					fileService.uploadImages(libroCreateView.getLibroCreado(), libroCreateView.getFileList());
					JOptionPane.showMessageDialog(null, "Libro creado correctamente", "Creación exitosa", JOptionPane.INFORMATION_MESSAGE);
					libroCreateView.setJButtonState(true);
				}else {
					JOptionPane.showMessageDialog(null, "Fallo en la creación del libro", "Creación fallida", JOptionPane.INFORMATION_MESSAGE);
				} 
			}	

		}catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
		}

	}

	private void setView(LibroCreateView view) {
		this.libroCreateView = view;
	}

	private void initServices() {
		libroService = new LibroServiceImpl();
		fileService = new FileServiceImpl();
	}

}
