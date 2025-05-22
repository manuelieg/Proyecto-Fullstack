package FullstackPrueba2.example.FullstackPrueba2.service;

import FullstackPrueba2.example.FullstackPrueba2.model.CuponDescuento;
import FullstackPrueba2.example.FullstackPrueba2.repository.CuponDescuentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CuponDescuentoService {

    @Autowired
    CuponDescuentoRepository cuponDescuentoRepository;

    public String agregarCupon(CuponDescuento cupon) {
        cuponDescuentoRepository.save(cupon);
        return "Cupón agregado con éxito";
    }

    public String listarCupones() {
        String output = "";
        for(CuponDescuento cupon : cuponDescuentoRepository.findAll()) {
            output += "ID Cupón: " + cupon.getId() + "\n";
            output += "Código: " + cupon.getCodigo() + "\n";
            output += "Descuento: " + cupon.getDescuento() + "%\n";
            output += "Activo: " + cupon.isActivo() + "\n";
        }
        if(output.isEmpty()){
            return "No hay cupones";
        } else {
            return output;
        }
    }

    public String obtenerCuponPorID(int id) {
        String output = "";
        if(cuponDescuentoRepository.existsById(id)){
            CuponDescuento cupon = cuponDescuentoRepository.findById(id).get();
            output += "ID Cupón: " + cupon.getId() + "\n";
            output += "Código: " + cupon.getCodigo() + "\n";
            output += "Descuento: " + cupon.getDescuento() + "%\n";
            output += "Activo: " + cupon.isActivo() + "\n";
            return output;
        } else {
            return "No se encuentra el cupón";
        }
    }

    public String actualizarCupon(int id, CuponDescuento cupon) {
        if(cuponDescuentoRepository.existsById(id)){
            CuponDescuento actualizar = cuponDescuentoRepository.findById(id).get();
            actualizar.setCodigo(cupon.getCodigo());
            actualizar.setDescuento(cupon.getDescuento());
            actualizar.setActivo(cupon.isActivo());
            cuponDescuentoRepository.save(actualizar);
            return "Cupón actualizado con éxito";
        } else {
            return "No se encuentra el cupón";
        }
    }

    public String eliminarCupon(int id) {
        if(cuponDescuentoRepository.existsById(id)){
            cuponDescuentoRepository.deleteById(id);
            return "Cupón eliminado con éxito";
        } else {
            return "No se encuentra el cupón";
        }
    }
}