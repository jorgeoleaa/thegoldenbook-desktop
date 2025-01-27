package com.pinguela.thegoldenbook.ui.desktop.controller;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.dao.DataException;
import com.pinguela.thegoldenbook.model.ClasificacionEdad;
import com.pinguela.thegoldenbook.model.GeneroLiterario;
import com.pinguela.thegoldenbook.model.Idioma;
import com.pinguela.thegoldenbook.service.ClasificacionEdadService;
import com.pinguela.thegoldenbook.service.GeneroLiterarioService;
import com.pinguela.thegoldenbook.service.IdiomaService;
import com.pinguela.thegoldenbook.service.LibroCriteria;
import com.pinguela.thegoldenbook.service.LibroService;
import com.pinguela.thegoldenbook.service.impl.ClasificacionEdadServiceImpl;
import com.pinguela.thegoldenbook.service.impl.GeneroLiterarioServiceImpl;
import com.pinguela.thegoldenbook.service.impl.IdiomaServiceImpl;
import com.pinguela.thegoldenbook.service.impl.LibroServiceImpl;
import com.pinguela.thegoldenbook.ui.desktop.TheGoldenBookWindow;
import com.pinguela.thegoldenbook.ui.desktop.model.LibroSearchTableModel;
import com.pinguela.thegoldenbook.ui.desktop.view.LibroSearchView;
import com.pinguela.thegoldenbook.ui.desktop.view.PaginatedSearchView;

public class ShowLibroSearchAction extends BaseAction{


	public ShowLibroSearchAction(String text) {
		super(text);
	}

	public ShowLibroSearchAction(String text, Icon icon) {
		super(text, icon);
	}

	@Override
	public void doAction() {

			LibroSearchView lsv = new LibroSearchView();
			
			lsv.setStart(new LibroPagedSearchAction(PagedSearchAction.START, lsv, null , new ImageIcon(TheGoldenBookWindow.class.getResource("/nuvola/32x32/1826_player_rev_player_rev.png"))));
			lsv.setPrevious(new LibroPagedSearchAction(PagedSearchAction.PREVIOUS, lsv, null ,new ImageIcon(TheGoldenBookWindow.class.getResource("/nuvola/32x32/1687_1leftarrow_1leftarrow.png"))));
			lsv.setNext(new LibroPagedSearchAction(PagedSearchAction.NEXT, lsv, null, new ImageIcon(TheGoldenBookWindow.class.getResource("/nuvola/32x32/1814_next_arrow_left_left_next_arrow.png"))));
			lsv.setEnd(new LibroPagedSearchAction(PagedSearchAction.END, lsv, null ,new ImageIcon(TheGoldenBookWindow.class.getResource("/nuvola/32x32/1823_fwd_fwd_player_player.png"))));
			
			TheGoldenBookWindow.getInstance().addClosableTab("Búsqueda de Libros", lsv);
	}

}
