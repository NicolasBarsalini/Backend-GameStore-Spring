package com.devsuperior.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.repositories.GameRepository;

//devemos registrar esse componente no sistema, qualquer componente q n herde nenhum jpa deve ser registrado, pro framework gerenciar-los

//@Component, ou:
@Service //registra como componente do sistema, injetar um componente no outro
public class GameService {
	
	@Autowired //njeto uma instancia do gamerepository dentro do gameservice
	private GameRepository gameRepository; //fazemos a injeção do componente gamerepository, visando unir dois componentes
	
	@Transactional(readOnly = true)
	public List<GameMinDTO> findAll(){ //meu service devolve uma lista de objeto, o service devolve dto, retorna uma lista de gamemindto
		List<Game> result = gameRepository.findAll(); //faz uma consulta no banco de dados e traz os todos os objetos
		List<GameMinDTO> dto = result.stream().map(x -> new GameMinDTO (x)).toList(); //converter a lista de game para gamemin dto
		 //passa o próprio x como argumento
		return dto;
		//ou
		//return gameRepository.findAll().stream().map(x -> new GameMinDTO (x)).toList();
	}
	
	//devemos criar o controlador rest agora

	//colocar uma notation para induzir toda a pratica de acesso ao banco de dados seja transacional, duradoura e funcional
	@Transactional(readOnly = true) //do spring
	public GameDTO findById(Long gameId) {
		Game result = gameRepository.findById(gameId).get(); //retorna um tipo optional, para pegar o game dentro do optional, tenho q colocar um .get
		//poderiamos colocar um tratamento de exeções caso n exista o id.
		GameDTO dto = new GameDTO(result); //converter para dto o game encontrado
		return dto;
	}
}
