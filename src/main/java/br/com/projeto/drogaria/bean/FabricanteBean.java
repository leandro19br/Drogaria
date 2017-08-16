package br.com.projeto.drogaria.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import org.omnifaces.util.Messages;

import com.google.gson.Gson;

import br.com.projeto.drogaria.domain.Fabricante;

@SuppressWarnings("serial")
@ManagedBean(name = "fabricante")
@ViewScoped
public class FabricanteBean implements Serializable {

	private Fabricante fabricante;
	private List<Fabricante> fabricantes;

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

	// Método para Listar os Fabricantes
	@PostConstruct
	public void listar() {

		try {
			/*
			 * ao invés de chamar o DAO será chamado o serviço REST para
			 * desacoplar o Front do Back
			 */
			// FabricanteDAO dao = new FabricanteDAO();
			// fabricantes = dao.listar();
			// primeiro criar um client
			Client cliente = ClientBuilder.newClient();
			// serve para passar os endereços do serviço
			WebTarget caminho = cliente.target("http://localhost:8080/Drogaria/rest/fabricante");
			/* dispara uma requisição GET do tipo json */
			String json = caminho.request().get(String.class);
			// precisa converter esse json em um List

			Gson gson = new Gson();
			/*
			 * como não tem como fazer uma conversão em Array tem que converter
			 * em um vetor e depois em list
			 */
			Fabricante[] vetor = gson.fromJson(json, Fabricante[].class);
			// metodo para converter o vetor para Arraylist
			fabricantes = Arrays.asList(vetor);

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar LISTAR o Estado");
			// detalhamento do erro na execução verificar no log do server
			erro.printStackTrace();
		}

	}

	// Método para estanciar novo Fabricante
	public void novo() {

		fabricante = new Fabricante();

	}

	public void salvar() {

		try {
			/*
			 * estanciando objeto FabricanteDAO FabricanteDAO dao = new
			 * FabricanteDAO();
			 * 
			 * chamado o método salvar de dao utilizando merge tanto para novo
			 * como para editar
			 * 
			 * dao.merge(fabricante); para atualizar a lista depois da inclusão
			 * novo();limpar a tela fabricantes = dao.listar();
			 * 
			 */
			/* Utilizando o serviço REST */

			// 1 criar cliente
			Client cliente = ClientBuilder.newClient();
			// 2 definindo caminho do serviço
			WebTarget caminho = cliente.target("http://localhost:8080/Drogaria/rest/fabricante");
			// 3 convertendo o Fabricante para json
			Gson gson = new Gson();
			String json = gson.toJson(fabricante);
			// 4 criar um Entity a partir da String para a requisição POST
			caminho.request().post(Entity.json(json));

			// Limpando

			fabricante = new Fabricante();
			// atualizando a lista com REST

			json = caminho.request().get(String.class);
			// precisa converter esse json em um List

			Fabricante[] vetor = gson.fromJson(json, Fabricante[].class);
			// metodo para converter o vetor para Arraylist
			fabricantes = Arrays.asList(vetor);

			Messages.addGlobalInfo("Fabricante salvo com Sucesso !");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao SALVAR o Fabricante");
			erro.printStackTrace();
		}

	}

	public void excluir(ActionEvent evento) {

		try {
			/* pega o fabricante que vai ser deletado */
			fabricante = (Fabricante) evento.getComponent().getAttributes().get("fabricanteSelecionado");
			// 1 - criar cliente
			Client cliente = ClientBuilder.newClient();
			/*
			 * 2 - definindo caminho do serviço + o código do fabricante que
			 * será deletado utilizando o .path que é amarrado ao path do
			 * FabricanteServive
			 */
			/*
			 * O PRIMEIRO CAMINHO SE REFERE AO
			 * http://localhost:8080/Drogaria/rest/fabricante sem o código
			 */
			WebTarget caminho = cliente.target("http://localhost:8080/Drogaria/rest/fabricante");
			/*
			 * o CaminhoExcluir vai exibir o seguinte
			 * http://localhost:8080/Drogaria/rest/fabricante/10 ,por exemplo
			 * passando o código do fabricante que será excluído
			 */
			WebTarget caminhoExcluir = caminho.path("{codigo}").resolveTemplate("codigo", fabricante.getCodigo());

			/* fazendo uma chamada e deletando passando o caminhoExcluir */
			caminhoExcluir.request().delete();

			/* atualizar a lista */
			String json = caminho.request().get(String.class);
			Gson gson = new Gson();
			// precisa converter esse json em um List
			Fabricante[] vetor = gson.fromJson(json, Fabricante[].class);
			// metodo para converter o vetor para Arraylist
			fabricantes = Arrays.asList(vetor);

			Messages.addGlobalInfo("Fabricante " + fabricante.getDescricao() + " Excluido com Sucesso!");

		} catch (RuntimeException error) {
			Messages.addGlobalInfo("Erro ao Excluir Fabricante");
			error.printStackTrace();
		}

	}

	public void editar(ActionEvent evento) {

		try {
			fabricante = (Fabricante) evento.getComponent().getAttributes().get("fabricanteSelecionado");

			Messages.addGlobalInfo("Nome: " + fabricante.getDescricao());
		} catch (RuntimeException error) {
			Messages.addGlobalInfo("Erro ao Excluir Fabricante");
			error.printStackTrace();
		}

	}

}
