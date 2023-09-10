package com.maike.login;


import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

import com.maike.template.InicioPage;

public class LoginPage extends WebPage {
	private static final long serialVersionUID = 795475219774704903L;
	
	public LoginPage(){
		final TextField<String> campoNomeUsuario = new TextField<String>("nomeUsuario", new Model<String>());
		final PasswordTextField campoPasswordUsuario = new PasswordTextField("passwordUsuario", new Model<String>());
		
		final Label mesagemErroLogin = new Label("mensagemErroLogin", Model.of("Erro ao realizar login."));
		mesagemErroLogin.setOutputMarkupPlaceholderTag(true).setVisible(false);
		
		Form<String> formularioLogin = new Form<String>("formularioLogin") {
			private static final long serialVersionUID = -2198354306445910901L;
			
			@Override
			public final void onSubmit() {
				String nomeUsuario = campoNomeUsuario.getModelObject();
				String senha = campoPasswordUsuario.getModelObject();
				
				if(nomeUsuario.equals("maike") && senha.equals("rodrigues")) {
					getSession().setAttribute("userName", nomeUsuario);
					setResponsePage(InicioPage.class);					
				}else {
					mesagemErroLogin.setVisible(true);
				}
			}
		
		};
		
		add(mesagemErroLogin, formularioLogin);
		formularioLogin.add(campoNomeUsuario, campoPasswordUsuario);
		
	}

}
