package interfacegrafica.servicos;

import interfacegrafica.AdicionarServico;

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
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class NovoServico extends JDialog {
	private OutroServico novoServico;

	private final JPanel contentPanel = new JPanel();
	private JTextField campoDescricao;
	private JTextField campoPreco;
	private JTextField display;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NovoServico dialog = new NovoServico();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void limparEntradas() {
		campoDescricao.setText("");
		campoPreco.setText("");
	}

	/**
	 * Create the dialog.
	 */
	public NovoServico() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNovoServico = new JLabel("NOVO SERVICO");
		lblNovoServico.setBounds(10, 11, 414, 36);
		lblNovoServico.setHorizontalAlignment(SwingConstants.CENTER);
		lblNovoServico.setFont(new Font("Simplified Arabic", Font.BOLD, 20));
		contentPanel.add(lblNovoServico);
		{
			JLabel label = new JLabel("Descricao:");
			label.setFont(new Font("Tahoma", Font.BOLD, 11));
			label.setBounds(10, 100, 97, 14);
			contentPanel.add(label);
		}
		{
			campoDescricao = new JTextField();
			campoDescricao.setColumns(10);
			campoDescricao.setBounds(117, 97, 283, 20);
			contentPanel.add(campoDescricao);
		}
		{
			JLabel label = new JLabel("Preco:");
			label.setFont(new Font("Tahoma", Font.BOLD, 11));
			label.setBounds(10, 149, 46, 14);
			contentPanel.add(label);
		}
		{
			campoPreco = new JTextField();
			campoPreco.setColumns(10);
			campoPreco.setBounds(117, 146, 97, 20);
			contentPanel.add(campoPreco);
		}
		
		display = new JTextField();
		display.setColumns(10);
		display.setBounds(10, 58, 414, 20);
		contentPanel.add(display);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							novoServico = new OutroServico( campoDescricao.getText(), campoPreco.getText());
							AdicionarServico.adicionarServico(novoServico);
							dispose();
						} catch (Exception e) {
							display.setText("Operacao invalida.");
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
