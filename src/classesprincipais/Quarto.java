package classesprincipais;

import java.util.ArrayList;
import java.util.List;
//OBS
/*
 * Para cada quarto alugado, o total disponivel do hotel deve ser atualizado (decrementado)
 * e os hospedes devem ser tambem inseridos na listaGeraldeHospedes.
 */
/**
 * Classe que representa um quarto no sistema do hotel. 
 * @author Jeferson Rolim Holanda
 *
 */
public class Quarto implements Servico{
	private String referencia; //EX: 203E ou quarto101 ou demais referencias; 
	private Hospede hospedeCliente;// provavelmente vai sair na versao final
	private List<Hospede> acompanhantes;
	private static int totalAlugado;
	private static int totalDisponivel;
	private int totalDiasAlugado=1;
	private Tipo tipo;
	private int NUM_HOSPEDE_CLIENTE = 1;
	
	public Quarto( int totalDiasAluguel, Tipo tp ) {
		this.totalDiasAlugado = totalDiasAluguel;
		this.tipo = tp;
	}
	public Quarto( Tipo tp ) {
		this.tipo = tp;
	}
	/**
	 * Construtor que recebe uma referencia individual do quarto, o tipo de quarto e o total de dias do aluguel.
	 * @param referencia
	 * @param tipo
	 * @param totalDias
	 */
	public Quarto( String referencia, Tipo tipo, int totalDias ) {
		this.referencia = referencia;
		this.tipo = tipo;
		this.totalDiasAlugado = totalDias;
		if( tipo.getCapacidade() > 1 )
			acompanhantes = new ArrayList<Hospede>();
	}
	public Quarto( Tipo tp, String referencia ) {
		this.tipo = tp;
		this.referencia = referencia;
	}
	/**
	 * Enumeracao que faz o controle do total de hospedes comportados por cada tipo de quarto e o preco.
	 * @author Jeferson Rolim Holanda
	 *
	 */
	public enum Tipo {
		PRESIDENCIAL(4, 1200.0),// os precos poderiam ser constantes definidas nas configuracoes 
		LUXO_SIMPLES( 1, 520.0 ), LUXO_DUPLO( 2, 570.0 ), LUXO_TRIPLO( 3, 620.0 ), 
		EXECUTIVO_SIMPLES( 1, 360.0 ), EXECUTIVO_DUPLO( 2, 385.0 ), EXECUTIVO_TRIPLO( 3, 440.0 );
		private int capacidade;
		private double preco;
		Tipo( int qntd, double prc ) { //Tipo( quantidadeDeHospedes, preco )
			this.capacidade = qntd;
			this.preco = prc;
		}
		public int getCapacidade() {
			return capacidade;
		}
		public void setCapacidade(int quantidadeMaxima) {
			this.capacidade = quantidadeMaxima;
		}
		public double getPreco() {
			return preco;
		}
		public void setPreco(double preco) {
			this.preco = preco;
		}
	}
	/**
	 * Retorna o total a pagar com base no total de dias alugado e no preco do tipo de quarto escolhido.
	 */
	@Override
	public double totalAPagar() {
		return tipo.getPreco() * this.totalDiasAlugado;
	}

	public static int getTotalAlugado() {
		return totalAlugado;
	}
	public static void setTotalAlugado(int totalAlugado) {
		Quarto.totalAlugado = totalAlugado;
	}
	/**
	 * 
	 * @return
	 * 		O total de quartos disponiveis no hotel.
	 */
	public static int getTotalDisponivel() {
		return totalDisponivel;
	}
	public static void setTotalDisponivel(int totalDisponivel) {
		Quarto.totalDisponivel = totalDisponivel;
	}
	public int getTotalDiasAlugado() {
		return totalDiasAlugado;
	}
	public void setTotalDiasAlugado(int totalDiasAlugado) {
		this.totalDiasAlugado = totalDiasAlugado;
	}

	public double retornaPreco( ) {
		return tipo.getPreco();
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getPreco() {
		return String.format("%.2f", tipo.getPreco());
	}
	public String getCapacidade() {
		return String.format("%d", tipo.getCapacidade());
	}
	public Hospede getHospedeCliente() {
		return hospedeCliente;
	}
	public void setHospedeCliente(Hospede hospedeCliente) {
		this.hospedeCliente = hospedeCliente;
	}
	public List<Hospede> getAcompanhantes() {
		return acompanhantes;
	}
	/**
	 * Define os acompanhantes do hospede naquele quarto. 
	 * @param hospedesAcompanhantes
	 * 		Colecao de acompanhantes que estarao no quarto.
	 */
	public void setAcompanhantes(List<Hospede> hospedesAcompanhantes) {
		if( this.acompanhantes == null)
			this.acompanhantes = new ArrayList<>();
		this.acompanhantes = hospedesAcompanhantes;
	}
	/**
	 * Adiciona um acompanhante a colecao de acompanhantes do quarto, caso ele comparte mais de uma pessoa.
	 * @param hospede
	 */
	public void adicionarAcompanhante( Hospede hospede ) {
		if( this.acompanhantes == null)
			this.acompanhantes = new ArrayList<>();
		if( tipo.getCapacidade() - this.NUM_HOSPEDE_CLIENTE - acompanhantes.size() <= 0 ) 
			throw new IllegalArgumentException( "O quarto nao comporta mais hospedes.");
		this.acompanhantes.add(hospede);
	}
	/**
	 * Remove um acompanhante da colecao do quarto. 
	 * @param hospede
	 * 		Hospede acomapnhante a ser removido.
	 */
	public void removerAcompanhante( Hospede hospede ) {//Exception
		if( this.acompanhantes == null)
			throw new IllegalArgumentException( "Nao ha acompanhantes.");
		if( this.acompanhantes.contains(hospede))
			acompanhantes.remove(hospede);
	}
	/**
	 *  
	 */
	@Override
	public String toString() {
		return String
				.format("Quarto [Total Dias da Estadia: %s, Tipo: %s, Acompanhantes: %s]\n",
						 totalDiasAlugado, tipo, acompanhantes);
	}
	
}
