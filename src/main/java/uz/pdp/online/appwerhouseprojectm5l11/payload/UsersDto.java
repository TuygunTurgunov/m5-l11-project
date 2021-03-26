package uz.pdp.online.appwerhouseprojectm5l11.payload;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class UsersDto {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
    private List<Integer> warehouses;

}
