package mavenproject.persistencia.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mavenproject.persistencia.entidade.Usuario;
import mavenproject.persistencia.jdbc.UsuarioDAO;

@WebServlet("/usuariocontroller.do")
public class UsuarioController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.print("Chamou o doGet!");
		super.doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nome = req.getParameter("nome");
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		
		Usuario usuario =  new Usuario();
		usuario.setNome(nome);
		usuario.setLogin(login);
		usuario.setSenha(senha);

		UsuarioDAO usuDao = new UsuarioDAO();
		usuDao.salvar(usuario);
		System.out.print("Usuario cadastrado!");
		
		super.doPost(req, resp);
	}

}
