package uz.pdp.online.appwerhouseprojectm5l11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import uz.pdp.online.appwerhouseprojectm5l11.entity.Category;
import uz.pdp.online.appwerhouseprojectm5l11.entity.Measurement;
import uz.pdp.online.appwerhouseprojectm5l11.payload.CategoryDto;
import uz.pdp.online.appwerhouseprojectm5l11.payload.Result;
import uz.pdp.online.appwerhouseprojectm5l11.repository.CategoryRepository;
import uz.pdp.online.appwerhouseprojectm5l11.repository.MeasurementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public Result addCategoryService(CategoryDto categoryDto){
        boolean existsByName = categoryRepository.existsByName(categoryDto.getName());
        if (existsByName)
            return new Result("This category already exists",false);

        Category category=new Category();
        category.setName(categoryDto.getName());

        if (categoryDto.getParentCategoryId()!=null){
            Optional<Category> optionalCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (!optionalCategory.isPresent()){
            return new Result("Bunday ota kategoriya mavjud emas",false);
            }

            category.setParentCategory(optionalCategory.get());
        }
        categoryRepository.save(category);
        return new Result("Category successfully saved",true);

    }

    public List<Category>getCategory(){
        List<Category> all = categoryRepository.findAll();
        return all;

    }
    public Category getCategoryById(Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()){
            return null;
        }
        return optionalCategory.get();
    }

    public Result editCategory (Integer id,CategoryDto categoryDto){

        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent())
            return new Result("Category not found by id",false);

        Category category = optionalCategory.get();

        boolean existsByName = categoryRepository.existsByName(categoryDto.getName());
        if (existsByName)
            return new Result("This parent category already exists",false);
        category.setName(categoryDto.getName());


        if (categoryDto.getParentCategoryId()!=null){
            Optional<Category> optionalCategory1 = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (!optionalCategory.isPresent())
                return new Result("This Parent category not found by id",false);


            category.setParentCategory(optionalCategory1.get());

        }
        categoryRepository.save(category);
        return new Result("Category successfully edited",true);
    }

    public Result deleteCategory(Integer id){

        try {
            categoryRepository.deleteById(id);
            return new Result("Category deleted",true);
        }catch (Exception e){
            return new Result("Category not found by id",false);
        }


    }
}