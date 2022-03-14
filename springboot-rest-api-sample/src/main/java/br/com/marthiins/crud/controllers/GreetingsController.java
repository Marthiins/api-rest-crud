package br.com.marthiins.crud.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.marthiins.crud.model.Usuario;
import br.com.marthiins.crud.repository.UsuarioRepository;

@RestController
public class GreetingsController {

	@Autowired /* Injeção de dependencias */
	private UsuarioRepository usuarioRepository; /* usuario repository é uma variavel do java */

	@GetMapping(value = "listatodos") /* Buscar da API buscar todos */
	@ResponseBody /* Retorna os dados para o corpo da resposta */
	public ResponseEntity<List<Usuario>> listaUsuario() {

		List<Usuario> usuarios = usuarioRepository.findAll();/* Executa a consulta no banco de dados */

		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}

	@GetMapping(value = "buscaruserid") /* Mapeia a url */
	@ResponseBody /* Fazer a descrição da resposta */
	public ResponseEntity<Usuario> buscaruserid(
			@RequestParam(name = "iduser") Long iduser) {/*@RequestParam(name = "iduser") recebe os dados para consultar*/

		Usuario usuario = usuarioRepository.findById(iduser).get();

		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

	@GetMapping(value = "buscarPorNome") /* Mapeia a url */
	@ResponseBody /* Fazer a descrição da resposta */
	public ResponseEntity<List<Usuario>> buscarPorNome(
		 @RequestParam(name = "name") String name) {/*@RequestParam(name = "iduser") recebe os dados para consulta*/

		List<Usuario> usuario = usuarioRepository.buscarPorNome(name.trim().toUpperCase()); /* Pesquisa no banco trim tira os espaços */

		return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
	}

	@PostMapping(value = "salvar") /* Mapeia a url */
	@ResponseBody /* Fazer a descrição da resposta */
	public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) { /* @RequestBody recebe os dados para salvar */

		Usuario user = usuarioRepository.save(usuario);

		return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);

	}

	@PutMapping(value = "atualizar") /* Mapeia a url */
	@ResponseBody /* Fazer a descrição da resposta */
	public ResponseEntity<?> atualizar(
			@RequestBody Usuario usuario) { /* quando se tem ? podemos retornar qualquer coisa */

		if (usuario.getId() == null) {
			return new ResponseEntity<String>("Id não foi informado para atualização", HttpStatus.OK);
		}
		Usuario user = usuarioRepository.saveAndFlush(usuario);

		return new ResponseEntity<Usuario>(user, HttpStatus.OK);

	}

	@DeleteMapping(value = "delete") /* Mapeia a url */
	@ResponseBody /* Fazer a descrição da resposta */
	public ResponseEntity<String> delete(
			@RequestParam Long iduser) {/* @RequestParam requisição deparametro para passar o id para delete */

		usuarioRepository.deleteById(iduser); /* Quanto deleta não tem retorno por isso não tem o Usuario User */

		return new ResponseEntity<String>("User deletado com sucesso", HttpStatus.OK);
	}

}
