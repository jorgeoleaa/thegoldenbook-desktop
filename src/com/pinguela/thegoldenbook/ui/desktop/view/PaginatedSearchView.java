package com.pinguela.thegoldenbook.ui.desktop.view;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.model.Results;
import com.pinguela.thegoldenbook.ui.desktop.controller.PagedSearchAction;

public abstract class PaginatedSearchView<E> extends SearchView{

	public static final int PAGE_SIZE = 10;
	private Results<E> results;
	
	private JButton inicioBtn;
	private JButton anteriorBtn;
	private JButton siguienteBtn;
	private JButton finalBtn;
	
	private int currentPosition = 1;
	private JPanel paginationPanel;
	private JLabel paginationLbl;
	
	private static Logger logger = LogManager.getLogger(PaginatedSearchView.class);

	public PaginatedSearchView() {
		initialize();
	}
	
	public PaginatedSearchView(PagedSearchAction<E> start, PagedSearchAction<E> previous, PagedSearchAction<E> next, PagedSearchAction<E> end) {
		this();
		
		setStart(start);
		setPrevious(previous);
		setNext(next);
		setEnd(end);
		
		initialize();
	}
	
	private void initialize() {
		paginationPanel = new JPanel();
		add(paginationPanel, BorderLayout.SOUTH);
		
		inicioBtn = new JButton("Inicio");
		inicioBtn.setEnabled(false);
		paginationPanel.add(inicioBtn);
		
		anteriorBtn = new JButton("Anterior");
		anteriorBtn.setEnabled(false);
		paginationPanel.add(anteriorBtn);
		
		paginationLbl = new JLabel("-");
		paginationPanel.add(paginationLbl);
		
		siguienteBtn = new JButton("siguiente");
		siguienteBtn.setEnabled(false);
		paginationPanel.add(siguienteBtn);
		
		finalBtn = new JButton("final");
		finalBtn.setEnabled(false);
		paginationPanel.add(finalBtn);
		
	}
	
	public void updateView() {
		
		paginationLbl.setText(currentPosition+" - "+results.getTotal());
		
		inicioBtn.setEnabled(getCurrentPosition()>PAGE_SIZE);
		anteriorBtn.setEnabled(inicioBtn.isEnabled());
		siguienteBtn.setEnabled(getCurrentPosition()<results.getTotal()-PAGE_SIZE+1);
		finalBtn.setEnabled(siguienteBtn.isEnabled());
		
		

	}
	
	public int getCurrentPosition() {
		return currentPosition;
	}	
	
	public void setCurrentPosition(int currentPagingPosition) {
		this.currentPosition = currentPagingPosition;
	}
	
	public Results<E> getResults() {
		return this.results;
	}
	
	public void setResults(Results<E> results) {
		this.results = results;
	}
	
	
	

	public void setStart(PagedSearchAction<E> start) {
		this.inicioBtn.setAction(start);
		this.inicioBtn.setEnabled(false);
	
	}

	

	public void setPrevious(PagedSearchAction<E> previous) {
		this.anteriorBtn.setAction(previous);
	}

	

	public void setNext(PagedSearchAction<E> next) {
		this.siguienteBtn.setAction(next);
	}



	public void setEnd(PagedSearchAction<E> end) {
		this.finalBtn.setAction(end);;
	}



}
