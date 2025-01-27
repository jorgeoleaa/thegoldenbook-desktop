package com.pinguela.thegoldenbook.ui.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import com.pinguela.thegoldenbook.ui.desktop.dialog.AutorCreateDialog;

public class ShowCreateAutorAction extends AbstractAction{
	
	public ShowCreateAutorAction(String text) {
		super(text);
	}
	
	public ShowCreateAutorAction(String title, Icon icon) {
		super(title, icon);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		AutorCreateDialog autorCreateDialog = new AutorCreateDialog();
		autorCreateDialog.setVisible(true);
		autorCreateDialog.repaint();
		autorCreateDialog.revalidate();
		
	}
	

}
