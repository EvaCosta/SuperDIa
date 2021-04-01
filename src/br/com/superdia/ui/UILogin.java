package br.com.superdia.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.superdia.SuperDiaCaixa;
import br.com.superdia.controller.Constants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UILogin extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPasswordField passwdInput;
	private JLabel lblLogo;
	private JTextField loginInput;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	
	public UILogin() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(UILogin.class.getResource(Constants.FAVICON)));
		setTitle(Constants.LOGIN_TITLE);
		setSize(new Dimension(364, 356));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		loginInput = new JTextField();
		loginInput.setBounds(23, 141, 303, 32);
		contentPanel.add(loginInput);
		loginInput.setColumns(10);
		
		passwdInput = new JPasswordField();
		passwdInput.setBounds(23, 202, 303, 32);
		contentPanel.add(passwdInput);
		
		JButton btnLogin = new JButton(Constants.BUTTON_SIGN_IN);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signIn();
			}
		});
		
		btnLogin.setBounds(23, 264, 303, 35);
		contentPanel.add(btnLogin);
		getRootPane().setDefaultButton(btnLogin);
		
		lblLogo = new JLabel("New label");
		lblLogo.setBounds(23, 11, 303, 99);
		contentPanel.add(lblLogo);
		
		final Toolkit toolkit = Toolkit.getDefaultToolkit();
		final Dimension screenSize = toolkit.getScreenSize();
		final int x = (screenSize.width - getWidth()) / 2;
		final int y = (screenSize.height - getHeight()) / 2;
		setLocation(x, y);
		
		URL url = SuperDiaCaixa.class.getResource("/assets/logos/superdia_logo.png");
		ImageIcon icon = new ImageIcon(url);
		lblLogo.setIcon(icon);
		
		lblNewLabel = new JLabel(Constants.LABEL_LOGIN);
		lblNewLabel.setBounds(23, 121, 46, 14);
		contentPanel.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel(Constants.LABEL_PASSWORD);
		lblNewLabel_1.setBounds(23, 184, 46, 14);
		contentPanel.add(lblNewLabel_1);
		
		setResizable(false);
		setVisible(true);
	}

	private void signIn() {
		dispose();
		new UIDashboard(this);
	}
}
