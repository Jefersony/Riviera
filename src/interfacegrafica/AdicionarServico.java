package interfacegrafica;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import classesprincipais.*;
import interfacegrafica.servicos.*;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.awt.Color;

public class AdicionarServico extends JDialog {
	private static Contrato contratoAtual;

	private final JPanel contentPanel = new JPanel();
	private JTextField campoNumCartao;
	private JTextField campoCpf;
	private JTextArea display;
	private JButton btnQuarto;
	private JButton btnCarro;
	private JButton btnRefeicao;
	private JButton btnBaba;
	private JButton btnOutro;
	private JLabel lblEscolhaOTipo;
	private AdicionarQuarto addQuarto = new AdicionarQuarto();
	private AdicionarCarro addCarro = new AdicionarCarro();
	private AdicionarRefeicao addRefeicao = new AdicionarRefeicao();
	private AdicionarBaba addBaba = new AdicionarBaba();
	private NovoServico novoServico = new NovoServico();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AdicionarServico dialog = new AdicionarServico();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void limparEntradas() {
		campoNumCartao.setText("");
		campoCpf.setText("");
		display.setText("");
	}
	public void disponibilizarBotoes( boolean boo ) {
		this.btnQuarto.setEnabled(boo);
		this.btnCarro.setEnabled(boo);
		this.btnRefeicao.setEnabled(boo);
		this.btnBaba.setEnabled(boo);
		this.btnOutro.setEnabled(boo);
		this.lblEscolhaOTipo.setEnabled(boo);
	}
	public static void adicionarServico( Servico serv ) {
		contratoAtual.adicionarServico(serv);
	}

	/**
	 * Create the dialog.
	 */
	public AdicionarServico() {
		setBounds(100, 100, 580, 460);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblAdicionarServico = new JLabel("ADICIONAR SERVICO");
		lblAdicionarServico.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdicionarServico.setFont(new Font("Simplified Arabic", Font.BOLD, 20));
		lblAdicionarServico.setBounds(10, 11, 544, 36);
		contentPanel.add(lblAdicionarServico);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 58, 544, 88);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNCartao = new JLabel("N. Cartao:");
		lblNCartao.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNCartao.setBounds(10, 46, 74, 14);
		panel.add(lblNCartao);
		
		campoNumCartao = new JTextField();
		campoNumCartao.setBounds(94, 43, 130, 20);
		panel.add(campoNumCartao);
		campoNumCartao.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("CPF:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(249, 46, 46, 14);
		panel.add(lblNewLabel);
		
		campoCpf = new JTextField();
		campoCpf.setBounds(304, 43, 130, 20);
		panel.add(campoCpf);
		campoCpf.setColumns(10);
		
		JLabel lblVerifiqueSeExiste = new JLabel("Verifique se existe um contrato atraves do numero do cartao do cliente ou de seu CPF.");
		lblVerifiqueSeExiste.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblVerifiqueSeExiste.setBounds(10, 11, 524, 20);
		panel.add(lblVerifiqueSeExiste);
		
		JButton btnVerificar = new JButton("Verificar");
		btnVerificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if( Main.getHotel().getContratos().isEmpty() ) display.setText( "Nao ha contratos cadastrados.");
				else{
					List<Contrato> contratos = Main.getHotel().getContratos(); 
					for( int i=0; i<contratos.size(); i++ ) {
						Contrato c = contratos.get(i);
						if( campoNumCartao.getText().equals(c.getNumCartao()) || campoCpf.getText().equals(c.getHospede().getCpf()) ) {
							display.setText(c.toString());
							disponibilizarBotoes(true);
							contratoAtual = contratos.get(i); 
							break;
						}
					}
				}
			}
		});
		btnVerificar.setBounds(445, 42, 89, 23);
		panel.add(btnVerificar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(10, 289, 544, 88);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		btnQuarto = new JButton("Quarto");
		btnQuarto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addQuarto.setVisible(true);
			}
		});
		btnQuarto.setEnabled(false);
		btnQuarto.setBounds(16, 54, 89, 23);
		panel_1.add(btnQuarto);
		
		btnCarro = new JButton("Carro");
		btnCarro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addCarro.setVisible(true);
			}
		});
		btnCarro.setEnabled(false);
		btnCarro.setBounds(121, 54, 89, 23);
		panel_1.add(btnCarro);
		
		btnRefeicao = new JButton("Refeicao");
		btnRefeicao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addRefeicao.setVisible(true);
			}
		});
		btnRefeicao.setEnabled(false);
		btnRefeicao.setBounds(226, 54, 89, 23);
		panel_1.add(btnRefeicao);
		
		btnBaba = new JButton("Baba");
		btnBaba.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addBaba.setVisible(true);
			}
		});
		btnBaba.setEnabled(false);
		btnBaba.setBounds(331, 54, 89, 23);
		panel_1.add(btnBaba);
		
		btnOutro = new JButton("Outro");
		btnOutro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				novoServico.limparEntradas();
				novoServico.setVisible(true);
			}
		});
		btnOutro.setEnabled(false);
		btnOutro.setBounds(436, 54, 89, 23);
		panel_1.add(btnOutro);
		
		lblEscolhaOTipo = new JLabel("Escolha o tipo de servico a ser adicionado na conta do cliente.");
		lblEscolhaOTipo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEscolhaOTipo.setEnabled(false);
		lblEscolhaOTipo.setBounds(10, 11, 524, 20);
		panel_1.add(lblEscolhaOTipo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 157, 544, 125);
		contentPanel.add(scrollPane);
		
		display = new JTextArea();
		scrollPane.setViewportView(display);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton btnListar = new JButton("Listar");
			btnListar.setToolTipText("Exibe todos os servicos do contrato do cliente.");
			btnListar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						display.setText(String.format("Lista de servicos consumidos por %s:\n", contratoAtual.getHospede().getNome()));
						if( contratoAtual == null ) throw new Exception( "Nao ha contrato selecionado.");
						List<Servico> servicos = contratoAtual.getListaServicos();
						int cont=1;
						for( Servico serv : servicos ) {
							display.setText(display.getText() + cont + " -- " + serv.toString() );
							cont++;
						}
					} catch (Exception e2) {
						display.setText(e2.getMessage());
					}
				}
			});
			buttonPane.add(btnListar);
			{
				JButton okButton = new JButton("Concluido");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Fechar");
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
