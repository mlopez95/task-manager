package com.sodep.api.dao;

import com.sodep.api.beans.TaskRequest;
import com.sodep.api.exception.ApiException;
import com.sodep.entities.Task;

/**
 * @author mlopez
 * @fecha 2019-05-11,11:10
 */


public interface TaskDao {

    Task save(TaskRequest request) throws ApiException;
    Task update(Long id, TaskRequest request) throws ApiException;
    void delete(Long id) throws ApiException;
}
