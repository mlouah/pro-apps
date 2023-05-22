package com.perso.proapps.repository;

import com.perso.proapps.domain.ProjectStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ProjectStatus entity.
 */
@Repository
public interface ProjectStatusRepository extends JpaRepository<ProjectStatus, Long> {
    default Optional<ProjectStatus> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<ProjectStatus> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<ProjectStatus> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct projectStatus from ProjectStatus projectStatus left join fetch projectStatus.projectName left join fetch projectStatus.projectStatusCode",
        countQuery = "select count(distinct projectStatus) from ProjectStatus projectStatus"
    )
    Page<ProjectStatus> findAllWithToOneRelationships(Pageable pageable);

    @Query(
        "select distinct projectStatus from ProjectStatus projectStatus left join fetch projectStatus.projectName left join fetch projectStatus.projectStatusCode"
    )
    List<ProjectStatus> findAllWithToOneRelationships();

    @Query(
        "select projectStatus from ProjectStatus projectStatus left join fetch projectStatus.projectName left join fetch projectStatus.projectStatusCode where projectStatus.id =:id"
    )
    Optional<ProjectStatus> findOneWithToOneRelationships(@Param("id") Long id);
}
