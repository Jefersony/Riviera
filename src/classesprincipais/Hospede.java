package classesprincipais;
/**
 * Classe que representa um hospede no sistema.
 * @author Jeferson Rolim Holanda
 *
 */
public class Hospede {
	private String nome;
	private String cpf;
	private String endereco;
	private String dataNascimento;
	/**
	 * Construtor que recebe nome, CPF, endereco como strings. 
	 * Se o endereco for uma string vazia, o atributo endereco recebera o valor default.
	 * @param nome
	 * @param cpf
	 * @param endereco
	 */
	public Hospede( String nome, String cpf, String endereco ) throws Exception {
		if( nome.equals( "" ) ) throw new Exception( "Nome nao informado.");
		if( cpf.equals("") ) throw new Exception( "CPF nao informado.");
		this.nome = nome;
		this.cpf = cpf;
		if( endereco.equals("")) 
			this.endereco = "NAO INFORMADO";
		else 
			this.endereco = endereco;
	}
	/**
	 * Construtor que recebe nome e CPF como strings.
	 * @param nome
	 * @param cpf
	 */
	public Hospede( String nome, String cpf ) {
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = "NAO INFORMADO";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	@Override
	public String toString() {
		return String.format("Nome: %s, CPF: %s, Data de Nascimento: %s, endereco: %s", nome,
				cpf, this.dataNascimento, endereco);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Hospede))
			return false;
		Hospede other = (Hospede) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}
