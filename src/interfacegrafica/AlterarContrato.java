package interfacegrafica;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import classesprincipais.*;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import java.awt.Color;

import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.JSlider;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class AlterarContrato extends JDialog {
	private Contrato contratoAtual;

	private final JPanel contentPanel = new JPanel();
	private JTextField campoCartao;
	private JTextField campoCpf;
	private JTextField displayVerificar;
	private JTextField campoNovoCartao;
	private JTextField campoNovaDataInicio;
	private JTextField campoNovaDataFim;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AlterarContrato dialog = new AlterarContrato();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void limparEntradas() {
		displayVerificar.setText("");
		campoNovaDataFim.setText("");
		campoCartao.setText("");
		campoNovoCartao.setText("");
		campoCpf.setText("");
		campoNovaDataInicio.setText("");
	}

	/**
	 * Create the dialog.
	 */
	public AlterarContrato() {
		setBounds(100, 100, 580, 460);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblAlterarContrato = new JLabel("ALTERAR CONTRATO");
			lblAlterarContrato.setHorizontalAlignment(SwingConstants.CENTER);
			lblAlterarContrato.setFont(new Font("Simplified Arabic", Font.BOLD, 20));
			lblAlterarContrato.setBounds(10, 11, 544, 36);
			contentPanel.add(lblAlterarContrato);
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			panel.setLayout(null);
			panel.setBounds(10, 58, 544, 90);
			contentPanel.add(panel);
			{
				JLabel lblNCartao = new JLabel("N. Cartao:");
				lblNCartao.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblNCartao.setBounds(10, 11, 70, 14);
				panel.add(lblNCartao);
			}
			{
				JLabel lblDataDeInicio = new JLabel("CPF do Cliente:");
				lblDataDeInicio.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblDataDeInicio.setBounds(278, 11, 102, 14);
				panel.add(lblDataDeInicio);
			}
			{
				campoCartao = new JTextField();
				campoCartao.setColumns(10);
				campoCartao.setBounds(90, 8, 165, 20);
				panel.add(campoCartao);
			}
			{
				campoCpf = new JTextField();
				campoCpf.setColumns(10);
				campoCpf.setBounds(390, 8, 144, 20);
				panel.add(campoCpf);
			}
			{
				JButton btnProcurar = new JButton("Procurar");
				btnProcurar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						List<Contrato> contratos = Main.getHotel().getContratos(); 
						for( int i=0; i<contratos.size(); i++ ) {
							Contrato c = contratos.get(i);
							Hospede clienteAtual = c.getHospede();
							if( c.getNumCartao().equals(campoCartao.getText()) ||
								clienteAtual.getCpf().equals(campoCpf.getText()) ) {
								displayVerificar.setText( "Contrato encontrado.");
								campoNovoCartao.setEnabled( true );
								campoNovaDataInicio.setEnabled( true );
								campoNovaDataFim.setEnabled( true );
								contratoAtual = c;
							}
						}
					}
				});
				btnProcurar.setBounds(10, 56, 97, 23);
				panel.add(btnProcurar);
			}
			
			displayVerificar = new JTextField();
			displayVerificar.setBounds(117, 57, 263, 22);
			panel.add(displayVerificar);
			displayVerificar.setColumns(10);
			
			JButton btnAlerarHospede = new JButton("Alterar Hospede");
			btnAlerarHospede.setEnabled(false);
			btnAlerarHospede.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			btnAlerarHospede.setBounds(390, 56, 144, 23);
			panel.add(btnAlerarHospede);
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			panel.setBounds(10, 159, 544, 218);
			contentPanel.add(panel);
			panel.setLayout(null);
			
			JLabel lblNovoNCartao = new JLabel("Novo N. Cartao:");
			lblNovoNCartao.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNovoNCartao.setBounds(10, 11, 98, 14);
			panel.add(lblNovoNCartao);
			
			campoNovoCartao = new JTextField();
			campoNovoCartao.setEnabled(false);
			campoNovoCartao.setBounds(147, 8, 173, 20);
			panel.add(campoNovoCartao);
			campoNovoCartao.setColumns(10);
			
			JLabel lblNovaDataInicio = new JLabel("Nova Data Inicio:");
			lblNovaDataInicio.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNovaDataInicio.setBounds(10, 54, 127, 14);
			panel.add(lblNovaDataInicio);
			
			campoNovaDataInicio = new JTextField();
			campoNovaDataInicio.setEnabled(false);
			campoNovaDataInicio.setColumns(10);
			campoNovaDataInicio.setBounds(147, 51, 173, 20);
			panel.add(campoNovaDataInicio);
			
			JLabel lblNovaDataFim = new JLabel("Nova Data Fim:");
			lblNovaDataFim.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNovaDataFim.setBounds(10, 106, 98, 14);
			panel.add(lblNovaDataFim);
			
			campoNovaDataFim = new JTextField();
			campoNovaDataFim.setEnabled(false);
			campoNovaDataFim.setColumns(10);
			campoNovaDataFim.setBounds(147, 103, 173, 20);
			panel.add(campoNovaDataFim);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Atualizar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if( !(campoNovoCartao.getText().equals("")) ) contratoAtual.setNumCartao(campoNovoCartao.getText());
						if( !(campoNovaDataInicio.getText().equals("")) ) contratoAtual.setDataInicioContrato(campoNovaDataInicio.getText());
						if( !(campoNovaDataFim.getText().equals("")) ) contratoAtual.setDataFimContrato(campoNovaDataFim.getText());
						
						dispose();
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
						campoNovoCartao.setEnabled( false );
						campoNovaDataInicio.setEnabled( false );
						campoNovaDataFim.setEnabled( false );
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
