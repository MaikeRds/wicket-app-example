package com.maike.template;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;

public class InicioPage extends BasePage {

	private static final long serialVersionUID = -5876308912301792758L;
	
	public InicioPage() {
		Label labelMensagemBoasVindas = new Label("mensagemBoasVindas", Model.of("Bem vindo á agenda eletrônica!"));
		add(labelMensagemBoasVindas);
	}
	

}
