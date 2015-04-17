package testes;

import org.junit.Assert;
import org.junit.Test;

import classesprincipais.Carro;
import classesprincipais.Carro.Tipo;

public class CarroTeste {
	private Carro carro1;
	private Carro carro2;
	Carro carro3;

	@Test
	public void testCriaCarros() {
		carro1 = new Carro( Tipo.EXECUTIVO, 4, false, false );
		carro2 = new Carro( "xld 2530", Tipo.LUXO, 2, true, true );
	}
	@Test
	public void testaGets() {
		testCriaCarros();
		Assert.assertEquals( Tipo.EXECUTIVO, carro1.getTipo());
		Assert.assertEquals( 4, carro1.getTotalDiasAlugado());
		Assert.assertEquals( false, carro1.isComTanqueCheio());
		Assert.assertEquals( false, carro1.isComSeguro());
		Assert.assertEquals( "xld 2530", carro2.getPlaca());
		Assert.assertEquals( "xld 2530", carro2.getReferencia());
	}

}
