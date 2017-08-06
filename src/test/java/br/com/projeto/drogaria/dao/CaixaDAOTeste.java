package br.com.projeto.drogaria.dao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Ignore;
import org.junit.Test;

import br.com.projeto.drogaria.domain.Caixa;
import junit.framework.Assert;

public class CaixaDAOTeste {

	@Test
	@Ignore
	public void salvar() throws ParseException {
		Caixa caixa = new Caixa();
		caixa.setDataAbertura(new SimpleDateFormat("dd/MM/yyyy").parse("04/08/2008"));
		caixa.setValor(new BigDecimal(100.00));

		CaixaDAO dao = new CaixaDAO();
		dao.merge(caixa);

	}

	@Test
	// @Ignore
	public void buscar() throws ParseException {

		CaixaDAO dao = new CaixaDAO();
		Caixa caixa = dao.buscar(new SimpleDateFormat("dd/MM/yyyy").parse("05/08/2008"));
		System.out.println(caixa);
		/* testa se o caixa é null utilizado pelo Junit */
		Assert.assertNull(caixa);
	}

}
