package br.com.projeto.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.projeto.drogaria.dao.ClienteDAO;
import br.com.projeto.drogaria.dao.PessoaDAO;
import br.com.projeto.drogaria.domain.Cliente;
import br.com.projeto.drogaria.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean(name = "cliente")
@ViewScoped
public class ClienteBean implements Serializable {

	private Cliente cliente;
	private Pessoa pessoa;
	private List<Pessoa> pessoas;
	private List<Cliente> clientes;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@PostConstruct
	public void listar() {

		try {
			ClienteDAO clientedao = new ClienteDAO();
			clientes = clientedao.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao Listar os Clientes");
			erro.printStackTrace();
		}
	}

	public void novo() {
		// criando um novo cliente
		cliente = new Cliente();

		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao gerar uma novo cliente");
			erro.printStackTrace();
		}

	}

	public void salvar() {

		try {

			// Primeiro Estanciar o objeto DAO
			ClienteDAO clienteDAO = new ClienteDAO();

			// Segundo, chamar o merge
			clienteDAO.merge(cliente);

			// terceiro limpar os dados, criando um novo objeto
			cliente = new Cliente();

			// quarto, atualizar a tabela depois de o novo ser inserido
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");

			clientes = clienteDAO.listar();

			Messages.addGlobalInfo("Cadastro Realizado Com Sucesso !");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao Salvar !");
			erro.printStackTrace();
		}

	}

	public void excluir(ActionEvent evento) {

		try {

			cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");

			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.excluir(cliente);

			clientes = clienteDAO.listar();

			Messages.addGlobalInfo(" Cadastro de Cliente Removida Com Sucesso !");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao Excluir o cadastro de cliente");
			erro.printStackTrace();
		}

	}

	public void editar(ActionEvent evento) {

		try {
			cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu ao tentar selecionar uma Pessoa");
			erro.printStackTrace();
		}

	}

}
