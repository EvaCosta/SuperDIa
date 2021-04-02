package br.com.superdia.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.com.superdia.SuperDiaCaixa;
import br.com.superdia.controller.Constants;
import br.com.superdia.controller.Messages;
import br.com.superdia.controller.Singleton;
import br.com.superdia.es.PopupMessage;
import br.com.superdia.modelo.ItemCarrinho;
import br.com.superdia.modelo.RegistroVenda;
import br.com.superdia.sessionbeans.IRegistroVenda;

public class UIDashboard extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private DefaultTableModel dtmPurchases = new DefaultTableModel();
	private JTable table;
	
	public UIDashboard(Component parent) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(UILogin.class.getResource(Constants.FAVICON)));
		setTitle(Constants.DASHBOARD_TITLE);
		setSize(new Dimension(754, 487));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(10, 20, 108, 368);
		contentPanel.add(lblLogo);

		setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu(Constants.MENU);
		menuBar.add(mnNewMenu);
		
		JMenuItem menuLogout = new JMenuItem(Constants.EXIT);
		Image scaledIcon = new ImageIcon(UIDashboard.class.getResource(Constants.EXIT_IMAGE)).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		menuLogout.setIcon(new ImageIcon(scaledIcon));

		menuLogout.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				signOut();
			}
		});
		
		mnNewMenu.add(menuLogout);
		
		JMenu mnNewMenu_1 = new JMenu(Constants.SUPER_DIA_MENU);
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem(Constants.REGISTER_PURCHASE);
		mntmNewMenuItem.setIcon(new ImageIcon(UIDashboard.class.getResource(Constants.CART_IMAGE)));
		mntmNewMenuItem.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				registerNewPruchase();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem);
	
		URL url = SuperDiaCaixa.class.getResource(Constants.LOGO_ALT_VERTICAL);
		ImageIcon icon = new ImageIcon(url);
		lblLogo.setIcon(icon);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(88, 20, 634, 355);
		contentPanel.add(scrollPane);
		
		table = new JTable(dtmPurchases) {
			private static final long serialVersionUID = 1L;
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				return false;
			}
		};
		
		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		JButton btnPurchaseView = new JButton("Visualizar compra");
		btnPurchaseView.setBounds(591, 386, 131, 28);
		contentPanel.add(btnPurchaseView);
	
		initTable(dtmPurchases);
		setLocationRelativeTo(parent);
		setVisible(true);
	}

	private void initTable(DefaultTableModel dtm) {
		IRegistroVenda registerBean;
		try {
			cleanTable(dtm);
			registerBean = Singleton.getIRegistroVenda();
			List<RegistroVenda> registers = registerBean.lista();
			String registersData[][] = new String[registers.size()][3];
			
			for (int i = 0, j = 0; i < registers.size(); i++) {
				registersData[j][0] = String.valueOf(registers.get(i).getId());
				registersData[j][1] = String.valueOf(registers.get(i).getUsuario());
				
				double total = 0;
				List<ItemCarrinho> productsPurchase = registers.get(i).getItens(); 
				for (int k = 0; k < productsPurchase.size(); k++) {
					total += productsPurchase.get(k).getProduto().getPreco() * productsPurchase.get(k).getQuantidade();
				}
				
				registersData[j++][2] = String.valueOf(total);
			}
			
			dtm.setDataVector(registersData, Constants.COLUMNS_PURCHASE_TABLE);
		} catch (Exception e) {
			PopupMessage.messageError(Messages.ERROR_LOAD_REGISTERS_PURCHASE, Constants.DASHBOARD_TITLE);
			e.printStackTrace();
		}
	}

	private void signOut() {
		dispose();
		new UILogin();
	}
	
	private void registerNewPruchase() {
		new UIRegisterPurchase(this);
		initTable(dtmPurchases);
	}
	
	private void cleanTable(DefaultTableModel dtm){
		int size = dtm.getDataVector().size();
		String [][] data = new String[size][3];
		
		for (int i = 0, j = 0; i < data.length; i++) {
			data[j][0] = "";
			data[j][1] = "";
			data[j][2] = "";
			j++;
		}
		
		dtm.setDataVector(data, Constants.COLUMNS_PURCHASE_TABLE);
	}
}
