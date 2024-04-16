package com.course.project.Dto.Mapper;
import com.course.project.Models.*;
import com.course.project.Dto.*;
import com.course.project.Services.Impl.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class Mapper {
    private ProductService productService;
    private OrderService orderService;
    private CategoryService categoryService;

    public Mapper(ProductService productService, OrderService orderService, CategoryService categoryService) {
        this.productService = productService;
        this.orderService = orderService;
        this.categoryService = categoryService;
    }

    public Order MapOrderFromDto(OrderCreate orderCreate)
    {
        var order = new Order();
        for(int i = 0; i<orderCreate.getProductsInOrder().length ; i++){
            var currentProduct = productService.Get(orderCreate.getProductsInOrder()[i][0]).orElseThrow();
            if (currentProduct.getQuantity() < orderCreate.getProductsInOrder()[i][1]){
                return null;
            }
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrder(order);
            orderProduct.setProduct(currentProduct);
            orderProduct.setQuantity(orderCreate.getProductsInOrder()[i][1]);
            currentProduct.setQuantity(currentProduct.getQuantity() - orderCreate.getProductsInOrder()[i][1]);
            productService.Save(currentProduct);
            order.addOrderProduct(orderProduct);
        }
        return order;
    }
    public Product MapProductFromDto(ProductCreate productDto)
    {
        var product = new Product();
        product.setTitle(productDto.getTitle());
        product.setImageUrl(productDto.getImageUrl());
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
        return product;
    }
    public ProductRead MapProductToDto(Product product){
        var productRead = new ProductRead();
        productRead.setId(product.getId());
        productRead.setTitle(product.getTitle());
        productRead.setPrice(product.getPrice());
        productRead.setImageUrl(product.getImageUrl());
        productRead.setQuantity(product.getQuantity());
        for (var category : product.getCategoryProducts()){
            productRead.getCategories().add(new CategoryRead(){
                {
                    setId(category.getCategory().getId());
                    setTitle(category.getCategory().getTitle());
                }
            });
        }
        return productRead;
    }
    public OrderRead MapOrderToDto(Order order){
        var orderRead = new OrderRead();
        orderRead.setId(order.getId());
        orderRead.setStatus(order.getStatus());
        orderRead.setUser(order.getUser());
        for(var product: order.getOrderProducts()){
            orderRead.getProducts().add(new OrderItem(){
                {
                    setProduct(MapProductToDto(product.getProduct()));
                    setQuantity(product.getQuantity());
                }
            });
        }
        return orderRead;
    }
    public UserRead MapUserToDto(User user)
    {
        var userRead = new UserRead();
        userRead.setId(user.getId());
        userRead.setEmail(user.getEmail());
        userRead.setPassword(user.getPassword());
        userRead.setRole(user.getRole());
        for (var order: user.getOrders()){
            userRead.getOrders().add(MapOrderToDto(order));
        }
        return userRead;
    }
}
