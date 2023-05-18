package com.perso.proapps.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TaskPriority.
 */
@Entity
@Table(name = "task_priority")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TaskPriority implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "priority")
    private String priority;

    @Lob
    @Column(name = "task_priority_picto")
    private byte[] taskPriorityPicto;

    @Column(name = "task_priority_picto_content_type")
    private String taskPriorityPictoContentType;

    @Column(name = "notes")
    private String notes;

    @OneToMany(mappedBy = "taskPriority")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "taskPriority", "taskStatus", "taskOwner", "taskMoM", "projectName" }, allowSetters = true)
    private Set<Task> tasks = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TaskPriority id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public TaskPriority code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPriority() {
        return this.priority;
    }

    public TaskPriority priority(String priority) {
        this.setPriority(priority);
        return this;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public byte[] getTaskPriorityPicto() {
        return this.taskPriorityPicto;
    }

    public TaskPriority taskPriorityPicto(byte[] taskPriorityPicto) {
        this.setTaskPriorityPicto(taskPriorityPicto);
        return this;
    }

    public void setTaskPriorityPicto(byte[] taskPriorityPicto) {
        this.taskPriorityPicto = taskPriorityPicto;
    }

    public String getTaskPriorityPictoContentType() {
        return this.taskPriorityPictoContentType;
    }

    public TaskPriority taskPriorityPictoContentType(String taskPriorityPictoContentType) {
        this.taskPriorityPictoContentType = taskPriorityPictoContentType;
        return this;
    }

    public void setTaskPriorityPictoContentType(String taskPriorityPictoContentType) {
        this.taskPriorityPictoContentType = taskPriorityPictoContentType;
    }

    public String getNotes() {
        return this.notes;
    }

    public TaskPriority notes(String notes) {
        this.setNotes(notes);
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Set<Task> getTasks() {
        return this.tasks;
    }

    public void setTasks(Set<Task> tasks) {
        if (this.tasks != null) {
            this.tasks.forEach(i -> i.setTaskPriority(null));
        }
        if (tasks != null) {
            tasks.forEach(i -> i.setTaskPriority(this));
        }
        this.tasks = tasks;
    }

    public TaskPriority tasks(Set<Task> tasks) {
        this.setTasks(tasks);
        return this;
    }

    public TaskPriority addTask(Task task) {
        this.tasks.add(task);
        task.setTaskPriority(this);
        return this;
    }

    public TaskPriority removeTask(Task task) {
        this.tasks.remove(task);
        task.setTaskPriority(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TaskPriority)) {
            return false;
        }
        return id != null && id.equals(((TaskPriority) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TaskPriority{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", priority='" + getPriority() + "'" +
            ", taskPriorityPicto='" + getTaskPriorityPicto() + "'" +
            ", taskPriorityPictoContentType='" + getTaskPriorityPictoContentType() + "'" +
            ", notes='" + getNotes() + "'" +
            "}";
    }
}
