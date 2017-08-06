package br.com.projeto.drogaria.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.projeto.drogaria.domain.Fabricante;
import br.com.projeto.drogaria.domain.Produto;

public class ProdutoDAOTeste {

	@Test
	@Ignore
	public void salvar() {

		/* Buscar o fabricante por ser uma chave estrangeira */

		Integer codigoFabricante = 2;

		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(codigoFabricante);

		if (fabricante == null) {
			System.out.println("Registro do Fabricante não Encontrado !");
		} else {
			Produto produto = new Produto();
			produto.setDescricao("Buscofem 400mg");
			;
			produto.setFabricante(fabricante);
			/* para Digitar um campo Bigdecimal tem que criar um Bigdecimal */
			produto.setPreco(new BigDecimal("20.30"));
			produto.setQuantidade(new Short("10"));

			ProdutoDAO cidadeDAO = new ProdutoDAO();
			cidadeDAO.salvar(produto);
			System.out.println("Registro Cadastrado com Sucesso !");
		}

	}

	@Test
	@Ignore
	public void listar() {

		ProdutoDAO produtoDAO = new ProdutoDAO();
		/* colocar o resultado da pesquisa em uma lista */
		List<Produto> lista = produtoDAO.listar();

		for (Produto produto : lista) {

			System.out.println("Descrição do Produto:" + produto.getDescricao());
			System.out.println("Fabricante do Produto:" + produto.getFabricante().getDescricao());
			System.out.println("Preço do Produto:" + produto.getPreco());
			System.out.println("Quantidade do Produto:" + produto.getQuantidade());
		}

	}

	@Test
	@Ignore
	public void buscar() {

		Integer codigo = 1;

		/* criar a cidadeDAO */
		ProdutoDAO produtoDAO = new ProdutoDAO();
		/* colocar o resultado da pesquisa em uma lista */
		Produto produto = produtoDAO.buscar(codigo);

		if (produto == null) {
			System.out.println("Registro não Encontrado !");
		} else {

			System.out.println("Descrição do Produto:" + produto.getDescricao());
			System.out.println("Fabricante do Produto:" + produto.getFabricante().getDescricao());
			System.out.println("Preço do Produto:" + produto.getPreco());
			System.out.println("Quantidade do Produto:" + produto.getQuantidade());
		}

	}

	@Test
	@Ignore
	public void excluir() {

		Integer codigo = 1;
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(codigo);

		if (produto == null) {
			System.out.println("Registro não encontrado");
		} else {

			produtoDAO.excluir(produto);
			System.out.println("Descrição do Produto:" + produto.getDescricao());
			System.out.println("Fabricante do Produto:" + produto.getFabricante().getDescricao());
			System.out.println("Preço do Produto:" + produto.getPreco());
			System.out.println("Quantidade do Produto:" + produto.getQuantidade());
			System.out.println("Registro Excluido com Sucesso !");
		}

	}

	@Test
	@Ignore
	public void editar() {

		Integer produtoCodigo = 4;
		Integer fabricanteCodigo = 13;

		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(produtoCodigo);

		if (produto == null) {
			System.out.println("Registro Produto não encontrado");
		} else {

			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			Fabricante fabricante = fabricanteDAO.buscar(fabricanteCodigo);

			if (fabricante == null) {
				System.out.println("Registro Fabricante não encontrado");
			} else {
				produto.setDescricao("Omeprazol");
				produto.setFabricante(fabricante);
				produto.setPreco(new BigDecimal("25.30"));
				produto.setQuantidade(new Short("50"));

				produtoDAO.editar(produto);
				System.out.println("Registro Editado com Sucesso !");
			}

		}

	}

}
