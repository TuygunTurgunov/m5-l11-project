package uz.pdp.online.appwerhouseprojectm5l11.payload;

import lombok.Data;

import java.util.Date;

@Data
public class InputProductDto {
    private Integer productId;
    private Double amount;
    private Double price;
    private Date date;
    private Integer inputId;
}
