package br.com.rbaselio.leilao.dominio;

import static org.junit.Assert.*;


import org.junit.Test;


public class LeilaoTeste {

	@Test
	public void deveReceberUmLance() {
		Leilao leilao = new Leilao("Macbook");
		
		leilao.propoe(new Lance(new Usuario("Steve"), 2000));
		
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
		
		
	}
	
	@Test
	public void deveReceberVariosLances() {
		Leilao leilao = new Leilao("Macbook");
		
		leilao.propoe(new Lance(new Usuario("Steve"), 2000));
		leilao.propoe(new Lance(new Usuario("Bill"), 3000));
		
		assertEquals(2, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
		assertEquals(3000.0, leilao.getLances().get(1).getValor(), 0.00001);
	}
	
	
	@Test
	public void naoAceitaDoisLancesSeguidos() {
		Leilao leilao = new Leilao("Macbook");
		
		leilao.propoe(new Lance(new Usuario("Steve"), 2000));
		leilao.propoe(new Lance(new Usuario("Steve"), 3000));
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
	}
	
	@Test
	public void naoAceitaDeCincoLancesSeguidos() {
		Leilao leilao = new Leilao("Macbook");
		
		leilao.propoe(new Lance(new Usuario("Steve"), 2000));
		leilao.propoe(new Lance(new Usuario("Bill"), 3000));
		leilao.propoe(new Lance(new Usuario("Steve"), 4000));
		leilao.propoe(new Lance(new Usuario("Bill"), 5000));
		leilao.propoe(new Lance(new Usuario("Steve"), 6000));
		leilao.propoe(new Lance(new Usuario("Bill"), 7000));
		leilao.propoe(new Lance(new Usuario("Steve"), 8000));
		leilao.propoe(new Lance(new Usuario("Bill"), 9000));
		leilao.propoe(new Lance(new Usuario("Steve"), 10000));
		leilao.propoe(new Lance(new Usuario("Bill"), 11000));
		
		leilao.propoe(new Lance(new Usuario("Steve"), 12000));
		
		
		assertEquals(10, leilao.getLances().size());
		assertEquals(11000.0, leilao.getLances().get(leilao.getLances().size() - 1).getValor(), 0.00001);
	}
	
	@Test
    public void deveDobrarOUltimoLanceDado() {
        Leilao leilao = new Leilao("Macbook Pro 15");
        Usuario steveJobs = new Usuario("Steve Jobs");
        Usuario billGates = new Usuario("Bill Gates");

        leilao.propoe(new Lance(steveJobs, 2000));
        leilao.propoe(new Lance(billGates, 3000));
        leilao.dobraLance(steveJobs);

        assertEquals(4000, leilao.getLances().get(2).getValor(), 0.00001);
    }
	
	@Test
    public void naoDeveDobrarCasoNaoHajaLanceAnterior() {
        Leilao leilao = new Leilao("Macbook Pro 15");
        Usuario steveJobs = new Usuario("Steve Jobs");

        leilao.dobraLance(steveJobs);

        assertEquals(0, leilao.getLances().size());
    }
	
	
}
