package uz.pdp.online.appwerhouseprojectm5l11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.online.appwerhouseprojectm5l11.entity.Warehouse;
import uz.pdp.online.appwerhouseprojectm5l11.payload.Result;
import uz.pdp.online.appwerhouseprojectm5l11.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {
    @Autowired
    WarehouseRepository warehouseRepository;

    public Result add(Warehouse warehouse) {
        boolean existsByName = warehouseRepository.existsByName(warehouse.getName());
        if (existsByName)
            return new Result("Warehouse already exists", false);

        warehouseRepository.save(warehouse);
        return new Result("Warehouse saved", true);
    }

    public List<Warehouse> getAll() {
        List<Warehouse> warehouseList = warehouseRepository.findAll();
        return warehouseList;
    }

    public Warehouse getOne(Integer id) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (!optionalWarehouse.isPresent())
            return null;
        return optionalWarehouse.get();
    }

    public Result edit(Integer id, Warehouse warehouse) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (!optionalWarehouse.isPresent())
            return new Result("Warehouse not found by id", false);

        boolean existsByName = warehouseRepository.existsByName(warehouse.getName());
        if (existsByName)
            return new Result("This warehouse already exists", false);

        Warehouse editWarehouse = optionalWarehouse.get();
        editWarehouse.setActive(warehouse.getActive());
        editWarehouse.setName(warehouse.getName());
        warehouseRepository.save(editWarehouse);
        return new Result("Warehouse edited", true);


    }

    public Result delete(Integer id) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (optionalWarehouse.isPresent()) {
            Warehouse warehouse = optionalWarehouse.get();
            warehouse.setActive(false);
            warehouseRepository.save(warehouse);
            return new Result("Warehouse active is false", true);
        }
        return new Result("Warehouse not found by id", false);
    }


}
