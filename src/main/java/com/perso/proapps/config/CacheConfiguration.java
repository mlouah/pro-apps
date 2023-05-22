package com.perso.proapps.config;

import java.time.Duration;
import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;
import org.hibernate.cache.jcache.ConfigSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.*;
import tech.jhipster.config.JHipsterProperties;
import tech.jhipster.config.cache.PrefixedKeyGenerator;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration =
            Eh107Configuration.fromEhcacheCacheConfiguration(
                CacheConfigurationBuilder
                    .newCacheConfigurationBuilder(Object.class, Object.class, ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                    .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                    .build()
            );
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.perso.proapps.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.perso.proapps.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.perso.proapps.domain.User.class.getName());
            createCache(cm, com.perso.proapps.domain.Authority.class.getName());
            createCache(cm, com.perso.proapps.domain.User.class.getName() + ".authorities");
            createCache(cm, com.perso.proapps.domain.MoM.class.getName());
            createCache(cm, com.perso.proapps.domain.MoM.class.getName() + ".tasks");
            createCache(cm, com.perso.proapps.domain.Task.class.getName());
            createCache(cm, com.perso.proapps.domain.Person.class.getName());
            createCache(cm, com.perso.proapps.domain.Person.class.getName() + ".tasks");
            createCache(cm, com.perso.proapps.domain.Person.class.getName() + ".projects");
            createCache(cm, com.perso.proapps.domain.Client.class.getName());
            createCache(cm, com.perso.proapps.domain.Client.class.getName() + ".projects");
            createCache(cm, com.perso.proapps.domain.Project.class.getName());
            createCache(cm, com.perso.proapps.domain.Project.class.getName() + ".moMTitles");
            createCache(cm, com.perso.proapps.domain.Project.class.getName() + ".tasks");
            createCache(cm, com.perso.proapps.domain.Project.class.getName() + ".projectStatuses");
            createCache(cm, com.perso.proapps.domain.TaskStatus.class.getName());
            createCache(cm, com.perso.proapps.domain.TaskStatus.class.getName() + ".tasks");
            createCache(cm, com.perso.proapps.domain.Company.class.getName());
            createCache(cm, com.perso.proapps.domain.Company.class.getName() + ".projects");
            createCache(cm, com.perso.proapps.domain.ProjectStatus.class.getName());
            createCache(cm, com.perso.proapps.domain.ProjectStatusCode.class.getName());
            createCache(cm, com.perso.proapps.domain.ProjectStatusCode.class.getName() + ".projectStatuses");
            createCache(cm, com.perso.proapps.domain.ProjectPriority.class.getName());
            createCache(cm, com.perso.proapps.domain.ProjectPriority.class.getName() + ".projects");
            createCache(cm, com.perso.proapps.domain.ProjectCategory.class.getName());
            createCache(cm, com.perso.proapps.domain.ProjectCategory.class.getName() + ".projects");
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache != null) {
            cache.clear();
        } else {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}
