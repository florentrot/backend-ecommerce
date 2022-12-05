package com.florentin.ecomerce.config;

import com.florentin.ecomerce.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * I use this class for HttpMethod control
 */
@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    @Value("${allowed.origins}")
    private String[] theAllowedOrigins;

    private EntityManager entityManager;

    @Autowired
    public MyDataRestConfig(EntityManager theEntityManager) {
        this.entityManager=theEntityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {



        HttpMethod[] theUnsupportedActions = {  HttpMethod.PUT,
                                                HttpMethod.POST,
                                                HttpMethod.DELETE,
                                                HttpMethod.PATCH};
        // disable HTTP methods for Product: PUT, POST, PATCH and DELETE
        disableHttpMethods(config, theUnsupportedActions, Product.class);
        disableHttpMethods(config, theUnsupportedActions, ProductCategory.class);
        disableHttpMethods(config, theUnsupportedActions, Country.class);
        disableHttpMethods(config, theUnsupportedActions, County.class);
        disableHttpMethods(config, theUnsupportedActions, Order.class);


        //call an internal helper method
        exposeIds(config);

        //configure cors mapping
        cors.addMapping("/api/**").allowedOrigins(theAllowedOrigins);
    }

    private void disableHttpMethods(RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions,Class theClass) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions)))
                .withCollectionExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions)));
    }

    /**
     * I need to understand this... or not...
     */
    private void exposeIds(RepositoryRestConfiguration config) {
        //expose entity ids
        //

        // - get a list of all entity classes from the entity manager
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        // - create and array of the entity types
        List<Class> entityClasses = new ArrayList<>();

        // - get the entity types for the entities
        for(EntityType<?> tempEntityType : entities) {
            entityClasses.add(tempEntityType.getJavaType());
        }

        // - expose the entity ids for the array of entity/domain types
        Class[] domainType = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainType);
    }


}
