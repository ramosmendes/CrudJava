package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

			System.out.print("Executado com sucesso!!!");
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

	public void update(Contato contato) {

		String sql = "UPDATE contatos SET nome = ?, idade = ?, dataCadastro = ? " + "WHERE id = ?";
		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Criar conexão
			conn = ConnectionFactory.createConnectionToMySql();

			// Classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			// Adicionar os valores para atualizar
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));

			// Id do registro que deseja alterar
			pstm.setInt(4, contato.getId());

			// Executar a query
			pstm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteByID(int id) {
		String sql = "DELETE FROM contatos WHERE id = ?";

		Connection conn = null;

		PreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.createConnectionToMySql();

			pstm = conn.prepareStatement(sql);

			pstm.setInt(1, id);

			pstm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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

	public List<Contato> getContatos() {
		String sql = "SELECT * FROM contatos";

		List<Contato> contacts = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstm = null;

		// Classe que recupera os dados do database ****SELECT****
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMySql();

			pstm = (PreparedStatement) conn.prepareStatement(sql);

			rset = pstm.executeQuery();

			while (rset.next()) {
				Contato cont = new Contato();

				// Recuperar ID
				cont.setId(rset.getInt("id"));

				// Recuperar Nome
				cont.setNome(rset.getString("nome"));

				// Recuperar Idade
				cont.setIdade(rset.getInt("idade"));

				// Recuperar Datas
				cont.setDataCadastro(rset.getDate("dataCadastro"));

				contacts.add(cont);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}

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

		return contacts;

	}
}
