package com.maike.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.mapper.mount.IMountedRequestMapper;
import org.apache.wicket.request.mapper.mount.MountMapper;
import org.apache.wicket.util.lang.PackageName;

import com.maike.login.LoginPage;
import com.maike.template.BasePage;
import com.maike.template.CriarPage;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see com.maike.app.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{
	
	private Connection conexao;
	
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return BasePage.class; 
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();
		mountPage("/criar", CriarPage.class);
		mountPackage("/base", BasePage.class);
		mountPackage("/login", LoginPage.class);
		// add your configuration here
		conexao = criaConexao();
	}
	
	

	private Connection criaConexao() {
		String url = "jdbc:postgresql://localhost:5432/test";
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			throw new UnsupportedOperationException(e.getMessage());
		}
	    
		try {
			return DriverManager.getConnection(url, "postgres", "mysecretpassword");
		} catch (SQLException e) {
			throw new RuntimeException();			
		}
	}

	public Connection getConexao() {
		return conexao;
	}
}
