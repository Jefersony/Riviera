package interfacegrafica.servicos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import classesprincipais.*;
import classesprincipais.Carro.Tipo;
import interfacegrafica.AdicionarServico;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFormattedTextField;
import javax.swing.text.*;
import java.awt.Color;

public class AdicionarCarro extends JDialog {
	private Servico carro;

	private final JPanel contentPanel = new JPanel();
	private JTextField display;
	private JComboBox comboBoxTipoCarro;
	private JCheckBox chckbxTanqueCheio;
	private JCheckBox chckbxSeguro;
	private boolean comTanqueCheio=false;
	private boolean comSeguro=false;
	private JFormattedTextField campoPlaca;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AdicionarCarro dialog = new AdicionarCarro();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AdicionarCarro() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblAdicionarCarro = new JLabel("ADICIONAR CARRO");
			lblAdicionarCarro.setHorizontalAlignment(SwingConstants.CENTER);
			lblAdicionarCarro.setFont(new Font("Simplified Arabic", Font.BOLD, 20));
			lblAdicionarCarro.setBounds(10, 11, 414, 36);
			contentPanel.add(lblAdicionarCarro);
		}
		{
			JLabel label = new JLabel("Tipo:");
			label.setFont(new Font("Tahoma", Font.BOLD, 11));
			label.setBounds(10, 140, 46, 14);
			contentPanel.add(label);
		}
		{
			String[] tiposDeCarro = {"Executivo", "Luxo"};
			comboBoxTipoCarro = new JComboBox(tiposDeCarro);
			comboBoxTipoCarro.setBounds(66, 137, 135, 20);
			contentPanel.add(comboBoxTipoCarro);
		}
		{
			display = new JTextField();
			display.setColumns(10);
			display.setBounds(10, 58, 414, 20);
			contentPanel.add(display);
		}
		
		chckbxTanqueCheio = new JCheckBox("Tanque cheio");
		chckbxTanqueCheio.setBackground(Color.LIGHT_GRAY);
		chckbxTanqueCheio.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxTanqueCheio.setBounds(6, 181, 135, 23);
		contentPanel.add(chckbxTanqueCheio);
		
		chckbxSeguro = new JCheckBox("Seguro");
		chckbxSeguro.setBackground(Color.LIGHT_GRAY);
		chckbxSeguro.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxSeguro.setBounds(168, 181, 118, 23);
		contentPanel.add(chckbxSeguro);
		{
			JLabel lblPlaca = new JLabel("Placa:");
			lblPlaca.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblPlaca.setBounds(10, 100, 46, 14);
			contentPanel.add(lblPlaca);
		}
		{
			campoPlaca = new JFormattedTextField();
			campoPlaca.setBounds(66, 97, 135, 20);
			contentPanel.add(campoPlaca);
			try {
				campoPlaca.setFormatterFactory( new DefaultFormatterFactory( new MaskFormatter("UUU-####")));
			} catch (Exception e) {
				display.setText(e.getMessage());
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if( campoPlaca.getText().contains( " " ) ) {
							display.setText("Preencha o campo da placa.");
						}else{
							String placa = campoPlaca.getText();
							if( chckbxTanqueCheio.isSelected() == true )
								comTanqueCheio = true;
							if( chckbxSeguro.isSelected() == true )
								comSeguro = true;
							
							if( comboBoxTipoCarro.getSelectedIndex() == 0 ){
								carro = new Carro( placa, Tipo.EXECUTIVO, 0, comTanqueCheio, comSeguro);
							}
							else{
								carro = new Carro( placa, Tipo.LUXO, 0, comTanqueCheio, comSeguro );
							}
							AdicionarServico.adicionarServico(carro);
							display.setText( "Servico adicionado com sucesso.");
							dispose();
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Fechar");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
