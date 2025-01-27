package com.pinguela.thegoldenbook.ui.desktop.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.model.DireccionDTO;
import com.pinguela.thegoldenbook.service.DireccionService;
import com.pinguela.thegoldenbook.service.impl.DireccionServiceImpl;
import com.pinguela.thegoldenbook.ui.desktop.dialog.DireccionCreateDialog;
import com.pinguela.thegoldenbook.ui.desktop.utils.SwingUtils;

public class CreateDireccionAction extends BaseAction{

	private DireccionCreateDialog direccionDialog = null;
	private DireccionService direccionService = null;

	private static Logger logger = LogManager.getLogger(CreateDireccionAction.class);

	public CreateDireccionAction(DireccionCreateDialog dialog, String text) {
		super(text);
		initServices();
		setDialgo(dialog);
	}

	public CreateDireccionAction(DireccionCreateDialog dialog, String text, Icon icon, Long id) {
		super(text, icon);
		initServices();
		setDialgo(dialog);
	}

	@Override
	public void doAction() {

		try {

			DireccionDTO direccion = new DireccionDTO();
			direccionDialog.setDireccion(direccion);

			Set<String> fieldsToCheck = new LinkedHashSet<String>(Arrays.asList(
					"nombreVia", "dirVia", "codigoPostal"
					));

			if(SwingUtils.comprobarCamposVacios(direccion, fieldsToCheck)) {
				direccionDialog.getErrorLabel().setVisible(true);
				direccionDialog.getErrorLabel().setOpaque(true);
				direccionDialog.getErrorLabel().setBackground(Color.RED);
				Timer timer = new Timer(2500, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						
						direccionDialog.getErrorLabel().setVisible(false);
					}
				});
				timer.setRepeats(false);
				timer.start();
			}else {
				if(direccionService.create(direccion) != null) {
					JOptionPane.showMessageDialog(null, "Dirección creada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "Fallo al crear la dirección", "Error", JOptionPane.INFORMATION_MESSAGE);
				}
			}

			

		}catch(Exception ex) {
			logger.warn(ex.getMessage(), ex);
		}
	}

	private void setDialgo(DireccionCreateDialog dialog) {
		this.direccionDialog = dialog;
	}

	private void initServices() {
		direccionService = new DireccionServiceImpl();
	}

}
