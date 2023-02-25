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

		// c1Dao.save(c1);

		// Visualização dos registros;

		for (Contato c : c1Dao.getContatos()) {
			System.out.println("\nContato: " + c.getNome());
		}

	}

}
