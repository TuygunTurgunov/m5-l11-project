package uz.pdp.online.appwerhouseprojectm5l11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.appwerhouseprojectm5l11.entity.InputProduct;
import uz.pdp.online.appwerhouseprojectm5l11.payload.InputProductDto;
import uz.pdp.online.appwerhouseprojectm5l11.payload.Result;
import uz.pdp.online.appwerhouseprojectm5l11.service.InputProductService;

@RestController
@RequestMapping("/inputProduct")
public class InputProductController {
    @Autowired
    InputProductService inputProductService;

    @PostMapping
    public Result add(@RequestBody InputProductDto inputProductDto){

        Result result = inputProductService.add(inputProductDto);
        return  result;
    }

    @GetMapping
    public Page<InputProduct> inputProducts(@RequestParam Integer page){

        Page<InputProduct> inputProductPage = inputProductService.allInputProductPage(page);
        return inputProductPage;
    }
    @GetMapping("/inputId/{id}")
    public Page<InputProduct>inputProductPage(@PathVariable Integer id,@RequestParam Integer page){

        Page<InputProduct> inputProductPage = inputProductService.inputProductPage(page, id);
        return inputProductPage;

    }

    @GetMapping("/{id}")
    public InputProduct oneInputProduct(@PathVariable Integer id){
        InputProduct inputProduct = inputProductService.oneInputProduct(id);
        return inputProduct;
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id,@RequestBody  InputProductDto inputProductDto){

        Result result = inputProductService.edit(id, inputProductDto);
        return result;
    }

    @DeleteMapping("/{id}")
    public  Result delete(@PathVariable Integer id){
        Result result = inputProductService.delete(id);
        return result;
    }





}
