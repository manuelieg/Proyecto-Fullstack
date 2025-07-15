package FullstackPrueba2.example.FullstackPrueba2;

import FullstackPrueba2.example.FullstackPrueba2.model.Evaluacion;
import FullstackPrueba2.example.FullstackPrueba2.repository.EvaluacionRepository;
import FullstackPrueba2.example.FullstackPrueba2.service.EvaluacionService;

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
public class EvaluacionTest {
    @Mock
    private EvaluacionRepository evaluacionRepository;

    @InjectMocks
    private EvaluacionService evaluacionService;

    private Evaluacion evaluacionMock;

    @BeforeEach
    void simularJson() {
        MockitoAnnotations.openMocks(this);
        evaluacionMock = new Evaluacion();
        evaluacionMock.setId(1);
        evaluacionMock.setTitulo("Evaluacion Modelo E-R");
        evaluacionMock.setDescripcion("Evaluar conocimientos sobre el modelo Entidad-Relacion");
        evaluacionMock.setFechaInicio("14/07/2025");
        evaluacionMock.setFechaFin("15/07/2025");
        evaluacionMock.setCurso(null);
        evaluacionMock.setProfesor(null);
    }

    @Test
    void testListarEvaluaciones() {
        when(evaluacionRepository.findAll()).thenReturn(List.of(evaluacionMock));
        List<Evaluacion> evaluaciones = evaluacionService.listarEvaluaciones();
        assertNotNull(evaluaciones);
        assertEquals(1, evaluaciones.size());
        assertEquals("Evaluacion Modelo E-R", evaluaciones.get(0).getTitulo());
    }

    @Test
    void testObtenerEvaluacionPorId() {
        when(evaluacionRepository.findById(1)).thenReturn(Optional.of(evaluacionMock));
        Optional<Evaluacion> evaluacion = evaluacionService.obtenerEvaluacionPorId(1);
        assertTrue(evaluacion.isPresent());
        assertEquals("Evaluacion Modelo E-R", evaluacion.get().getTitulo());
    }

    @Test
    void testAgregarEvaluacion() {
        when(evaluacionRepository.save(evaluacionMock)).thenReturn(evaluacionMock);
        Evaluacion nuevo = evaluacionService.agregarEvaluacion(evaluacionMock);
        assertNotNull(nuevo);
        assertEquals("Evaluacion Modelo E-R", nuevo.getTitulo());
        verify(evaluacionRepository, times(1)).save(evaluacionMock);
    }

    @Test
    void testEliminarEvaluacion() {
        doNothing().when(evaluacionRepository).deleteById(1);
        evaluacionService.eliminarEvaluacion(1);
        verify(evaluacionRepository, times(1)).deleteById(1);
    }
}
