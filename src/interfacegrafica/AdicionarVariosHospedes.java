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
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JFormattedTextField;
import javax.swing.text.*;

public class AdicionarVariosHospedes extends JDialog {
	private Hotel hotel = Main.getHotel();
	private Hospede hospede;
	private int totalAtual= 0;
	private int totalHospedes;

	private final JPanel contentPanel = new JPanel();
	private JTextField campoNome;
	private JTextField campoCpf;
	private JTextField campoEndereco;
	private JTextField campoTotalHospedes;
	private JTextField displayStatusOperacao;
	private JFormattedTextField campoDataNascimento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AdicionarVariosHospedes dialog = new AdicionarVariosHospedes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void limparEntradas() {
		campoNome.setText("");
		campoCpf.setText("");
		campoEndereco.setText("");
		campoTotalHospedes.setText("");
		campoDataNascimento.setText("");
		totalAtual = 0;
	}
	public JTextField getCampoTotalHospedes() {
		return this.campoTotalHospedes;
	}

	/**
	 * Create the dialog.
	 */
	public AdicionarVariosHospedes() {
		setBounds(100, 100, 580, 460);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			panel.setLayout(null);
			panel.setBounds(10, 103, 544, 263);
			contentPanel.add(panel);
			{
				JLabel label = new JLabel("Nome:");
				label.setFont(new Font("Tahoma", Font.BOLD, 11));
				label.setBounds(10, 109, 46, 14);
				panel.add(label);
			}
			{
				JLabel label = new JLabel("CPF:");
				label.setFont(new Font("Tahoma", Font.BOLD, 11));
				label.setBounds(10, 152, 46, 14);
				panel.add(label);
			}
			{
				JLabel label = new JLabel("Endereco:");
				label.setFont(new Font("Tahoma", Font.BOLD, 11));
				label.setBounds(10, 235, 61, 14);
				panel.add(label);
			}
			{
				campoNome = new JTextField();
				campoNome.setColumns(10);
				campoNome.setBounds(78, 106, 293, 20);
				panel.add(campoNome);
			}
			{
				campoCpf = new JTextField();
				campoCpf.setColumns(10);
				campoCpf.setBounds(78, 149, 144, 20);
				panel.add(campoCpf);
			}
			{
				campoEndereco = new JTextField();
				campoEndereco.setColumns(10);
				campoEndereco.setBounds(81, 232, 290, 20);
				panel.add(campoEndereco);
			}
			{
				JLabel lblTotalDeHospedes = new JLabel("Total de hospedes: ");
				lblTotalDeHospedes.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblTotalDeHospedes.setBounds(10, 45, 141, 14);
				panel.add(lblTotalDeHospedes);
			}
			{
				campoTotalHospedes = new JTextField();
				campoTotalHospedes.setBounds(161, 42, 77, 20);
				panel.add(campoTotalHospedes);
				campoTotalHospedes.setColumns(10);
			}
			{
				JLabel lblStatusDaOperacao = new JLabel("Status da operacao: ");
				lblStatusDaOperacao.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblStatusDaOperacao.setBounds(10, 14, 141, 14);
				panel.add(lblStatusDaOperacao);
			}
			{
				displayStatusOperacao = new JTextField();
				displayStatusOperacao.setFont(new Font("Tahoma", Font.BOLD, 11));
				displayStatusOperacao.setBackground(Color.WHITE);
				displayStatusOperacao.setForeground(Color.BLACK);
				displayStatusOperacao.setEditable(false);
				displayStatusOperacao.setBounds(161, 11, 373, 20);
				panel.add(displayStatusOperacao);
				displayStatusOperacao.setColumns(10);
				displayStatusOperacao.setText("Digite os dados do hospede.");
			}
			{
				JLabel label = new JLabel("Data de Nascimento:");
				label.setFont(new Font("Tahoma", Font.BOLD, 11));
				label.setBounds(10, 192, 150, 14);
				panel.add(label);
			}
			{
				campoDataNascimento = new JFormattedTextField();
				campoDataNascimento.setBounds(170, 189, 96, 20);
				panel.add(campoDataNascimento);
				try {
					campoDataNascimento.setFormatterFactory(new DefaultFormatterFactory( new MaskFormatter( "##/##/####" )));
				} catch (Exception e) {
					displayStatusOperacao.setText(e.getMessage());
				}
			}
		}
		{
			JLabel lblCadastrarHospedes = new JLabel("CADASTRAR HOSPEDES");
			lblCadastrarHospedes.setHorizontalAlignment(SwingConstants.CENTER);
			lblCadastrarHospedes.setFont(new Font("Simplified Arabic", Font.BOLD, 20));
			lblCadastrarHospedes.setBounds(10, 11, 544, 36);
			contentPanel.add(lblCadastrarHospedes);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							totalHospedes = Integer.parseInt(campoTotalHospedes.getText());
							
							if( totalHospedes > totalAtual ) {
								hospede = new Hospede( campoNome.getText(), campoCpf.getText(), campoEndereco.getText());
								hospede.setDataNascimento(campoDataNascimento.getText());
								if( hotel.isHospedeCadastrado( hospede.getCpf()) ) {
									displayStatusOperacao.setText("Hospede ja cadastrado com este CPF.");
								}else{
									hotel.adicionaHospede(hospede);
									displayStatusOperacao.setText("Digite os dados do proximo hospede.");
									totalAtual ++;
									campoNome.setText("");
									campoCpf.setText("");
									campoEndereco.setText("");
									campoTotalHospedes.setEditable(false);
									if( totalAtual == totalHospedes ) dispose();
								}
								//Main.getHotel().adicionaHospede(hospede);
								
							}else{
								campoTotalHospedes.setText("");
								totalAtual = 0;
								displayStatusOperacao.setText("Digite os dados do hospede.");
								dispose();
							}
							if( totalHospedes == totalAtual ) displayStatusOperacao.setText( "Operacao Concluida.");	
						} catch (Exception e2) {
							displayStatusOperacao.setText( "Insira um valor positivo para total de hospedes.");
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
