package uz.pdp.online.appwerhouseprojectm5l11.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.online.appwerhouseprojectm5l11.entity.Users;
import uz.pdp.online.appwerhouseprojectm5l11.entity.Warehouse;
import uz.pdp.online.appwerhouseprojectm5l11.payload.GenerateCode;
import uz.pdp.online.appwerhouseprojectm5l11.payload.Result;
import uz.pdp.online.appwerhouseprojectm5l11.payload.UsersDto;
import uz.pdp.online.appwerhouseprojectm5l11.repository.UsersRepository;
import uz.pdp.online.appwerhouseprojectm5l11.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UsersService {
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    WarehouseRepository warehouseRepository;

    public Result add(UsersDto usersDto){
        boolean existsByPhoneNumber = usersRepository.existsByPhoneNumber(usersDto.getPhoneNumber());
        if (existsByPhoneNumber)
            return new Result("Such kind of telephone number already exists",false);

        Users users=new Users();
        users.setCode(GenerateCode.setCode());
        users.setFirstName(usersDto.getFirstName());
        users.setLastName(usersDto.getLastName());
        users.setPhoneNumber(usersDto.getPhoneNumber());
        users.setPassword(usersDto.getPassword());
        List<Warehouse> warehouseSet = warehouseRepository.findAllById(usersDto.getWarehouses());
        users.setWarehouses(warehouseSet);
        usersRepository.save(users);
        List<Warehouse> all = warehouseRepository.findAll();
        return new Result("User saved",true);

    }

    //GET ALL USER BY PAGE
    public Page<Users> usersPage(Integer page){
        Pageable pageable= PageRequest.of(page,10);

        Page<Users> usersPage = usersRepository.findAll(pageable);
        return usersPage;


    }

    //GET ONE USER BY ID
    public Users getOneUser(Integer id){
        Optional<Users> optionalUsers = usersRepository.findById(id);
        if (!optionalUsers.isPresent())
            return null;
        return optionalUsers.get();
    }

    //EDIT USER

    public Result edit(Integer id,UsersDto usersDto){
        Optional<Users> optionalUsers = usersRepository.findById(id);
        if (!optionalUsers.isPresent())
            return new Result("User not found by id",false);

        boolean existsByPhoneNumber = usersRepository.existsByPhoneNumber(usersDto.getPhoneNumber());
        if (existsByPhoneNumber)
            return new Result("This phone number already exists",false);

        Users users = optionalUsers.get();
        List<Warehouse> warehouseSet = warehouseRepository.findAllById(usersDto.getWarehouses());
        users.setWarehouses(warehouseSet);
        users.setCode(GenerateCode.setCode());
        users.setFirstName(usersDto.getFirstName());
        users.setLastName(usersDto.getLastName());
        users.setPhoneNumber(usersDto.getPhoneNumber());
        usersRepository.save(users);
        return new Result("User edited",true);


    }

    //Delete

    public Result delete(Integer id){
        Optional<Users> optionalUsers = usersRepository.findById(id);
        if (optionalUsers.isPresent()){
            Users users = optionalUsers.get();
            users.setActive(false);
            usersRepository.save(users);
            return new Result("User active is false",true);
        }

        return new Result("Uer not found by id",false);
    }






}
