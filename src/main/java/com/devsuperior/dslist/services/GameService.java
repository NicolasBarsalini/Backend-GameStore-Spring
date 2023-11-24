package com.devsuperior.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.repositories.GameRepository;

//devemos registrar esse componente no sistema, qualquer componente q n herde nenhum jpa deve ser registrado, pro framework gerenciar-los

//@Component, ou:
@Service //registra como componente do sistema, injetar um componente no outro
public class GameService {
	
	@Autowired //njeto uma instancia do gamerepository dentro do gameservice
	private GameRepository gameRepository; //fazemos a injeção do componente gamerepository, visando unir dois componentes
	
	
	public List<GameMinDTO> findAll(){ //meu service devolve uma lista de objeto, o service devolve dto, retorna uma lista de gamemindto
		List<Game> result = gameRepository.findAll(); //faz uma consulta no banco de dados e traz os todos os objetos
		List<GameMinDTO> dto = result.stream().map(x -> new GameMinDTO (x)).toList(); //converter a lista de game para gamemin dto
		 //passa o próprio x como argumento
		return dto;
		//ou
		//return gameRepository.findAll().stream().map(x -> new GameMinDTO (x)).toList();
	}
	
	//devemos criar o controlador rest agora
}
