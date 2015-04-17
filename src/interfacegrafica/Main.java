package interfacegrafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.SwingConstants;

import java.awt.FlowLayout;

import javax.swing.JMenuItem;

import java.awt.Toolkit;

import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import classesprincipais.*;
import classesprincipais.Contrato.Tarifacao;
import classesprincipais.Quarto.Tipo;

import java.awt.Color;

import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
/**
 * Classe de entrada do programa.
 * @author Jeferson Rolim Holanda
 * (c) 2015
 */
public class Main extends JFrame {
	public static Hotel hotel = new Hotel(); 
	private JPanel contentPane;
	private AdicionarCliente telaAddCliente = new AdicionarCliente();
	private AdicionarHospede telaCadastrarHospede = new AdicionarHospede();
	private AdicionarVariosHospedes telaCadastrarVariosHospedes = new AdicionarVariosHospedes();
	private RemoverHospede telaRmvHospede = new RemoverHospede();
	private PesquisarHospede telaPqsHospede = new PesquisarHospede();
	private PesquisarContrato telaPqsrContrato = new PesquisarContrato();
	private CriarContrato telaCriarContrato = new CriarContrato();
	private VisualizarContrato telaVisualizarContrato = new VisualizarContrato();
	private AlterarHospede telaAlterarHospede = new AlterarHospede();
	private AlterarContrato telaAlterarContrato = new AlterarContrato();
	private AdicionarServico telaAdicionarServico = new AdicionarServico();
	private RemoverContrato telaRemoverContrato = new RemoverContrato();
	private RemoverServico telaRemoverServico = new RemoverServico();
	private PesquisarServicos telaPesquisarServicos = new PesquisarServicos();
	private GerarFatura telaGerarFatura = new GerarFatura(); 
	public static JTextArea areaTexto = new JTextArea();
	private JTextPane statusDisplay; 
	private final Action sair = new Sair();
	private final Action adicionarCliente = new AcaoAdicionarCliente();
	private final Action adicionarHospede = new AcaoCadastrarHospede();
	private final Action adicionarVariosHospedes = new AcaoCadastrarVariosHospedes();
	private final Action removerHospede = new AcaoRemoverHospede();
	private final Action pesquisarHospede = new AcaoPesquisarHospede();
	private final Action listarHospedes = new AcaoListarHospedes();
	private final Action criarContrato = new AcaoCriarContrato();
	private final Action listarContratos = new AcaoListarContratos();
	private final Action visualizarContrato = new AcaoVisualizarContrato();
	private final Action alterarContrato = new AcaoAlterarContrato();
	private final Action alterarHospede = new AcaoAlterarHospede();
	private final Action limparDisplay = new LimparDisplay();
	private final Action adicionarServico = new AcaoAdicionarServico();
	private final Action removerContrato = new AcaoRemoverContrato();
	private final Action removerServico = new AcaoRemoverServico();
	private final Action pesquisarContrato = new AcaoPesquisarContrato();
	private final Action pesquisarServicos = new AcaoPesquisarServicos();
	private final Action gerarFatura = new AcaoGerarFatura();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Hospede hospDefault = new Hospede( "jeff", "123", "rua capitao joao alves");
					hospDefault.setDataNascimento("01/01/1980");
					Contrato cntrtDefault = new Contrato( new Hospede( "jeff", "123"), Tarifacao.NORMAL, "456", "01/01", "10/01");
					adicionarServicos(cntrtDefault);
					hotel.adicionaHospede(hospDefault);// hospede adicionado para testes 
					hotel.adicionarContrato(cntrtDefault);// contrato adicionado para testes
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static Hotel getHotel() {
		return Main.hotel;
	}
	public AdicionarCliente getAddCliente() {
		return this.telaAddCliente;
	}
	private static void adicionarServicos( Contrato cntrt ) {
		cntrt.adicionarServico(new Quarto(Tipo.EXECUTIVO_DUPLO));
		cntrt.adicionarServico(new Carro(Carro.Tipo.LUXO, 1, true, false));
		cntrt.adicionarServico(new Baba(0,0));
		cntrt.adicionarServico(new Refeicao(Refeicao.Tipo.BEBIDA, "Agua", 2));
		cntrt.adicionarServico(new OutroServico("outro servico", "0.0"));
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setBackground(Color.BLUE);
		setTitle("Sistema Hotello");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\VIN\u00CDCIUS ROLIM HOLAN\\Documents\\Computa\u00E7\u00E3o\\WorkSpaceDoEclipse\\PrototipoProjeto2014_2\\src\\icones\\City-Hotel-icon.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 460);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);
		
		JMenu mnTemporada = new JMenu("Temporada");
		mnTemporada.setEnabled(false);
		mnMenu.add(mnTemporada);
		
		JMenuItem mntmAlta = new JMenuItem("Alta");
		mnTemporada.add(mntmAlta);
		
		JMenuItem mntmNormal = new JMenuItem("Normal");
		mnTemporada.add(mntmNormal);
		
		JMenuItem mntmBaixa = new JMenuItem("Baixa");
		mnTemporada.add(mntmBaixa);
		
		JMenuItem mntmConfiguracoes = new JMenuItem("Configuracoes");
		mntmConfiguracoes.setEnabled(false);
		mnMenu.add(mntmConfiguracoes);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.setAction(sair);
		mnMenu.add(mntmSair);
		
		JMenu mnCheckin = new JMenu("Checkin");
		menuBar.add(mnCheckin);
		
		JMenuItem mntmAdicionarCliente = new JMenuItem("Adicionar Cliente");
		mntmAdicionarCliente.setAction(adicionarCliente);
		mnCheckin.add(mntmAdicionarCliente);
		
		JMenuItem mntmAdicionarAcompanhante = new JMenuItem("Adicionar Acompanhante");
		mntmAdicionarAcompanhante.setEnabled(false);
		mnCheckin.add(mntmAdicionarAcompanhante);
		
		JMenu mnHospedes = new JMenu("Hospedes");
		menuBar.add(mnHospedes);
		
		JMenu mnCriarHospedes = new JMenu("Cadastrar");
		mnHospedes.add(mnCriarHospedes);
		
		JMenuItem mntmUnico = new JMenuItem("Unico");
		mntmUnico.setAction(adicionarHospede);
		mnCriarHospedes.add(mntmUnico);
		
		JMenuItem mntmVarios = new JMenuItem("Varios");
		mntmVarios.setAction(adicionarVariosHospedes);
		mnCriarHospedes.add(mntmVarios);
		
		JMenuItem mntmAlterarDados = new JMenuItem("Alterar Dados");
		mntmAlterarDados.setAction(alterarHospede);
		mnHospedes.add(mntmAlterarDados);
		
		JMenuItem mntmRemoverHospede = new JMenuItem("Remover");
		mntmRemoverHospede.setAction(removerHospede);
		mnHospedes.add(mntmRemoverHospede);
		
		JMenu mnContratos = new JMenu("Contrato");
		menuBar.add(mnContratos);
		
		JMenuItem mntmCriarContrato = new JMenuItem("Criar Contrato");
		mntmCriarContrato.setAction(criarContrato);
		mnContratos.add(mntmCriarContrato);
		
		JMenuItem mntmVisualizarContrato = new JMenuItem("Visualizar Contrato");
		mntmVisualizarContrato.setAction(visualizarContrato);
		mnContratos.add(mntmVisualizarContrato);
		
		JMenuItem mntmAlterarContrato = new JMenuItem("Alterar Contrato");
		mntmAlterarContrato.setAction(alterarContrato);
		mnContratos.add(mntmAlterarContrato);
		
		JMenuItem mntmRemoverContrato = new JMenuItem("Remover Contrato");
		mntmRemoverContrato.setAction(removerContrato);
		mnContratos.add(mntmRemoverContrato);
		
		JMenu mnServicos = new JMenu("Servico");
		menuBar.add(mnServicos);
		
		JMenuItem mntmCriarServico = new JMenuItem("Novo");
		mntmCriarServico.setEnabled(false);
		mnServicos.add(mntmCriarServico);
		
		JMenuItem mntmAdicionarServico = new JMenuItem("Adicionar");
		mntmAdicionarServico.setAction(adicionarServico);
		mnServicos.add(mntmAdicionarServico);
		
		JMenu mnRemoverServico = new JMenu("Remover Servico");
		mnRemoverServico.setEnabled(false);
		mnServicos.add(mnRemoverServico);
		
		JMenuItem mntmDeCliente = new JMenuItem("De Cliente");
		mnRemoverServico.add(mntmDeCliente);
		
		JMenuItem mntmDoHotel = new JMenuItem("Do Hotel");
		mnRemoverServico.add(mntmDoHotel);
		
		JMenuItem mntmRemover = new JMenuItem("Remover");
		mntmRemover.setAction(removerServico);
		mnServicos.add(mntmRemover);
		
		JMenu mnCheckout = new JMenu("Checkout");
		menuBar.add(mnCheckout);
		
		JMenuItem mntmGerarFatura = new JMenuItem("Gerar Fatura");
		mntmGerarFatura.setAction(gerarFatura);
		mnCheckout.add(mntmGerarFatura);
		
		JMenu mnPesquisar = new JMenu("Pesquisar");
		menuBar.add(mnPesquisar);
		
		JMenuItem mntmHospede = new JMenuItem("Hospede");
		mntmHospede.setAction(pesquisarHospede);
		mnPesquisar.add(mntmHospede);
		
		JMenuItem mntmContrato = new JMenuItem("Contrato");
		mntmContrato.setAction(pesquisarContrato);
		mnPesquisar.add(mntmContrato);
		
		JMenuItem mntmServico = new JMenuItem("Servico");
		mntmServico.setAction(pesquisarServicos);
		mnPesquisar.add(mntmServico);
		
		JMenu mnListar = new JMenu("Listar");
		menuBar.add(mnListar);
		
		JMenuItem mntmListarHospedes = new JMenuItem("Hospedes");
		mntmListarHospedes.setAction(listarHospedes);
		mnListar.add(mntmListarHospedes);
		
		JMenuItem mntmContratos = new JMenuItem("Contratos");
		mntmContratos.setAction(listarContratos);
		mnListar.add(mntmContratos);
		
		JMenuItem mntmLimpar = new JMenuItem("Limpar");
		mntmLimpar.setAction(limparDisplay);
		mnListar.add(mntmLimpar);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setForeground(Color.BLUE);
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		statusDisplay = new JTextPane();
		statusDisplay.setBounds(145, 333, 409, 20);
		contentPane.add(statusDisplay);
		
		JLabel lblStatusDoDisplay = new JLabel("Status do Display:");
		lblStatusDoDisplay.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblStatusDoDisplay.setBounds(10, 339, 125, 14);
		contentPane.add(lblStatusDoDisplay);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setBounds(10, 11, 544, 311);
		contentPane.add(scrollPane);
		areaTexto.setEditable(false);
		
		
		scrollPane.setViewportView(areaTexto);
	}
	private class Sair extends AbstractAction {
		public Sair() {
			putValue(NAME, "Sair");
			putValue(SHORT_DESCRIPTION, "Fechar o sistema.");
		}
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	private class AcaoAdicionarCliente extends AbstractAction {
		public AcaoAdicionarCliente() {
			putValue(NAME, "Adicionar Cliente");
			putValue(SHORT_DESCRIPTION, "Adiciona um hospede ao hotel e cria um contrato para o hospede.");
		}
		public void actionPerformed(ActionEvent e) {
			try {
				telaAddCliente.limparEntradas();
				telaAddCliente.setVisible(true);
				//addCliente.show();
				//this.wait();
			} catch (Exception e2) {
				System.err.println( e2.getMessage());
			}
		}
	}
	private class AcaoCadastrarHospede extends AbstractAction {
		public AcaoCadastrarHospede() {
			putValue(NAME, "Unico");
			putValue(SHORT_DESCRIPTION, "Efetuar o cadastro de um hospede.");
		}
		public void actionPerformed(ActionEvent e) {
			telaCadastrarHospede.limparEntradas();
			telaCadastrarHospede.setVisible(true);
		}
	}
	private class AcaoCadastrarVariosHospedes extends AbstractAction {
		public AcaoCadastrarVariosHospedes() {
			putValue(NAME, "Varios");
			putValue(SHORT_DESCRIPTION, "Efetuar o cadastro de uma quantidade determinada de hospedes.");
		}
		public void actionPerformed(ActionEvent e) {
			telaCadastrarVariosHospedes.limparEntradas();
			telaCadastrarVariosHospedes.getCampoTotalHospedes().setEditable(true);
			telaCadastrarVariosHospedes.setVisible(true);
		}
	}
	private class AcaoRemoverHospede extends AbstractAction {
		public AcaoRemoverHospede() {
			putValue(NAME, "Remover");
			putValue(SHORT_DESCRIPTION, "Remove o hospede cuja o cpf e nome for informado.");
		}
		public void actionPerformed(ActionEvent e) {
			telaRmvHospede.limparEntradas();
			telaRmvHospede.setVisible(true);
		}
	}
	private class AcaoPesquisarHospede extends AbstractAction {
		public AcaoPesquisarHospede() {
			putValue(NAME, "Hospede");
			putValue(SHORT_DESCRIPTION, "Pesquisar hospede por nome ou CPF informados.");
		}
		public void actionPerformed(ActionEvent e) {
				try {
					areaTexto.setText("");
					telaPqsHospede.limparEntradas();
					telaPqsHospede.setVisible(true);
				} catch (Exception e2) {
					areaTexto.setText( "Erro no sistema.");
				}
		}
	}
	private class AcaoListarHospedes extends AbstractAction {
		public AcaoListarHospedes() {
			putValue(NAME, "Hospedes");
			putValue(SHORT_DESCRIPTION, "Exibe no display principal informacoes sobre todos os hospedes cadastrados no hotel.");
		}
		public void actionPerformed(ActionEvent e) {
			areaTexto.setText("");
			if( hotel.getListaGeralHospedes().isEmpty() ) {
				areaTexto.setText("\nNao ha hospedes cadastrados.");
				statusDisplay.setText("Hotel vazio");
			}
			else{
				statusDisplay.setText("Lista de Hospedes cadastrados.");
				areaTexto.setText("");
				int cont=1;
				for( Hospede h : hotel.getListaGeralHospedes() ) {
					areaTexto.setText(areaTexto.getText() + "\n"+ cont + " -- " + h.toString() + "\n");
					cont++;
				}
			}
		}
	}
	private class AcaoCriarContrato extends AbstractAction {
		public AcaoCriarContrato() {
			putValue(NAME, "Novo");
			putValue(SHORT_DESCRIPTION, "Cria um contrato para algum hospede ja cadastrado no hotel.");
		}
		public void actionPerformed(ActionEvent e) {
			telaCriarContrato.limparEntradas();
			telaCriarContrato.disponibilizaNome();
			telaCriarContrato.disponibilizaCpf();
			telaCriarContrato.setVisible(true);
		}
	}
	private class AcaoListarContratos extends AbstractAction {
		public AcaoListarContratos() {
			putValue(NAME, "Contratos");
			putValue(SHORT_DESCRIPTION, "Exibe informacoes sobre todos os contratos do hotel no display principal.");
		}
		public void actionPerformed(ActionEvent e) {
			areaTexto.setText("");
			try {
				statusDisplay.setText("Lista de Contratos cadastrados.");
				if( hotel.getContratos().isEmpty() ) areaTexto.setText("\nNao ha contratos cadastrados.");
				else {
					int cont=1;
					for( Contrato c : hotel.getContratos() ) {
						areaTexto.setText( areaTexto.getText() + "\n"+ cont + " -- " + c.toString() + "\n");
						cont++;
					}
				}
			} catch (Exception excep) {
				System.out.println( excep.getMessage());
			}
		}
	}
	private class AcaoVisualizarContrato extends AbstractAction {
		public AcaoVisualizarContrato() {
			putValue(NAME, "Visualizar");
			putValue(SHORT_DESCRIPTION, "Exibe informacoes sobre o contrato atraves de uma busca por n. de cartao de data de inicio.");
		}
		public void actionPerformed(ActionEvent e) {
			telaVisualizarContrato.limparEntradaDados();
			telaVisualizarContrato.setVisible(true);
		}
	}
	private class AcaoAlterarContrato extends AbstractAction {
		public AcaoAlterarContrato() {
			putValue(NAME, "Atualizar");
			putValue(SHORT_DESCRIPTION, "Alterar dados de um contrato ja cadastrado no sistema.");
		}
		public void actionPerformed(ActionEvent e) {
			telaAlterarContrato.limparEntradas();
			telaAlterarContrato.setVisible(true);
		}
	}
	private class AcaoAlterarHospede extends AbstractAction {
		public AcaoAlterarHospede() {
			putValue(NAME, "Atualizar");
			putValue(SHORT_DESCRIPTION, "Alterar dados sobre um hospede cadastrado no sistema.");
		}
		public void actionPerformed(ActionEvent e) {
			telaAlterarHospede.limparEntradas();
			telaAlterarHospede.setVisible(true);
		}
	}
	private class LimparDisplay extends AbstractAction {
		public LimparDisplay() {
			putValue(NAME, "Limpar");
			putValue(SHORT_DESCRIPTION, "Limpar display principal.");
		}
		public void actionPerformed(ActionEvent e) {
			areaTexto.setText("");
			statusDisplay.setText("");
		}
	}
	private class AcaoAdicionarServico extends AbstractAction {
		public AcaoAdicionarServico() {
			putValue(NAME, "Adicionar");
			putValue(SHORT_DESCRIPTION, "Adicionar um servico ao contrato do hospede.");
		}
		public void actionPerformed(ActionEvent e) {
			telaAdicionarServico.limparEntradas();
			telaAdicionarServico.disponibilizarBotoes(false);
			telaAdicionarServico.setVisible(true);
		}
	}
	private class AcaoRemoverContrato extends AbstractAction {
		public AcaoRemoverContrato() {
			putValue(NAME, "Remover");
			putValue(SHORT_DESCRIPTION, "Remove um contrato cadastrado.");
		}
		public void actionPerformed(ActionEvent e) {
			telaRemoverContrato.limparEntradas();
			telaRemoverContrato.setVisible(true);
		}
	}
	private class AcaoRemoverServico extends AbstractAction {
		public AcaoRemoverServico() {
			putValue(NAME, "Remover");
			putValue(SHORT_DESCRIPTION, "Remover servico do contrato de um cliente.");
		}
		public void actionPerformed(ActionEvent e) {
			telaRemoverServico.limparEntradas();
			telaRemoverServico.setVisible(true);
		}
	}
	private class AcaoPesquisarContrato extends AbstractAction {
		public AcaoPesquisarContrato() {
			putValue(NAME, "Contrato");
			putValue(SHORT_DESCRIPTION, "Mostra no display principal os contratos que possuem as informacoes dadas.");
		}
		public void actionPerformed(ActionEvent e) {
			telaPqsrContrato.setVisible(true);
		}
	}
	private class AcaoPesquisarServicos extends AbstractAction {
		public AcaoPesquisarServicos() {
			putValue(NAME, "Servicos");
			putValue(SHORT_DESCRIPTION, "Lista todos os servicos consumidos pelo cliente.");
		}
		public void actionPerformed(ActionEvent e) {
			telaPesquisarServicos.limparEntradas();
			telaPesquisarServicos.setVisible(true);
		}
	}
	private class AcaoGerarFatura extends AbstractAction {
		public AcaoGerarFatura() {
			putValue(NAME, "Gerar Fatura");
			putValue(SHORT_DESCRIPTION, "Gera fatura a ser paga pelo cliente.");
		}
		public void actionPerformed(ActionEvent e) {
			telaGerarFatura.limparEntradas();
			telaGerarFatura.setVisible(true);
		}
	}
}
