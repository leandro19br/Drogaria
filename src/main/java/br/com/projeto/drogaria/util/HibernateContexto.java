package br.com.projeto.drogaria.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/*ServletContextListner para melhorar desempenho da inicialização do Hibernate 
 * foi adicionada a dependência do Servlet no arquivo POM
 * interface que tem dois métodos quando o Tomcat é ligado e desligado*/

/* No arquivo web.xml colocar a seguinte tag*/
/*<listener>
		<listener-class>br.com.projeto.drogaria.util.HibernateContexto</listener-class>
	</listener>*/
public class HibernateContexto implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		/*
		 * Destruindo a fábrica de sessões quando desligo o Tomcat
		 */
		HibernateUtil.getFabricaDeSessoes().close();
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		/*
		 * fazendo uma chamada ao Hinbernate a fábrica de sessões quando ligo o
		 * Tomcat
		 */
		HibernateUtil.getFabricaDeSessoes();

	}

}
