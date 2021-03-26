package uz.pdp.online.appwerhouseprojectm5l11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.appwerhouseprojectm5l11.entity.Product;
import uz.pdp.online.appwerhouseprojectm5l11.payload.ProductDto;
import uz.pdp.online.appwerhouseprojectm5l11.payload.Result;
import uz.pdp.online.appwerhouseprojectm5l11.service.ProductService;


@RestController
@RequestMapping("/product")
public class ProductController {
   @Autowired
   ProductService productService;


    @PostMapping
    public Result addProductController(@RequestBody ProductDto productDto){

        Result result = productService.addProductService(productDto);
        return result;
    }
    @GetMapping
    public Page<Product> getAllProductPage(@RequestParam Integer page){
        Page<Product> allProduct = productService.getAllProduct(page);
        return allProduct;
    }
    @GetMapping("/categoryId/{id}")
    public Page<Product>getByCatId(@PathVariable Integer id , @RequestParam Integer page){
        Page<Product> productByCategoryId = productService.getProductByCategoryId(page, id);
        return productByCategoryId;

    }
    @GetMapping("/productId/{id}")
    public Product getOneProduct(@PathVariable Integer id){
        Product product = productService.getOneProduct(id);
        return product;
    }
    @PutMapping("/{id}")
    public Result editProduct(@PathVariable Integer id,@RequestBody ProductDto productDto){
        Result result = productService.editProduct(id, productDto);
        return result;

    }
    @DeleteMapping("/{id}")
    public Result deleteProduct(@PathVariable Integer id){
        Result result = productService.deleteProduct(id);
        return result;
    }
}