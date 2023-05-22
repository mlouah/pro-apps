package com.perso.proapps.repository;

import com.perso.proapps.domain.ProjectCategory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ProjectCategory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProjectCategoryRepository extends JpaRepository<ProjectCategory, Long> {}
