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

import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.awt.Color;

public class RemoverServico extends JDialog {
	private Contrato contratoAtual;
	private List<Servico> servicos;

	private final JPanel contentPanel = new JPanel();
	private JTextField campoCartao;
	private JTextField campoCpf;
	private JTextField campoNServico;
	private JTextArea display;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RemoverServico dialog = new RemoverServico();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void limparEntradas() {
		campoCartao.setText("");
		campoCpf.setText("");
		campoNServico.setText("");
		display.setText("");
	}

	/**
	 * Create the dialog.
	 */
	public RemoverServico() {
		setBounds(100, 100, 580, 460);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblRemoverServico = new JLabel("REMOVER SERVICO");
			lblRemoverServico.setHorizontalAlignment(SwingConstants.CENTER);
			lblRemoverServico.setFont(new Font("Simplified Arabic", Font.BOLD, 20));
			lblRemoverServico.setBounds(10, 11, 544, 36);
			contentPanel.add(lblRemoverServico);
		}
		{
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			panel.setBounds(10, 58, 544, 88);
			contentPanel.add(panel);
			{
				JLabel label = new JLabel("N. Cartao:");
				label.setFont(new Font("Tahoma", Font.BOLD, 11));
				label.setBounds(10, 46, 74, 14);
				panel.add(label);
			}
			{
				campoCartao = new JTextField();
				campoCartao.setColumns(10);
				campoCartao.setBounds(94, 43, 130, 20);
				panel.add(campoCartao);
			}
			{
				JLabel label = new JLabel("CPF:");
				label.setFont(new Font("Tahoma", Font.BOLD, 11));
				label.setBounds(249, 46, 46, 14);
				panel.add(label);
			}
			{
				campoCpf = new JTextField();
				campoCpf.setColumns(10);
				campoCpf.setBounds(304, 43, 130, 20);
				panel.add(campoCpf);
			}
			{
				JLabel label = new JLabel("Verifique se existe um contrato atraves do numero do cartao do cliente ou de seu CPF.");
				label.setFont(new Font("Tahoma", Font.BOLD, 11));
				label.setBounds(10, 11, 524, 20);
				panel.add(label);
			}
			{
				JButton button = new JButton("Verificar");
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if( Main.getHotel().getContratos().isEmpty() ) display.setText( "Nao ha contratos cadastrados.");
						else{
							List<Contrato> contratos = Main.getHotel().getContratos(); 
							for( int i=0; i<contratos.size(); i++ ) {
								Contrato c = contratos.get(i);
								if( campoCartao.getText().equals(c.getNumCartao()) || campoCpf.getText().equals(c.getHospede().getCpf()) ) {
									display.setText(c.toString());
									//disponibilizarBotoes(true);
									contratoAtual = contratos.get(i); 
									break;
								}
							}
						}
					}
				});
				button.setBounds(445, 42, 89, 23);
				panel.add(button);
			}
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 157, 544, 187);
		contentPanel.add(scrollPane);
		
		display = new JTextArea();
		scrollPane.setViewportView(display);
		
		JLabel lblNServico = new JLabel("N. Servico:");
		lblNServico.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNServico.setBounds(324, 356, 73, 20);
		contentPanel.add(lblNServico);
		
		campoNServico = new JTextField();
		campoNServico.setBounds(407, 356, 86, 20);
		contentPanel.add(campoNServico);
		campoNServico.setColumns(10);
		
		JLabel label = new JLabel("Servicos do Cliente:");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(10, 356, 116, 20);
		contentPanel.add(label);
		
		JButton button = new JButton("Listar");
		button.setToolTipText("Exibe na tela todos os servicos consumidos pelo cliente.");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					display.setText("");
					if( contratoAtual == null ) throw new Exception( "Contrato nao selecionado.");
					servicos = contratoAtual.getListaServicos();
					if( servicos.isEmpty() ) {
						display.setText("Nao ha servicos consumidos pelo cliente.");
					}else{
						int cont=1;
						for( int i=0; i<servicos.size(); i++ ) {
							display.setText(display.getText() + "\n" +"( " + cont + " )" + " -- " + servicos.get(i) );
							cont++;
						}
					}
				} catch (Exception e2) {
					display.setText(e2.getMessage());
				}
				
			}
		});
		button.setBounds(136, 355, 89, 23);
		contentPanel.add(button);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("REMOVER");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if( servicos.isEmpty() ) {
								display.setText("Nao ha servicos a serem removidos.");
							}else{
								int posicao = Integer.parseInt(campoNServico.getText()) - 1;// posicao do servico a ser removido
								if( posicao < 0 || posicao >= servicos.size() ) throw new Exception( "Nao ha servico nesta posicao da lista.");
								servicos.remove(posicao);
								campoNServico.setText("");
								display.setText("");
								if( servicos.isEmpty() ) {
									display.setText("Nao ha mais servicos consumidos pelo cliente.");
								}else{
									int cont=1;
									for( int i=0; i<servicos.size(); i++ ) {
										display.setText(display.getText() + "\n" +"( " + cont + " )" + " -- " + servicos.get(i) );
										cont++;
									}
								}
							}
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
