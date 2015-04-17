package classesprincipais;

import java.util.List;

import classesprincipais.Contrato.Tarifacao;
import classesprincipais.Quarto.Tipo;

public class GeradorFatura {
	public static Hotel hotel = new Hotel(); 
	
	public static double totalFatura( Contrato contrato ) {
		List<Servico> servicos = contrato.getListaServicos();
		if( servicos.isEmpty() ) return 0.0;
		double somador = 0.0;
		for( Servico serv : servicos ) {
			somador+= serv.totalAPagar();
		}
		return somador;
	}
	public static String geraFatura( Contrato contrato ) {
		List<Servico> servicos = contrato.getListaServicos();
		if( servicos.isEmpty() ) return "Nao ha servicos na conta do cliente.";
		double somador= 0.0;
		String concat= "";
		int cont = 1;
		for( Servico serv : servicos ) {
			concat += "("+cont+") " + serv.toString() + "      -->  Preco do servico: " + serv.totalAPagar() + "\n";
			somador+= serv.totalAPagar();
			cont++;
		}
		concat += "Total a pagar: " + somador + "\n";
		return concat;
	}
	
	public static void main(String[] args) throws Exception {
		Hospede hospDefault = new Hospede( "jeff", "123");
		Contrato cntrtDefault = new Contrato( new Hospede( "jeff", "123"), Tarifacao.NORMAL, "456", "01/01");
		adicionarServicos(cntrtDefault);
		hotel.adicionaHospede(hospDefault);// hospede adicionado para testes 
		hotel.adicionarContrato(cntrtDefault);// contrato adicionado para testes
		System.out.println( totalFatura( cntrtDefault));
		System.out.println( geraFatura( cntrtDefault));
	}
	
	private static void adicionarServicos( Contrato cntrt ) {
		cntrt.adicionarServico(new Quarto(Tipo.EXECUTIVO_DUPLO));
		cntrt.adicionarServico(new Carro("dfg-1256", Carro.Tipo.LUXO, 1, true, false));
		cntrt.adicionarServico(new Baba("12:30", "17:30"));
		cntrt.adicionarServico(new Refeicao(Refeicao.Tipo.BEBIDA, "Agua", 2));
		cntrt.adicionarServico(new OutroServico("outro servico", "0.0"));
	}

}
