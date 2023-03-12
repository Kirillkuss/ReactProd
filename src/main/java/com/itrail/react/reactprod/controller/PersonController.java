package com.itrail.react.reactprod.controller;

import com.itrail.react.reactprod.entity.Person;
import com.itrail.react.reactprod.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping("/{id}")
    @Operation( description = "Получение клинета по ид", summary = "Получение клинета по ид")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200" , description = "Found the person", content = { @Content(mediaType = "application/json") }),
            @ApiResponse( responseCode = "400", description = "Bad request",content = { @Content(mediaType = "application/json") }),
            @ApiResponse( responseCode = "500", description = "System malfunction",content = { @Content(mediaType = "application/json") })
    })
    public Mono<Person> findById(@PathVariable("id") Long id ) throws Exception{
        return  service.findId( id );
    }

    @GetMapping("/all")
    @Operation( description = "Получение списка всех клиентов", summary = "Получение списка всех клиентов")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200" , description = "Found the persons", content = { @Content(mediaType = "application/json") }),
            @ApiResponse( responseCode = "400", description = "Bad request",content = { @Content(mediaType = "application/json") }),
            @ApiResponse( responseCode = "500", description = "System malfunction",content = { @Content(mediaType = "application/json") })
    })
    public Flux<Person> findAllPerson() throws Exception{
        return service.findAllPerson();
    }

    @PostMapping("/add")
    @Operation( description = "Добавление клиента", summary = "Добавление клиента")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200" , description = "Add person", content = { @Content(mediaType = "application/json") }),
            @ApiResponse( responseCode = "400", description = "Bad request",content = { @Content(mediaType = "application/json") }),
            @ApiResponse( responseCode = "500", description = "System malfunction",content = { @Content(mediaType = "application/json") })
    })
    public Mono<Person> addPerson( Person person ){
        return  service.addPerson( person );
    }
}
