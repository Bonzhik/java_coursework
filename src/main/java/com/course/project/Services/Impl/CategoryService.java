package com.course.project.Services.Impl;

import com.course.project.Models.Category;
import com.course.project.Repositories.CategoryRepository;
import com.course.project.Services.Interfaces.ICategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Optional<Category> Get(long id) {
        return categoryRepository.findById(id);
    }
    @Override
    public Category Get(String title) {
        return categoryRepository.findByTitle(title);
    }

    @Override
    public List<Category> GetAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Boolean Save(Category category) {
        try{
            categoryRepository.save(category);
            return true;
        } catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public Boolean Delete(long id)
    {
        try{
            categoryRepository.deleteById(id);
            return true;
        }catch (Exception ex){
            return  false;
        }
    }
}
