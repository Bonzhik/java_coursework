package com.course.project.Services.Impl;

import com.course.project.Models.Status;
import com.course.project.Repositories.StatusRepository;
import com.course.project.Services.Interfaces.IStatusService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StatusService implements IStatusService {
    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public Status Get(long id) {
        return null;
    }

    @Override
    public Status Get(String title) {
        return statusRepository.findByTitle(title);
    }

    @Override
    public List<Status> GetAll() {
        return null;
    }

    @Override
    public Boolean Save(Status status) {
        return null;
    }

    @Override
    public Boolean Delete(long id) {
        return null;
    }
}
