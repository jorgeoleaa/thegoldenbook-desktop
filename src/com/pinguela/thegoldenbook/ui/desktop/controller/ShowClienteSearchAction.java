package com.pinguela.thegoldenbook.ui.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.service.ClienteService;
import com.pinguela.thegoldenbook.service.impl.ClienteServiceImpl;
import com.pinguela.thegoldenbook.ui.desktop.TheGoldenBookWindow;
import com.pinguela.thegoldenbook.ui.desktop.model.ClienteTableModel;
import com.pinguela.thegoldenbook.ui.desktop.view.ClienteSearchView;

public class ShowClienteSearchAction extends AbstractAction {
    
    private ClienteSearchView clienteSearchView;
    private ClienteService clienteService;
    
    private static Logger logger = LogManager.getLogger(ShowClienteSearchAction.class);
    
    public ShowClienteSearchAction(String text) {
    	super(text);
    	initServices();
    }
    
    public ShowClienteSearchAction(String text, Icon icon) {
        super(text, icon);
        initServices();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	try {
    		clienteSearchView = new ClienteSearchView();
    		ClienteTableModel ctm = new ClienteTableModel(clienteService.findAll(1, Integer.MAX_VALUE).getPage());
    		clienteSearchView.setTableModel(ctm);
            TheGoldenBookWindow.getInstance().addClosableTab("Buscar clientes", clienteSearchView);
            
    	}catch(Exception ex) {
    		logger.error(ex.getMessage(), ex);
    	}
    	
        
    }

    
    private void initServices() {
    	clienteService = new ClienteServiceImpl();
    }
}
