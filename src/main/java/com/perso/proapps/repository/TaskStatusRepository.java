package com.perso.proapps.repository;

import com.perso.proapps.domain.TaskStatus;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TaskStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaskStatusRepository extends JpaRepository<TaskStatus, Long> {}
