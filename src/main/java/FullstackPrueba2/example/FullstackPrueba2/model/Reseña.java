package FullstackPrueba2.example.FullstackPrueba2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reseña {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int calificacion;
    private String comentario;
    private String fecha;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario estudiante;

    @ManyToOne
    @JoinColumn(name = "id_curso")
    private Curso curso;
}