package com.course.project.Controllers;

import com.course.project.Dto.ProductCreate;
import com.course.project.Dto.ProductRead;
import com.course.project.Models.Category;
import com.course.project.Models.CategoryProduct;
import com.course.project.Models.Product;
import com.course.project.Services.Impl.CategoryService;
import com.course.project.Services.Impl.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/product")
public class ProductController {
    private final CategoryService categoryService;
    private final ProductService productService;

    public ProductController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }
    @GetMapping
    public ResponseEntity<List<ProductRead>> GetAll(){
        try{
            List<ProductRead> products = new ArrayList<ProductRead>();
            return ResponseEntity.ok(products);
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("{id}")
    public ResponseEntity<ProductRead> Get(@PathVariable long id){
        try{
            //ProductRead product = new product();
            //return ResponseEntity.ok(product);
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("byCategory")
    public ResponseEntity<List<ProductRead>> GetByCategories(@RequestParam List<Long> categories){
        try{
            List<ProductRead> products = new ArrayList<ProductRead>();
            return ResponseEntity.ok(products);
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> Delete(@PathVariable long id){
        try{
            productService.Delete(id);
            return ResponseEntity.ok("Удаление успешно");
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @PostMapping
    public ResponseEntity<ProductCreate> Save(@RequestBody ProductCreate productDto)
    {
        try{
            var product = new Product();
            product.setTitle(productDto.getTitle());
            product.setPrice(productDto.getPrice());
            product.setQuantity(productDto.getPrice());
            for (var categoryId : productDto.getCategories())
            {
                Category category = categoryService.Get(categoryId).orElseThrow();
                CategoryProduct categoryProduct = new CategoryProduct();
                categoryProduct.setCategory(category);
                categoryProduct.setProduct(product);
                product.addCategoryProduct(categoryProduct);
            }
            productService.Save(product);
            return ResponseEntity.ok(productDto);
        }catch(Exception ex)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("{id}")
    public ResponseEntity<ProductCreate> Save(@PathVariable long id, @RequestBody ProductCreate productDto)
    {
        try{
            var product = new Product();
            product.setId(id);
            product.setTitle(productDto.getTitle());
            product.setPrice(productDto.getPrice());
            product.setQuantity(productDto.getPrice());
            for (var categoryId : productDto.getCategories())
            {
                Category category = categoryService.Get(categoryId).orElseThrow();
                CategoryProduct categoryProduct = new CategoryProduct();
                categoryProduct.setCategory(category);
                categoryProduct.setProduct(product);
                product.addCategoryProduct(categoryProduct);
            }
            productService.Save(product);
            return ResponseEntity.ok(productDto);
        }catch(Exception ex)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
