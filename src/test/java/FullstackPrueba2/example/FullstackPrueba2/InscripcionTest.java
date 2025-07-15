package FullstackPrueba2.example.FullstackPrueba2;


import FullstackPrueba2.example.FullstackPrueba2.model.Inscripcion;
import FullstackPrueba2.example.FullstackPrueba2.repository.InscripcionRepository;
import FullstackPrueba2.example.FullstackPrueba2.service.InscripcionService;

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
public class InscripcionTest {

    @Mock
    private InscripcionRepository inscripcionRepository;

    @InjectMocks
    private InscripcionService inscripcionService;

    private Inscripcion inscripcionMock;

    @BeforeEach
    void simularJson() {
        MockitoAnnotations.openMocks(this);
        inscripcionMock = new Inscripcion();
        inscripcionMock.setId(1);
        inscripcionMock.setFechaInscripcion("15/07/2025");
        inscripcionMock.setCurso(null);
        inscripcionMock.setEstudiante(null);
    }

    @Test
    void testListarInscripciones() {
        when(inscripcionRepository.findAll()).thenReturn(List.of(inscripcionMock));
        List<Inscripcion> inscripciones = inscripcionService.listarInscripciones();
        assertNotNull(inscripciones);
        assertEquals(1, inscripciones.size());
        assertEquals("15/07/2025", inscripciones.get(0).getFechaInscripcion());
    }

    @Test
    void testObtenerInscripcionPorId() {
        when(inscripcionRepository.findById(1)).thenReturn(Optional.of(inscripcionMock));
        Optional<Inscripcion> inscripcion = inscripcionService.obtenerInscripcionPorId(1);
        assertTrue(inscripcion.isPresent());
        assertEquals("15/07/2025", inscripcion.get().getFechaInscripcion());
    }

    @Test
    void testAgregarInscripcion() {
        when(inscripcionRepository.save(inscripcionMock)).thenReturn(inscripcionMock);
        Inscripcion nueva = inscripcionService.agregarInscripcion(inscripcionMock);
        assertNotNull(nueva);
        assertEquals("15/07/2025", nueva.getFechaInscripcion());
        verify(inscripcionRepository, times(1)).save(inscripcionMock);
    }

    @Test
    void testEliminarInscripcion() {
        doNothing().when(inscripcionRepository).deleteById(1);
        inscripcionService.eliminarInscripcion(1);
        verify(inscripcionRepository, times(1)).deleteById(1);
    }
}
