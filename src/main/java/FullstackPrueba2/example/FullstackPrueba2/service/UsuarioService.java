package FullstackPrueba2.example.FullstackPrueba2.service;

import FullstackPrueba2.example.FullstackPrueba2.model.Usuario;
import FullstackPrueba2.example.FullstackPrueba2.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Service
@Transactional
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String agregarUsuario(Usuario usuario) {
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        usuarioRepository.save(usuario);
        return "Usuario agregado con exito";
    }

    public String listarUsuarios() {
        String output = "";
        for(Usuario usuario:usuarioRepository.findAll()){
            output += "ID Usuario: " + usuario.getId() + "\n";
            output += "Username: " + usuario.getUsername() + "\n";
            output += "Nombre: " + usuario.getNombre() + "\n";
            output += "Apellido: " + usuario.getApellido() + "\n";
            output += "Correo: " + usuario.getCorreo() + "\n";
            output += "Teléfono: " + usuario.getTelefono() + "\n";
            output += "Rol: " + usuario.getRol() + "\n";
        }

        if(output.isEmpty()){
            return "No hay usuarios";
        } else {
            return output;
        }
    }

    public String obtenerUsuarioPorID(int id){
        String output="";
        if(usuarioRepository.existsById(id)){
            Usuario usuario = usuarioRepository.findById(id).get();
            output += "ID Usuario: " + usuario.getId() + "\n";
            output += "Username: " + usuario.getUsername() + "\n";
            output += "Nombre: " + usuario.getNombre() + "\n";
            output += "Apellido: " + usuario.getApellido() + "\n";
            output += "Correo: " + usuario.getCorreo() + "\n";
            output += "Teléfono: " + usuario.getTelefono() + "\n";
            output += "Rol: " + usuario.getRol() + "\n";
            return output;
        } else {
            return "No se encuentra el usuario";
        }
    }

    public String actualizarUsuario(int id, Usuario usuario) {
        if (usuarioRepository.existsById(id)) {
            Usuario actualizar = usuarioRepository.findById(id).get();
            actualizar.setUsername(usuario.getUsername());
            if (usuario.getContrasena() != null && !usuario.getContrasena().isEmpty()) {
                actualizar.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
            }
            actualizar.setNombre(usuario.getNombre());
            actualizar.setApellido(usuario.getApellido());
            actualizar.setTelefono(usuario.getTelefono());
            actualizar.setCorreo(usuario.getCorreo());
            actualizar.setRol(usuario.getRol());
            usuarioRepository.save(actualizar);
            return "Usuario actualizado con éxito";
        } else {
            return "No se encuentra el usuario";
        }
    }

    public String eliminarUsuario(int id){
        if(usuarioRepository.existsById(id)){
            usuarioRepository.deleteById(id);
            return "Usuario eliminado con exito";
        } else {
            return "No se encuentra el usuario";
        }
    }
}
