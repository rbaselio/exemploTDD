package br.com.rbaselio.leilao.teste;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import br.com.rbaselio.leilao.dominio.Lance;
import br.com.rbaselio.leilao.dominio.Usuario;
import br.com.rbaselio.leilao.servico.FiltroDeLances;

public class FiltroDeLancesTest {

	@Test
    public void deveSelecionarLancesMaioresQue5000() {
        Usuario joao = new Usuario("Joao");

        FiltroDeLances filtro = new FiltroDeLances();
        List<Lance> resultado = filtro.filtra(Arrays.asList(
                new Lance(joao,10000), 
                new Lance(joao, 800)));

        assertEquals(1, resultado.size());
        assertEquals(10000, resultado.get(0).getValor(), 0.00001);
    }

    @Test
    public void deveEliminarMenoresQue500() {
        Usuario joao = new Usuario("Joao");

        FiltroDeLances filtro = new FiltroDeLances();
        List<Lance> resultado = filtro.filtra(Arrays.asList(
                new Lance(joao,400), 
                new Lance(joao, 300)));

        assertEquals(0, resultado.size());
    }

    @Test
    public void deveEliminarEntre700E1000() {
        Usuario joao = new Usuario("Joao");

        FiltroDeLances filtro = new FiltroDeLances();
        List<Lance> resultado = filtro.filtra(Arrays.asList(
                new Lance(joao, 800),
                new Lance(joao, 1000),
                new Lance(joao, 700),
                new Lance(joao, 900)));

        assertEquals(0, resultado.size());
    }

    @Test
    public void deveEliminarEntre3000E5000() {
        Usuario joao = new Usuario("Joao");

        FiltroDeLances filtro = new FiltroDeLances();
        List<Lance> resultado = filtro.filtra(Arrays.asList(
                new Lance(joao,4000), 
                new Lance(joao, 3500)));

        assertEquals(0, resultado.size());
    }
}