package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class Avaliador {

	private double maiorDeTodos = Double.NEGATIVE_INFINITY;
    private double menorDeTodos = Double.POSITIVE_INFINITY;
    private double mediaValores = 0;

    public void avalia(Leilao leilao) {

        for(Lance lance : leilao.getLances()) {
        	mediaValores += lance.getValor();
            if(lance.getValor() > maiorDeTodos) {
                maiorDeTodos = lance.getValor();
            }
            else if(lance.getValor() < menorDeTodos) {
                menorDeTodos = lance.getValor();
            }
        }
        mediaValores = mediaValores / leilao.getLances().size();
        
        
    }

    public double getMaiorLance() { return maiorDeTodos; }
    public double getMenorLance() { return menorDeTodos; }
    public double getMedia() { return mediaValores; }
	
}