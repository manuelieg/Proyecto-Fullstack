package FullstackPrueba2.example.FullstackPrueba2.controller;

import FullstackPrueba2.example.FullstackPrueba2.model.Cliente;
import FullstackPrueba2.example.FullstackPrueba2.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String getCliente() {
        return clienteService.listarClientes();
    }

    @PostMapping
    public String postCliente(@RequestBody Cliente cliente) {
        return clienteService.agregarCliente(cliente);
    }

    @GetMapping("/{id}")
    public String getClienteById(@PathVariable int id) {
        return clienteService.obtenerClientePorID(id);
    }

    @DeleteMapping("/{id}")
    public String deleteCliente(@PathVariable int id) {
        return clienteService.eliminarCliente(id);
    }

    @PutMapping("/{id}")
    public String updteCliente(@PathVariable int id, @RequestBody Cliente cliente) {
        return clienteService.actualizarCliente(id, cliente);
    }
}