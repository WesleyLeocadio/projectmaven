package mavenproject.persistencia.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
