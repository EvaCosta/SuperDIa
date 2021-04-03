package br.com.superdia.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Locale;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import br.com.superdia.client.ClienteExterno;
import br.com.superdia.controller.LojasParceiras;
import br.com.superdia.controller.ProdutoController;
import br.com.superdia.modelo.Produto;
import net.miginfocom.swing.MigLayout;

public class UIEstoque extends JFrame {

	private ProdutoController controller;
	private Produto selectedProduct;

	private UIVendas uiVendas;
	
	public UIVendas getUiVendas() {
		return uiVendas;
	}

	public void setUiVendas(UIVendas uiVendas) {
		this.uiVendas = uiVendas;
	}

	public UIEstoque(ProdutoController controller) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(UIEstoque.class.getResource("/assets/favicon-32x32.png")));
		this.controller = controller;

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnNewMenu_2 = new JMenu("Tela");
		menuBar.add(mnNewMenu_2);
		
		ButtonGroup btnGroup = new ButtonGroup();
		
		
		rdbtnmntmNewRadioItem = new JRadioButtonMenuItem("Controle de Estoque");
		rdbtnmntmNewRadioItem.setSelected(true);
		rdbtnmntmNewRadioItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			}
		});
		mnNewMenu_2.add(rdbtnmntmNewRadioItem);
		
		rdbtnmntmNewRadioItem_1 = new JRadioButtonMenuItem("Controle de Vendas");
		rdbtnmntmNewRadioItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				rdbtnmntmNewRadioItem.setSelected(true);
				rdbtnmntmNewRadioItem_1.setSelected(false);
				uiVendas.setVisible(true);
			}
		});
		mnNewMenu_2.add(rdbtnmntmNewRadioItem_1);

		btnGroup.add(rdbtnmntmNewRadioItem);
		btnGroup.add(rdbtnmntmNewRadioItem_1);
		
		importarMenu = new JMenu("Importar");
		menuBar.add(importarMenu);

		redBullMenuItem = new JMenuItem("Produtos externos de Red Bull Shopus...");
		redBullMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				importExternal(LojasParceiras.RED_BULL);
			}
		});
		importarMenu.add(redBullMenuItem);

		kylieMenuItem = new JMenuItem("Produtos externos de Kylie Cosmetics...");
		kylieMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				importExternal(LojasParceiras.KYLIE_COSMETICS);
			}
		});
		importarMenu.add(kylieMenuItem);

		ajudaMenu = new JMenu("Ajuda");
		menuBar.add(ajudaMenu);

		mntmNewMenuItem_2 = new JMenuItem("Sobre...");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(UIEstoque.this,
						"(c) 2021 - TSI\n"
								+ "Desenvolvido por Ramon, Leonardo, João Pedro, Eva, Rafaela, Paulo, Carlos.",
						"Sobre", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		ajudaMenu.add(mntmNewMenuItem_2);
		selectedProduct = new Produto();
		buildWindow();
	}

	private void importExternal(String url) {

		int opcao = JOptionPane.showConfirmDialog(this, "Deseja buscar produtos externos de " + url + "?",
				"Importar Produtos", JOptionPane.OK_CANCEL_OPTION);
	
		
		if (opcao == JOptionPane.YES_OPTION) {

			ClienteExterno clienteExterno = new ClienteExterno();
			try {
				
				int novosProdutos = 
						controller.salvarProdutosExternos(clienteExterno.obterProdutosExternos(url));
				
				
				refreshTable();
				
				JOptionPane.showMessageDialog(this, novosProdutos + " novos produtos adicionados.", 
						"Importar Produtos", JOptionPane.INFORMATION_MESSAGE);
				
				
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Um erro ocorreu " + e);
			}
		}
		
		
	}

	private static final long serialVersionUID = 1L;
	private JTextField nomeTextField;
	private JTextField descricaoTextField;
	private JTextField precoTextField;
	private JButton gravarButton;
	private JButton editarButton;
	private JButton removerButton;
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel lblNewLabel_3;
	private JSpinner quantidadeSpinner;
	private JLabel lblNewLabel_4;
	private JSpinner estoqueMinimoSpinner;
	private JLabel lblImg;
	private JMenuBar menuBar;
	private JMenu importarMenu;
	private JMenu ajudaMenu;
	private JMenuItem redBullMenuItem;
	private JMenuItem kylieMenuItem;
	private JMenuItem mntmNewMenuItem_2;
	private JMenu mnNewMenu_2;
	private JRadioButtonMenuItem rdbtnmntmNewRadioItem;
	private JRadioButtonMenuItem rdbtnmntmNewRadioItem_1;

	private void buildWindow() {
		setLocale(new Locale("pt", "BR"));

		setTitle("Super Dia Estoque");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][grow]"));

		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		getContentPane().add(lblNewLabel, "cell 0 0,alignx trailing");

		nomeTextField = new JTextField();
		nomeTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		getContentPane().add(nomeTextField, "cell 1 0,growx");
		nomeTextField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Descri\u00E7\u00E3o");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		getContentPane().add(lblNewLabel_1, "cell 0 1,alignx trailing");

		descricaoTextField = new JTextField();
		descricaoTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		getContentPane().add(descricaoTextField, "cell 1 1,growx");
		descricaoTextField.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Pre\u00E7o");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		getContentPane().add(lblNewLabel_2, "cell 0 2,alignx trailing");

		precoTextField = new JTextField();
		precoTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		getContentPane().add(precoTextField, "cell 1 2,growx");
		precoTextField.setColumns(10);

		lblNewLabel_3 = new JLabel("Quantidade");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		getContentPane().add(lblNewLabel_3, "cell 0 3,alignx right");

		quantidadeSpinner = new JSpinner();
		quantidadeSpinner.setFont(new Font("Tahoma", Font.PLAIN, 12));
		quantidadeSpinner.setMinimumSize(new Dimension(65, 20));
		getContentPane().add(quantidadeSpinner, "flowx,cell 1 3");

		gravarButton = new JButton("Adicionar Novo");
		gravarButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		gravarButton.setToolTipText("Adiciona um novo produto");
		gravarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gravarButton();
			}
		});

		getContentPane().add(gravarButton, "flowx,cell 1 6");

		editarButton = new JButton("Editar ");
		editarButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		editarButton.setToolTipText("Grava as altera\u00E7\u00F5es do produto selecionado");
		editarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarButton();
			}
		});
		editarButton.setEnabled(false);
		getContentPane().add(editarButton, "cell 1 6");

		removerButton = new JButton("Remover");
		removerButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		removerButton.setToolTipText("Remove o produto selecionado");
		removerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removerButton();
			}
		});
		removerButton.setEnabled(false);
		getContentPane().add(removerButton, "cell 1 6");

		createAndPopulateTable();

		lblImg = new JLabel("");
		lblImg.setIcon(new ImageIcon(UIEstoque.class.getResource("/assets/superdia_logo_alt_2.png")));
		getContentPane().add(lblImg, "cell 0 8");

		scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, "cell 1 8,grow");

		scrollPane.setViewportView(table);

		lblNewLabel_4 = new JLabel("Estoque M\u00EDnimo");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		getContentPane().add(lblNewLabel_4, "cell 1 3");

		estoqueMinimoSpinner = new JSpinner();
		estoqueMinimoSpinner.setFont(new Font("Tahoma", Font.PLAIN, 12));
		estoqueMinimoSpinner.setMinimumSize(new Dimension(65, 20));
		getContentPane().add(estoqueMinimoSpinner, "cell 1 3");

		setSize(new Dimension(700, 540));
		setVisible(true);
		
		checarEstoqueProdutos();

	}

	void checarEstoqueProdutos() {
		
		String produtos = "";
		for (Produto product : controller.obterProdutos()) {
			if(product.getQuantidadeEstoque() <= product.getEstoqueMinimo()) {
				produtos += String.format("%s  | Quantidade restante: %d\n", 
						product.getNome(), product.getQuantidadeEstoque());
			}
				
		}
		
		if(!produtos.isEmpty()) {
			JOptionPane.showMessageDialog(this, "AVISO: Os seguintes produtos atingiram a quantidade mínima:\n\n" + produtos,
					"Produtos em falta", JOptionPane.WARNING_MESSAGE);
		}
		
		
	}

	private void createAndPopulateTable() {

		Object[] columns = new String[] { "ID", "Nome", "Descrição", "Preço", "Quantidade", "Estoque Mínimo" };

		Object[][] data = new Object[controller.obterProdutos().size()][6];
		int i = 0;
		for (Produto product : controller.obterProdutos()) {
			data[i++] = new Object[] { product.getId(), product.getNome(), product.getDescricao(), product.getPreco(),
					product.getQuantidadeEstoque(), product.getEstoqueMinimo() };
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

				System.out.println(table.getSelectedRow());

				if (table.getSelectedRow() == -1)
					return;

				editarButton.setEnabled(true);
				removerButton.setEnabled(true);

				selectedProduct.setId(Long.parseLong(table.getValueAt(table.getSelectedRow(), 0).toString()));
				selectedProduct.setNome(table.getValueAt(table.getSelectedRow(), 1).toString());
				selectedProduct.setDescricao(table.getValueAt(table.getSelectedRow(), 2).toString());
				selectedProduct.setPreco(Double.parseDouble(table.getValueAt(table.getSelectedRow(), 3).toString()));
				selectedProduct
						.setQuantidadeEstoque(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 4).toString()));
				selectedProduct
						.setEstoqueMinimo(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 5).toString()));

				setFormProduct(selectedProduct);

			}
		});

	}

	private void refreshTable() {

		DefaultTableModel model = (DefaultTableModel) table.getModel();

		while (model.getRowCount() > 0) {
			model.removeRow(0);
		}

		for (Produto product : controller.obterProdutos()) {
			model.addRow(new Object[] { product.getId(), product.getNome(), product.getDescricao(), product.getPreco(),
					product.getQuantidadeEstoque(), product.getEstoqueMinimo() });
		}

		model.fireTableDataChanged();

	}

	private void gravarButton() {
		Produto p = extractProductFromForm();

		if (p == null)
			return;

		controller.add(p);

		setFormProduct(null);

		refreshTable();
		
		checarEstoqueProdutos();

	}

	private void editarButton() {
		Produto p = extractProductFromForm();

		if (p == null)
			return;

		p.setId(selectedProduct.getId());

		controller.update(p);

		setFormProduct(null);

		refreshTable();
		
		checarEstoqueProdutos();
	}

	private void removerButton() {

		controller.remove(selectedProduct);

		setFormProduct(null);

		refreshTable();

	}

	private Produto extractProductFromForm() {

		Produto p = new Produto();

		p.setNome(nomeTextField.getText());
		p.setDescricao(descricaoTextField.getText());
		p.setEstoqueMinimo(Integer.parseInt(estoqueMinimoSpinner.getValue().toString()));
		p.setQuantidadeEstoque(Integer.parseInt(quantidadeSpinner.getValue().toString()));

		try {
			p.setPreco(Double.parseDouble(precoTextField.getText()));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Preço inválido!", "Super Dia Estoque", 0);
			return null;
		}

		return p;
	}

	private void setFormProduct(Produto p) {

		if (p == null) {
			p = new Produto();
			p.setPreco(0D);
			p.setId(0L);
			p.setQuantidadeEstoque(0);
			p.setEstoqueMinimo(0);
			editarButton.setEnabled(false);
			removerButton.setEnabled(false);
		}

		nomeTextField.setText(p.getNome());
		descricaoTextField.setText(p.getDescricao());
		precoTextField.setText(p.getPreco().toString());
		quantidadeSpinner.setValue(p.getQuantidadeEstoque());
		estoqueMinimoSpinner.setValue(p.getEstoqueMinimo());
	}

}
