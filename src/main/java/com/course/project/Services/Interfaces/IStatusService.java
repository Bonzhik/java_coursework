package com.course.project.Services.Interfaces;

import com.course.project.Models.Status;

import java.util.List;

public interface IStatusService {
    Status Get(long id);
    Status Get(String title);
    List<Status> GetAll();
    Boolean Save(Status status);
    Boolean Delete(long id);
}
