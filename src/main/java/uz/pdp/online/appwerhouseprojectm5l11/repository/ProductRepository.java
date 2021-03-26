package uz.pdp.online.appwerhouseprojectm5l11.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.appwerhouseprojectm5l11.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    boolean existsByNameAndCategoryId(String name, Integer category_id);
    Page<Product>findAllByCategoryId(Integer category_id, Pageable pageable);




}
