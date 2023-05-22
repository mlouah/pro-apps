package com.perso.proapps.service;

import com.perso.proapps.domain.Client;
import com.perso.proapps.repository.ClientRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Client}.
 */
@Service
@Transactional
public class ClientService {

    private final Logger log = LoggerFactory.getLogger(ClientService.class);

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Save a client.
     *
     * @param client the entity to save.
     * @return the persisted entity.
     */
    public Client save(Client client) {
        log.debug("Request to save Client : {}", client);
        return clientRepository.save(client);
    }

    /**
     * Update a client.
     *
     * @param client the entity to save.
     * @return the persisted entity.
     */
    public Client update(Client client) {
        log.debug("Request to update Client : {}", client);
        return clientRepository.save(client);
    }

    /**
     * Partially update a client.
     *
     * @param client the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Client> partialUpdate(Client client) {
        log.debug("Request to partially update Client : {}", client);

        return clientRepository
            .findById(client.getId())
            .map(existingClient -> {
                if (client.getName() != null) {
                    existingClient.setName(client.getName());
                }
                if (client.getCode() != null) {
                    existingClient.setCode(client.getCode());
                }
                if (client.getClientLogo() != null) {
                    existingClient.setClientLogo(client.getClientLogo());
                }
                if (client.getClientLogoContentType() != null) {
                    existingClient.setClientLogoContentType(client.getClientLogoContentType());
                }
                if (client.getUrl() != null) {
                    existingClient.setUrl(client.getUrl());
                }
                if (client.getDateCreation() != null) {
                    existingClient.setDateCreation(client.getDateCreation());
                }
                if (client.getDateModify() != null) {
                    existingClient.setDateModify(client.getDateModify());
                }
                if (client.getLastModifyBy() != null) {
                    existingClient.setLastModifyBy(client.getLastModifyBy());
                }
                if (client.getCreatedBy() != null) {
                    existingClient.setCreatedBy(client.getCreatedBy());
                }
                if (client.getNotes() != null) {
                    existingClient.setNotes(client.getNotes());
                }

                return existingClient;
            })
            .map(clientRepository::save);
    }

    /**
     * Get all the clients.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Client> findAll() {
        log.debug("Request to get all Clients");
        return clientRepository.findAll();
    }

    /**
     * Get one client by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Client> findOne(Long id) {
        log.debug("Request to get Client : {}", id);
        return clientRepository.findById(id);
    }

    /**
     * Delete the client by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Client : {}", id);
        clientRepository.deleteById(id);
    }
}
