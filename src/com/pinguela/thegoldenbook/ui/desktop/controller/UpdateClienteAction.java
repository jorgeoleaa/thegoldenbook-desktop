package com.pinguela.thegoldenbook.ui.desktop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.model.ClienteDTO;
import com.pinguela.thegoldenbook.service.ClienteService;
import com.pinguela.thegoldenbook.service.impl.ClienteServiceImpl;
import com.pinguela.thegoldenbook.ui.desktop.dialog.ClienteDetailDialog;
import com.pinguela.thegoldenbook.ui.desktop.model.ClienteTableModel;
import com.pinguela.thegoldenbook.ui.desktop.view.ClienteSearchView;

public class UpdateClienteAction extends BaseAction{

	private ClienteDetailDialog dialog = null;
	private ClienteService clienteService = null;
	
	private static Logger logger = LogManager.getLogger(UpdateClienteAction.class);
	
	public UpdateClienteAction(ClienteDetailDialog dialog, String text) {
		super(text);
		initServices();
		setDialog(dialog);
	}
	
	public UpdateClienteAction(ClienteDetailDialog dialog, String text, Icon icon) {
		super(text, icon);
		initServices();
		setDialog(dialog);
	}
	
	@Override
	public void doAction() {
		
		try {
			List<ClienteDTO> clientes = null;
			
			if(clienteService.update(dialog.getUpdateModel())) {
				ClienteSearchView view = ClienteSearchView.getInstance();
				if(view.getTextFieldValue().isEmpty()) {
					clientes = clienteService.findAll(1, Integer.MAX_VALUE).getPage();
					view.setTableModel(new ClienteTableModel(clientes));
				}else {
					clientes = new ArrayList<ClienteDTO>();
					if(view.getSelectedSearchMethod() == 1) {
						clientes.clear();
						clientes.add(clienteService.findById(Long.valueOf(view.getTextFieldValue())));
					}else if (view .getSelectedSearchMethod() == 2) {
						clientes.clear();
						clientes.add(clienteService.findByNick(view.getTextFieldValue()));
					}else if(view.getSelectedSearchMethod() == 3) {
						clientes.clear();
						clientes.add(clienteService.findByEmail(view.getTextFieldValue()));
					}
				}
				JOptionPane.showMessageDialog(null, "Cliente actualizado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null, "Error al intentar actualizar el cliente", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		
	}
	
	private void initServices() {
		clienteService = new ClienteServiceImpl();
	}
	
	private void setDialog(ClienteDetailDialog dialog) {
		this.dialog = dialog;
	}

}
