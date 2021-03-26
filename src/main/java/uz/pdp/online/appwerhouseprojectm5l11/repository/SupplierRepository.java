package uz.pdp.online.appwerhouseprojectm5l11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.appwerhouseprojectm5l11.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier,Integer> {
    boolean existsByPhoneNumber(String phoneNumber);
}
