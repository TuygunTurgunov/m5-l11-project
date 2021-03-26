package uz.pdp.online.appwerhouseprojectm5l11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.online.appwerhouseprojectm5l11.entity.Output;
import uz.pdp.online.appwerhouseprojectm5l11.entity.OutputProduct;
import uz.pdp.online.appwerhouseprojectm5l11.entity.Product;
import uz.pdp.online.appwerhouseprojectm5l11.payload.OutputProductDto;
import uz.pdp.online.appwerhouseprojectm5l11.payload.Result;
import uz.pdp.online.appwerhouseprojectm5l11.repository.OutputProductRepository;
import uz.pdp.online.appwerhouseprojectm5l11.repository.OutputRepository;
import uz.pdp.online.appwerhouseprojectm5l11.repository.ProductRepository;

import java.util.Optional;

@Service
public class OutputProductService {
    @Autowired
    OutputProductRepository outputProductRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OutputRepository outputRepository;

    public Result add(OutputProductDto outputProductDto){
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (!optionalProduct.isPresent())
            return new Result("product not found by id",false);

        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if (!optionalOutput.isPresent())
            return new Result("Output not found by id",false);

        OutputProduct outputProduct=new OutputProduct();
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setOutput(optionalOutput.get());
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProductRepository.save(outputProduct);
        return new Result("Output Product saved",true);

    }
    //Get all
    public Page<OutputProduct>outputProducts(Integer page){
        Pageable pageable= PageRequest.of(page,10);
        Page<OutputProduct> allByOutputId = outputProductRepository.findAll(pageable);
        return allByOutputId;
    }


    //Get by output id
    public Page<OutputProduct>outputProductsByOutputId(Integer id,Integer page){
        Pageable pageable= PageRequest.of(page,10);
        Page<OutputProduct> allByOutputId = outputProductRepository.findAllByOutputId(id, pageable);
        return allByOutputId;
    }

    //GET one outputProduct
    public OutputProduct  getOne(Integer id){
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (!optionalOutputProduct.isPresent())
            return null;
        return optionalOutputProduct.get();
    }

    //EDIT
    public Result edit(Integer id,OutputProductDto outputProductDto){

        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (!optionalOutputProduct.isPresent())
            return new Result("not found by id",false);

        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (!optionalProduct.isPresent())
            return new Result("product not found by id",false);

        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if (!optionalOutput.isPresent())
            return new Result("Output not found by id",false);

        OutputProduct outputProduct = optionalOutputProduct.get();
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setOutput(optionalOutput.get());
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProductRepository.save(outputProduct);
        return new Result("edited",true);

    }

    //DELETE
    public Result deleteByIdd(Integer id){
      try {
          outputProductRepository.deleteById(id);
          return new Result("deleted",true);
      }catch (Exception e ){
          return new Result("Error in deleted",false);
      }
    }






}
