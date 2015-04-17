package interfacegrafica.servicos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import classesprincipais.*;
import classesprincipais.Refeicao.Tipo;
import interfacegrafica.AdicionarServico;

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

public class AdicionarRefeicao extends JDialog {
	private Servico refeicao;

	private final JPanel contentPanel = new JPanel();
	private JTextField display;
	private JTextField campoDescricao;
	private JTextField campoPreco;
	private JComboBox comboBoxRefeicao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AdicionarRefeicao dialog = new AdicionarRefeicao();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AdicionarRefeicao() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblAdicionarRefeicao = new JLabel("ADICIONAR REFEICAO");
			lblAdicionarRefeicao.setHorizontalAlignment(SwingConstants.CENTER);
			lblAdicionarRefeicao.setFont(new Font("Simplified Arabic", Font.BOLD, 20));
			lblAdicionarRefeicao.setBounds(10, 11, 414, 36);
			contentPanel.add(lblAdicionarRefeicao);
		}
		{
			display = new JTextField();
			display.setColumns(10);
			display.setBounds(10, 58, 414, 20);
			contentPanel.add(display);
		}
		{
			JLabel lblDescricao = new JLabel("Descricao:");
			lblDescricao.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblDescricao.setBounds(10, 89, 93, 20);
			contentPanel.add(lblDescricao);
		}
		{
			campoDescricao = new JTextField();
			campoDescricao.setBounds(113, 89, 272, 20);
			contentPanel.add(campoDescricao);
			campoDescricao.setColumns(10);
		}
		{
			JLabel lblPreco = new JLabel("Preco:");
			lblPreco.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblPreco.setBounds(10, 120, 93, 20);
			contentPanel.add(lblPreco);
		}
		{
			campoPreco = new JTextField();
			campoPreco.setBounds(113, 120, 138, 20);
			contentPanel.add(campoPreco);
			campoPreco.setColumns(10);
		}
		{
			JLabel label = new JLabel("Tipo:");
			label.setFont(new Font("Tahoma", Font.BOLD, 11));
			label.setBounds(10, 154, 71, 14);
			contentPanel.add(label);
		}
		{
			String[] tiposDeRefeicao = {"", "Almoco", "Jantar", "Lanche", "Bebida", "Sobremesa"};
			comboBoxRefeicao = new JComboBox(tiposDeRefeicao);
			comboBoxRefeicao.setBounds(113, 151, 110, 20);
			contentPanel.add(comboBoxRefeicao);
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
							if( comboBoxRefeicao.getSelectedIndex() == 0 ) {
								display.setText( "Escolha o tipo de refeicao.");
							}else{
								String descricao = campoDescricao.getText();
								String strPreco = campoPreco.getText().replace(',', '.');
								double preco = Double.parseDouble(strPreco);
								
								if( comboBoxRefeicao.getSelectedIndex() == 1 )
									refeicao = new Refeicao( Tipo.ALMOCO, descricao, preco);
								else if( comboBoxRefeicao.getSelectedIndex() == 2 )
									refeicao = new Refeicao( Tipo.JANTAR, descricao, preco);
								else if( comboBoxRefeicao.getSelectedIndex() == 3 )
									refeicao = new Refeicao( Tipo.LANCHE, descricao, preco);
								else if( comboBoxRefeicao.getSelectedIndex() == 4 )
									refeicao = new Refeicao( Tipo.BEBIDA, descricao, preco);
								else 
									refeicao = new Refeicao( Tipo.JANTAR, descricao, preco);
								
								AdicionarServico.adicionarServico(refeicao);
								display.setText( "Servico adicionado com sucesso");
								dispose();
							}
						} catch (Exception e2) {
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
