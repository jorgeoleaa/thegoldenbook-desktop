package com.pinguela.thegoldenbook.ui.desktop.controller;

import javax.swing.Icon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.service.LibroService;
import com.pinguela.thegoldenbook.service.impl.LibroServiceImpl;
import com.pinguela.thegoldenbook.ui.desktop.dialog.CreatePedidoDialog;
import com.pinguela.thegoldenbook.ui.desktop.utils.SwingUtils;
import com.pinguela.thegoldenbook.ui.desktop.view.LibroSelectionView;

public class ShowLibroSelectionAction extends BaseAction{

	private LibroService libroService = null;
	private CreatePedidoDialog dialog;

	private static Logger logger = LogManager.getLogger();

	public ShowLibroSelectionAction(CreatePedidoDialog dialog, String text) {
		super(text);
		this.dialog = dialog;
		initServices();
	}

	public ShowLibroSelectionAction(CreatePedidoDialog dialog, String text, Icon icon) {
		super(text, icon);
		this.dialog = dialog;
		initServices();
	}

	@Override
	public void doAction() {

		LibroSelectionView view = new LibroSelectionView(dialog);
		SwingUtils.setCenterView(view, true, null, null);

	}

	private void initServices() {
		libroService = new LibroServiceImpl();
	}
}
