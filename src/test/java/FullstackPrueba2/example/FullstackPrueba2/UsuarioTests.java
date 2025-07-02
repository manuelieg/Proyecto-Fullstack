package FullstackPrueba2.example.FullstackPrueba2;

import FullstackPrueba2.example.FullstackPrueba2.controller.UsuarioController;
import FullstackPrueba2.example.FullstackPrueba2.model.Usuario;
import FullstackPrueba2.example.FullstackPrueba2.repository.UsuarioRepository;
import FullstackPrueba2.example.FullstackPrueba2.service.UsuarioService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UsuarioTests {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UsuarioService usuarioServiceMock;

    @Test
    @DisplayName("FindAll Test")
    void testUsuarioServiceMock() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        assertNotNull(usuarios);
        assertEquals(1, usuarios.size());
    }

    @Test
    @DisplayName("Rectificar username del Usuario")
    void testFindUsuario(){
        Usuario prueba = usuarioRepository.findById(1).get();
        assertNotNull(prueba);
        assertEquals("admin",prueba.getUsername());
    }

    @Test
    @DisplayName("Test controller")
    void testController()  {
        when(usuarioServiceMock.listarUsuarios()).thenReturn(List.of(new Usuario()));

        try{
            mockMvc.perform(get("/usuarios"))
                    .andExpect(status().isOk())
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            fail();
        }
    }
}
