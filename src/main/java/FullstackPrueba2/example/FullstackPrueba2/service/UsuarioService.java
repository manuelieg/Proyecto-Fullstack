package FullstackPrueba2.example.FullstackPrueba2.service;

import FullstackPrueba2.example.FullstackPrueba2.model.Usuario;
import FullstackPrueba2.example.FullstackPrueba2.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario agregarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> obtenerUsuarioPorID(int id) {
        return usuarioRepository.findById(id);
    }

    public void actualizarUsuario(int id, Usuario usuario) {
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
        }
    }

    public void eliminarUsuario(int id) {
        usuarioRepository.deleteById(id);
    }
}