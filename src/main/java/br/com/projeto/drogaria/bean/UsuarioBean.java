package br.com.projeto.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.projeto.drogaria.dao.PessoaDAO;
import br.com.projeto.drogaria.dao.UsuarioDAO;
import br.com.projeto.drogaria.domain.Pessoa;
import br.com.projeto.drogaria.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean(name = "usuario")
@ViewScoped
public class UsuarioBean implements Serializable {
	private Usuario usuario;
	private Pessoa pessoa;
	private List<Usuario> usuarios;
	private List<Pessoa> pessoas;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	@PostConstruct
	public void listar() {

		try {
			UsuarioDAO usuariodao = new UsuarioDAO();
			usuarios = usuariodao.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao Listar os usuários");
			erro.printStackTrace();
		}
	}

	public void novo() {
		// criando um novo cliente
		usuario = new Usuario();

		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao gerar uma novo usuário");
			erro.printStackTrace();
		}

	}

	public void salvar() {

		try {

			// Primeiro Estanciar o objeto DAO
			UsuarioDAO usuarioDAO = new UsuarioDAO();

			// Segundo, chamar o merge
			usuarioDAO.merge(usuario);

			// terceiro limpar os dados, criando um novo objeto
			usuario = new Usuario();
			// quarto, atualizar a tabela depois de o novo ser inserido
			usuarios = usuarioDAO.listar();

			// o mesmo com Pessoa
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");

			Messages.addGlobalInfo("Cadastro Realizado Com Sucesso !");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao Salvar !");
			erro.printStackTrace();
		}

	}

	public void excluir(ActionEvent evento) {

		try {

			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.excluir(usuario);

			usuarios = usuarioDAO.listar();

			Messages.addGlobalInfo(" Cadastro de Usuario Removida Com Sucesso !");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao Excluir o Cadastro de Usuário");
			erro.printStackTrace();
		}

	}

	public void editar(ActionEvent evento) {

		try {
			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu ao tentar selecionar uma Pessoa");
			erro.printStackTrace();
		}

	}

}
