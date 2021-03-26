package uz.pdp.online.appwerhouseprojectm5l11.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.online.appwerhouseprojectm5l11.entity.Attachment;
import uz.pdp.online.appwerhouseprojectm5l11.entity.Category;
import uz.pdp.online.appwerhouseprojectm5l11.entity.Measurement;
import uz.pdp.online.appwerhouseprojectm5l11.entity.Product;
import uz.pdp.online.appwerhouseprojectm5l11.payload.GenerateCode;
import uz.pdp.online.appwerhouseprojectm5l11.payload.ProductDto;
import uz.pdp.online.appwerhouseprojectm5l11.payload.Result;
import uz.pdp.online.appwerhouseprojectm5l11.repository.AttachmentRepository;
import uz.pdp.online.appwerhouseprojectm5l11.repository.CategoryRepository;
import uz.pdp.online.appwerhouseprojectm5l11.repository.MeasurementRepository;
import uz.pdp.online.appwerhouseprojectm5l11.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    MeasurementRepository measurementRepository;

    public Result addProductService(ProductDto productDto) {


        boolean existsByNameAndCategoryId = productRepository.existsByNameAndCategoryId(productDto.getName(), productDto.getCategoryId());
        if (existsByNameAndCategoryId)
            return new Result("Bunday mahsulot ushbu categoriyada bor", false);

        //Category ni tekshirish
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new Result("Bunday kategoriya manjud emas", false);

        //Photo ni tekshirish
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (!optionalAttachment.isPresent())
            return new Result("Bunday id li rasm mavjud emas", false);

        //Measurement ni tekshirish
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (!optionalMeasurement.isPresent())
            return new Result("Bunday o'lchov birligi mavjud emas", false);


        ///Saqlash
        Product product = new Product();
        product.setName(productDto.getName());
//        product.setCode(setCode());// todo  generatsiya qilish kerak
        product.setCode(GenerateCode.setCode());// todo  generatsiya qilish kerak
        product.setCategory(optionalCategory.get());
        product.setPhoto(optionalAttachment.get());
        product.setMeasurement(optionalMeasurement.get());
        productRepository.save(product);
        return new Result("Product saved", true);

    }

//    public String setCode() {
//        String code = UUID.randomUUID().toString();
//        return code;
//    }

    public Product getOneProduct(Integer productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (!optionalProduct.isPresent())
            return null;
        return optionalProduct.get();
    }

    public Page<Product> getAllProduct(Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage;

    }
    public Page<Product> getProductByCategoryId(Integer page,Integer categoryId){

       Pageable pageable=PageRequest.of(page,10);
        Page<Product> allByCategoryId = productRepository.findAllByCategoryId(categoryId, pageable);
        return  allByCategoryId;
    }
    public Result deleteProduct(Integer id){

            Optional<Product> optionalProduct = productRepository.findById(id);
            if (optionalProduct.isPresent()){
                Product product = optionalProduct.get();
                product.setActive(false);
                productRepository.save(product);
                return new Result("Product active false",true);
            }

            return new Result("Product not found by id",false);

    }

    public Result editProduct(Integer productId,ProductDto productDto){
        //product by id
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent())
            return new Result("Product not found by id",false);

        boolean existsByNameAndCategoryId = productRepository.existsByNameAndCategoryId(productDto.getName(), productDto.getCategoryId());
        if (existsByNameAndCategoryId)
            return new Result("Bunday mahsulot ushbu categoriyada bor", false);

        //category by id
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new Result("Category not found by id",false);

        //measurement by id
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (!optionalMeasurement.isPresent())
            return new Result("Measurement not found by id",false);

        //attachment
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (!optionalAttachment.isPresent())
            return new Result("Photo not found by id",false);

        Product product = optionalProduct.get();
        product.setName(productDto.getName());
        product.setCategory(optionalCategory.get());
        product.setMeasurement(optionalMeasurement.get());
        product.setPhoto(optionalAttachment.get());
        product.setCode(GenerateCode.setCode());
        productRepository.save(product);
        return new Result("Product edited successfully",true);

    }

}