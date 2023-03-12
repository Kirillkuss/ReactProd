package com.itrail.react.reactprod.service;

import com.itrail.react.reactprod.entity.Person;
import com.itrail.react.reactprod.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.math.BigDecimal;
import java.util.*;

@Service
public class PersonService {

    @Autowired
    PersonRepository repository;


    List<Person> list = new ArrayList<>(Arrays.asList( new Person( 1L, "Test", BigDecimal.ONE ),
                                                       new Person( 2L, "Pockemon", BigDecimal.TEN ),
                                                       new Person( 4L, "Person", BigDecimal.valueOf( 134.45 ))));
    /**
     * Поиск клината по ИД
     * @param id - ИД клиента
     * @return Mono<Person>
     */
    public Mono<Person> findId( Long id ){
        Person response = list.stream()
                              .filter(f ->Objects.equals( f.getId(), id ))
                              .findFirst()
                              .orElse( null );
        if (  response != null ){
            return Mono.just( response );
        }else{
            return Mono.empty();
        }
    }
    /**
     * Поиск всех клиентов
     * @return Flux<Person>
     */
    public Flux<Person> findAllPerson(){
        return Flux.fromIterable( list );
    }


    public Mono<Person> addPerson( Person person ){
        list.add( person );
        Person response = list.stream().filter( f-> Objects.equals( f.getId(), person.getId() )).findFirst().orElse( null );
        if (  response != null ){
            return Mono.just( response );
        } else {
            return Mono.empty();
        }
    }

    public Flux<Person> getAllRepository() throws Exception{
        return repository.findAll();
    }
}
