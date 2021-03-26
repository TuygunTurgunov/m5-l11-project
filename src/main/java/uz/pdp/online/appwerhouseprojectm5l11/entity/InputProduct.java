package uz.pdp.online.appwerhouseprojectm5l11.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class InputProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private  Product product;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private Double price ;

    private Date expireDate;

    @ManyToOne
    private Input input;

}
