package com.pinguela.thegoldenbook.ui.desktop.controller;

import javax.swing.Icon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.PinguelaException;
import com.pinguela.thegoldenbook.service.PedidoCriteria;
import com.pinguela.thegoldenbook.service.PedidoService;
import com.pinguela.thegoldenbook.service.impl.PedidoServiceImpl;
import com.pinguela.thegoldenbook.ui.desktop.TheGoldenBookWindow;
import com.pinguela.thegoldenbook.ui.desktop.model.PedidoTableModel;
import com.pinguela.thegoldenbook.ui.desktop.view.PedidoSearchView;

public class ShowPedidoSearchAction extends BaseAction{

	private PedidoService pedidoService = null;
	
	private static Logger logger = LogManager.getLogger(ShowPedidoSearchAction.class);
	
	public ShowPedidoSearchAction(String text) {
		super(text);
		initServices();
	}
	
	public ShowPedidoSearchAction(String text, Icon icon) {
		super(text, icon);
		initServices();
	}
	
	@Override
	public void doAction() {
		
		try {
			
			PedidoSearchView psv = new PedidoSearchView();
			
			PedidoCriteria criteria = new PedidoCriteria();
			PedidoTableModel ptm = new PedidoTableModel(pedidoService.findByCriteria(criteria, 1, Integer.MAX_VALUE).getPage());
			psv.setResultsModel(ptm);
			
			TheGoldenBookWindow.getInstance().addClosableTab("Búsqueda de pedidos", psv);
			
		}catch(PinguelaException pe) {
			logger.warn(pe.getMessage(), pe);
		}
		
	}
	
	private void initServices() {
		pedidoService = new PedidoServiceImpl();
	}

}
