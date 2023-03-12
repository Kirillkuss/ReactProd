package com.itrail.react.reactprod.controller;

import com.itrail.react.reactprod.entity.Person;
import com.itrail.react.reactprod.service.PersonServiceTwo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("person")
@Tag( name = "PERSONS", description = "CRUD for PERSON")
public class PersonControllerTwo {

    @Autowired
    PersonServiceTwo service;

    @GetMapping("/allPerson")
    @Operation( description = "Получение списка Person", summary = "Получение списка Person")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200" , description = "Found the  list Person", content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = Person.class ))) }),
            @ApiResponse( responseCode = "400", description = "Bad request",content = { @Content(mediaType = "application/json") }),
            @ApiResponse( responseCode = "500", description = "System malfunction",content = { @Content(mediaType = "application/json") })
    })
    public Flux<Person> getAllPerson() throws Exception{
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
        return service.addPerson(person);
    }
}
