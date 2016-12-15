package br.com.caelum.agiletickets.models;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.domain.precos.PrecoReserva;
import br.com.caelum.agiletickets.domain.precos.PrecoReservaMaxima;
import br.com.caelum.agiletickets.domain.precos.PrecoReservaMinima;


public enum TipoDeEspetaculo {
	
	CINEMA(new PrecoReservaMinima()), 
	SHOW(new PrecoReservaMinima()), 
	TEATRO(null), 
	BALLET(new PrecoReservaMaxima()), 
	ORQUESTRA(new PrecoReservaMaxima());
	
	private PrecoReserva precoReserva;
	
	private TipoDeEspetaculo(PrecoReserva precoReserva) {
		this.precoReserva = precoReserva;
	}
	
	public BigDecimal calcularPreco(Sessao sessao, int quantidade){
		BigDecimal preco = sessao.getPreco();
		if(precoReserva!=null){
			preco = precoReserva.calcular(sessao, quantidade);
		}
		return preco;
	}
}
