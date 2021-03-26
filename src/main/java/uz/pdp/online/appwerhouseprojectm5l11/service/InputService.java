package uz.pdp.online.appwerhouseprojectm5l11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.online.appwerhouseprojectm5l11.entity.Currency;
import uz.pdp.online.appwerhouseprojectm5l11.entity.Input;
import uz.pdp.online.appwerhouseprojectm5l11.entity.Supplier;
import uz.pdp.online.appwerhouseprojectm5l11.entity.Warehouse;
import uz.pdp.online.appwerhouseprojectm5l11.payload.GenerateCode;
import uz.pdp.online.appwerhouseprojectm5l11.payload.InputDto;
import uz.pdp.online.appwerhouseprojectm5l11.payload.Result;
import uz.pdp.online.appwerhouseprojectm5l11.repository.CurrencyRepository;
import uz.pdp.online.appwerhouseprojectm5l11.repository.InputRepository;
import uz.pdp.online.appwerhouseprojectm5l11.repository.SupplierRepository;
import uz.pdp.online.appwerhouseprojectm5l11.repository.WarehouseRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class InputService {
    @Autowired
    InputRepository inputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    SupplierRepository supplierRepository;

    public Result add(InputDto inputDto){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new Result("Warehouse not found by id",false);

        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("Currency not found by id",false);

        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (!optionalSupplier.isPresent())
            return new Result("Supplier not found by id",false);

        Input input=new Input();
        input.setCode(GenerateCode.setCode());
        input.setCurrency(optionalCurrency.get());
        input.setFactureNumber(inputDto.getFactureNumber());
        input.setSupplier(optionalSupplier.get());
        input.setWarehouse(optionalWarehouse.get());
        inputRepository.save(input);
        return new Result("Input successfully saved",true);
    }

    //GET INPUT PAGE
    public Page<Input> getInputPage(Integer page){
        Pageable pageable= PageRequest.of(page,10);
        Page<Input> inputPage = inputRepository.findAll(pageable);
        return inputPage;
    }

    //GET ONE INPUT
    public Input getOneInput(Integer id){
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (optionalInput.isPresent())
            return optionalInput.get();

        return null;
    }


    //EDIT
    public Result edit(Integer id,InputDto inputDto){

        Optional<Input> optionalInput = inputRepository.findById(id);
        if (!optionalInput.isPresent())
            return new Result("Input not found by id",false);

        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (!optionalSupplier.isPresent())
            return new Result("Supplier not found by id",false);

        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("Currency not found bu id",false);

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new Result("Warehouse not found by id",false);

        Input input = optionalInput.get();
        input.setWarehouse(optionalWarehouse.get());
        input.setCurrency(optionalCurrency.get());
        input.setSupplier(optionalSupplier.get());
        input.setFactureNumber(inputDto.getFactureNumber());
        input.setCode(GenerateCode.setCode());
        Timestamp timestamp=Timestamp.valueOf(LocalDateTime.now());
        input.setDateWithTime(timestamp);
        inputRepository.save(input);
        return new Result("Input edited",true);
    }
    public Result delete(Integer id){
        try {
            inputRepository.deleteById(id);
            return new Result("deleted",true);
        }catch (Exception e){
            return new Result("Error in deleted",false);
        }


    }






}
