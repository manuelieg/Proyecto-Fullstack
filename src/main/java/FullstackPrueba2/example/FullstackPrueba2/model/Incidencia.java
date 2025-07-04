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
public class Incidencia extends RepresentationModel<Incidencia> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String descripcion;
    private String estado;
    private String fechaReporte;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuarioReporta;
}