package com.task1.server2api.entity;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;




@Document(collection= "server2api")
public class Server {

	
	private String id;
	@Id
	private String serverName;
	
	private String language;
	
	private String framework;
	
	public Server() {
		
	}
	
	public Server(String serverName,String id, String language, String framework) {
		super();
		
		this.serverName = serverName;
		this.id = id;
		this.language = language;
		this.framework = framework;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public String getLanguage() {
		return language;	
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getFramework() {
		return framework;
	}
	public void setFramework(String framework) {
		this.framework = framework;
	}
}

