package com.course.project.Controllers;

import com.course.project.Dto.RegisterModel;
import com.course.project.Services.Impl.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserRead>> GetAll()
    {
        try{
            //return ResponseEntity.ok(users)
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(user);
        }
    }
    @GetMapping("{id}")
    public ResponseEntity<UserRead> Get(@PathVariable long id)
    {
        try{

        }catch(Exception ex){

        }
    }
    @GetMapping("{byEmail}")
    public ResponseEntity<UserRead> Get(@PathVariable String email)
    {
        try{
            //return ResponseEntity.ok(user)
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(user);
        }
    }
    @PutMapping("{id}")
    public ResponseEntity<RegisterModel> Save(@PathVariable long id, @RequestBody RegisterModel userDto)
    {
        try{
            return ResponseEntity.ok(userDto);
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(userDto);
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> Delete(@PathVariable long id)
    {
        try{
            return ResponseEntity.ok("Deleted");
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

}
