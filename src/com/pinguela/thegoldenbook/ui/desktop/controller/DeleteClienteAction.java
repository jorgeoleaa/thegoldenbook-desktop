package com.pinguela.thegoldenbook.ui.desktop.controller;

import java.util.List;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.model.DireccionDTO;
import com.pinguela.thegoldenbook.model.Pedido;
import com.pinguela.thegoldenbook.model.ValoracionDTO;
import com.pinguela.thegoldenbook.service.ClienteService;
import com.pinguela.thegoldenbook.service.DireccionService;
import com.pinguela.thegoldenbook.service.PedidoCriteria;
import com.pinguela.thegoldenbook.service.PedidoService;
import com.pinguela.thegoldenbook.service.ValoracionService;
import com.pinguela.thegoldenbook.service.impl.ClienteServiceImpl;
import com.pinguela.thegoldenbook.service.impl.DireccionServiceImpl;
import com.pinguela.thegoldenbook.service.impl.PedidoServiceImpl;
import com.pinguela.thegoldenbook.service.impl.ValoracionServiceImpl;
import com.pinguela.thegoldenbook.ui.desktop.model.ClienteTableModel;
import com.pinguela.thegoldenbook.ui.desktop.view.ClienteActionsView;
import com.pinguela.thegoldenbook.ui.desktop.view.ClienteSearchView;

public class DeleteClienteAction extends BaseAction{

	private ClienteActionsView actionsView = null;
	private ClienteService clienteService = null;
	private ClienteSearchView clienteSearchView = null;
	private DireccionService direccionService = null;
	private ValoracionService valoracionService = null;
	private PedidoService pedidoService = null;

	private static Logger logger = LogManager.getLogger(DeleteClienteAction.class);

	public DeleteClienteAction(ClienteActionsView view, ClienteSearchView searchView, String text) {
		super(text);
		initServices();
		setActionsView(view);
		setSearchView(searchView);
	}

	public DeleteClienteAction(ClienteActionsView view, String text, Icon icon) {
		super(text, icon);
		initServices();
		setActionsView(view);
	}

	@Override
	public void doAction() {

		try {

			
			for(DireccionDTO direccion : actionsView.getModel().getDirecciones()) {
				direccionService.delete(direccion.getId());
			}
			
			List<ValoracionDTO> valoraciones = valoracionService.findByCliente(actionsView.getModel().getId(), 1, Integer.MAX_VALUE).getPage();
			for(ValoracionDTO valoracion : valoraciones) {
				valoracionService.delete(valoracion.getClienteId(), valoracion.getLibroId());
			}
			
			PedidoCriteria criteria = new PedidoCriteria();
			criteria.setClienteId(actionsView.getModel().getId());
			List<Pedido> pedidos = pedidoService.findByCriteria(criteria, 1, Integer.MAX_VALUE).getPage();

			for(Pedido pedido : pedidos) {
				pedidoService.delete(pedido.getId());
			}
			
			if(clienteService.delete(actionsView.getModel().getId()) == true) {
				clienteSearchView.setTableModel(new ClienteTableModel(clienteService.findAll(1, Integer.MAX_VALUE).getPage()));
				JOptionPane.showMessageDialog(null, "Cliente eliminado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null, "Error al intentar eliminar el cliente", "Error", JOptionPane.ERROR_MESSAGE);
			}

		}catch(Exception ex) {
			logger.warn(ex.getMessage(), ex);
		}

	}

	private void initServices() {
		pedidoService = new PedidoServiceImpl();
		clienteService = new ClienteServiceImpl();
		direccionService = new DireccionServiceImpl();
		valoracionService = new ValoracionServiceImpl();
	}

	private void setActionsView(ClienteActionsView view) {
		this.actionsView = view;
	}

	private void setSearchView(ClienteSearchView view) {
		this.clienteSearchView = view;
	}

}
