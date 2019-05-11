/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sodep.services;

import com.sodep.api.beans.TaskRequest;
import com.sodep.api.exception.ApiException;
import com.sodep.entities.Task;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author rodrigo
 */
public interface TaskService {

    Iterable<Task> findAll();
    Task findById(Long id);
    Task saveTask(TaskRequest task) throws ApiException;
    Task updateTask(Long id, TaskRequest task) throws ApiException ;
    void deleteTask(Long id) throws ApiException ;
    Iterable<Task> findAllForFilter(boolean completed, int page, int size);
    List<Task> findAllForAssignee(Long assigneeId);


}
