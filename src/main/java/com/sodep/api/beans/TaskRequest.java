package com.sodep.api.beans;

import com.sodep.entities.Assignee;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author mlopez
 * @fecha 2019-05-11,09:12
 */


public class TaskRequest {

    /** Description of the task */
    @NotEmpty(message = "El campo [description] no puede estar vacio")
    private String description;

    /**Identifier of the assigned person */
    private Long assigneeId;

    /**
     The date where the task
     should be completed.
     */
    private Date due;

    /**
     The date where the task
     was completed.
     */
    private Date completedAt;

    @NotNull(message = "El campo [completed] no puede ser null")
    private Boolean completed;

    public Boolean isCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(Long assigneeId) {
        this.assigneeId = assigneeId;
    }

    public Date getDue() {
        return due;
    }

    public void setDue(Date due) {
        this.due = due;
    }

    public Date getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Date completedAt) {
        this.completedAt = completedAt;
    }
}
