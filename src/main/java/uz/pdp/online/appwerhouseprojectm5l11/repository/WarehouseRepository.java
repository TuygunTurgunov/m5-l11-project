package uz.pdp.online.appwerhouseprojectm5l11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.appwerhouseprojectm5l11.entity.Warehouse;


import java.util.List;
import java.util.Set;

public interface WarehouseRepository extends JpaRepository<Warehouse,Integer> {

    boolean existsByName(String name);

    //for users
    List<Warehouse> findAllById(Iterable<Integer> iterable);

}
