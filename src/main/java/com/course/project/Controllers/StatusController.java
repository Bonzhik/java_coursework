package com.course.project.Controllers;

import com.course.project.Dto.StatusCreate;
import com.course.project.Models.Status;
import com.course.project.Services.Impl.StatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/status")
public class StatusController {
    private final StatusService statusService;
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping
    public ResponseEntity<List<Status>> GetAll(){
        return ResponseEntity.ok(statusService.GetAll());
    }

    @PostMapping
    public ResponseEntity<Status> Save(@RequestBody StatusCreate statusCreate)
    {
        var status = new Status();
        status.setTitle(statusCreate.getTitle());
        if (!statusService.Save(status)){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(status);
        }
        return ResponseEntity.ok(status);
    }
    @PutMapping("{id}")
    public ResponseEntity<Status> Save(@PathVariable long id, @RequestBody StatusCreate statusCreate)
    {
        var status = new Status();
        status.setTitle(statusCreate.getTitle());
        if (!statusService.Save(status)){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(status);
        }
        return ResponseEntity.ok(status);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> Delete(@PathVariable long id)
    {
        try{
            statusService.Delete(id);
            return ResponseEntity.ok("Удаление успешно");
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
