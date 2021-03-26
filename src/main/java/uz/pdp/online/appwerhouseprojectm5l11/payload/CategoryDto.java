package uz.pdp.online.appwerhouseprojectm5l11.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private String name;
    private  Integer parentCategoryId;

}
