package uz.pdp.online.appwerhouseprojectm5l11.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.online.appwerhouseprojectm5l11.entity.template.AbsEntity;

import javax.persistence.Entity;
@EqualsAndHashCode(callSuper = true)////Extends bo'gan (AbsEntity )class dan foydalanish
@Data
@Entity
public class Warehouse extends AbsEntity {

}
