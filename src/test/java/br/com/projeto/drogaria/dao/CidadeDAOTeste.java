package br.com.projeto.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.projeto.drogaria.domain.Cidade;
import br.com.projeto.drogaria.domain.Estado;

public class CidadeDAOTeste {

	@Test
	@Ignore
	public void salvar() {

		/*
		 * por causa da chave estrangeira precisa fazer uma pesquisa do estado
		 */
		EstadoDAO estadodao = new EstadoDAO();
		Estado estado = estadodao.buscar(6);
		if (estado == null) {
			System.out.println("Registro não encontrado");
		} else {
			Cidade cidade = new Cidade();
			cidade.setNome("Campinas");
			cidade.setEstado(estado);

			CidadeDAO cidadeDAO = new CidadeDAO();
			cidadeDAO.salvar(cidade);
			System.out.println("Registro Cadastrado com Sucesso !");

		}

	}

	@Test
	@Ignore
	public void listar() {

		/* criar a cidadeDAO */
		CidadeDAO cidadeDAO = new CidadeDAO();
		/* colocar o resultado da pesquisa em uma lista */
		List<Cidade> lista = cidadeDAO.listar();

		for (Cidade cidade : lista) {

			System.out.println("Cidade:" + cidade.getNome());
			System.out.println("Estado:" + cidade.getEstado().getNome());
		}

	}

	@Test
	@Ignore
	public void buscar() {

		Integer codigo = 1;

		/* criar a cidadeDAO */
		CidadeDAO cidadeDAO = new CidadeDAO();
		/* colocar o resultado da pesquisa em uma lista */
		Cidade cidade = cidadeDAO.buscar(codigo);

		if (cidade == null) {
			System.out.println("Registro não Encontrado !");
		} else {

			System.out.println("Cidade :" + cidade.getNome());
			System.out.println("Estado :" + cidade.getEstado().getNome());
			System.out.println("Sigla :" + cidade.getEstado().getSigla());
		}

	}

	@Test
	@Ignore
	public void excluir() {

		Integer codigo = 10;
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigo);

		if (cidade == null) {
			System.out.println("Registro não encontrado");
		} else {

			cidadeDAO.excluir(cidade);
			System.out.println("Registro Removida com Sucesso !");
			System.out.println("Cidade :" + cidade.getNome());
			System.out.println("Estado :" + cidade.getEstado().getNome());
			System.out.println("Sigla :" + cidade.getEstado().getSigla());

		}

	}

	@Test
	// @Ignore
	public void editar() {

		Integer cidadeCodigo = 3;
		Integer estadoCodigo = 3;

		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(cidadeCodigo);

		if (cidade == null) {
			System.out.println("Registro Cidade não encontrado");
		} else {

			EstadoDAO estadoDAO = new EstadoDAO();
			Estado estado = estadoDAO.buscar(estadoCodigo);

			if (estado == null) {
				System.out.println("Registro ESTADO não encontrado");
			} else {
				cidade.setNome("Buzios");
				cidade.setEstado(estado);

				cidadeDAO.editar(cidade);
				System.out.println("Registro Editado com Sucesso !");
			}

		}

	}

	@Test
	// @Ignore
	public void buscarPorEstado() {

		Integer estadoCodigo = 1;

		/* criar a cidadeDAO */
		CidadeDAO cidadeDAO = new CidadeDAO();
		/* colocar o resultado da pesquisa em uma lista */
		List<Cidade> lista = cidadeDAO.bucarPorEstado(estadoCodigo);

		for (Cidade cidade : lista) {

			System.out.println("Cidade:" + cidade.getNome());
			System.out.println("");
			System.out.println("Estado:" + cidade.getEstado().getNome());
		}

	}

}
