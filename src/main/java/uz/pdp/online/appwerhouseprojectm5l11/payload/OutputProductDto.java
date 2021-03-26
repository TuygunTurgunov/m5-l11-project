package uz.pdp.online.appwerhouseprojectm5l11.payload;

import lombok.Data;
import uz.pdp.online.appwerhouseprojectm5l11.entity.Output;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

@Data
public class OutputProductDto {
    private Integer productId;
    private Double amount;
    private Double price ;
    private Integer outputId;


}
