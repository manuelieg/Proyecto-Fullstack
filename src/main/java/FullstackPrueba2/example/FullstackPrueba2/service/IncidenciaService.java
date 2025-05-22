package FullstackPrueba2.example.FullstackPrueba2.service;

import FullstackPrueba2.example.FullstackPrueba2.model.Incidencia;
import FullstackPrueba2.example.FullstackPrueba2.repository.IncidenciaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class IncidenciaService {

    @Autowired
    IncidenciaRepository incidenciaRepository;

    public String agregarIncidencia(Incidencia incidencia) {
        incidenciaRepository.save(incidencia);
        return "Incidencia agregada con éxito";
    }

    public String listarIncidencias() {
        String output = "";
        for(Incidencia incidencia : incidenciaRepository.findAll()) {
            output += "ID Incidencia: " + incidencia.getId() + "\n";
            output += "Descripción: " + incidencia.getDescripcion() + "\n";
            output += "Estado: " + incidencia.getEstado() + "\n";
            output += "Usuario Reporta ID: " + (incidencia.getUsuarioReporta() != null ? incidencia.getUsuarioReporta().getId() : "N/A") + "\n";
        }
        if(output.isEmpty()){
            return "No hay incidencias";
        } else {
            return output;
        }
    }

    public String obtenerIncidenciaPorID(int id) {
        String output = "";
        if(incidenciaRepository.existsById(id)){
            Incidencia incidencia = incidenciaRepository.findById(id).get();
            output += "ID Incidencia: " + incidencia.getId() + "\n";
            output += "Descripción: " + incidencia.getDescripcion() + "\n";
            output += "Estado: " + incidencia.getEstado() + "\n";
            output += "Usuario Reporta ID: " + (incidencia.getUsuarioReporta() != null ? incidencia.getUsuarioReporta().getId() : "N/A") + "\n";
            return output;
        } else {
            return "No se encuentra la incidencia";
        }
    }

    public String actualizarIncidencia(int id, Incidencia incidencia) {
        if(incidenciaRepository.existsById(id)){
            Incidencia actualizar = incidenciaRepository.findById(id).get();
            actualizar.setDescripcion(incidencia.getDescripcion());
            actualizar.setEstado(incidencia.getEstado());
            actualizar.setUsuarioReporta(incidencia.getUsuarioReporta());
            incidenciaRepository.save(actualizar);
            return "Incidencia actualizada con éxito";
        } else {
            return "No se encuentra la incidencia";
        }
    }

    public String eliminarIncidencia(int id) {
        if(incidenciaRepository.existsById(id)){
            incidenciaRepository.deleteById(id);
            return "Incidencia eliminada con éxito";
        } else {
            return "No se encuentra la incidencia";
        }
    }
}