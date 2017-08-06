package br.com.projeto.drogaria.util;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
/*Classe de configuração do RESTful*/

//aplication patch quando quiser chamar o meu Restful eu coloco o /"rest"
//http://localhost:8080/Drogaria/rest
@ApplicationPath("rest")
public class DrogariaResourceConfig extends ResourceConfig {

	// criando um construtor onde dirá o caminho da pasta onde estarão os
	// serviços
	public DrogariaResourceConfig() {
		packages("br.com.projeto.drogaria.service");

	}

}
