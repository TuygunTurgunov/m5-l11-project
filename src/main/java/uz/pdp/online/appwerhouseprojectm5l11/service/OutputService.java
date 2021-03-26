package uz.pdp.online.appwerhouseprojectm5l11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.online.appwerhouseprojectm5l11.entity.*;
import uz.pdp.online.appwerhouseprojectm5l11.payload.GenerateCode;
import uz.pdp.online.appwerhouseprojectm5l11.payload.OutputDto;
import uz.pdp.online.appwerhouseprojectm5l11.payload.Result;
import uz.pdp.online.appwerhouseprojectm5l11.repository.ClientRepository;
import uz.pdp.online.appwerhouseprojectm5l11.repository.CurrencyRepository;
import uz.pdp.online.appwerhouseprojectm5l11.repository.OutputRepository;
import uz.pdp.online.appwerhouseprojectm5l11.repository.WarehouseRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OutputService {
    @Autowired
    OutputRepository outputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    //ADD
    public Result add(OutputDto outputDto){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new Result("Warehouse not found by id",false);

        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("Currency not found by id",false);

        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (!optionalClient.isPresent())
            return new Result("Client not found by id",false);


        Output output=new Output();
        output.setCode(GenerateCode.setCode());
        output.setCurrency(optionalCurrency.get());
        output.setClient(optionalClient.get());
        output.setWarehouse(optionalWarehouse.get());
        output.setFactureNumber(outputDto.getFactureNumber());
        outputRepository.save(output);
        return new Result("Output saved",true);

    }

    //EDIT
    public Result edit(Integer id,OutputDto outputDto){

        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (!optionalOutput.isPresent())
            return new Result("Output not found by id",false);

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new Result("Warehouse not found by id",false);

        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("Currency not found by id",false);

        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (!optionalClient.isPresent())
            return new Result("Client not found by id",false);

        Output output = optionalOutput.get();
        output.setFactureNumber(outputDto.getFactureNumber());
        output.setClient(optionalClient.get());
        output.setWarehouse(optionalWarehouse.get());
        output.setCurrency(optionalCurrency.get());
        output.setCode(GenerateCode.setCode());
        Timestamp timestamp=Timestamp.valueOf(LocalDateTime.now());
        output.setDateWithTime(timestamp);
        outputRepository.save(output);
        return new Result("Output edited",true);

    }

    //GET All

    public Page<Output> outputPage(Integer page){
        Pageable pageable= PageRequest.of(page,10);
        Page<Output> outputPage = outputRepository.findAll(pageable);
        return outputPage;
    }

    //GET one
    public Output oneOutput(Integer id){
        Optional<Output> outputOptional = outputRepository.findById(id);
        if (outputOptional.isPresent()){
            return outputOptional.get();
        }
        return null;
    }

    //DELETE
    public Result delete(Integer id){

        try {
            outputRepository.deleteById(id);
            return new Result("output deleted",true);
        }catch (Exception e){
            return new Result("error in delete",false);
        }
    }
}
