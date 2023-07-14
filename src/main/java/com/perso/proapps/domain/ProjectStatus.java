package com.perso.proapps.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ProjectStatus.
 */
@Entity
@Table(name = "project_status")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ProjectStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "status_date")
    private LocalDate statusDate;

    @Column(name = "status_title")
    private String statusTitle;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "updated_progress")
    private Double updatedProgress;

    @Column(name = "updated_workload_eac")
    private Integer updatedWorkloadEAC;

    @Column(name = "updated_end_date")
    private LocalDate updatedEndDate;

    @Column(name = "updated_cost")
    private Double updatedCost;

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

    @ManyToOne
    @JsonIgnoreProperties(
        value = {
            "moMTitles", "tasks", "projectPriority", "projectCategory", "clientCode", "internalProjectManager", "company", "projectStatus",
        },
        allowSetters = true
    )
    private Project projectName;

    @ManyToOne
    @JsonIgnoreProperties(value = { "projectStatuses" }, allowSetters = true)
    private ProjectStatusCode projectStatusCode;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ProjectStatus id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStatusDate() {
        return this.statusDate;
    }

    public ProjectStatus statusDate(LocalDate statusDate) {
        this.setStatusDate(statusDate);
        return this;
    }

    public void setStatusDate(LocalDate statusDate) {
        this.statusDate = statusDate;
    }

    public String getStatusTitle() {
        return this.statusTitle;
    }

    public ProjectStatus statusTitle(String statusTitle) {
        this.setStatusTitle(statusTitle);
        return this;
    }

    public void setStatusTitle(String statusTitle) {
        this.statusTitle = statusTitle;
    }

    public Double getUpdatedProgress() {
        return this.updatedProgress;
    }

    public ProjectStatus updatedProgress(Double updatedProgress) {
        this.setUpdatedProgress(updatedProgress);
        return this;
    }

    public void setUpdatedProgress(Double updatedProgress) {
        this.updatedProgress = updatedProgress;
    }

    public Integer getUpdatedWorkloadEAC() {
        return this.updatedWorkloadEAC;
    }

    public ProjectStatus updatedWorkloadEAC(Integer updatedWorkloadEAC) {
        this.setUpdatedWorkloadEAC(updatedWorkloadEAC);
        return this;
    }

    public void setUpdatedWorkloadEAC(Integer updatedWorkloadEAC) {
        this.updatedWorkloadEAC = updatedWorkloadEAC;
    }

    public LocalDate getUpdatedEndDate() {
        return this.updatedEndDate;
    }

    public ProjectStatus updatedEndDate(LocalDate updatedEndDate) {
        this.setUpdatedEndDate(updatedEndDate);
        return this;
    }

    public void setUpdatedEndDate(LocalDate updatedEndDate) {
        this.updatedEndDate = updatedEndDate;
    }

    public Double getUpdatedCost() {
        return this.updatedCost;
    }

    public ProjectStatus updatedCost(Double updatedCost) {
        this.setUpdatedCost(updatedCost);
        return this;
    }

    public void setUpdatedCost(Double updatedCost) {
        this.updatedCost = updatedCost;
    }

    public Instant getDateCreation() {
        return this.dateCreation;
    }

    public ProjectStatus dateCreation(Instant dateCreation) {
        this.setDateCreation(dateCreation);
        return this;
    }

    public void setDateCreation(Instant dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Instant getDateModify() {
        return this.dateModify;
    }

    public ProjectStatus dateModify(Instant dateModify) {
        this.setDateModify(dateModify);
        return this;
    }

    public void setDateModify(Instant dateModify) {
        this.dateModify = dateModify;
    }

    public String getLastModifyBy() {
        return this.lastModifyBy;
    }

    public ProjectStatus lastModifyBy(String lastModifyBy) {
        this.setLastModifyBy(lastModifyBy);
        return this;
    }

    public void setLastModifyBy(String lastModifyBy) {
        this.lastModifyBy = lastModifyBy;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public ProjectStatus createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getNotes() {
        return this.notes;
    }

    public ProjectStatus notes(String notes) {
        this.setNotes(notes);
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Project getProjectName() {
        return this.projectName;
    }

    public void setProjectName(Project project) {
        this.projectName = project;
    }

    public ProjectStatus projectName(Project project) {
        this.setProjectName(project);
        return this;
    }

    public ProjectStatusCode getProjectStatusCode() {
        return this.projectStatusCode;
    }

    public void setProjectStatusCode(ProjectStatusCode projectStatusCode) {
        this.projectStatusCode = projectStatusCode;
    }

    public ProjectStatus projectStatusCode(ProjectStatusCode projectStatusCode) {
        this.setProjectStatusCode(projectStatusCode);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProjectStatus)) {
            return false;
        }
        return id != null && id.equals(((ProjectStatus) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProjectStatus{" +
            "id=" + getId() +
            ", statusDate='" + getStatusDate() + "'" +
            ", statusTitle='" + getStatusTitle() + "'" +
            ", updatedProgress=" + getUpdatedProgress() +
            ", updatedWorkloadEAC=" + getUpdatedWorkloadEAC() +
            ", updatedEndDate='" + getUpdatedEndDate() + "'" +
            ", updatedCost=" + getUpdatedCost() +
            ", dateCreation='" + getDateCreation() + "'" +
            ", dateModify='" + getDateModify() + "'" +
            ", lastModifyBy='" + getLastModifyBy() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", notes='" + getNotes() + "'" +
            "}";
    }
}
