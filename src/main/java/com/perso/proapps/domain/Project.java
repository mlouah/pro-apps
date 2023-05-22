package com.perso.proapps.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Project.
 */
@Entity
@Table(name = "project")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "real_endt_date")
    private LocalDate realEndtDate;

    @Lob
    @Column(name = "project_description")
    private String projectDescription;

    @Column(name = "initial_end_date")
    private LocalDate initialEndDate;

    @Column(name = "initial_cost")
    private Double initialCost;

    @Column(name = "initial_work_load")
    private Integer initialWorkLoad;

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

    @OneToMany(mappedBy = "projet")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "tasks", "projet" }, allowSetters = true)
    private Set<MoM> moMTitles = new HashSet<>();

    @OneToMany(mappedBy = "projectName")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "taskStatus", "taskMoM", "projectName", "taskOwner" }, allowSetters = true)
    private Set<Task> tasks = new HashSet<>();

    @OneToMany(mappedBy = "projectName")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "projectName", "projectStatusCode" }, allowSetters = true)
    private Set<ProjectStatus> projectStatuses = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "projects" }, allowSetters = true)
    private ProjectPriority projectPriority;

    @ManyToOne
    @JsonIgnoreProperties(value = { "projects" }, allowSetters = true)
    private ProjectCategory projectCategory;

    @ManyToOne
    @JsonIgnoreProperties(value = { "projects" }, allowSetters = true)
    private Client clientCode;

    @ManyToOne
    @JsonIgnoreProperties(value = { "tasks", "projects" }, allowSetters = true)
    private Person internalProjectManager;

    @ManyToOne
    @JsonIgnoreProperties(value = { "projects" }, allowSetters = true)
    private Company company;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Project id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Project name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public Project code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public Project startDate(LocalDate startDate) {
        this.setStartDate(startDate);
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getRealEndtDate() {
        return this.realEndtDate;
    }

    public Project realEndtDate(LocalDate realEndtDate) {
        this.setRealEndtDate(realEndtDate);
        return this;
    }

    public void setRealEndtDate(LocalDate realEndtDate) {
        this.realEndtDate = realEndtDate;
    }

    public String getProjectDescription() {
        return this.projectDescription;
    }

    public Project projectDescription(String projectDescription) {
        this.setProjectDescription(projectDescription);
        return this;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public LocalDate getInitialEndDate() {
        return this.initialEndDate;
    }

    public Project initialEndDate(LocalDate initialEndDate) {
        this.setInitialEndDate(initialEndDate);
        return this;
    }

    public void setInitialEndDate(LocalDate initialEndDate) {
        this.initialEndDate = initialEndDate;
    }

    public Double getInitialCost() {
        return this.initialCost;
    }

    public Project initialCost(Double initialCost) {
        this.setInitialCost(initialCost);
        return this;
    }

    public void setInitialCost(Double initialCost) {
        this.initialCost = initialCost;
    }

    public Integer getInitialWorkLoad() {
        return this.initialWorkLoad;
    }

    public Project initialWorkLoad(Integer initialWorkLoad) {
        this.setInitialWorkLoad(initialWorkLoad);
        return this;
    }

    public void setInitialWorkLoad(Integer initialWorkLoad) {
        this.initialWorkLoad = initialWorkLoad;
    }

    public Instant getDateCreation() {
        return this.dateCreation;
    }

    public Project dateCreation(Instant dateCreation) {
        this.setDateCreation(dateCreation);
        return this;
    }

    public void setDateCreation(Instant dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Instant getDateModify() {
        return this.dateModify;
    }

    public Project dateModify(Instant dateModify) {
        this.setDateModify(dateModify);
        return this;
    }

    public void setDateModify(Instant dateModify) {
        this.dateModify = dateModify;
    }

    public String getLastModifyBy() {
        return this.lastModifyBy;
    }

    public Project lastModifyBy(String lastModifyBy) {
        this.setLastModifyBy(lastModifyBy);
        return this;
    }

    public void setLastModifyBy(String lastModifyBy) {
        this.lastModifyBy = lastModifyBy;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public Project createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getNotes() {
        return this.notes;
    }

    public Project notes(String notes) {
        this.setNotes(notes);
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Set<MoM> getMoMTitles() {
        return this.moMTitles;
    }

    public void setMoMTitles(Set<MoM> moMS) {
        if (this.moMTitles != null) {
            this.moMTitles.forEach(i -> i.setProjet(null));
        }
        if (moMS != null) {
            moMS.forEach(i -> i.setProjet(this));
        }
        this.moMTitles = moMS;
    }

    public Project moMTitles(Set<MoM> moMS) {
        this.setMoMTitles(moMS);
        return this;
    }

    public Project addMoMTitle(MoM moM) {
        this.moMTitles.add(moM);
        moM.setProjet(this);
        return this;
    }

    public Project removeMoMTitle(MoM moM) {
        this.moMTitles.remove(moM);
        moM.setProjet(null);
        return this;
    }

    public Set<Task> getTasks() {
        return this.tasks;
    }

    public void setTasks(Set<Task> tasks) {
        if (this.tasks != null) {
            this.tasks.forEach(i -> i.setProjectName(null));
        }
        if (tasks != null) {
            tasks.forEach(i -> i.setProjectName(this));
        }
        this.tasks = tasks;
    }

    public Project tasks(Set<Task> tasks) {
        this.setTasks(tasks);
        return this;
    }

    public Project addTasks(Task task) {
        this.tasks.add(task);
        task.setProjectName(this);
        return this;
    }

    public Project removeTasks(Task task) {
        this.tasks.remove(task);
        task.setProjectName(null);
        return this;
    }

    public Set<ProjectStatus> getProjectStatuses() {
        return this.projectStatuses;
    }

    public void setProjectStatuses(Set<ProjectStatus> projectStatuses) {
        if (this.projectStatuses != null) {
            this.projectStatuses.forEach(i -> i.setProjectName(null));
        }
        if (projectStatuses != null) {
            projectStatuses.forEach(i -> i.setProjectName(this));
        }
        this.projectStatuses = projectStatuses;
    }

    public Project projectStatuses(Set<ProjectStatus> projectStatuses) {
        this.setProjectStatuses(projectStatuses);
        return this;
    }

    public Project addProjectStatus(ProjectStatus projectStatus) {
        this.projectStatuses.add(projectStatus);
        projectStatus.setProjectName(this);
        return this;
    }

    public Project removeProjectStatus(ProjectStatus projectStatus) {
        this.projectStatuses.remove(projectStatus);
        projectStatus.setProjectName(null);
        return this;
    }

    public ProjectPriority getProjectPriority() {
        return this.projectPriority;
    }

    public void setProjectPriority(ProjectPriority projectPriority) {
        this.projectPriority = projectPriority;
    }

    public Project projectPriority(ProjectPriority projectPriority) {
        this.setProjectPriority(projectPriority);
        return this;
    }

    public ProjectCategory getProjectCategory() {
        return this.projectCategory;
    }

    public void setProjectCategory(ProjectCategory projectCategory) {
        this.projectCategory = projectCategory;
    }

    public Project projectCategory(ProjectCategory projectCategory) {
        this.setProjectCategory(projectCategory);
        return this;
    }

    public Client getClientCode() {
        return this.clientCode;
    }

    public void setClientCode(Client client) {
        this.clientCode = client;
    }

    public Project clientCode(Client client) {
        this.setClientCode(client);
        return this;
    }

    public Person getInternalProjectManager() {
        return this.internalProjectManager;
    }

    public void setInternalProjectManager(Person person) {
        this.internalProjectManager = person;
    }

    public Project internalProjectManager(Person person) {
        this.setInternalProjectManager(person);
        return this;
    }

    public Company getCompany() {
        return this.company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Project company(Company company) {
        this.setCompany(company);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Project)) {
            return false;
        }
        return id != null && id.equals(((Project) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Project{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", code='" + getCode() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", realEndtDate='" + getRealEndtDate() + "'" +
            ", projectDescription='" + getProjectDescription() + "'" +
            ", initialEndDate='" + getInitialEndDate() + "'" +
            ", initialCost=" + getInitialCost() +
            ", initialWorkLoad=" + getInitialWorkLoad() +
            ", dateCreation='" + getDateCreation() + "'" +
            ", dateModify='" + getDateModify() + "'" +
            ", lastModifyBy='" + getLastModifyBy() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", notes='" + getNotes() + "'" +
            "}";
    }
}
