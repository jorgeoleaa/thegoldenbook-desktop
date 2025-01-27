package com.pinguela.thegoldenbook.ui.desktop.view;

import java.awt.BorderLayout;
import java.awt.Window;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.model.ClienteDTO;
import com.pinguela.thegoldenbook.service.ClienteService;
import com.pinguela.thegoldenbook.service.impl.ClienteServiceImpl;
import com.pinguela.thegoldenbook.ui.desktop.controller.AddClienteAction;
import com.pinguela.thegoldenbook.ui.desktop.controller.ClienteSelectionSearchAction;
import com.pinguela.thegoldenbook.ui.desktop.dialog.CreatePedidoDialog;
import com.pinguela.thegoldenbook.ui.desktop.model.ClienteSelectionTableModel;
import com.pinguela.thegoldenbook.ui.desktop.model.LibroSelectionTableModel;
import com.pinguela.thegoldenbook.ui.desktop.renderer.ClienteSelectionTableCellEditor;
import com.pinguela.thegoldenbook.ui.desktop.renderer.ClienteSelectionTableCellRenderer;

public class ClienteSelectionView extends ClienteSearchView {

	private ClienteService clienteService = new ClienteServiceImpl();
	private ClienteDTO cliente = null;
	private CreatePedidoDialog pedidoDialog;
	
	private static Logger logger = LogManager.getLogger();
	
	public ClienteSelectionView(CreatePedidoDialog dialog) {
		
		this.pedidoDialog = dialog;
		postInitialize();
	}
	
	public void postInitialize() {
		
		try {
			
			
			setTableModel(new ClienteSelectionTableModel(new ArrayList<ClienteDTO>()));
			
			setSearchAction(new ClienteSelectionSearchAction(this, "Buscar"));
			
			JPanel buttonPanel = new JPanel();
			add(buttonPanel, BorderLayout.SOUTH);
			buttonPanel.setLayout(new BorderLayout(0, 0));
			
			JButton addButon = new JButton();
			addButon.setAction(new AddClienteAction(pedidoDialog, this, cliente, "Añadir"));
			buttonPanel.add(addButon);
			
		}catch(Exception ex) {
			logger.error(ex.getMessage());
		}
		
		
	}
	
	@Override
	protected void setTableColumnEditor() {
		getResultsTable().getColumnModel().getColumn(8).setCellEditor(new ClienteSelectionTableCellEditor());

	}
	
	@Override
	protected void setTableColumnRenderer() {
		
		getResultsTable().getColumnModel().getColumn(8).setCellRenderer(new ClienteSelectionTableCellRenderer());
	}
	
	public void setSelectedCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}
	
	public List<ClienteDTO> getSelectedClientes(){
		ClienteSelectionTableModel model = (ClienteSelectionTableModel) super.getResultsTable().getModel();
		
		List<ClienteDTO> selectedClientes= new ArrayList<ClienteDTO>();
		for(int i = 0; i<model.getRowCount(); i++) {
			if((Boolean) model.getValueAt(i, 8)) {
				selectedClientes.add((ClienteDTO)model.getValueAt(i, 0));
			}
		}
		return selectedClientes;
	}
	
	 public Window getTopLevelWindow() {
	        return SwingUtilities.getWindowAncestor(this);
	    }

}
