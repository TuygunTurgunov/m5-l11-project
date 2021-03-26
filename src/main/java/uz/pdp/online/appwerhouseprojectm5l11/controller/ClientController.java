package uz.pdp.online.appwerhouseprojectm5l11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.appwerhouseprojectm5l11.entity.Client;
import uz.pdp.online.appwerhouseprojectm5l11.payload.Result;
import uz.pdp.online.appwerhouseprojectm5l11.service.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    ClientService clientService;

    @PostMapping
    public Result add(@RequestBody Client client){
        Result result = clientService.add(client);
        return result;
    }

    @GetMapping
    public Page<Client> getClientPage(@RequestParam Integer page){
        Page<Client> clients = clientService.clientsPage(page);
        return clients;
    }

    @GetMapping("/{id}")
    public Client getOneClient(@PathVariable Integer id){

        Client oneClient = clientService.getOneClient(id);
        return oneClient;

    }


    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id,@RequestBody Client client){
        Result result = clientService.edit(id, client);
        return result;
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){

        Result result = clientService.delete(id);
        return result;

    }









}
