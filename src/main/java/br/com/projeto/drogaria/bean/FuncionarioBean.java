package br.com.projeto.drogaria.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.projeto.drogaria.dao.FuncionarioDAO;
import br.com.projeto.drogaria.dao.PessoaDAO;
import br.com.projeto.drogaria.domain.Funcionario;
import br.com.projeto.drogaria.domain.Pessoa;

@ManagedBean(name = "funcionarioBean")
@ViewScoped
public class FuncionarioBean {

	private Funcionario funcionario;
	private Pessoa pessoa;
	private List<Funcionario> funcionarios;
	private List<Pessoa> pessoas;

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
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
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao Listar os Funcionários");
			erro.printStackTrace();
		}
	}

	public void novo() {
		// criando um novo cliente
		funcionario = new Funcionario();

		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao gerar uma novo Funcionário");
			erro.printStackTrace();
		}

	}

	public void salvar() {

		try {

			// Primeiro Estanciar o objeto DAO
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

			// Segundo, chamar o merge
			funcionarioDAO.merge(funcionario);

			// terceiro limpar os dados, criando um novo objeto
			funcionario = new Funcionario();

			// quarto, atualizar a tabela depois de o novo ser inserido
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");

			funcionarios = funcionarioDAO.listar();

			Messages.addGlobalInfo("Cadastro Realizado Com Sucesso !");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao Salvar !");
			erro.printStackTrace();
		}

	}

	public void excluir(ActionEvent evento) {

		try {

			funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");

			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.excluir(funcionario);

			funcionarios = funcionarioDAO.listar();

			Messages.addGlobalInfo(" Cadastro de Funcionário Removida Com Sucesso !");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao Excluir o cadastro de Funcionário");
			erro.printStackTrace();
		}

	}

	public void editar(ActionEvent evento) {

		try {
			funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu ao tentar selecionar uma Pessoa");
			erro.printStackTrace();
		}

	}

}
