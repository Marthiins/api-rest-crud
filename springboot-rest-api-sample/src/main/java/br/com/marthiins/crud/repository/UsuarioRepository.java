package br.com.marthiins.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.marthiins.crud.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
/*Buscar ususario por nome trim tira os espaços*/
	
	@Query(value = "select u from Usuario u where upper(trim (u.nome)) like %?1%")
	List<Usuario>buscarPorNome(String name); /*Interface não fazemos nada dentro dele*/
}


