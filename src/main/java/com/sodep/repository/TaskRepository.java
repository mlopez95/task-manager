/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sodep.repository;

import com.sodep.entities.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


/**
 *
 * @author rodrigo
 */
public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {

    Page<Task> findAllByCompleted(String completed, Pageable pageable);
    List<Task> findByAssignee_Id(Long id);
}
