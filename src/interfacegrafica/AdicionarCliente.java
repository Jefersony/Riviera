package interfacegrafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import classesprincipais.*; 
import classesprincipais.Contrato.Tarifacao;
import classesprincipais.Quarto.Tipo;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdicionarCliente extends JFrame {
	private Hotel hotel = Main.getHotel();	
	private Hospede hospede;
	private Contrato contrato;
	private Servico quarto;

	private JPanel contentPane;
	private JTextField campoNome;
	private JTextField campoCpf;
	private JTextField campoEndereco;
	private JTextField campoCartao;
	private JTextField campoDataChegada;
	private JTextField campoDataSaida;
	private JComboBox comboBoxTarifacao;
	private JComboBox comboBoxTipoQuarto;
	private JLabel display;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdicionarCliente frame = new AdicionarCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void limparEntradas() {
		campoNome.setText("");
		campoCpf.setText("");
		campoEndereco.setText("");
		campoCartao.setText("");
		campoDataChegada.setText("");
		campoDataSaida.setText("");
		comboBoxTarifacao.setSelectedIndex(0);
		comboBoxTipoQuarto.setSelectedIndex(0);
	}

	/**
	 * Create the frame.
	 */
	public AdicionarCliente() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 580, 460);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setForeground(Color.BLUE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNome.setBounds(10, 62, 46, 14);
		contentPane.add(lblNome);
		
		campoNome = new JTextField();
		campoNome.setBounds(76, 62, 478, 20);
		contentPane.add(campoNome);
		campoNome.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCpf.setBounds(10, 94, 46, 14);
		contentPane.add(lblCpf);
		
		campoCpf = new JTextField();
		campoCpf.setBounds(76, 94, 135, 20);
		contentPane.add(campoCpf);
		campoCpf.setColumns(10);
		
		JLabel lblEndereco = new JLabel("Endereco:");
		lblEndereco.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEndereco.setBounds(10, 127, 59, 14);
		contentPane.add(lblEndereco);
		
		campoEndereco = new JTextField();
		campoEndereco.setBounds(76, 127, 478, 20);
		contentPane.add(campoEndereco);
		campoEndereco.setColumns(10);
		
		JLabel lblAdicionarCliente = new JLabel("ADICIONAR CLIENTE");
		lblAdicionarCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdicionarCliente.setFont(new Font("Simplified Arabic", Font.BOLD, 20));
		lblAdicionarCliente.setBounds(10, 11, 544, 36);
		contentPane.add(lblAdicionarCliente);
		
		JLabel lblQuarto = new JLabel("Tipo de Quarto:");
		lblQuarto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQuarto.setBounds(10, 197, 105, 14);
		contentPane.add(lblQuarto);
		
		String[] tiposDeQuarto = { "Executivo Simples", "Executivo Duplo", "Executivo Triplo", 
									"Luxo Simples", "Luxo Duplo", "Luxo Triplo", "Suite Presidencial" };
		comboBoxTipoQuarto = new JComboBox(tiposDeQuarto);
		comboBoxTipoQuarto.setEditable(true);
		comboBoxTipoQuarto.setBounds(125, 194, 135, 20);
		contentPane.add(comboBoxTipoQuarto);
		
		JLabel lblNCartaoDe = new JLabel("Num. Cartao:");
		lblNCartaoDe.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNCartaoDe.setBounds(248, 94, 91, 14);
		contentPane.add(lblNCartaoDe);
		
		campoCartao = new JTextField();
		campoCartao.setBounds(349, 93, 205, 20);
		contentPane.add(campoCartao);
		campoCartao.setColumns(10);
		
		JLabel lblDataDeChegada = new JLabel("Data de Chegada:");
		lblDataDeChegada.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDataDeChegada.setBounds(10, 228, 105, 14);
		contentPane.add(lblDataDeChegada);
		
		campoDataChegada = new JTextField();
		campoDataChegada.setBounds(125, 225, 135, 20);
		contentPane.add(campoDataChegada);
		campoDataChegada.setColumns(10);
		
		campoDataSaida = new JTextField();
		campoDataSaida.setBounds(419, 225, 135, 20);
		contentPane.add(campoDataSaida);
		campoDataSaida.setColumns(10);
		
		JLabel lblDataDeSaida = new JLabel("Data de Saida:");
		lblDataDeSaida.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDataDeSaida.setBounds(304, 228, 105, 14);
		contentPane.add(lblDataDeSaida);
		
		JButton btnConcluido = new JButton("Concluido");
		btnConcluido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					hospede = new Hospede( campoNome.getText(), campoCpf.getText(), campoEndereco.getText() );
					Tarifacao tpTarifacao;
					
					if( hotel.isHospedeCadastrado(campoCpf.getText())) throw new Exception( "Hospede ja cadastrado com este CPF");

					if( comboBoxTarifacao.getSelectedIndex() == 1) tpTarifacao = Tarifacao.NORMAL;
					else if( comboBoxTarifacao.getSelectedIndex() == 2) tpTarifacao = Tarifacao.ALTA;
					else if( comboBoxTarifacao.getSelectedIndex() == 3) tpTarifacao = Tarifacao.BAIXA;
					else tpTarifacao = Main.getHotel().getTarifacao();
					
					contrato = new Contrato( hospede, tpTarifacao, campoCartao.getText(), campoDataChegada.getText(), campoDataSaida.getText());
					
					Main.getHotel().adicionaHospede(hospede);
					if( comboBoxTipoQuarto.getSelectedIndex() == 6 )
						quarto = new Quarto(Tipo.PRESIDENCIAL);
					else if( comboBoxTipoQuarto.getSelectedIndex() == 1 )
						quarto = new Quarto(Tipo.EXECUTIVO_DUPLO);
					else if( comboBoxTipoQuarto.getSelectedIndex() == 2 )
						quarto = new Quarto(Tipo.EXECUTIVO_TRIPLO);
					else if( comboBoxTipoQuarto.getSelectedIndex() == 3 )
						quarto = new Quarto(Tipo.LUXO_SIMPLES);
					else if( comboBoxTipoQuarto.getSelectedIndex() == 4 )
						quarto = new Quarto(Tipo.LUXO_DUPLO);
					else if( comboBoxTipoQuarto.getSelectedIndex() == 5 )
						quarto = new Quarto(Tipo.LUXO_TRIPLO);
					else quarto = new Quarto(Tipo.EXECUTIVO_SIMPLES);
					
					contrato.adicionarServico(quarto);
					Main.getHotel().adicionarContrato(contrato);
					dispose();
				} catch (Exception e) {
					display.setText(e.getMessage());
				}
			}
		});
		btnConcluido.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnConcluido.setBounds(218, 301, 121, 23);
		contentPane.add(btnConcluido);
		
		JLabel lblTarifacao = new JLabel("Tarifacao:");
		lblTarifacao.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTarifacao.setBounds(304, 197, 105, 14);
		contentPane.add(lblTarifacao);
		
		String[] tipoTarifacao = { "", "Normal", "Alta", "Baixa" };
		comboBoxTarifacao = new JComboBox(tipoTarifacao);
		comboBoxTarifacao.setBounds(419, 194, 135, 20);
		contentPane.add(comboBoxTarifacao);
		
		display = new JLabel("");
		display.setHorizontalAlignment(SwingConstants.CENTER);
		display.setBounds(10, 276, 544, 14);
		contentPane.add(display);
		
	}
}
