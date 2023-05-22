package com.perso.proapps.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Person.
 */
@Entity
@Table(name = "person")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "is_external")
    private Boolean isExternal;

    @Column(name = "date_creation")
    private Instant dateCreation;

    @Column(name = "date_modify")
    private Instant dateModify;

    @Column(name = "last_modify_by")
    private String lastModifyBy;

    @Column(name = "created_by")
    private String createdBy;

    @Lob
    @Column(name = "notes")
    private String notes;

    @OneToMany(mappedBy = "taskOwner")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "taskStatus", "taskMoM", "projectName", "taskOwner" }, allowSetters = true)
    private Set<Task> tasks = new HashSet<>();

    @OneToMany(mappedBy = "internalProjectManager")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = {
            "moMTitles",
            "tasks",
            "projectStatuses",
            "projectPriority",
            "projectCategory",
            "clientCode",
            "internalProjectManager",
            "company",
        },
        allowSetters = true
    )
    private Set<Project> projects = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Person id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Person name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public Person code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getIsExternal() {
        return this.isExternal;
    }

    public Person isExternal(Boolean isExternal) {
        this.setIsExternal(isExternal);
        return this;
    }

    public void setIsExternal(Boolean isExternal) {
        this.isExternal = isExternal;
    }

    public Instant getDateCreation() {
        return this.dateCreation;
    }

    public Person dateCreation(Instant dateCreation) {
        this.setDateCreation(dateCreation);
        return this;
    }

    public void setDateCreation(Instant dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Instant getDateModify() {
        return this.dateModify;
    }

    public Person dateModify(Instant dateModify) {
        this.setDateModify(dateModify);
        return this;
    }

    public void setDateModify(Instant dateModify) {
        this.dateModify = dateModify;
    }

    public String getLastModifyBy() {
        return this.lastModifyBy;
    }

    public Person lastModifyBy(String lastModifyBy) {
        this.setLastModifyBy(lastModifyBy);
        return this;
    }

    public void setLastModifyBy(String lastModifyBy) {
        this.lastModifyBy = lastModifyBy;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public Person createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getNotes() {
        return this.notes;
    }

    public Person notes(String notes) {
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
            this.tasks.forEach(i -> i.setTaskOwner(null));
        }
        if (tasks != null) {
            tasks.forEach(i -> i.setTaskOwner(this));
        }
        this.tasks = tasks;
    }

    public Person tasks(Set<Task> tasks) {
        this.setTasks(tasks);
        return this;
    }

    public Person addTask(Task task) {
        this.tasks.add(task);
        task.setTaskOwner(this);
        return this;
    }

    public Person removeTask(Task task) {
        this.tasks.remove(task);
        task.setTaskOwner(null);
        return this;
    }

    public Set<Project> getProjects() {
        return this.projects;
    }

    public void setProjects(Set<Project> projects) {
        if (this.projects != null) {
            this.projects.forEach(i -> i.setInternalProjectManager(null));
        }
        if (projects != null) {
            projects.forEach(i -> i.setInternalProjectManager(this));
        }
        this.projects = projects;
    }

    public Person projects(Set<Project> projects) {
        this.setProjects(projects);
        return this;
    }

    public Person addProject(Project project) {
        this.projects.add(project);
        project.setInternalProjectManager(this);
        return this;
    }

    public Person removeProject(Project project) {
        this.projects.remove(project);
        project.setInternalProjectManager(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Person)) {
            return false;
        }
        return id != null && id.equals(((Person) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Person{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", code='" + getCode() + "'" +
            ", isExternal='" + getIsExternal() + "'" +
            ", dateCreation='" + getDateCreation() + "'" +
            ", dateModify='" + getDateModify() + "'" +
            ", lastModifyBy='" + getLastModifyBy() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", notes='" + getNotes() + "'" +
            "}";
    }
}
