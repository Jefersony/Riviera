package interfacegrafica;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import classesprincipais.*;
import interfacegrafica.Main;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JFormattedTextField.AbstractFormatter;
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

public class AdicionarHospede extends JDialog {
	private Hospede hospede;
	private Hotel hotel = Main.hotel;

	private final JPanel contentPanel = new JPanel();
	private JTextField campoNome;
	private JTextField campoCpf;
	private JTextField campoEndereco;
	private JFormattedTextField campoDataNascimento;
	private JLabel display;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AdicionarHospede dialog = new AdicionarHospede();
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
		campoDataNascimento.setText("");
	}

	/**
	 * Create the dialog.
	 */
	public AdicionarHospede() {
		setBounds(100, 100, 580, 460);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			panel.setBounds(10, 75, 544, 263);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblNome = new JLabel("Nome:");
				lblNome.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblNome.setBounds(10, 11, 46, 14);
				panel.add(lblNome);
			}
			{
				JLabel lblCpf = new JLabel("CPF:");
				lblCpf.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblCpf.setBounds(10, 54, 46, 14);
				panel.add(lblCpf);
			}
			{
				JLabel lblEndereco = new JLabel("Endereco:");
				lblEndereco.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblEndereco.setBounds(10, 152, 61, 14);
				panel.add(lblEndereco);
			}
			{
				campoNome = new JTextField();
				campoNome.setBounds(78, 8, 340, 20);
				panel.add(campoNome);
				campoNome.setColumns(10);
			}
			{
				campoCpf = new JTextField();
				campoCpf.setBounds(78, 51, 188, 20);
				panel.add(campoCpf);
				campoCpf.setColumns(10);
			}
			{
				campoEndereco = new JTextField();
				campoEndereco.setBounds(81, 149, 340, 20);
				panel.add(campoEndereco);
				campoEndereco.setColumns(10);
			}
			{
				JLabel lblDataDeNascimento = new JLabel("Data de Nascimento:");
				lblDataDeNascimento.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblDataDeNascimento.setBounds(10, 100, 150, 14);
				panel.add(lblDataDeNascimento);
			}
			
			campoDataNascimento = new JFormattedTextField();
			campoDataNascimento.setBounds(170, 97, 96, 20);
			panel.add(campoDataNascimento);
			try {
				campoDataNascimento.setFormatterFactory(new DefaultFormatterFactory( new MaskFormatter( "##/##/####" )));
			} catch (Exception e) {
				display.setText(e.getMessage());
			}
			
			display = new JLabel("");
			display.setBounds(10, 222, 524, 20);
			panel.add(display);
		}
		{
			JLabel lblCadastrarHospede = new JLabel("CADASTRAR HOSPEDE");
			lblCadastrarHospede.setHorizontalAlignment(SwingConstants.CENTER);
			lblCadastrarHospede.setFont(new Font("Simplified Arabic", Font.BOLD, 20));
			lblCadastrarHospede.setBounds(10, 11, 544, 36);
			contentPanel.add(lblCadastrarHospede);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.LIGHT_GRAY);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							hospede = new Hospede( campoNome.getText(), campoCpf.getText(), campoEndereco.getText());
							hospede.setDataNascimento(campoDataNascimento.getText());
							if( hotel.isHospedeCadastrado( hospede.getCpf()) ) {
								display.setText("Hospede ja cadastrado com este CPF.");
							}else{
								Main.hotel.adicionaHospede(hospede);
								dispose();
							}
						} catch (Exception e) {
							display.setText(e.getMessage());
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
