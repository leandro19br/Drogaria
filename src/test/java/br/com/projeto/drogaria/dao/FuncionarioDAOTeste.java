package br.com.projeto.drogaria.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.projeto.drogaria.domain.Funcionario;
import br.com.projeto.drogaria.domain.Pessoa;

public class FuncionarioDAOTeste {

	@Test
	@Ignore
	public void salvar() throws ParseException {

		Integer codigoPessoa = 4;

		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(codigoPessoa);

		if (pessoa == null) {
			System.out.println("Registro da Pessoa não Encontrado !");
		} else {
			Funcionario funcionario = new Funcionario();

			funcionario.setCarteiraTrabalho("888888");
			funcionario.setDataAdmissao(new SimpleDateFormat("dd/MM/yyyy").parse("28/08/2008"));
			funcionario.setPessoa(pessoa);

			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.salvar(funcionario);
			System.out.println("Registro Cadastrado com Sucesso !");
		}

	}

	@Test
	@Ignore
	public void listar() {

		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		/* colocar o resultado da pesquisa em uma lista */
		List<Funcionario> lista = funcionarioDAO.listar();

		for (Funcionario funcionario : lista) {

			System.out.println("");
			System.out.println("Carteira de Trabalho: " + funcionario.getCarteiraTrabalho());
			System.out.println("Data do Cadastro: " + funcionario.getDataAdmissao());
			System.out.println("Nome do Cliente:" + funcionario.getPessoa().getNome());
			System.out.println("");

		}

	}

	@Test
	@Ignore
	public void buscar() {

		Integer codigo = 4;

		/* criar a cidadeDAO */
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		/* colocar o resultado da pesquisa em uma lista */
		Funcionario funcionario = funcionarioDAO.buscar(codigo);

		if (funcionario == null) {
			System.out.println("Registro não Encontrado !");
		} else {

			System.out.println("");
			System.out.println("Carteira de Trabalho: " + funcionario.getCarteiraTrabalho());
			System.out.println("Data do Cadastro: " + funcionario.getDataAdmissao());
			System.out.println("Nome do Cliente:" + funcionario.getPessoa().getNome());
			System.out.println("");

		}

	}

	@Test
	@Ignore
	public void excluir() {

		Integer codigo = 3;
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(codigo);

		if (funcionario == null) {
			System.out.println("Registro não encontrado");
		} else {

			funcionarioDAO.excluir(funcionario);

			System.out.println("");
			System.out.println("Carteira de Trabalho: " + funcionario.getCarteiraTrabalho());
			System.out.println("Data do Cadastro: " + funcionario.getDataAdmissao());
			System.out.println("Nome do Cliente:" + funcionario.getPessoa().getNome());
			System.out.println("");

			System.out.println("Registro Excluido com Sucesso !");
		}

	}

	@Test
	// @Ignore
	public void editar() throws ParseException {

		Integer funcionarioCodigo = 2;
		Integer pessoaCodigo = 4;

		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(funcionarioCodigo);

		if (funcionario == null) {
			System.out.println("Registro Funcionario não encontrado");
		} else {

			PessoaDAO pessoaDAO = new PessoaDAO();
			Pessoa pessoa = pessoaDAO.buscar(pessoaCodigo);

			if (pessoa == null) {
				System.out.println("Registro Pessoa não encontrado");
			} else {

				funcionario.setCarteiraTrabalho("9999999");
				funcionario.setDataAdmissao(new SimpleDateFormat("dd/MM/yyyy").parse("21/11/2009"));
				funcionario.setPessoa(pessoa);

				funcionarioDAO.editar(funcionario);
				System.out.println("Registro Editado com Sucesso !");
			}

		}

	}

}
