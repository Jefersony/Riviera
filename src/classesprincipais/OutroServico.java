package classesprincipais;

//import java.text.DecimalFormat;
import java.util.regex.Pattern;

/**
 * Classe que representa um outro servico do hotel. Este novo tipo de servico devera receber uma descricao e um preco.
 * @author Jeferson Rolim Holanda
 *
 */
public class OutroServico implements Servico {
	private String descricao;
	private double preco;
	/**
	 * Construtor que recebe a descricao e o preco do servico.
	 * @param descricao
	 * @param stringPreco
	 */
	public OutroServico( String descricao, String stringPreco) {
		this.descricao = descricao;
		//DecimalFormat nf = new DecimalFormat("#.00");
		//Pattern p = Pattern.compile("\\d*\\d.\\d\\d");
		Pattern.matches(stringPreco, "\\d*\\d.\\d\\d");
		String novaStr = stringPreco.replace("," , ".").trim();
		this.preco = Double.parseDouble(novaStr);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
	/**
	 * Retorna o total a pagar pelo servico. Este e equivalente a getPreco().
	 */
	@Override
	public double totalAPagar() {
		// TODO Auto-generated method stub
		return this.preco;
	}
	/**
	 * retorna a descricao do servico.
	 */
	@Override
	public String getReferencia() {
		// TODO Auto-generated method stub
		return this.descricao;
	}
	/**
	 * Retorna informacoes sobre a descricao e o preco do servico.
	 */
	@Override
	public String toString() {
		return String.format("OutroServico [Descricao: %s, Preco: %.2f]\n",
				descricao, preco);
	}

}
