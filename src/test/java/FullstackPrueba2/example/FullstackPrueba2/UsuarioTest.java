package FullstackPrueba2.example.FullstackPrueba2;

import FullstackPrueba2.example.FullstackPrueba2.model.Usuario;
import FullstackPrueba2.example.FullstackPrueba2.repository.UsuarioRepository;
import FullstackPrueba2.example.FullstackPrueba2.service.UsuarioService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
public class UsuarioTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuarioMock;

    @BeforeEach
    void simularJson() {
        MockitoAnnotations.openMocks(this);
        usuarioMock = new Usuario();
        usuarioMock.setId(1);
        usuarioMock.setUsername("rueditas");
        usuarioMock.setContrasena("1234");
        usuarioMock.setNombre("Freddy");
        usuarioMock.setApellido("Turbina");
        usuarioMock.setTelefono("123456789");
        usuarioMock.setCorreo("rueditas@31minutos.cl");
        usuarioMock.setRol(null);
    }

    @Test
    void testListarUsuarios() {
        when(usuarioRepository.findAll()).thenReturn(List.of(usuarioMock));
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        assertNotNull(usuarios);
        assertEquals(1, usuarios.size());
        assertEquals("Freddy", usuarios.get(0).getNombre());
    }

    @Test
    void testObtenerUsuarioPorId() {
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuarioMock));
        Optional<Usuario> usuario = usuarioService.obtenerUsuarioPorID(1);
        assertTrue(usuario.isPresent());
        assertEquals("Freddy", usuario.get().getNombre());
    }

    @Test
    void testAgregarUsuario() {
        when(usuarioRepository.save(usuarioMock)).thenReturn(usuarioMock);
        Usuario nuevo = usuarioService.agregarUsuario(usuarioMock);
        assertNotNull(nuevo);
        assertEquals("Freddy", nuevo.getNombre());
        verify(usuarioRepository, times(1)).save(usuarioMock);
    }

    @Test
    void testEliminarUsuario() {
        doNothing().when(usuarioRepository).deleteById(1);
        usuarioService.eliminarUsuario(1);
        verify(usuarioRepository, times(1)).deleteById(1);
    }
}
