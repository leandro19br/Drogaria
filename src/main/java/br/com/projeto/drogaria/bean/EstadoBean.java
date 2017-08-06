package br.com.projeto.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.projeto.drogaria.dao.EstadoDAO;
import br.com.projeto.drogaria.domain.Estado;

@SuppressWarnings("serial")
@ManagedBean(name = "estado")
// @RequestScoped
@ViewScoped
// Serializable é para trabalhar com CDI
public class EstadoBean implements Serializable {

	// Ctrl+shift+o importar
	private Estado estado;
	private List<Estado> estados;// armazenar a lista de estados

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	/*
	 * Resolver o erro ao retornar null, o objeto tem que ser criado dando new.
	 * Criando método para criar o objeto antes de salvar e chamar esse método
	 * no xml
	 */

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	// método para retornar a lista de Estados
	@PostConstruct // chamado logo deopois que o EstadoBean for criado
	public void listar() {

		try {
			// preencher listagem estados

			EstadoDAO dao = new EstadoDAO();
			estados = dao.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar LISTAR o Estado");
			// detalhamento do erro na execução verificar no log do server
			erro.printStackTrace();

		}

	}

	public void novo() {
		// criando um novo Estado
		estado = new Estado();
	}

	// método faz parte de uma ação
	public void salvar() {
		/*
		 * exibir mensagem sem o omniFaces
		 */
		/*
		 * String texto = "Programação Java";
		 * 
		 * FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_FATAL,
		 * texto, texto);
		 * 
		 * FacesContext contexto = FacesContext.getCurrentInstance();
		 * contexto.addMessage(null, mensagem);
		 */

		/*
		 * Ctrl + Shift + O para limpar as importações que não estão sendo
		 * utilizadas
		 */
		try {
			EstadoDAO dao = new EstadoDAO();
			/* utilizando o merge tanto para novo como update. */
			dao.merge(estado);

			// criando um novo objeto sem "limpo" depois
			// atualizar a tela ou estaciar direto no método
			novo();
			/* para atualizar a lista depois da inclusão */
			estados = dao.listar();

			Messages.addGlobalInfo("Estado salvo com Sucesso !");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar SALVAR o Estado");
			// detalhamento do erro na execução verificar no log do server
			erro.printStackTrace();
		}

	}

	// ActionEvente serve para pegar parêmtremos enviados pela visão
	public void excluir(ActionEvent evento) {
		/*
		 * pega o componente que disparou o evento,pega todos os atributos
		 * depois pega o atriburto que vc quer recuparar
		 * 
		 */
		try {
			estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");

			EstadoDAO dao = new EstadoDAO();
			dao.excluir(estado);
			// recarregar os estados após ser excluido
			estados = dao.listar();

			Messages.addGlobalInfo("Estado Excluido com Sucesso ! ");

		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro ao Exluir estado ");
			error.printStackTrace();
		}

	}

	public void editar(ActionEvent evento) {
		/*
		 * pega o componente que disparou o evento,pega todos os atributos
		 * depois pega o atriburto que vc quer recuparar
		 * 
		 */
		try {
			estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");

		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro ao Editar estado ");
			error.printStackTrace();
		}

	}
}
