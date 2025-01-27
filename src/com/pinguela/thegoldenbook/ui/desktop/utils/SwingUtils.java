package com.pinguela.thegoldenbook.ui.desktop.utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;
import java.lang.reflect.Field;
import java.util.Set;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.pinguela.thegoldenbook.ui.desktop.dialog.TheGoldenBookDialog;
import com.pinguela.thegoldenbook.ui.desktop.view.View;
import com.toedter.calendar.IDateEditor;
import com.toedter.calendar.JTextFieldDateEditor;

public class SwingUtils {

	private static final Color textFieldForeground = UIManager.getColor("TextField.foreground");

	//	public static final void centerOnScreen(JDialog dialog) {	
	//		dialog.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - dialog.getWidth()/2, (Toolkit.getDefaultToolkit().getScreenSize().height)/2 - dialog.getHeight()/2);
	//	}

	public static void centerOnScreen(Window window) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension windowSize = window.getSize();
		int x = (screenSize.width - windowSize.width) / 2;
		int y = (screenSize.height - windowSize.height) / 2;
		window.setLocation(x, y);
	}


	public static void paintDateText(IDateEditor dateEditor) {
		if (dateEditor instanceof JTextFieldDateEditor) {
			JTextFieldDateEditor textField = (JTextFieldDateEditor) dateEditor;
			textField.setForeground(textFieldForeground);
			textField.addPropertyChangeListener("foreground", event -> {
				if (Color.BLACK.equals(event.getNewValue())) {
					textField.setForeground(textFieldForeground);
				}
			});
		}
	}


	public static void setCenterView(View view, boolean asDialog, JPanel panel, JFrame frame) {
		if (asDialog) {
			JDialog dialog = new TheGoldenBookDialog(view);
			dialog.setModal(true);
			dialog.getContentPane().add(view);
			dialog.pack();
			dialog.setLocationRelativeTo(null); // Esto centra el diálogo en la pantalla
			dialog.setVisible(true);
		} else {
			panel.removeAll();
			panel.add(view, BorderLayout.CENTER);
			panel.repaint();
			panel.revalidate();
		}
	}


	public static void setFullScreen(JFrame frame) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds(0, 0, screenSize.width, screenSize.height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.pack();
		//frame.setUndecorated(true);
		frame.setVisible(true);
	}

	public static String getTextFieldValueOrNull(JTextField textField) {
		String value = textField.getText().trim(); 
		return value.isEmpty() ? null : value;
	}

	public static boolean comprobarCamposVacios(Object objeto, Set<String> camposAComprobar) throws IllegalAccessException {
		for (Field campo : objeto.getClass().getDeclaredFields()) {
			if (camposAComprobar.contains(campo.getName())) {
				campo.setAccessible(true);
				Object valor = campo.get(objeto);
				if (valor instanceof String && valor.equals("")) {
					return true;
				}
			}
		}
		return false;
	}
	public static final void centerOnParent(Window child, boolean absolute) {
		child.pack();
		boolean useChildsOwner = child.getOwner() != null ? ((child.getOwner() instanceof JFrame) || (child.getOwner() instanceof JDialog)) : false;
		final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		final Dimension parentSize = useChildsOwner ? child.getOwner().getSize() : screenSize ;
		final Point parentLocationOnScreen = useChildsOwner ? child.getOwner().getLocationOnScreen() : new Point(0,0) ;
		final Dimension childSize = child.getSize();
		childSize.width = Math.min(childSize.width, screenSize.width);
		childSize.height = Math.min(childSize.height, screenSize.height);
		child.setSize(childSize);        
		int x;
		int y;
		if ((child.getOwner() != null) && child.getOwner().isShowing()) {
			x = (parentSize.width - childSize.width) / 2;
			y = (parentSize.height - childSize.height) / 2;
			x += parentLocationOnScreen.x;
			y += parentLocationOnScreen.y;
		} else {
			x = (screenSize.width - childSize.width) / 2;
			y = (screenSize.height - childSize.height) / 2;
		}
		if (!absolute) {
			x /= 2;
			y /= 2;
		}
		child.setLocation(x, y);
	}
}
