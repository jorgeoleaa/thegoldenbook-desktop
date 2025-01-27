package com.pinguela.thegoldenbook.ui.desktop.controller;

import java.util.List;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.PinguelaException;
import com.pinguela.thegoldenbook.model.Pedido;
import com.pinguela.thegoldenbook.service.PedidoService;
import com.pinguela.thegoldenbook.service.impl.PedidoServiceImpl;
import com.pinguela.thegoldenbook.ui.desktop.model.PedidoTableModel;
import com.pinguela.thegoldenbook.ui.desktop.view.PedidoActionsView;
import com.pinguela.thegoldenbook.ui.desktop.view.PedidoSearchView;

public class DeletePedidoAction extends BaseAction{
	
	private PedidoSearchView view;
	private PedidoActionsView actionsView;
	private PedidoService pedidoService = null;
	
	private static Logger logger = LogManager.getLogger(DeletePedidoAction.class);
	
	public DeletePedidoAction(PedidoSearchView view, PedidoActionsView actionsView, String text) {
		super(text);
		setView(view);
		initServices();
		setActionsView(actionsView);
	}
	
	public DeletePedidoAction(PedidoSearchView view, PedidoActionsView actionsView, String text, Icon icon) {
		super(text, icon);
		setView(view);
		initServices();
		setActionsView(actionsView);
	}
	
	@Override
	public void doAction() {
		
		try {
			
			if(pedidoService.delete(actionsView.getModel().getId())) {
				List<Pedido> pedidos = pedidoService.findByCriteria(view.getCriteria(), 1, Integer.MAX_VALUE).getPage();
				PedidoTableModel model = new PedidoTableModel(pedidos);
				view.setResultsModel(model);
				JOptionPane.showMessageDialog(view, "Pedido eliminado correctamente", "ÉXITO", JOptionPane.INFORMATION_MESSAGE);
			}else {
		
				JOptionPane.showMessageDialog(view, "Error al intentar eliminar el pedido", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			
		}catch(PinguelaException pe) {
			logger.warn(pe.getMessage(), pe);
		}
	
	}
	
	private void setView(PedidoSearchView view) {
		this.view = view;
	}
	
	private void setActionsView(PedidoActionsView view) {
		this.actionsView = view;
	}
	
	private void initServices() {
		pedidoService = new PedidoServiceImpl();
	}

}
