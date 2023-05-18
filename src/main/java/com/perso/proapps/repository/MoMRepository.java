package com.perso.proapps.repository;

import com.perso.proapps.domain.MoM;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the MoM entity.
 */
@Repository
public interface MoMRepository extends JpaRepository<MoM, Long> {
    default Optional<MoM> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<MoM> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<MoM> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(value = "select distinct moM from MoM moM left join fetch moM.projet", countQuery = "select count(distinct moM) from MoM moM")
    Page<MoM> findAllWithToOneRelationships(Pageable pageable);

    @Query("select distinct moM from MoM moM left join fetch moM.projet")
    List<MoM> findAllWithToOneRelationships();

    @Query("select moM from MoM moM left join fetch moM.projet where moM.id =:id")
    Optional<MoM> findOneWithToOneRelationships(@Param("id") Long id);
}
