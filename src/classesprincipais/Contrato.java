package classesprincipais;

import java.util.ArrayList;
import java.util.List;

public class Contrato {
	private Hospede hospede;
	private Tarifacao tipoTarifacao;
	private List<Servico> listaServicos;
	private String numCartao;
	private int totalQuartosAlugados;
	private int totalServicosAdicionais;
	private String dataInicioContrato;
	private String dataFimContrato;
	public enum Tarifacao{
		NORMAL, ALTA, BAIXA
	}
	/**
	 * Construtor que recebe o hospede que sera o cliente a assinar o contrato, o tipo de tarifacao,
	 *  o numero de cartao de credito do cliente e as datas de inicio e fim do contrato. 
	 * @param hospede
	 * @param tipo
	 * @param numCartao
	 * @param dataInicio
	 * @param dataFim
	 */
	public Contrato( Hospede hospede, Tarifacao tipo, String numCartao, String dataInicio, String dataFim ) throws Exception {
		if( hospede == null ) throw new Exception( "Hospede nao selecionado para o contrato.");
		if( numCartao.equals("") ) throw new Exception( "Numero do cartao nao informado.");
		if( dataInicio.equals("") ) throw new Exception( "Data de inicio nao informada.");
		if( dataFim.equals("")) throw new Exception( "Data do fim nao informada.");
		this.hospede = hospede;
		this.tipoTarifacao = tipo;
		this.numCartao = numCartao;
		this.dataInicioContrato = dataInicio;
		if( dataFim.contains( " " ) ){
			this.dataFimContrato = "XX/XX/XXXX";
		}else{
			this.dataFimContrato = dataFim;
		}
		this.listaServicos = new ArrayList<>();
	}
	/**
	 * Construtor que recebe o hospede que sera o cliente a assinar o contrato, o tipo de tarifacao,
	 *  o numero de cartao de credito do cliente e a datas de inicio do contrato. 
	 * @param hospede
	 * @param tipo
	 * @param numCartao
	 * @param dataInicio
	 */
	public Contrato( Hospede hospede, Tarifacao tipo, String numCartao, String dataInicio ) throws Exception{
		if( hospede == null ) throw new Exception( "Hospede nao selecionado para o contrato.");
		if( numCartao.equals("") ) throw new Exception( "Numero do cartao nao informado.");
		if( dataInicio.equals("") ) throw new Exception( "Data de inicio nao informada.");
		this.hospede = hospede;
		this.tipoTarifacao = tipo;
		this.numCartao = numCartao;
		this.dataInicioContrato = dataInicio;
		this.dataFimContrato = "XX/XX/XXXX";// colocado recentemente
		this.listaServicos = new ArrayList<>();
	}
	
	public Hospede getHospede() {
		return hospede;
	}
	public void setHospede(Hospede hospede) {
		this.hospede = hospede;
	}
	public Tarifacao getTipoTarifacao() {
		return tipoTarifacao;
	}
	public void setTipoTarifacao(Tarifacao tipoTarifacao) {
		this.tipoTarifacao = tipoTarifacao;
	}
	/**
	 * Retorna a colecao de servicos consumidos no contrato.
	 * @return
	 */
	public List<Servico> getListaServicos() {
		return listaServicos;
	}
	public void setListaServicos(List<Servico> listaServicos) {
		this.listaServicos = listaServicos;
	}
	public String getNumCartao() {
		return numCartao;
	}
	public void setNumCartao(String numCartao) {
		this.numCartao = numCartao;
	}
	public int getTotalServicos() {
		return listaServicos.size();
	}
	public int getTotalQuartosAlugados() {
		return totalQuartosAlugados;
	}
	public void setTotalQuartosAlugados(int totalQuartosAlugados) {
		this.totalQuartosAlugados = totalQuartosAlugados;
	}
	public int getTotalServicosAdicionais() {
		return totalServicosAdicionais;
	}
	public void setTotalServicosAdicionais(int totalServicosAdicionais) {
		this.totalServicosAdicionais = totalServicosAdicionais;
	}
	public String getDataInicioContrato() {
		return dataInicioContrato;
	}
	public void setDataInicioContrato(String dataInicioContrato) {
		this.dataInicioContrato = dataInicioContrato;
	}
	public String getDataFimContrato() {
		return dataFimContrato;
	}
	public void setDataFimContrato(String dataFimContrato) {
		this.dataFimContrato = dataFimContrato;
	}
	public void adicionarServico( Servico serv ) {
		listaServicos.add(serv);
	}
	public String getClienteInfo() {
		return hospede.toString();
	}
	/**
	 * Remove um servico da colecao de sevicos do contrato.
	 * @param serv
	 * 		Servico a ser removido.
	 */
	public void removerServico(Servico serv) {
		if( listaServicos.size() == 0 ) throw new IllegalArgumentException( "Nao ha contratos a serem removidos.");
		this.listaServicos.remove(serv);
	}
	/**
	 * Retorna o servico que for igual ao servico passado como parametro.
	 * @param serv
	 * @return
	 * 		Servico igual ao do parametro.
	 */
	public Servico pesquisarServico( Servico serv ) {
		if( listaServicos.size() == 0 ) throw new IllegalArgumentException( "Nao ha servicos cadastrados.");
		if( !(listaServicos.contains(serv))) throw new IllegalArgumentException( "Este servico nao esta na lista.");
		for( Servico s : this.listaServicos )
			if( s.equals(serv)) return s;
			
		return null;
	}
	/**
	 * Retorna o servico da colecao de servicos que tiver a mesma string passada como parametro.
	 * @param referencia
	 * 		String do atributo referencia.
	 * @return
	 * 		O servico que possuir tal referencia.
	 */
	public Servico pesquisarServico( String referencia ) {
		if( listaServicos.size() == 0 ) throw new IllegalArgumentException( "Nao ha servicos cadastrados.");
		for( Servico s : this.listaServicos )
			if( s.getReferencia().equals(referencia)) return s;
			
		return null;
	}
	/**
	 * Retorna as informacoes basicas do cliente assinante e as do contrato como: 
	 * tipo de tarifacao, numero do cartao de credito do assinante, lista de servicos e as datas de inicio e fim do contrato. 
	 */
	@Override
	public String toString() {
		return String
				.format("Cliente: %s; tipoTarifacao: %s; Numero do Cartao: %s; Total Quartos Alugados: %s;\n"
						+ "Total Servicos Adicionais: %s, Data de Inicio do Contrato: %s, Data de Fim do Contrato: %s]\n"
						+ "Total de Servicos Contratados: %s",
						hospede.getNome(), tipoTarifacao, numCartao,
						totalQuartosAlugados, totalServicosAdicionais,
						dataInicioContrato, dataFimContrato, listaServicos.size());
	}
	/**
	 * 
	 */
	@Override
	public boolean equals( Object obj ) {
		if( !(obj instanceof Contrato) ) return false;
		Contrato contrato = (Contrato) obj;
		return contrato.getNumCartao().equals(numCartao) &&
				contrato.getDataInicioContrato().equals(dataInicioContrato);
	}
	
	/* AtualizarServico devera ser implementado na interface grafica:
	 * Os servicos dos hospedes serao atualizados atraves do acesso individual a cada hospede.
	 * Todos os servicos serao dispostos em algum display e assim, havera a opcao de escolha pra
	 * qual dos servicos atualizar.
	 */

}
