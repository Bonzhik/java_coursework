package com.course.project.Controllers;

import com.course.project.Dto.OrderCreate;
import com.course.project.Models.Order;
import com.course.project.Models.OrderProduct;
import com.course.project.Services.Impl.OrderService;
import com.course.project.Services.Impl.ProductService;
import com.course.project.Services.Impl.StatusService;
import com.course.project.Services.Impl.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;

@RestController
@RequestMapping("api/order")
public class OrderController {
    private OrderService orderService;
    private ProductService productService;
    private UserService userService;
    private StatusService statusService;
    public OrderController(OrderService orderService, ProductService productService, UserService userService,StatusService statusService) {
        this.orderService = orderService;
        this.productService = productService;
        this.userService = userService;
        this.statusService = statusService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> Save(@RequestBody OrderCreate orderCreate, @RequestHeader("Authorization") String token)
    {
        try{
            String secretKey = "aaskldhj12fasdfas3123kjdlf3hajsdhfuawe112323hkjsadhfjkhuaksjfh";
            byte[] keyBytes = Base64.getDecoder().decode(secretKey.getBytes(StandardCharsets.UTF_8));
            var key = new SecretKeySpec(keyBytes, "HmacSHA256");
            String jwtToken = token.substring(7);
            Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwtToken).getBody();
            String userEmail = claims.getSubject();
            var order= new Order();
            order.setUser(userService.Get(userEmail));
            order.setStatus(statusService.Get("Create"));
            for(int i = 0; i<orderCreate.getProductsInOrder().length ; i++){
                var currentProduct = productService.Get(orderCreate.getProductsInOrder()[i][0]).orElseThrow();
                if (currentProduct.getQuantity() < orderCreate.getProductsInOrder()[i][1]){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Недостаточно товаров");
                }
                OrderProduct orderProduct = new OrderProduct();
                orderProduct.setOrder(order);
                orderProduct.setProduct(currentProduct);
                orderProduct.setQuantity(orderCreate.getProductsInOrder()[i][1]);
                currentProduct.setQuantity(currentProduct.getQuantity() - orderCreate.getProductsInOrder()[i][1]);
                productService.Save(currentProduct);
                order.addOrderProduct(orderProduct);
            }
            orderService.Save(order);
            return ResponseEntity.ok("Заказ создан");

        }catch(Exception ex)
        {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping
    public ResponseEntity<List<OrderRead>> GetAll(){
        try{
            var orders = new ArrayList<OrderRead>();
            return ResponseEntity.ok(orders);
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("{id}")
    public ResponseEntity<OrderRead> Get(@PathVariable long id)
    {
        try{
            return ResponseEntity.ok(order);
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("byuser/{userid}")
    public ResponseEntity<List<OrderRead>> GetByUser(@PathVariable long userid){
        try{
            var orders = new ArrayList<OrderRead>();
            return ResponseEntity.ok(orders);
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> Delete(@PathVariable long id){
        try{
            return ResponseEntity.ok("Deleted");
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }
}
