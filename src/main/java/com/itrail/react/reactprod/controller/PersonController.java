package com.itrail.react.reactprod.controller;

import com.itrail.react.reactprod.entity.Person;
import com.itrail.react.reactprod.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("person")
@Tag( name = "PERSONS", description = "CRUD for PERSON")
public class PersonController {

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

    @GetMapping("/allPerson")
    @Operation( description = "Получение списка Person", summary = "Получение списка Person")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200" , description = "Found the  list Person", content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = Person.class ))) }),
            @ApiResponse( responseCode = "400", description = "Bad request",content = { @Content(mediaType = "application/json") }),
            @ApiResponse( responseCode = "500", description = "System malfunction",content = { @Content(mediaType = "application/json") })
    })
    public Flux<Person> getAllPerson() throws Exception{
        sendMessage( "ReactProd -- getAllPerson");
        return service.allPerson();
    }

    @GetMapping("/findByIdPerson/{id}")
    @Operation( description = "Получение Person по ID", summary = "Получение Person по ID")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200" , description = "Found the Person by ID", content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = Person.class ))) }),
            @ApiResponse( responseCode = "400", description = "Bad request",content = { @Content(mediaType = "application/json") }),
            @ApiResponse( responseCode = "500", description = "System malfunction",content = { @Content(mediaType = "application/json") })
    })
    public Mono<Person> findByIdPerson(Long id ) throws Exception{
        sendMessage( "ReactProd -- findByIdPerson" + service.findByIdPerson( id).toString() );
        return service.findByIdPerson( id );
    }

    @PostMapping( "/updatePerson")
    @Operation( description = "Обновление Person", summary = "Обновление Person")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200" , description = "Update Person ", content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = Person.class ))) }),
            @ApiResponse( responseCode = "400", description = "Bad request",content = { @Content(mediaType = "application/json") }),
            @ApiResponse( responseCode = "500", description = "System malfunction",content = { @Content(mediaType = "application/json") })
    })
    public Mono<Person>  updatePerson( Person person ) throws Exception{
        sendMessage( "ReactProd -- updatePerson");
        return service.updatePerson( person );
    }
    @Operation( description = "Удаление Person", summary = "Удаление Person")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200" , description = "Delete Person ", content = { @Content(mediaType = "application/json") }),
            @ApiResponse( responseCode = "400", description = "Bad request",content = { @Content(mediaType = "application/json") }),
            @ApiResponse( responseCode = "500", description = "System malfunction",content = { @Content(mediaType = "application/json") })
    })
    @DeleteMapping( "deletePerson/{id}")
    public Mono<Void>  deletePerson( Long id ) throws Exception{
        return service.deletePerson( id );
    }

    @PutMapping("createPreson")
    @Operation( description = "Добавление Person", summary = "Добавление Person")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200" , description = "Create Person ", content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = Person.class ))) }),
            @ApiResponse( responseCode = "400", description = "Bad request",content = { @Content(mediaType = "application/json") }),
            @ApiResponse( responseCode = "500", description = "System malfunction",content = { @Content(mediaType = "application/json") })
    })
    public Mono<Person> addPerson( Person person ) throws Exception{
        sendMessage( "ReactProd -- addPerson");
        return service.addPerson(person);
    }
}
