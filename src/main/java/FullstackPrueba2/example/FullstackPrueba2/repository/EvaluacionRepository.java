package FullstackPrueba2.example.FullstackPrueba2.repository;

import FullstackPrueba2.example.FullstackPrueba2.model.Evaluacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluacionRepository extends JpaRepository<Evaluacion, Integer> {
}
