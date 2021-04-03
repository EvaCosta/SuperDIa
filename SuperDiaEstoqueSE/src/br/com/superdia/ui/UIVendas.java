package br.com.superdia.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import br.com.superdia.controller.VendasController;
import br.com.superdia.modelo.RegistroVenda;
import net.miginfocom.swing.MigLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;

public class UIVendas extends JFrame {

	private VendasController controller;
	private RegistroVenda selectedVenda;
	private UIEstoque uiEstoque;
	
	public UIEstoque getUiEstoque() {
		return uiEstoque;
	}

	public void setUiEstoque(UIEstoque uiEstoque) {
		this.uiEstoque = uiEstoque;
	}

	public UIVendas(VendasController controller) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(UIEstoque.class.getResource("/assets/favicon-32x32.png")));
		this.controller = controller;
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnNewMenu = new JMenu("Tela\r\n");
		menuBar.add(mnNewMenu);
		
		rdbtnmntmNewRadioItem = new JRadioButtonMenuItem("Controle de Estoque");
		rdbtnmntmNewRadioItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnmntmNewRadioItem_1.setSelected(true);
				rdbtnmntmNewRadioItem.setSelected(false);
				setVisible(false);
				uiEstoque.setVisible(true);
				uiEstoque.checarEstoqueProdutos();
			}
		});
		mnNewMenu.add(rdbtnmntmNewRadioItem);
		
		rdbtnmntmNewRadioItem_1 = new JRadioButtonMenuItem("Controle de Vendas");
		rdbtnmntmNewRadioItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		rdbtnmntmNewRadioItem_1.setSelected(true);
		mnNewMenu.add(rdbtnmntmNewRadioItem_1);
		selectedVenda = new RegistroVenda();
		buildWindow();
	}

	private static final long serialVersionUID = 1L;
	private JButton removerButton;
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel lblImg;
	private JTextPane textPane;
	private JScrollPane scrollPane_1;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JRadioButtonMenuItem rdbtnmntmNewRadioItem;
	private JRadioButtonMenuItem rdbtnmntmNewRadioItem_1;

	private void buildWindow() {
		setLocale(new Locale("pt", "BR"));

		setTitle("Super Dia Estoque");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new MigLayout("", "[][grow]", "[][grow][grow][]"));

		removerButton = new JButton("Remover");
		removerButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		removerButton.setToolTipText("Remove o produto selecionado");
		removerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removerButton();
			}
		});
		removerButton.setEnabled(false);
		getContentPane().add(removerButton, "cell 1 0");

		createAndPopulateTable();

		lblImg = new JLabel("");
		lblImg.setIcon(new ImageIcon(UIEstoque.class.getResource("/assets/superdia_logo_alt_2.png")));
		getContentPane().add(lblImg, "cell 0 1");

		scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, "cell 1 1,grow");

		scrollPane.setViewportView(table);
		
		scrollPane_1 = new JScrollPane();
		getContentPane().add(scrollPane_1, "cell 1 2,grow");
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		scrollPane_1.setViewportView(textPane);
		textPane.setPreferredSize(new Dimension(7, 300));

		setSize(new Dimension(700, 540));
		setVisible(false);

	}

	private void createAndPopulateTable() {

		List<RegistroVenda> registros = controller.listaRegistros();
		Object[] columns = new String[] { "ID da Compra", "Valor da Compra", "Nome do Usuario", "Id do Usuario"};

		Object[][] data = new Object[registros.size()][4];
		int i = 0;
		for (RegistroVenda registro : registros) {
			data[i++] = new Object[] 
					{
						registro.getId(),
						0.0,
						registro.getUsuario().getUsuario(),
						registro.getUsuario().getId(),
					};
		}

		DefaultTableModel tableModel = new DefaultTableModel(data, columns) {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = new JTable(tableModel);
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {

				if (table.getSelectedRow() == -1)
					return;		
				
				Long idVenda  = Long.parseLong(table.getValueAt(table.getSelectedRow(), 0).toString());
				Long idUsuario =  Long.parseLong(table.getValueAt(table.getSelectedRow(), 3).toString());
				
				textPane.setText(controller.obterItensCarrinho(idVenda, idUsuario));
				
				removerButton.setEnabled(true);


			}
		});

	}

	private void refreshTable() {

		controller.atualizar();
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();

		while (model.getRowCount() > 0) {
			model.removeRow(0);
		}

		for (RegistroVenda r : controller.listaRegistros()) {
			model.addRow(new Object[] {
					r.getId(),
					r.getUsuario().getUsuario(),
					0.0
				});
		}

		model.fireTableDataChanged();

	}

	private void removerButton() {

		controller.removeVenda(selectedVenda);

		refreshTable();

	}


}
