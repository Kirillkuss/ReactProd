package com.itrail.react.reactprod.service;

import com.itrail.react.reactprod.entity.Person;
import com.itrail.react.reactprod.exc.MyException;
import com.itrail.react.reactprod.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository repository;

    public Flux<Person> allPerson() throws Exception{
        return repository.findAll() ;
    }

    public Mono<Person> findByIdPerson( Long id ) throws Exception{
        return  repository.findById( id ).switchIfEmpty( Mono.error( new MyException( 400 , "Нет клинта с таким ИД")));
    }

    public Mono<Person> updatePerson( Person person ) throws Exception{
        repository.findById( person.getId() ).switchIfEmpty( Mono.error( new MyException( 400 , "Нет клинта с таким ИД")));
          return repository.save( person );
    }

    public Mono<Void>  deletePerson( Long id ) throws Exception{
        //repository.findById( id ).switchIfEmpty( Mono.error( new MyException( 400 , "Нет клинта с таким ИД")));
        return repository.deleteById( id );
    }

    public Mono<Person> addPerson( Person person ) throws Exception{
        return repository.createPerson(  person.getId(), person.getName(), person.getLogin(), person.getPhone(), person.getWallet());
    }

}
