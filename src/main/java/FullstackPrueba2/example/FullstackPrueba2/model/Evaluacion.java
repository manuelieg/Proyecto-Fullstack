package FullstackPrueba2.example.FullstackPrueba2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evaluacion extends RepresentationModel<Evaluacion> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titulo;
    private String descripcion;
    private String fechaInicio;
    private String fechaFin;

    @ManyToOne
    @JoinColumn(name = "id_curso")
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "id_profesor")
    private Usuario profesor;
}