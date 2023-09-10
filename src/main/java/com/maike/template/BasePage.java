package com.maike.template;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

import com.maike.login.LoginPage;

public class BasePage extends WebPage {

	private static final long serialVersionUID = 8472645348497738340L;
	
	public BasePage() {
		String userName = (String) getSession().getAttribute("userName");
		
		if(userName == null) {
			setResponsePage(LoginPage.class);
			return;
		}
		
		add(new Link<Void>("sair") {
			private static final long serialVersionUID = -285725433200376007L;

			@Override
			public void onClick() {
				 getSession().invalidate();
				 setResponsePage(InicioPage.class);
			}
			
		});
	}

}
