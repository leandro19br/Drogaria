package br.com.projeto.drogaria.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

import br.com.projeto.drogaria.util.HibernateUtil;

/*Classe DAO Acesso aos dadosData Acesses Object
 * servirá para todos os domains
 * */

public class GenericDAO<Entidade> {

	private Class<Entidade> classe;

	/*
	 * construtor - Para capturar o tipo classe do GenericDao utilizando a API
	 * Reflection - serve para ler atributos e métodos de uma classe em tempos
	 * de execução
	 */

	@SuppressWarnings("unchecked")
	public GenericDAO() {
		/*
		 * esse código paga qual o tipo da classe filha o argumento 0 para pegar
		 * o que está entre <>
		 */
		this.classe = (Class<Entidade>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	// metodo genérico para salvar passando Entidade como parâmetro
	public void salvar(Entidade entidade) {
		// capturar uma sessão aberta
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		// declar objeto para transação
		Transaction transacao = null;

		try {
			// começando uma trançao que recebe uma sessão
			transacao = sessao.beginTransaction();
			// fazer o processo de salvar
			sessao.save(entidade);
			transacao.commit();
		} catch (RuntimeException ex) {
			// se a transação for diferente de null ou seja está aberta
			if (transacao != null) {
				// desfaz a transação
				transacao.rollback();

			}
			// passa a mensagem de erro
			throw ex;
		} finally {
			// se der certo ou errado executa por isso deverá fechara sessão
			sessao.close();
		}

	}

	// método para Listar

	public List<Entidade> listar() {
		// primeiro abrir a sessão
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			/*
			 * não precisa de transação em pesquisa pois não há alterações
			 * criteria para realizar uma consulta - precisa de uma sessão
			 * aberta Criteria consulta = sessao.createCriteria(classe);
			 * 
			 * List<Entidade> resultado = consulta.list(); Não utilizei pois
			 * estava dando Deprecated, por isso utilizei o código abaixo
			 */
			// Create CriteriaBuilder
			CriteriaBuilder builder = sessao.getCriteriaBuilder();

			// Create CriteriaQuery
			CriteriaQuery<Entidade> consulta = builder.createQuery(classe);

			// Specify criteria root
			consulta.from(classe);

			// Execute query
			List<Entidade> resultado = sessao.createQuery(consulta).getResultList();

			return resultado;

		} catch (Exception erro) {
			throw erro;// passar erro
		} finally {
			// liberar recursos
			sessao.close();
		}
	}

	// listar com ordenação sempre ascendente passando parâmetro String
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Entidade> listar(String campoOrdenacao) {
		// primeiro abrir a sessão
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {

			Criteria consulta = sessao.createCriteria(classe);
			consulta.addOrder(Order.asc(campoOrdenacao));
			List<Entidade> resultado = consulta.list();

			return resultado;

		} catch (Exception erro) {
			throw erro;// passar erro
		} finally {
			// liberar recursos
			sessao.close();
		}
	}

	public Entidade buscar(Integer codigo) {
		// primeiro abrir a sessão
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Entidade resultado = null;
		try {
			resultado = sessao.find(classe, codigo);
			return resultado;

		} catch (Exception erro) {
			throw erro;// passar erro
		} finally {
			// liberar recursos
			sessao.close();
		}
	}

	// metodo genérico para Excluir passando Entidade como parâmetro

	public void excluir(Entidade entidade) {
		// capturar uma sessão aberta
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		// declar objeto para transação
		Transaction transacao = null;

		try {
			// começando uma trançao que recebe uma sessão
			transacao = sessao.beginTransaction();
			// fazer o processo de salvar
			sessao.delete(entidade);
			transacao.commit();
		} catch (RuntimeException ex) {
			// se a transação for diferente de null ou seja está aberta
			if (transacao != null) {
				// desfaz a transação
				transacao.rollback();

			}
			// passa a mensagem de erro
			throw ex;
		} finally {
			// se der certo ou errado executa por isso deverá fechara sessão
			sessao.close();
		}

	}

	// método para Editar

	public void editar(Entidade entidade) {
		// capturar uma sessão aberta
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		// declar objeto para transação
		Transaction transacao = null;

		try {
			// começando uma trançao que recebe uma sessão
			transacao = sessao.beginTransaction();
			// fazer o processo de salvar
			sessao.update(entidade);
			transacao.commit();
		} catch (RuntimeException ex) {
			// se a transação for diferente de null ou seja está aberta
			if (transacao != null) {
				// desfaz a transação
				transacao.rollback();

			}
			// passa a mensagem de erro
			throw ex;
		} finally {
			// se der certo ou errado executa por isso deverá fechara sessão
			sessao.close();
		}

	}

	// fundir salvar com atualizar
	@SuppressWarnings("unchecked")
	public Entidade merge(Entidade entidade) {
		// capturar uma sessão aberta
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		// declar objeto para transação
		Transaction transacao = null;

		try {
			// começando uma trançao que recebe uma sessão
			transacao = sessao.beginTransaction();
			// fazer o processo de salvar
			Entidade retorno = (Entidade) sessao.merge(entidade);
			transacao.commit();
			return retorno;
		} catch (RuntimeException ex) {
			// se a transação for diferente de null ou seja está aberta
			if (transacao != null) {
				// desfaz a transação
				transacao.rollback();

			}
			// passa a mensagem de erro
			throw ex;
		} finally {
			// se der certo ou errado executa por isso deverá fechara sessão
			sessao.close();
		}

	}

}
