package com.microservices.backend.Controller;

import com.microservices.backend.Entites.client.Client;
import com.microservices.backend.Service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping()
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(clientService.getClientRepository());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        try {
            Client client1 = clientService.getClientById(id);
            if (client1 != null) {
                return ResponseEntity.ok(client1);
            }
           return ResponseEntity.notFound().build();
        }catch (Exception e) {
            throw new RuntimeException("Error");
        }
    }

    @PostMapping()
    public ResponseEntity<String> addClient(@RequestBody Client client) {
        clientService.addClient(client);
        return ResponseEntity.ok("Client added");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Long id) {
        try {
            clientService.deleteClient(id);
             return ResponseEntity.ok("Client deleted");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Client not found");
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateClient(@PathVariable Long id, @RequestBody Client client) {
        try {
            clientService.updateClient(id, client);
            return ResponseEntity.ok("Client updated");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Client not found");
        }
    }
}
