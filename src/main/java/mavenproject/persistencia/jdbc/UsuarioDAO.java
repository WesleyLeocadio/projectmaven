package mavenproject.persistencia.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mavenproject.persistencia.entidade.Usuario;

public class UsuarioDAO {
	
	private Connection conexao = ConexaoFactory.getConnection();
	
	public void cadastrar(Usuario usu) {
		//Criando o Statment
		PreparedStatement preparador = null;
		String sql = "INSERT INTO usuario (nome, login, senha) VALUES (?, ?, ?);";
		
		try {
			preparador = conexao.prepareStatement(sql);
			preparador.setString(1, usu.getNome());
			preparador.setString(2, usu.getLogin());
			preparador.setString(3, usu.getSenha());
			//executando o comando SQL no banco
			preparador.execute();

		} catch (SQLException e) {
			e.getMessage();
		} finally {
			safeClosePreparedStatement(preparador);
		}
	}
	
	
	public void alterar (Usuario usu) {
		PreparedStatement preparador = null;
		String sql = "UPDATE usuario SET nome=?, login=?, senha=? WHERE id=?";
		
		try {
			preparador = conexao.prepareStatement(sql);
			preparador.setString(1, usu.getNome());
			preparador.setString(2, usu.getLogin());
			preparador.setString(3, usu.getSenha());
			preparador.setInt(4, usu.getId());
			//executando o comando SQL no banco
			preparador.execute();
		} catch (SQLException e) {
			e.getMessage();
		} finally {
			safeClosePreparedStatement(preparador);
		}
	}
	
	public void excluir(Usuario usu) {
		PreparedStatement preparador = null;
		String sql = "DELETE FROM usuario WHERE id=?";

		try {
			preparador = conexao.prepareStatement(sql);
			preparador.setInt(0, usu.getId());
			//executando o comando SQL no banco
			preparador.execute();

		} catch (SQLException e) {
			e.getMessage();
		} finally {
			safeClosePreparedStatement(preparador);
		}
	}	
	
	public void salvar(Usuario u) {
		if( u.getId()!= null ) {
			alterar(u);
		} else {
			cadastrar(u);
		}
	}
	
	public Usuario buscaPorId(Integer id) {
		Usuario usuarioRetorno = null;
		String sql = "SELECT * FROM usuario where id=?";
		try {
			PreparedStatement preparador =  conexao.prepareStatement(sql);
			preparador.setInt(1, id);
			//retorno da consulta em ResultSet
			ResultSet rs = preparador.executeQuery();
			if(rs.next()) {
				usuarioRetorno = new Usuario();
				usuarioRetorno.setId(rs.getInt("id"));
				usuarioRetorno.setNome(rs.getString("nome"));
				usuarioRetorno.setLogin(rs.getString("login"));
				usuarioRetorno.setSenha(rs.getString("senha"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usuarioRetorno;
	}
	/**
	 * Realiza a busca de todos os registros da tabela de usuários do banco
	 * @return Uma lista usuários
	 */
	public List<Usuario> buscaPorTodos() {
		List<Usuario> lista = new ArrayList<Usuario>();
		String sql = "SELECT * FROM usuario;";
		try {
			PreparedStatement preparador =  conexao.prepareStatement(sql);
			//retorno da consulta em ResultSet
			ResultSet rs = preparador.executeQuery();
			while(rs.next()) {
				Usuario usuarioRetorno = new Usuario();
				usuarioRetorno.setId(rs.getInt("id"));
				usuarioRetorno.setNome(rs.getString("nome"));
				usuarioRetorno.setLogin(rs.getString("login"));
				usuarioRetorno.setSenha(rs.getString("senha"));
				lista.add(usuarioRetorno);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public Usuario autenticar(String login, String senha) {
		Usuario usuarioRetorno = null;
		PreparedStatement preparador = null;
		
		String sql = "SELECT * FROM usuario WHERE login = ? and senha = ?;";

		try {
			preparador =  conexao.prepareStatement(sql);
			preparador.setString(1, login);
			preparador.setString(2, senha);

			ResultSet rs = preparador.executeQuery();
			if(rs.next()) {
				usuarioRetorno = new Usuario();
				usuarioRetorno.setId(rs.getInt("id"));
				usuarioRetorno.setLogin(rs.getString("login"));
				usuarioRetorno.setNome(rs.getString("nome"));
				usuarioRetorno.setSenha(rs.getString("senha"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			safeClosePreparedStatement(preparador);
		}

		
		return usuarioRetorno;
	}
	
	/**
	 * Fechando o objeto preparador
	 * @param pre
	 */
	private void safeClosePreparedStatement(PreparedStatement pre) {
		if (pre != null) {
			try {
				pre.close();
			} catch (SQLException e) {
				e.getMessage();
			}
		}
	}
}
