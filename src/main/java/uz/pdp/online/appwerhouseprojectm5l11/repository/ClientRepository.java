package uz.pdp.online.appwerhouseprojectm5l11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.appwerhouseprojectm5l11.entity.Client;

public interface ClientRepository extends JpaRepository<Client,Integer> {

    boolean existsByPhoneNumber(String phoneNumber);

}
