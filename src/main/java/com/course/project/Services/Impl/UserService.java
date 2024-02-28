package com.course.project.Services.Impl;

import com.course.project.Models.Role;
import com.course.project.Models.User;
import com.course.project.Repositories.UserRepository;
import com.course.project.Services.Interfaces.IUserService;
import jakarta.transaction.Transactional;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService, UserDetailsService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User Get(long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public User Get(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> GetAll() {
        return userRepository.findAll();
    }
    @Override
    public Boolean Delete(long id) {
        try{
            userRepository.deleteById(id);
            return true;
        }catch (Exception ex){
            return  false;
        }
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = Get(email);
        if (user == null){
            throw new UsernameNotFoundException(String.format("Пользователь '%s' не найден",email));
        };
        return user;
    }
}
