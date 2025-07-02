package FullstackPrueba2.example.FullstackPrueba2.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String contrasena;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;

    @Enumerated(EnumType.STRING)
    private Rol rol;
}
