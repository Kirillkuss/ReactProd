package com.itrail.react.reactprod.rest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.itrail.react.reactprod.entity.Person;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping(" persons")
@Tag( name = "3. PERSONS", description = "CRUD for PERSON")
public interface IPerson {

    @GetMapping("/all-persons")
    @Operation( description = "Получение списка Person", summary = "Получение списка Person")
    @ApiResponses(value = {
        @ApiResponse( responseCode = "200", description = "Found the  list Person", content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = Person.class ))) }),
        @ApiResponse( responseCode = "400", description = "Bad request",            content = { @Content(mediaType = "application/json") }),
        @ApiResponse( responseCode = "500", description = "System malfunction",     content = { @Content(mediaType = "application/json") })
    })
    public Flux<Person> getAllPerson() throws Exception;

    @GetMapping("/find/{id}")
    @Operation( description = "Получение Person по ID", summary = "Получение Person по ID")
    @ApiResponses(value = {
        @ApiResponse( responseCode = "200", description = "Found the Person by ID", content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = Person.class ))) }),
        @ApiResponse( responseCode = "400", description = "Bad request",            content = { @Content(mediaType = "application/json") }),
        @ApiResponse( responseCode = "500", description = "System malfunction",     content = { @Content(mediaType = "application/json") })
    })
    public Mono<Person> findByIdPerson( Long id ) throws Exception;

    @PostMapping( "/update")
    @Operation( description = "Обновление Person", summary = "Обновление Person")
    @ApiResponses(value = {
        @ApiResponse( responseCode = "200", description = "Update Person ",     content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = Person.class ))) }),
        @ApiResponse( responseCode = "400", description = "Bad request",        content = { @Content(mediaType = "application/json") }),
        @ApiResponse( responseCode = "500", description = "System malfunction", content = { @Content(mediaType = "application/json") })
    })
    public Mono<Person>  updatePerson( @RequestBody Person person ) throws Exception;

    @Operation( description = "Удаление Person", summary = "Удаление Person")
    @ApiResponses(value = {
        @ApiResponse( responseCode = "200", description = "Delete Person ",     content = { @Content(mediaType = "application/json") }),
        @ApiResponse( responseCode = "400", description = "Bad request",        content = { @Content(mediaType = "application/json") }),
        @ApiResponse( responseCode = "500", description = "System malfunction", content = { @Content(mediaType = "application/json") })
    })
    @DeleteMapping( "delete/{id}")
    public Mono<Void> deletePerson( Long id ) throws Exception;

    @PutMapping("create")
    @Operation( description = "Добавление Person", summary = "Добавление Person")
    @ApiResponses(value = {
        @ApiResponse( responseCode = "200", description = "Create Person ",     content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = Person.class ))) }),
        @ApiResponse( responseCode = "400", description = "Bad request",        content = { @Content(mediaType = "application/json") }),
        @ApiResponse( responseCode = "500", description = "System malfunction", content = { @Content(mediaType = "application/json") })
    })
    public Mono<Person> addPerson( @RequestBody Person person ) throws Exception;
    
}
