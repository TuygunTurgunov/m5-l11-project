package uz.pdp.online.appwerhouseprojectm5l11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.appwerhouseprojectm5l11.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

    boolean existsByName(String name);



}
