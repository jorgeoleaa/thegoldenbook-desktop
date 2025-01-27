package com.pinguela.thegoldenbook.ui.desktop.controller;

import java.awt.Window;
import java.util.List;

import com.pinguela.thegoldenbook.model.LibroDTO;
import com.pinguela.thegoldenbook.ui.desktop.dialog.CreatePedidoDialog;
import com.pinguela.thegoldenbook.ui.desktop.view.LibroSelectionView;

public class AñadirLibrosSeleccionadosAction extends BaseAction{

	private CreatePedidoDialog dialog;
	private LibroSelectionView view;
	
	public AñadirLibrosSeleccionadosAction (CreatePedidoDialog dialog, LibroSelectionView view, String text) {
		super(text);
		this.dialog = dialog;
		this.view = view;
	}
	
	@Override
	public void doAction() {
		Double precio = 0.0d;
		List<LibroDTO> selectedLibros = view.getSelectedBook();
		for(LibroDTO libro: selectedLibros) {
			precio += libro.getPrecio();
		}
		dialog.setPrecioTotal(precio);
		dialog.setSelectedBooksModel(selectedLibros);
		dialog.repaint();
		dialog.revalidate();
		
		 Window window = view.getTopLevelWindow();
	        if (window != null) {
	            window.dispose();
	        }
		
	}

}
