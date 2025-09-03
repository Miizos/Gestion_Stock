package com.microservices.backend.Service;

import com.microservices.backend.Entites.client.Clients;
import com.microservices.backend.Repository.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Service
@AllArgsConstructor
@NoArgsConstructor
public class ClientService {
    private ClientRepository clientRepository;

    public List<Clients> getClientRepository() {
        return clientRepository.findAll();
    }
    public String addClient(Clients client) {
        clientRepository.save(client);
        return "Client added";
    }
}
