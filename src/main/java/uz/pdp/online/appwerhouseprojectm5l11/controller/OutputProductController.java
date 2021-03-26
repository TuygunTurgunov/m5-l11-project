package uz.pdp.online.appwerhouseprojectm5l11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.appwerhouseprojectm5l11.entity.OutputProduct;
import uz.pdp.online.appwerhouseprojectm5l11.payload.OutputProductDto;
import uz.pdp.online.appwerhouseprojectm5l11.payload.Result;
import uz.pdp.online.appwerhouseprojectm5l11.service.OutputProductService;

@RestController
@RequestMapping("/outputProduct")
public class OutputProductController {

    @Autowired
    OutputProductService outputProductService;

    @PostMapping
    public Result add(@RequestBody OutputProductDto outputProductDto){
        Result result = outputProductService.add(outputProductDto);
        return result;
    }

    @GetMapping("/outputId/{id}")
    public Page<OutputProduct> outputProductPageByOutputId(@PathVariable Integer id,@RequestParam Integer page){

        Page<OutputProduct> outputProducts = outputProductService.outputProductsByOutputId(id, page);
        return outputProducts;

    }
    @GetMapping
    public Page<OutputProduct> outputProductPage(@RequestParam Integer page){
        Page<OutputProduct> outputProducts = outputProductService.outputProducts(page);
        return outputProducts;
    }

    @GetMapping("/{id}")
    public OutputProduct oneOutputProduct(@PathVariable Integer id){
        OutputProduct one = outputProductService.getOne(id);
        return one;
    }
    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id,@RequestBody OutputProductDto outputProductDto){
        Result result = outputProductService.edit(id, outputProductDto);
        return  result;
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        Result result = outputProductService.deleteByIdd(id);
        return result;
    }
}
