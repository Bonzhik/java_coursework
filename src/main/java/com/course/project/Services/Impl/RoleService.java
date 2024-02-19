package com.course.project.Services.Impl;

import com.course.project.Models.Role;
import com.course.project.Repositories.RoleRepository;
import com.course.project.Services.Interfaces.IRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService {
    private final RoleRepository rolePepository;

    public RoleService(RoleRepository rolePepository) {
        this.rolePepository = rolePepository;
    }

    @Override
    public Role Get(long id) {
        return null;
    }

    @Override
    public Role Get(String title) {
        return null;
    }

    @Override
    public List<Role> GetAll() {
        return null;
    }

    @Override
    public Boolean Save(Role role) {
        return null;
    }


    @Override
    public Boolean Delete(long id) {
        return null;
    }
}
