package com.pinguela.thegoldenbook.ui.desktop.controller;

import java.awt.Dialog.ModalityType;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.SwingUtilities;

import com.pinguela.thegoldenbook.ui.desktop.dialog.ValoracionDetailDialog;
import com.pinguela.thegoldenbook.ui.desktop.utils.SwingUtils;
import com.pinguela.thegoldenbook.ui.desktop.view.ValoracionActionsView;

public class ShowValoracionDetailAction extends BaseAction {
    
    private ValoracionActionsView actionsView;
    
    public ShowValoracionDetailAction(ValoracionActionsView view, String text) {
        super(text);
        setView(view);
    }
    
    public ShowValoracionDetailAction(String text, Icon icon) {
        super(text, icon);
    }
    
    @Override
    public void doAction() {
    	
        ValoracionDetailDialog valoracionDetailView = new ValoracionDetailDialog(actionsView.getModel());
      
        SwingUtils.setCenterView(valoracionDetailView, true, null, null);
    }
    
    private void setView(ValoracionActionsView view) {
        this.actionsView = view;
    }

}
