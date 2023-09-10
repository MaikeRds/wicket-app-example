package com.maike.template;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.feedback.ErrorLevelFeedbackMessageFilter;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.StringValidator;

import com.maike.app.WicketApplication;
import com.maike.contato.Contato;
import com.maike.contato.ContatoDAO;
import com.maike.contato.EstadoCivil;

public class CriarPage extends BasePage {
	private static final long serialVersionUID = 4293742563963257628L;
	
	public CriarPage() {
		this(new Contato());
	}

	protected CriarPage(Contato contato) {
		Label titulo = new Label("titulo", Model.of("Criação de contato"));
		add(titulo);
		
		CompoundPropertyModel<Contato> compoundPropertyModelContato = new CompoundPropertyModel<Contato>(contato);
		Form<Contato> form = new Form<Contato>("formularioContato", compoundPropertyModelContato) {
			private static final long serialVersionUID = 1L;
			
			@Override
			public void onSubmit() {
				Contato contatoSubmetido = getModelObject();
				System.out.println(contatoSubmetido);
				salvar(contatoSubmetido);
				setResponsePage(InicioPage.class);
			}
			
		};
		
		add(form);
		
		TextField<String> inputNome = new TextField<String>("nome");
		TextField<String> inputEmail = new TextField<String>("email");
		TextField<String> inputTelefone = new TextField<String>("telefone");
		DropDownChoice<EstadoCivil> comboEstadoCivil = new DropDownChoice<>("estadoCivil", Arrays.asList(EstadoCivil.values()), 
				new IChoiceRenderer<EstadoCivil>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Object getDisplayValue(EstadoCivil object) {
						return object.getLabel();
					}

					@Override
					public String getIdValue(EstadoCivil object, int index) {
						// TODO Auto-generated method stub
						return object.name();
					}

					@Override
					public EstadoCivil getObject(String id, IModel<? extends List<? extends EstadoCivil>> choices) {
						// TODO Auto-generated method stub
						return EstadoCivil.valueOf(id);
					}
				});
		
		form.add(inputNome, inputEmail, inputTelefone, comboEstadoCivil);
		
		inputNome.setLabel(Model.of("Nome do Contato")).setRequired(true).add(StringValidator.maximumLength(10));
		inputEmail.setLabel(Model.of("Email do Contato")).add(EmailAddressValidator.getInstance());
		
		add(new FeedbackPanel("feedbackMessage", new ErrorLevelFeedbackMessageFilter(FeedbackMessage.ERROR)));
	}
	
	protected void salvar(Contato contato) {
		Connection conexao = ((WicketApplication) getApplication()).getConexao();
		ContatoDAO dao = new ContatoDAO(conexao);
		dao.inserir(contato);
	}

}
