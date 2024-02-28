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
        return statusRepository.findById(id).orElseThrow();
    }

    @Override
    public Status Get(String title) {
        return statusRepository.findByTitle(title);
    }

    @Override
    public List<Status> GetAll() {
        return statusRepository.findAll();
    }

    @Override
    public Boolean Save(Status status) {
        try{
            statusRepository.save(status);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    @Override
    public Boolean Delete(long id) {
        try{
            statusRepository.deleteById(id);
            return true;
        }catch (Exception ex){
            return  false;
        }
    }
}
