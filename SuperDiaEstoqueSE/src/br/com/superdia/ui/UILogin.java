package br.com.superdia.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class UILogin extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	
	public UILogin() {
		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Bem-vindo");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Login");
		lblNewLabel_1.setBounds(63, 37, 46, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Senha");
		lblNewLabel_2.setBounds(63, 72, 46, 14);
		panel.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(130, 34, 122, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JPasswordField passwordTextField = new JPasswordField();
		passwordTextField.setBounds(130, 69, 122, 20);
		panel.add(passwordTextField);
		
		JButton btnNewButton = new JButton("Acessar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(263, 112, 89, 23);
		panel.add(btnNewButton);
		
		setSize(new Dimension(395, 215));
		
		setVisible(true);
	}
}
