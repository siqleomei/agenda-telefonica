package br.com.agendatelefonica.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.agendatelefonica.model.Contato;

public class ContatoDAO {

	public static String QUERY_NOME = "SELECT * FROM tb_contato WHERE nome LIKE ?";
	public static String QUERY_TELEFONE = "SELECT * FROM tb_contato WHERE telefone LIKE ?";
	public static String QUERY_ENDERECO = "SELECT * FROM tb_contato WHERE endereco LIKE ?";
	
	public static boolean insert(Contato contato) throws SQLException {
		boolean result = false;
		
		Connection conn = DbConnection.getConnection();
		try {
			PreparedStatement mPs = conn.prepareStatement("INSERT INTO tb_contato (nome, telefone, endereco) VALUES (?, ?, ?)");
			mPs.setString(1, contato.getNome());
			mPs.setString(2, contato.getTelefone());
			mPs.setString(3, contato.getEndereco());
			
			result = !mPs.execute();
			mPs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		
		return result;
	}
	
	public static boolean update(Contato contato) throws SQLException {
		boolean result = false;
		
		Connection conn = DbConnection.getConnection();
		try {
			PreparedStatement mPs = conn.prepareStatement("UPDATE tb_contato SET nome = ?, telefone = ?, endereco = ? WHERE id = ?");
			mPs.setString(1, contato.getNome());
			mPs.setString(2, contato.getTelefone());
			mPs.setString(3, contato.getEndereco());
			mPs.setInt(4, contato.getId());
			
			result = !mPs.execute();
			mPs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		
		return result;
	}
	
	public static boolean delete(Contato contato) throws SQLException {
		boolean result = false;
		
		Connection conn = DbConnection.getConnection();
		try {
			PreparedStatement mPs = conn.prepareStatement("DELETE FROM tb_contato WHERE id = ?");
			mPs.setInt(1, contato.getId());
			
			result = !mPs.execute();
			mPs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		
		return result;
	}
	
	public static List<Contato> selectAll() throws SQLException {
		List<Contato> contatos = null;
		
		Connection conn = DbConnection.getConnection();
		try {
			PreparedStatement mPs = conn.prepareStatement("SELECT * FROM tb_contato");
			
			ResultSet mRs = mPs.executeQuery();
			if (mRs.first()) {
				contatos = new ArrayList<>();
				
				do {
					Contato contato = new Contato();
					contato.setId(mRs.getInt("id"));
					contato.setNome(mRs.getString("nome"));
					contato.setTelefone(mRs.getString("telefone"));
					contato.setEndereco(mRs.getString("endereco"));
					
					contatos.add(contato);
				} while (mRs.next());
			}
			mRs.close();
			mPs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		
		return contatos;
	}
	
	public static List<Contato> select(String query, String field) throws SQLException {
		List<Contato> contatos = null;
		
		Connection conn = DbConnection.getConnection();
		try {
			PreparedStatement mPs = conn.prepareStatement(query);
			mPs.setString(1, "%" + field + "%");
			
			ResultSet mRs = mPs.executeQuery();
			if (mRs.first()) {
				contatos = new ArrayList<>();
				
				do {
					Contato contato = new Contato();
					contato.setId(mRs.getInt("id"));
					contato.setNome(mRs.getString("nome"));
					contato.setTelefone(mRs.getString("telefone"));
					contato.setEndereco(mRs.getString("endereco"));
					
					contatos.add(contato);
				} while (mRs.next());
			}
			mRs.close();
			mPs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		
		return contatos;
	}
}
