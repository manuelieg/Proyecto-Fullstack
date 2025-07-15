package FullstackPrueba2.example.FullstackPrueba2;

import FullstackPrueba2.example.FullstackPrueba2.model.Curso;
import FullstackPrueba2.example.FullstackPrueba2.repository.CursoRepository;
import FullstackPrueba2.example.FullstackPrueba2.service.CursoService;

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
import static org.mockito.Mockito.times;

@ActiveProfiles("test")
public class CursoTest {

    @Mock
    private CursoRepository cursoRepository;

    @InjectMocks
    private CursoService cursoService;

    private Curso cursoMock;

    @BeforeEach
    void simularJson() {
        MockitoAnnotations.openMocks(this);
        cursoMock = new Curso();
        cursoMock.setId(1);
        cursoMock.setTitulo("Introducción a Base de Datos");
        cursoMock.setDescripcion("Curso inicial de Base de Datos");
        cursoMock.setProfesor(null);
    }

    @Test
    void testlistarCursos() {
        when(cursoRepository.findAll()).thenReturn(List.of(cursoMock));
        List<Curso> cursos = cursoService.listarCursos();
        assertNotNull(cursos);
        assertEquals(1, cursos.size());
        assertEquals("Introducción a Base de Datos", cursos.get(0).getTitulo());
    }

    @Test
    void testObtenerCursoPorId() {
        when(cursoRepository.findById(1)).thenReturn(Optional.of(cursoMock));
        Optional<Curso> curso = cursoService.obtenerCursoPorId(1);
        assertTrue(curso.isPresent());
        assertEquals("Introducción a Base de Datos", curso.get().getTitulo());
    }

    @Test
    void testAgregarCurso() {
        when(cursoRepository.save(cursoMock)).thenReturn(cursoMock);
        Curso nuevo = cursoService.agregarCurso(cursoMock);
        assertNotNull(nuevo);
        assertEquals("Introducción a Base de Datos", nuevo.getTitulo());
        verify(cursoRepository, times(1)).save(cursoMock);
    }

    @Test
    void testEliminarCurso() {
        doNothing().when(cursoRepository).deleteById(1);
        cursoService.eliminarCurso(1);
        verify(cursoRepository, times(1)).deleteById(1);
    }


}
