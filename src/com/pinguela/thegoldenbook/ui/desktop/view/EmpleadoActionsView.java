package com.pinguela.thegoldenbook.ui.desktop.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.pinguela.thegoldenbook.model.EmpleadoDTO;
import com.pinguela.thegoldenbook.ui.desktop.controller.DeleteEmpleadoAction;
import com.pinguela.thegoldenbook.ui.desktop.controller.ShowEmpleadoDetailAction;

public class EmpleadoActionsView extends JPanel {
	
	private EmpleadoDTO model = null;

	/**
	 * Create the panel.
	 */
	public EmpleadoActionsView(EmpleadoSearchView view) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton verButton = new JButton();
		verButton.setAction(new ShowEmpleadoDetailAction(this ,"Ver"));
		GridBagConstraints gbc_verButton = new GridBagConstraints();
		gbc_verButton.insets = new Insets(0, 0, 0, 5);
		gbc_verButton.gridx = 0;
		gbc_verButton.gridy = 0;
		add(verButton, gbc_verButton);
		
		JButton deleteButton = new JButton();
		deleteButton.setAction(new DeleteEmpleadoAction(view, this, "Eliminar"));
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 0;
		add(deleteButton, gbc_btnNewButton_1);

	}
	
	public void setModel(EmpleadoDTO empleado) {
		this.model = empleado;
	}
	
	public EmpleadoDTO getModel() {
		return model;
	}
	
	public EmpleadoActionsView (EmpleadoDTO model) {
		setModel(model);
	}
	
	

}
