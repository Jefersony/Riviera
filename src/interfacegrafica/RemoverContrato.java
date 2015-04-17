package interfacegrafica;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import classesprincipais.*;
import java.awt.Color;

public class RemoverContrato extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField campoNumCartao;
	private JTextField campoCPF;
	private JTextArea display;
	private Contrato contrato;
	private Hospede hospede;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RemoverContrato dialog = new RemoverContrato();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void limparEntradas() {
		campoNumCartao.setText("");
		campoCPF.setText("");
		display.setText("");
	}

	/**
	 * Create the dialog.
	 */
	public RemoverContrato() {
		setBounds(100, 100, 580, 460);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblRemoverContrato = new JLabel("REMOVER CONTRATO");
			lblRemoverContrato.setHorizontalAlignment(SwingConstants.CENTER);
			lblRemoverContrato.setFont(new Font("Simplified Arabic", Font.BOLD, 20));
			lblRemoverContrato.setBounds(10, 11, 544, 36);
			contentPanel.add(lblRemoverContrato);
		}
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(10, 58, 544, 104);
		contentPanel.add(panel);
		
		JLabel lblCartaoDoCliente = new JLabel("Cartao do Cliente:");
		lblCartaoDoCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCartaoDoCliente.setBounds(10, 11, 125, 14);
		panel.add(lblCartaoDoCliente);
		
		JLabel lblCpfDoCliente = new JLabel("CPF do Cliente:");
		lblCpfDoCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCpfDoCliente.setBounds(10, 54, 125, 14);
		panel.add(lblCpfDoCliente);
		
		campoNumCartao = new JTextField();
		campoNumCartao.setColumns(10);
		campoNumCartao.setBounds(145, 8, 218, 20);
		panel.add(campoNumCartao);
		
		campoCPF = new JTextField();
		campoCPF.setColumns(10);
		campoCPF.setBounds(145, 48, 144, 20);
		panel.add(campoCPF);
		
		JButton verificar = new JButton("Buscar");
		verificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if( Main.getHotel().getContratos().isEmpty() ) display.setText( "Nao ha contratos cadastrados.");
				else{
					for( Contrato c : Main.getHotel().getContratos() ) {
						if( campoNumCartao.getText().equals(c.getNumCartao()) || campoCPF.getText().equals(c.getHospede().getCpf()) ) {
							display.setText(c.toString());
							contrato = c;
							hospede = c.getHospede();
							break;
						}
					}
				}
			}
		});
		verificar.setBounds(437, 50, 97, 23);
		panel.add(verificar);
		
		display = new JTextArea();
		display.setBounds(10, 173, 542, 202);
		contentPanel.add(display);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Contrato");
				okButton.setToolTipText("Remove apenas o contrato, deixando seu assinante ainda cadastrado no hotel.");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							Main.getHotel().getContratos().remove(contrato);
							dispose();
						} catch (Exception e2) {
							display.setText( e2.getMessage());
						}
						
					}
				});
				
				JButton btnRmvCntrtE = new JButton("Contrato e Hospede");
				btnRmvCntrtE.setToolTipText("Remove o contrato e o seu hospede assinante.");
				btnRmvCntrtE.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							Main.getHotel().getContratos().remove(contrato);
							Main.getHotel().removerHospede(hospede);
							dispose();
						} catch (Exception e2) {
							display.setText( e2.getMessage());
						}
					}
				});
				buttonPane.add(btnRmvCntrtE);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Fechar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
