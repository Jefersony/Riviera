package testes;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import classesprincipais.Refeicao;
import classesprincipais.Refeicao.Tipo;

public class RestauranteTeste {
	Refeicao refeicao1;
	Refeicao refeicao2;
	Refeicao refeicao3;
	Refeicao refeicao4;
	
	@Before
	public void criaRefeicoes() {
		refeicao1 = new Refeicao( Tipo.ALMOCO, "arroz, carne e feijao", 15.75 );
		refeicao2 = new Refeicao( Tipo.JANTAR, "sopa de letrinhas", 12.30);
		refeicao3 = new Refeicao( Tipo.LANCHE, "sanduiche de atum", 13.80);
		refeicao4 = new Refeicao( Tipo.BEBIDA, "whisky Green Label (dose)", 17.60 );
	}
	
	@Test
	public void testaGets() {
		Assert.assertEquals( refeicao1.getTipo().getPreco(), 15.75, 0.1);
		Assert.assertEquals( "arroz, carne e feijao", refeicao1.getTipo().getNome());
		Assert.assertEquals( 12.30, refeicao2.getTipo().getPreco(), 0.1);
	}
	
	@Test
	public void testaStringGetPreco() {
		Assert.assertEquals( "13,80", refeicao3.getPreco());
	}

}
