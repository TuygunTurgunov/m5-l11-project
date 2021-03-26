package uz.pdp.online.appwerhouseprojectm5l11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.appwerhouseprojectm5l11.entity.Currency;
import uz.pdp.online.appwerhouseprojectm5l11.entity.Input;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency,Integer> {
    boolean existsByName(String name);


}
