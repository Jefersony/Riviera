package interfacegrafica;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import classesprincipais.*;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.util.List;

public class GerarFatura extends JDialog {
	//private GeradorFatura gf = new GeradorFatura();
	private Contrato contrato;

	private final JPanel contentPanel = new JPanel();
	private JTextField campoCpf;
	private JTextField campoCartao;
	private JTextArea display;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GerarFatura dialog = new GerarFatura();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void limparEntradas(){
		campoCpf.setText("");
		campoCartao.setText("");
		display.setText("");
	}

	/**
	 * Create the dialog.
	 */
	public GerarFatura() {
		setTitle("Gerar Fatura");
		setBounds(100, 100, 580, 460);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 544, 110);
		contentPanel.add(panel);
		panel.setLayout(null);
		{
			JLabel lblCpfDoCliente = new JLabel("CPF do cliente: ");
			lblCpfDoCliente.setBounds(10, 45, 101, 14);
			panel.add(lblCpfDoCliente);
			lblCpfDoCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
		}
		
		campoCpf = new JTextField();
		campoCpf.setBounds(130, 42, 153, 20);
		panel.add(campoCpf);
		campoCpf.setColumns(10);
		
		JLabel lblCartaoDoCliente = new JLabel("N. Cartao: ");
		lblCartaoDoCliente.setBounds(307, 45, 77, 14);
		panel.add(lblCartaoDoCliente);
		lblCartaoDoCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		campoCartao = new JTextField();
		campoCartao.setBounds(394, 42, 140, 20);
		panel.add(campoCartao);
		campoCartao.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Digite o CPF ou o numero do cartao do cliente.");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 524, 20);
		panel.add(lblNewLabel);
		
		JButton btnIdentificar = new JButton("Identificar");
		btnIdentificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String numCartao = campoCartao.getText();
					String cpf = campoCpf.getText();
					Main.areaTexto.setText("");
					if( Main.getHotel().getContratos().isEmpty() ) display.setText("Nao ha contratos cadastrados .");
					for( Contrato c : Main.getHotel().getContratos() ) {
						if( c.getNumCartao().equals(numCartao) || c.getHospede().getCpf().equals(cpf)) {
							contrato = c;
							display.setText( "Contrato selecionado:\n\n" + contrato.toString());
						}
					}
				} catch (Exception e2) {
					display.setText(e2.getMessage());
				}
			}
		});
		btnIdentificar.setBounds(227, 73, 115, 23);
		panel.add(btnIdentificar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 140, 544, 237);
		contentPanel.add(scrollPane);
		
		display = new JTextArea();
		scrollPane.setViewportView(display);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Fatura");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							//List<Servico> colecaoServicos = contrato.getListaServicos();
							String fatura = GeradorFatura.geraFatura(contrato);
							display.setText(fatura);
						} catch (Exception e2) {
							display.setText(e2.getMessage());
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
