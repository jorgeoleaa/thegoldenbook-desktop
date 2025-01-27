package com.pinguela.thegoldenbook.ui.desktop;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.pinguela.thegoldenbook.model.EmpleadoDTO;
import com.pinguela.thegoldenbook.ui.desktop.controller.LogOutAction;
import com.pinguela.thegoldenbook.ui.desktop.controller.ShowAutorSearchAction;
import com.pinguela.thegoldenbook.ui.desktop.controller.ShowClienteSearchAction;
import com.pinguela.thegoldenbook.ui.desktop.controller.ShowCreateAutorAction;
import com.pinguela.thegoldenbook.ui.desktop.controller.ShowCreateClienteAction;
import com.pinguela.thegoldenbook.ui.desktop.controller.ShowCreatePedidoAction;
import com.pinguela.thegoldenbook.ui.desktop.controller.ShowEmpleadoCreateAction;
import com.pinguela.thegoldenbook.ui.desktop.controller.ShowEmpleadoSearchAction;
import com.pinguela.thegoldenbook.ui.desktop.controller.ShowLibroCreateAction;
import com.pinguela.thegoldenbook.ui.desktop.controller.ShowLibroSearchAction;
import com.pinguela.thegoldenbook.ui.desktop.controller.ShowPedidoSearchAction;
import com.pinguela.thegoldenbook.ui.desktop.controller.ShowProfileViewAction;
import com.pinguela.thegoldenbook.ui.desktop.dialog.LoginDialog;
import com.pinguela.thegoldenbook.ui.desktop.utils.I18n;
import com.pinguela.thegoldenbook.ui.desktop.utils.SwingUtils;
import com.pinguela.thegoldenbook.ui.desktop.view.LibroSearchView;

public class TheGoldenBookWindow extends JFrame{

	private JFrame frame;
	private static LoginDialog loginDialog;
	private static TheGoldenBookWindow instance;
	private EmpleadoDTO usuarioAutenticado = null;
	private JMenu empleadosMenu;
	private JTabbedPane tabbedPane;
	private JPanel centerPanel;

