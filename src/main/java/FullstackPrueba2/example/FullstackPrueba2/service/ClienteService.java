package FullstackPrueba2.example.FullstackPrueba2.service;

import FullstackPrueba2.example.FullstackPrueba2.model.Cliente;
import FullstackPrueba2.example.FullstackPrueba2.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public String agregarCliente(Cliente cliente) {
        clienteRepository.save(cliente);
        return "Cliente agregado con éxito";
    }

    public String listarClientes() {
        String output = "";
        for (Cliente cliente : clienteRepository.findAll()) {
            output += "ID Cliente: " + cliente.getId() + "\n";
            output += "Nombre: " + cliente.getNombre() + "\n";
            output += "Apellido: " + cliente.getApellido() + "\n";
            output += "Correo: " + cliente.getCorreo() + "\n";
            output += "Teléfono: " + cliente.getTelefono() + "\n";
        }

        if(output.isEmpty()){
            return "No hay clientes";
        } else {
            return output;
        }
    }

    public String obtenerClientePorID(int id) {
        String output = "";
        if (clienteRepository.existsById(id)) {
            Cliente cliente = clienteRepository.findById(id).get();
            output += "ID Cliente: " + cliente.getId() + "\n";
            output += "Nombre: " + cliente.getNombre() + "\n";
            output += "Apellido: " + cliente.getApellido() + "\n";
            output += "Correo: " + cliente.getCorreo() + "\n";
            output += "Teléfono: " + cliente.getTelefono() + "\n";
            return output;
        } else {
            return "No se encuentra cliente";
        }
    }

    public String actualizarCliente(int id, Cliente cliente) {
        if (clienteRepository.existsById(id)) {
            Cliente actualizar = clienteRepository.findById(id).get();
            actualizar.setNombre(cliente.getNombre());
            actualizar.setApellido(cliente.getApellido());
            actualizar.setCorreo(cliente.getCorreo());
            actualizar.setTelefono(cliente.getTelefono());
            clienteRepository.save(actualizar);
            return "Cliente actualizado con éxito";
        } else {
            return "Cliente no encontrado";
        }
    }

    public String eliminarCliente(int id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return "Cliente eliminado con éxito";
        } else {
            return "Cliente no encontrado";
        }
    }
}