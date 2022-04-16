package com.task1.server2api.service;


import java.util.List;

import com.task1.server2api.entity.Server;

public interface ServerService {

	List<Server> getAllServers();
	
	Server saveServer(Server server);
	
	Server getServerByName(String id);
	
	Server updateServer(Server server);
	
	void deleteServerByName(String id);

}

