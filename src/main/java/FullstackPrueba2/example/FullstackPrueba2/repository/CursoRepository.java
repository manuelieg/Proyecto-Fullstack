package FullstackPrueba2.example.FullstackPrueba2.repository;

import FullstackPrueba2.example.FullstackPrueba2.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {
}