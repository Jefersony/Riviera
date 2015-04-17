package classesprincipais;
/**
 * Classe que representa o servico de refeicao do hotel.
 * @author Jeferson Rolim Holanda
 *
 */
public class Refeicao implements Servico {
	private String data;
	private static int totalRefeicoes;
	private Tipo tipo;
	/**
	 * Construtor que recebe o tipo de refeicao, o nome do prato/produto e o preco.
	 * @param refe
	 * @param nome
	 * @param preco
	 */
	public Refeicao( Tipo refe, String nome, double preco ) {
		this.tipo = refe;
		this.tipo.setNome(nome);
		
		this.tipo.setPreco(preco);
	}
	/**
	 * Enumeracao que guardara o nome do prato e o preco.
	 * @author Jeferson Rolim Holanda
	 *
	 */
	public enum Tipo {
		ALMOCO( "NOME", 0.0 ), JANTAR( "NOME", 0.0 ), BEBIDA( "NOME", 0.0 ), LANCHE( "NOME", 0.0 ), SOBREMESA( "NOME", 0.0 );
		private String nome;
		private double preco;
		Tipo( String nome, double preco ) {
			this.nome = nome;
			this.preco = preco;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public double getPreco() {
			return preco;
		}
		public void setPreco(double preco) {
			this.preco = preco;
		}
		
	}
	/**
	 * Para este servico, equivale a Tipo.getPreco()
	 */
	@Override
	public double totalAPagar() {
		return tipo.getPreco();
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public static int getTotalRefeicoes() {
		return totalRefeicoes;
	}
	public static void setTotalRefeicoes(int totalRefeicoes) {
		Refeicao.totalRefeicoes = totalRefeicoes;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	public String getNome() {
		return tipo.getNome();
	}
	public String getPreco() {
		return String.format("%.2f", tipo.getPreco());
	}
	@Override
	public String getReferencia() {
		return tipo.getNome();
	}
	/**
	 * retorna na string a data o tipo de refeicao e o preco.
	 */
	@Override
	public String toString() {
		return String.format("Refeicao [Data: %s, Tipo: %s, Preco: %.2f]\n", data, tipo, tipo.getPreco());
	}
	
}
