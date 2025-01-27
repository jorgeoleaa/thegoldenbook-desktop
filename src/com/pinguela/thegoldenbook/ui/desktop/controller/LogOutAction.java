package com.pinguela.thegoldenbook.ui.desktop.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.pinguela.thegoldenbook.ui.desktop.TheGoldenBookWindow;

public class LogOutAction extends AbstractAction{
	
	private TheGoldenBookWindow window = null;
	
	public LogOutAction(String text) {
		super(text);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		window = TheGoldenBookWindow.getInstance();
		window.setVisible(false);
		window.setUsuarioAutenticado(null);
		window.showLogin();
	}

}
