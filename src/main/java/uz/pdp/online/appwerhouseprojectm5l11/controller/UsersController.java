package uz.pdp.online.appwerhouseprojectm5l11.controller;

import org.hibernate.usertype.UserVersionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.appwerhouseprojectm5l11.entity.Users;
import uz.pdp.online.appwerhouseprojectm5l11.payload.Result;
import uz.pdp.online.appwerhouseprojectm5l11.payload.UsersDto;
import uz.pdp.online.appwerhouseprojectm5l11.service.UsersService;



@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UsersService usersService;

    @PostMapping
    public Result add(@RequestBody UsersDto usersDto) {
        Result result = usersService.add(usersDto);
        return result;
    }

    @GetMapping
    public Page<Users>getUsersPage(@RequestParam Integer page){
        Page<Users> usersPage = usersService.usersPage(page);
        return usersPage;
    }


    @GetMapping("/{id}")
    public Users getOneUser(@PathVariable Integer id){

        Users oneUser = usersService.getOneUser(id);
        return oneUser;
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id,@RequestBody UsersDto usersDto){

        Result result = usersService.edit(id, usersDto);
        return result;

    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){

        Result result = usersService.delete(id);
        return result;

    }

}