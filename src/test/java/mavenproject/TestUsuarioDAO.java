package mavenproject;

import java.util.List;

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
		
	}

	public static void testarListarTodos() {
		UsuarioDAO usuarioDao = new UsuarioDAO();
		List<Usuario> lista = usuarioDao.buscaPorTodos();
		for(Usuario u: lista) 
			System.out.print(u);
	}
}
