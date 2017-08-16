package br.com.projeto.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.projeto.drogaria.dao.FabricanteDAO;
import br.com.projeto.drogaria.dao.ProdutoDAO;
import br.com.projeto.drogaria.domain.Fabricante;
import br.com.projeto.drogaria.domain.Produto;

@SuppressWarnings("serial")
@ManagedBean(name = "produtoBean2")
@ViewScoped
public class ProdutoBean2 implements Serializable {

	private Produto produto;
	private List<Fabricante> fabricantes;
	private List<Produto> produtos;
	private ProdutoDAO produtoDAO;// atributo para estanciar o objeto
	private FabricanteDAO fabricanteDAO;
	/* esse código vem do View Param para edição do produto */
	private Integer codigoProduto;

	public Integer getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(Integer codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	/*
	 * vai estanciar o DAO toda vez que a página for renderizada
	 */

	@PostConstruct
	public void inicio() {

		/* estanciando os objetos */
		produtoDAO = new ProdutoDAO();
		fabricanteDAO = new FabricanteDAO();

	}

	public void listar() {

		try {

			produtos = produtoDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao Listar os Produtos");
			erro.printStackTrace();
		}
	}

	/* método paa carregar a tela e os dados do fabricante */
	public void carregarEdição() {

		try {
			/*
			 * pesquisando o produto pelo código que veio da tela de produto
			 * Listar
			 */
			produto = produtoDAO.buscar(codigoProduto);

			/* carregando a lista de fabricantes e chamar no f:metadata */
			fabricantes = fabricanteDAO.listar("descricao");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao carregar os dados para Edição");
			erro.printStackTrace();
		}

	}

}
