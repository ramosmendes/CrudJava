package br.com.agenda.application;

import java.util.Date;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.model.Contato;

public class Program {
	public static void main(String[] args) {
		ContatoDAO c1Dao = new ContatoDAO();

		Contato c1 = new Contato();
		c1.setNome("Jonas Gabriel");
		c1.setIdade(26);
		c1.setDataCadastro(new Date());

		c1Dao.save(c1);

	}

}
