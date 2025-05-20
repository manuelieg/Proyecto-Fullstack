package FullstackPrueba2.example.FullstackPrueba2.service;

import FullstackPrueba2.example.FullstackPrueba2.model.Usuario;
import FullstackPrueba2.example.FullstackPrueba2.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public String agregarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
        return "Usuario agregado con exito";
    }

    public String listarUsuarios() {
        String output = "";
        for(Usuario usuario:usuarioRepository.findAll()){
            output += "ID Usuario: "+usuario.getId()+"\n";
            output += "Rol: "+usuario.getRol()+"\n";
            output += "Username: "+usuario.getUsername()+"\n";
            output += "Contrasena: "+usuario.getContrasena()+"\n";
            output += "Correo: "+usuario.getCorreo()+"\n";
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
            output += "ID Usuario: "+usuario.getId()+"\n";
            output += "Rol: "+usuario.getRol()+"\n";
            output += "Username: "+usuario.getUsername()+"\n";
            output += "Contrasena: "+usuario.getContrasena()+"\n";
            output += "Correo: "+usuario.getCorreo()+"\n";
            return output;
        } else {
            return "No se encuentra el usuario";
        }
    }

    public String actualizarUsuario(int id,Usuario usuario){
        if(usuarioRepository.existsById(id)){
            Usuario actualizar =  usuarioRepository.findById(id).get();
            actualizar.setUsername(usuario.getUsername());
            actualizar.setRol(usuario.getRol());
            actualizar.setContrasena(usuario.getContrasena());
            actualizar.setCorreo(usuario.getCorreo());
            usuarioRepository.save(actualizar);
            return "Usuario actualizado con exito";
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
