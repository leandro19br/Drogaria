package br.com.projeto.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.projeto.drogaria.domain.Estado;

public class EstadoDAOTeste {

	// criar um método para testar o salvar
	@Test
	@Ignore
	public void salvar() {
		Estado estado = new Estado();
		estado.setNome("Rio de Janeiro");
		estado.setSigla("RJ");

		EstadoDAO dao = new EstadoDAO();
		dao.salvar(estado);
	}

	@Test
	@Ignore
	public void listar() {

		EstadoDAO dao = new EstadoDAO();
		List<Estado> resultado = dao.listar();
		// mostrar resultado
		System.out.println("total de registros " + resultado.size());

		// mostrar resultado com for
		for (Estado estado : resultado) {

			System.out.println("Nome: " + estado.getNome() + " Sigla: " + estado.getSigla());
		}
	}

	@Test
	@Ignore
	public void buscar() {

		Integer codigo = 5;
		EstadoDAO dao = new EstadoDAO();
		// estado recebe o resultado de buscar por código
		Estado estado = dao.buscar(codigo);

		if (estado == null) {
			System.out.println("Registro não encontrado !");
		} else {
			System.out.println("Nome: " + estado.getNome() + " Sigla: " + estado.getSigla());
		}

	}

	@Test
	@Ignore
	public void excluir() {
		Integer codigo = 1;
		EstadoDAO dao = new EstadoDAO();
		/* na pesquisa trouxe o estado que será deletado */
		Estado estado = dao.buscar(codigo);
		if (estado != null) {

			/*
			 * se estado for diferente de null irá chamar o dao.excluir passando
			 * a variável estado onde está o estado que deverá ser excluir
			 */
			dao.excluir(estado);
			System.out.println("Registro Deletado com sucesso !");
		} else {
			System.out.println("Registro não encontrado !");
		}

	}

	@Test
	public void editar() {
		Integer codigo = 3;
		/* na pesquisa trouxe o estado que será alterado */
		EstadoDAO dao = new EstadoDAO();
		Estado estado = dao.buscar(codigo);

		if (estado != null) {
			System.out.println("entrou");
			/* depois da pesquisa voce pode dar set e o que voce quer editar */
			estado.setSigla("RJ");
			estado.setNome("Rio de Janeiro");
			dao.editar(estado);

			System.out.println("Registro Editado com sucesso !");
		} else {
			System.out.println("Registro não encontrado !");
		}

	}

}
