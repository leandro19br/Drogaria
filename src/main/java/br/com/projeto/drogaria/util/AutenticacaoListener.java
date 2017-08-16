package br.com.projeto.drogaria.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.omnifaces.util.Faces;

import br.com.projeto.drogaria.bean.AutenticacaoBean;
import br.com.projeto.drogaria.domain.Usuario;

/* O PhaseListener intersepta antes e depois das fazes do JSF. 
 * implementar a inteface do java PhaseListener e seus métodos.
 *Após fazero listener precisa registrar no FacesConfig*/

@SuppressWarnings("serial")
public class AutenticacaoListener implements PhaseListener {

	/*
	 * no AutenticacaoBean eu tenho o usuário logado que pode der usado, porém o
	 * phaseListener não conversam diretamente por anotação, mas consigo
	 * capturar dados do frameWork usando omnifaces
	 */

	/* mostra o evento antes da fase */
	@Override
	public void afterPhase(PhaseEvent event) {

		/*
		 * preciso saber em qual página que eu estou se ela precisa ser
		 * autenticada
		 */
		String paginaAtual = Faces.getViewId();

		/*
		 * fazendo um teste se essa é uma página para autenticação,essa
		 * expressão irá retornar true ou false se tiver autenticacao é uma
		 * página publica senão é privada
		 */

		boolean ehPaginaDeAutenticacao = paginaAtual.contains("autenticacao.xhtml");
		/*
		 * não é a pagina de autenticação verifica se o usuário está autenticado
		 */
		if (!ehPaginaDeAutenticacao) {
			/*
			 * recebe o AutenticacaoBean que está na sessão e onde tem os dados
			 * que eu preciso
			 */
			AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");
			/*
			 * fazendo a primeira restrição, se o autenticacaoBean for nulo é
			 * por que não foi autenticado
			 */
			if (autenticacaoBean == null) {
				// será redirecionado para autenticação
				Faces.navigate("/pages/autenticacao.xhtml");
				return;
			}
			/*
			 * resolvendo outro problema se o bean foi criado se tem usuário
			 * logado se o usuário for nulo
			 */
			Usuario usuario = autenticacaoBean.getUsuarioLogado();
			if (usuario == null) {
				Faces.navigate("/pages/autenticacao.xhtml");
				return;

			}
		}

	}

	/* mostra o evento depois da fase */
	@Override
	public void beforePhase(PhaseEvent event) {
		// System.out.println("Depois da Fase: " + event.getPhaseId());
	}

	/* mostra a qual fase atual que ele vai ouvir */
	@Override
	public PhaseId getPhaseId() {
		// neste caso ele vai ouvir todas as fases
		return PhaseId.RESTORE_VIEW;
	}

}
