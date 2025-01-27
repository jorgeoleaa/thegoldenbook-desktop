package com.pinguela.thegoldenbook.ui.desktop.controller;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.pinguela.thegoldenbook.model.LibroDTO;
import com.pinguela.thegoldenbook.service.FileService;
import com.pinguela.thegoldenbook.service.impl.FileServiceImpl;
import com.pinguela.thegoldenbook.ui.desktop.view.LibroCreateView;

public class ShowUploadImageAction extends BaseAction{
	
	private FileService fileService = null;
	private LibroCreateView libroCreateView = null;
	
	public ShowUploadImageAction(LibroCreateView view, String text) {
		super(text);
		this.libroCreateView = view;
		initServices();
		
	}

	public ShowUploadImageAction(LibroCreateView view, String text, Icon icon) {
		super(text, icon);
		initServices();
		this.libroCreateView = view;
	}

	@Override
	public void doAction() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setMultiSelectionEnabled(true);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagenes", "jpg", "jpeg", "png");
		fileChooser.setFileFilter(filter);
		
		int returnValue = fileChooser.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File[] selectedFiles = fileChooser.getSelectedFiles();
			List<File> fileList = Arrays.asList(selectedFiles);
			libroCreateView.setFileList(fileList);
			libroCreateView.showImagesInLabels(fileList, libroCreateView.getLabels());
			
		}
	}
	
	private void initServices() {
		fileService = new FileServiceImpl();
	}

}
