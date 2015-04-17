package interfacegrafica;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import classesprincipais.*;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JFormattedTextField;
import javax.swing.text.*;

public class AlterarHospede extends JDialog {
	private int indiceDoHospede;
	private List<Hospede> listaHospedes;

	private final JPanel contentPanel = new JPanel();
	private JTextField campoNovoNome;
	private JTextField campoNovoCpf;
	private JTextField campoNovoEndereco;
	private JTextField campoNome;
	private JTextField campoCpf;
	private JTextField display;
	private JFormattedTextField campoNovaDataNascimento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AlterarHospede dialog = new AlterarHospede();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void limparEntradas() {
		campoNome.setText("");
		campoCpf.setText("");
		campoNovoEndereco.setText("");
		campoNovoNome.setText("");
		campoNovoCpf.setText("");
		campoNovoEndereco.setText("");
		campoNovaDataNascimento.setText("");
		display.setText("");
	}

	/**
	 * Create the dialog.
	 */
	public AlterarHospede() {
		setBounds(100, 100, 580, 460);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(192, 192, 192));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			panel.setBounds(10, 191, 544, 186);
			contentPanel.add(panel);
			{
				JLabel lblNovoNome = new JLabel("Novo Nome:");
				lblNovoNome.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblNovoNome.setBounds(10, 27, 82, 14);
				panel.add(lblNovoNome);
			}
			{
				JLabel lblNovoCpf = new JLabel("Novo CPF:");
				lblNovoCpf.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblNovoCpf.setBounds(10, 70, 82, 14);
				panel.add(lblNovoCpf);
			}
			{
				JLabel lblNovoEndereco = new JLabel("Novo Endereco:");
				lblNovoEndereco.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblNovoEndereco.setBounds(10, 158, 113, 14);
				panel.add(lblNovoEndereco);
			}
			{
				campoNovoNome = new JTextField();
				campoNovoNome.setColumns(10);
				campoNovoNome.setBounds(133, 24, 340, 20);
				panel.add(campoNovoNome);
			}
			{
				campoNovoCpf = new JTextField();
				campoNovoCpf.setColumns(10);
				campoNovoCpf.setBounds(133, 67, 188, 20);
				panel.add(campoNovoCpf);
			}
			{
				campoNovoEndereco = new JTextField();
				campoNovoEndereco.setColumns(10);
				campoNovoEndereco.setBounds(133, 155, 340, 20);
				panel.add(campoNovoEndereco);
			}
			
			JLabel label = new JLabel("Data de Nascimento:");
			label.setFont(new Font("Tahoma", Font.BOLD, 11));
			label.setBounds(10, 113, 150, 14);
			panel.add(label);
			
			campoNovaDataNascimento = new JFormattedTextField();
			campoNovaDataNascimento.setBounds(170, 110, 96, 20);
			panel.add(campoNovaDataNascimento);
			try {
				campoNovaDataNascimento.setFormatterFactory(new DefaultFormatterFactory( new MaskFormatter( "##/##/####" )));
			} catch (Exception e) {
				display.setText(e.getMessage());
			}
		}
		{
			JLabel lblAlterarHospede = new JLabel("ALTERAR INFORMACOES DO HOSPEDE");
			lblAlterarHospede.setHorizontalAlignment(SwingConstants.CENTER);
			lblAlterarHospede.setFont(new Font("Simplified Arabic", Font.BOLD, 20));
			lblAlterarHospede.setBounds(10, 11, 544, 36);
			contentPanel.add(lblAlterarHospede);
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			panel.setBackground(new Color(240, 240, 240));
			panel.setBounds(10, 58, 544, 111);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblNome = new JLabel("Nome:");
				lblNome.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblNome.setBounds(10, 14, 56, 14);
				panel.add(lblNome);
			}
			{
				campoNome = new JTextField();
				campoNome.setColumns(10);
				campoNome.setBounds(76, 8, 340, 20);
				panel.add(campoNome);
			}
			{
				JLabel lblCpf = new JLabel("CPF:");
				lblCpf.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblCpf.setBounds(10, 42, 56, 14);
				panel.add(lblCpf);
			}
			{
				campoCpf = new JTextField();
				campoCpf.setColumns(10);
				campoCpf.setBounds(76, 39, 188, 20);
				panel.add(campoCpf);
			}
			{
				JButton btnVerificar = new JButton("VERIFICAR");
				btnVerificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						boolean bandeira = true;
						listaHospedes = Main.getHotel().getListaGeralHospedes();
						for( int i=0; i<listaHospedes.size(); i++ ) { // este for eh necessario por causa do indice do hosp q deve ser guardado
							Hospede h = listaHospedes.get(i);        // e por causa da obrigatoriedade de informar ambos nome e CPF
							if( campoCpf.getText().equals(h.getCpf()) || 
								campoNome.getText().equals(h.getNome()) && campoCpf.getText().equals(h.getCpf()) ) {
								display.setText( "Hospede encontrado no sistema.");
								bandeira = false;
								indiceDoHospede = i;
								if( !(campoNome.getText().equals(h.getNome())) ) 
									display.setText( display.getText() + " Aviso: O nome nao confere.");
								break;
							}
						}
						if( bandeira ) {
							display.setText("Hospede nao encontrado no sistema.");
						}
					}
				});
				btnVerificar.setBounds(313, 39, 103, 23);
				panel.add(btnVerificar);
			}
			{
				display = new JTextField();
				display.setBackground(Color.WHITE);
				display.setEditable(false);
				display.setBounds(10, 80, 524, 20);
				panel.add(display);
				display.setColumns(10);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Atualizar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						display.setText( "" );
						Hospede hospede = Main.getHotel().getListaGeralHospedes().get(indiceDoHospede);
						Contrato contratoAtual = Main.getHotel().pesquisaContrato(hospede);
						if( !(campoNovoNome.getText().equals("")) ){
							hospede.setNome(campoNovoNome.getText());
							contratoAtual.getHospede().setNome(campoNovoNome.getText());
							display.setText("Nome atualizado;");
						}
						if( !( campoNovoCpf.getText().equals("")) ) {
							hospede.setCpf( campoNovoCpf.getText());
							contratoAtual.getHospede().setCpf(campoNovoCpf.getText());
							display.setText( display.getText() + " " + "CPF atualizado;" );
						}
						if( !(campoNovaDataNascimento.getText().contains(" "))) {
							hospede.setDataNascimento(campoNovaDataNascimento.getText());
							contratoAtual.getHospede().setDataNascimento(campoNovaDataNascimento.getText());
							display.setText( display.getText() + " " + "Data atualizada;" );
						}
						if( !( campoNovoEndereco.getText().equals("")) ) {
							hospede.setEndereco( campoNovoEndereco.getText());
							contratoAtual.getHospede().setEndereco(campoNovoEndereco.getText());
							display.setText( display.getText() + " " + "Endereco atualizado." );
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
