package testes;

import org.junit.Assert;
import org.junit.Test;

import classesprincipais.Refeicao;
import classesprincipais.Refeicao.Tipo;

public class RefeicaoTest {

	Refeicao refeicao1;
	Refeicao refeicao2;
	
	String nome1 = "Sanduiche", nome2 = "Empanado";

	public void criaRefeicao() {
		refeicao1 = new Refeicao( Tipo.LANCHE  , "Sanduiche", 5.50);
		refeicao2 = new Refeicao( Tipo.ALMOCO  , "Empanado", 15.90);		
	}
	
	@Test
	public void testaGets() {
		criaRefeicao();
		Assert.assertEquals(nome1, refeicao1.getNome());
		Assert.assertEquals(nome2, refeicao2.getNome());
		Assert.assertEquals("5,50", refeicao1.getPreco());
		
	}
	public void testaSets() {
		criaRefeicao();
		Assert.assertEquals("5,50", refeicao1.getPreco());
	
	}
	

}
