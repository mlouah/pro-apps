package com.perso.proapps.repository;

import com.perso.proapps.domain.Project;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Project entity.
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    default Optional<Project> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<Project> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<Project> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    // query 1
    @Query(
        value = "select distinct project from Project project left join fetch project.projectPriority left join fetch project.projectCategory left join fetch project.clientCode left join fetch project.internalProjectManager left join fetch project.company",
        countQuery = "select count(distinct project) from Project project"
    )
    Page<Project> findAllWithToOneRelationships(Pageable pageable);

    // query 2
    @Query(
        "select distinct project from Project project left join fetch project.projectPriority left join fetch project.projectCategory left join fetch project.clientCode left join fetch project.internalProjectManager left join fetch project.company WHERE  project.id <5 "
    )
    List<Project> findAllWithToOneRelationships();

    // query 3
    @Query(
        "select project from Project project left join fetch project.projectPriority left join fetch project.projectCategory left join fetch project.clientCode left join fetch project.internalProjectManager left join fetch project.company where project.id =:id"
    )
    Optional<Project> findOneWithToOneRelationships(@Param("id") Long id);

    // query 4 - mlouah new
    @Query(
        "select distinct project from Project project left join fetch project.projectPriority left join fetch project.projectCategory left join fetch project.clientCode left join fetch project.internalProjectManager left join fetch project.company WHERE  project.company.code ='S2M' "
    )
    List<Project> findAll();
}
