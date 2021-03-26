package uz.pdp.online.appwerhouseprojectm5l11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.online.appwerhouseprojectm5l11.entity.Client;
import uz.pdp.online.appwerhouseprojectm5l11.payload.Result;
import uz.pdp.online.appwerhouseprojectm5l11.repository.ClientRepository;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;


    public Result add(Client client) {
        boolean existsByPhoneNumber = clientRepository.existsByPhoneNumber(client.getPhoneNumber());
        if (existsByPhoneNumber)
            return new Result("This phone number is already exists", false);

        clientRepository.save(client);
        return new Result("Client saved", true);
    }

    public Page<Client> clientsPage(Integer page) {

        Pageable pageable = PageRequest.of(page, 10);
        Page<Client> clientPage = clientRepository.findAll(pageable);
        return clientPage;
    }

    public Client getOneClient(Integer id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (!optionalClient.isPresent())
            return null;

        return optionalClient.get();
    }

    public Result edit(Integer id, Client client) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (!optionalClient.isPresent())
            return new Result("Client not found by id", false);

        boolean existsByPhoneNumber = clientRepository.existsByPhoneNumber(client.getPhoneNumber());
        if (existsByPhoneNumber)
            return new Result("this phone number already exists", false);

        Client editClient = optionalClient.get();
        editClient.setPhoneNumber(client.getPhoneNumber());
        editClient.setActive(client.getActive());
        editClient.setName(client.getName());
        clientRepository.save(editClient);
        return new Result("Client edited", true);


    }

    public Result delete(Integer id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            client.setActive(false);
            clientRepository.save(client);
            return new Result("Client active is false", true);
        }
        return new Result("Client not found by id", false);

    }
}
