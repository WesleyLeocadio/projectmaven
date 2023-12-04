package mavenproject;

import mavenproject.persistencia.entidade.Usuario;
import mavenproject.persistencia.jdbc.UsuarioDAO;

public class TestUsuarioDAO {

	public static void main(String[] args) {
		
		Usuario usu = new Usuario();
		usu.setNome("Wesley");
		usu.setLogin("teste");
		usu.setSenha("123456");
		
		UsuarioDAO usuDao = new UsuarioDAO();
		usuDao.cadastrar(usu);
		System.out.print("Usuário cadastrado com sucesso!");
		
	}

}
