package uz.pdp.online.appwerhouseprojectm5l11.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.appwerhouseprojectm5l11.entity.Supplier;
import uz.pdp.online.appwerhouseprojectm5l11.payload.Result;
import uz.pdp.online.appwerhouseprojectm5l11.service.SupplierService;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    SupplierService supplierService;


    @PostMapping
    public Result add(@RequestBody Supplier supplier){
        Result result = supplierService.add(supplier);
        return  result;
    }

    @GetMapping
    public Page<Supplier>getSupplierPage(@RequestParam Integer page){
        Page<Supplier> pageSupplier = supplierService.getPageSupplier(page);
        return pageSupplier;
    }


    @GetMapping("/{id}")
    public Supplier getOneSupplier(@PathVariable Integer id){
        Supplier oneSupplier = supplierService.getOneSupplier(id);
        return oneSupplier;
    }


    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id,@RequestBody Supplier supplier){
        Result result = supplierService.edit(id, supplier);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        Result result = supplierService.delete(id);
        return result;

    }







}
