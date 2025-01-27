package com.pinguela.thegoldenbook.ui.desktop.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public abstract class BaseAction 
	extends AbstractAction
	implements MouseListener,
			 	KeyListener,
			 	ItemListener,
			 	ChangeListener,
			 	//WindowListener, tambien se podria
			 	PropertyChangeListener {

	public BaseAction() {		
	}
	public BaseAction(String name) {
		super(name);
	}
	public BaseAction(String name, Icon icon) {
		super(name, icon);
	}
	
	/**
	 * Método común de ejecición de todas nuestras acciones.
	 */
	public abstract void doAction();
		
	
	
	// Adapter del API de listeners a nuestras acciones.
	// Nos quedamos solo con los métodos princiales de cada Listener.
	// Si la accion concreta necesitase del evento específico 
	// (por los los datos propios del evento), entonces
	// debe sobreescribir el método de recepción de ese evento
	// y en él, si quiere, invocar al doAction()
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		doAction();
	}
	
	@Override
	public void mouseClicked(MouseEvent evt) {
		doAction();
	}
	
	@Override
	public void keyPressed(KeyEvent evt) {
		doAction();
	}
	
	@Override
    public void itemStateChanged(ItemEvent e) {
		doAction();
	}
	
	@Override 
	public void stateChanged(ChangeEvent e) {
		doAction();
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		doAction();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	
	@Override
	public void keyReleased(KeyEvent e) {}
	
	
	@Override
	public void mousePressed(MouseEvent e) {}
	
	@Override
	public void mouseReleased(MouseEvent e) {}
	
	@Override
	public void mouseEntered(MouseEvent e) {}
	
	@Override
	public void mouseExited(MouseEvent e) {}
	
	    
	
}
