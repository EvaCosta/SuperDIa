package br.com.superdia.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import br.com.superdia.SuperDiaCaixa;
import br.com.superdia.controller.Constants;
import br.com.superdia.controller.Messages;
import br.com.superdia.controller.Singleton;
import br.com.superdia.es.PopupMessage;
import br.com.superdia.modelo.ItemCarrinho;
import br.com.superdia.modelo.Produto;
import br.com.superdia.sessionbeans.IServicosCliente;

public class UIRegisterPurchase extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable jtProducts;
	private JTextField tfSelected;
	private JTable jtCart;
	private IServicosCliente serivcesClientBean;
	private JSpinner spinnerAmount;
	private SpinnerModel spinnerAmountModel;
	private DefaultTableModel dtmProduct = new DefaultTableModel();
	private DefaultTableModel dtmCart = new DefaultTableModel();
	private Produto productSelected;
	
	public UIRegisterPurchase(Component parent) {
		tryLoadEJB();
		initTableProducts(dtmProduct, Constants.COLUMNS_PRODUCTS_AVAILABLE_TABLE);
		initTableCart(dtmCart, Constants.COLUMNS_PRODUCTS_PURCHASE_TABLE);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Singleton.closeSession();
			}
		});
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(UILogin.class.getResource(Constants.FAVICON)));
		setTitle(Constants.REGISTER_PURCHASE_TITLE);
		setSize(new Dimension(727, 795));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu(Constants.MENU);
		menuBar.add(mnNewMenu);
		
		JMenuItem menuLogout = new JMenuItem(Constants.EXIT);
		Image scaledIcon = new ImageIcon(SuperDiaCaixa.class.getResource(Constants.EXIT_IMAGE)).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		menuLogout.setIcon(new ImageIcon(scaledIcon));

		menuLogout.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				signOut();
			}
		});
		
		mnNewMenu.add(menuLogout);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), Constants.AVAILABLE_PRODUCTS, TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 111, 691, 236);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 18, 671, 208);
		panel.add(scrollPane);
		
		jtProducts = new JTable();
		jtProducts = new JTable(dtmProduct) {
			private static final long serialVersionUID = 1L;
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				long id = Long.valueOf(jtProducts.getModel().getValueAt(row, 0).toString());
				setProductSelected(serivcesClientBean.buscaProdutoPorId(id));
				return false;
			}
		};
		jtProducts.getTableHeader().setReorderingAllowed(false);
		jtProducts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(jtProducts);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), Constants.PRODUCTS_ON_CART, TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 429, 691, 268);
		contentPanel.add(panel_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 18, 671, 208);
		panel_1.add(scrollPane_1);
		
		jtCart = new JTable();
		jtCart = new JTable(dtmCart) {
			private static final long serialVersionUID = 1L;
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				return false;
			}
		};
		jtCart.getTableHeader().setReorderingAllowed(false);
		jtCart.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(jtCart);
		
		JButton btnRemoveCart = new JButton(Constants.REMOVE_CART);
		btnRemoveCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeProduct();
			}
		});
		btnRemoveCart.setBounds(548, 229, 133, 28);
		panel_1.add(btnRemoveCart);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, Constants.SELECTED_PRODUCT, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 358, 691, 60);
		contentPanel.add(panel_2);
		panel_2.setLayout(null);
		
		tfSelected = new JTextField();
		tfSelected.setEditable(false);
		tfSelected.setBounds(10, 21, 474, 28);
		panel_2.add(tfSelected);
		tfSelected.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("X");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(485, 28, 25, 14);
		panel_2.add(lblNewLabel);
		
		spinnerAmountModel = new SpinnerNumberModel(0, 0, 0, 1);
		spinnerAmount = new JSpinner(spinnerAmountModel);
		spinnerAmount.setBounds(506, 21, 70, 28);
		panel_2.add(spinnerAmount);
		
		JButton btnAdd = new JButton(Constants.ADD_CART);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addToCart();
			}
		});
		btnAdd.setBounds(592, 21, 89, 28);
		panel_2.add(btnAdd);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(10, 11, 684, 89);
		contentPanel.add(lblLogo);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
	
		JButton btnSave = new JButton(Constants.SAVE_PURCHASE);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerPurchase();
			}
		});
		buttonPane.add(btnSave);
		getRootPane().setDefaultButton(btnSave);

		JButton btnCancel = new JButton(Constants.BACK_DASHBOARD);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel(); 	
			}
		});
		buttonPane.add(btnCancel);
		
		URL url = SuperDiaCaixa.class.getResource(Constants.LOGO_ALT);
		ImageIcon icon = new ImageIcon(url);
		lblLogo.setIcon(icon);
		
		setModal(true);
		setLocationRelativeTo(parent);
		setResizable(false);
		setVisible(true);
	}

	private void registerPurchase() {
		serivcesClientBean.finalizaCompra(Singleton.getUser());
		updateTable();
	}

	private void removeProduct() {
		int index = jtCart.getSelectedRow();
		if(index != -1) {
			List<ItemCarrinho> cartProducts = serivcesClientBean.listaItensCarrinho();
			serivcesClientBean.removeItemCarrinho(cartProducts.get(index));
		}
		initTableCart(dtmCart, Constants.COLUMNS_PRODUCTS_PURCHASE_TABLE);
	}
	
	private void cancel() {
		int confirmation = PopupMessage.questionConfirmationDialog(Messages.CONFIRMATION_CANCEL_PURCHASE, Constants.REGISTER_PURCHASE_TITLE);
		if(confirmation == 0) {
			List<ItemCarrinho> itemsCart = serivcesClientBean.listaItensCarrinho(); 
			for (int i = 0; i < itemsCart.size(); i++) serivcesClientBean.removeItemCarrinho(itemsCart.get(i));
		}
		
		initTableCart(dtmCart, Constants.COLUMNS_PRODUCTS_PURCHASE_TABLE);
	}

	private void addToCart() {
		ItemCarrinho itemCart = new ItemCarrinho();
		int amount = Integer.parseInt(spinnerAmount.getValue().toString().substring(0, spinnerAmount.getValue().toString().lastIndexOf('.')).replace(".", ""));
		if(amount == 0) return;
		
		itemCart.setProduto(productSelected);
		itemCart.setQuantidade(amount);
		
		serivcesClientBean.adicionaItemCarrinho(itemCart);
		
		initTableCart(dtmCart, Constants.COLUMNS_PRODUCTS_PURCHASE_TABLE);
	}
	
	private void signOut() {
		int confirmation = PopupMessage.questionConfirmationDialog(Messages.CONFIRMATION_EXIT, Constants.APPLICATION_NAME);
		if(confirmation == 0) {
			dispose();
			Singleton.closeSession();
			new UILogin();
		}
	}

	private void setProductSelected(Produto produto) {
		productSelected = produto;
		tfSelected.setText(productSelected.getNome());
		spinnerAmountModel = new SpinnerNumberModel(0, 0, produto.getQuantidadeEstoque().doubleValue(), 1);
		spinnerAmount.setModel(spinnerAmountModel);
	}

	private void initTableProducts(DefaultTableModel dtm, String[] columns) {
		try {
			cleanTable(dtm, columns);
			
			List<Produto> products = serivcesClientBean.listaProdutos();
			String productsData[][] = new String[products.size()][columns.length];
			
			for (int row = 0, column = 0; row < products.size(); column=0, row++) {
				productsData[row][column++] = String.valueOf(products.get(row).getId());
				productsData[row][column++] = String.valueOf(products.get(row).getNome());
				productsData[row][column++] = String.valueOf(products.get(row).getDescricao());
				productsData[row][column++] = String.valueOf(products.get(row).getQuantidadeEstoque());
				productsData[row][column] = String.format("%.2f", products.get(row).getPreco());
			}
			
			dtm.setDataVector(productsData, columns);
		} catch (Exception e) {
			PopupMessage.messageError(Messages.ERROR_LOAD_REGISTERS_PURCHASE, Constants.REGISTER_PURCHASE_TITLE);
			e.printStackTrace();
		}
	}
	
	private void initTableCart(DefaultTableModel dtm, String[] columns) {
		cleanTable(dtm, columns);
		
		List<ItemCarrinho> cartItems = serivcesClientBean.listaItensCarrinho();
	
		String cartData[][] = new String[cartItems.size()][columns.length];
		
		for (int row = 0, column = 0; row < cartItems.size(); column = 0, row++) {
			cartData[row][column++] = String.valueOf(cartItems.get(row).getProduto().getId());
			cartData[row][column++] = String.valueOf(cartItems.get(row).getProduto().getNome());
			cartData[row][column++] = String.valueOf(cartItems.get(row).getProduto().getDescricao());
			cartData[row][column++] = String.valueOf(cartItems.get(row).getQuantidade());
			cartData[row][column] = String.format("%.2f", cartItems.get(row).getProduto().getPreco() * cartItems.get(row).getQuantidade());
		}
		
		dtm.setDataVector(cartData, columns);
	}
	
	private void tryLoadEJB() {
		try {
			serivcesClientBean = Singleton.getIServicosCliente();
		} catch (Exception e) {
			e.printStackTrace();
			PopupMessage.messageError(Messages.ERROR_REGISTER_PURCHASE_EJB, Constants.REGISTER_PURCHASE_TITLE);
			dispose();
		}		
	}
	
	private void cleanTable(DefaultTableModel dtm, String[] columns){
		int size = dtm.getDataVector().size();
		String [][] data = new String[size][columns.length];
		
		for (int row = 0, column = 0; row < data.length; column=0, row++) {
			data[row][column++] = "";
			data[row][column++] = "";
			data[row][column++] = "";
			data[row][column++] = "";
			data[row][column] = "";
		}
		
		dtm.setDataVector(data, columns);
	}
	
	private void updateTable() {
		initTableProducts(dtmProduct, Constants.COLUMNS_PRODUCTS_AVAILABLE_TABLE);
		initTableCart(dtmCart, Constants.COLUMNS_PRODUCTS_PURCHASE_TABLE);
		productSelected = null;
	}
}
