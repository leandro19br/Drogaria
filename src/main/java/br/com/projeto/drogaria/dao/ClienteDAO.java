package br.com.projeto.drogaria.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.projeto.drogaria.domain.Cliente;
import br.com.projeto.drogaria.util.HibernateUtil;

public class ClienteDAO extends GenericDAO<Cliente> {

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Cliente> listarOrdenado() {
		// primeiro abrir a sessão
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			// criar um critério
			Criteria consulta = sessao.createCriteria(Cliente.class);
			/*
			 * ensinar oa Hibernate que existe um relacioanemto pessoa com o
			 * apelido p(alias)
			 */
			consulta.createAlias("pessoa", "p");
			consulta.addOrder(Order.asc("p.nome"));
			List<Cliente> resultado = consulta.list();
			return resultado;

		} catch (Exception erro) {
			throw erro;// passar erro
		} finally {
			// liberar recursos
			sessao.close();
		}
	}

}
