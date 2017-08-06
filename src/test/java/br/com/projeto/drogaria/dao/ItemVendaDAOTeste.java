package br.com.projeto.drogaria.dao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.projeto.drogaria.domain.ItemVenda;
import br.com.projeto.drogaria.domain.Produto;
import br.com.projeto.drogaria.domain.Venda;

public class ItemVendaDAOTeste {

	@Test
	@Ignore
	public void salvar() {

		Integer codigoProduto = 2;
		Integer codigoVenda = 6;

		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(codigoProduto);

		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscar(codigoVenda);

		if (produto == null) {
			System.out.println("Registro do PRODUTO não Encontrado !");

		} else if (venda == null) {
			System.out.println("Registro do VENDA não Encontrado !");

		} else {
			ItemVenda itemVenda = new ItemVenda();

			itemVenda.setQuantidade(new Short("5"));
			itemVenda.setValorParcial(new BigDecimal("20.00"));
			itemVenda.setProduto(produto);
			itemVenda.setVenda(venda);

			ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
			itemVendaDAO.salvar(itemVenda);
			System.out.println("Registro Cadastrado com Sucesso !");
		}

	}

	@Test
	@Ignore
	public void listar() {

		ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
		/* colocar o resultado da pesquisa em uma lista */
		List<ItemVenda> lista = itemVendaDAO.listar();

		for (ItemVenda itemVenda : lista) {

			System.out.println("");
			System.out.println("Quantidade: " + itemVenda.getQuantidade());
			System.out.println("Preço: " + itemVenda.getValorParcial());
			System.out.println("Produto:" + itemVenda.getProduto().getDescricao());
			System.out.println("Venda:" + itemVenda.getVenda().getCliente());
			System.out.println("");

		}

	}

	@Test
	@Ignore
	public void buscar() {

		Integer codigo = 4;

		/* criar a cidadeDAO */
		ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
		/* colocar o resultado da pesquisa em uma lista */
		ItemVenda itemVenda = itemVendaDAO.buscar(codigo);

		if (itemVenda == null) {
			System.out.println("Registro não Encontrado !");
		} else {

			System.out.println("");
			System.out.println("Quantidade: " + itemVenda.getQuantidade());
			System.out.println("Preço: " + itemVenda.getValorParcial());
			System.out.println("Produto:" + itemVenda.getProduto().getDescricao());
			System.out.println("Venda:" + itemVenda.getVenda().getCliente());
			System.out.println("");

		}

	}

	@Test
	@Ignore
	public void excluir() {

		Integer codigo = 2;
		ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
		ItemVenda itemVenda = itemVendaDAO.buscar(codigo);

		if (itemVenda == null) {
			System.out.println("Registro não encontrado");
		} else {

			itemVendaDAO.excluir(itemVenda);

			System.out.println("");
			System.out.println("Quantidade: " + itemVenda.getQuantidade());
			System.out.println("Preço: " + itemVenda.getValorParcial());
			System.out.println("Produto:" + itemVenda.getProduto().getDescricao());
			System.out.println("Venda:" + itemVenda.getVenda().getCliente());
			System.out.println("");

			System.out.println("Registro Excluido com Sucesso !");
		}

	}

	@Test
	// @Ignore
	public void editar() throws ParseException {

		Integer codigoProduto = 2;
		Integer codigoVenda = 1;
		Integer codigoItemVenda = 1;

		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(codigoProduto);

		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscar(codigoVenda);

		ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
		ItemVenda itemVenda = itemVendaDAO.buscar(codigoItemVenda);

		if (produto == null) {
			System.out.println("Registro do PRODUTO não Encontrado !");

		} else if (venda == null) {
			System.out.println("Registro do VENDA não Encontrado !");

		} else if (itemVenda == null) {
			System.out.println("Registro da ITEM não Encontrado !");

		} else {

			itemVenda.setQuantidade(new Short("2"));
			itemVenda.setValorParcial(new BigDecimal("5.00"));
			itemVenda.setProduto(produto);
			itemVenda.setVenda(venda);

			itemVendaDAO.editar(itemVenda);

			System.out.println("Registro Editado com Sucesso !");
		}

	}

}
