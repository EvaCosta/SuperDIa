package br.com.superdia.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import br.com.superdia.controller.Constants;
import br.com.superdia.controller.Messages;
import br.com.superdia.controller.Singleton;
import br.com.superdia.es.PopupMessage;
import br.com.superdia.modelo.ItemCarrinho;
import br.com.superdia.sessionbeans.ICarrinho;
import br.com.superdia.sessionbeans.IProduto;

public class UIRegisterPurchase extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable jtProducts;
	private JTextField tfSelected;
	private JTable jtCart;
	private IProduto productBean;
	private ICarrinho cartBean;
	private JSpinner spinnerAmount;
	private SpinnerModel spinnerAmountModel;
	
	public UIRegisterPurchase(Component parent) {
		tryLoadEJB();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(UILogin.class.getResource(Constants.FAVICON)));
		setTitle(Constants.REGISTER_PURCHASE_TITLE);
		setSize(new Dimension(727, 650));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), Constants.AVAILABLE_PRODUCTS, TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 691, 236);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 18, 671, 208);
		panel.add(scrollPane);
		
		jtProducts = new JTable();
		jtProducts.setEnabled(false);
		scrollPane.setViewportView(jtProducts);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), Constants.PRODUCTS_ON_CART, TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 329, 691, 236);
		contentPanel.add(panel_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 18, 671, 208);
		panel_1.add(scrollPane_1);
		
		jtCart = new JTable();
		scrollPane_1.setViewportView(jtCart);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, Constants.SELECTED_PRODUCT, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 258, 691, 60);
		contentPanel.add(panel_2);
		panel_2.setLayout(null);
		
		tfSelected = new JTextField();
		tfSelected.setEditable(false);
		tfSelected.setBounds(10, 21, 474, 28);
		panel_2.add(tfSelected);
		tfSelected.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("X");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(485, 28, 25, 14);
		panel_2.add(lblNewLabel);
		
		spinnerAmountModel = new SpinnerNumberModel(0, 0, 10, 1);
		spinnerAmount = new JSpinner(spinnerAmountModel);
		spinnerAmount.setBounds(506, 21, 70, 28);
		panel_2.add(spinnerAmount);
		
		JButton btnAdd = new JButton(Constants.ADD_CART);
		btnAdd.setBounds(592, 21, 89, 28);
		panel_2.add(btnAdd);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
	
		JButton btnSave = new JButton(Constants.SAVE_PURCHASE);
		buttonPane.add(btnSave);
		getRootPane().setDefaultButton(btnSave);

		JButton btnCancel = new JButton(Constants.BACK_DASHBOARD);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});
		buttonPane.add(btnCancel);
		
		
		spinnerAmountModel = new SpinnerNumberModel(0, 0, 30, 1);
		spinnerAmount.setModel(spinnerAmountModel);
		setModal(true);
		setLocationRelativeTo(parent);
		setResizable(false);
		setVisible(true);
	}

	private void cancel() {
		int confirmation = PopupMessage.questionConfirmationDialog(Messages.CONFIRMATION_CANCEL_PURCHASE, Constants.REGISTER_PURCHASE_TITLE);
		if(confirmation == 0) {
			List<ItemCarrinho> itensCart = cartBean.lista(); 
			for (int i = 0; i < itensCart.size(); i++) cartBean.remove(itensCart.get(i));
			dispose();
		}
	}
	
	private void tryLoadEJB() {
		try {
			productBean = Singleton.getIProduto();
			cartBean = Singleton.getICarrinho();
		} catch (Exception e) {
			e.printStackTrace();
			PopupMessage.messageError(Messages.ERROR_REGISTER_PURCHASE_EJB, Constants.REGISTER_PURCHASE);
			dispose();
		}		
	}
}
