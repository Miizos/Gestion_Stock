package com.microservices.backend.Service;

import com.microservices.backend.Entites.client.Client;
import com.microservices.backend.Repository.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getClientRepository() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        Client client = clientRepository.findById(id).orElse(null);
        return client;
    }

    public String addClient(Client client) {
        clientRepository.save(client);
        return "Client added";
    }

    public String deleteClient(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        if(client.isPresent()) {
            clientRepository.deleteById(id);
            return "Client deleted";
        }
        return "Client not found";
    }
    public String updateClient(Long id, Client client) {
        Client client1 = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        client1.setLastName(client.getLastName());
        client1.setAddress(client.getAddress());
        client1.setPhone(client.getPhone());
        client1.setEmail(client.getEmail());
        client1.setIce(client.getIce());
        client1.setEtat(client.getEtat());
        clientRepository.save(client1);
        return "Client updated";
    }
}
