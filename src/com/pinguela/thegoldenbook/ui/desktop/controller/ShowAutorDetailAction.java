package com.pinguela.thegoldenbook.ui.desktop.controller;

import javax.swing.Icon;

import com.pinguela.thegoldenbook.ui.desktop.dialog.AutorDetailDialog;
import com.pinguela.thegoldenbook.ui.desktop.view.AutorActionsView;
import com.pinguela.thegoldenbook.ui.desktop.view.AutorSearchView;

public class ShowAutorDetailAction extends BaseAction{
	
	private AutorActionsView autorActionsView;
	private AutorSearchView autorSearchView;

	public ShowAutorDetailAction(AutorSearchView searchView, AutorActionsView view, String text, Icon icon) {
		super(text, icon);
		setView(view);
		setAutorSearchView(searchView);
	}
	
	public ShowAutorDetailAction(AutorSearchView searchView, AutorActionsView view, String text) {
		super(text);
		setView(view);
		setAutorSearchView(searchView);
	}
	
	@Override
	public void doAction() {
		AutorDetailDialog view = new AutorDetailDialog(autorSearchView, autorActionsView.getModel());
		view.setVisible(true);
		view.repaint();
		view.revalidate();
		//TheGoldenBookWindow.getInstance().addClosableTab("Detalle de autor", view);
		
		
	}
	
	private void setView(AutorActionsView view) {
		this.autorActionsView = view;
	}
	
	private void setAutorSearchView(AutorSearchView view) {
		this.autorSearchView = view;
	}

}
