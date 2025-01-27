package com.pinguela.thegoldenbook.ui.desktop.controller;

import javax.swing.Icon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.service.ClienteService;
import com.pinguela.thegoldenbook.service.impl.ClienteServiceImpl;
import com.pinguela.thegoldenbook.ui.desktop.dialog.CreatePedidoDialog;
import com.pinguela.thegoldenbook.ui.desktop.model.ClienteSelectionTableModel;
import com.pinguela.thegoldenbook.ui.desktop.utils.SwingUtils;
import com.pinguela.thegoldenbook.ui.desktop.view.ClienteSelectionView;

public class ShowClienteSelectionAction extends BaseAction{

	private ClienteService clienteService = null;
	private CreatePedidoDialog dialog;
	
	private static Logger logger = LogManager.getLogger(ShowClienteSelectionAction.class);
	
	public ShowClienteSelectionAction (CreatePedidoDialog dialog, String text) {
		super(text);
		setDialog(dialog);
		initServices();
	}
	
	public ShowClienteSelectionAction (CreatePedidoDialog dialog, String text, Icon icon) {
		super(text, icon);
		setDialog(dialog);
		initServices();
	}
	
	@Override
	public void doAction() {
		
		try {
			ClienteSelectionView view = new ClienteSelectionView(dialog);
			view.setTableModel(new ClienteSelectionTableModel(clienteService.findAll(1, Integer.MAX_VALUE).getPage()));
			SwingUtils.setCenterView(view, true, null, null);
			
		}catch(Exception ex) {
			logger.warn(ex.getMessage(), ex);
		}
		
	}
	
	private void initServices() {
		clienteService = new ClienteServiceImpl();
	}
	
	private void setDialog(CreatePedidoDialog dialog){
		this.dialog = dialog;
	}

}
