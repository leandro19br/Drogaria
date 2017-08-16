package br.com.projeto.drogaria.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/*classe que vai ser utilizada para criação das tabelas 
 * do menu dinâmico*/

@Entity
public class Menu {

	@Id // chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY) // gerar id automático
	private Integer codigo;

	/* para armazenas o texto do menu */
	@Column(length = 50, nullable = false)
	private String rotulo;

	@Column(length = 50)
	private String caminho;

	/*
	 * fetch é para carregar todos os submenus no mesmo momente que o menu for
	 * carregado
	 */
	/* tipo do Fetch - serve para a cada subitem montar um select */
	/*
	 * a chave estrangeira não vai ser de outra tabela vai ser da mesma tabela
	 * para isso será utilizado o referencedColumnName
	 */
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(referencedColumnName = "codigo")
	@Fetch(FetchMode.SUBSELECT)
	private List<Menu> itensMenu; // lista utilizada para submenus

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public List<Menu> getItensMenu() {
		return itensMenu;
	}

	public void setItensMenu(List<Menu> itensMenu) {
		this.itensMenu = itensMenu;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getRotulo() {
		return rotulo;
	}

	public void setRotulo(String rotulo) {
		this.rotulo = rotulo;
	}

}
