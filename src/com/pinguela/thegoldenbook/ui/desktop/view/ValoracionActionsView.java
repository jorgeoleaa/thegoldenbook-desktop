package com.pinguela.thegoldenbook.ui.desktop.view;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.pinguela.thegoldenbook.model.ValoracionDTO;
import com.pinguela.thegoldenbook.ui.desktop.controller.DeleteValoracionAction;
import com.pinguela.thegoldenbook.ui.desktop.controller.ShowValoracionDetailAction;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class ValoracionActionsView extends JPanel{
	
	private ValoracionDTO model = null;

	public ValoracionActionsView(LibroDetailView view) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{49, 59, 0};
		gridBagLayout.rowHeights = new int[]{21, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton detailBtn = new JButton();
		detailBtn.setAction(new ShowValoracionDetailAction(this, "Ver"));
		GridBagConstraints gbc_detailBtn = new GridBagConstraints();
		gbc_detailBtn.anchor = GridBagConstraints.NORTHWEST;
		gbc_detailBtn.insets = new Insets(0, 0, 5, 5);
		gbc_detailBtn.gridx = 0;
		gbc_detailBtn.gridy = 0;
		add(detailBtn, gbc_detailBtn);
		
		JButton deleteButton = new JButton();
		deleteButton.setAction(new DeleteValoracionAction(view, this, "Eliminar"));
		GridBagConstraints gbc_editBtn = new GridBagConstraints();
		gbc_editBtn.insets = new Insets(0, 0, 5, 0);
		gbc_editBtn.anchor = GridBagConstraints.NORTHWEST;
		gbc_editBtn.gridx = 1;
		gbc_editBtn.gridy = 0;
		add(deleteButton, gbc_editBtn);

	}
	


	public ValoracionDTO getModel() {
		return model;
	}

	public void setModel(ValoracionDTO model) {
		this.model = model;
	}
}
