package br.com.projeto.drogaria.util;

import org.hibernate.Session;
import org.junit.Test;

/*Classe que testa a Classe HibernateUtil*/
public class HibernateUtilteste {

	// metodo de teste conectar a sessão
	@Test
	public void conectar() {
		// capturar uma fabrica de sessão e guardar em uma variável

		// abrindo uma conexão e guardando numa variável tipo Session
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		// encerra a conexão
		sessao.close();
		HibernateUtil.getFabricaDeSessoes().close();
	}
}
