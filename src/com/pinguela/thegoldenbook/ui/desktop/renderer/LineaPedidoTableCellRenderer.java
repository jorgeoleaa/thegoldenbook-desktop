package com.pinguela.thegoldenbook.ui.desktop.renderer;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.model.Autor;
import com.pinguela.thegoldenbook.model.LibroDTO;
import com.pinguela.thegoldenbook.model.LineaPedido;
import com.pinguela.thegoldenbook.service.LibroService;
import com.pinguela.thegoldenbook.service.impl.LibroServiceImpl;

public class LineaPedidoTableCellRenderer extends DefaultTableCellRenderer{

	private LibroService libroService = null;

	private static Logger logger = LogManager.getLogger();

	public LineaPedidoTableCellRenderer() {
		libroService = new LibroServiceImpl();
	}

	public Component getTableCellRendererComponent(
			JTable table,
			Object value,
			boolean isSelected,
			boolean hasFocus,
			int row,
			int column) {

		Component c = this;
		LineaPedido lineaPedido = (LineaPedido) value;

		try {
			LibroDTO libro = libroService.findByLibro(lineaPedido.getLibroId());

			if(column == 0) {
				setText(lineaPedido.getId().toString());
			}else if(column == 1){
				setText(lineaPedido.getPrecio().toString());
			}else if(column == 2) {
				setText(lineaPedido.getLibroId().toString());
			}else if(column == 3) {
				setText(libro.getNombre());
			}else if(column ==4) {
				for(Autor autor : libro.getAutores()) {
					setText(autor.getNombre() + " "+autor.getApellido1());
				}	
			}else if(column == 5) {
				setText(lineaPedido.getPedidoId().toString());
			}


		}catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
		}

		return c;

	}
}
