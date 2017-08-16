package br.com.projeto.drogaria.dao;

import java.text.ParseException;
import java.util.List;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Ignore;
import org.junit.Test;

import br.com.projeto.drogaria.domain.Pessoa;
import br.com.projeto.drogaria.domain.Usuario;
import br.com.projeto.drogaria.enumeracao.TipoUsuario;

public class UsuarioDAOTeste {

	@Test
	// @Ignore
	public void salvar() {

		Integer codigoPessoa = 3;

		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(codigoPessoa);

		if (pessoa == null) {
			System.out.println("Registro da Pessoa não Encontrado !");
		} else {
			Usuario usuario = new Usuario();
			usuario.setSenhaSemCriptografia("123456");
			/*
			 * SimpleHash funçãoque faz a conversão da senha em criptografia no
			 * caso md5 e passando o campo que será criptografado
			 */
			SimpleHash hash = new SimpleHash("md5", usuario.getSenhaSemCriptografia());
			/* senha será gravada no banco com hexadecimal */
			usuario.setSenha(hash.toHex());
			usuario.setTipo('G');
			/* não utiliza mais caracter e sim a constante */
			usuario.setTipoUsuario(TipoUsuario.GERENTE);
			usuario.setAtivo(false);
			usuario.setPessoa(pessoa);

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.salvar(usuario);
			System.out.println("Registro Cadastrado com Sucesso !");
		}

	}

	@Test
	@Ignore
	public void listar() {

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		/* colocar o resultado da pesquisa em uma lista */
		List<Usuario> lista = usuarioDAO.listar();

		for (Usuario usuario : lista) {

			System.out.println("");
			System.out.println("Senha: " + usuario.getSenha());
			System.out.println("Tipo: " + usuario.getTipo());
			System.out.println("Ativo:" + usuario.getAtivo());
			System.out.println("Nome:" + usuario.getPessoa().getNome());
			System.out.println("");

		}

	}

	@Test
	@Ignore
	public void buscar() {

		Integer codigo = 2;

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		/* colocar o resultado da pesquisa em uma lista */
		Usuario usuario = usuarioDAO.buscar(codigo);

		if (usuario == null) {
			System.out.println("Registro não Encontrado !");
		} else {

			System.out.println("");
			System.out.println("Senha: " + usuario.getSenha());
			System.out.println("Tipo: " + usuario.getTipo());
			System.out.println("Ativo:" + usuario.getAtivo());
			System.out.println("Nome:" + usuario.getPessoa().getNome());
			System.out.println("");

		}

	}

	@Test
	@Ignore
	public void excluir() {

		Integer codigo = 1;
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar(codigo);

		if (usuario == null) {
			System.out.println("Registro não encontrado");
		} else {

			usuarioDAO.excluir(usuario);

			System.out.println("");
			System.out.println("Senha: " + usuario.getSenha());
			System.out.println("Tipo: " + usuario.getTipo());
			System.out.println("Ativo:" + usuario.getAtivo());
			System.out.println("Nome:" + usuario.getPessoa().getNome());
			System.out.println("");

			System.out.println("Registro Excluido com Sucesso !");
		}

	}

	@Test
	@Ignore
	public void editar() throws ParseException {

		Integer usuarioCodigo = 2;
		Integer pessoaCodigo = 4;

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar(usuarioCodigo);

		if (usuario == null) {
			System.out.println("Registro Cliente não encontrado");
		} else {

			PessoaDAO pessoaDAO = new PessoaDAO();
			Pessoa pessoa = pessoaDAO.buscar(pessoaCodigo);

			if (pessoa == null) {
				System.out.println("Registro Pessoa não encontrado");
			} else {

				usuario.setSenha("1921");
				usuario.setTipo('c');
				usuario.setAtivo(true);
				usuario.setPessoa(pessoa);

				usuarioDAO.editar(usuario);
				System.out.println("Registro Editado com Sucesso !");
			}

		}

	}

	@Test
	@Ignore
	public void autenticar() {
		/* passar o cpf e senha do usuário */
		String cpf = "265.141.544-44";
		String senha = "123456";

		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuario = dao.autenticar(cpf, senha);

		System.out.println("Usuário autenticado :" + usuario);

	}

}
