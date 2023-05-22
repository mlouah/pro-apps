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
 * A MoM.
 */
@Entity
@Table(name = "mo_m")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MoM implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "meeting_date")
    private LocalDate meetingDate;

    @Column(name = "minutes_writing_date")
    private LocalDate minutesWritingDate;

    @Lob
    @Column(name = "meeting_objectif")
    private String meetingObjectif;

    @Lob
    @Column(name = "meeting_doc_references")
    private String meetingDocReferences;

    @Column(name = "is_mo_m_actions_closed")
    private Boolean isMoMActionsClosed;

    @Lob
    @Column(name = "subject_decisions")
    private String subjectDecisions;

    @Lob
    @Column(name = "meeting_photo_capture")
    private byte[] meetingPhotoCapture;

    @Column(name = "meeting_photo_capture_content_type")
    private String meetingPhotoCaptureContentType;

    @Column(name = "date_creation")
    private Instant dateCreation;

    @Column(name = "date_modify")
    private Instant dateModify;

    @Column(name = "last_modify_by")
    private String lastModifyBy;

    @Column(name = "created_by")
    private String createdBy;

    @Lob
    @Column(name = "attendees")
    private String attendees;

    @Lob
    @Column(name = "notes")
    private String notes;

    @OneToMany(mappedBy = "taskMoM")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "taskStatus", "taskMoM", "projectName", "taskOwner" }, allowSetters = true)
    private Set<Task> tasks = new HashSet<>();

    @ManyToOne
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
    private Project projet;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public MoM id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public MoM title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getMeetingDate() {
        return this.meetingDate;
    }

    public MoM meetingDate(LocalDate meetingDate) {
        this.setMeetingDate(meetingDate);
        return this;
    }

    public void setMeetingDate(LocalDate meetingDate) {
        this.meetingDate = meetingDate;
    }

    public LocalDate getMinutesWritingDate() {
        return this.minutesWritingDate;
    }

    public MoM minutesWritingDate(LocalDate minutesWritingDate) {
        this.setMinutesWritingDate(minutesWritingDate);
        return this;
    }

    public void setMinutesWritingDate(LocalDate minutesWritingDate) {
        this.minutesWritingDate = minutesWritingDate;
    }

    public String getMeetingObjectif() {
        return this.meetingObjectif;
    }

    public MoM meetingObjectif(String meetingObjectif) {
        this.setMeetingObjectif(meetingObjectif);
        return this;
    }

    public void setMeetingObjectif(String meetingObjectif) {
        this.meetingObjectif = meetingObjectif;
    }

    public String getMeetingDocReferences() {
        return this.meetingDocReferences;
    }

    public MoM meetingDocReferences(String meetingDocReferences) {
        this.setMeetingDocReferences(meetingDocReferences);
        return this;
    }

    public void setMeetingDocReferences(String meetingDocReferences) {
        this.meetingDocReferences = meetingDocReferences;
    }

    public Boolean getIsMoMActionsClosed() {
        return this.isMoMActionsClosed;
    }

    public MoM isMoMActionsClosed(Boolean isMoMActionsClosed) {
        this.setIsMoMActionsClosed(isMoMActionsClosed);
        return this;
    }

    public void setIsMoMActionsClosed(Boolean isMoMActionsClosed) {
        this.isMoMActionsClosed = isMoMActionsClosed;
    }

    public String getSubjectDecisions() {
        return this.subjectDecisions;
    }

    public MoM subjectDecisions(String subjectDecisions) {
        this.setSubjectDecisions(subjectDecisions);
        return this;
    }

    public void setSubjectDecisions(String subjectDecisions) {
        this.subjectDecisions = subjectDecisions;
    }

    public byte[] getMeetingPhotoCapture() {
        return this.meetingPhotoCapture;
    }

    public MoM meetingPhotoCapture(byte[] meetingPhotoCapture) {
        this.setMeetingPhotoCapture(meetingPhotoCapture);
        return this;
    }

    public void setMeetingPhotoCapture(byte[] meetingPhotoCapture) {
        this.meetingPhotoCapture = meetingPhotoCapture;
    }

    public String getMeetingPhotoCaptureContentType() {
        return this.meetingPhotoCaptureContentType;
    }

    public MoM meetingPhotoCaptureContentType(String meetingPhotoCaptureContentType) {
        this.meetingPhotoCaptureContentType = meetingPhotoCaptureContentType;
        return this;
    }

    public void setMeetingPhotoCaptureContentType(String meetingPhotoCaptureContentType) {
        this.meetingPhotoCaptureContentType = meetingPhotoCaptureContentType;
    }

    public Instant getDateCreation() {
        return this.dateCreation;
    }

    public MoM dateCreation(Instant dateCreation) {
        this.setDateCreation(dateCreation);
        return this;
    }

    public void setDateCreation(Instant dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Instant getDateModify() {
        return this.dateModify;
    }

    public MoM dateModify(Instant dateModify) {
        this.setDateModify(dateModify);
        return this;
    }

    public void setDateModify(Instant dateModify) {
        this.dateModify = dateModify;
    }

    public String getLastModifyBy() {
        return this.lastModifyBy;
    }

    public MoM lastModifyBy(String lastModifyBy) {
        this.setLastModifyBy(lastModifyBy);
        return this;
    }

    public void setLastModifyBy(String lastModifyBy) {
        this.lastModifyBy = lastModifyBy;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public MoM createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getAttendees() {
        return this.attendees;
    }

    public MoM attendees(String attendees) {
        this.setAttendees(attendees);
        return this;
    }

    public void setAttendees(String attendees) {
        this.attendees = attendees;
    }

    public String getNotes() {
        return this.notes;
    }

    public MoM notes(String notes) {
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
            this.tasks.forEach(i -> i.setTaskMoM(null));
        }
        if (tasks != null) {
            tasks.forEach(i -> i.setTaskMoM(this));
        }
        this.tasks = tasks;
    }

    public MoM tasks(Set<Task> tasks) {
        this.setTasks(tasks);
        return this;
    }

    public MoM addTasks(Task task) {
        this.tasks.add(task);
        task.setTaskMoM(this);
        return this;
    }

    public MoM removeTasks(Task task) {
        this.tasks.remove(task);
        task.setTaskMoM(null);
        return this;
    }

    public Project getProjet() {
        return this.projet;
    }

    public void setProjet(Project project) {
        this.projet = project;
    }

    public MoM projet(Project project) {
        this.setProjet(project);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MoM)) {
            return false;
        }
        return id != null && id.equals(((MoM) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MoM{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", meetingDate='" + getMeetingDate() + "'" +
            ", minutesWritingDate='" + getMinutesWritingDate() + "'" +
            ", meetingObjectif='" + getMeetingObjectif() + "'" +
            ", meetingDocReferences='" + getMeetingDocReferences() + "'" +
            ", isMoMActionsClosed='" + getIsMoMActionsClosed() + "'" +
            ", subjectDecisions='" + getSubjectDecisions() + "'" +
            ", meetingPhotoCapture='" + getMeetingPhotoCapture() + "'" +
            ", meetingPhotoCaptureContentType='" + getMeetingPhotoCaptureContentType() + "'" +
            ", dateCreation='" + getDateCreation() + "'" +
            ", dateModify='" + getDateModify() + "'" +
            ", lastModifyBy='" + getLastModifyBy() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", attendees='" + getAttendees() + "'" +
            ", notes='" + getNotes() + "'" +
            "}";
    }
}
