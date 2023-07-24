package com.itrail.react.reactprod.rest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.itrail.react.reactprod.entity.Car;
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

@RequestMapping(value = "cars")
@Tag( name = "2. CARS", description = "CRUD for CAR")
@SecurityRequirement(name = "Bearer Authentication")
public interface ICar {

    @GetMapping("/all-car")
    @Operation( description = "Полуение списка Car", summary = "Полуение списка Car")
    @ApiResponses(value = {
        @ApiResponse( responseCode = "200", description = "Found List Car ",   content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = Car.class ))) }),
        @ApiResponse( responseCode = "400", description = "Bad request",       content = { @Content(mediaType = "application/json") }),
        @ApiResponse( responseCode = "500", description = "System malfunction",content = { @Content(mediaType = "application/json") })
    })
    public Flux<Car> allCars() throws Exception;

    @GetMapping( "/find/{id}")
    @Operation( description = "Полуение Car по ID", summary = "Полуение Car по ID")
    @ApiResponses(value = {
        @ApiResponse( responseCode = "200", description = "Found Car by ID",   content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = Car.class ))) }),
        @ApiResponse( responseCode = "400", description = "Bad request",       content = { @Content(mediaType = "application/json") }),
        @ApiResponse( responseCode = "500", description = "System malfunction",content = { @Content(mediaType = "application/json") })
    })
    public Mono<Car> findCarById( Long id ) throws Exception;

    @PostMapping("/update")
    @Operation( description = "Обновление Car", summary = "Обновление Car")
    @ApiResponses(value = {
        @ApiResponse( responseCode = "200", description = "Update Car",        content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = Car.class ))) }),
        @ApiResponse( responseCode = "400", description = "Bad request",       content = { @Content(mediaType = "application/json") }),
        @ApiResponse( responseCode = "500", description = "System malfunction",content = { @Content(mediaType = "application/json") })
    })
    public Mono<Car> updateCar( Car car ) throws Exception;

    @PutMapping( "/create")
    @Operation( description = "Добавление Car", summary = "Добавление Car")
    @ApiResponses(value = {
        @ApiResponse( responseCode = "200", description = "Create Car ",       content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = Car.class ))) }),
        @ApiResponse( responseCode = "400", description = "Bad request",       content = { @Content(mediaType = "application/json") }),
        @ApiResponse( responseCode = "500", description = "System malfunction",content = { @Content(mediaType = "application/json") })
    })
    public Mono<Car> createCar( Car car ) throws Exception;

    @DeleteMapping( "/delete/{id}")
    @Operation( description = "Удаление Car", summary = "Удаление Car")
    @ApiResponses(value = {
        @ApiResponse( responseCode = "200", description = "Delete Car ",       content = { @Content(mediaType = "application/json") }),
        @ApiResponse( responseCode = "400", description = "Bad request",       content = { @Content(mediaType = "application/json") }),
        @ApiResponse( responseCode = "500", description = "System malfunction",content = { @Content(mediaType = "application/json") })
    })
    public Mono deleteCar( Long id ) throws Exception;
    
}
