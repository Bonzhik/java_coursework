package com.course.project.Dto;

import java.util.List;

public class ProductRead {
    public int id;
    public String title;
    public int quantity;
    public int price;
    public List<CategoryRead> categories;
}
