package com.course.project.Controllers;

import com.course.project.Dto.CategoryCreate;
import com.course.project.Models.Category;
import com.course.project.Services.Impl.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<Category>> GetAll(){
        return ResponseEntity.ok(categoryService.GetAll());
    }

    @PostMapping
    public ResponseEntity<Category> Save(@RequestBody CategoryCreate categoryCreate)
    {
        var category = new Category();
        category.setTitle(categoryCreate.getTitle());
        if (!categoryService.Save(category)){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(category);
        }
        return ResponseEntity.ok(category);
    }
    @PutMapping("{id}")
    public ResponseEntity<Category> Save(@PathVariable long id, @RequestBody CategoryCreate categoryCreate)
    {
        var category = new Category();
        category.setTitle(categoryCreate.getTitle());
        if (!categoryService.Save(category)){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(category);
        }
        return ResponseEntity.ok(category);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> Delete(@PathVariable long id)
    {
        try{
            categoryService.Delete(id);
            return ResponseEntity.ok("Удаление успешно");
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
