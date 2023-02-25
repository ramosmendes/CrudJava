package br.com.agenda.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	// Nome usuário do mysql
	private static final String USERNAME = "root";

	// Senha do banco
	private static final String PASSWORD = "";

	// Caminho do banco de dados
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/agenda";

	/*
	 * Conexão com o banco de dados.
	 */

	public static Connection createConnectionToMySql() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");

		// Cria a conexão com o banco de Dados
		Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

		return connection;
	}

	public static void main(String[] args) throws Exception {
		// Recuperar a conexão com o BD.
		Connection con = createConnectionToMySql();

		// Testar se a conexão é nula
		if (con != null) {
			System.out.println("Conexão obtida com sucesso!");
			con.close();
		}

	}
}
