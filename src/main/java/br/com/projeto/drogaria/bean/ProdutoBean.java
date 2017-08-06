package br.com.projeto.drogaria.bean;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.projeto.drogaria.dao.FabricanteDAO;
import br.com.projeto.drogaria.dao.ProdutoDAO;
import br.com.projeto.drogaria.domain.Fabricante;
import br.com.projeto.drogaria.domain.Produto;
import br.com.projeto.drogaria.util.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

@SuppressWarnings("serial")
@ManagedBean(name = "produto")
@ViewScoped
public class ProdutoBean implements Serializable {

	private List<Produto> produtos;
	private Produto produto;
	private List<Fabricante> fabricantes;

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

	@PostConstruct
	public void listar() {

		try {
			ProdutoDAO dao = new ProdutoDAO();
			produtos = dao.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao Listar os Produtos");
			erro.printStackTrace();
		}
	}

	public void novo() {
		produto = new Produto();

		try {
			FabricanteDAO dao = new FabricanteDAO();
			fabricantes = dao.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao Listar um novo Fabricante");
			erro.printStackTrace();
		}

	}

	public void salvar() {

		try {

			// Validação se a foto foi feito upload através do caminho
			if (produto.getCaminho() == null) {
				Messages.addGlobalError("Campo Imagem é Obrigatório !");
				// dando o return ele não faz o restante da ação após o if
				return;
			}

			// Primeiro Estanciar o objeto DAO
			ProdutoDAO produtoDAO = new ProdutoDAO();

			// Segundo, chamar o merge
			// produto retorno serve para pegar o id do produto gravado para ser
			// utilizado no nome do
			// arquivo que será gravado da foto
			Produto produtoRetorno = produtoDAO.merge(produto);

			// path(caminho) de origem do arquivo
			Path origem = Paths.get(produto.getCaminho());
			// path(caminho) de destino do caminho onde as fotos ficarão
			// gravadas
			Path destino = Paths.get("C:/Users/leand/OneDrive/Documentos/Programação WEB com java/Uploads/"
					+ produtoRetorno.getCodigo() + ".png");
			// fazer a copia do arquivo para o destino com as Paths que foram
			// criadas
			// irá dar erro de Try/Cath porém como já tem um podemos colocar um
			// | e colocar a outra excessão
			Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);

			// terceiro limpar os dados de cidade, criando um novo objeto
			produto = new Produto();

			// recarreghe o combo de estados caso algum estado tenha sido salvo
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.listar();

			// quarto, atualizar a tabela depois de o novo ser inserido
			produtos = produtoDAO.listar();

			Messages.addGlobalInfo("Cadastro Realizado Com Sucesso !");

		} catch (RuntimeException | IOException erro) {
			Messages.addGlobalError("Ocorreu um erro ao Salvar o Produto");
			erro.printStackTrace();
		}

	}

	public void excluir(ActionEvent evento) {

		try {

			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

			ProdutoDAO dao = new ProdutoDAO();
			dao.excluir(produto);

			// deleter a foto do arquivo
			Path path = Paths.get("C:/Users/leand/OneDrive/Documentos/Programação WEB com java/Uploads/"
					+ produto.getCodigo() + ".png");
			Files.deleteIfExists(path);

			produtos = dao.listar();

			Messages.addGlobalInfo("Produto Removida Com Sucesso !");

		} catch (RuntimeException | IOException erro) {
			Messages.addGlobalError("Ocorreu um erro ao Excluir o Produto");
			erro.printStackTrace();
		}

	}

	public void editar(ActionEvent evento) {

		try {
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
			produto.setCaminho("C:/Users/leand/OneDrive/Documentos/Programação WEB com java/Uploads/"
					+ produto.getCodigo() + ".png");
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu ao tentar selecionar um Fabricante");
			erro.printStackTrace();
		}

	}

	/*
	 * método para fazer o carregamento das fotos que recebe um parâmetro
	 * FileUploadEvent e chamar esse método na TAG fileUpload ao finalizar o
	 * método o arquivo é perdido pois fica em arquivos temporários do Tomcat
	 * para contornar podemos gravar no arquivo temp do Windows
	 */

	public void upload(FileUploadEvent evento) {

		String nome = evento.getFile().getFileName();
		String tipo = evento.getFile().getContentType();
		long tamanho = evento.getFile().getSize();

		try {
			// arquivo do upload para armazenar, mas ele fecha rapidamente
			UploadedFile arquivoUpload = evento.getFile();
			// criando um arquivo temp dentro do SO nova biblioiteca de
			// manipulação
			// de arquivos
			// primeiro parâmetro é o nome do arquivo temporário, segundo é
			// estenção
			// PATH é o caminho do arquivo onde será guardado
			Path arquivoTemp = Files.createTempFile(null, null);
			// preciso copiar o que esta em arquivoUpload para arquivoTemp
			// REPLACE_EXISTING faz a cópia por cima do outro arquivo
			Files.copy(arquivoUpload.getInputstream(), arquivoTemp, StandardCopyOption.REPLACE_EXISTING);
			// utilizado para guardar o ccaminho temporário o atributo caminho
			// foi criado na classe Produto
			produto.setCaminho(arquivoTemp.toString());
			System.out.println(produto.getCaminho());// mostra o caminho da foto
			Messages.addGlobalInfo("Upload Realizado com Sucesso !");
		} catch (IOException e) {
			e.printStackTrace();
			Messages.addGlobalInfo("Ocorreu um erro ao tentar fazer o Upload de arquivo");
		} finally {
			Messages.addGlobalInfo("Nome: " + nome + "Tipo: " + tipo + "Tamanho: " + tamanho);
		}

	}

	/*
	 * método para imprimir o relatório Precisa ser importadas as dependências
	 * do JasperSoft no POM
	 */
	public void imprimir() {
		try {

			/*
			 * navegando na árvore de componentes da aplicação para pegar o
			 * filtro, passando o id do form e o id do componente
			 * Faces.getViewRoot().findComponent("idDoFormulario:idComponente");
			 * o resultado será guardado dentro de um DataTable
			 */
			DataTable tabela = (DataTable) Faces.getViewRoot().findComponent("frmListagem:tabela");
			/* pegando os filtros, getFilters utiliza um mapa */
			Map<String, Object> filtros = tabela.getFilters();

			/*
			 * capturando os filtros colocados na aplicação, como ambas os
			 * filtros são String podemos guardar em duas variáveis String
			 */
			String proDescricao = (String) filtros.get("descricao");
			String fabDescricao = (String) filtros.get("fabricante.descricao");

			/*
			 * tratando para pegar se a descrição for nula ou seja se não houver
			 * filtro
			 */

			/*
			 * classe para preencher relatório* onde eu preciso do primeiro
			 * parâmetro (sourceFileName) o caminho do arquivo - endereço em
			 * memória + params - parâmetros do relatório + connection - conexão
			 */
			/*
			 * traduz o caminho verdadeiro(/reports/produtos.jasper) em caminho
			 * da memória onde está acontecendo a execução
			 */
			String caminho = Faces.getRealPath("/reports/cadproduto.jrxml");
			/*
			 * gerar os parâmetros de pesquisa extrutura de dados que guarda o
			 * nome e o valor estrutura de mapa
			 */
			JasperCompileManager.compileReportToFile(caminho);

			Map<String, Object> paramentros = new HashMap<>();
			/*
			 * a conexão tem que ser do tipo connection e a que nos temos é a
			 * session(hibernate) que precisa ser convertido. Essa conversão
			 * será feita no HibernateUtil
			 */
			/*
			 * PASSANDO OS PARAMETROS DO PRIME PARA O RELATORIO, ONDE
			 * PRODUTO_DESC E FABRICANTE_DESC SÃO PARAMETROS DO RELATÓRIO,
			 * fazendo um if para verificar se o parametro está null colocando %
			 * para filtrar como no SQL
			 */

			if (proDescricao == null) {
				paramentros.put("PRODUTO_DESC", "%%");
			} else {
				paramentros.put("PRODUTO_DESC", "%" + proDescricao + "%");
			}
			if (fabDescricao == null) {
				paramentros.put("FABRICANTE_DESC", "%%");
			} else {
				paramentros.put("FABRICANTE_DESC", "%" + fabDescricao + "%");
			}

			Connection conexao = HibernateUtil.getConexao();
			/*
			 * o JasperFillManager retorna um JasperPrint que é o relatório
			 * popilado
			 */
			caminho = caminho.replaceAll("jrxml", "jasper");
			JasperPrint relatorio = JasperFillManager.fillReport(caminho, paramentros, conexao);
			// printreport imprime utilizando com ctrl + P
			JasperPrintManager.printReport(relatorio, true);

		} catch (JRException erro) {
			Messages.addGlobalError("Ocorreu um erro ao gerar relatório");
			erro.printStackTrace();
		}

	}

}
