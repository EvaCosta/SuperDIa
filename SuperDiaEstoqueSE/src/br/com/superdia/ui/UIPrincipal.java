package br.com.superdia.ui;

import java.awt.Dimension;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class UIPrincipal extends JFrame {
	public UIPrincipal() {
		setLocale(new Locale("pt", "BR"));
		setVisible(true);
		setTitle("Super Dia Estoque");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][grow]"));
		
		JLabel lblNewLabel = new JLabel("Nome");
		getContentPane().add(lblNewLabel, "cell 0 0,alignx trailing");
		
		textField = new JTextField();
		getContentPane().add(textField, "cell 1 0,growx");
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Descri\u00E7\u00E3o");
		getContentPane().add(lblNewLabel_1, "cell 0 1,alignx trailing");
		
		textField_1 = new JTextField();
		getContentPane().add(textField_1, "cell 1 1,growx");
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Pre\u00E7o");
		getContentPane().add(lblNewLabel_2, "cell 0 2,alignx trailing");
		
		textField_2 = new JTextField();
		getContentPane().add(textField_2, "cell 1 2,growx");
		textField_2.setColumns(10);
		
		lblNewLabel_3 = new JLabel("Quantidade");
		getContentPane().add(lblNewLabel_3, "cell 0 3");
		
		spinner = new JSpinner();
		spinner.setMinimumSize(new Dimension(65, 20));
		getContentPane().add(spinner, "flowx,cell 1 3");
		
		btnNewButton = new JButton("Gravar");
		getContentPane().add(btnNewButton, "flowx,cell 1 6");
		
		btnNewButton_1 = new JButton("Editar");
		getContentPane().add(btnNewButton_1, "cell 1 6");
		
		btnNewButton_2 = new JButton("Remover");
		getContentPane().add(btnNewButton_2, "cell 1 6");
		
		scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, "cell 1 8,grow");
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		lblNewLabel_4 = new JLabel("Estoque M\u00EDnimo");
		getContentPane().add(lblNewLabel_4, "cell 1 3");
		
		spinner_1 = new JSpinner();
		spinner_1.setMinimumSize(new Dimension(65, 20));
		getContentPane().add(spinner_1, "cell 1 3");
		
		setSize(new Dimension(500, 500));
		
	}
	
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel lblNewLabel_3;
	private JSpinner spinner;
	private JLabel lblNewLabel_4;
	private JSpinner spinner_1;

}
