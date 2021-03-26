package uz.pdp.online.appwerhouseprojectm5l11.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.appwerhouseprojectm5l11.entity.OutputProduct;

public interface OutputProductRepository extends JpaRepository<OutputProduct,Integer> {

    Page<OutputProduct>findAllByOutputId(Integer output_id, Pageable pageable);

}
