package com.perso.proapps.repository;

import com.perso.proapps.domain.ProjectPriority;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ProjectPriority entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProjectPriorityRepository extends JpaRepository<ProjectPriority, Long> {}
