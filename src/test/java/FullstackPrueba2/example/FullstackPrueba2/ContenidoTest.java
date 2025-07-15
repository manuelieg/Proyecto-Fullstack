package FullstackPrueba2.example.FullstackPrueba2;

import FullstackPrueba2.example.FullstackPrueba2.model.Contenido;
import FullstackPrueba2.example.FullstackPrueba2.repository.ContenidoRepository;
import FullstackPrueba2.example.FullstackPrueba2.service.ContenidoService;

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
public class ContenidoTest {
    @Mock
    private ContenidoRepository contenidoRepository;

    @InjectMocks
    private ContenidoService contenidoService;

    private Contenido contenidoMock;

    @BeforeEach
    void simularJson() {
        MockitoAnnotations.openMocks(this);
        contenidoMock = new Contenido();
        contenidoMock.setId(1);
        contenidoMock.setTitulo("Modelo Entidad-Relacion");
        contenidoMock.setDescripcion("Entender la importancia del modelo Entidad-Relacion en BDD");
        contenidoMock.setUrl("https://www.modelo-entidad-Relacion");
        contenidoMock.setCurso(null);
    }

    @Test
    void testListarContenidos() {
        when(contenidoRepository.findAll()).thenReturn(List.of(contenidoMock));
        List<Contenido> contenidos = contenidoService.listarContenidos();
        assertNotNull(contenidos);
        assertEquals(1, contenidos.size());
        assertEquals("Modelo Entidad-Relacion", contenidos.get(0).getTitulo());
    }

    @Test
    void testObtenerContenidoPorId() {
        when(contenidoRepository.findById(1)).thenReturn(Optional.of(contenidoMock));
        Optional<Contenido> contenido = contenidoService.obtenerContenidoPorID(1);
        assertTrue(contenido.isPresent());
        assertEquals("Modelo Entidad-Relacion", contenido.get().getTitulo());
    }

    @Test
    void testAgregarContenido() {
        when(contenidoRepository.save(contenidoMock)).thenReturn(contenidoMock);
        Contenido nuevo = contenidoService.agregarContenido(contenidoMock);
        assertNotNull(nuevo);
        assertEquals("Modelo Entidad-Relacion", nuevo.getTitulo());
        verify(contenidoRepository, times(1)).save(contenidoMock);
    }

    @Test
    void testEliminarContenido() {
        doNothing().when(contenidoRepository).deleteById(1);
        contenidoService.eliminarContenido(1);
        verify(contenidoRepository, times(1)).deleteById(1);
    }
}
