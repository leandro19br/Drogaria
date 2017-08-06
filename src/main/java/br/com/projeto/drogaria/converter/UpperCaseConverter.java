package br.com.projeto.drogaria.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/*Classe que converte em maiúsculo*/
/*para saber que a classe é conversora utiliza anoção */
/*essa classe implemeta Converter, onde vai adicionar os métodos não implementados */
@FacesConverter("upperCaseConverter")
public class UpperCaseConverter implements Converter {

	/* Pega uma String e Converte em Objeto */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		/* String pode ser vazia = null e pode ter algo dentro */
		if (value == null) {
			return null;
		} else {
			/* se vier algo retorna em maiusculo */
			return value.toUpperCase();
		}

	}

	/* pega o Objeto e converte para String */
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		/* Objeto pode vale nulo ou vazia */
		if (value == null) {
			/* "" é uma String vazia */
			return "";
		} else {
			/* senão returna convertendo o Objeto em String */
			return value.toString();
		}

	}

}
