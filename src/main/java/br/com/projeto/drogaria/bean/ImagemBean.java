package br.com.projeto.drogaria.bean;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/*Classe para gerenciar a foto já que se utilizar com SessionScoped 
 * cai em uma excessão pois não envia o caminho das imagens no primeiro carregamento
 * e utilizar RequestScoped com PrimeFaces não funciona bem
 * Essa classe será amarrada no xhtm na TAG graphicImage*/

@ManagedBean(name = "imagemBean")
@RequestScoped // imagem vai ser recarregada a cada click guardar os bytes da
				// foto
public class ImagemBean {
	// criar variáeis para gurdar caminho da foto e bites da foto

	/*
	 * para amarrar o caminho que vem do ProdutoBean e não vem automático. Para
	 * isso no xhtm foi criado uma tag de parâmetro para ser enviado
	 */
	@ManagedProperty("#{param.caminho}")
	private String caminho;
	/* StreamedContent classe do prime que usa para */
	private StreamedContent foto;

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	/*
	 * método foi feito um if caso a imagem for null ou vazia para chamar uma
	 * imagem de 1 px
	 */
	public StreamedContent getFoto() throws IOException {
		if (caminho == null || caminho.isEmpty()) {
			Path path = Paths.get("C:/Users/leand/OneDrive/Documentos/Programação WEB com java/Uploads/branco.png");
			// precisa transformar o resultado do caminho acima em bytes
			InputStream stream = Files.newInputStream(path);
			// copiar essa stream para a foto a imagem branca
			foto = new DefaultStreamedContent(stream);
		} else {
			// se for diferente passa o caminho da foto verdadeira
			Path path = Paths.get(caminho);
			// precisa transformar o resultado do caminho acima em bytes
			InputStream stream = Files.newInputStream(path);
			// copiar essa stream para a foto
			foto = new DefaultStreamedContent(stream);

		}
		return foto;
	}

	public void setFoto(StreamedContent foto) {
		this.foto = foto;
	}

}
