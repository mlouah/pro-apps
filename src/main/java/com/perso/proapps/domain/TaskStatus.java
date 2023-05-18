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
 * A TaskStatus.
 */
@Entity
@Table(name = "task_status")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TaskStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "status")
    private String status;

    @Lob
    @Column(name = "status_picto")
    private byte[] statusPicto;

    @Column(name = "status_picto_content_type")
    private String statusPictoContentType;

    @Column(name = "notes")
    private String notes;

    @OneToMany(mappedBy = "taskStatus")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "taskPriority", "taskStatus", "taskOwner", "taskMoM", "projectName" }, allowSetters = true)
    private Set<Task> tasks = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TaskStatus id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public TaskStatus code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return this.status;
    }

    public TaskStatus status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public byte[] getStatusPicto() {
        return this.statusPicto;
    }

    public TaskStatus statusPicto(byte[] statusPicto) {
        this.setStatusPicto(statusPicto);
        return this;
    }

    public void setStatusPicto(byte[] statusPicto) {
        this.statusPicto = statusPicto;
    }

    public String getStatusPictoContentType() {
        return this.statusPictoContentType;
    }

    public TaskStatus statusPictoContentType(String statusPictoContentType) {
        this.statusPictoContentType = statusPictoContentType;
        return this;
    }

    public void setStatusPictoContentType(String statusPictoContentType) {
        this.statusPictoContentType = statusPictoContentType;
    }

    public String getNotes() {
        return this.notes;
    }

    public TaskStatus notes(String notes) {
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
            this.tasks.forEach(i -> i.setTaskStatus(null));
        }
        if (tasks != null) {
            tasks.forEach(i -> i.setTaskStatus(this));
        }
        this.tasks = tasks;
    }

    public TaskStatus tasks(Set<Task> tasks) {
        this.setTasks(tasks);
        return this;
    }

    public TaskStatus addTask(Task task) {
        this.tasks.add(task);
        task.setTaskStatus(this);
        return this;
    }

    public TaskStatus removeTask(Task task) {
        this.tasks.remove(task);
        task.setTaskStatus(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TaskStatus)) {
            return false;
        }
        return id != null && id.equals(((TaskStatus) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TaskStatus{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", status='" + getStatus() + "'" +
            ", statusPicto='" + getStatusPicto() + "'" +
            ", statusPictoContentType='" + getStatusPictoContentType() + "'" +
            ", notes='" + getNotes() + "'" +
            "}";
    }
}
