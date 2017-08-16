package br.com.projeto.drogaria.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import br.com.projeto.drogaria.dao.CidadeDAO;
import br.com.projeto.drogaria.domain.Cidade;

@Path("cidade")
public class CidadeService {

	/*
	 * método listar as cidades do rest filtrando pelo código do estado, passado
	 * pela path estadoCodigo para o parâmetros do método
	 */
	@GET
	@Path("{estadoCodigo}")
	public String buscarPorEstado(@PathParam("estadoCodigo") Integer estadoCodigo) {

		CidadeDAO dao = new CidadeDAO();
		List<Cidade> cidades = dao.bucarPorEstado(estadoCodigo);

		Gson gson = new Gson();
		String json = gson.toJson(cidades);

		return json;

	}

}
