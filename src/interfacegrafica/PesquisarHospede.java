package interfacegrafica;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import classesprincipais.Hospede;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class PesquisarHospede extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField campoNome;
	private JTextField campoCpf;
	private String nome;
	private String cpf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PesquisarHospede dialog = new PesquisarHospede();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getNome() {
		return nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void limparEntradas() {
		campoNome.setText("");
		campoCpf.setText("");
	}

	/**
	 * Create the dialog.
	 */
	public PesquisarHospede() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 90, 46, 14);
		contentPanel.add(lblNewLabel);
		
		JLabel lblCpf = new JLabel("CPF: ");
		lblCpf.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCpf.setBounds(10, 157, 46, 14);
		contentPanel.add(lblCpf);
		
		campoNome = new JTextField();
		campoNome.setBounds(66, 87, 280, 20);
		contentPanel.add(campoNome);
		campoNome.setColumns(10);
		
		campoCpf = new JTextField();
		campoCpf.setBounds(66, 154, 144, 20);
		contentPanel.add(campoCpf);
		campoCpf.setColumns(10);
		
		JLabel lblPesquisarHospede = new JLabel("PESQUISAR HOSPEDE");
		lblPesquisarHospede.setBackground(Color.WHITE);
		lblPesquisarHospede.setHorizontalAlignment(SwingConstants.CENTER);
		lblPesquisarHospede.setFont(new Font("Simplified Arabic", Font.BOLD, 20));
		lblPesquisarHospede.setBounds(10, 11, 414, 36);
		contentPanel.add(lblPesquisarHospede);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						nome = campoNome.getText();
						cpf = campoCpf.getText();
						Main.areaTexto.setText("");
						if( Main.getHotel().getListaGeralHospedes().isEmpty() ) Main.areaTexto.setText("Hotel vazio.");
						for( Hospede h : Main.getHotel().getListaGeralHospedes() ) {
							if( h.getNome().equals(nome) || h.getCpf().equals(cpf)) 
								Main.areaTexto.setText( Main.areaTexto.getText() + "\n" + h.toString() + "\n");
						}
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
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
