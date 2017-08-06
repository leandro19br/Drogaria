package br.com.projeto.drogaria.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class Usuario {

	@Id // chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY) // gerar id automático
	private Integer codigo;

	/* senha criptografada, ficará no banco */
	@Column(length = 32, nullable = false)
	private String senha;

	/* senha sem criptografada, ficará não precisa guardar no banco */
	@Transient
	private String senhaSemCriptografia;

	@Column(nullable = false)
	private Character tipo;// gerente, atendente

	@Column(nullable = false)
	private Boolean ativo;

	@OneToOne
	@JoinColumn(nullable = false)
	private Pessoa pessoa;

	public String getSenhaSemCriptografia() {
		return senhaSemCriptografia;
	}

	public void setSenhaSemCriptografia(String senhaSemCriptografia) {
		this.senhaSemCriptografia = senhaSemCriptografia;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Character getTipo() {
		return tipo;
	}

	/*
	 * Criando um get diferente para "converter" o tipo CHAR para String e
	 * mostrar para o usuário o que é o A - Administrador, B - Balconista etc
	 * que será usado no usuario.xhtml
	 * 
	 * 
	 * anotação que serve para dizer que não é um campo fisico serve só para
	 * formatação
	 * 
	 */
	@Transient
	public String getTipoFormatado() {
		String tipoFormatado = null;
		if (tipo == 'A') {
			tipoFormatado = "Administrador";
		} else if (tipo == 'B') {
			tipoFormatado = "Balconista";
		} else if (tipo == 'G') {
			tipoFormatado = "Gerente";
		}
		return tipoFormatado;
	}

	public void setTipo(Character tipo) {
		this.tipo = tipo;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	/*
	 * Formatando o ativo para "trazir" para sim e não
	 */
	@Transient
	public String getAtivoFormatado() {
		String ativoFormatado = null;
		if (ativo) {
			ativoFormatado = "sim";
		} else {
			ativoFormatado = "Não";
		}
		return ativoFormatado;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	// implementando o toString para converter o SelectItems
	@Override
	public String toString() {
		return String.format("%s[codigo=%d]", getClass().getSimpleName(), getCodigo());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	/*
	 * Utilizar iquals e HashCode utilizado para diferenciar objeto para que
	 * funcione a seleção do estado no editar
	 */

}
