package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;

public class ContatoDAO {

	/*
	 * CRUD c: CREATE r: READ u: UPDATE d: DELETE
	 */

	public void save(Contato contact) {
		String sql = "INSERT INTO contatos(nome,idade,dataCadastro) VALUES  (?,?,?)";

		Connection conn = null;

		PreparedStatement pstm = null;

		try {
			// Criar conexão com o Database
			conn = ConnectionFactory.createConnectionToMySql();

			// PreparedStatement pronta para executar uma query
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			// Adicionar os valores esperados pela query;
			pstm.setString(1, contact.getNome());
			pstm.setInt(2, contact.getIdade());
			pstm.setDate(3, new Date(contact.getDataCadastro().getTime()));

			// executar query
			pstm.execute();

		} catch (Exception e) {
			System.out.println("ERROR! : " + e.getMessage());
		} finally {
			// Fechar as conexões

			try {
				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
