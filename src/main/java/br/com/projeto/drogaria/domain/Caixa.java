package br.com.projeto.drogaria.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Caixa {

	@Id // chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY) // gerar id automático
	private Integer codigo;

	@Column(nullable = false, unique = true) // campo obrigatório e o campo deve
												// ser unico
	@Temporal(TemporalType.DATE)
	private Date dataAbertura;

	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dataFechamento;

	@Column(nullable = false, precision = 7, scale = 2)
	private BigDecimal valorAbertura;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public Date getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public BigDecimal getValor() {
		return valorAbertura;
	}

	public void setValor(BigDecimal valor) {
		this.valorAbertura = valor;
	}

}