	private Map<String, Integer> tabCountMap;
	private JPanel menuPanel;
	private JPanel profilePanel;
	private JMenuItem searchLibroMenuItem;
	private JLabel empleadoNameLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new FlatMacDarkLaf());

					TheGoldenBookWindow window = TheGoldenBookWindow.getInstance();
					loginDialog = new LoginDialog();
					window.showLogin();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TheGoldenBookWindow() {
		tabCountMap = new HashMap<String, Integer>();
		initialize();
		postInitialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setTitle("The Golden Book");
		ImageIcon icon = new ImageIcon(getClass().getResource("/icons/logotiendalibrospequeño.jpg"));
		frame.setIconImage(icon.getImage());
		frame.setBounds(100, 100, 1164, 608);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel mainPanel = new JPanel(new BorderLayout());
		frame.getContentPane().add(mainPanel);

		JPanel northPanel = new JPanel();
		mainPanel.add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(new BorderLayout(0, 0));

		menuPanel = new JPanel();
		northPanel.add(menuPanel, BorderLayout.NORTH);
		GridBagLayout gbl_menuPanel = new GridBagLayout();
		gbl_menuPanel.columnWidths = new int[]{690, 251, 0};
		gbl_menuPanel.rowHeights = new int[]{22, 0};
		gbl_menuPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_menuPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		menuPanel.setLayout(gbl_menuPanel);

		JMenuBar menuBar = new JMenuBar();
		GridBagConstraints gbc_menuBar = new GridBagConstraints();
		gbc_menuBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_menuBar.gridwidth = 2;
		gbc_menuBar.anchor = GridBagConstraints.NORTH;
		gbc_menuBar.gridx = 0;
		gbc_menuBar.gridy = 0;
		menuPanel.add(menuBar, gbc_menuBar);

		JMenu libroMenu = new JMenu(I18n.getString("libros"));
		menuBar.add(libroMenu);

		JMenuItem libroSearchMenuItem = new JMenuItem();
		libroSearchMenuItem.setAction(new ShowLibroSearchAction(I18n.getString("buscar.libros")));
		libroMenu.add(libroSearchMenuItem);
		JMenuItem libroInsertMenuItem = new JMenuItem();
		libroInsertMenuItem.setAction(new ShowLibroCreateAction(I18n.getString("añadir.libro")));
		libroMenu.add(libroInsertMenuItem);

		JMenu pedidoMenu = new JMenu("Pedido");
		menuBar.add(pedidoMenu);

		JMenuItem pedidoSearchMenuItem = new JMenuItem();
		pedidoSearchMenuItem.setAction(new ShowPedidoSearchAction("Buscar..."));
		pedidoMenu.add(pedidoSearchMenuItem);

		JMenuItem pedidoCreateMenuItem = new JMenuItem();
		pedidoCreateMenuItem.setAction(new ShowCreatePedidoAction("Crear..."));
		pedidoMenu.add(pedidoCreateMenuItem);

		JMenu clienteMenu = new JMenu("Cliente");
		menuBar.add(clienteMenu);

		JMenuItem buscarClienteMenuItem = new JMenuItem();
		buscarClienteMenuItem.setAction(new ShowClienteSearchAction("Buscar..."));
		clienteMenu.add(buscarClienteMenuItem);

		JMenuItem createClienteMenuItem = new JMenuItem();
		createClienteMenuItem.setAction(new ShowCreateClienteAction("Crear..."));
		clienteMenu.add(createClienteMenuItem);

		JMenu autorMenu = new JMenu("Autor");
		menuBar.add(autorMenu);

		JMenuItem buscarAutorMenuItem = new JMenuItem();
		buscarAutorMenuItem.setAction(new ShowAutorSearchAction("Buscar..."));
		autorMenu.add(buscarAutorMenuItem);

		JMenuItem crearAutorMenuItem = new JMenuItem();
		crearAutorMenuItem.setAction(new ShowCreateAutorAction("Crear..."));
		autorMenu.add(crearAutorMenuItem);
		
		JPanel quickActionsPane = new JPanel();
		northPanel.add(quickActionsPane, BorderLayout.SOUTH);
		quickActionsPane.setLayout(new BorderLayout(0, 0));
		
		JPanel actionsPanel = new JPanel();
		quickActionsPane.add(actionsPanel, BorderLayout.WEST);
		GridBagLayout gbl_actionsPanel = new GridBagLayout();
		gbl_actionsPanel.columnWidths = new int[]{0, 0};
		gbl_actionsPanel.rowHeights = new int[]{0, 0};
		gbl_actionsPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_actionsPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		actionsPanel.setLayout(gbl_actionsPanel);
		
		JMenuBar actionsMenuBar = new JMenuBar();
		GridBagConstraints gbc_actionsMenuBar = new GridBagConstraints();
		gbc_actionsMenuBar.gridx = 0;
		gbc_actionsMenuBar.gridy = 0;
		actionsPanel.add(actionsMenuBar, gbc_actionsMenuBar);
		
		searchLibroMenuItem = new JMenuItem();
		searchLibroMenuItem.setAction(new ShowLibroSearchAction(I18n.getString("buscar.libros"), new ImageIcon(TheGoldenBookWindow.class.getResource("/nuvola/32x32/1371_book_zoom_search_book_search_zoom.png"))));
		actionsMenuBar.add(searchLibroMenuItem);
		
		JMenuItem createLibroMenuItem = new JMenuItem();
		createLibroMenuItem.setAction(new ShowLibroCreateAction("Insertar libro", new ImageIcon(TheGoldenBookWindow.class.getResource("/nuvola/32x32/1819_pencil_pencil.png"))));
		actionsMenuBar.add(createLibroMenuItem);
		
		JMenuItem searchPedidoMenuItem = new JMenuItem();
		searchPedidoMenuItem.setAction(new ShowPedidoSearchAction("Buscar pedido", new ImageIcon(TheGoldenBookWindow.class.getResource("/nuvola/32x32/1877_viewmag_viewmag.png"))));
		actionsMenuBar.add(searchPedidoMenuItem);
		
		JMenuItem clienteInsertMenuItem = new JMenuItem();
		clienteInsertMenuItem.setAction(new ShowCreateClienteAction("Nuevo cliente", new ImageIcon(TheGoldenBookWindow.class.getResource("/nuvola/32x32/1294_doctor_medical_dentist_health_health_medical_dentist_doctor.png"))));
		actionsMenuBar.add(clienteInsertMenuItem);
		
		JMenuItem createPedidoMenuItem = new JMenuItem("New menu item");
		createPedidoMenuItem.setAction(new ShowCreatePedidoAction("Nuevo pedido", new ImageIcon(TheGoldenBookWindow.class.getResource("/nuvola/32x32/1727_add_add.png"))));
		actionsMenuBar.add(createPedidoMenuItem);
		
		
		
		profilePanel = new JPanel();
		quickActionsPane.add(profilePanel, BorderLayout.EAST);
		GridBagLayout gbl_profilePanel = new GridBagLayout();
		gbl_profilePanel.columnWidths = new int[]{0};
		gbl_profilePanel.rowHeights = new int[]{0};
		gbl_profilePanel.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_profilePanel.rowWeights = new double[]{Double.MIN_VALUE};
		profilePanel.setLayout(gbl_profilePanel);
		
		empleadosMenu = new JMenu("Empleados");
		menuBar.add(empleadosMenu);

		JMenuItem buscarEmpleadoMenuItem = new JMenuItem();
		buscarEmpleadoMenuItem.setAction(new ShowEmpleadoSearchAction("Buscar..."));
		empleadosMenu.add(buscarEmpleadoMenuItem);

		JMenuItem crearEmpleadoMenuItem = new JMenuItem();
		crearEmpleadoMenuItem.setAction(new ShowEmpleadoCreateAction("Crear..."));
		empleadosMenu.add(crearEmpleadoMenuItem);

		JMenuBar profileMenuBar = new JMenuBar();
		GridBagConstraints gbc_profileMenuBar = new GridBagConstraints();
		gbc_profileMenuBar.gridx = 0;
		gbc_profileMenuBar.gridy = 0;
		profilePanel.add(profileMenuBar, gbc_profileMenuBar);
		
		empleadoNameLabel = new JLabel("");
		profileMenuBar.add(empleadoNameLabel);

		JMenu userMenu = new JMenu("");
		userMenu.setIcon(new ImageIcon(TheGoldenBookWindow.class.getResource("/nuvola/32x32/1447_man_male_male_man_user_employee_manager_employee_operator_manager_personal_operator_administrator_administrator_personal_user.png")));
		profileMenuBar.add(userMenu);

		JMenuItem profileMenuItem = new JMenuItem();
		profileMenuItem.setAction(new ShowProfileViewAction("Mi perfil"));
		userMenu.add(profileMenuItem);

		JMenuItem logOutMenuItem = new JMenuItem();
		logOutMenuItem.setAction(new LogOutAction("Cerrar Sesión"));
		userMenu.add(logOutMenuItem);
		
		
		JPanel southPanel = new JPanel();
		mainPanel.add(southPanel, BorderLayout.SOUTH);

		JPanel westPanel = new JPanel();
		mainPanel.add(westPanel, BorderLayout.WEST);

		JPanel eastPanel = new JPanel();
		mainPanel.add(eastPanel, BorderLayout.EAST);

		centerPanel = new JPanel();
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		centerPanel.add(tabbedPane);
	}

	private void postInitialize() {

		LibroSearchView defaultView = new LibroSearchView();
		addDefaultTab("Búsqueda de libros", defaultView);
	
		
	}

	public void showLogin() {
		loginDialog.pack();
		SwingUtils.centerOnScreen(loginDialog);
		loginDialog.setModal(true);
		loginDialog.setVisible(true);
	}

	public static TheGoldenBookWindow getInstance() {
		if (instance == null) {
			instance = new TheGoldenBookWindow();
		}
		return instance;
	}

	public void setUsuarioAutenticado(EmpleadoDTO e) {
		this.usuarioAutenticado = e;
		empleadoNameLabel.setText(usuarioAutenticado.getNombre()+" "+usuarioAutenticado.getApellido1());
	}

	public EmpleadoDTO getUsuarioAutenticado() {
		return usuarioAutenticado;
	}

	public void addClosableTab(final String title, final Component panel) {
		int count = tabCountMap.getOrDefault(title, 1);

		String titleWithCount = title + " (" + count + ")";

		for (int i = 0; i < tabbedPane.getTabCount(); i++) {
			String tabTitle = ((JLabel) ((JPanel) tabbedPane.getTabComponentAt(i)).getComponent(0)).getText();
			if (tabTitle.equals(titleWithCount)) {
				tabbedPane.setSelectedIndex(i);
				return;
			}
		}

		JButton closeButton = new JButton(new ImageIcon(TheGoldenBookWindow.class.getResource("/nuvola/16x16/1250_delete_delete.png")));
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = tabbedPane.indexOfComponent(panel);
				if (index != -1) {
					tabbedPane.remove(index);
					updateTabTitles(title);
				}
			}
		});

		JPanel tabTitlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		tabTitlePanel.setOpaque(false);
		JLabel titleLabel = new JLabel(titleWithCount);
		tabTitlePanel.add(titleLabel);
		tabTitlePanel.add(closeButton);
		tabbedPane.addTab(null, panel);

		int lastIndex = tabbedPane.getTabCount() - 1;
		tabbedPane.setTabComponentAt(lastIndex, tabTitlePanel);
		tabbedPane.setSelectedIndex(lastIndex);

		// Incrementar el contador para la siguiente pestaña
		tabCountMap.put(title, count + 1);
	}

	private void updateTabTitles(String title) {
		int count = 1;
		for (int i = 0; i < tabbedPane.getTabCount(); i++) {
			String tabTitle = ((JLabel) ((JPanel) tabbedPane.getTabComponentAt(i)).getComponent(0)).getText();
			if (tabTitle.startsWith(title)) {
				String newTitleWithCount = title + " (" + count + ")";
				((JLabel) ((JPanel) tabbedPane.getTabComponentAt(i)).getComponent(0)).setText(newTitleWithCount);
				count++;
			}
		}

		tabCountMap.put(title, count);
	}

	public void addDefaultTab(final String title, final Component panel) {
		if (!tabCountMap.containsKey(title)) {
			tabCountMap.put(title, 1); // Asegurar que la pestaña por defecto comience con 1
		}
		addClosableTab(title, panel);
	}




	public void setVisible(boolean visible) {
		this.frame.setVisible(visible);
	}

	public JMenu getEmpleadosMenuItem() {
		return empleadosMenu;
	}



}
