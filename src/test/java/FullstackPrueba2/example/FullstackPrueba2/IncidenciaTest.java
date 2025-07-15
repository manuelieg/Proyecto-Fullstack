package FullstackPrueba2.example.FullstackPrueba2;

import FullstackPrueba2.example.FullstackPrueba2.model.Incidencia;
import FullstackPrueba2.example.FullstackPrueba2.repository.IncidenciaRepository;
import FullstackPrueba2.example.FullstackPrueba2.service.IncidenciaService;

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
public class IncidenciaTest {

    @Mock
    private IncidenciaRepository incidenciaRepository;

    @InjectMocks
    private IncidenciaService incidenciaService;

    private Incidencia incidenciaMock;

    @BeforeEach
    void simularJson() {
        MockitoAnnotations.openMocks(this);
        incidenciaMock = new Incidencia();
        incidenciaMock.setId(1);
        incidenciaMock.setDescripcion("Problemas al iniciar sesión");
        incidenciaMock.setEstado("En Proceso");
        incidenciaMock.setFechaReporte("15/07/2025");
        incidenciaMock.setUsuarioReporta(null);
    }

    @Test
    void testListarIncidencias() {
        when(incidenciaRepository.findAll()).thenReturn(List.of(incidenciaMock));
        List<Incidencia> incidencias = incidenciaService.listarIncidencias();
        assertNotNull(incidencias);
        assertEquals(1, incidencias.size());
        assertEquals("Problemas al iniciar sesión", incidencias.get(0).getDescripcion());
    }

    @Test
    void testObtenerIncidenciaPorId() {
        when(incidenciaRepository.findById(1)).thenReturn(Optional.of(incidenciaMock));
        Optional<Incidencia> incidencia = incidenciaService.obtenerIncidenciaPorId(1);
        assertTrue(incidencia.isPresent());
        assertEquals("Problemas al iniciar sesión", incidencia.get().getDescripcion());
    }

    @Test
    void testAgregarIncidencia() {
        when(incidenciaRepository.save(incidenciaMock)).thenReturn(incidenciaMock);
        Incidencia nuevo = incidenciaService.agregarIncidencia(incidenciaMock);
        assertNotNull(nuevo);
        assertEquals("Problemas al iniciar sesión", nuevo.getDescripcion());
        verify(incidenciaRepository, times(1)).save(incidenciaMock);
    }

    @Test
    void testEliminarIncidencia() {
        doNothing().when(incidenciaRepository).deleteById(1);
        incidenciaService.eliminarIncidencia(1);
        verify(incidenciaRepository, times(1)).deleteById(1);
    }
}
