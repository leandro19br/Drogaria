package br.com.projeto.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import br.com.projeto.drogaria.dao.MenuDAO;
import br.com.projeto.drogaria.domain.Menu;

@SuppressWarnings("serial")
@ManagedBean(name = "menuBean")
@SessionScoped
public class MenuBean implements Serializable {
	/* atributo utilizado para menu Dinâmico */
	private MenuModel menuModel;

	public MenuModel getMenuModel() {
		return menuModel;
	}

	public void setMenuModel(MenuModel menuModel) {
		this.menuModel = menuModel;
	}

	@PostConstruct
	public void iniciar() {

		MenuDAO dao = new MenuDAO();
		List<Menu> lista = dao.listar();

		/* estanciando e criando o Menu */
		menuModel = new DefaultMenuModel();

		for (Menu menu : lista) {
			if (menu.getCaminho() == null) {
				/* criando o submenu */
				DefaultSubMenu submenu = new DefaultSubMenu(menu.getRotulo());

				for (Menu item : menu.getItensMenu()) {
					/* criando os itens do submenu */
					DefaultMenuItem menuItem = new DefaultMenuItem(item.getRotulo());
					menuItem.setUrl(item.getCaminho());
					/* dentro do submenu foi incluido item dele */
					submenu.addElement(menuItem);
				}
				/* adicionando o submenu ao menuModel */
				menuModel.addElement(submenu);
			}

		}

	}

}
