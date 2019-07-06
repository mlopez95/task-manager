package com.sodep.repository;

import com.sodep.entities.Task;
import org.springframework.data.repository.CrudRepository;

/**
 * @author mlopez
 * @fecha 2019-07-06,17:09
 */
public interface TaskRepositoryPersistent extends CrudRepository<Task, Long> {

}
