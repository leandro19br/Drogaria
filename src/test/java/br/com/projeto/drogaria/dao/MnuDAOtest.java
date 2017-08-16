package br.com.projeto.drogaria.dao;

import java.util.List;

import org.junit.Test;

import br.com.projeto.drogaria.domain.Menu;

public class MnuDAOtest {

	@Test
	public void listar() {

		MenuDAO dao = new MenuDAO();
		List<Menu> lista = dao.listar();

		for (Menu menu : lista) {
			if (menu.getCaminho() == null) {
				System.out.println(menu.getCaminho() + "-" + menu.getRotulo());
				for (Menu item : menu.getItensMenu()) {
					System.out.println("\t" + item.getRotulo() + "-" + item.getCaminho());
				}
			}

		}

	}

}
