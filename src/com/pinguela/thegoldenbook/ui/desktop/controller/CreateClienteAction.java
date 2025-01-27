package com.pinguela.thegoldenbook.ui.desktop.controller;

import java.awt.event.ActionEvent;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.model.ClienteDTO;
import com.pinguela.thegoldenbook.service.ClienteService;
import com.pinguela.thegoldenbook.service.impl.ClienteServiceImpl;
import com.pinguela.thegoldenbook.ui.desktop.dialog.ClienteCreateDialog;
import com.pinguela.thegoldenbook.ui.desktop.utils.SwingUtils;

public class CreateClienteAction extends AbstractAction {

    private ClienteCreateDialog clienteCreateDialog;
    private ClienteService clienteService;
    private ClienteDTO cliente = new ClienteDTO();

    private static Logger logger = LogManager.getLogger(CreateClienteAction.class);

    public CreateClienteAction(ClienteCreateDialog dialog, String text) {
        super(text);
        initServices();
        setView(dialog);
    }

    public CreateClienteAction(ClienteCreateDialog dialog, String text, Icon icon) {
        super(text, icon);
        initServices();
        setView(dialog);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            clienteCreateDialog.setClienteData(cliente);
     
            Set<String> camposAComprobar = new LinkedHashSet<>(Arrays.asList(
                "nombre", "apellido1", "dniNie", "nickname", "telefono", "email"
            ));

            if (SwingUtils.comprobarCamposVacios(cliente, camposAComprobar)) {
                JOptionPane.showMessageDialog(clienteCreateDialog, "Tienes que rellenar obligatoriamente todos los campos excepto el segundo apellido", "Error", JOptionPane.INFORMATION_MESSAGE);
            } else {
                Long id = clienteService.registrar(cliente);
                if (id != null) {
                    clienteCreateDialog.setClienteCreadoId(id);
                    JOptionPane.showMessageDialog(null, "El cliente se registró correctamente", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
                    clienteCreateDialog.getDirectionButton().setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al registrar el cliente", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
        }
    }

    private void initServices() {
        clienteService = new ClienteServiceImpl();
    }

    private void setView(ClienteCreateDialog cdialog) {
        this.clienteCreateDialog = cdialog;
    }


}
