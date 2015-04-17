package interfacegrafica;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import classesprincipais.*;
import classesprincipais.Contrato.Tarifacao;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class CriarContrato extends JDialog {
	private Hospede hospProContrato ;
	private Hotel hotel = Main.getHotel();

	private final JPanel contentPanel = new JPanel();
	private JTextField campoNome;
	private JTextField campoCpf;
	private JTextField displayHospedeCadastrado;
	private JTextField campoNumCartao;
	private JTextField campoDataInicio;
	private JTextField campoDataFim;
	private JComboBox comboBoxTarifacao;
	private JLabel display;
	private JButton botaoOK;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CriarContrato dialog = new CriarContrato();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void limparEntradas() {
		campoNome.setText("");
		campoCpf.setText("");
		campoNumCartao.setText("");
		campoDataInicio.setText("");
		campoDataFim.setText("");
		comboBoxTarifacao.setSelectedIndex(0);
		displayHospedeCadastrado.setText("");
		display.setText("");
		botaoOK.setEnabled(false);
	}
	public void disponibilizaNome() {
		campoNome.setText("");
		campoNome.setEnabled(true);
	}
	public void disponibilizaCpf() {
		campoCpf.setText("");
		campoCpf.setEnabled(true);
	}

	/**
	 * Create the dialog.
	 */
	public CriarContrato() {
		setBounds(100, 100, 580, 460);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblCriarContrato = new JLabel("CRIAR CONTRATO");
		lblCriarContrato.setHorizontalAlignment(SwingConstants.CENTER);
		lblCriarContrato.setFont(new Font("Simplified Arabic", Font.BOLD, 20));
		lblCriarContrato.setBounds(10, 11, 544, 36);
		contentPanel.add(lblCriarContrato);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(10, 58, 544, 131);
		contentPanel.add(panel);
		
		JLabel label = new JLabel("Nome:");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(10, 65, 46, 14);
		panel.add(label);
		
		JLabel label_1 = new JLabel("CPF:");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_1.setBounds(10, 15, 46, 14);
		panel.add(label_1);
		
		campoNome = new JTextField();
		campoNome.setBackground(Color.WHITE);
		campoNome.setEditable(false);
		campoNome.setColumns(10);
		campoNome.setBounds(78, 62, 456, 20);
		panel.add(campoNome);
		
		campoCpf = new JTextField();
		campoCpf.setColumns(10);
		campoCpf.setBounds(78, 12, 144, 20);
		panel.add(campoCpf);
		
		JLabel lblHospedeCadastrado = new JLabel("Hospede cadastrado?");
		lblHospedeCadastrado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHospedeCadastrado.setBounds(232, 15, 139, 14);
		panel.add(lblHospedeCadastrado);
		
		displayHospedeCadastrado = new JTextField();
		displayHospedeCadastrado.setHorizontalAlignment(SwingConstants.CENTER);
		displayHospedeCadastrado.setEditable(false);
		displayHospedeCadastrado.setBounds(381, 12, 46, 20);
		panel.add(displayHospedeCadastrado);
		displayHospedeCadastrado.setColumns(10);
		
		JButton btnVerificar = new JButton("Verificar");
		btnVerificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if( hotel.isHospedeCadastrado( campoCpf.getText()) ) { //main.gethotel atribuido a variavel
					Hospede hspd = hotel.pesquisaHospede( campoCpf.getText() );
					campoNome.setText(hspd.getNome());
					if( hotel.jaTemContrato( campoCpf.getText()) ) {
						display.setText( "Este hospede ja possui um contrato cadastrado.");
					}else{
						display.setText("Digite os dados para o contrato.");
						botaoOK.setEnabled(true);
						displayHospedeCadastrado.setEnabled(true);
						displayHospedeCadastrado.setText("SIM");
						//campoNome.setEnabled(false);
						campoCpf.setEnabled(false);
						campoNumCartao.setEnabled(true);
						campoDataInicio.setEnabled(true);
						campoDataFim.setEnabled(true);
						comboBoxTarifacao.setEnabled(true);
						comboBoxTarifacao.setSelectedIndex(0);
						hospProContrato = Main.getHotel().pesquisaHospede( campoCpf.getText() );
					}
				}else{
					displayHospedeCadastrado.setEnabled(true);
					displayHospedeCadastrado.setText("NAO");
					campoNumCartao.setEnabled(false);
					campoDataInicio.setEnabled(false);
					campoDataFim.setEnabled(false);
					comboBoxTarifacao.setEnabled(false);
					campoNome.setEnabled(true);
					campoCpf.setEnabled(true);
					botaoOK.setEnabled(false);
				}
			}
		});
		btnVerificar.setBounds(437, 11, 97, 23);
		panel.add(btnVerificar);
		
		display = new JLabel("Digite o CPF do cliente e confira o nome.");
		display.setFont(new Font("Tahoma", Font.BOLD, 11));
		display.setHorizontalAlignment(SwingConstants.CENTER);
		display.setBounds(10, 106, 524, 14);
		panel.add(display);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 200, 544, 177);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		String[] tipoTarifacao = { "", "Normal", "Alta", "Baixa" };
		comboBoxTarifacao = new JComboBox(tipoTarifacao);
		comboBoxTarifacao.setBounds(399, 44, 135, 20);
		panel_1.add(comboBoxTarifacao);
		
		JLabel lblTarifacao = new JLabel("Tarifacao: ");
		lblTarifacao.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTarifacao.setBounds(294, 47, 95, 14);
		panel_1.add(lblTarifacao);
		
		JLabel lblNCartao = new JLabel("N. Cartao:");
		lblNCartao.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNCartao.setBounds(10, 47, 67, 14);
		panel_1.add(lblNCartao);
		
		campoNumCartao = new JTextField();
		campoNumCartao.setEnabled(false);
		campoNumCartao.setBounds(87, 44, 173, 20);
		panel_1.add(campoNumCartao);
		campoNumCartao.setColumns(10);
		
		JLabel lblDataInicio = new JLabel("Data Inicio:");
		lblDataInicio.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDataInicio.setBounds(10, 93, 84, 14);
		panel_1.add(lblDataInicio);
		
		campoDataInicio = new JTextField();
		campoDataInicio.setEnabled(false);
		campoDataInicio.setBounds(104, 90, 117, 20);
		panel_1.add(campoDataInicio);
		campoDataInicio.setColumns(10);
		
		JLabel lblDataFim = new JLabel("Data Fim:");
		lblDataFim.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDataFim.setBounds(299, 96, 75, 14);
		panel_1.add(lblDataFim);
		
		campoDataFim = new JTextField();
		campoDataFim.setEnabled(false);
		campoDataFim.setBounds(384, 93, 135, 20);
		panel_1.add(campoDataFim);
		campoDataFim.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				botaoOK = new JButton("OK");
				botaoOK.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							Tarifacao tpTarifacao;
							if( comboBoxTarifacao.getSelectedIndex() == 1) tpTarifacao = Tarifacao.NORMAL;
							else if( comboBoxTarifacao.getSelectedIndex() == 2) tpTarifacao = Tarifacao.ALTA;
							else if( comboBoxTarifacao.getSelectedIndex() == 3) tpTarifacao = Tarifacao.BAIXA;
							else tpTarifacao = Main.getHotel().getTarifacao();
							
							Contrato novoContrato = new Contrato( hospProContrato,
									tpTarifacao, campoNumCartao.getText(), campoDataInicio.getText(), campoDataFim.getText() );
							if( Main.getHotel().jaTemContratoComEsseCartao(novoContrato.getNumCartao()))
								throw new Exception( "Ja existe um contrato cadastrado com esse cartao.");
							Main.getHotel().adicionarContrato(novoContrato);//mostrar confirmacao do contrato adicionado
							//sucessoNaOperacao.setVisible(true);
							campoNumCartao.setEnabled(false);
							campoDataInicio.setEnabled(false);
							campoDataFim.setEnabled(false);
							comboBoxTarifacao.setEnabled(false);
							comboBoxTarifacao.setSelectedIndex(0);
							campoNome.setEnabled(true);
							campoCpf.setEnabled(true);
							displayHospedeCadastrado.setEnabled(false);//
							dispose();
						} catch (Exception e2) {
							display.setText(e2.getMessage());
						}
					}
				});
				
				JButton btnLimpar = new JButton("Limpar");
				btnLimpar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						limparEntradas();
					}
				});
				buttonPane.add(btnLimpar);
				botaoOK.setActionCommand("OK");
				buttonPane.add(botaoOK);
				getRootPane().setDefaultButton(botaoOK);
			}
			{
				JButton cancelButton = new JButton("Fechar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						campoNumCartao.setEnabled(false);
						campoDataInicio.setEnabled(false);
						campoDataFim.setEnabled(false);
						comboBoxTarifacao.setEnabled(false);
						comboBoxTarifacao.setSelectedIndex(0);
						campoNome.setEnabled(true);
						campoCpf.setEnabled(true);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
