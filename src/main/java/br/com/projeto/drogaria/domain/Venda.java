package br.com.projeto.drogaria.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Venda {

	@Id // chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY) // gerar id automático
	private Integer codigo;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date horario;

	@Column(nullable = false, precision = 7, scale = 2)
	private BigDecimal valorTotal;

	/*
	 * um cliente pode ter varias vendas, mas a venda pode ter só um cliente
	 */
	@ManyToOne
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Funcionario funcionario;

	/* uma venda pode ter vários itens */
	/* carregar os filhos com base no pai na tela de vendaListagem */
	/*
	 * para poder carregar os produtos fetch = FetchType.EAGER por padrão é modo
	 * lazy(lento)
	 */
	/*
	 * @OneToMany precisa saber com que chave estrangeira ele se liga na tabela
	 * de venda para isso é preciso colocar o mappedBy = "venda"
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "venda")
	private List<ItemVenda> intensVenda;

	public List<ItemVenda> getIntensVenda() {
		return intensVenda;
	}

	public void setIntensVenda(List<ItemVenda> intensVenda) {
		this.intensVenda = intensVenda;
	}

	public Date getDataVenda() {
		return horario;
	}

	public void setDataVenda(Date dataVenda) {
		this.horario = dataVenda;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

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
		Venda other = (Venda) obj;
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
