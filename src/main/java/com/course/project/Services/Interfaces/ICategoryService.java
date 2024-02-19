package com.course.project.Services.Interfaces;

import com.course.project.Models.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    Optional<Category> Get(long id);
    Category Get(String title);
    List<Category> GetAll();
    Boolean Save(Category category);
    Boolean Delete(long id);
}
