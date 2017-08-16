package br.com.projeto.drogaria.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.projeto.drogaria.dao.ClienteDAO;
import br.com.projeto.drogaria.dao.FuncionarioDAO;
import br.com.projeto.drogaria.dao.ProdutoDAO;
import br.com.projeto.drogaria.dao.VendaDAO;
import br.com.projeto.drogaria.domain.Cliente;
import br.com.projeto.drogaria.domain.Funcionario;
import br.com.projeto.drogaria.domain.ItemVenda;
import br.com.projeto.drogaria.domain.Produto;
import br.com.projeto.drogaria.domain.Venda;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class VendaBeam implements Serializable {

	private List<Produto> produtos;
	private Produto produto;
	private List<Cliente> clientes;
	private List<Funcionario> funcionarios;
	private List<ItemVenda> itensVenda;
	private List<Venda> vendas;
	private Venda venda;

	public List<Venda> getVendas() {
		return vendas;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public List<ItemVenda> getItensVenda() {
		return itensVenda;
	}

	public void setItensVenda(List<ItemVenda> itensVenda) {
		this.itensVenda = itensVenda;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	// @PostConstruct
	public void novo() {

		try {
			// criando um novo objeto Venda
			venda = new Venda();
			venda.setValorTotal(new BigDecimal("0.00"));// preço começando com
														// valor 0

			ProdutoDAO dao = new ProdutoDAO();
			produtos = dao.listar("descricao");

			// criando listagem para o carrinho de compras vazio
			itensVenda = new ArrayList<>();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao Listar os Produtos");
			erro.printStackTrace();
		}
	}

	public void listar() {

		try {

			/* criando o VendaDAO */

			VendaDAO vendaDAO = new VendaDAO();
			vendas = vendaDAO.listar("horario");// listar venda

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao Listar as Vendas");
			erro.printStackTrace();
		}

	}

	public void adicionar(ActionEvent evento) {

		// pegar o produto selecionada recebe um actionEvent
		// com base do botão adicionar com o atributo passado
		Produto produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
		/*
		 * verificando se um item já foi adicionada, para isso foi criado a
		 * variável achou se achou o valor será a posição se não achou será -1
		 */

		int achou = -1;
		/* for para percorrer a List */
		for (int posicao = 0; posicao < itensVenda.size(); posicao++) {
			/*
			 * a lista é uma lista de itens portando precisa capturar o item na
			 * posição corrente
			 */
			/*
			 * essa linha itensVenda.get(posicao).getProduto() pegou o produto
			 * da linha corrente e vai verificar se é igual ao produto que eu
			 * estou procurando e marca o achou com a posição
			 */
			if (itensVenda.get(posicao).getProduto().equals(produto)) {
				achou = posicao;
			}
		}

		/* se não achou a posição portanto é um item novo */
		if (achou < 0) {
			/*
			 * pegar o produto e converter em itemVenda pois um é da tabela
			 * produto e o outro da tabela itemProduto
			 */
			ItemVenda itemVenda = new ItemVenda(); // estanciandoitem venda

			itemVenda.setValorParcial(produto.getPreco());// preço do produto
			itemVenda.setProduto(produto);// vem do produto
			itemVenda.setQuantidade(new Short("1"));// sempre que for para o
													// carrinho por padrão é 1
			// pegar esse itemVenda e colocar dentro da lista de itensVenda
			/* nesse momento ainda não será gravada no banco */
			itensVenda.add(itemVenda);

		} else {
			/*
			 * se ele achou vai ter que editar pega o do arrey list na posição
			 * achou e guarda em itemVenda Não preciso adicionar no ArrayList
			 * pois ao fazer itensVenda.get(achou); já estarei manipulando o
			 * Array
			 */
			ItemVenda itemVenda = itensVenda.get(achou);
			/*
			 * vai ter que converter a quantidade primeiro em String "" e depois
			 * em Short pois em java na somatória o + 1 é transformada em int
			 */
			itemVenda.setQuantidade(new Short(itemVenda.getQuantidade() + 1 + ""));
			itemVenda.getProduto().getPreco();
			/*
			 * bigdecinal não aceita as operações com sinal, utilizam métodos
			 * para isso, portanto essa conversão para Bigdecimal depois depois
			 * do método multiplay para multiplicar
			 */
			itemVenda.setValorParcial(produto.getPreco().multiply(new BigDecimal(itemVenda.getQuantidade())));

		}
		// chamando o método para calucular o valor
		calcular();

	}

	/* atualização do preço de venda do item quando a quantidade for editado */
	public void atuaslizarPrecoParcial() {
		/* fazendo um for para calcular item a item todos os preco de venda */
		for (ItemVenda itemVenda : itensVenda) {
			itemVenda.setValorParcial(
					itemVenda.getProduto().getPreco().multiply(new BigDecimal(itemVenda.getQuantidade())));
			/* chamando o método calcular() para reclacular o preço total */
		}
		this.calcular();
	}

	public void remover(ActionEvent evento) {

		/*
		 * pegar o produto selecionada recebe um actionEvent com base do botão
		 * adicionar com o atributo passado e vou guardar essa itemVenda
		 * selecionado
		 */
		ItemVenda itemVenda = (ItemVenda) evento.getComponent().getAttributes().get("itemSelecionado");

		// quer dizer que não encontrou
		int achou = -1;

		/* for para percorrer a List */
		for (int posicao = 0; posicao < itensVenda.size(); posicao++) {

			/*
			 * Faz uma verificação se o item na posição corrente é igual ao item
			 * que foi clicado para achara posição, se forem iguais achou recebe
			 * a posição
			 */
			if (itensVenda.get(posicao).getProduto().equals(itemVenda.getProduto())) {
				achou = posicao;

			}
		}

		// se o achou for >-1, quer dizer que o item foi encontrado

		if (achou > -1) {

			// vai pegar a lista itensVenda e remover o achou que é a posição
			// encontrada
			itensVenda.remove(achou);
		}
		// calcularo valor após ser removido
		calcular();

	}

	// método para calcular o total da compra

	public void calcular() {
		// primeiro zerar o valor Total
		venda.setValorTotal(new BigDecimal("0.00"));
		// fazendo um for para percorrer os itens da venda
		for (int posicao = 0; posicao < itensVenda.size(); posicao++) {
			// capturar o item da venda corrente
			ItemVenda itemVenda = itensVenda.get(posicao);
			// somar a venda pegando o preço total antiga mais o valor parcial e
			// seta no valor Total
			venda.setValorTotal(venda.getValorTotal().add(itemVenda.getValorParcial()));

		}

	}

	// método vai fazer a finalização da compra e vai inicializar cliente e
	// funcionário
	public void finalizar() {

		try {

			/*
			 * pegar hora e data do sistema foi colocado no método finalizar
			 * pois ao finalizar a compra setará o horário
			 */
			venda.setHorario(new Date());
			// setando cliente e funcionário como null ao clicar em finalizar
			venda.setCliente(null);
			venda.setFuncionario(null);

			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listarOrdenado();

			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.listarOrdenado();

		} catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu um erro ao Finalizar Venda");
			e.printStackTrace();
		}

	}

	public void salvar() {

		try {

			/*
			 * validar se o valor total é zero pode utilizar o signum que
			 * converte em inteiro por ser um bigdecimal
			 */

			if (venda.getValorTotal().signum() == 0) {
				Messages.addGlobalInfo("Seu carrinho de compras está VAZIO, infome pelo menos um produto");
				return;
			}

			VendaDAO vendaDAO = new VendaDAO();
			/* vou passar para o método Salvar a venda e o item da venda */
			vendaDAO.salvar(venda, itensVenda);

			// chamar o método novo que limpa os intens e zera a venda
			novo();
			System.out.println(venda.getValorTotal());
			Messages.addGlobalInfo("Venda realizada com sucesso");

		} catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu um erro ao Salvar Venda");
			e.printStackTrace();
		}

	}

}
