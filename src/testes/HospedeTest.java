package testes;

import org.junit.Assert;
import org.junit.Test;
import classesprincipais.Hospede;

public class HospedeTest {
	Hospede hospede1;
	Hospede hospede2;
	Hospede hospede3;
	
	String nome1 = "joao", nome2 = "jefferson";
	
	public void criaHospede()throws Exception {
		hospede1 = new Hospede(nome1, "12345678", "nelson meira");
		hospede2 = new Hospede(nome2, "87654321", "rua da praca");
		
	}
	
	@Test
	public void testaGets() throws Exception{
		criaHospede();
		Assert.assertEquals("joao", hospede1.getNome());
		Assert.assertEquals("jefferson", hospede2.getNome());
	}
	
	@Test
	public void testaSets() throws Exception{
		criaHospede();
		Assert.assertNull(hospede1.getDataNascimento());
		hospede2.setDataNascimento("090988");
		Assert.assertEquals("090988", hospede2.getDataNascimento());
		
		
		}
}
