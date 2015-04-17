package interfacegrafica;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import classesprincipais.*;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PesquisarContrato extends JDialog {
	private String cpf;
	private String numCartao;

	private final JPanel contentPanel = new JPanel();
	private JTextField campoCartao;
	private JTextField campoCpf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PesquisarContrato dialog = new PesquisarContrato();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void limparEntradas() {
		campoCartao.setText("");
		campoCpf.setText("");
	}

	/**
	 * Create the dialog.
	 */
	public PesquisarContrato() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNumeroDoCartao = new JLabel("Numero do Cartao:");
		lblNumeroDoCartao.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNumeroDoCartao.setBounds(10, 141, 128, 14);
		contentPanel.add(lblNumeroDoCartao);
		
		campoCartao = new JTextField();
		campoCartao.setColumns(10);
		campoCartao.setBounds(148, 138, 217, 20);
		contentPanel.add(campoCartao);
		
		JLabel label_1 = new JLabel("CPF do Cliente:");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_1.setBounds(10, 98, 102, 14);
		contentPanel.add(label_1);
		
		campoCpf = new JTextField();
		campoCpf.setColumns(10);
		campoCpf.setBounds(148, 95, 217, 20);
		contentPanel.add(campoCpf);
		
		JLabel lblPesquisarContrato = new JLabel("PESQUISAR CONTRATO");
		lblPesquisarContrato.setBackground(Color.WHITE);
		lblPesquisarContrato.setHorizontalAlignment(SwingConstants.CENTER);
		lblPesquisarContrato.setFont(new Font("Simplified Arabic", Font.BOLD, 20));
		lblPesquisarContrato.setBounds(10, 11, 414, 36);
		contentPanel.add(lblPesquisarContrato);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						numCartao = campoCartao.getText();
						cpf = campoCpf.getText();
						Main.areaTexto.setText("");
						if( Main.getHotel().getContratos().isEmpty() ) Main.areaTexto.setText("Nao ha contratos cadastrados .");
						for( Contrato c : Main.getHotel().getContratos() ) {
							if( c.getNumCartao().equals(numCartao) || c.getHospede().getCpf().equals(cpf)) 
								Main.areaTexto.setText( Main.areaTexto.getText() + "\n" + c.toString() + "\n");
						}
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
