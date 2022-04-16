package com.task1.server2api.controller;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.task1.server2api.entity.Server;
import com.task1.server2api.repository.ServerRepository;
import com.task1.server2api.service.ServerService;

@Controller
public class serverController {


	private ServerService serverService;
	

	@Autowired
	public ServerRepository serverrepository;

	public serverController(ServerService serverService) {
		super();
		this.serverService = serverService;
	}
	
		
		
	// handler method to handle list students and return mode and view
	@GetMapping("/servers")
	public String listServers(Model model) {
		model.addAttribute("sts", serverService.getAllServers());
		return "servers";
	}
	
	@GetMapping("/servers/new")
	public String createServerForm(Model model) {
		
		// create student object to hold student form data
		Server server = new Server();
		model.addAttribute("st", server);
		return "create_servers";
		
	}
	
	@PostMapping("/servers")
	public String saveServer(@ModelAttribute("st") Server server) {
		serverService.saveServer(server);
		return "redirect:/servers";
	}
	
	@GetMapping("/servers/edit/{id}")
	public String editServerForm(@PathVariable String id, Model model) {
		model.addAttribute("st", serverService.getServerByName(id));
		return "edit_servers";
	}

	@PostMapping("/servers/{id}")
	public String updateServer(@PathVariable String id,
			@ModelAttribute("server") Server server,
			Model model) {
		
		// get student from database by id
		Server existingServer = serverService.getServerByName(id);
		existingServer.setId(server.getId());
		existingServer.setServerName(server.getServerName());
		existingServer.setLanguage(server.getLanguage());
		existingServer.setFramework(server.getFramework());
		
		// save updated student object
		serverService.updateServer(existingServer);
		return "redirect:/servers";		
	}
	
	// handler method to handle delete student request
	@GetMapping("/servers/{name}")
	public String listServersbyid(@PathVariable String name,Model model) {
		model.addAttribute("sts", serverService.getServerByName(name));
		return "serversbyname";
	}
	
	@GetMapping("/servers/delete/{id}")
	public String deleteStudent(@PathVariable String id) {
		serverService.deleteServerByName(id);
		return "redirect:/servers";
	}

}




