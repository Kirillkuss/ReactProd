package com.itrail.react.reactprod.controller;

import com.itrail.react.reactprod.entity.Animal;
import com.itrail.react.reactprod.entity.Person;
import com.itrail.react.reactprod.responses.BaseResponseError;
import com.itrail.react.reactprod.rest.IPerson;
import com.itrail.react.reactprod.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PersonController implements IPerson {


    @ExceptionHandler(Throwable.class)
    public Mono<ResponseEntity<BaseResponseError>> errBaseResponse( Throwable ex ){
        log.error( ex.getMessage());
        return Mono.just( ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR).body( new BaseResponseError( 500, ex.getMessage())));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public Mono<ResponseEntity<BaseResponseError>> errBaseResponse( NoSuchElementException ex ){
        log.error( ex.getMessage());
        return  Mono.just( ResponseEntity.status( HttpStatus.BAD_REQUEST).body( new BaseResponseError( 400, ex.getMessage())));
    }  

    private final PersonService service;
    private Person send = new Person();

    private final KafkaTemplate<String, Person> kafkaTemplate;

    public void sendMessage(Person person) {
        kafkaTemplate.send("TopicOne", person);
    }

    @KafkaListener( topics = "TopicTwo", groupId = "MyGroupTopics")
    public void getMessageTwo( Animal animal ){
       log.info( "Message from SpringPro >> " + animal.toString() );
    }

    public Flux<Person> getAllPerson() throws Exception{
        Flux<Person> response = service.allPerson();
        response.subscribe( s -> { 
            send = s;
            sendMessage( send );
        });
        return response;
    }
    

    public Mono<Person> findByIdPerson(Long id ) throws Exception{
        Mono<Person> response = service.findByIdPerson( id );
        response.subscribe( s -> {
            send = s;
        });
        sendMessage( send );
        return response;
    }

    public Mono<Person>  updatePerson( Person person ) throws Exception{
        Mono<Person> response = service.updatePerson( person );
        response.subscribe( s -> {
            send = s;
        });
        sendMessage( send );
        return response;
    }

    public Mono<Void> deletePerson( Long id ) throws Exception{
        return service.deletePerson( id );
    }

    public Mono<Person> addPerson( Person person ) throws Exception{
        Mono<Person> response = service.addPerson(person);
        response.subscribe( s -> {
            send = s;
        });
        sendMessage( send );
        return response;
    }

}
