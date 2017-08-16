package br.com.projeto.drogaria.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.google.gson.Gson;

import br.com.projeto.drogaria.dao.ProdutoDAO;
import br.com.projeto.drogaria.domain.Produto;

/*http://localhost:8080/Drogaria/rest - chama o servidor*/
/*http://localhost:8080/Drogaria/rest/produto - chama o serviço*/
@Path("produto") // nome do serviço
public class ProdutoService {

	/* métodos de chamada GET,POST,PUT e DELETE */

	/* Método retorna um Json que nada mais é que texto */
	@GET // definindo protocolo de chamada
	public String listar() {
		/* buscando os produtos no banco */
		ProdutoDAO produtoDAO = new ProdutoDAO();
		/* guardando o resultado da pesquisa em uma lista de Objetos Porduto */

		List<Produto> produtos = produtoDAO.listar("descricao");

		/* convertendo essa lista em String utilizando a biblioteca do Google */
		Gson gson = new Gson();
		String json = gson.toJson(produtos); // lista de Objetos convertida em
												// formato texto

		return json;

	}

	/*
	 * método para salvar tendo um retorno do produto salvo, onde recebe como
	 * parâmetro o produto a salvar
	 */

	@POST // protocolo voltado para inclusões
	public String salvar(String json) {

		/* converter a String json em objeto do tipo Produto */
		Gson gson = new Gson();
		/*
		 * convertendo a String json em um porduto, onde eu passo a Sting = json
		 * e o tipo que será convertido Produto.class, convertendo tb a chave
		 * estrangeira
		 */
		Produto produto = gson.fromJson(json, Produto.class);

		ProdutoDAO produtoDA = new ProdutoDAO();
		/* salvando o produto */
		produto = produtoDA.merge(produto);
		/* a partir daqui o porduto já tem a chave primária */
		/*
		 * convertendo novamente em json para passar como retorno para o método
		 */
		String jsonSaida = gson.toJson(produto);
		return jsonSaida;

	}

}
