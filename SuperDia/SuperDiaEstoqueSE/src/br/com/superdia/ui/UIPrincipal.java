package br.com.superdia.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import br.com.superdia.controller.Produto;
import br.com.superdia.controller.ProdutoController;
import net.miginfocom.swing.MigLayout;

public class UIPrincipal extends JFrame {

	private ProdutoController controller;
	private Produto selectedProduct;

	public UIPrincipal(ProdutoController controller) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(UIPrincipal.class.getResource("/assets/favicon-32x32.png")));
		this.controller = controller;
		selectedProduct = new Produto();
		buildWindow();
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
		lblImg.setIcon(new ImageIcon(UIPrincipal.class.getResource("/assets/superdia_logo_alt_2.png")));
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

		setSize(new Dimension(700, 500));
		setVisible(true);

	}

	private void createAndPopulateTable() {

		Object[] columns = new String[] {"ID", "Nome", "Descrição", 
				"Preço", "Quantidade", "Estoque Mínimo"};

		Object[][] data = new Object[controller.getProducts().size()][6];
		int i = 0;
		for (Produto product : controller.getProducts()) {
			data[i++] = new Object[] {
					product.getId(),
					product.getNome(), 
					product.getDescricao(), 
					product.getPreco(),
					product.getQuantidade(), 
					product.getEstoqueMinimo()};
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


		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event) {

				System.out.println(table.getSelectedRow());

				if(table.getSelectedRow() == -1) return;

				editarButton.setEnabled(true);
				removerButton.setEnabled(true);
				

				selectedProduct.setId(
						Long.parseLong(table.getValueAt(table.getSelectedRow(), 0).toString()));
				selectedProduct.setNome(
						table.getValueAt(table.getSelectedRow(), 1).toString());
				selectedProduct.setDescricao(
						table.getValueAt(table.getSelectedRow(), 2).toString());
				selectedProduct.setPreco(
						Double.parseDouble(table.getValueAt(table.getSelectedRow(), 3).toString()));
				selectedProduct.setQuantidade(
						Integer.parseInt(table.getValueAt(table.getSelectedRow(), 4).toString()));
				selectedProduct.setEstoqueMinimo(
						Integer.parseInt(table.getValueAt(table.getSelectedRow(), 5).toString()));

				setFormProduct(selectedProduct);

			}
		});



	}


	private void refreshTable() {

		DefaultTableModel model = (DefaultTableModel) table.getModel();


		while(model.getRowCount()>0){
			model.removeRow(0);
		}

		for (Produto product : controller.getProducts()) {
			model.addRow( new Object[] {
					product.getId(),
					product.getNome(), 
					product.getDescricao(), 
					product.getPreco(),
					product.getQuantidade(), 
					product.getEstoqueMinimo()});
		}

		model.fireTableDataChanged();

	}

	private void gravarButton() {
		Produto p = extractProductFromForm();

		if(p == null) return;

		controller.add(p);

		setFormProduct(null);
		
		refreshTable();

	}

	private void editarButton() {
		Produto p = extractProductFromForm();

		if(p == null) return;

		p.setId(selectedProduct.getId());

		controller.update(p);

		setFormProduct(null);

		refreshTable();

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
		p.setQuantidade(Integer.parseInt(quantidadeSpinner.getValue().toString()));

		try{
			p.setPreco(Double.parseDouble(precoTextField.getText()));
		}catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Preço inválido!", "Super Dia Estoque", 0);
			return null;
		}

		return p;
	}

	private void setFormProduct(Produto p) {

		if(p == null) {
			p = new Produto("", "", 0D, 0, 0);
			editarButton.setEnabled(false);
			removerButton.setEnabled(false);
		}

		nomeTextField.setText(p.getNome());
		descricaoTextField.setText(p.getDescricao());
		precoTextField.setText(p.getPreco().toString());
		quantidadeSpinner.setValue(p.getQuantidade());
		estoqueMinimoSpinner.setValue(p.getEstoqueMinimo());
	}
}
