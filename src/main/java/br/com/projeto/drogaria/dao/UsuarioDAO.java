package br.com.projeto.drogaria.dao;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.projeto.drogaria.domain.Usuario;
import br.com.projeto.drogaria.util.HibernateUtil;

public class UsuarioDAO extends GenericDAO<Usuario> {
	/* método para autenticar o usuário */
	/*
	 * o retorno pode ser boolean ou o Usuario e recebe como parâmetro a senha e
	 * o CPF
	 */
	@SuppressWarnings("deprecation")
	public Usuario autenticar(String cpf, String senha) {
		// primeiro abrir a sessão
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Usuario.class);
			/* criando um "apelido para chegar até o CPF da pessoa" */
			consulta.createAlias("pessoa", "p");
			/* restrições "where" */
			consulta.add(Restrictions.eq("p.cpf", cpf));
			/*
			 * a senha no banco está criptografada, portanto terá que ser
			 * criptografada antes de enviar como restrição ao banco
			 */
			SimpleHash hash = new SimpleHash("md5", senha);
			consulta.add(Restrictions.eq("senha", hash.toHex()));
			/* geralmente a aplicação retorna somente um Usuario */
			Usuario resultado = (Usuario) consulta.uniqueResult();

			System.out.println("pessoa " + resultado);

			return resultado;

		} catch (Exception erro) {
			throw erro;// passar erro
		} finally {
			// liberar recursos
			sessao.close();
		}
	}

}
