package br.com.projeto.drogaria.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.projeto.drogaria.domain.ItemVenda;
import br.com.projeto.drogaria.domain.Produto;
import br.com.projeto.drogaria.domain.Venda;
import br.com.projeto.drogaria.util.HibernateUtil;

public class VendaDAO extends GenericDAO<Venda> {

	/*
	 * Utilizando o merge para salvar e recebe 2 paramentros, vai salvar a venda
	 * e percorrer os itens da venda
	 */
	public void salvar(Venda venda, List<ItemVenda> itensVenda) {

		// capturar uma sessão aberta
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		// declar objeto para transação
		Transaction transacao = null;

		try {
			// começando uma trançao que recebe uma sessão
			transacao = sessao.beginTransaction();

			// salvando a venda passando a venda, mas ainda não tem chave
			// primária
			sessao.save(venda);
			/* depois do save o hibernate já grava o id no objeto salvo */
			/* fazendo um for para varrer os itens venda */
			for (int posicao = 0; posicao < itensVenda.size(); posicao++) {
				/* capturar o item da lista da linha corrente */
				ItemVenda itemVenda = itensVenda.get(posicao);
				/* preciso setar a venda que vem do salvamento de cima */
				itemVenda.setVenda(venda);
				/* agora eu mando salvar o item venda */
				sessao.save(itemVenda);

				/* fazendo a lógica para baixa em estoque */

				/*
				 * pegando o item que está em itemVenda e guardando na variávem
				 * produto
				 */
				Produto produto = itemVenda.getProduto();
				/*
				 * fazendo a subtração da quantidade do produto - o itemVenda
				 * que foi vendido e setando do produto o novo valor terá que
				 * fazer uma conversão por que o valor esperado é short e o
				 * cáculo retorna um int
				 */
				/* fazendo a validação para quantidade em estoque */
				int quantidade = produto.getQuantidade() - itemVenda.getQuantidade();// o
																						// valor
																						// esperado
																						// tem
																						// que
																						// ser
																						// >=0
				if (quantidade >= 0) {
					produto.setQuantidade(new Short(quantidade + ""));
					/* atualizando o produto fazendo um update */
					sessao.update(produto);
				} else {
					/*
					 * caso a quantidade seja <0 será jogada uma exeção para o
					 * Catch, onde será feito o Rolback que manda uma mensagem
					 * ao Bean do ero na tela
					 */
					throw new RuntimeException("Quantidade insuficinente em Estoque");
				}

			}

			transacao.commit();

		} catch (RuntimeException ex) {
			// se a transação for diferente de null ou seja está aberta
			if (transacao != null) {
				// desfaz a transação
				transacao.rollback();

			}
			// passa a mensagem de erro
			throw ex;
		} finally {
			// se der certo ou errado executa por isso deverá fechara sessão
			sessao.close();
		}
	}

}
