package testes;

import org.junit.Assert;
import org.junit.Test;

import classesprincipais.Hospede;
import classesprincipais.Quarto;
import classesprincipais.Quarto.Tipo;

public class QuartoTeste {
	Quarto quarto1;
	Quarto quarto2;
	Quarto quarto3;
	
	Hospede hospedeCliente;
	Hospede hosp1;
	Hospede hosp2;
	Hospede hosp3;
	Hospede hosp4;
	
	public void criaQuartos() {
		quarto1 = new Quarto( "201A", Tipo.PRESIDENCIAL, 5 );
		quarto2 = new Quarto( "202B", Tipo.EXECUTIVO_SIMPLES, 8);
		quarto3 = new Quarto( "203C", Tipo.LUXO_TRIPLO, 3);
	}
	public void criaHospedes() {
		hospedeCliente = new Hospede( "cliente", "0000");
		hosp1 = new Hospede( "pessoaA", "cpf1");
		hosp2 = new Hospede( "pessoaB", "cpf2");
		hosp3 = new Hospede( "pessoaC", "cpf3");
		hosp4 = new Hospede( "pessoaD", "cpf4");
	}
	
	@Test
	public void testaGets() {
		criaQuartos();
		Assert.assertEquals(quarto1.getTipo().getPreco(), 1200.0, 0.1);
		Assert.assertEquals( 8, quarto2.getTotalDiasAlugado());
		Assert.assertEquals( "360,00", quarto2.getPreco());
		
	}
	@Test
	public void testaAdicionarAcompanhante() {
		criaQuartos();
		quarto1.adicionarAcompanhante(hosp1);
		Assert.assertTrue(quarto1.getAcompanhantes().size() == 1);
		try {
			quarto2.adicionarAcompanhante(hosp2);
			Assert.fail("O quarto so comprta uma pessoa (o hospede cliente.");
		} catch (Exception e) {
			Assert.assertEquals("O quarto nao comporta mais hospedes.", e.getMessage());
		}
		quarto3.setHospedeCliente(hospedeCliente);
		quarto3.adicionarAcompanhante(hosp1);
		quarto3.adicionarAcompanhante(hosp2);
		
		Assert.assertTrue( quarto3.getAcompanhantes().size() == 2 );
		try {
			quarto3.adicionarAcompanhante(hosp4);
			Assert.fail("O quarto so comprta uma pessoa (o hospede cliente.");
		} catch (Exception e) {
			Assert.assertEquals("O quarto nao comporta mais hospedes.", e.getMessage());
		}
	}
	@Test
	public void testaRemoverAcompanhantes() {
		criaQuartos();
		quarto3.setHospedeCliente(hospedeCliente);
		quarto3.adicionarAcompanhante(hosp1);
		quarto3.adicionarAcompanhante(hosp2);
		Assert.assertTrue( quarto3.getAcompanhantes().size() == 2 );
		quarto3.removerAcompanhante(hosp2);
		Assert.assertTrue( quarto3.getAcompanhantes().size() == 1 );
		quarto3.removerAcompanhante(hosp1);
		Assert.assertTrue( quarto3.getAcompanhantes().size() == 0 );
		
		quarto1.adicionarAcompanhante(hosp1);
		Assert.assertTrue( quarto1.getAcompanhantes().size() == 1 );
		quarto1.removerAcompanhante(hosp1);
		Assert.assertTrue( quarto3.getAcompanhantes().size() == 0 );
	}

}
