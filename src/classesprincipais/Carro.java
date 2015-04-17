package classesprincipais;
/**
 * 
 * @author Jeferson Rolim Holanda
 *
 */
public class Carro implements Servico{
	private String nome;
	private String placa;
	private int totalDiasAlugado=1;
	private Tipo tipo;
	private boolean comTanqueCheio;
	private boolean comSeguro;
	private static double PRECO_TANQUE_CHEIO = 150.0;
	private static double PRECO_SEGURO = 100.0;
	/**
	 * Construtor que recebe o tipo do carro, o total de dias do aluguel e os booleans para determinar se o servico ja incluira tanque cheio e/ou seguro. 
	 * @param tp
	 * 		Pode ser EXECUTIVO ou LUXO.
	 * @param totalDiasAluguel
	 * @param comTanqueCheio
	 * @param comSeguro
	 */
	public Carro( Tipo tp, int totalDiasAluguel, boolean comTanqueCheio, boolean comSeguro ) {
		this.tipo = tp;
		this.totalDiasAlugado = totalDiasAluguel;
		this.comTanqueCheio = comTanqueCheio;
		this.comSeguro = comSeguro;
	}
	/**
	 * Construtor que recebe a placa do carro, o tipo do carro, o total de dias do aluguel e os booleans para determinar se o servico ja incluira tanque cheio e/ou seguro.
	 * @param placa
	 * @param tp
	 * 		Pode ser EXECUTIVO ou LUXO.
	 * @param totalDiasAluguel
	 * @param comTanqueCheio
	 * @param comSeguro
	 */
	public Carro( String placa, Tipo tp, int totalDiasAluguel, boolean comTanqueCheio, boolean comSeguro ) {
		this.placa = placa;
		this.tipo = tp;
		this.totalDiasAlugado = totalDiasAluguel;
		this.comTanqueCheio = comTanqueCheio;
		this.comSeguro = comSeguro;
	}
	/**
	 * 
	 * @author Jeferson Rolim Holanda
	 *
	 */
	public enum Tipo {
		EXECUTIVO( 60.0 ), LUXO( 100.0 );
		private double precoDiaria;
		Tipo( double preco ) {
			this.precoDiaria = preco;
		}
		public double getPrecoDiaria() {
			return precoDiaria;
		}
		public void setPrecoDiaria(double precoDiaria) {
			this.precoDiaria = precoDiaria;
		}		
	}
	/**
	 * Retorna o total a pagar pelo servico de acordo com o total de dias do aluguel e o preco.
	 */
	@Override
	public double totalAPagar() {
		double total;
		total = this.totalDiasAlugado * tipo.getPrecoDiaria();
		if( this.isComSeguro() ) total += Carro.PRECO_SEGURO;
		if( this.isComTanqueCheio()) total += Carro.PRECO_TANQUE_CHEIO;
		return total;
	}
	
	public int getTotalDiasAlugado() {
		return totalDiasAlugado;
	}
	public void setTotalDiasAlugado(int totalDiasAlugado) {
		this.totalDiasAlugado = totalDiasAlugado;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	public boolean isComTanqueCheio() {
		return comTanqueCheio;
	}
	public void setComTanqueCheio(boolean comTanqueCheio) {
		this.comTanqueCheio = comTanqueCheio;
	}
	public boolean isComSeguro() {
		return comSeguro;
	}
	public void setComSeguro(boolean comSeguro) {
		this.comSeguro = comSeguro;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPreco() {
		return String.format("%.2f", tipo.getPrecoDiaria());
	}
	public static double getPrecoTanqueCheio() {
		return PRECO_TANQUE_CHEIO;
	}
	public static void setPrecoTanqueCheio(double precoTanqueCheio) {
		if( precoTanqueCheio < 0 ) throw new IllegalArgumentException("Preco invalido para tanque cheio.");
		Carro.PRECO_TANQUE_CHEIO = precoTanqueCheio;
	}
	public static double getPrecoSeguro() {
		return PRECO_SEGURO;
	}
	public static void setPrecoSeguro(double precoSeguro) {
		if( precoSeguro < 0 ) throw new IllegalArgumentException( "Preco invalido para seguro.");
		Carro.PRECO_SEGURO = precoSeguro;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	/**
	 * Retorna a referencia individual do servico, no caso, a placa do veiculo.
	 */
	@Override
	public String getReferencia() {
		return this.placa;
	}
	/**
	 * Retorna as informacoes da placa, total de dias do aluguel, o tipo do carro alugado, se tave tanque cheio e/ou seguro.
	 */
	@Override
	public String toString() {
		return String
				.format("Carro [Placa: %s, Total Dias do Aluguel: %s, Tipo: %s, Com Tanque Cheio: %s, Com Seguro: %s]\n",
						placa, totalDiasAlugado, tipo, converteBool(comTanqueCheio) , converteBool(comSeguro));
	}
	private String converteBool( boolean bool ) {
		if( bool == true ) return "Sim";
		else return "Nao";
	}

}
