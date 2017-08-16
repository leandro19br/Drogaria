package br.com.projeto.drogaria.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import br.com.projeto.drogaria.dao.FabricanteDAO;
import br.com.projeto.drogaria.domain.Fabricante;

/*Classe de repositório de Serviçoes*/
/*ferramenta para testar aplicação rest GET, POST etc.  Advanced Rest Client */

//http://localhost:8080/Drogaria/rest/[nome do repositorio de serviços]

@Path("fabricante")
public class FabricanteService {
	// http://localhost:8080/Drogaria/rest/fabricante
	@GET
	public String listar() {

		FabricanteDAO dao = new FabricanteDAO();
		List<Fabricante> fabricantes = dao.listar();

		Gson gson = new Gson();
		String json = gson.toJson(fabricantes);

		return json;

	}

	// http://localhost:8080/Drogaria/rest/fabricante/10
	@GET
	/* adicionando mais um nível "/" na URL */
	@Path("{codigo}")
	/* para amarrar o path ao objeto código através do @PathParam("{codigo}") */
	public String buscar(@PathParam("codigo") Integer codigo) {
		FabricanteDAO dao = new FabricanteDAO();
		Fabricante fabricante = dao.buscar(codigo);// o código vai ter que vir
													// por uma chamada de
													// serviço

		Gson gson = new Gson();
		String json = gson.toJson(fabricante);

		return json;

	}

	// protocolo POST serve para inserir informações
	// recebe json e converte em objeto
	// http://localhost:8080/Drogaria/rest/fabricante
	@POST
	public String salvar(String json) {
		Gson gson = new Gson();
		// recebe gson string e converte no tipo da classe
		Fabricante fabricante = gson.fromJson(json, Fabricante.class);
		// método para salvar
		FabricanteDAO dao = new FabricanteDAO();
		dao.salvar(fabricante);

		// fazendo a conversão novamente para retornar String com o objeto salvo
		String jsonsaida = gson.toJson(fabricante);

		return jsonsaida;

	}

	/*
	 * Editando utilizando o POST caso o método de salvamento for merge -
	 * {"descricao":"FabricanteTeste","codigo":6}
	 */

	/* Editando utilizando o PUT */
	@PUT
	public String editar(String json) {
		Gson gson = new Gson();
		// recebe gson string e converte no tipo da classe
		Fabricante fabricante = gson.fromJson(json, Fabricante.class);
		// método para salvar
		FabricanteDAO dao = new FabricanteDAO();
		dao.editar(fabricante);

		// fazendo a conversão novamente para retornar String com o objeto salvo
		String jsonsaida = gson.toJson(fabricante);

		return jsonsaida;
	}

	/* Para excluir recebe um parâmetro que vem da URL que é o código */

	// http://localhost:8080/Drogaria/rest/fabricante/{codigo}
	// http://localhost:8080/Drogaria/rest/fabricante/10
	@DELETE
	@Path("{codigo}")
	public String excluir(@PathParam("codigo") Integer codigo) {

		// método Excluir
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		// pesquisa o fabricante antes de excluir
		Fabricante fabricante = fabricanteDAO.buscar(codigo);
		/* excluir fabricante */
		fabricanteDAO.excluir(fabricante);

		Gson gson = new Gson();
		// fazendo a conversão novamente para retornar String com o objeto salvo
		String jsonsaida = gson.toJson(fabricante);

		return jsonsaida;
	}

}
