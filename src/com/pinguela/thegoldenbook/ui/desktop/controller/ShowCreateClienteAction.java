package com.pinguela.thegoldenbook.ui.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import com.pinguela.thegoldenbook.ui.desktop.dialog.ClienteCreateDialog;
import com.pinguela.thegoldenbook.ui.desktop.utils.SwingUtils;

public class ShowCreateClienteAction extends AbstractAction{
	
	public ShowCreateClienteAction (String text) {
		super(text);
	}
	
	public ShowCreateClienteAction(String text, Icon icon) {
		super(text, icon);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ClienteCreateDialog ccd = new ClienteCreateDialog();
		SwingUtils.centerOnParent(ccd, true);
		ccd.setVisible(true);
		ccd.repaint();
		ccd.revalidate();
		
	}

}
