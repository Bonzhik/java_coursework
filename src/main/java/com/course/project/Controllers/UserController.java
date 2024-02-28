package com.course.project.Controllers;

import com.course.project.Dto.Mapper.Mapper;
import com.course.project.Dto.RegisterModel;
import com.course.project.Dto.UserRead;
import com.course.project.Dto.UserUpdate;
import com.course.project.Services.AuthService;
import com.course.project.Services.Impl.RoleService;
import com.course.project.Services.Impl.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {
    private UserService userService;
    private AuthService authService;
    private RoleService roleService;
    private Mapper mapper;

    public UserController(UserService userService,
                          AuthService authService,
                          RoleService roleService,
                          Mapper mapper) {
        this.userService = userService;
        this.authService = authService;
        this.roleService = roleService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<UserRead>> GetAll()
    {
        try{
            var users = userService.GetAll();
            var usersRead = new ArrayList<UserRead>();
            for (var user: users){
                usersRead.add(mapper.MapUserToDto(user));
            }
            return ResponseEntity.ok(usersRead);
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("{id}")
    public ResponseEntity<UserRead> Get(@PathVariable long id)
    {
        try{
            var user = mapper.MapUserToDto(userService.Get(id));
            return ResponseEntity.ok(user);
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("{byEmail}")
    public ResponseEntity<UserRead> Get(@PathVariable String email)
    {
        try{
            var user = mapper.MapUserToDto(userService.Get(email));
            return ResponseEntity.ok(user);
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @PutMapping("{id}")
    public ResponseEntity<UserUpdate> Save(@PathVariable long id, @RequestBody RegisterModel userDto)
    {
        try{
            var user = new UserUpdate();
            user.setId(id);
            user.setPassword(userDto.getPassword());
            user.setEmail(userDto.getEmail());
            user.setRole(roleService.Get(userDto.getRole()));
            authService.updateUser(user);
            return ResponseEntity.ok(user);
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> Delete(@PathVariable long id)
    {
        try{
            userService.Delete(id);
            return ResponseEntity.ok("Deleted");
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

}
