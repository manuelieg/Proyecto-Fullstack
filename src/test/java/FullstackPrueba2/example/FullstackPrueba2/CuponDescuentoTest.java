package FullstackPrueba2.example.FullstackPrueba2;

import FullstackPrueba2.example.FullstackPrueba2.model.CuponDescuento;
import FullstackPrueba2.example.FullstackPrueba2.repository.CuponDescuentoRepository;
import FullstackPrueba2.example.FullstackPrueba2.service.CuponDescuentoService;

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
public class CuponDescuentoTest {

    @Mock
    private CuponDescuentoRepository cuponDescuentoRepository;

    @InjectMocks
    private CuponDescuentoService cuponDescuentoService;

    private CuponDescuento cuponMock;

    @BeforeEach
    void simularJson() {
        MockitoAnnotations.openMocks(this);
        cuponMock = new CuponDescuento();
        cuponMock.setId(1);
        cuponMock.setCodigo("EDU10TECH");
        cuponMock.setDescuento(10.0);
        cuponMock.setFechaExpiracion("15/07/2025");
        cuponMock.setActivo(true);
    }

    @Test
    void testListarCupones() {
        when(cuponDescuentoRepository.findAll()).thenReturn(List.of(cuponMock));
        List<CuponDescuento> cupones = cuponDescuentoService.listarCupones();
        assertNotNull(cupones);
        assertEquals(1, cupones.size());
        assertEquals("EDU10TECH", cupones.get(0).getCodigo());
    }

    @Test
    void testObtenerCuponPorId() {
        when(cuponDescuentoRepository.findById(1)).thenReturn(Optional.of(cuponMock));
        Optional<CuponDescuento> cupon = cuponDescuentoService.obtenerCuponPorID(1);
        assertTrue(cupon.isPresent());
        assertEquals("EDU10TECH", cupon.get().getCodigo());
    }

    @Test
    void testAgregarCupon() {
        when(cuponDescuentoRepository.save(cuponMock)).thenReturn(cuponMock);
        CuponDescuento nuevo = cuponDescuentoService.agregarCupon(cuponMock);
        assertNotNull(nuevo);
        assertEquals("EDU10TECH", nuevo.getCodigo());
        verify(cuponDescuentoRepository, times(1)).save(cuponMock);
    }

    @Test
    void testEliminarCupon() {
        doNothing().when(cuponDescuentoRepository).deleteById(1);
        cuponDescuentoService.eliminarCupon(1);
        verify(cuponDescuentoRepository, times(1)).deleteById(1);
    }
}
