package br.com.superdia.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.naming.NamingException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.com.superdia.SuperDiaCaixa;
import br.com.superdia.controller.Constants;
import br.com.superdia.controller.Messages;
import br.com.superdia.controller.Singleton;
import br.com.superdia.es.PopupMessage;
import br.com.superdia.modelo.Usuario;
import br.com.superdia.sessionbeans.IUsuario;

public class UILogin extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPasswordField passwdInput;
	private JLabel lblLogo;
	private JTextField loginInput;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblError;
	
	public UILogin() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(UILogin.class.getResource(Constants.FAVICON)));
		setTitle(Constants.LOGIN_TITLE);
		setSize(new Dimension(402, 356));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		loginInput = new JTextField();
		loginInput.setBounds(23, 141, 345, 32);
		contentPanel.add(loginInput);
		loginInput.setColumns(10);
		
		passwdInput = new JPasswordField();
		passwdInput.setBounds(23, 202, 345, 32);
		contentPanel.add(passwdInput);
		
		JButton btnLogin = new JButton(Constants.BUTTON_SIGN_IN);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signIn();
			}
		});
		
		btnLogin.setBounds(23, 264, 345, 35);
		contentPanel.add(btnLogin);
		getRootPane().setDefaultButton(btnLogin);
		
		lblLogo = new JLabel("New label");
		lblLogo.setBounds(45, 11, 303, 99);
		contentPanel.add(lblLogo);
		
		final Toolkit toolkit = Toolkit.getDefaultToolkit();
		final Dimension screenSize = toolkit.getScreenSize();
		final int x = (screenSize.width - getWidth()) / 2;
		final int y = (screenSize.height - getHeight()) / 2;
		setLocation(x, y);
		
		URL url = SuperDiaCaixa.class.getResource(Constants.LOGO);
		ImageIcon icon = new ImageIcon(url);
		lblLogo.setIcon(icon);
		
		lblNewLabel = new JLabel(Constants.LABEL_LOGIN);
		lblNewLabel.setBounds(23, 121, 46, 14);
		contentPanel.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel(Constants.LABEL_PASSWORD);
		lblNewLabel_1.setBounds(23, 184, 46, 14);
		contentPanel.add(lblNewLabel_1);
		
		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setBounds(23, 239, 345, 14);
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		contentPanel.add(lblError);
		
		setResizable(false);
		setVisible(true);
	}

	private void signIn() {
		lblError.setText("");
		try {
			IUsuario userBean = Singleton.getIUsuario();
			Usuario user = new Usuario();
		
			user.setUsuario(loginInput.getText());
			user.setSenha(new String(passwdInput.getPassword()).trim());
			
			user = userBean.login(user);
			
			if(user != null && user.getPerfil().equalsIgnoreCase("caixa")) {				
				dispose();
				new UIDashboard(this);
			} else {
				lblError.setText(Messages.ERROR_LOGIN_MESSAGE);
				//PopupMessage.messageError(Messages.ERROR_LOGIN_MESSAGE, Constants.LOGIN_TITLE);
			}
		} catch (NamingException e) {
			PopupMessage.messageError(Messages.ERROR_LOGIN_EJB, Constants.LOGIN_TITLE);
			e.printStackTrace();
		}
	}
}
