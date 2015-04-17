package interfacegrafica;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import classesprincipais.*;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class PesquisarServicos extends JDialog {
	private Hotel hotel = Main.getHotel();
	private Hospede hosp;
	private Contrato contrato;
	private JTextArea display = Main.areaTexto;

	private final JPanel contentPanel = new JPanel();
	private JTextField campoCPF;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PesquisarServicos dialog = new PesquisarServicos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void limparEntradas(){
		comboBox.setSelectedIndex(0);
		campoCPF.setText("");
	}

	/**
	 * Create the dialog.
	 */
	public PesquisarServicos() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblPesquisarServico = new JLabel("PESQUISAR SERVICO");
			lblPesquisarServico.setHorizontalAlignment(SwingConstants.CENTER);
			lblPesquisarServico.setFont(new Font("Simplified Arabic", Font.BOLD, 20));
			lblPesquisarServico.setBackground(Color.WHITE);
			lblPesquisarServico.setBounds(10, 11, 414, 36);
			contentPanel.add(lblPesquisarServico);
		}
		{
			JLabel lblNewLabel = new JLabel("CPF do Cliente:");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNewLabel.setBounds(10, 81, 100, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			campoCPF = new JTextField();
			campoCPF.setBounds(120, 78, 140, 20);
			contentPanel.add(campoCPF);
			campoCPF.setColumns(10);
		}
		String[] tiposServicos = { "Todos", "Quarto", "Aluguel de Carro", "Restaurante", "Baba", "OutroServico" }; 
		comboBox = new JComboBox(tiposServicos);
		comboBox.setBounds(120, 130, 140, 20);
		contentPanel.add(comboBox);
		
		JLabel lblTipoDoServico = new JLabel("Tipo do Servico:");
		lblTipoDoServico.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTipoDoServico.setBounds(10, 133, 100, 14);
		contentPanel.add(lblTipoDoServico);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Pesquisar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							hosp = hotel.pesquisaHospede(campoCPF.getText());
							contrato = hotel.pesquisaContrato(hosp);
							List<Servico> servicos = contrato.getListaServicos();
							display.setText(String.format("Cliente: %s\n\n", hosp.getNome()));
							if( comboBox.getSelectedIndex() == 0) {
								display.setText(display.getText() +"Lista de todos os servicos consumidos pelo cliente:\n\n");
								//display.setText( display.getText() + servicos.toString() );
								int cont=1;
								for( Servico serv : servicos ) {
									display.setText( display.getText() + cont + " -- " + serv.toString() );
									cont++;
								}
							}else if( comboBox.getSelectedIndex() == 1 ) {
								for( Servico serv : servicos ) {
									if( serv instanceof Quarto ) {
										Quarto qrt = (Quarto) serv;
										display.setText( display.getText() + qrt.toString() );
									}
								}
							}else if( comboBox.getSelectedIndex() == 2 ) {
								for( Servico serv : servicos ) {
									if( serv instanceof Carro ) {
										Carro crr = (Carro) serv;
										display.setText( display.getText() + crr.toString() );
									}
								}
							}else if( comboBox.getSelectedIndex() == 3 ) {
								for( Servico serv : servicos ) {
									if( serv instanceof Refeicao ) {
										Refeicao rfc = (Refeicao) serv;
										display.setText( display.getText() + rfc.toString() );
									}
								}
							}else if( comboBox.getSelectedIndex() == 4 ) {
								for( Servico serv : servicos ) {
									if( serv instanceof Baba ) {
										Baba bb = (Baba) serv;
										display.setText( display.getText() + bb.toString() );
									}
								}
							}else{
								for( Servico serv : servicos ) {
									if( serv instanceof OutroServico ) {
										OutroServico otr = (OutroServico) serv;
										display.setText( display.getText() + otr.toString() );
									}	
								}
							}
						
						} catch (Exception e) {
							display.setText("Erro na operacao.");
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
						limparEntradas();
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
