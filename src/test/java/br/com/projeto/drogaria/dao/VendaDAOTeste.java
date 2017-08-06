package br.com.projeto.drogaria.dao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.projeto.drogaria.domain.Cliente;
import br.com.projeto.drogaria.domain.Funcionario;
import br.com.projeto.drogaria.domain.Venda;

public class VendaDAOTeste {

	@Test
	@Ignore
	public void salvar() throws ParseException {

		Integer codigoCliente = 2;
		Integer codigoFuncionario = 1;

		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(codigoCliente);

		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(codigoFuncionario);

		if (cliente == null) {
			System.out.println("Registro do CLIENTE não Encontrado !");

		} else if (funcionario == null) {
			System.out.println("Registro do FUNCIONARIO não Encontrado !");

		} else {
			Venda venda = new Venda();

			venda.setDataVenda(new Date());// pegar hora do sistema
			venda.setValorTotal(new BigDecimal("50.00"));
			venda.setCliente(cliente);
			venda.setFuncionario(funcionario);

			VendaDAO vendaDAO = new VendaDAO();
			vendaDAO.salvar(venda);
			System.out.println("Registro Cadastrado com Sucesso !");
		}

	}

	@Test
	@Ignore
	public void listar() {

		VendaDAO vendaDAO = new VendaDAO();
		/* colocar o resultado da pesquisa em uma lista */
		List<Venda> lista = vendaDAO.listar();

		for (Venda venda : lista) {

			System.out.println("");
			System.out.println("Data da Venda: " + venda.getDataVenda());
			System.out.println("Preço Total: " + venda.getValorTotal());
			System.out.println("Nome do Cliente:" + venda.getCliente().getPessoa().getNome());
			System.out.println("Nome do Vendedor:" + venda.getFuncionario().getPessoa().getNome());
			System.out.println("");

		}

	}

	@Test
	@Ignore
	public void buscar() {

		Integer codigo = 1;

		/* criar a cidadeDAO */
		VendaDAO vendaDAO = new VendaDAO();
		/* colocar o resultado da pesquisa em uma lista */
		Venda venda = vendaDAO.buscar(codigo);

		if (venda == null) {
			System.out.println("Registro não Encontrado !");
		} else {

			System.out.println("");
			System.out.println("Data da Venda: " + venda.getDataVenda());
			System.out.println("Preço Total: " + venda.getValorTotal());
			System.out.println("Nome do Cliente:" + venda.getCliente().getPessoa().getNome());
			System.out.println("Nome do Vendedor:" + venda.getFuncionario().getPessoa().getNome());
			System.out.println("");

		}

	}

	@Test
	@Ignore
	public void excluir() {

		Integer codigo = 2;
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscar(codigo);

		if (venda == null) {
			System.out.println("Registro não encontrado");
		} else {

			vendaDAO.excluir(venda);

			System.out.println("");
			System.out.println("Data da Venda: " + venda.getDataVenda());
			System.out.println("Preço Total: " + venda.getValorTotal());
			System.out.println("Nome do Cliente:" + venda.getCliente().getPessoa().getNome());
			System.out.println("Nome do Vendedor:" + venda.getFuncionario().getPessoa().getNome());
			System.out.println("");

			System.out.println("Registro Excluido com Sucesso !");
		}

	}

	@Test
	// @Ignore
	public void editar() throws ParseException {

		Integer codigoCliente = 2;
		Integer codigoFuncionario = 1;
		Integer codigoVenda = 1;

		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(codigoCliente);

		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(codigoFuncionario);

		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscar(codigoVenda);

		if (cliente == null) {
			System.out.println("Registro do CLIENTE não Encontrado !");

		} else if (funcionario == null) {
			System.out.println("Registro do FUNCIONARIO não Encontrado !");

		} else if (venda == null) {
			System.out.println("Registro da Venda não Encontrado !");

		} else {

			venda.setDataVenda(new Date());// pegar hora do sistema
			venda.setValorTotal(new BigDecimal("100.00"));
			venda.setCliente(cliente);
			venda.setFuncionario(funcionario);

			vendaDAO.editar(venda);
			System.out.println("Registro Editado com Sucesso !");
		}

	}
}
