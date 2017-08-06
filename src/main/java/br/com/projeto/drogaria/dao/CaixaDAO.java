package br.com.projeto.drogaria.dao;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.projeto.drogaria.domain.Caixa;
import br.com.projeto.drogaria.util.HibernateUtil;

public class CaixaDAO extends GenericDAO<Caixa> {

	/*
	 * método para buscar por data se já existe um caixa com essa data informada
	 */

	@SuppressWarnings("deprecation")
	public Caixa buscar(Date dataAbertura) {
		/* capturar sessão */
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		/* manipulação de BD precisa ser utilizado try/catch */
		try {
			/* critério de pesquisa do Hibernate */

			Criteria consuta = sessao.createCriteria(Caixa.class);
			/* criar uma restrição */
			/*
			 * a função Restrictions.eq verifica a data de abertura, onde
			 * "dataAbertura" vem do Domain e dataAbertura vem do parâmtro do
			 * método
			 */
			consuta.add(Restrictions.eq("dataAbertura", dataAbertura));
			/*
			 * capturar um resultado de um caixa que pode nulo ou diferente se
			 * for nulo não tem caixe se não já existe caixa e não pode ser
			 * aberto um novo
			 */

			Caixa resultado = (Caixa) consuta.uniqueResult();

			return resultado;

		} catch (RuntimeException err) {
			throw err;// repropaga o erro
		} finally {
			// se der certo ou errado vai fechar a seesão
			sessao.close();
		}
	}

}
