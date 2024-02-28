package com.course.project.Services;

import com.course.project.Dto.LoginModel;
import com.course.project.Dto.RegisterModel;
import com.course.project.Dto.UserUpdate;
import com.course.project.Models.User;
import com.course.project.Repositories.RoleRepository;
import com.course.project.Repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@Service
public class AuthService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private JwtService jwtService;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
    }
    public boolean signUp(RegisterModel userDto)
    {
        User user = new User();
        try{
            user.setEmail(userDto.getEmail());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setRole(roleRepository.findByTitle(userDto.getRole()));
            userRepository.save(user);
            return true;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    public boolean updateUser(UserUpdate userDto){
        User user = new User();
        try{
            user.setId(userDto.getId());
            user.setEmail(userDto.getEmail());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setRole(userDto.getRole());
            userRepository.save(user);
            return true;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    public String signIn(LoginModel userDto)
    {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));
            var user = userRepository.findByEmail(userDto.getEmail());
            var jwt = jwtService.generateToken(user);
            var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
            return jwt;

        } catch (Exception ex)
        {
            return null;
        }
    }
    public String refreshToken(String token)
    {
       String email = jwtService.extractUsername(token);
       var user = userRepository.findByEmail(email);
       if (jwtService.isTokenValid(token, user)){
           var jwt = jwtService.generateToken(user);
           return jwt;
       }
       return null;
    }
}
