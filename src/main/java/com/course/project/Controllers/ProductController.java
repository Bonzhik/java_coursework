package com.course.project.Controllers;

import com.course.project.Dto.Mapper.Mapper;
import com.course.project.Dto.ProductCreate;
import com.course.project.Dto.ProductRead;
import com.course.project.Models.Category;
import com.course.project.Models.CategoryProduct;
import com.course.project.Models.Product;
import com.course.project.Services.Impl.CategoryService;
import com.course.project.Services.Impl.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final Mapper mapper;

    public ProductController(CategoryService categoryService, ProductService productService, Mapper mapper) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.mapper = mapper;
    }
    @GetMapping
    public ResponseEntity<List<ProductRead>> GetAll(){
        try{
            List<ProductRead> productsRead = new ArrayList<ProductRead>();
            var products = productService.GetAll();
            for(var product : products){
                var currentProductRead = mapper.MapProductToDto(product);
                productsRead.add(currentProductRead);
            }
            return ResponseEntity.ok(productsRead);
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("{id}")
    public ResponseEntity<ProductRead> Get(@PathVariable long id){
        try{
            ProductRead product = mapper.MapProductToDto(productService.Get(id).orElseThrow());
            return ResponseEntity.ok(product);
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("byCategory/{categoryId}")
    public ResponseEntity<List<ProductRead>> GetByCategories(@PathVariable long categoryId){
        try{
            List<ProductRead> productsRead = new ArrayList<ProductRead>();
            var products = productService.GetByCategory(categoryId);
            for(var product : products){
                var currentProductRead = mapper.MapProductToDto(product);
                productsRead.add(currentProductRead);
            }
            return ResponseEntity.ok(productsRead);
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> Delete(@PathVariable long id){
        try{
            productService.Delete(id);
            return ResponseEntity.ok("Удаление успешно");
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProductCreate> Save(@RequestBody ProductCreate productDto)
    {
        try{
            var product = mapper.MapProductFromDto(productDto);
            productService.Save(product);
            return ResponseEntity.ok(productDto);
        }catch(Exception ex)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProductCreate> Save(@PathVariable long id, @RequestBody ProductCreate productDto)
    {
        try{
            var product = mapper.MapProductFromDto(productDto);
            product.setId(id);
            productService.Save(product);
            return ResponseEntity.ok(productDto);
        }catch(Exception ex)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
