package com.pinguela.thegoldenbook.ui.desktop.controller;

import javax.swing.Icon;

import com.pinguela.thegoldenbook.ui.desktop.dialog.ChangeContraseñaDialog;

public class ShowChangeContraseñaAction extends BaseAction{

	private Long clienteId = null;
	
	public ShowChangeContraseñaAction(Long id, String text) {
		super(text);
		this.clienteId = id;
	}
	
	public ShowChangeContraseñaAction(Long id, String text, Icon icon) {
		super(text, icon);
		this.clienteId = id;
	}
	
	@Override
	public void doAction() {
		ChangeContraseñaDialog dialog = new ChangeContraseñaDialog();
		dialog.setVisible(true);
		dialog.repaint();
		dialog.revalidate();
	}

}
