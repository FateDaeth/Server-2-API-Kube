package com.task1.server2api.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.task1.server2api.entity.Server;
import com.task1.server2api.repository.ServerRepository;
import com.task1.server2api.service.ServerService;

@Service
public class ServerServiceImpl implements ServerService{

private ServerRepository serverRepository;
	
	public ServerServiceImpl(ServerRepository serverRepository) {
		super();
		this.serverRepository = serverRepository;
	}

	@Override
	public List<Server> getAllServers() {
		return serverRepository.findAll();
	}

	@Override
	public Server saveServer(Server server) {
		return serverRepository.save(server);
	}

	@Override
	public Server getServerByName(String id) {
		return serverRepository.findById(id).get();
	}

	@Override
	public Server updateServer(Server server) {
		return serverRepository.save(server);
	}

	@Override
	public void deleteServerByName(String id) {
		serverRepository.deleteById(id);	
	}

}




