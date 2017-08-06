package br.com.projeto.drogaria.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity // entidade de persistência
public class Historico {

	@Id // chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY) // gerar id automático
	private Integer codigo;

	@Column(nullable = false) // campo obrigatório
	@Temporal(TemporalType.TIMESTAMP)
	private Date horario;

	@Column(nullable = false, length = 500)
	private String observacoes;

	@ManyToOne
	@JoinColumn(nullable = false) // produto é obrigatório
	private Produto produto;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
