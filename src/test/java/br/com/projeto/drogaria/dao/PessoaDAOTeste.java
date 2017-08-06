package br.com.projeto.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.projeto.drogaria.domain.Cidade;
import br.com.projeto.drogaria.domain.Pessoa;

public class PessoaDAOTeste {

	@Test
	@Ignore
	public void salvar() {

		/* Buscar o fabricante por ser uma chave estrangeira */

		Integer codigoCidade = 5;

		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigoCidade);

		if (cidade == null) {
			System.out.println("Registro da Cidade não Encontrado !");
		} else {
			Pessoa pessoa = new Pessoa();
			pessoa.setNome("Leandro Santiago");
			pessoa.setCpf("29729895899");
			pessoa.setRg("297494818");
			pessoa.setRua("Alimação");
			pessoa.setNumero(new Short("692"));
			pessoa.setBairro("Lavapés");
			pessoa.setCep("06700553");
			pessoa.setComplemento("casa");
			pessoa.setTelefone("46161335");
			pessoa.setCelular("984211594");
			pessoa.setEmail("erica2014santiago@gmail.com");
			pessoa.setCidade(cidade);

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.salvar(pessoa);
			System.out.println("Registro Cadastrado com Sucesso !");
		}

	}

	@Test
	@Ignore
	public void listar() {

		PessoaDAO pessoaDAO = new PessoaDAO();
		/* colocar o resultado da pesquisa em uma lista */
		List<Pessoa> lista = pessoaDAO.listar();

		for (Pessoa pessoa : lista) {

			System.out.println("Nome :" + pessoa.getNome());
			System.out.println("CPF :" + pessoa.getCpf());
			System.out.println("RG :" + pessoa.getRg());
			System.out.println("Rua:" + pessoa.getRua());
			System.out.println("Número:" + pessoa.getNumero());
			System.out.println("Bairro" + pessoa.getBairro());
			System.out.println("CEP: " + pessoa.getCep());
			System.out.println("Complemento: " + pessoa.getComplemento());
			System.out.println("Telefone: " + pessoa.getTelefone());
			System.out.println("Celular: " + pessoa.getCelular());
			System.out.println("E-mail: " + pessoa.getEmail());
			System.out.println("Cidade: " + pessoa.getCidade().getNome());
			System.out.println("");

		}

	}

	@Test
	@Ignore
	public void buscar() {

		Integer codigo = 6;

		PessoaDAO produtoDAO = new PessoaDAO();
		/* colocar o resultado da pesquisa em uma lista */
		Pessoa pessoa = produtoDAO.buscar(codigo);

		if (pessoa == null) {
			System.out.println("Registro não Encontrado !");
		} else {

			System.out.println("Nome :" + pessoa.getNome());
			System.out.println("CPF :" + pessoa.getCpf());
			System.out.println("RG :" + pessoa.getRg());
			System.out.println("Rua:" + pessoa.getRua());
			System.out.println("Número:" + pessoa.getNumero());
			System.out.println("Bairro" + pessoa.getBairro());
			System.out.println("CEP: " + pessoa.getCep());
			System.out.println("Complemento: " + pessoa.getComplemento());
			System.out.println("Telefone: " + pessoa.getTelefone());
			System.out.println("Celular: " + pessoa.getCelular());
			System.out.println("E-mail: " + pessoa.getEmail());
			System.out.println("Cidade: " + pessoa.getCidade().getNome());
		}

	}

	@Test
	@Ignore
	public void excluir() {

		Integer codigo = 3;
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(codigo);

		if (pessoa == null) {
			System.out.println("Registro não encontrado");
		} else {

			System.out.println("Nome :" + pessoa.getNome());
			System.out.println("CPF :" + pessoa.getCpf());
			System.out.println("RG :" + pessoa.getRg());
			System.out.println("Rua:" + pessoa.getRua());
			System.out.println("Número:" + pessoa.getNumero());
			System.out.println("Bairro" + pessoa.getBairro());
			System.out.println("CEP: " + pessoa.getCep());
			System.out.println("Complemento: " + pessoa.getComplemento());
			System.out.println("Telefone: " + pessoa.getTelefone());
			System.out.println("Celular: " + pessoa.getCelular());
			System.out.println("E-mail: " + pessoa.getEmail());
			System.out.println("Cidade: " + pessoa.getCidade().getNome());
			pessoaDAO.excluir(pessoa);
			System.out.println("Registro Excluido com Sucesso !");
		}

	}

	@Test
	// @Ignore
	public void editar() {

		Integer pessoaCodigo = 4;
		Integer cidadeCodigo = 3;

		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(pessoaCodigo);

		if (pessoa == null) {
			System.out.println("Registro Produto não encontrado");
		} else {

			CidadeDAO cidadeDAO = new CidadeDAO();
			Cidade cidade = cidadeDAO.buscar(cidadeCodigo);

			if (cidade == null) {
				System.out.println("Registro Fabricante não encontrado");
			} else {
				pessoa.setNome("Gustavo Santiago");
				pessoa.setCpf("29999895899");
				pessoa.setRg("29999998");
				pessoa.setRua("Aclimação");
				pessoa.setNumero(new Short("692"));
				pessoa.setBairro("Lavapés");
				pessoa.setCep("06700553");
				pessoa.setComplemento("casa");
				pessoa.setTelefone("46161335");
				pessoa.setCelular("974673211");
				pessoa.setEmail("gu19br@gmail.com");
				pessoa.setCidade(cidade);

				pessoaDAO.editar(pessoa);
				System.out.println("Registro Editado com Sucesso !");
			}

		}

	}

}
