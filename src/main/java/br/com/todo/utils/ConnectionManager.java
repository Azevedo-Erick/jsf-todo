package br.com.todo.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager{
	private String url;
	private String user;
	private String password;
	private Connection con;
	
	public ConnectionManager(){
		url = "jdbc:postgresql://localhost:5432/schema";
		user = "";
		password = "";
	}
	public void connect() {
		try{
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url);
			LogMessages.sucessConnect();
		}catch(Exception e) {
			e.printStackTrace();
			if(e instanceof ClassNotFoundException) {
				LogMessages.driverError();
			}else {
				LogMessages.errorOnConnection();
			}
		}
	}
	
	public void disconnect() {
		if(con!=null) {
			try {
			con.close();
			LogMessages.closingConnection();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Connection getCon() {
		return con;
	}
	public void setCon(Connection con) {
		this.con = con;
	}
	
	

}
