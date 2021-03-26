package uz.pdp.online.appwerhouseprojectm5l11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.online.appwerhouseprojectm5l11.entity.Supplier;
import uz.pdp.online.appwerhouseprojectm5l11.payload.Result;
import uz.pdp.online.appwerhouseprojectm5l11.repository.SupplierRepository;

import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    SupplierRepository supplierRepository;

    public Result add(Supplier supplier){
        boolean existsByPhoneNumber = supplierRepository.existsByPhoneNumber(supplier.getPhoneNumber());
        if (existsByPhoneNumber)
            return new Result("This supplier number already exists",false);

        supplierRepository.save(supplier);
        return new Result("Supplier saved",true);
    }

    public Page<Supplier> getPageSupplier(Integer page){


        Pageable pageable= PageRequest.of(page,10);
        Page<Supplier> supplierPage = supplierRepository.findAll(pageable);
        return supplierPage;
    }


    public Supplier getOneSupplier(Integer id){

        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (!optionalSupplier.isPresent())
            return null;
        return optionalSupplier.get();
    }

    public Result edit(Integer id,Supplier supplier){
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (!optionalSupplier.isPresent())
            return new Result("Supplier not found by id",false);

        boolean existsByPhoneNumber = supplierRepository.existsByPhoneNumber(supplier.getPhoneNumber());
        if (existsByPhoneNumber)
            return new Result("This phone number already exists",false);

        Supplier editSupplier = optionalSupplier.get();
        editSupplier.setPhoneNumber(supplier.getPhoneNumber());
        editSupplier.setActive(supplier.getActive());
        editSupplier.setName(supplier.getName());
        supplierRepository.save(editSupplier);
        return new Result("Supplier edited",true);

    }

    public Result delete(Integer id){
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isPresent()){
            Supplier supplier = optionalSupplier.get();
            supplier.setActive(false);
            supplierRepository.save(supplier);
            return new Result("supplier active is false",true);
        }
        return new Result("Supplier not found by id",false);
    }
}
