package com.sodep.api.dao.impl;

import com.sodep.api.beans.TaskRequest;
import com.sodep.api.dao.TaskDao;
import com.sodep.api.exception.ApiException;
import com.sodep.api.exception.ErrorConstants;
import com.sodep.entities.Task;
import com.sodep.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

/**
 * @author mlopez
 * @fecha 2019-05-11,10:53
 */
@Repository
public class TaskDaoImpl extends JdbcDaoSupport implements TaskDao {

    @Autowired
    private TaskRepository repository;

    private static final String SQL_INSERT_TASK = "insert into TASKS (DESCRIPTION, ASSIGNED_TO, DUE, COMPLETED_AT, COMPLETED) values ( ?, ?, ?, ?, ? )";
    private static final String SQL_UPDATE_TASK = "update TASKS SET DESCRIPTION=?, ASSIGNED_TO=?, DUE=?, COMPLETED_AT=?, COMPLETED=? where ID = ?";
    private static final String SQL_DELETE_TASK = "delete from TASKS where id = ?";

    public TaskDaoImpl(DataSource dataSource){
        setDataSource(dataSource);
    }

    @Override
    public Task save(TaskRequest request) throws ApiException{
        try{
            KeyHolder keyHolder = new GeneratedKeyHolder();
            getJdbcTemplate().update(connection -> {

                PreparedStatement ps = connection.prepareStatement(SQL_INSERT_TASK, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,request.getDescription());
                if(request.getAssigneeId()==null)
                    ps.setNull(2,Types.NULL);
                else
                    ps.setInt(2,request.getAssigneeId().intValue());

                if(request.getDue()==null)
                    ps.setNull(3, Types.NULL);
                else
                    ps.setTimestamp(3,new Timestamp(request.getDue().getTime()));

                if(request.getCompletedAt()==null)
                    ps.setNull(4, Types.NULL);
                else
                    ps.setTimestamp(4,new Timestamp(request.getCompletedAt().getTime()));

                ps.setString(5, String.valueOf(request.isCompleted()));
                return ps;
            }, keyHolder);
            return repository.findOne(keyHolder.getKey().longValue());
        }catch (DataAccessException e){
            throw new ApiException(ErrorConstants.DATABASE_ERROR);
        }

    }

    @Override
    public Task update(Long id, TaskRequest request) throws ApiException{
        Object[] params = {request.getDescription(), request.getAssigneeId(), request.getDue(), request.getCompletedAt(), String.valueOf(request.isCompleted()), id};
        int[] types = {Types.VARCHAR, Types.INTEGER, Types.TIMESTAMP, Types.TIMESTAMP, Types.VARCHAR, Types.INTEGER};
        try {
            int update = getJdbcTemplate().update(SQL_UPDATE_TASK, params, types);
            if(update==1)
                return repository.findOne(id);
            return null;

        }catch (DataAccessException e){
            throw new ApiException(ErrorConstants.DATABASE_ERROR);
        }
    }

    @Override
    public void delete(Long id) throws ApiException{
        try {
            getJdbcTemplate().update(SQL_DELETE_TASK, id);
        }catch (DataAccessException e){
            throw new ApiException(ErrorConstants.DATABASE_ERROR);
        }
    }


}
