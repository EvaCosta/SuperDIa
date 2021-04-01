package br.com.superdia.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.superdia.controller.Constants;

public class UIRegisterPurchase extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	
	public UIRegisterPurchase(Component parent) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(UILogin.class.getResource(Constants.FAVICON)));
		setTitle(Constants.REGISTER_PURCHASE_TITLE);
		setSize(new Dimension(717, 598));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
	
		JButton btnSave = new JButton(Constants.SAVE_PURCHASE);
		buttonPane.add(btnSave);
		getRootPane().setDefaultButton(btnSave);

		JButton btnCancel = new JButton(Constants.BACK_DASHBOARD);
		buttonPane.add(btnCancel);
		
		setModal(true);
		setLocationRelativeTo(parent);
		setResizable(false);
		setVisible(true);
	}

}
