package FullstackPrueba2.example.FullstackPrueba2.service;

import FullstackPrueba2.example.FullstackPrueba2.model.CuponDescuento;
import FullstackPrueba2.example.FullstackPrueba2.repository.CuponDescuentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CuponDescuentoService {

    @Autowired
    private CuponDescuentoRepository cuponDescuentoRepository;

    public List<CuponDescuento> listarCupones() {
        return cuponDescuentoRepository.findAll();
    }

    public CuponDescuento agregarCupon(CuponDescuento cupon) {
        return cuponDescuentoRepository.save(cupon);
    }

    public Optional<CuponDescuento> obtenerCuponPorID(int id) {
        return cuponDescuentoRepository.findById(id);
    }

    public void actualizarCupon(int id, CuponDescuento cupon) {
        if (cuponDescuentoRepository.existsById(id)) {
            CuponDescuento actualizar = cuponDescuentoRepository.findById(id).get();
            actualizar.setCodigo(cupon.getCodigo());
            actualizar.setDescuento(cupon.getDescuento());
            actualizar.setFechaExpiracion(cupon.getFechaExpiracion());
            actualizar.setActivo(cupon.isActivo());
            cuponDescuentoRepository.save(actualizar);
        }
    }

    public void eliminarCupon(int id) {
        cuponDescuentoRepository.deleteById(id);
    }
}