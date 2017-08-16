package br.com.rbaselio.leilao.teste;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.rbaselio.leilao.builder.CriadorDeLeilao;
import br.com.rbaselio.leilao.dominio.Lance;
import br.com.rbaselio.leilao.dominio.Leilao;
import br.com.rbaselio.leilao.dominio.Usuario;
import br.com.rbaselio.leilao.servico.Avaliador;

public class TesteDoAvaliador {
	private Avaliador leiloeiro;
	private Usuario joao;
	private Usuario jose;
	private Usuario maria;

	@Before
	public void criaAvaliador() {
		this.leiloeiro = new Avaliador();
		this.joao = new Usuario("Joao");
		this.jose = new Usuario("Jose");
		this.maria = new Usuario("Maria");
	}
	
	@Test(expected=RuntimeException.class)
	public void naoDeveAvaliarLeiloesSemNenhumLanceDado() {
	    Leilao leilao = new CriadorDeLeilao()
	        .para("Playstation 3 Novo")
	        .constroi();
	    leiloeiro.avalia(leilao);
	    
	}

	@Test
	public void deveEntenderLancesEmOrdemCrescente() {

		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
				.lance(joao, 300.0).lance(jose, 400.0).lance(maria, 250.0)
				.constroi();

		leiloeiro.avalia(leilao);
		double maiorEsperado = 400;
		double menorEsperado = 250;

		assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);

	}
	
	

	@Test
	public void deveEntenderLeilaoComApenasUmLance() {

		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
				.lance(joao, 1000.0).constroi();

		leiloeiro.avalia(leilao);

		assertEquals(1000, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(1000, leiloeiro.getMenorLance(), 0.00001);
	}

	@Test
	public void deveEntenderLeilaoComLancesEmOrdemRandomica() {

		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
				.lance(joao, 200.0).lance(maria, 450.0).lance(joao, 120.0)
				.lance(maria, 700.0).lance(joao, 630.0).lance(maria, 230.0)
				.constroi();

		leiloeiro.avalia(leilao);

		assertEquals(700.0, leiloeiro.getMaiorLance(), 0.0001);
		assertEquals(120.0, leiloeiro.getMenorLance(), 0.0001);
	}

	@Test
	public void deveEntenderLeilaoComLancesEmOrdemDecrescente() {
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
				.lance(joao, 400.0).lance(maria, 300.0).lance(joao, 200.0)
				.lance(maria, 100.0).constroi();

		leiloeiro.avalia(leilao);

		assertEquals(400.0, leiloeiro.getMaiorLance(), 0.0001);
		assertEquals(100.0, leiloeiro.getMenorLance(), 0.0001);
	}

	@Test
	public void deveEncontrarOsTresMaioresLances() {
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
				.lance(joao, 100.0).lance(maria, 200.0).lance(joao, 300.0)
				.lance(maria, 400.0).constroi();

		leiloeiro.avalia(leilao);

		List<Lance> maiores = leiloeiro.getTresMaiores();

		assertEquals(3, maiores.size());
		assertEquals(400, maiores.get(0).getValor(), 0.00001);
		assertEquals(300, maiores.get(1).getValor(), 0.00001);
		assertEquals(200, maiores.get(2).getValor(), 0.00001);
	}

	@Test
	public void deveDevolverTodosLancesCasoNaoHajaNoMinimo3() {
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
				.lance(joao, 100.0).lance(maria, 200.0).constroi();

		leiloeiro.avalia(leilao);

		List<Lance> maiores = leiloeiro.getTresMaiores();

		assertEquals(2, maiores.size());
		assertEquals(200, maiores.get(0).getValor(), 0.00001);
		assertEquals(100, maiores.get(1).getValor(), 0.00001);
	}

	@Test(expected=RuntimeException.class)
	public void deveDevolverListaVaziaCasoNaoHajaLances() {
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
				.constroi();
		leiloeiro.avalia(leilao);
		List<Lance> maiores = leiloeiro.getTresMaiores();
		assertEquals(0, maiores.size());
	}
	
	@After
	public void finaliza(){
		System.out.println("fim");
	}
}
