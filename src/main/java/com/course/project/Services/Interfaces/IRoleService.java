package com.course.project.Services.Interfaces;

import com.course.project.Models.Role;

import java.util.List;

public interface IRoleService {
    Role Get(long id);
    Role Get(String title);
    List<Role> GetAll();
    Boolean Save(Role role);
    Boolean Delete(long id);
}
