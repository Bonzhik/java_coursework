package com.course.project.Controllers;

import com.course.project.Dto.Mapper.Mapper;
import com.course.project.Dto.OrderCreate;
import com.course.project.Dto.OrderRead;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("api/order")
public class OrderController {
    private OrderService orderService;
    private ProductService productService;
    private UserService userService;
    private StatusService statusService;
    private Mapper mapper;
    public OrderController(
            OrderService orderService,
            ProductService productService,
            UserService userService,
            StatusService statusService,
            Mapper mapper) {
        this.orderService = orderService;
        this.productService = productService;
        this.userService = userService;
        this.statusService = statusService;
        this.mapper = mapper;
    }

    @PostMapping
    @Transactional
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> Save(@RequestBody OrderCreate orderCreate, @RequestHeader("Authorization") String token)
    {
        try{
            String secretKey = "aaskldhj12fasdfas3123kjdlf3hajsdhfuawe112323hkjsadhfjkhuaksjfh";
            byte[] keyBytes = Base64.getDecoder().decode(secretKey.getBytes(StandardCharsets.UTF_8));
            var key = new SecretKeySpec(keyBytes, "HmacSHA256");
            String jwtToken = token.substring(7);
            Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwtToken).getBody();
            String userEmail = claims.getSubject();
            var order= mapper.MapOrderFromDto(orderCreate);
            if (order == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Недостаточно товаров");
            order.setUser(userService.Get(userEmail));
            order.setStatus(statusService.Get("Create"));
            orderService.Save(order);
            return ResponseEntity.ok("Заказ создан");

        }catch(Exception ex)
        {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<OrderRead>> GetAll(){
        try{
            var ordersRead = new ArrayList<OrderRead>();
            var orders = orderService.GetAll();
            for(var order : orders){
                ordersRead.add(mapper.MapOrderToDto(order));
            }
            return ResponseEntity.ok(ordersRead);
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<OrderRead> Get(@PathVariable long id)
    {
        try{
            var order = mapper.MapOrderToDto(orderService.Get(id));
            return ResponseEntity.ok(order);
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("byuser/{userid}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<OrderRead>> GetByUser(@PathVariable long userid){
        try{
            var ordersRead = new ArrayList<OrderRead>();
            var orders = orderService.GetByUser(userid);
            for(var order : orders){
                ordersRead.add(mapper.MapOrderToDto(order));
            }
            return ResponseEntity.ok(ordersRead);
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @PutMapping("{id}")
    @Transactional
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> Save(@PathVariable long id, @RequestBody OrderCreate orderCreate, @RequestHeader("Authorization") String token)
    {
        try{
            String secretKey = "aaskldhj12fasdfas3123kjdlf3hajsdhfuawe112323hkjsadhfjkhuaksjfh";
            byte[] keyBytes = Base64.getDecoder().decode(secretKey.getBytes(StandardCharsets.UTF_8));
            var key = new SecretKeySpec(keyBytes, "HmacSHA256");
            String jwtToken = token.substring(7);
            Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwtToken).getBody();
            String userEmail = claims.getSubject();
            var order= mapper.MapOrderFromDto(orderCreate);
            order.setId(id);
            if (order == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Недостаточно товаров");
            order.setUser(userService.Get(userEmail));
            order.setStatus(statusService.Get("Create"));
            orderService.Save(order);
            return ResponseEntity.ok("Заказ создан");

        }catch(Exception ex)
        {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> Delete(@PathVariable long id){
        try{
            return ResponseEntity.ok("Deleted");
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }
}
