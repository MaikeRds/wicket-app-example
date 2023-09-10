package com.maike.template;


import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.Model;

import com.maike.app.WicketApplication;
import com.maike.contato.Contato;
import com.maike.contato.ContatoDAO;

public class PesquisarPage extends BasePage {
	private static final long serialVersionUID = -3501751388782183386L;
	List<Contato> contatos = new ArrayList<>();
	 private String searchNome = ""; // Nome para busca
	
	public PesquisarPage() {
		Label titulo = new Label("titulo", Model.of("Pesquisar contatos"));
		add(titulo);

		
		Form<String> formularioPesquisa = new Form<String>("formularioPesquisa");
		add(formularioPesquisa);
		
		final TextField<String> pesquisaNome = new TextField<>("pesquisaNome", new Model<String>());
		formularioPesquisa.add(pesquisaNome);
		
		final WebMarkupContainer containerResultado = new WebMarkupContainer("divResultados");
		containerResultado.setVisible(false);
		containerResultado.setOutputMarkupPlaceholderTag(true);
		add(containerResultado);
		
		PropertyListView<Contato> listaResultados = new PropertyListView<Contato>("contatos", contatos) {
			private static final long serialVersionUID = -2717431676094681575L;
			@Override
			protected void populateItem(ListItem<Contato> item) {
				final Contato contato = item.getModelObject();
				item.add(new Label("nome", contato.getNome()));
				item.add(new Label("email", contato.getEmail()));
				item.add(new Label("telefone", contato.getTelefone()));
				item.add(new Label("estadoCivil", contato.getEstadoCivil()));
				item.add(new Link<Void>("linkEditar") {
					private static final long serialVersionUID = 5414528189967674136L;

					@Override
					public void onClick() {
						setResponsePage(new EditarPage(contato));						
					}
					
				});
				
			}
			
		};
		
		containerResultado.add(listaResultados);
		
		AjaxButton botaoPesquisar = new AjaxButton("botaoPesquisar", formularioPesquisa) {
			private static final long serialVersionUID = 6909471672279629129L;
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				searchNome = pesquisaNome.getModelObject();
				listarPorNome(searchNome);
				containerResultado.setVisible(true);
				target.add(containerResultado);
			}
		};		
		formularioPesquisa.add(botaoPesquisar);
	}
	
	private void listarPorNome(String nome) {
		contatos.clear();
		Connection conexao = ((WicketApplication) getApplication()).getConexao();
		ContatoDAO dao = new ContatoDAO(conexao);
		for(Contato contato : dao.listarPorNome(nome)) {
			contatos.add(contato);
		}
	}
	

}
