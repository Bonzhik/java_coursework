package com.course.project.Services.Interfaces;

import com.course.project.Models.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    Optional<Product> Get(long id);
    Product Get(String title);
    List<Product> GetAll();
    Boolean Save(Product product);
    Boolean Delete(long id);
}
