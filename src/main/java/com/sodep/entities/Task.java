package com.sodep.entities;

import com.sodep.api.beans.TaskRequest;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="tasks")
public final class Task implements Serializable {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
    
	private String description;


    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "assigned_to")
    private Assignee assignee;

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
    
    /**
      The date where the task
      was created.
    */
	private Date createdAt;

    /** Status the task */
	private String completed;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Assignee getAssignee() {
        return assignee;
    }

    public void setAssignee(final Assignee assignee) {
        this.assignee = assignee;
    }

    public Date getDue() {
        return due;
    }

    public void setDue(final Date due) {
        this.due = due;
    }

    public Date getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(final Date completedAt) {
        this.completedAt = completedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }

    public Task(TaskRequest request, Long taskId){
        if(request.getAssigneeId()!=null){
            Assignee assignee = new Assignee();
            assignee.setId(request.getAssigneeId());
            this.assignee = assignee;
        }

        if(taskId!=null){
            this.id = taskId;
        }
        this.completedAt = request.getCompletedAt();
        this.completed= String.valueOf(request.isCompleted());
        this.due = request.getDue();
        this.description = request.getDescription();
        this.createdAt = new Date();

    }

    public Task() {
    }
}
