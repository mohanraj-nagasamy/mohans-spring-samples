package com.jsf2.test.app.service;

import com.jsf2.test.app.domain.Person;
import com.jsf2.test.app.repository.PersonRepository;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dimitar Makariev
 */
@Service
class PersonBatchCreateServiceImpl implements PersonBatchCreateService{

    private final PersonRepository personRepository;

    @Inject
    PersonBatchCreateServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void batchCreateRecords(int numberOfRecords) {
        for (int i = 0; i < numberOfRecords; i++) {
             final Person person = new Person();
            person.setFirstName("record " + i);
            person.setFamilyName("aaa");
            personRepository.save(person);
        }
    }

    public int deleteAll() {
       int count = personRepository.countAll();
       int previousCount=count;
       int countDeleted=0;
       long beginTime = System.currentTimeMillis();
       while(count>0){
           final Person person = personRepository.findAll(0, 1).get(0);
           personRepository.delete(person.getId());
           
           previousCount = count;
           count = personRepository.countAll();
           if(count>=previousCount){
               throw new RuntimeException("Unsuccessfull deleteAll, or concurent writinng of Person. Operation is stopped");
           }
           countDeleted++;           
       }
       long deletingTime = System.currentTimeMillis()-beginTime;
       Logger log = Logger.getLogger("com.jsf2.test.app.service.PersonBatchCreateServiceImpl");
       log.warning("used deleteAll method. deleted "+countDeleted+" items for "+deletingTime+"ms");
       return countDeleted;
    }
   
}
