package uz.pdp.online.appwerhouseprojectm5l11.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.appwerhouseprojectm5l11.entity.InputProduct;
import uz.pdp.online.appwerhouseprojectm5l11.entity.Product;

public interface InputProductRepository extends JpaRepository<InputProduct,Integer> {

    Page<InputProduct>findAllByInputId(Integer input_id, Pageable pageable);
}
