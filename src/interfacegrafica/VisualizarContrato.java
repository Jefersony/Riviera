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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import classesprincipais.Contrato;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class VisualizarContrato extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField campoNumCartao;
	private JTextField campoCPF;
	private JTextArea displayContrato;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VisualizarContrato dialog = new VisualizarContrato();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void limparEntradaDados() {
		campoNumCartao.setText("");
		campoCPF.setText("");
		displayContrato.setText("");
	}

	/**
	 * Create the dialog.
	 */
	public VisualizarContrato() {
		setBounds(100, 100, 580, 460);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblVisualizarContrato = new JLabel("VISUALIZAR CONTRATO");
		lblVisualizarContrato.setHorizontalAlignment(SwingConstants.CENTER);
		lblVisualizarContrato.setFont(new Font("Simplified Arabic", Font.BOLD, 20));
		lblVisualizarContrato.setBounds(10, 11, 544, 36);
		contentPanel.add(lblVisualizarContrato);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(10, 58, 544, 104);
		contentPanel.add(panel);
		
		JLabel lblCartaoDoCliente = new JLabel("Numero do Cartao do Cliente:");
		lblCartaoDoCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCartaoDoCliente.setBounds(10, 11, 186, 14);
		panel.add(lblCartaoDoCliente);
		
		JLabel lblDataDeInicio = new JLabel("CPF do Cliente Assinante:");
		lblDataDeInicio.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDataDeInicio.setBounds(10, 54, 186, 14);
		panel.add(lblDataDeInicio);
		
		campoNumCartao = new JTextField();
		campoNumCartao.setColumns(10);
		campoNumCartao.setBounds(206, 8, 218, 20);
		panel.add(campoNumCartao);
		
		campoCPF = new JTextField();
		campoCPF.setColumns(10);
		campoCPF.setBounds(208, 51, 144, 20);
		panel.add(campoCPF);
		
		JButton btnProcurar = new JButton("Procurar");
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if( Main.getHotel().getContratos().isEmpty() ) displayContrato.setText( "Nao ha contratos cadastrados.");
				else{
					for( Contrato c : Main.getHotel().getContratos() ) {
						if( campoNumCartao.getText().equals(c.getNumCartao()) || campoCPF.getText().equals(c.getHospede().getCpf()) ) {
							displayContrato.setText(c.toString());
							break;
						}
					}
				}
			}
		});
		btnProcurar.setBounds(437, 50, 97, 23);
		panel.add(btnProcurar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 173, 544, 204);
		contentPanel.add(scrollPane);
		
		displayContrato = new JTextArea();
		scrollPane.setViewportView(displayContrato);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						limparEntradaDados();
					}
				});
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
