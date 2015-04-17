package interfacegrafica.servicos;

import interfacegrafica.AdicionarServico;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import classesprincipais.*;
import classesprincipais.Quarto.Tipo;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AdicionarQuarto extends JDialog {
	private Servico quarto;
	//private AdicionarServico adicServ = new AdicionarServico();

	private final JPanel contentPanel = new JPanel();
	private JComboBox comboBoxTipoQuarto;
	private JTextField display;
	private JTextField campoReferencia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AdicionarQuarto dialog = new AdicionarQuarto();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AdicionarQuarto() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblAdicionarQuarto = new JLabel("ADICIONAR QUARTO");
			lblAdicionarQuarto.setHorizontalAlignment(SwingConstants.CENTER);
			lblAdicionarQuarto.setFont(new Font("Simplified Arabic", Font.BOLD, 20));
			lblAdicionarQuarto.setBounds(10, 11, 414, 36);
			contentPanel.add(lblAdicionarQuarto);
		}
		{
			JLabel lblTipo = new JLabel("Tipo:");
			lblTipo.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblTipo.setBounds(10, 92, 46, 14);
			contentPanel.add(lblTipo);
		}
		String[] tiposDeQuarto = { "Executivo Simples", "Executivo Duplo", "Executivo Triplo", 
				"Luxo Simples", "Luxo Duplo", "Luxo Triplo", "Suite Presidencial" };
		comboBoxTipoQuarto = new JComboBox(tiposDeQuarto);
		comboBoxTipoQuarto.setBounds(101, 89, 125, 20);
		contentPanel.add(comboBoxTipoQuarto);
		{
			display = new JTextField();
			display.setBounds(10, 58, 414, 20);
			contentPanel.add(display);
			display.setColumns(10);
		}
		
		JLabel lblReferencia = new JLabel("Referencia:");
		lblReferencia.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblReferencia.setBounds(10, 133, 71, 14);
		contentPanel.add(lblReferencia);
		
		campoReferencia = new JTextField();
		campoReferencia.setBounds(101, 130, 125, 20);
		contentPanel.add(campoReferencia);
		campoReferencia.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if( campoReferencia.getText().equals("") ) {
							display.setText("Digite o numero do quarto no campo Referencia.");
						}else{
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
							
							AdicionarServico.adicionarServico(quarto);
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
