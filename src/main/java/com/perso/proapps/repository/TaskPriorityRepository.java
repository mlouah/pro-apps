package com.perso.proapps.repository;

import com.perso.proapps.domain.TaskPriority;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TaskPriority entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaskPriorityRepository extends JpaRepository<TaskPriority, Long> {}
