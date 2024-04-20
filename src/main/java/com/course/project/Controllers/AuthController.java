package com.course.project.Controllers;

import com.course.project.Dto.LoginModel;
import com.course.project.Dto.RegisterModel;
import com.course.project.Dto.UserRead;
import com.course.project.Models.User;
import com.course.project.Services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/signup")
    public ResponseEntity<Boolean> signUp(@RequestBody RegisterModel signUpRequest){
        return ResponseEntity.ok(authService.signUp(signUpRequest));
    }
    @PostMapping("/signin")
    public ResponseEntity<UserRead> signIn(@RequestBody LoginModel signInRequest){
        return ResponseEntity.ok(authService.signIn(signInRequest));
    }
    @PostMapping("/refresh")
    public ResponseEntity<String> refreshToken(@RequestBody String refreshTokenRequest){
        return ResponseEntity.ok(authService.refreshToken(refreshTokenRequest));
    }
}
