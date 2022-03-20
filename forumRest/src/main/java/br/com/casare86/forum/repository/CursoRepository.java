package br.com.casare86.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.casare86.forum.modelo.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

	Curso findByNome(String nome);

}
