package com.pinguela.thegoldenbook.ui.desktop.controller;

import javax.swing.Icon;

import com.pinguela.thegoldenbook.ui.desktop.TheGoldenBookWindow;
import com.pinguela.thegoldenbook.ui.desktop.utils.SwingUtils;
import com.pinguela.thegoldenbook.ui.desktop.view.LibroCreateView;

public class ShowLibroCreateAction extends BaseAction{

	public ShowLibroCreateAction(String text) {
		super(text);
	}
	
	public ShowLibroCreateAction(String text, Icon icon) {
		super(text, icon);
	}
	
	@Override
	public void doAction() {
		LibroCreateView lcv = new LibroCreateView();
		SwingUtils.setCenterView(lcv, true, null, null);
		//TheGoldenBookWindow.getInstance().addClosableTab("Creación de libros", lcv);
		
	}

}
