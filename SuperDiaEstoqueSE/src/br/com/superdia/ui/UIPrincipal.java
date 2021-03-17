package br.com.superdia.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

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

import br.com.superdia.controller.Product;
import br.com.superdia.controller.ProductController;
import net.miginfocom.swing.MigLayout;

public class UIPrincipal extends JFrame {

	private ProductController controller;
	private Product selectedProduct;

	public UIPrincipal(ProductController controller) {
		this.controller = controller;
		selectedProduct = new Product();
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

	private void buildWindow() {
		setLocale(new Locale("pt", "BR"));

		setTitle("Super Dia Estoque");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][grow]"));

		JLabel lblNewLabel = new JLabel("Nome");
		getContentPane().add(lblNewLabel, "cell 0 0,alignx trailing");

		nomeTextField = new JTextField();
		getContentPane().add(nomeTextField, "cell 1 0,growx");
		nomeTextField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Descri\u00E7\u00E3o");
		getContentPane().add(lblNewLabel_1, "cell 0 1,alignx trailing");

		descricaoTextField = new JTextField();
		getContentPane().add(descricaoTextField, "cell 1 1,growx");
		descricaoTextField.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Pre\u00E7o");
		getContentPane().add(lblNewLabel_2, "cell 0 2,alignx trailing");

		precoTextField = new JTextField();
		getContentPane().add(precoTextField, "cell 1 2,growx");
		precoTextField.setColumns(10);

		lblNewLabel_3 = new JLabel("Quantidade");
		getContentPane().add(lblNewLabel_3, "cell 0 3");

		quantidadeSpinner = new JSpinner();
		quantidadeSpinner.setMinimumSize(new Dimension(65, 20));
		getContentPane().add(quantidadeSpinner, "flowx,cell 1 3");

		gravarButton = new JButton("Adicionar");
		gravarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gravarButton();
			}
		});

		getContentPane().add(gravarButton, "flowx,cell 1 6");

		editarButton = new JButton("Editar");
		editarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarButton();
			}
		});
		editarButton.setEnabled(false);
		getContentPane().add(editarButton, "cell 1 6");

		removerButton = new JButton("Remover");
		removerButton.setEnabled(false);
		getContentPane().add(removerButton, "cell 1 6");

		createAndPopulateTable();
		refreshTable();

		scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, "cell 1 8,grow");

		scrollPane.setViewportView(table);

		lblNewLabel_4 = new JLabel("Estoque M\u00EDnimo");
		getContentPane().add(lblNewLabel_4, "cell 1 3");

		estoqueMinimoSpinner = new JSpinner();
		estoqueMinimoSpinner.setMinimumSize(new Dimension(65, 20));
		getContentPane().add(estoqueMinimoSpinner, "cell 1 3");

		setSize(new Dimension(500, 500));
		setVisible(true);

	}

	private void createAndPopulateTable() {

		Object[] columns = new String[] {"ID", "Nome", "Descrição", 
				"Preço", "Quantidade", "Estoque Mínimo"};

		Object[][] data = new Object[6][controller.getProducts().size()];
		int i = 0;
		for (Product product : controller.getProducts()) {
			data[i++] = new Object[] {
					product.getId(),
					product.getNome(), 
					product.getDescricao(), 
					product.getPreco(),
					product.getQuantidade(), 
					product.getEstoqueMinimo()};
		}

		DefaultTableModel tableModel = new DefaultTableModel(data, columns);


		table = new JTable(tableModel);

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

		for (Product product : controller.getProducts()) {
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
		Product p = extractProductFromForm();

		if(p == null) return;

		controller.add(p);

		refreshTable();

	}

	private void editarButton() {
		Product p = extractProductFromForm();
		
		if(p == null) return;

		p.setId(selectedProduct.getId());
		
		controller.update(p);
		
		setFormProduct(null);
		
		refreshTable();

	}

	private Product extractProductFromForm() {

		Product p = new Product();

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

	private void setFormProduct(Product p) {

		if(p == null) {
			p = new Product("", "", 0D, 0, 0);
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
