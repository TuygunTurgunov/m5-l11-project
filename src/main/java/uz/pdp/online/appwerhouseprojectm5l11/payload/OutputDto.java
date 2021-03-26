package uz.pdp.online.appwerhouseprojectm5l11.payload;

import lombok.Data;

@Data
public class OutputDto {

    private  Integer warehouseId;
    private  Integer clientId;
    private  Integer currencyId;
    private String factureNumber;

}
