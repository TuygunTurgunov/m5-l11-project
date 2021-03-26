package uz.pdp.online.appwerhouseprojectm5l11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.appwerhouseprojectm5l11.entity.Warehouse;
import uz.pdp.online.appwerhouseprojectm5l11.payload.Result;
import uz.pdp.online.appwerhouseprojectm5l11.service.WarehouseService;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    WarehouseService warehouseService;

    @PostMapping
    public Result post(@RequestBody Warehouse warehouse){
        Result result = warehouseService.add(warehouse);
        return result;
    }
    @GetMapping("/{id}")
    public Warehouse getOneWarehouse(@PathVariable Integer id){

        Warehouse warehouse = warehouseService.getOne(id);
        return warehouse;

    }

    @GetMapping
    public List<Warehouse> getAllWarehouse(){
        List<Warehouse> warehouseList = warehouseService.getAll();
        return warehouseList;

    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id,@RequestBody Warehouse warehouse){

        Result result = warehouseService.edit(id, warehouse);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){

        Result result = warehouseService.delete(id);
        return result;

    }

}
