package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Assert;
import org.junit.Test;

public class EspetaculoTest {

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}
	
	@Test
	public void deveCriarUnicaSessaoQuandoInicioEFimNoMesmoDia(){
		Espetaculo pablo = new Espetaculo();
		pablo.setNome("Pablo");
		pablo.setId((long) 1);
		
		LocalDate inicio = new LocalDate(2016, 12, 14);
		LocalDate fim =  new LocalDate(2016, 12, 14);
		LocalTime horario = new LocalTime(21, 0);
		Periodicidade periodicidade = Periodicidade.DIARIA;
		
		List<Sessao> sessoes = pablo.criaSessoes(inicio, fim, horario, periodicidade);
		Assert.assertNotNull("lista de excecoes não pode ser nula", sessoes);
		Assert.assertEquals(1, sessoes.size());
		
		Sessao sessao = sessoes.get(0);
		Assert.assertEquals("14/12/16", sessao.getDia());
		Assert.assertEquals(sessao.getHora(), "21:00");
	}
	
	@Test
	public void deveCriarUnicaSessaoNoDiaQuandoInicioEFimDiferentes(){
		Espetaculo pablo = new Espetaculo();
		pablo.setNome("Pablo");
		pablo.setId((long) 1);
		
		LocalDate inicio = new LocalDate(2016, 12, 14);
		LocalDate fim =  new LocalDate(2016, 12, 16);
		LocalTime horario = new LocalTime(21, 0);
		Periodicidade periodicidade = Periodicidade.DIARIA;
		
		List<Sessao> sessoes = pablo.criaSessoes(inicio, fim, horario, periodicidade);
		Assert.assertNotNull("lista de excecoes não pode ser nula", sessoes);
		Assert.assertEquals(3, sessoes.size());
		
		Sessao sessao = sessoes.get(0);
		Assert.assertEquals("14/12/16", sessoes.get(0).getDia());
		Assert.assertEquals("15/12/16", sessoes.get(1).getDia());
		Assert.assertEquals("16/12/16", sessoes.get(2).getDia());
		Assert.assertEquals(sessao.getHora(), "21:00");
	}
	
	@Test
	public void deveCriarSessaoDiaMesPosterior() {
		Espetaculo pablo = new Espetaculo();
		pablo.setNome("Pablo");
		pablo.setId((long) 1);
		
		LocalDate inicio = new LocalDate(2016, 11, 30);
		LocalDate fim =  new LocalDate(2016, 12, 01);
		LocalTime horario = new LocalTime(21, 0);
		Periodicidade periodicidade = Periodicidade.DIARIA;
		
		List<Sessao> sessoes = pablo.criaSessoes(inicio, fim, horario, periodicidade);
		Assert.assertNotNull("lista de excecoes não pode ser nula", sessoes);
		Assert.assertEquals(1, sessoes.size());
		
		Sessao sessao = sessoes.get(0);
		Assert.assertEquals("30/11/16", sessoes.get(0).getDia());
		Assert.assertEquals(sessao.getHora(), "21:00");
	}
	
	@Test
	public void deveCriarSessaoSemanal(){
		Espetaculo pablo = new Espetaculo();
		pablo.setNome("Pablo");
		pablo.setId((long) 1);
		
		LocalDate inicio = new LocalDate(2016, 12, 14);
		LocalDate fim =  new LocalDate(2016, 12, 22);
		LocalTime horario = new LocalTime(21, 0);
		Periodicidade periodicidade = Periodicidade.SEMANAL;
		
		List<Sessao> sessoes = pablo.criaSessoes(inicio, fim, horario, periodicidade);
		Assert.assertNotNull("lista de excecoes não pode ser nula", sessoes);
		Assert.assertEquals(2, sessoes.size());
		
		Sessao sessao = sessoes.get(0);
		Assert.assertEquals("14/12/16", sessao.getDia());
		Assert.assertEquals(sessao.getHora(), "21:00");
	}

	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
}
