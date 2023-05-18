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
 * A Task.
 */
@Entity
@Table(name = "task")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "subject")
    private String subject;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "deal_line")
    private LocalDate dealLine;

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

    @ManyToOne
    @JsonIgnoreProperties(value = { "tasks" }, allowSetters = true)
    private TaskPriority taskPriority;

    @ManyToOne
    @JsonIgnoreProperties(value = { "tasks" }, allowSetters = true)
    private TaskStatus taskStatus;

    @ManyToOne
    @JsonIgnoreProperties(value = { "tasks" }, allowSetters = true)
    private Person taskOwner;

    @ManyToOne
    @JsonIgnoreProperties(value = { "tasks", "projet" }, allowSetters = true)
    private MoM taskMoM;

    @ManyToOne
    @JsonIgnoreProperties(value = { "moMTitles", "tasks" }, allowSetters = true)
    private Project projectName;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Task id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return this.subject;
    }

    public Task subject(String subject) {
        this.setSubject(subject);
        return this;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTitle() {
        return this.title;
    }

    public Task title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDealLine() {
        return this.dealLine;
    }

    public Task dealLine(LocalDate dealLine) {
        this.setDealLine(dealLine);
        return this;
    }

    public void setDealLine(LocalDate dealLine) {
        this.dealLine = dealLine;
    }

    public Instant getDateCreation() {
        return this.dateCreation;
    }

    public Task dateCreation(Instant dateCreation) {
        this.setDateCreation(dateCreation);
        return this;
    }

    public void setDateCreation(Instant dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Instant getDateModify() {
        return this.dateModify;
    }

    public Task dateModify(Instant dateModify) {
        this.setDateModify(dateModify);
        return this;
    }

    public void setDateModify(Instant dateModify) {
        this.dateModify = dateModify;
    }

    public String getLastModifyBy() {
        return this.lastModifyBy;
    }

    public Task lastModifyBy(String lastModifyBy) {
        this.setLastModifyBy(lastModifyBy);
        return this;
    }

    public void setLastModifyBy(String lastModifyBy) {
        this.lastModifyBy = lastModifyBy;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public Task createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getNotes() {
        return this.notes;
    }

    public Task notes(String notes) {
        this.setNotes(notes);
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public TaskPriority getTaskPriority() {
        return this.taskPriority;
    }

    public void setTaskPriority(TaskPriority taskPriority) {
        this.taskPriority = taskPriority;
    }

    public Task taskPriority(TaskPriority taskPriority) {
        this.setTaskPriority(taskPriority);
        return this;
    }

    public TaskStatus getTaskStatus() {
        return this.taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Task taskStatus(TaskStatus taskStatus) {
        this.setTaskStatus(taskStatus);
        return this;
    }

    public Person getTaskOwner() {
        return this.taskOwner;
    }

    public void setTaskOwner(Person person) {
        this.taskOwner = person;
    }

    public Task taskOwner(Person person) {
        this.setTaskOwner(person);
        return this;
    }

    public MoM getTaskMoM() {
        return this.taskMoM;
    }

    public void setTaskMoM(MoM moM) {
        this.taskMoM = moM;
    }

    public Task taskMoM(MoM moM) {
        this.setTaskMoM(moM);
        return this;
    }

    public Project getProjectName() {
        return this.projectName;
    }

    public void setProjectName(Project project) {
        this.projectName = project;
    }

    public Task projectName(Project project) {
        this.setProjectName(project);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Task)) {
            return false;
        }
        return id != null && id.equals(((Task) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Task{" +
            "id=" + getId() +
            ", subject='" + getSubject() + "'" +
            ", title='" + getTitle() + "'" +
            ", dealLine='" + getDealLine() + "'" +
            ", dateCreation='" + getDateCreation() + "'" +
            ", dateModify='" + getDateModify() + "'" +
            ", lastModifyBy='" + getLastModifyBy() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", notes='" + getNotes() + "'" +
            "}";
    }
}
