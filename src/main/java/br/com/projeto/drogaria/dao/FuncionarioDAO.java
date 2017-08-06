package br.com.projeto.drogaria.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.projeto.drogaria.domain.Funcionario;
import br.com.projeto.drogaria.util.HibernateUtil;

public class FuncionarioDAO extends GenericDAO<Funcionario> {

	/* funcionario já tem dois listar que trouxe por herança de GenericDAO */
	/*
	 * Será criado um terceiro listar para resilver o problema de navegar no
	 * pessoa
	 */

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Funcionario> listarOrdenado() {
		// primeiro abrir a sessão
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			// criar um critério
			Criteria consulta = sessao.createCriteria(Funcionario.class);
			/*
			 * ensinar oa Hibernate que existe um relacioanemto pessoa com o
			 * apelido p(alias)
			 */
			consulta.createAlias("pessoa", "p");

			/*
			 * poderia navegar de pessoa para outra tabela por exemplo para
			 * navegar até a cidade ou pode pesquisar sobre grafosdo Hibernate
			 * consulta.createAlias("p.pessoa", "c");
			 */

			consulta.addOrder(Order.asc("p.nome"));

			List<Funcionario> resultado = consulta.list();
			return resultado;

		} catch (Exception erro) {
			throw erro;// passar erro
		} finally {
			// liberar recursos
			sessao.close();
		}
	}

}
