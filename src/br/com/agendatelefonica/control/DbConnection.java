package br.com.agendatelefonica.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

	public static Connection getConnection() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/bd_agendatelefonica";
		String user = "root";
		String password = "217moto";
				
		Connection conexao = DriverManager.getConnection(url, user, password);		
		return conexao;	
	}
}
