package com.perso.proapps.service;

import com.perso.proapps.domain.Company;
import com.perso.proapps.repository.CompanyRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Company}.
 */
@Service
@Transactional
public class CompanyService {

    private final Logger log = LoggerFactory.getLogger(CompanyService.class);

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    /**
     * Save a company.
     *
     * @param company the entity to save.
     * @return the persisted entity.
     */
    public Company save(Company company) {
        log.debug("Request to save Company : {}", company);
        return companyRepository.save(company);
    }

    /**
     * Update a company.
     *
     * @param company the entity to save.
     * @return the persisted entity.
     */
    public Company update(Company company) {
        log.debug("Request to update Company : {}", company);
        return companyRepository.save(company);
    }

    /**
     * Partially update a company.
     *
     * @param company the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Company> partialUpdate(Company company) {
        log.debug("Request to partially update Company : {}", company);

        return companyRepository
            .findById(company.getId())
            .map(existingCompany -> {
                if (company.getName() != null) {
                    existingCompany.setName(company.getName());
                }
                if (company.getCode() != null) {
                    existingCompany.setCode(company.getCode());
                }
                if (company.getCompanyProfile() != null) {
                    existingCompany.setCompanyProfile(company.getCompanyProfile());
                }
                if (company.getUrl() != null) {
                    existingCompany.setUrl(company.getUrl());
                }
                if (company.getLogo() != null) {
                    existingCompany.setLogo(company.getLogo());
                }
                if (company.getLogoContentType() != null) {
                    existingCompany.setLogoContentType(company.getLogoContentType());
                }
                if (company.getDateCreation() != null) {
                    existingCompany.setDateCreation(company.getDateCreation());
                }
                if (company.getDateModify() != null) {
                    existingCompany.setDateModify(company.getDateModify());
                }
                if (company.getLastModifyBy() != null) {
                    existingCompany.setLastModifyBy(company.getLastModifyBy());
                }
                if (company.getCreatedBy() != null) {
                    existingCompany.setCreatedBy(company.getCreatedBy());
                }
                if (company.getNotes() != null) {
                    existingCompany.setNotes(company.getNotes());
                }

                return existingCompany;
            })
            .map(companyRepository::save);
    }

    /**
     * Get all the companies.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Company> findAll() {
        log.debug("Request to get all Companies");
        return companyRepository.findAll();
    }

    /**
     * Get one company by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Company> findOne(Long id) {
        log.debug("Request to get Company : {}", id);
        return companyRepository.findById(id);
    }

    /**
     * Delete the company by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Company : {}", id);
        companyRepository.deleteById(id);
    }
}
