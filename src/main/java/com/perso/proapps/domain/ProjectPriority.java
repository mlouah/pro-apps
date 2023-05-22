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
 * A ProjectPriority.
 */
@Entity
@Table(name = "project_priority")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ProjectPriority implements Serializable {

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

    @Column(name = "date_creation")
    private Instant dateCreation;

    @Column(name = "date_modify")
    private Instant dateModify;

    @Column(name = "last_modify_by")
    private String lastModifyBy;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "notes")
    private String notes;

    @OneToMany(mappedBy = "projectPriority")
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

    public ProjectPriority id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public ProjectPriority code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPriority() {
        return this.priority;
    }

    public ProjectPriority priority(String priority) {
        this.setPriority(priority);
        return this;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Instant getDateCreation() {
        return this.dateCreation;
    }

    public ProjectPriority dateCreation(Instant dateCreation) {
        this.setDateCreation(dateCreation);
        return this;
    }

    public void setDateCreation(Instant dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Instant getDateModify() {
        return this.dateModify;
    }

    public ProjectPriority dateModify(Instant dateModify) {
        this.setDateModify(dateModify);
        return this;
    }

    public void setDateModify(Instant dateModify) {
        this.dateModify = dateModify;
    }

    public String getLastModifyBy() {
        return this.lastModifyBy;
    }

    public ProjectPriority lastModifyBy(String lastModifyBy) {
        this.setLastModifyBy(lastModifyBy);
        return this;
    }

    public void setLastModifyBy(String lastModifyBy) {
        this.lastModifyBy = lastModifyBy;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public ProjectPriority createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getNotes() {
        return this.notes;
    }

    public ProjectPriority notes(String notes) {
        this.setNotes(notes);
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Set<Project> getProjects() {
        return this.projects;
    }

    public void setProjects(Set<Project> projects) {
        if (this.projects != null) {
            this.projects.forEach(i -> i.setProjectPriority(null));
        }
        if (projects != null) {
            projects.forEach(i -> i.setProjectPriority(this));
        }
        this.projects = projects;
    }

    public ProjectPriority projects(Set<Project> projects) {
        this.setProjects(projects);
        return this;
    }

    public ProjectPriority addProject(Project project) {
        this.projects.add(project);
        project.setProjectPriority(this);
        return this;
    }

    public ProjectPriority removeProject(Project project) {
        this.projects.remove(project);
        project.setProjectPriority(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProjectPriority)) {
            return false;
        }
        return id != null && id.equals(((ProjectPriority) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProjectPriority{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", priority='" + getPriority() + "'" +
            ", dateCreation='" + getDateCreation() + "'" +
            ", dateModify='" + getDateModify() + "'" +
            ", lastModifyBy='" + getLastModifyBy() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", notes='" + getNotes() + "'" +
            "}";
    }
}
