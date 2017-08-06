package br.com.projeto.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.projeto.drogaria.dao.CidadeDAO;
import br.com.projeto.drogaria.dao.EstadoDAO;
import br.com.projeto.drogaria.domain.Cidade;
import br.com.projeto.drogaria.domain.Estado;

@SuppressWarnings("serial")
@ManagedBean(name = "cidade")
@ViewScoped
public class CidadeBean implements Serializable {

	private List<Cidade> cidades;// lista para guardar as Cidades
	private Cidade cidade;
	private List<Estado> estados;// lista para guardar os Estados

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
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

	@PostConstruct
	public void listar() {

		try {
			CidadeDAO dao = new CidadeDAO();
			cidades = dao.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao Listar as Cidades");
			erro.printStackTrace();
		}
	}

	public void novo() {
		cidade = new Cidade();

		try {
			EstadoDAO dao = new EstadoDAO();
			// passando parâmetro para ordenar no campo de estado
			estados = dao.listar("nome");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao Listar uma nova Cidades");
			erro.printStackTrace();
		}

	}

	public void salvar() {

		try {

			// Primeiro Estanciar o objeto DAO
			CidadeDAO dao = new CidadeDAO();

			// Segundo, chamar o merge
			dao.merge(cidade);

			// terceiro limpar os dados de cidade, criando um novo objeto
			cidade = new Cidade();

			// recarreghe o combo de estados caso algum estado tenha sido salvo
			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar();

			// quarto, atualizar a tabela depois de o novo ser inserido
			cidades = dao.listar();

			Messages.addGlobalInfo("Cadastro Realizado Com Sucesso !");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao Salvar a Cidades");
			erro.printStackTrace();
		}

	}

	public void excluir(ActionEvent evento) {

		try {

			cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeSelecionado");

			CidadeDAO dao = new CidadeDAO();
			dao.excluir(cidade);

			cidades = dao.listar();

			Messages.addGlobalInfo("Cidade Removida Com Sucesso !");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao Excluir a Cidades");
			erro.printStackTrace();
		}

	}

	public void editar(ActionEvent evento) {

		try {
			cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeSelecionado");

			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu ao tentar selecionar uma Cidades");
			erro.printStackTrace();
		}

	}

}
