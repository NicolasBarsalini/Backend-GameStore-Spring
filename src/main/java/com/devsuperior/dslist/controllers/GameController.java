package com.devsuperior.dslist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.services.GameService;

@RestController
//fazer o mapeamento do recurso

@RequestMapping(path = "/games") //configuro o caminho q vai ser respondido na api
public class GameController { //ele vai ser a porta de entrada do backend, ele quem disponibiliza a API
	
	@Autowired
	private GameService gameService; //injetar o componente gameService
	//devemos mapear ele também com o verbo http, como e um endpoint pra buscar os games, ele é get
	
	@GetMapping
	public List<GameMinDTO> findAll(){
		List<GameMinDTO> result = gameService.findAll();
		return result;
	}
	
	@GetMapping(value = "/{id}") //deve receber um id  e devemos configurar que o id do finByid seja o do que ser mandado na requisição
	public GameDTO findById(@PathVariable Long id){ //indica que este id eh o que foi usado no corpo da requisição
		GameDTO result = gameService.findById(id);
		return result;
	}
}
