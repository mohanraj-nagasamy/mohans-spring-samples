package com.jsf2.test.app.repository;

import com.jsf2.test.app.domain.Person;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Dimitar Makariev
 */
@Repository
public class PersonRepository {

    private EntityManager entityManager;

    @PersistenceContext
    void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @RolesAllowed(value = {"ROLE_USER"})
    @Transactional
    public void delete(Long id) {
        final Person forDelete = entityManager.getReference(Person.class, id);
        entityManager.remove(forDelete);
    }

    @RolesAllowed(value = {"ROLE_USER"})
    @Transactional
    public Person save(Person person) {
        return entityManager.merge(person);
    }

    public int countAll() {
        return ((Integer) entityManager.createQuery("SELECT count(o) from Person o").getSingleResult()).intValue();
    }

    @RolesAllowed(value = {"ROLE_USER"})
    public List<Person> findAll(int firstResult, int maxResult) {
        return entityManager.createNamedQuery("Person.findAll").setFirstResult(firstResult).setMaxResults(maxResult).getResultList();
    }

    @RolesAllowed(value = {"ROLE_USER"})
    public Person findById(Long id) {
        return entityManager.find(Person.class, id);
    }
    
}
