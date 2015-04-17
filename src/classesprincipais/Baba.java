package classesprincipais;
/**
 * Classe que representa os servicos efetuados por babas.
 * @author Jeferson Rolim Holanda
 *
 */
public class Baba implements Servico {
	private String data; //do dia inicial do servico
	private static double precoPorHora = 25.0;
	private int quantidadeHoras=1;
	private int totalDias=1;
	private String horaInicio;
	private String horaFim;
	private int numHoraInicio;
	private int numHoraFim;
	/**
	 * Construtor usado para receber somente strings.
	 * @param horaInicio
	 * @param horaFim
	 */
	public Baba( String horaInicio, String horaFim ) {
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
	}
	/**
	 * Construtor para receber valores numericos que representem as horas do servico prestado.
	 * @param hInicial
	 * @param hFinal
	 */
	public Baba( int hInicial , int hFinal ) {
		this.numHoraInicio = hInicial;
		this.numHoraFim = hFinal;
	}
	/**
	 * Construtor que recebe valores int para representar as horas e uma string para representar a data.
	 * @param hInicial
	 * @param hFinal
	 * @param data
	 */
	public Baba( int hInicial , int hFinal, String data ) {
		if( hInicial < 0 || hInicial > 23 ) throw new IllegalArgumentException( "Valor invalido para hora.");
		if( hFinal < 0 || hFinal > 23 ) throw new IllegalArgumentException( "Valor invalido para hora.");
		this.numHoraInicio = hInicial;
		this.numHoraFim = hFinal;
		this.data = data;
	}
	/**
	 * Retorna o total a pagar pelo servico prestado com base nos horarios de hora normal e dobrada.
	 */
	@Override
	public double totalAPagar() { // de 18 as 7 hora dobrada | atencao para a precisao de calculo do metodo
		if( numHoraInicio > 7 && numHoraFim <= 18 ) return quantidadeHoras * totalDias * precoPorHora;
		
		else if( numHoraInicio <= 18 && numHoraFim <= 7 ) return quantidadeHoras * totalDias * precoPorHora * 2;
		
		else {
			double totalHoraNormal =  18 - numHoraInicio;
			double totalHoraDobrada = numHoraFim - 18;
			return ( Math.abs( totalHoraNormal * precoPorHora ) + Math.abs( totalHoraDobrada * precoPorHora * 2 )) * totalDias;
		}
	}
	
	public static double getPrecoPorHora() {
		return precoPorHora;
	}
	public static void setPrecoPorHora(double precoPorHora) {
		Baba.precoPorHora = precoPorHora;
	}
	public int getQuantidadeHoras() {
		return quantidadeHoras;
	}
	public void setQuantidadeHoras(int quantidadeHoras) {
		this.quantidadeHoras = quantidadeHoras;
	}
	public int getTotalDias() {
		return totalDias;
	}
	public void setTotalDias(int totalDias) {
		this.totalDias = totalDias;
	}
	public String getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	public String getHoraFim() {
		return horaFim;
	}
	public void setHoraFim(String horaFim) {
		this.horaFim = horaFim;
	}
	public int getNumHoraInicio() {
		return numHoraInicio;
	}
	public void setNumHoraInicio(int  numHoraInicio) {
		this.numHoraInicio = numHoraInicio;
	}
	public int getNumHoraFim() {
		return numHoraFim;
	}
	public void setNumHoraFim(int numHoraFim) {
		this.numHoraFim = numHoraFim;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	/**
	 * Retorna uma string usada como referencia individual do servico. Ex: O nome da atendente ou seu codigo de funcionario.
	 */
	@Override
	public String getReferencia() {
		return this.data;
	}
	/**
	 * Retorna uma string informando a data, a duracao do servico (descrito em horas), a hora do inicio e a hora do termino.
	 */
	@Override
	public String toString() {
		return String
				.format("Baba [Data: %s, Total Horas: %s, Hora do Inicio: %s, Hora do Fim: %s]\n",
						data, quantidadeHoras, horaInicio, horaFim);
	}
	
}
