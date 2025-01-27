package com.pinguela.thegoldenbook.ui.desktop.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.model.EstadoPedido;
import com.pinguela.thegoldenbook.model.LibroDTO;
import com.pinguela.thegoldenbook.model.LineaPedido;
import com.pinguela.thegoldenbook.model.Pedido;
import com.pinguela.thegoldenbook.service.PedidoService;
import com.pinguela.thegoldenbook.service.impl.PedidoServiceImpl;
import com.pinguela.thegoldenbook.ui.desktop.dialog.CreatePedidoDialog;

public class CreatePedidoAction extends BaseAction{

	private CreatePedidoDialog dialog;
	private PedidoService pedidoService = null;

	private static Logger logger = LogManager.getLogger();

	public CreatePedidoAction (CreatePedidoDialog dialog, String text) {
		super(text);
		this.dialog = dialog;
		initServices();
	}

	@Override
	public void doAction() {

		try {
			List<LineaPedido> lineas = new ArrayList<LineaPedido>();	
			Double precio = 0.0d;

			Pedido pedido = new Pedido();
			pedido.setClienteId(dialog.getClienteSeleccionado().getId());
			LocalDate localDate = LocalDate.now();
			Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			pedido.setFechaRealizacion(date);
			pedido.setTipoEstadoPedidoId(((EstadoPedido)dialog.getSelectedEstado()).getId());
			for(LibroDTO libro : dialog.getSelectedLibros()) {
				LineaPedido linea = new LineaPedido();
				linea.setLibroId(libro.getId());
				linea.setPrecio(libro.getPrecio());
				linea.setUnidades(1);
				lineas.add(linea);
				precio += libro.getPrecio();
			}
			pedido.setLineas(lineas);
			pedido.setPrecio(precio);
			

			if(pedidoService.create(pedido) != null) {
				JOptionPane.showMessageDialog(dialog, "Pedido creado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
				lineas.clear();
				
			}



		}catch(Exception ex) {
			logger.error(ex.getMessage(), ex);

		}
	}

	private void initServices() {
		pedidoService = new PedidoServiceImpl();
	}

}
