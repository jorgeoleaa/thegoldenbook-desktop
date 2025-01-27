package com.pinguela.thegoldenbook.ui.desktop.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.SwingUtilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.model.EmpleadoDTO;
import com.pinguela.thegoldenbook.service.EmpleadoService;
import com.pinguela.thegoldenbook.service.impl.EmpleadoServiceImpl;
import com.pinguela.thegoldenbook.ui.desktop.TheGoldenBookWindow;
import com.pinguela.thegoldenbook.ui.desktop.dialog.LoginDialog;
import com.pinguela.thegoldenbook.ui.desktop.utils.SwingUtils;
import com.pinguela.thegoldenbook.ui.desktop.view.LibroSearchView;

public class LoginAction extends AbstractAction{

	private LoginDialog loginDialog = null;
	private EmpleadoService empleadoService = null;

	private Logger logger = LogManager.getLogger(LoginAction.class);


	public LoginAction (LoginDialog dialog, String text) {
		super(text);
		setDialog(dialog);
		initServices();
	}

	public LoginAction(LoginDialog dialog, String text, Icon icon) {
		super(text, icon);
		setDialog(dialog);
		initServices();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		try {

			int contador = 0;
			EmpleadoDTO empleado = empleadoService.autenticar(loginDialog.getIdValue(), loginDialog.getPasswordValue());

			if(empleado != null) {
				loginDialog.setVisible(false);
				TheGoldenBookWindow window = TheGoldenBookWindow.getInstance();
				window.setUsuarioAutenticado(empleado);
				if(empleado.getTipo_empleado_id() != 1) {
					window.getEmpleadosMenuItem().setVisible(false);
				}
				LibroSearchView view = new LibroSearchView();
				window.pack();
				SwingUtils.centerOnScreen(window);
				window.setVisible(true);
			}else {
				loginDialog.getErrorLabel().setOpaque(true);
				loginDialog.getErrorLabel().setBackground(Color.RED);
				loginDialog.getErrorLabel().setVisible(true);
				logger.warn("Usuario o contraseña incorrectos: "+loginDialog.getIdValue());
				contador++;
				if(contador == 3) {
					logger.warn("El usuario "+loginDialog.getIdValue()+" ha alcanzado el número máximo de intentos.");

					System.exit(0);
				}
			}

		}catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
		}


	}

	private void setDialog(LoginDialog dialog) {
		this.loginDialog = dialog;
	}

	private void initServices() {
		empleadoService = new EmpleadoServiceImpl();
	}

}
