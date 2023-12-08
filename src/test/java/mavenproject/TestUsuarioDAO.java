package mavenproject;

import mavenproject.persistencia.entidade.Usuario;
import mavenproject.persistencia.jdbc.UsuarioDAO;

public class TestUsuarioDAO {

	public static void main(String[] args) {
		/**
		Usuario usu = new Usuario();
		usu.setNome("Wesley");
		usu.setLogin("teste");
		usu.setSenha("123456");
		
		UsuarioDAO usuDao = new UsuarioDAO();
		usuDao.cadastrar(usu);
		System.out.print("Usuário cadastrado com sucesso!");
		*/
		
		System.out.println(retornarDataComBarra(null));
	}
	
	public static String retornarDataComBarra(String data) {
		String novaData = "";
		if (data != null && data.length() == 8) {
			novaData = data.substring(0, 2);
			novaData = novaData + '.';
			novaData = novaData + data.substring(2, 4);
			novaData = novaData + '.';
			novaData = novaData + data.substring(4, data.length());
		} else {
			novaData = data.substring(0,2);
			novaData = novaData + '.';
			novaData = novaData + data.substring(3, 5);
			novaData = novaData + '.';
			novaData = novaData + data.substring(6, data.length());
		}
		return novaData;
	}

}
