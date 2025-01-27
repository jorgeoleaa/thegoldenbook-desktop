package com.pinguela.thegoldenbook.ui.desktop.controller;

import javax.swing.Icon;

import com.pinguela.thegoldenbook.ui.desktop.dialog.CreatePedidoDialog;
import com.pinguela.thegoldenbook.ui.desktop.utils.SwingUtils;

public class ShowCreatePedidoAction extends BaseAction{
	
	public ShowCreatePedidoAction(String text) {
		super(text);
	}
	
	public ShowCreatePedidoAction(String text, Icon icon) {
		super(text, icon);
	}

	@Override
	public void doAction() {
		CreatePedidoDialog dialog = new CreatePedidoDialog();
		SwingUtils.centerOnParent(dialog, true);
		dialog.setVisible(true);
		dialog.repaint();
		dialog.revalidate();
		
	}

}
