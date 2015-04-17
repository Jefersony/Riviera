package interfacegrafica;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import classesprincipais.*;
import interfacegrafica.Main;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.DropMode;
import javax.swing.JScrollPane;
import java.awt.Color;

public class RemoverHospede extends JDialog {
	private Hotel hotel = Main.getHotel();
	private Hospede hospede;

	private final JPanel contentPanel = new JPanel();
	private JTextField campoNome;
	private JTextField campoCpf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RemoverHospede dialog = new RemoverHospede();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void limparEntradas() {
		campoNome.setText("");
		campoCpf.setText("");
	}

	/**
	 * Create the dialog.
	 */
	public RemoverHospede() {
		setBounds(100, 100, 580, 460);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(10, 89, 544, 92);
		contentPanel.add(panel);
		
		JLabel label = new JLabel("Nome:");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(10, 11, 46, 14);
		panel.add(label);
		
		JLabel label_1 = new JLabel("CPF:");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_1.setBounds(10, 54, 46, 14);
		panel.add(label_1);
		
		campoNome = new JTextField();
		campoNome.setColumns(10);
		campoNome.setBounds(78, 8, 293, 20);
		panel.add(campoNome);
		
		campoCpf = new JTextField();
		campoCpf.setColumns(10);
		campoCpf.setBounds(78, 51, 144, 20);
		panel.add(campoCpf);
		//areaTexto.setText(String.format("rmv hosp inicio: %d", Main.hotel.getListaGeralHospedes().size()));
		
		JLabel lblRemoverHospede = new JLabel("REMOVER HOSPEDE");
		lblRemoverHospede.setHorizontalAlignment(SwingConstants.CENTER);
		lblRemoverHospede.setFont(new Font("Simplified Arabic", Font.BOLD, 20));
		lblRemoverHospede.setBounds(10, 11, 544, 36);
		contentPanel.add(lblRemoverHospede);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 192, 544, 185);
		contentPanel.add(scrollPane);
		
		final JTextArea display = new JTextArea();
		scrollPane.setViewportView(display);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("REMOVER");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if( campoNome.getText().equals("") && campoCpf.getText().equals("") ) {
							display.setText("Primeiramente uma pesquisa por nome pode ser feita para verificar se\n"
									+ "ha hospedes no hotel com o nome ou CPF informados.");
						}else if(campoNome.getText().equals("")) {
							display.setText("Por questao de seguranca, o nome do hospede a ser removido tambem deve ser informado.");
						}else{
							try {
								hospede = new Hospede( campoNome.getText(), campoCpf.getText());
								if( hotel.removerHospede(hospede)){ // Main.getHotel().removerHospede(hospede)
									display.setText("Hospede Removido.");
								}else{
									if(campoCpf.getText().equals("")) {
										display.setText("O campo CPF esta vazio.");
									}else{
										display.setText("O hospede nao foi removido.");
									}
								}
							} catch (Exception e) {
								//System.out.println( e.getMessage());
								display.setText("Erro na operacao.");
							}						
							
							}
						List<Contrato> contratos = Main.getHotel().getContratos(); 
						for( int i=0; i<contratos.size(); i++ ) {
							Contrato c = contratos.get(i);
							if( c.getHospede().getCpf().equals(campoCpf.getText()) ||
								c.getHospede().getNome().equals(campoNome.getText()) && 
								c.getHospede().getCpf().equals(campoCpf.getText()) )  {
								Main.getHotel().getContratos().remove(c);
							}
						}
						}
				});
				
				JButton btnPesquisar = new JButton("Pesquisar");
				btnPesquisar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						display.setText("");
						if( campoNome.getText().equals("") && campoCpf.getText().equals("") ) {
							display.setText("Insira alguma informacao do hospede em um dos campos de dados.");
						}else if( ( campoCpf.getText().equals("") && !(campoNome.getText().equals("")) ) ){
							//display.setText("O CPF do cliente nao foi informado.");
							if( hotel.getListaGeralHospedes().isEmpty() ) display.setText("O Hotel esta vazio.");
							
							boolean hspdEncontrado = false;
							for( Hospede h : Main.getHotel().getListaGeralHospedes() ) {
								if( h.getNome().equals(campoNome.getText()) ){
									display.setText( display.getText() + "\n" + h.toString() + "\n");
									hspdEncontrado = true;
								}
							}
							if( hspdEncontrado == false ) display.setText( "Nenhum hospede foi encontrado com esse nome.");
						}
						else{
							try {
								//hospede = new Hospede( campoNome.getText(), campoCpf.getText());
								Hospede hspde= Main.getHotel().pesquisaHospede( campoCpf.getText());//Main.getHotel().pesquisaHospede( campoCpf.getText()).toString()
								display.setText(hspde.toString());
							} catch (Exception e) {
								display.setText(e.getMessage());
							}
						}
					}
				});
				
				JButton btnLimpar = new JButton("Limpar");
				btnLimpar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						campoNome.setText("");
						campoCpf.setText("");
						display.setText("");
					}
				});
				buttonPane.add(btnLimpar);
				buttonPane.add(btnPesquisar);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Fechar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						campoNome.setText("");
						campoCpf.setText("");
						display.setText("");
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
