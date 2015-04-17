package classesprincipais;

/**
 * Classe do lab 08 reutilizada.
 * @author Jeferson Rolim Holanda
 *
 */
public class Opiniao {
	private String comentario;
	private int nota;
	private String data;
	
	/**
	 * Construtor.
	 * @param comentario 
	 * 		Comentario de ate 140 caracteres.
	 * @param nota 
	 * 		Nota de -2 a 2 para a opiniao sobre o produto.
	 * @param data
	 * 		Data do comentario aceita somente no formato XX/XX/XXXX.
	 */
	public Opiniao( String comentario, int nota, String data ) throws Exception {
		verificaComentario( comentario );
		verificaNota( nota );
		verificaData( data );
		this.comentario = comentario;
		this.nota = nota;
		this.data = data;
		
	}
	/**
	 * 
	 * @return Comentario da opiniao.
	 */
	public String getComentario() {
		return comentario;
	}
	
	/**
	 * 
	 * @return Nota dada na opiniao.
	 */
	public int getNota() {
		return nota;
	}
	
	/**
	 * 
	 * @return Data em que foi postada a opiniao. 
	 */
	public String getData() {
		return data;
	}
	
	/**
	 * Define o comentario da opiniao para o produto.
	 */
	public void setComentario( String comentario ) {
		this.comentario = comentario;
	}
	/**
	 * Define a nota da opiniao.
	 */
	public void setNota( int nota ) {
		this.nota = nota;
	}
	/**
	 * Define a data da opiniao.
	 * @param data
	 */
	public void setData( String data ) {
		this.data = data;
	}
	private Exception verificaComentario( String comentario ) throws Exception {
		if( comentario.length() > 140 ) throw new Exception( "Comentario de tamanho invalido.");
		
		if( comentario == null || comentario.equals("") ) throw new Exception( "Entrada invalida para comentario.");
		
		return null;
	}
	private Exception verificaNota( int nota ) throws Exception { 
		if( nota < -2 || nota > 2 ) throw new Exception( "Valor invalido para nota.");
		
		return null;
	}
	private Exception verificaData( String data ) throws Exception {
		if( data.length() < 10 || data.length() > 10 ) throw new Exception( "Tamanho invalido para data.");
		if( data.charAt(2) != '/' || data.charAt(5) != '/' ) throw new Exception( "Formato invalido para data.");
		return null;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((comentario == null) ? 0 : comentario.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + nota;
		return result;
	}
	/**
	 * Duas opinioes sao iguais se tiverem mesmo comentario, nota e data. 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Opiniao))
			return false;
		Opiniao other = (Opiniao) obj;
		if (comentario == null) {
			if (other.comentario != null)
				return false;
		} else if (!comentario.equals(other.comentario))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (nota != other.nota)
			return false;
		return true;
	}
	/**
	 * String com comentario, nota e data da opiniao.
	 */
	@Override
	public String toString() {
		return String.format("Opiniao [comentario=%s, nota=%s, data=%s]",
				comentario, nota, data);
	}
	

}

