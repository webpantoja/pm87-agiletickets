package br.com.caelum.agiletickets.models;

import java.math.BigDecimal;

import javax.validation.constraints.AssertTrue;

import org.junit.Assert;
import org.junit.Test;

public class SessaoTest {

	@Test
	public void deveriaVenderIngressoQuandoHaVagasSuficienteDisponivel() throws Exception {
		Sessao sessao = new Sessao();
        sessao.setTotalIngressos(2);

        Assert.assertTrue(sessao.podeReservar(1));
	}

	@Test
	public void naoDeveVender3ingressoSeHa2vagas() throws Exception {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(2);

		Assert.assertFalse(sessao.podeReservar(3));
	}

	@Test
	public void reservarIngressosDeveDiminuirONumeroDeIngressosDisponiveis() throws Exception {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(5);

		sessao.reserva(3);
		Assert.assertEquals(2, sessao.getIngressosDisponiveis().intValue());
	}
	
	@Test
	public void deveVender2IngressosSeHa2Vagas(){
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(2);
		
		Assert.assertTrue(sessao.podeReservar(2));
	}
	
	@Test
	public void deveAcrescentarValorQuandoAtingirNumeroMaximoDeReserva(){
		Espetaculo espetaculo = new Espetaculo();
		espetaculo.setNome("Show");
		espetaculo.setTipo(TipoDeEspetaculo.CINEMA);
		
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(100);
		sessao.reserva(94);
		sessao.setPreco(new BigDecimal(50));
		sessao.setEspetaculo(espetaculo);
		BigDecimal valor = sessao.getEspetaculo().getTipo().calcularPreco(sessao, 1);
		
		Assert.assertEquals(valor, new BigDecimal(50));
		
	}
	
}
