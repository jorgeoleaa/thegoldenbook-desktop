package com.pinguela.thegoldenbook.ui.desktop.controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.model.ClienteDTO;
import com.pinguela.thegoldenbook.service.ClienteService;
import com.pinguela.thegoldenbook.service.impl.ClienteServiceImpl;
import com.pinguela.thegoldenbook.ui.desktop.model.ClienteTableModel;
import com.pinguela.thegoldenbook.ui.desktop.view.ClienteSearchView;

public class ClienteSearchAction extends BaseAction{

	private static Logger logger = LogManager.getLogger();
	private ClienteSearchView view = null;
	private ClienteService clienteService = null;
	private ClienteDTO cliente = new ClienteDTO();
	private List<ClienteDTO> clientes = new ArrayList<ClienteDTO>();

	public ClienteSearchAction(ClienteSearchView view, String text) {
		super(text);
		setView(view);
		initServices();
	}

	public ClienteSearchAction(ClienteSearchView view, String text, Icon icon) {
		super(text, icon);
		setView(view);
		initServices();
	}

	@Override
	public void doAction() {
		
		view.setTableModel(new ClienteTableModel(new ArrayList<ClienteDTO>()));

		try {
			int value = view.getSelectedSearchMethod();
			if(!view.getTextFieldValue().equals("")) {
				if(value == 1) {
					clientes.clear();
					cliente = clienteService.findById(Long.valueOf(view.getTextFieldValue()));
					clientes.add(cliente);
					view.setTableModel(new ClienteTableModel(clientes));
				}else if (value == 2) {
					clientes.clear();
					cliente = clienteService.findByNick(view.getTextFieldValue());
					clientes.add(cliente);
					view.setTableModel(new ClienteTableModel(clientes));
				}else {
					clientes.clear();
					cliente = clienteService.findByEmail(view.getTextFieldValue());
					clientes.add(cliente);
					view.setTableModel(new ClienteTableModel(clientes));
				}
			}else {
//				JOptionPane.showMessageDialog(null, "Tienes que introducir algún parametro en el buscador", "Error", JOptionPane.ERROR_MESSAGE);
				List<ClienteDTO> clientesTotal = clienteService.findAll(1, Integer.MAX_VALUE).getPage();
				view.setTableModel(new ClienteTableModel(clientesTotal));
			}
		
		}catch(Exception ex) {
			
			logger.error(ex.getMessage(), ex);

		}
	}


	private void setView(ClienteSearchView view) {
		this.view = view;
	}

	private void initServices() {
		clienteService = new ClienteServiceImpl();
	}

}
