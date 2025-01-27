package com.pinguela.thegoldenbook.ui.desktop.controller;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.PinguelaException;
import com.pinguela.thegoldenbook.model.ClienteDTO;
import com.pinguela.thegoldenbook.model.EstadoPedido;
import com.pinguela.thegoldenbook.service.ClienteService;
import com.pinguela.thegoldenbook.service.EstadoPedidoService;
import com.pinguela.thegoldenbook.service.impl.ClienteServiceImpl;
import com.pinguela.thegoldenbook.service.impl.EstadoPedidoServiceImpl;
import com.pinguela.thegoldenbook.ui.desktop.model.LineaPedidoTableModel;
import com.pinguela.thegoldenbook.ui.desktop.utils.SwingUtils;
import com.pinguela.thegoldenbook.ui.desktop.view.PedidoActionsView;
import com.pinguela.thegoldenbook.ui.desktop.view.PedidoDetailView;
import com.pinguela.thegoldenbook.ui.desktop.view.PedidoSearchView;

public class ShowPedidoDetailAction extends BaseAction{
	
	private PedidoActionsView view = null;
	private ClienteService clienteService = null;	
	private EstadoPedidoService estadoPedidoService = null;
	private PedidoSearchView searchView;
	
	private static Logger logger = LogManager.getLogger(ShowPedidoDetailAction.class);

	public ShowPedidoDetailAction(PedidoSearchView searchView, PedidoActionsView view, String text) {
		super(text);
		initServices();
		setView(view);
		setSearchView(searchView);
	}
	
	public ShowPedidoDetailAction(PedidoSearchView searchView, PedidoActionsView view, String text, Icon icon) {
		super(text, icon);
		initServices();
		setView(view);
		setSearchView(searchView);
	}
	
	@Override
	public void doAction() {
		
		try {
			
			PedidoDetailView detailView = new PedidoDetailView(searchView, view.getModel());
			
			List<EstadoPedido> estados = estadoPedidoService.findAll();
			DefaultComboBoxModel<EstadoPedido> estadoComboModel =
					new DefaultComboBoxModel<EstadoPedido>(estados.toArray(new EstadoPedido[estados.size()]));
			detailView.setComboModel(estadoComboModel);
			
			int index = 0;
			for(EstadoPedido estado : estados) {
				if(estado.getId() == view.getModel().getTipoEstadoPedidoId()) {
					detailView.setComboEstadoSelectedValue(index);
					break;
				}
				index++;
			}
			
			detailView.setPedidoDataFields(view.getModel());
			ClienteDTO cliente = clienteService.findById(view.getModel().getClienteId());
			detailView.setClienteDireccionFields(cliente);
			
			detailView.setResultsTableModel(new LineaPedidoTableModel(view.getModel().getLineas()));
			
			SwingUtils.setCenterView(detailView, true, null, null);
			
		}catch(PinguelaException pe) {
			logger.warn(pe.getMessage(), pe);
		}

	}
	
	private void setView(PedidoActionsView view) {
		this.view = view;
	}
	
	private void setSearchView(PedidoSearchView view) {
		this.searchView = view;
	}
	
	private void initServices() {
		clienteService = new ClienteServiceImpl();
		estadoPedidoService = new EstadoPedidoServiceImpl();
	}
	

}
