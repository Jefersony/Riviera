package classesprincipais;

import java.util.*;
import classesprincipais.Contrato.Tarifacao;
/**
 * Classe principal do sistema que representa o hotel.
 * @author Jeferson Rolim Holanda
 *
 */
public class Hotel {
	private String data;
	private static Contrato.Tarifacao tarifacao = Tarifacao.NORMAL;
	private List<Contrato> contratos = new ArrayList<Contrato>();
	private List<Hospede> listaGeralHospedes = new ArrayList<>();
	private List<Opiniao> opinioes = new ArrayList<Opiniao>();
	/**
	 * Enumeracao que faz o controle do aluguel de quartos. Guarda o total disponivel e o total maximo de cada quarto.
	 * @author Jeferson Rolim Holanda
	 *
	 */
	public enum Disponibilidade {
		PRESIDENCIAL( 5, 5 ), 
		LUXO_SIMPLES( 5, 5 ), LUXO_DUPLO( 15, 15 ), LUXO_TRIPLO( 20, 20 ), 
		EXECUTIVO_SIMPLES( 5, 5 ), EXECUTIVO_DUPLO( 15, 15 ), EXECUTIVO_TRIPLO( 20, 20 );
		private int totalDisponivel;
		private int totalMaximo;
		
		Disponibilidade( int dispo, int qntd ) {
			this.totalDisponivel = dispo;
			this.totalMaximo = qntd;
		}
		public int getTotalMaximo() {
			return totalMaximo;
		}
		public int getTotalDisponivel() {
			return totalDisponivel;
		}
	}
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public List<Contrato> getContratos() {
		return contratos;
	}
	public void setContratos(List<Contrato> contratos) {
		this.contratos = contratos;
	}
	public void adicionarContrato( Contrato cont ) {
		this.contratos.add( cont );
	}
	/**
	 * Remove o contrato do hospede passado como parametro.
	 * @param cliente
	 */
	public void removerContrato( Hospede cliente ) {
		for( Contrato c : new ArrayList<Contrato>( contratos )) {
			if( c.getHospede().equals(cliente)) contratos.remove(c);
		}
	}
	public List<Opiniao> getOpinioes() {
		return opinioes;
	}
	public void setOpinioes(List<Opiniao> opinioes) {
		this.opinioes = opinioes;
	}
	/**
	 * Adiciona uma opiniao a lista de opinioes do hotel. 
	 * @param op
	 */
	public void adicionarOpiniao( Opiniao op ) {
		this.opinioes.add(op);
	}
	/**
	 * Remove a opiniao da colecao de opinioes do hotel que possuir a mesma data e o mesmo comentario.
	 * @param comentario
	 * @param data
	 */
	public void removerOpiniao( String comentario, String data ) {
		ArrayList<Opiniao> copiaLista = new ArrayList<Opiniao>( opinioes );
		for( Opiniao op : copiaLista ) {
			if( op.getComentario().equals(comentario) && op.getData().equals(data))
				opinioes.remove(op);
		}
	}
	public List<Hospede> getListaGeralHospedes() {
		return listaGeralHospedes;
	}
	public void setListaGeralHospedes(List<Hospede> listaGeralHospedes) {
		this.listaGeralHospedes = listaGeralHospedes;
	}
	public Contrato.Tarifacao getTarifacao() {
		return tarifacao;
	}
	public void setTarifacao(Contrato.Tarifacao tarifacao) {
		Hotel.tarifacao = tarifacao;
	}
	/**
	 * Adiciona um hospede na colecao de hospedes do hotel.
	 * @param hospede
	 * 		Hospede a ser adicionado.
	 */
	public void adicionaHospede( Hospede hospede ) {
		this.listaGeralHospedes.add(hospede);
	}
	/**
	 * Remove da colecao de hospedes do hotel o hospede que possuir as mesmas informacoes do hospede passado como parametro. 
	 * @param hsp
	 * @return
	 * 		Retorna true se a operacao foi concluida com sucesso, false caso contrario.
	 */
	public boolean removerHospede( Hospede hsp ) {
		for( Hospede h : new ArrayList<Hospede>( this.listaGeralHospedes)) {
			if( h.getCpf().equals(hsp.getCpf()) || h.getNome().equals(hsp.getNome()) && h.getCpf().equals(hsp.getCpf()) ) {
				this.listaGeralHospedes.remove(h);
				return true;
			}
		}
		return false;
	}
	/**
	 * Retorna da colecao de hospedes do hotel o hospede que possuir as mesmas informacoes do hospede passado como parametro.
	 * @param hosp
	 * @return
	 * 		O hospede encontrado na lista do hotel.
	 */
	public Hospede pesquisaHospede( Hospede hosp ) {
		if( this.listaGeralHospedes.isEmpty() ) throw new IllegalArgumentException( "Nao ha hospedes no hotel.");
		//if( !(this.listaGeralHospedes.contains(hosp))) throw new IllegalArgumentException( "Nao ha hospede com essa descricao."); //atencao
		for( Hospede h : this.listaGeralHospedes ) {
			if( hosp.getNome().equals(h.getNome()) && hosp.getCpf().equals(h.getCpf()) ) return h; 
		}
		return new Hospede("invalido", "invalido");
	}
	/**
	 * Atualiza o nome do hospede que for passado como parametro para o novo nome que tambem e passado como parametro.
	 * @param hosp
	 * 		Hospede a ter o nome alterado.
	 * @param novoNome
	 * 		Novo nome para o hospede.
	 */
	public void atualizarNomeHospede( Hospede hosp, String novoNome ) {
		if( this.listaGeralHospedes.isEmpty() ) throw new IllegalArgumentException( "Nao ha hospedes no hotel.");
		//if( !(this.listaGeralHospedes.contains(hosp))) throw new IllegalArgumentException( "Nao ha hospede com essa descricao."); //atencao
		for( Hospede h : this.listaGeralHospedes ) {
			if( hosp.getNome().equals(h.getNome()) && hosp.getCpf().equals(h.getCpf()) ) h.setNome(novoNome); 
		}
	}
	/**
	 * Atualiza o CPF do hospede que for passado como parametro para o novo CPF que tambem e passado como parametro.
	 * @param hosp
	 * 		Hospede a ter o CPF alterado.
	 * @param novoCpf
	 * 		Novo CPF para o hospede.
	 */
	public void atualizarCpfHospede( Hospede hosp, String novoCpf ) {
		if( this.listaGeralHospedes.isEmpty() ) throw new IllegalArgumentException( "Nao ha hospedes no hotel.");
		//if( !(this.listaGeralHospedes.contains(hosp))) throw new IllegalArgumentException( "Nao ha hospede com essa descricao."); //atencao
		for( Hospede h : this.listaGeralHospedes ) {
			if( hosp.getNome().equals(h.getNome()) && hosp.getCpf().equals(h.getCpf()) ) h.setCpf(novoCpf); 
		}
	}
	/**
	 * Atualiza o endereco do hospede que for passado como parametro para o novo endereco que tambem e passado como parametro.
	 * @param hosp
	 * @param novoEndereco
	 */
	public void atualizarEnderecoHospede( Hospede hosp, String novoEndereco ) {
		if( this.listaGeralHospedes.isEmpty() ) throw new IllegalArgumentException( "Nao ha hospedes no hotel.");
		//if( !(this.listaGeralHospedes.contains(hosp))) throw new IllegalArgumentException( "Nao ha hospede com essa descricao."); //atencao
		for( Hospede h : this.listaGeralHospedes ) {
			if( hosp.getNome().equals(h.getNome()) && hosp.getCpf().equals(h.getCpf()) ) h.setEndereco(novoEndereco); 
		}
	}
	/**
	 * retorna o hospede da colecao de hospedes do hotel que possuir o mesmo CPF passado como parametro.
	 * @param cpf
	 * @return
	 * 		O hospede que possuir o CPF informado.
	 */
	public Hospede pesquisaHospede( String cpf ) {
		for( Hospede h : this.listaGeralHospedes ) {
			if(  h.getCpf().equals(cpf)){
				return h;
			}
		}
		return new Hospede( "invalido", "invalido");
	}
	/**
	 * Retorna true se ja existir um hospede cadastrado com o CPf informado no parametro, false, caso contrario.
	 * @param cpf
	 * @return
	 * 		
	 */
	public boolean isHospedeCadastrado( String cpf ) {
		for( Hospede h : this.listaGeralHospedes ) {
			if( h.getCpf().equals(cpf)) return true;
		}
		return false;
	}
	/**
	 * Retorna um contrato da colecao de contratos do hotel que possuir as mesmas informacoes do contrato passado como parametro.
	 * @param cnt
	 * @return
	 * 		Um contrato da colecao igual ao do parametro.
	 */
	public Contrato pesquisaContrato( Contrato cnt ) {
		if( this.contratos.contains(cnt)) {
			int indice = contratos.indexOf(cnt);
			return contratos.get(indice);
		}
		else return null;
	}
	/**
	 * Retorna um contrato da colecao de contratos do hotel que possuir o mesmo hospede do contrato passado como parametro.
	 * @param hospProcurado
	 * @return
	 */
	public Contrato pesquisaContrato( Hospede hospProcurado ) {
		for( Contrato c : contratos ){
			Hospede assinante = c.getHospede();
			if( assinante.getCpf().equals( hospProcurado.getCpf()) ) {
				return c;
			}
		}
		return null;// cuidado aqui new Contrato(hospProcurado, tarifacao, data, data)
	}
	/**
	 * Retorna true se ja houver um contrato que possui um hospede com o cpf informado, false, caso contrario.
	 * @param cpfHosp
	 * @return
	 */
	public boolean jaTemContrato( String cpfHosp ) {
		for( Contrato c : this.contratos ) {
			if( c.getHospede().getCpf().equals(cpfHosp)) return true;
		}
		return false;
	}
	/**
	 * Retorna true se ja houver um contrato que possui um hospede com o numero de cartao de credito informado, false, caso contrario.
	 * @param num
	 * @return
	 */
	public boolean jaTemContratoComEsseCartao( String num ) {
		for( Contrato c : this.contratos ) {
			if( c.getNumCartao().equals(num)) return true;
		}
		return false; 
	}
}
