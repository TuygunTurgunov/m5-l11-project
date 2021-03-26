package uz.pdp.online.appwerhouseprojectm5l11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.appwerhouseprojectm5l11.entity.Category;
import uz.pdp.online.appwerhouseprojectm5l11.payload.CategoryDto;
import uz.pdp.online.appwerhouseprojectm5l11.payload.Result;
import uz.pdp.online.appwerhouseprojectm5l11.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired

    CategoryService categoryService;

    @PostMapping
    public Result addCategoryController(@RequestBody CategoryDto categoryDto){
        Result result = categoryService.addCategoryService(categoryDto);
        return  result;
    }

    @GetMapping
    public List<Category>getCategories(){
        return categoryService.getCategory();
    }
    @GetMapping("/{getCategoryById}")
    public Category getById(@PathVariable Integer getCategoryById){
        return categoryService.getCategoryById(getCategoryById);
    }

    @PutMapping("/{editCategoryById}")
    public Result editCategory(@PathVariable Integer editCategoryById,@RequestBody CategoryDto categoryDto){
        Result result = categoryService.editCategory(editCategoryById, categoryDto);
        return result;
    }
    @DeleteMapping("/{deleteById}")
    public Result deleteCategory(@PathVariable Integer deleteById){

        Result result = categoryService.deleteCategory(deleteById);
        return result;

    }





}
