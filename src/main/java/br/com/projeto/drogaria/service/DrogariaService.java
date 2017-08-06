package br.com.projeto.drogaria.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/*Classe de repositório de Serviçoes*/

//http://localhost:8080/Drogaria/rest/[nome do repositorio de serviços]
//http://localhost:8080/Drogaria/rest/drogaria
@Path("drogaria")
public class DrogariaService {
	// criando primeiro serviço que é um método

	/*
	 * método vai ser um serviço que não recebe parâmetroe devolve como resposta
	 * uma String
	 */
	/*
	 * O navegador por padrão sempre usa GET
	 */
	@GET
	public String exibir() {

		return "Java";

	}

}
