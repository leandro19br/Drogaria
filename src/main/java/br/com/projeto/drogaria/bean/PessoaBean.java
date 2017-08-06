package br.com.projeto.drogaria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.projeto.drogaria.dao.CidadeDAO;
import br.com.projeto.drogaria.dao.EstadoDAO;
import br.com.projeto.drogaria.dao.PessoaDAO;
import br.com.projeto.drogaria.domain.Cidade;
import br.com.projeto.drogaria.domain.Estado;
import br.com.projeto.drogaria.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean(name = "pessoa")
@ViewScoped
public class PessoaBean implements Serializable {

	private Pessoa pessoa;
	private List<Pessoa> pessoas;
	private List<Cidade> cidades;
	private List<Estado> estados;
	// criando uma variável de Estado para guardar os estados
	private Estado estado;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@PostConstruct
	public void listar() {

		try {
			PessoaDAO dao = new PessoaDAO();
			pessoas = dao.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao Listar as pessoas");
			erro.printStackTrace();
		}
	}

	public void novo() {
		pessoa = new Pessoa();

		try {

			pessoa = new Pessoa();
			// para limpar o estado ao dar novo
			estado = new Estado();

			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar("nome");
			/*
			 * estanciando cidade vazia onde a lista estará vazia, pois a cidade
			 * aparecerá de acordo com o estado
			 */

			cidades = new ArrayList<Cidade>();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao gerar uma nova pessoa");
			erro.printStackTrace();
		}

	}

	public void salvar() {

		try {

			// Primeiro Estanciar o objeto DAO
			PessoaDAO pessoaDAO = new PessoaDAO();

			// Segundo, chamar o merge
			pessoaDAO.merge(pessoa);

			// terceiro limpar os dados, criando um novo objeto
			pessoa = new Pessoa();

			estado = new Estado();

			// recarreghe o combo de cidade caso algum estado tenha sido salvo
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidades = cidadeDAO.listar();

			// quarto, atualizar a tabela depois de o novo ser inserido
			pessoas = pessoaDAO.listar();

			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar("nome");

			cidades = new ArrayList<Cidade>();

			Messages.addGlobalInfo("Cadastro Realizado Com Sucesso !");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao Salvar !");
			erro.printStackTrace();
		}

	}

	public void excluir(ActionEvent evento) {

		try {

			pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionado");

			PessoaDAO dao = new PessoaDAO();
			dao.excluir(pessoa);

			pessoas = dao.listar();

			Messages.addGlobalInfo(" Cadastro de Pessoa Removida Com Sucesso !");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao Excluir o cadastro de Pessoa");
			erro.printStackTrace();
		}

	}

	public void editar(ActionEvent evento) {

		try {
			/*
			 * pegar a pessoa selecionada (evento é o botão) get pega o objeto e
			 * precisa ser convertido
			 */
			pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionado");

			// selecionando o estado selecionado
			estado = pessoa.getCidade().getEstado();

			// listar todos os estados
			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar("nome");
			/*
			 * atribuir a cidade aos estados ou utilizar o método já criado
			 * popular();
			 */
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidades = cidadeDAO.bucarPorEstado(estado.getCodigo());

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu ao tentar selecionar uma Pessoa");
			erro.printStackTrace();
		}

	}

	/*
	 * popular as cidade com base no estado* utilizando no xhtml <p:ajax
	 * listener="#{pessoa.popular}"/>
	 */

	public void popular() {

		try {

			if (estado != null) {
				// System.out.println("Nome: " + estado.getNome() + "\nCódigo "
				// + estado.getCodigo());

				CidadeDAO cidadeDAO = new CidadeDAO();
				cidades = cidadeDAO.bucarPorEstado(estado.getCodigo());

			} else {
				// caso seja null criara uma lista nova
				cidades = new ArrayList<>();
			}

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu ao tentar filtrar as Cidades");
			erro.printStackTrace();
		}

	}

}
