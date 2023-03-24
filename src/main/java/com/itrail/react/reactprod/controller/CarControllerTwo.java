package com.itrail.react.reactprod.controller;

import com.itrail.react.reactprod.entity.Car;
import com.itrail.react.reactprod.service.CarServiceTwo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping(value = "cars")
@Tag( name = "CARS", description = "CRUD for CAR")
public class CarControllerTwo {

    @Autowired
    CarServiceTwo service;

    @GetMapping("/allCar")
    @Operation( description = "Полуение списка Car", summary = "Полуение списка Car")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200" , description = "Found List Car ", content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = Car.class ))) }),
            @ApiResponse( responseCode = "400", description = "Bad request",content = { @Content(mediaType = "application/json") }),
            @ApiResponse( responseCode = "500", description = "System malfunction",content = { @Content(mediaType = "application/json") })
    })
    public Flux<Car> allCars() throws Exception{
        return service.getAllCar();
    }

    @GetMapping( "/findCarById/{id}")
    @Operation( description = "Полуение Car по ID", summary = "Полуение Car по ID")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200" , description = "Found Car by ID", content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = Car.class ))) }),
            @ApiResponse( responseCode = "400", description = "Bad request",content = { @Content(mediaType = "application/json") }),
            @ApiResponse( responseCode = "500", description = "System malfunction",content = { @Content(mediaType = "application/json") })
    })
    public Mono<Car> findCarById( Long id ) throws Exception{
        return service.findCarById( id );
    }

    @PostMapping("/updateCar")
    @Operation( description = "Обновление Car", summary = "Обновление Car")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200" , description = "Update Car", content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = Car.class ))) }),
            @ApiResponse( responseCode = "400", description = "Bad request",content = { @Content(mediaType = "application/json") }),
            @ApiResponse( responseCode = "500", description = "System malfunction",content = { @Content(mediaType = "application/json") })
    })
    public Mono<Car> updateCar( Car car ) throws Exception{
        return service.updateCar( car );
    }

    @PutMapping( "/createCar")
    @Operation( description = "Добавление Car", summary = "Добавление Car")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200" , description = "Create Car ", content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = Car.class ))) }),
            @ApiResponse( responseCode = "400", description = "Bad request",content = { @Content(mediaType = "application/json") }),
            @ApiResponse( responseCode = "500", description = "System malfunction",content = { @Content(mediaType = "application/json") })
    })
    public Mono<Car> createCar( Car car ) throws Exception{
        return service.createCar( car );
    }

    @DeleteMapping( "/deleteById/{id}")
    @Operation( description = "Удаление Car", summary = "Удаление Car")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200" , description = "Delete Car ", content = { @Content(mediaType = "application/json") }),
            @ApiResponse( responseCode = "400", description = "Bad request",content = { @Content(mediaType = "application/json") }),
            @ApiResponse( responseCode = "500", description = "System malfunction",content = { @Content(mediaType = "application/json") })
    })
    public Mono<Void> deleteCar( Long id ) throws Exception{
        return service.deleteCar( id );
    }
 }
