package br.com.projeto.drogaria.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.projeto.drogaria.dao.UsuarioDAO;
import br.com.projeto.drogaria.domain.Pessoa;
import br.com.projeto.drogaria.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean(name = "autenticacaoBean")
@SessionScoped // escopo de sessão é criado no momento em que eu logo e
				// destruido
				// quando fecho a sessão (tomar cuidado com o import !)
public class AutenticacaoBean implements Serializable {

	private Usuario usuario;
	/*
	 * será um atributo de Clase para que possa pegar os dados do usuario logado
	 */
	private Usuario usuarioLogado;

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/* estanciar o objeto para que não ocorra erro de Nullpointer */
	@PostConstruct
	public void inicio() {
		usuario = new Usuario();
		usuario.setPessoa(new Pessoa());// estanciando Pessoa para acessar o CPF
	}

	/* metodo para autenticar o usuário e ser chamado no botão */
	public void autenticar() {

		try {

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			/* chamando o método autenticar e pegando o CPf e Senha */
			usuarioLogado = usuarioDAO.autenticar(usuario.getPessoa().getCpf(), usuario.getSenha());

			/*
			 * o retorno do método pode ser null ou o usuário, portanto vou
			 * guardar esse retorno em uma variável usuarioLogado e verificar
			 * com if
			 */
			if (usuarioLogado == null) {
				Messages.addGlobalError("CPF e/ou Senha Inválido");
				return;// para não passar para o redirect
			}
			/*
			 * Se o retorno não for Null será redirecionado para a tela
			 * principal utilizando OmniFaces
			 */
			Faces.redirect("./pages/principal.xhtml");

		} catch (IOException e) {
			Messages.addGlobalError(e.getMessage());// mostra para o usuário
			e.printStackTrace();// mostra no log
		}

	}
	/*
	 * public void deslogar() {
	 * 
	 * HttpSession autentica = Faces.getSession(); autentica.invalidate();
	 * Faces.navigate("/pages/autentica.xhtml");
	 * 
	 * }
	 * 
	 * /* Método recebe um conjunto de permissões e verifica se o usuario logado
	 * pode ver esse método será chamado no componente que voce quer dar
	 * permissão por exemplo no model.xhtml
	 */

	public boolean temPermissao(List<String> permissoes) {

		/*
		 * fazendo uma varredura em todas as permissões que no caso são 3 A,G,B
		 */
		for (String permissao : permissoes) {
			/* transforma um texto em chararcter posição 0 */
			if (usuarioLogado.getTipo() == permissao.charAt(0)) {
				return true;
			}
		}
		return false;

	}

}
