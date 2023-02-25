package br.com.agenda.application;

import java.util.Date;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.model.Contato;

public class Program {
	public static void main(String[] args) {
		ContatoDAO c1Dao = new ContatoDAO();

		Contato c1 = new Contato();
		c1.setNome("Maria Madalena");
		c1.setIdade(63);
		c1.setDataCadastro(new Date());

		c1Dao.save(c1);

		// Atualizar contato:
		Contato updateContact = new Contato();

		updateContact.setNome("Rafaela Maria");
		updateContact.setIdade(45);
		updateContact.setDataCadastro(new Date());
		updateContact.setId(2);// Número que está no banco de Dados

		c1Dao.update(updateContact);

		// Delete de registros:

		// c1Dao.deleteByID(3);

		// Visualização dos registros:

		for (Contato c : c1Dao.getContatos()) {
			System.out.println("\nContato: " + c.getNome() + ", ID = " + c.getId());
		}

	}

}
