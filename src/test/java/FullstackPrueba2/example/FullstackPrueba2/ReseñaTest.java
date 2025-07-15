package FullstackPrueba2.example.FullstackPrueba2;

import FullstackPrueba2.example.FullstackPrueba2.model.Reseña;
import FullstackPrueba2.example.FullstackPrueba2.model.Usuario;
import FullstackPrueba2.example.FullstackPrueba2.model.Curso;
import FullstackPrueba2.example.FullstackPrueba2.repository.ReseñaRepository;
import FullstackPrueba2.example.FullstackPrueba2.service.ReseñaService;

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
public class ReseñaTest {

    @Mock
    private ReseñaRepository reseñaRepository;

    @InjectMocks
    private ReseñaService reseñaService;

    private Reseña reseñaMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        reseñaMock = new Reseña();
        reseñaMock.setId(1);
        reseñaMock.setCalificacion(5);
        reseñaMock.setComentario("Muy buen curso");
        reseñaMock.setFecha("15/07/2025");
        reseñaMock.setEstudiante(new Usuario());
        reseñaMock.setCurso(new Curso());
    }

    @Test
    void testListarReseñas() {
        when(reseñaRepository.findAll()).thenReturn(List.of(reseñaMock));
        List<Reseña> reseñas = reseñaService.listarReseñas();
        assertNotNull(reseñas);
        assertEquals(1, reseñas.size());
        assertEquals("Muy buen curso", reseñas.get(0).getComentario());
    }

    @Test
    void testObtenerReseñaPorId() {
        when(reseñaRepository.findById(1)).thenReturn(Optional.of(reseñaMock));
        Optional<Reseña> reseña = reseñaService.obtenerReseñaPorID(1);
        assertTrue(reseña.isPresent());
        assertEquals("Muy buen curso", reseña.get().getComentario());
    }

    @Test
    void testAgregarReseña() {
        when(reseñaRepository.save(reseñaMock)).thenReturn(reseñaMock);
        Reseña nueva = reseñaService.agregarReseña(reseñaMock);
        assertNotNull(nueva);
        assertEquals("Muy buen curso", nueva.getComentario());
        verify(reseñaRepository, times(1)).save(reseñaMock);
    }

    @Test
    void testEliminarReseña() {
        doNothing().when(reseñaRepository).deleteById(1);
        reseñaService.eliminarReseña(1);
        verify(reseñaRepository, times(1)).deleteById(1);
    }

}
