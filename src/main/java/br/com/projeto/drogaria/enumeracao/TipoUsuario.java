package br.com.projeto.drogaria.enumeracao;

/*enum é um conjunto de constantes finita. Será utilizado em 
 * cadastro de usuário para determinar o Gerente,Administrador ou Balconista*/
public enum TipoUsuario {
	/*
	 * por convenção, constantes são colocadas em Maiúsculo separados por, e no
	 * ultimo;
	 */

	ADMINISTRADOR, GERENTE, BALCONISTA;

	/* formatando para aparecer o restante em minusculo */
	@Override
	public String toString() {
		switch (this) {
		case ADMINISTRADOR:

			return "Administrador";

		case GERENTE:

			return "Gerente";

		case BALCONISTA:

			return "Balconista";

		default:
			return null;
		}

	}
}
