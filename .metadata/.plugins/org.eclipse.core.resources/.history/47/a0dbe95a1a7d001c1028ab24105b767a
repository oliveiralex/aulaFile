package com.example.demo.services;

import com.example.demo.dto.ClientDTO;
import com.example.demo.entities.Client;
import com.example.demo.exception.BusinessException;
import com.example.demo.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {
        var result = repository.findAll(pageRequest);
        return result.map(this::toClientDTO);
    }

    @Transactional(readOnly = true)
    public ClientDTO findBydId(Long id) {
        var result = repository.findById(id);
        var client =  result.orElseThrow(() -> new BusinessException("Entity not found", HttpStatus.NOT_FOUND.value()));
        return toClientDTO(client);
    }

    @Transactional
    public ClientDTO create(ClientDTO clientDTO) {
        var result = repository.save(toClient(clientDTO));
        return toClientDTO(result);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO clientDTO) {
        try {
            var client = repository.getOne(id);
            copyDTOToEntity(client, clientDTO);
            var result = repository.save(client);
            return toClientDTO(result);

        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }catch (Exception e) {
            throw new BusinessException("An error occurred: " + e.getMessage());
        }
    }

    private ClientDTO toClientDTO(Client client) {
        return ClientDTO.builder()
                .id(client.getId())
                .name(client.getName())
                .cpf(client.getCpf())
                .income(client.getIncome())
                .birthDate(client.getBirthDate())
                .children(client.getChildren())
                .build();
    }

    private Client toClient(ClientDTO clientDTO) {
        return Client.builder()
                .name(clientDTO.getName())
                .cpf(clientDTO.getCpf())
                .income(clientDTO.getIncome())
                .birthDate(clientDTO.getBirthDate())
                .children(clientDTO.getChildren())
                .build();
    }

    private void copyDTOToEntity(Client client, ClientDTO clientDTO) {
        client.setName(clientDTO.getName());
        client.setCpf(clientDTO.getCpf());
        client.setIncome(clientDTO.getIncome());
        client.setBirthDate(clientDTO.getBirthDate());
        client.setChildren(clientDTO.getChildren());
    }
}
