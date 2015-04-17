package testes;

import org.junit.Assert;
import org.junit.Test;

import classesprincipais.Baba;
import classesprincipais.Carro;
import classesprincipais.Contrato;
import classesprincipais.Contrato.Tarifacao;
import classesprincipais.Hospede;
import classesprincipais.Quarto;
import classesprincipais.Quarto.Tipo;
import classesprincipais.Refeicao;
import classesprincipais.Servico;;

public class ContratoTeste {
	Contrato contrato1, contrato2, contrato3, contrato4;
	Hospede hosp1, hosp2, hosp3, hosp4;
	String data = "01/01/2015";
	Servico quarto;
	Servico refeicao;
	Servico carro;
	Servico baba;
	
	Quarto quarto1, quarto2, quarto3;
	

	public void criaContratos() throws Exception{
		contrato1 = new Contrato( new Hospede( "jeferson", "123456", "rua da praca"),
				Tarifacao.NORMAL, "numeroCartao", "dataInicio", "dataFim");
		contrato2 = new Contrato( new Hospede( "rolim", "1234567", "rua da prefeitura"),
				Tarifacao.BAIXA, "numeroCartao", "dataInicio", "dataFim");
		contrato3 = new Contrato( new Hospede( "holanda", "12345678", "rua da igreja"),
				Tarifacao.ALTA, "numeroCartao", "dataInicio", "dataFim");
		
		//System.out.println( contrato1.getTipoTarifacao().equals(Tarifacao.NORMAL));
	}
	public void criaHospedes() {
		hosp1 = new Hospede( "pessoaA", "cpf1");
		hosp2 = new Hospede( "pessoaB", "cpf2");
		hosp3 = new Hospede( "pessoaC", "cpf3");
		hosp4 = new Hospede( "pessoaD", "cpf4");
	}
	public void criaServicos() {
		quarto = new Quarto("201A", Tipo.LUXO_DUPLO, 4);
		refeicao = new Refeicao( Refeicao.Tipo.ALMOCO, "arroz, feijao, carne", 8.0 );
		carro = new Carro("AAA0000", Carro.Tipo.LUXO, 4, true, false);
		baba = new Baba( 19, 23, "01/01/2015");
	}
	public void novosContratos() throws Exception{
		criaHospedes();
		contrato1 = new Contrato(hosp1, Tarifacao.NORMAL, "1234", data);
		contrato2 = new Contrato(hosp2, Tarifacao.ALTA, "2345", data);
		contrato3 = new Contrato(hosp3, Tarifacao.BAIXA, "3456", data);
		contrato4 = new Contrato(hosp4, Tarifacao.BAIXA, "4567", data);
	}
	public void criaQuartos() {
		quarto1 = new Quarto( "201A", Tipo.PRESIDENCIAL, 5 );
		quarto2 = new Quarto( "202B", Tipo.EXECUTIVO_SIMPLES, 8);
		quarto3 = new Quarto( "203C", Tipo.LUXO_TRIPLO, 3);
	}
	
	@Test
	public void testaAdicionaServico() throws Exception{
		criaContratos();
		
		quarto = new Quarto("203E", Tipo.EXECUTIVO_SIMPLES, 5);
		contrato1.adicionarServico(quarto);
		Assert.assertTrue(contrato1.getListaServicos().size() == 1);
		
		refeicao = new Refeicao( Refeicao.Tipo.JANTAR, "Sopa", 11.50);
		contrato1.adicionarServico(refeicao);
		Assert.assertFalse(contrato1.getListaServicos().size() == 1);
		Assert.assertTrue(contrato1.getListaServicos().size() == 2);
		
		carro = new Carro( Carro.Tipo.LUXO, 3, true, true);
		contrato1.adicionarServico(carro);
		Assert.assertFalse(contrato1.getListaServicos().size() == 2);
		Assert.assertTrue(contrato1.getListaServicos().size() == 3);
		
		baba = new Baba(20, 3, "12/01/2015");
		contrato1.adicionarServico(baba);
		Assert.assertFalse(contrato1.getListaServicos().size() == 3);
		Assert.assertTrue(contrato1.getListaServicos().size() == 4);
		
		//contrato2.adicionarServicoContratado(quarto1);
	}
	@Test
	public void testaRemoverServico() throws Exception{
		criaContratos();
		testaAdicionaServico();
		Assert.assertTrue(contrato1.getListaServicos().size() == 4);
		contrato1.removerServico(baba);
		Assert.assertTrue(contrato1.getListaServicos().size() == 3);
		contrato1.removerServico(carro);
		Assert.assertTrue(contrato1.getListaServicos().size() == 2);
		
		contrato2.adicionarServico(quarto1);
		Assert.assertEquals( 1, contrato2.getListaServicos().size());
		contrato2.removerServico(quarto1);
		Assert.assertTrue( contrato2.getListaServicos().size() == 0);
	}
	@Test
	public void testaPesquisarServico() throws Exception{
		criaContratos();
		criaQuartos();
		contrato3.adicionarServico(quarto1);
		contrato3.adicionarServico(quarto2);
		contrato3.adicionarServico(quarto3);
		Assert.assertEquals( 3, contrato3.getListaServicos().size());
		Assert.assertEquals( quarto3, contrato3.pesquisarServico( "203C"));
		Assert.assertEquals( quarto3, contrato3.pesquisarServico( quarto3 ));
	}
	@Test
	public void testaAtualizarServico() throws Exception{
		criaContratos();
		criaQuartos();
		contrato3.adicionarServico(quarto1);
		contrato3.adicionarServico(quarto2);
		contrato3.adicionarServico(quarto3);
		Assert.assertEquals(3, contrato3.getListaServicos().size());
		
	}

}
