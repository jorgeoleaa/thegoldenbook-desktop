package com.pinguela.thegoldenbook.ui.desktop.controller;

import javax.swing.Icon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.model.Pedido;
import com.pinguela.thegoldenbook.model.Results;
import com.pinguela.thegoldenbook.service.PedidoCriteria;
import com.pinguela.thegoldenbook.service.PedidoService;
import com.pinguela.thegoldenbook.service.impl.PedidoServiceImpl;
import com.pinguela.thegoldenbook.ui.desktop.model.PedidoTableModel;
import com.pinguela.thegoldenbook.ui.desktop.view.PedidoSearchView;

public class SearchPedidoAction extends BaseAction{

	private PedidoSearchView view;
	private PedidoService pedidoService = null;
	private PedidoCriteria criteria = null;
	
	private static Logger logger = LogManager.getLogger(SearchPedidoAction.class);
	
	public SearchPedidoAction(PedidoSearchView view, String text, Icon icon) {
		super(text, icon);
		postInitialize();
		setView(view);
	}
	
	public SearchPedidoAction(PedidoSearchView view, String text) {
		super(text);
		postInitialize();
		setView(view);
	}
	
	
	
	@Override
	public void doAction() {
		
		try {
			
			criteria = new PedidoCriteria();
			view.setCriteriaModel(criteria);
			logger.info("Buscando pedidos por "+criteria);
			Results<Pedido> pedidos = pedidoService.findByCriteria(criteria, 1, Integer.MAX_VALUE);
			view.setResultsModel(new PedidoTableModel(pedidos.getPage()));

		}catch(Exception ex) {
			logger.warn(ex.getMessage(), ex);
		}
		
	}
	
	private void setView(PedidoSearchView view) {
		this.view = view;
	}
	
	private void postInitialize() {
		pedidoService = new PedidoServiceImpl();
	}

}
