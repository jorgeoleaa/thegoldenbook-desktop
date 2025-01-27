package com.pinguela.thegoldenbook.ui.desktop.controller;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.service.PedidoCriteria;
import com.pinguela.thegoldenbook.service.PedidoService;
import com.pinguela.thegoldenbook.service.impl.PedidoServiceImpl;
import com.pinguela.thegoldenbook.ui.desktop.model.PedidoTableModel;
import com.pinguela.thegoldenbook.ui.desktop.view.PedidoDetailView;
import com.pinguela.thegoldenbook.ui.desktop.view.PedidoSearchView;

public class UpdatePedidoAction extends BaseAction{
	
	private PedidoSearchView searchView;
	private PedidoDetailView detailView;
	private PedidoService pedidoService = null;
	
	private static Logger logger = LogManager.getLogger(UpdatePedidoAction.class);
	
	public UpdatePedidoAction(PedidoSearchView view, PedidoDetailView detailView, String text) {
		super(text);
		initServices();
		setPedidoSearchView(view);
		setPedidoDetailView(detailView);
	}
	
	public UpdatePedidoAction(PedidoSearchView view, PedidoDetailView detailView, String text, Icon icon) {
		super(text, icon);
		initServices();
		setPedidoSearchView(view);
		setPedidoDetailView(detailView);
	}
	
	@Override
	public void doAction() {
		
		try {
			
			if(pedidoService.update(detailView.getPedido())) {
				JOptionPane.showMessageDialog(null, "Pedido actualizado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
				PedidoCriteria criteria = searchView.getCriteria();
				PedidoTableModel model = new PedidoTableModel(pedidoService.findByCriteria(searchView.getCriteria(), 1, Integer.MAX_VALUE).getPage());
				searchView.setResultsModel(model);
			}else {
				JOptionPane.showMessageDialog(null, "Error al actualizar el pedido", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}catch(Exception ex) {
			logger.warn(ex.getMessage(), ex);
		}
		
	}
	
	private void setPedidoSearchView(PedidoSearchView view) {
		this.searchView = view;
	}
	
	private void setPedidoDetailView(PedidoDetailView view) {
		this.detailView = view;
	}
	
	private void initServices() {
		pedidoService = new PedidoServiceImpl();
	}

}
