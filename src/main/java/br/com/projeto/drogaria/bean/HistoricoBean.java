package br.com.projeto.drogaria.bean;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.projeto.drogaria.dao.HistoricoDAO;
import br.com.projeto.drogaria.dao.ProdutoDAO;
import br.com.projeto.drogaria.domain.Historico;
import br.com.projeto.drogaria.domain.Produto;

@SuppressWarnings("serial")
@ManagedBean(name = "historicoBean")
@ViewScoped
public class HistoricoBean implements Serializable {
	/* criando as variáveis */
	private Produto produto;
	/* utilizando essa variável para exibir ou n o painel */
	private Boolean exibePainelDados;
	private Historico historico;

	public Historico getHistorico() {
		return historico;
	}

	public void setHistorico(Historico historico) {
		this.historico = historico;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Boolean getExibePainelDados() {
		return exibePainelDados;
	}

	public void setExibePainelDados(Boolean exibePainelDados) {
		this.exibePainelDados = exibePainelDados;
	}

	/* criando o objeto produto ao carregar a tela */
	@PostConstruct
	public void novo() {
		produto = new Produto();
		historico = new Historico();
		/* quando a tela abrir o painel deve sumir */
		exibePainelDados = false;

	}

	public void buscar() {

		try {
			/* já existe um dao que busca por código */

			ProdutoDAO produtoDAO = new ProdutoDAO();
			/*
			 * neste caso, se o id não existir o produto pode vir null, por isso
			 * terá que ser criado uma variável temporária para recever o
			 * resultado
			 */
			Produto resultado = produtoDAO.buscar(produto.getCodigo());
			/* a variável resultado pode valer nulo ou algo diferente */

			if (resultado == null) {
				exibePainelDados = false;// se o resultado for null n aparece o
											// painel
				Messages.addGlobalWarn("Não existe Produto para o Código Informado");
			} else {
				/* se for diferente de nulo o produto recebe o resultado */
				exibePainelDados = true;// se o resultado for diferente de null
										// aparece o painel
				produto = resultado;
				/* para popular a tela tem que dar um update no xhtml */
			}

		} catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu um erro ao bucar o Produto");
			e.printStackTrace();
		}

	}

	public void salvar() {

		try {

			historico.setHorario(new Date());// setando o horário do server no
												// horário
			/*
			 * gravando a chave estrangeira do produto que vem da busca pelo
			 * código
			 */
			historico.setProduto(produto);
			HistoricoDAO historicoDAO = new HistoricoDAO();
			historicoDAO.salvar(historico);

			Messages.addGlobalInfo("Histórico Salvo com Sucesso !");

		} catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu um erro ao Salvaro Histórico");
			e.printStackTrace();
		}
	}

}
