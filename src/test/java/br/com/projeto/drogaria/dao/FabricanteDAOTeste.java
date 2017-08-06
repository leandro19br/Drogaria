package br.com.projeto.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.projeto.drogaria.domain.Fabricante;

public class FabricanteDAOTeste {

	// criar um método para testar o salvar
	@Test
	@Ignore
	public void salvar() {
		Fabricante fabricante = new Fabricante();
		fabricante.setDescricao("Bahier");

		Fabricante fabricante1 = new Fabricante();
		fabricante1.setDescricao("Astrazeneca");

		FabricanteDAO dao = new FabricanteDAO();
		dao.salvar(fabricante);
		dao.salvar(fabricante1);
	}

	@Test
	@Ignore
	public void listar() {

		FabricanteDAO dao = new FabricanteDAO();
		List<Fabricante> resultado = dao.listar();
		// mostrar resultado
		System.out.println("total de registros " + resultado.size());

		// mostrar resultado com for
		for (Fabricante fabricante : resultado) {

			System.out.println(fabricante.getDescricao());
		}
	}

	@Test
	@Ignore
	public void buscar() {

		Integer codigo = 7;
		FabricanteDAO dao = new FabricanteDAO();
		// estado recebe o resultado de buscar por código
		Fabricante fabricante = dao.buscar(codigo);

		if (fabricante == null) {
			System.out.println("Registro não encontrado !");
		} else {
			System.out.println(fabricante.getDescricao());
		}

	}

	@Test
	@Ignore
	public void excluir() {
		Integer codigo = 1;
		FabricanteDAO dao = new FabricanteDAO();
		/* na pesquisa trouxe o estado que será deletado */
		Fabricante fabricante = dao.buscar(codigo);
		if (fabricante != null) {

			/*
			 * se estado for diferente de null irá chamar o dao.excluir passando
			 * a variável estado onde está o estado que deverá ser excluir
			 */
			dao.excluir(fabricante);
			System.out.println("Registro Deletado com sucesso !");
		} else {
			System.out.println("Registro não encontrado !");
		}

	}

	@Test
	@Ignore
	public void editar() {
		Integer codigo = 3;
		/* na pesquisa trouxe o estado que será alterado */
		FabricanteDAO dao = new FabricanteDAO();
		Fabricante fabricante = dao.buscar(codigo);

		if (fabricante != null) {

			/* depois da pesquisa voce pode dar set e o que voce quer editar */
			fabricante.setDescricao("Bahier");
			dao.editar(fabricante);

			System.out.println("Registro Editado com sucesso !");
		} else {
			System.out.println("Registro não encontrado !");
		}

	}

	@Test
	// @Ignore
	public void merge() {
		/*
		 * teste de inserção Fabricante fabricante = new Fabricante();
		 * fabricante.setDescricao("Merge");
		 * 
		 * FabricanteDAO dao = new FabricanteDAO(); dao.merge(fabricante);
		 */
		FabricanteDAO dao = new FabricanteDAO();
		Fabricante fabricante = dao.buscar(8);
		fabricante.setDescricao("Teste Merge");
		dao.merge(fabricante);

	}
}
