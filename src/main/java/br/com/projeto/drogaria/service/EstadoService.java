package br.com.projeto.drogaria.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.google.gson.Gson;

import br.com.projeto.drogaria.dao.EstadoDAO;
import br.com.projeto.drogaria.domain.Estado;

//http://localhost:8080/Drogaria/rest/estado
@Path("estado")
public class EstadoService {
	/* método listar todos os estados do rest */
	@GET
	public String listar() {

		EstadoDAO dao = new EstadoDAO();
		List<Estado> estados = dao.listar();

		Gson gson = new Gson();
		String json = gson.toJson(estados);

		return json;

	}

}
