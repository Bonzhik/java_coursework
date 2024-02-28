package com.course.project.Repositories;

import com.course.project.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByTitle(String title);
    List<Product> findByCategoryProductsCategoryId(long id);
}
