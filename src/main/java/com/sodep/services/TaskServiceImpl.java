/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sodep.services;

import com.sodep.api.beans.TaskRequest;
import com.sodep.api.exception.ApiException;
import com.sodep.api.exception.ErrorConstants;
import com.sodep.entities.Task;
import com.sodep.repository.TaskRepository;
import com.sodep.repository.TaskRepositoryPersistent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 *
 * @author rodrigo
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepo;

    @Autowired
    private TaskRepositoryPersistent persistent;
    
    @Override
    public Iterable<Task> findAll() {
        return taskRepo.findAll(new Sort(Sort.Direction.ASC, "createdAt"));
    }

    @Override
    public Iterable<Task> findAllForFilter(boolean completed, int page, int size) {

        Pageable pageable = new PageRequest(page, size);
        return taskRepo.findAllByCompleted(String.valueOf(completed), pageable);
    }

    @Override
    public List<Task> findAllForAssignee(Long assigneeId) {
        return taskRepo.findByAssignee_Id(assigneeId);
    }

    @Override
    public Task findById(Long id) {
        return taskRepo.findOne(id);
    }

    @Override
    public Task saveTask(TaskRequest taskRequest) throws ApiException {

        Task task = new Task(taskRequest, null);
        if(permitsAssigned(taskRequest.getAssigneeId())) {
            Task result = persistent.save(task);
            return findById(result.getId());
//            return taskDao.save(taskRequest);
        }else
            throw new ApiException(ErrorConstants.ERROR_ASSIGNEE_LIMITS, "10000");
    }

    @Override
    public Task updateTask(Long id, TaskRequest task) throws ApiException  {
        Task task1 = new Task(task, id);
        if(permitsAssigned(task.getAssigneeId()))
            return persistent.save(task1);
        else
            throw new ApiException(ErrorConstants.ERROR_ASSIGNEE_LIMITS);

    }

    @Override
    public void deleteTask(Long id) throws ApiException  {
        persistent.delete(id);
    }

    private boolean permitsAssigned(Long idAssignee) {
        if(idAssignee==null)
            return true;
        else{
            List<Task> tasks = findAllForAssignee(idAssignee);
            return tasks.size() < 5;
        }
    }
}
