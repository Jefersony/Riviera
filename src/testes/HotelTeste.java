package testes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import classesprincipais.Hospede;
import classesprincipais.Contrato;
import classesprincipais.Hotel;
import classesprincipais.Contrato.Tarifacao;

public class HotelTeste {
	Hotel hotel;
	Contrato contrato1;
	Contrato contrato2;
	Contrato contrato3;
	
	Hospede hosp1;
	Hospede hosp2;
	Hospede hosp3;
	Hospede hosp4;
	
	@Before
	public void CriaHotel() {
		hotel = new Hotel();
	}
	@Before
	public void criaContratos() throws Exception{
		contrato1 = new Contrato( new Hospede( "jeferson", "123456", "rua da praca"),
				Tarifacao.NORMAL, "numeroCartao", "dataInicio", "dataFim");
		contrato2 = new Contrato( new Hospede( "rolim", "1234567", "rua da prefeitura"),
				Tarifacao.BAIXA, "numeroCartao", "dataInicio", "dataFim");
		contrato3 = new Contrato( new Hospede( "holanda", "12345678", "rua da igreja"),
				Tarifacao.ALTA, "numeroCartao", "dataInicio", "dataFim");
	}
	@Before
	public void criaHospedes() {
		hosp1 = new Hospede( "pessoaA", "cpf1");
		hosp2 = new Hospede( "pessoaB", "cpf2");
		hosp3 = new Hospede( "pessoaC", "cpf3");
		hosp4 = new Hospede( "pessoaD", "cpf4");
	}
	
	public void adicionarContratos() {
		hotel.adicionarContrato(contrato1);
		hotel.adicionarContrato(contrato2);
		hotel.adicionarContrato(contrato3);
	}
	public void adicionarHospedes() {
		hotel.adicionaHospede(hosp1);
		hotel.adicionaHospede(hosp2);
		hotel.adicionaHospede(hosp3);
		hotel.adicionaHospede(hosp4);
	}
	@Test
	public void testaAdicionarContrato() {
		adicionarContratos();
		Assert.assertEquals( 3, hotel.getContratos().size());
		Assert.assertEquals( "holanda", hotel.getContratos().get(2).getHospede().getNome());
	}
	@Test
	public void testaRemoverContratos() throws Exception{
		adicionarContratos();
		Assert.assertEquals( 3, hotel.getContratos().size());
		hotel.removerContrato(new Hospede("jeferson", "123456", "rua da praca" ));
		Assert.assertEquals( 2, hotel.getContratos().size());
		Assert.assertEquals( "rolim", hotel.getContratos().get(0).getHospede().getNome());
	}
	@Test
	public void testaAdicionarHospede() {
		Assert.assertTrue(hotel.getListaGeralHospedes().size() == 0);
		hotel.adicionaHospede(new Hospede( "pessoaA", "cpf1"));
		Assert.assertTrue(hotel.getListaGeralHospedes().size() == 1);
		hotel.adicionaHospede(new Hospede( "pessoaB", "cpf2"));
		hotel.adicionaHospede(new Hospede( "pessoaC", "cpf3"));
		hotel.adicionaHospede(new Hospede( "pessoaD", "cpf4"));
		Assert.assertTrue(hotel.getListaGeralHospedes().size() == 4);
	}
	
	@Test
	public void testaRemoverHospedes() {
		testaAdicionarHospede();
		Assert.assertTrue(hotel.getListaGeralHospedes().size() == 4);
		Assert.assertEquals( "pessoaB", hotel.getListaGeralHospedes().get(1).getNome());
		hotel.removerHospede(new Hospede( "pessoaB", "cpf2"));
		Assert.assertEquals( "pessoaC", hotel.getListaGeralHospedes().get(1).getNome());
		Assert.assertTrue(hotel.getListaGeralHospedes().size() == 3);
		hotel.removerHospede(new Hospede( "pessoaD", "cpf4"));
		Assert.assertTrue(hotel.getListaGeralHospedes().size() == 2);
	}
	
	@Test
	public void testaPesquisarHospede() {
		testaAdicionarHospede();
		criaHospedes();
		Assert.assertTrue(hotel.getListaGeralHospedes().size() == 4);
		Assert.assertEquals("pessoaA", hotel.pesquisaHospede(new Hospede( "pessoaA", "cpf1")).getNome());
		Assert.assertEquals( hosp2, hotel.pesquisaHospede(hosp2));
	}
	
	@Test
	public void testaAtualizarNomeDeHospede() {
		adicionarHospedes();
		Assert.assertTrue(hotel.getListaGeralHospedes().size() == 4);
		Assert.assertEquals( hosp1.getNome(), hotel.getListaGeralHospedes().get(0).getNome());
		String novoNome1 = "clienteA";
		hotel.atualizarNomeHospede(new Hospede( "pessoaA", "cpf1"), novoNome1 );
		Assert.assertEquals( novoNome1, hotel.getListaGeralHospedes().get(0).getNome());
		
		String novoNome3 = "clienteC";
		Assert.assertEquals( hosp3, hotel.pesquisaHospede(new Hospede( "pessoaC", "cpf3")));
		hotel.atualizarNomeHospede( new Hospede( "pessoaC", "cpf3"), novoNome3);
		Assert.assertEquals( novoNome3, hotel.getListaGeralHospedes().get(2).getNome());
	}
	
	@Test
	public void testaAtualizarCpfDeHospede() {
		adicionarHospedes();
		Assert.assertTrue(hotel.getListaGeralHospedes().size() == 4);
		Assert.assertEquals( hosp2.getCpf(), hotel.getListaGeralHospedes().get(1).getCpf());
		String novoCpf2 = "novoCpf2";
		hotel.atualizarCpfHospede(new Hospede("pessoaB", "cpf2"), novoCpf2);
		Assert.assertEquals( novoCpf2, hotel.getListaGeralHospedes().get(1).getCpf());
	}
	
	@Test
	public void testaAtualizarEnderecoDeHospede() {
		adicionarHospedes();
		Assert.assertTrue(hotel.getListaGeralHospedes().size() == 4);
		Assert.assertEquals( "NAO INFORMADO", hosp4.getEndereco());
		String novoEndereco4 = "Alameda dos anjos";
		hotel.atualizarEnderecoHospede(new Hospede( "pessoaD", "cpf4"), novoEndereco4);
		Assert.assertEquals( novoEndereco4, hosp4.getEndereco());
	}

}
