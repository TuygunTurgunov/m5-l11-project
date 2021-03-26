package uz.pdp.online.appwerhouseprojectm5l11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.online.appwerhouseprojectm5l11.entity.Measurement;
@Repository//qoysak ham qo'ymasak ham bir hil Jpa ni ichida bor
public interface MeasurementRepository extends JpaRepository<Measurement,Integer> {
    boolean existsByName(String name);


}
