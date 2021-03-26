package uz.pdp.online.appwerhouseprojectm5l11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.appwerhouseprojectm5l11.entity.Users;

public interface UsersRepository extends JpaRepository<Users,Integer> {

    boolean existsByPhoneNumber(String phoneNumber);




}
