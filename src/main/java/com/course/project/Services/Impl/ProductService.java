package com.course.project.Services.Impl;

import com.course.project.Models.Product;
import com.course.project.Repositories.ProductRepository;
import com.course.project.Services.Interfaces.IProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> Get(long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product Get(String title) {
        return null;
    }

    @Override
    public List<Product> GetAll() {
        return null;
    }

    @Override
    public Boolean Save(Product product) {
        try{
            productRepository.save(product);
            return true;
        }catch(Exception ex){
            return false;
        }
    }

    @Override
    public Boolean Delete(long id) {
        return null;
    }
}
