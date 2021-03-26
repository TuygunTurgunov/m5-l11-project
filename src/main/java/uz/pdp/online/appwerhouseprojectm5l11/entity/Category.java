package uz.pdp.online.appwerhouseprojectm5l11.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.online.appwerhouseprojectm5l11.entity.template.AbsEntity;
import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)//Extend bo'gan class dan foydalanish
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Category extends AbsEntity {
    @ManyToOne
    private  Category parentCategory;//null, 1, 1, ,1


/////////////////////////////////////////////////////////////////////////////////
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private  Integer id;//1, 10, 20,30

//    private String name;//Electronica,Telephone, PprtotiveTechnic,AudioVideoTech

//    private Boolean active;//true, true, true, true,




}
