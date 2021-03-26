package uz.pdp.online.appwerhouseprojectm5l11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.online.appwerhouseprojectm5l11.entity.Input;
import uz.pdp.online.appwerhouseprojectm5l11.entity.InputProduct;
import uz.pdp.online.appwerhouseprojectm5l11.entity.Product;
import uz.pdp.online.appwerhouseprojectm5l11.payload.InputProductDto;
import uz.pdp.online.appwerhouseprojectm5l11.payload.Result;
import uz.pdp.online.appwerhouseprojectm5l11.repository.InputProductRepository;
import uz.pdp.online.appwerhouseprojectm5l11.repository.InputRepository;
import uz.pdp.online.appwerhouseprojectm5l11.repository.ProductRepository;

import java.util.Date;
import java.util.Optional;

@Service
public class InputProductService {
    @Autowired
    InputProductRepository inputProductRepository;

    @Autowired
    ProductRepository productRepository;
    @Autowired
    InputRepository inputRepository;

    public Result add(InputProductDto inputProductDto) {

        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (!optionalInput.isPresent())
            return new Result("Input id not found by id", false);

        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (!optionalProduct.isPresent())
            return new Result("Product not found by id", false);

        InputProduct inputProduct = new InputProduct();
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setInput(optionalInput.get());
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpireDate(inputProductDto.getDate());
        inputProductRepository.save(inputProduct);
        return new Result("InputProduct saved", true);

    }

    //GET all inputProduct Page
    public Page<InputProduct> allInputProductPage(Integer page) {


        Pageable pageable = PageRequest.of(page, 10);
        Page<InputProduct> inputProductPage = inputProductRepository.findAll(pageable);
        return inputProductPage;
    }


    //GET Pageable by Input id
    public Page<InputProduct> inputProductPage(Integer page, Integer inputId) {

        Pageable pageable = PageRequest.of(page, 10);
        Page<InputProduct> allByInputId = inputProductRepository.findAllByInputId(inputId, pageable);
        return allByInputId;
    }


    //GET one
    public InputProduct oneInputProduct(Integer id) {
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (optionalInputProduct.isPresent())
            return optionalInputProduct.get();
        return null;
    }

    //EDIT
    public Result edit(Integer id, InputProductDto inputProductDto) {

        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (!optionalInputProduct.isPresent())
            return new Result("InputProduct not found by id", false);

        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (!optionalInput.isPresent())
            return new Result("Input id not found by id", false);

        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (!optionalProduct.isPresent())
            return new Result("Product not found by id", false);

        InputProduct inputProduct = optionalInputProduct.get();
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setInput(optionalInput.get());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setExpireDate(inputProductDto.getDate());
        inputProductRepository.save(inputProduct);
        return new Result("edited", true);

    }

    public Result delete(Integer id) {
        try {
            inputProductRepository.deleteById(id);
            return new Result("deleted", true);

        } catch (Exception e) {
            return new Result("Error in delete ", false);
        }


    }


}
