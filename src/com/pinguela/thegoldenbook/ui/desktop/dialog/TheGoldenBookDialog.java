package com.pinguela.thegoldenbook.ui.desktop.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.pinguela.thegoldenbook.ui.desktop.view.View;

public class TheGoldenBookDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();


	/**
	 * Create the dialog.
	 */
	public TheGoldenBookDialog(JFrame parent) {
		super(parent);
		init();
	}
	
	public TheGoldenBookDialog(View view) {
		super(getParentFrame(view));
		init();
	}
	
	private static Frame getParentFrame(View view) {
		return (JFrame) javax.swing.SwingUtilities.getWindowAncestor(view);
	}

	public TheGoldenBookDialog(JDialog parent) {
		super(parent);
		init();
	}
	
	public TheGoldenBookDialog() {
		init();
	}
	
	private void init() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		}
	}

}
