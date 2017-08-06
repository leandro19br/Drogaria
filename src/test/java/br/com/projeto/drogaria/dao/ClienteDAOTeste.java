package br.com.projeto.drogaria.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.projeto.drogaria.domain.Cliente;
import br.com.projeto.drogaria.domain.Pessoa;

public class ClienteDAOTeste {

	@Test
	// @Ignore
	public void salvar() throws ParseException {

		Integer codigoPessoa = 2;

		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(codigoPessoa);

		if (pessoa == null) {
			System.out.println("Registro da Pessoa não Encontrado !");
		} else {
			Cliente cliente = new Cliente();
			/*
			 * pegar hora do sistema cliente.setDataCadastro(new Date());
			 */
			/* colocando a data com formato da mascara determinada */
			cliente.setDataCadastro(new SimpleDateFormat("dd/MM/yyyy").parse("21/06/2016"));
			cliente.setLiberado(true);
			cliente.setPessoa(pessoa);

			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.salvar(cliente);
			System.out.println("Registro Cadastrado com Sucesso !");
		}

	}

	@Test
	@Ignore
	public void listar() {

		ClienteDAO clienteDAO = new ClienteDAO();
		/* colocar o resultado da pesquisa em uma lista */
		List<Cliente> lista = clienteDAO.listar();

		for (Cliente cliente : lista) {

			System.out.println("");
			System.out.println("Data do Cadastro: " + cliente.getDataCadastro());
			System.out.println("Cliente Liberado: " + cliente.getLiberado());
			System.out.println("Nome do Cliente:" + cliente.getPessoa().getNome());
			System.out.println("");

		}

	}

	@Test
	@Ignore
	public void buscar() {

		Integer codigo = 1;

		/* criar a cidadeDAO */
		ClienteDAO clienteDAO = new ClienteDAO();
		/* colocar o resultado da pesquisa em uma lista */
		Cliente cliente = clienteDAO.buscar(codigo);

		if (cliente == null) {
			System.out.println("Registro não Encontrado !");
		} else {

			System.out.println("");
			System.out.println("Data do Cadastro: " + cliente.getDataCadastro());
			System.out.println("Cliente Liberado: " + cliente.getLiberado());
			System.out.println("Nome do Cliente:" + cliente.getPessoa().getNome());

		}

	}

	@Test
	@Ignore
	public void excluir() {

		Integer codigo = 1;
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(codigo);

		if (cliente == null) {
			System.out.println("Registro não encontrado");
		} else {

			clienteDAO.excluir(cliente);

			System.out.println("");
			System.out.println("Data do Cadastro: " + cliente.getDataCadastro());
			System.out.println("Cliente Liberado: " + cliente.getLiberado());
			System.out.println("Nome do Cliente:" + cliente.getPessoa().getNome());

			System.out.println("Registro Excluido com Sucesso !");
		}

	}

	@Test
	@Ignore
	public void editar() throws ParseException {

		Integer clienteCodigo = 2;
		Integer pessoaCodigo = 5;

		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(clienteCodigo);

		if (cliente == null) {
			System.out.println("Registro Cliente não encontrado");
		} else {

			PessoaDAO pessoaDAO = new PessoaDAO();
			Pessoa pessoa = pessoaDAO.buscar(pessoaCodigo);

			if (pessoa == null) {
				System.out.println("Registro Pessoa não encontrado");
			} else {

				cliente.setDataCadastro(new SimpleDateFormat("dd/MM/yyyy").parse("08/06/2017"));
				cliente.setLiberado(false);
				cliente.setPessoa(pessoa);

				clienteDAO.editar(cliente);
				System.out.println("Registro Editado com Sucesso !");
			}

		}

	}

}
