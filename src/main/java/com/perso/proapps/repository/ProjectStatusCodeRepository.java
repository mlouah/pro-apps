package com.perso.proapps.repository;

import com.perso.proapps.domain.ProjectStatusCode;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ProjectStatusCode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProjectStatusCodeRepository extends JpaRepository<ProjectStatusCode, Long> {}
