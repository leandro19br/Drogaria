package br.com.projeto.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.projeto.drogaria.dao.ClienteDAO;
import br.com.projeto.drogaria.dao.FuncionarioDAO;
import br.com.projeto.drogaria.dao.VendaDAO;
import br.com.projeto.drogaria.domain.Cliente;
import br.com.projeto.drogaria.domain.Funcionario;
import br.com.projeto.drogaria.domain.Venda;

@SuppressWarnings("serial")
@ManagedBean(name = "vendaBean")
@ViewScoped
public class ItemVendaBean implements Serializable {

	private Venda venda;
	private List<Venda> vendas;
	private List<Cliente> clientes;
	private List<Funcionario> funcionarios;

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public List<Venda> getVendas() {
		return vendas;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	@PostConstruct
	public void listar() {

		try {
			VendaDAO dao = new VendaDAO();
			vendas = dao.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao Listar os Produtos");
			erro.printStackTrace();
		}
	}

	public void novo() {
		venda = new Venda();

		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.listar();

			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao Listar uma nova Venda");
			erro.printStackTrace();
		}

	}

	public void salvar() {

		try {

			// Primeiro Estanciar o objeto DAO
			VendaDAO vendaDAO = new VendaDAO();

			// Segundo, chamar o merge
			vendaDAO.merge(venda);

			// terceiro limpar os dados, criando um novo objeto
			venda = new Venda();

			// quarto, atualizar a tabela depois de o novo ser inserido
			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.listar();

			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listar();

			Messages.addGlobalInfo("Cadastro Realizado Com Sucesso !");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao Salvar !");
			erro.printStackTrace();
		}

	}

	public void excluir(ActionEvent evento) {

		try {

			venda = (Venda) evento.getComponent().getAttributes().get("vendaSelecionado");

			VendaDAO vendaDAO = new VendaDAO();
			vendaDAO.excluir(venda);

			vendas = vendaDAO.listar();

			Messages.addGlobalInfo(" Venda Removida Com Sucesso !");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao Excluir a Venda");
			erro.printStackTrace();
		}

	}

	public void editar(ActionEvent evento) {

		try {
			venda = (Venda) evento.getComponent().getAttributes().get("vendaSelecionado");

			VendaDAO vendaDAO = new VendaDAO();
			vendas = vendaDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu ao tentar selecionar uma Pessoa");
			erro.printStackTrace();
		}

	}

}
