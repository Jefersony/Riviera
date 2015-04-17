package testes;

import org.junit.Assert;
import org.junit.Test;

import classesprincipais.Baba;

public class BabaTest {
	Baba servico1;
	Baba servico2;
	Baba servico3;

	public void criaBabas() {
		servico1 = new Baba( 20, 2, "03/04/2014");
		servico2 = new Baba( 16, 22, "11/05/2014");
		servico3 = new Baba( 8, 14, "25/06/2014");
	}
	
	@Test
	public void testaGets() {
		criaBabas();
		Assert.assertEquals( 20, servico1.getNumHoraInicio());
		Assert.assertEquals(22, servico2.getNumHoraFim());
		Assert.assertEquals("25/06/2014", servico3.getData());
	}

}
