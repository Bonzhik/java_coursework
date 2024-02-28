package com.course.project.Services.Interfaces;

import com.course.project.Models.User;

import java.util.List;

public interface IUserService {
    User Get(long id);
    User Get(String email);
    List<User> GetAll();
    Boolean Delete(long id);
}
