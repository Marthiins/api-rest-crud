package br.com.marthiins.crud.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.marthiins.crud.model.Usuario;
import br.com.marthiins.crud.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
 
	@Autowired /* Injeção de dependencias */
	private UsuarioRepository usuarioRepository; /*usuario repository é uma variavel do java*/
	
	@GetMapping(value = "listatodos") /* Buscar da API buscar todos */
	@ResponseBody /* Retorna os dados para o corpo da resposta */
	public ResponseEntity<List<Usuario>> listaUsuario() {

		List<Usuario> usuarios = usuarioRepository.findAll();/* Executa a consulta no banco de dados */

		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}
	
	
	
}
