package com.course.project.Services;

import com.course.project.Dto.LoginModel;
import com.course.project.Dto.Mapper.Mapper;
import com.course.project.Dto.RegisterModel;
import com.course.project.Dto.UserRead;
import com.course.project.Dto.UserUpdate;
import com.course.project.Models.User;
import com.course.project.Repositories.RoleRepository;
import com.course.project.Repositories.UserRepository;
import lombok.var;
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
    private Mapper mapper;

    public AuthService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, RoleRepository roleRepository, Mapper mapper) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
        this.mapper = mapper;
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
    public UserRead signIn(LoginModel userDto)
    {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));
            var user = userRepository.findByEmail(userDto.getEmail());
            UserRead result = mapper.MapUserToDto(user);
            var jwt = jwtService.generateToken(user);
            var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
            result.setToken(jwt);
            return result;

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
