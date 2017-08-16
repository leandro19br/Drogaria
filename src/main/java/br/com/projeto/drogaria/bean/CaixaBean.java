package br.com.projeto.drogaria.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

import br.com.projeto.drogaria.dao.CaixaDAO;
import br.com.projeto.drogaria.dao.FuncionarioDAO;
import br.com.projeto.drogaria.domain.Caixa;
import br.com.projeto.drogaria.domain.Funcionario;

@SuppressWarnings("serial")
@ManagedBean(name = "caixaBean")
@ViewScoped
public class CaixaBean implements Serializable {
	/* modelo do agendador do primefaces */
	private ScheduleModel caixas;
	private Caixa caixa;
	private List<Funcionario> funcionarios;

	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}

	public ScheduleModel getCaixas() {
		return caixas;
	}

	public void setCaixas(ScheduleModel caixas) {
		this.caixas = caixas;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	/* método para mostar os caixas abertos */
	@PostConstruct // vai instaviar no momento que a tela for criada
	public void listar() {
		/* criando o objeto para o modelo de Schedule */
		caixas = new DefaultScheduleModel();
	}

	/* utilizar um parametro Selectvent para pegar a data(evento) */
	public void novo(SelectEvent evento) {

		/* estanciando o objeto caixa */
		caixa = new Caixa();
		/* fazendo o cast pois o retorna a data em objeto e precisa ser Date */
		caixa.setDataAbertura((Date) evento.getObject());

		/* populando a lista de funcionarios */
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		funcionarios = funcionarioDAO.listar();

	}

	public void salvar() {
		/*
		 * Para acertar o problema da data com 1 dia de atraso utilizando
		 * Calendar, parecido com Date Calendar vc não dá new vc instancia
		 */

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(caixa.getDataAbertura());
		calendar.add(Calendar.DATE, 1);
		caixa.setDataAbertura(calendar.getTime());

		/* salvando o caixa */
		CaixaDAO caixaDAO = new CaixaDAO();
		caixaDAO.salvar(caixa);

		Messages.addGlobalInfo("Caixa aberto com sucesso");

	}

}
