package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;

public class CalculadoraDePrecos {

	/**
	 * Calcula preço dos ingressos por espetáculo
	 * @param sessao
	 * @param quantidade
	 * @return
	 */
	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco = sessao.getEspetaculo().getTipo().calcularPreco(sessao, quantidade);

		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

}