package br.com.projeto.drogaria.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.projeto.drogaria.domain.Cidade;
import br.com.projeto.drogaria.util.HibernateUtil;

public class CidadeDAO extends GenericDAO<Cidade> {

	// metodo para buscar a Cidade por Estado
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Cidade> bucarPorEstado(Integer estadoCodigo) {

		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {

			// criar o critério de Cidade
			Criteria consulta = sessao.createCriteria(Cidade.class);
			// fazendo o Where eq especificar o nome da propriedade
			consulta.add(Restrictions.eq("estado.codigo", estadoCodigo));
			// ordenação por nome
			consulta.addOrder(Order.asc("nome"));
			// coloca tudo numa lista de Cidades de nome resultado
			List<Cidade> resultado = consulta.list();

			return resultado;

		} catch (Exception erro) {
			throw erro;// passar erro
		} finally {
			// liberar recursos
			sessao.close();
		}

	}

}
