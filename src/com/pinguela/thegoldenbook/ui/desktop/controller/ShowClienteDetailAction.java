package com.pinguela.thegoldenbook.ui.desktop.controller;

import javax.swing.Icon;

import com.pinguela.thegoldenbook.ui.desktop.dialog.ClienteDetailDialog;
import com.pinguela.thegoldenbook.ui.desktop.view.ClienteActionsView;

public class ShowClienteDetailAction extends BaseAction{
	
	private ClienteActionsView clienteActionsView;
	
	public ShowClienteDetailAction(ClienteActionsView view, String text) {
		super(text);
		setView(view);
	}
	
	public ShowClienteDetailAction(ClienteActionsView view, String text, Icon icon) {
		super(text, icon);
		setView(view);
	}
	
	
	@Override
	public void doAction() {
		
		ClienteDetailDialog dialog = new ClienteDetailDialog(clienteActionsView.getModel());
		dialog.setVisible(true);
		dialog.repaint();
		dialog.revalidate();
		
	}
	
	
	private void setView(ClienteActionsView view) {
		this.clienteActionsView = view;
	}
}
