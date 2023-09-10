package com.maike.template;

import java.sql.Connection;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;

import com.maike.app.WicketApplication;
import com.maike.contato.Contato;
import com.maike.contato.ContatoDAO;

public class EditarPage extends CriarPage {
	private static final long serialVersionUID = 4384342479089708576L;

	public EditarPage(Contato contato) {
		super(contato);
		Label titulo = new Label("titulo", Model.of("Editar de contato"));
		replace(titulo);
	}
	
	@Override
	protected void salvar(Contato contato) {
		Connection conexao = ((WicketApplication) getApplication()).getConexao();
		ContatoDAO dao = new ContatoDAO(conexao);
		dao.atualizar(contato);
	}
	

}
