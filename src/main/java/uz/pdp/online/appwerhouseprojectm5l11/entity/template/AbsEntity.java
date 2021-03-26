package uz.pdp.online.appwerhouseprojectm5l11.entity.template;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass//Shu abstract classdan voris oloa olsin (SUPER ) degan @
public abstract class  AbsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    private String name;
    private Boolean active=true;

}
