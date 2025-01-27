package com.pinguela.thegoldenbook.ui.desktop.view;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Window;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import com.pinguela.thegoldenbook.model.LibroDTO;
import com.pinguela.thegoldenbook.ui.desktop.TheGoldenBookWindow;
import com.pinguela.thegoldenbook.ui.desktop.controller.AñadirLibrosSeleccionadosAction;
import com.pinguela.thegoldenbook.ui.desktop.controller.LibroPagedSearchAction;
import com.pinguela.thegoldenbook.ui.desktop.controller.LibroPagedSelectionAction;
import com.pinguela.thegoldenbook.ui.desktop.controller.PagedSearchAction;
import com.pinguela.thegoldenbook.ui.desktop.dialog.CreatePedidoDialog;
import com.pinguela.thegoldenbook.ui.desktop.model.LibroSelectionTableModel;
import com.pinguela.thegoldenbook.ui.desktop.renderer.SelectionCellEditor;
import com.pinguela.thegoldenbook.ui.desktop.renderer.SelectionCellRenderer;

public class LibroSelectionView extends LibroSearchView {

	private CreatePedidoDialog pedidoDialog;
	
	public LibroSelectionView() {
		postIntialize();
	}
	
	public LibroSelectionView(CreatePedidoDialog dialog) {
		this.pedidoDialog = dialog;
		postIntialize();
	}
	
	private void postIntialize() {
		
		setTableModel(new LibroSelectionTableModel(new ArrayList<LibroDTO>()));
		
		setSearchAction(new LibroPagedSelectionAction(PagedSearchAction.START, this, "Buscar"));
		
		setStart(new LibroPagedSelectionAction(PagedSearchAction.START, this, null , new ImageIcon(TheGoldenBookWindow.class.getResource("/nuvola/32x32/1826_player_rev_player_rev.png"))));
		setPrevious(new LibroPagedSelectionAction(PagedSearchAction.PREVIOUS, this, null ,new ImageIcon(TheGoldenBookWindow.class.getResource("/nuvola/32x32/1687_1leftarrow_1leftarrow.png"))));
		setNext(new LibroPagedSelectionAction(PagedSearchAction.NEXT, this, null, new ImageIcon(TheGoldenBookWindow.class.getResource("/nuvola/32x32/1814_next_arrow_left_left_next_arrow.png"))));
		setEnd(new LibroPagedSelectionAction(PagedSearchAction.END, this, null ,new ImageIcon(TheGoldenBookWindow.class.getResource("/nuvola/32x32/1823_fwd_fwd_player_player.png"))));
		
		JButton addBookButton = new JButton();
		addBookButton.setAction(new AñadirLibrosSeleccionadosAction(pedidoDialog, this, "Agregar"));
		GridBagConstraints gbc_addBookButton = new GridBagConstraints();
		gbc_addBookButton.insets = new Insets(0, 0, 0, 5);
		gbc_addBookButton.gridx = 12;
		gbc_addBookButton.gridy = 4;
		getSearchFieldsPanel().add(addBookButton, gbc_addBookButton);
		
	}
	
	@Override
	protected void handleNameKeyReleased() {
	    String nombre = getNombreTextField().getText().trim();
	    if (nombre.length() > 1) {
	        LibroPagedSearchAction action = new LibroPagedSelectionAction(PagedSearchAction.START, LibroSelectionView.this);
	        action.doAction();
	    }
	}

	@Override
	protected void handleIsbnKeyReleased() {
		String isbn = getIsbnTextField().getText().trim();
		if (isbn.length() > 2) {
			LibroPagedSearchAction action = new LibroPagedSelectionAction(PagedSearchAction.START, LibroSelectionView.this);
			action.doAction();
		}
	}
	
	@Override
	protected void handleDesdeSliderChange() {
	    getPrecioDesdeValueSlider().setText(String.valueOf(getPrecioDesdeSlider().getValue()));
	    LibroPagedSelectionAction action = new LibroPagedSelectionAction(PagedSearchAction.START, LibroSelectionView.this);
	    action.doAction();
	  
	}
	
	@Override
	protected void handleHastaSliderChange() {
	    getPrecioHastaValueSlider().setText(String.valueOf(getPrecioHastaSlider().getValue()));
	    LibroPagedSelectionAction action = new LibroPagedSelectionAction(PagedSearchAction.START, LibroSelectionView.this);
	    action.doAction();
	  
	}
	
	protected void setSearchActionComponent() {
		 LibroPagedSelectionAction action = new LibroPagedSelectionAction(PagedSearchAction.START, LibroSelectionView.this);
	     action.doAction();
	}
	
	@Override
	protected void setTableColumnEditor() {
		getResultsTable().getColumnModel().getColumn(6).setCellEditor(new SelectionCellEditor());

	}
	
	@Override
	protected void setTableColumnRenderer() {
		
		getResultsTable().getColumnModel().getColumn(6).setCellRenderer(new SelectionCellRenderer());
	}
	
	
	public List<LibroDTO> getSelectedBook(){
		LibroSelectionTableModel model = (LibroSelectionTableModel) super.getResultsTable().getModel();
		
		List<LibroDTO> selectedBooks = new ArrayList<LibroDTO>();
		for(int i = 0; i<model.getRowCount(); i++) {
			if((Boolean) model.getValueAt(i, 6)) {
				selectedBooks.add((LibroDTO)model.getValueAt(i, 0));
			}
		}
		pedidoDialog.setSelectedLibros(selectedBooks);
		return selectedBooks;
	}
	
	
    public Window getTopLevelWindow() {
        return SwingUtilities.getWindowAncestor(this);
    }

}
