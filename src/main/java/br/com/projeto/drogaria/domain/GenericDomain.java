package br.com.projeto.drogaria.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/* classe pojo - tem atributos privados métos set e get
 * e anotações do hibernate e precisa implementar a 
 * interface Serializable*/

/*anotação para dizer que a classe não corresponde a uma tabela
 * mas servirá pra criar tabelas*/
@SuppressWarnings("serial")
@MappedSuperclass
public class GenericDomain implements Serializable {

	@Id // chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY) // gerar id automático
	private Long codigo;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

}
