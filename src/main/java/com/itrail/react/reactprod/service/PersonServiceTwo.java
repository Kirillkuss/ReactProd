package com.itrail.react.reactprod.service;

import com.itrail.react.reactprod.entity.Person;
import com.itrail.react.reactprod.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonServiceTwo {

    @Autowired
    PersonRepository repository;

    public Flux<Person> allPerson() throws Exception{
        return repository.findAll() ;
    }

    public Mono<Person> findByIdPerson( Long id ) throws Exception{
        return  repository.findById( id );

    }

    public Mono<Person> updatePerson( Person person ) throws Exception{
          return repository.save( person );
    }

    public Mono<Void>  deletePerson( Long id ) throws Exception{
        return repository.deleteById( id );
    }

    public Mono<Person> addPerson( Person person ) throws Exception{
        return repository.createPerson(  person.getId(), person.getName(), person.getLogin(), person.getPhone(), person.getWallet());
    }

}
