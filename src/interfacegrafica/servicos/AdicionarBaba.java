package interfacegrafica.servicos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import classesprincipais.*;
import interfacegrafica.AdicionarServico;

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
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.text.*;
import java.awt.Color;

public class AdicionarBaba extends JDialog {
	private Servico baba;

	private final JPanel contentPanel = new JPanel();
	private JTextField display;
	private JFormattedTextField campoHoraI;
	private JFormattedTextField campoHoraF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AdicionarBaba dialog = new AdicionarBaba();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AdicionarBaba() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblAdicionarBaba = new JLabel("ADICIONAR BABA");
			lblAdicionarBaba.setHorizontalAlignment(SwingConstants.CENTER);
			lblAdicionarBaba.setFont(new Font("Simplified Arabic", Font.BOLD, 20));
			lblAdicionarBaba.setBounds(10, 11, 414, 36);
			contentPanel.add(lblAdicionarBaba);
		}
		{
			JLabel lblHoraDoInicio = new JLabel("Hora do inicio:");
			lblHoraDoInicio.setBounds(73, 104, 95, 14);
			contentPanel.add(lblHoraDoInicio);
		}
		{
			JLabel lblHoraDoFim = new JLabel("Hora do fim:");
			lblHoraDoFim.setBounds(73, 140, 95, 14);
			contentPanel.add(lblHoraDoFim);
		}
		{
			display = new JTextField();
			display.setColumns(10);
			display.setBounds(10, 58, 414, 20);
			contentPanel.add(display);
		}
		{
			campoHoraI = new JFormattedTextField();
			campoHoraI.setBounds(178, 101, 95, 20);
			contentPanel.add(campoHoraI);
			try {
				campoHoraI.setFormatterFactory(new DefaultFormatterFactory( new MaskFormatter("##:##")));
			} catch (Exception e) {
				display.setText(e.getMessage());
			}
		}
		{
			campoHoraF = new JFormattedTextField();
			campoHoraF.setBounds(178, 137, 95, 20);
			contentPanel.add(campoHoraF);
			try {
				campoHoraF.setFormatterFactory(new DefaultFormatterFactory( new MaskFormatter("##:##")));
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
						if( campoHoraI.getText().contains(" ") || campoHoraF.getText().contains(" ") ) {
							display.setText( "Falta ser adicionado a hora do inicio ou do termino do servico.");
							
						}else{
							baba = new Baba( campoHoraI.getText(), campoHoraF.getText() );
							AdicionarServico.adicionarServico(baba);
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
