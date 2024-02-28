package com.course.project.Services.Impl;

import com.course.project.Models.Role;
import com.course.project.Repositories.RoleRepository;
import com.course.project.Services.Interfaces.IRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository rolePepository) {
        this.roleRepository = rolePepository;
    }

    @Override
    public Role Get(long id) {
        return roleRepository.findById(id).orElseThrow();
    }

    @Override
    public Role Get(String title) {
        return roleRepository.findByTitle(title);
    }

    @Override
    public List<Role> GetAll() {
        return roleRepository.findAll();
    }

    @Override
    public Boolean Save(Role role) {
        try{
            roleRepository.save(role);
            return true;
        }catch (Exception ex){
            return false;
        }
    }
    @Override
    public Boolean Delete(long id) {
        try{
            roleRepository.deleteById(id);
            return true;
        }catch (Exception ex){
            return  false;
        }
    }
}
