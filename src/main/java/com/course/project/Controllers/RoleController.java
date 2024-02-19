package com.course.project.Controllers;

import com.course.project.Dto.RoleCreate;
import com.course.project.Models.Role;
import com.course.project.Services.Impl.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/role")
public class RoleController {
    private final RoleService roleService;
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<Role>> GetAll(){
        return ResponseEntity.ok(roleService.GetAll());
    }

    @PostMapping
    public ResponseEntity<Role> Save(@RequestBody RoleCreate roleCreate)
    {
        var role = new Role();
        role.setTitle(roleCreate.title);
        if (!roleService.Save(role)){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(role);
        }
        return ResponseEntity.ok(role);
    }
    @PutMapping("{id}")
    public ResponseEntity<Role> Save(@PathVariable long id, @RequestBody RoleCreate roleCreate)
    {
        var role = new Role();
        role.setTitle(roleCreate.title);
        System.out.println(roleCreate.title);
        if (!roleService.Save(role)){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(role);
        }
        return ResponseEntity.ok(role);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> Delete(@PathVariable long id)
    {
        try{
            roleService.Delete(id);
            return ResponseEntity.ok("Удаление успешно");
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
