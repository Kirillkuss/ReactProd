package com.itrail.react.reactprod.controller;

import com.itrail.react.reactprod.entity.Person;
import com.itrail.react.reactprod.exc.MyException;
import com.itrail.react.reactprod.responses.BaseResponse;
import com.itrail.react.reactprod.rest.IPerson;
import com.itrail.react.reactprod.service.PersonService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class PersonController implements IPerson {

    @ExceptionHandler(Throwable.class)
    public Flux<BaseResponse> errBaseResponse( Throwable ex ){
        log.error( ex.getMessage());
        return Flux.just(  new BaseResponse<>( 500, ex.getMessage() ));
    }

    @ExceptionHandler(MyException.class)
    public Flux<BaseResponse> errBaseResponse( MyException ex ){
        log.error( ex.getMessage());
        return Flux.just( new BaseResponse<>( ex.getCode(), ex.getMessage() ));
    }  

    @Autowired
    PersonService service;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String msg) {
        kafkaTemplate.send("ReactTopic-1", msg);
    }

    @KafkaListener( topics = "ReactTopic-2", groupId = "test_topics")
    public void getMessageTwo(String msg){
        System.out.println( msg );
    }

    public Flux<Person> getAllPerson() throws Exception{
        sendMessage( "ReactProd -- getAllPerson");
        return service.allPerson();
    }

    public Mono<Person> findByIdPerson(Long id ) throws Exception{
        sendMessage( "ReactProd -- findByIdPerson" + service.findByIdPerson( id).toString() );
        return service.findByIdPerson( id );
    }

    public Mono<Person>  updatePerson( Person person ) throws Exception{
        sendMessage( "ReactProd -- updatePerson");
        log.info( "<<UPDATE PERSON>>");
        return service.updatePerson( person );
    }

    public Mono<Void>  deletePerson( Long id ) throws Exception{
        return service.deletePerson( id );
    }

    public Mono<Person> addPerson( Person person ) throws Exception{
        sendMessage( "ReactProd -- addPerson");
        return service.addPerson(person);
    }

}
