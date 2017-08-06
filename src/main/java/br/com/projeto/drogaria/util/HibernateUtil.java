package br.com.projeto.drogaria.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.service.ServiceRegistry;

//classe para criar sessão de comunicação do banco com Hibernate 
public class HibernateUtil {

	// retorna do método para a variável global fabricaDeSessoes
	private static SessionFactory fabricaDeSessoes = criarFabricaDeSessoes();

	public static SessionFactory getFabricaDeSessoes() {

		return fabricaDeSessoes;

	}

	/* convertendo sessão em connection por conta do relatório em Jasper */
	public static Connection getConexao() {
		/* pegando uma sessão aberta e guardando na variável sessao */
		final Session sessao = fabricaDeSessoes.openSession();
		/* sessao faz um trablaho de retorno uma conexão */
		Connection conexao = sessao.doReturningWork(new ReturningWork<Connection>() {
			/* método que será executado para retornar o connection */
			/* esse método recebe uma conexão do hibernate */
			@Override
			public Connection execute(Connection conn) throws SQLException {
				// return retorna para a sessao
				return conn;
			}
		});
		return conexao;
	}

	private static SessionFactory criarFabricaDeSessoes() {

		try {
			// ler o arquivo hibernate.cfg com as configurações
			Configuration configuracao = new Configuration().configure();
			// registrar o serviço passando para o serviço quais são as
			// configurações
			ServiceRegistry registro = new StandardServiceRegistryBuilder().applySettings(configuracao.getProperties())
					.build();
			// criar a fabrica de sessões numa variável local
			SessionFactory fabrica = configuracao.buildSessionFactory();

			return fabrica;

		} catch (Throwable ex) {

			System.err.println("A fábrica de sessões não pode ser criada." + ex);

			throw new ExceptionInInitializerError(ex);

		}

	}

}
