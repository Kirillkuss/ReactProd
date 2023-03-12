package com.itrail.react.reactprod.controller;

import com.itrail.react.reactprod.entity.Car;
import com.itrail.react.reactprod.responses.BaseResponse;
import com.itrail.react.reactprod.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@RestController
public class CarController {

    @Autowired
    CarService service;

    @RequestMapping( method = RequestMethod.GET, value = "/findById/{id}")
    @Operation( description = "Получение авто по ИД", summary = "Получение авто по ИД")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200" , description = "Found the car by ID", content = { @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = BaseResponse.class))) }),
            @ApiResponse( responseCode = "400", description = "Bad request",content = { @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = BaseResponse.class))) }),
            @ApiResponse( responseCode = "500", description = "System malfunction",content = { @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = BaseResponse.class))) })
    })
    public Mono<BaseResponse> findById(Long id) throws Exception{
        return service.findById( id );
    }

    @RequestMapping( method = RequestMethod.GET, value = "/findAll")
    @Operation( description = "Получение всех автомобилей", summary = "Получение всех автомобилей")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200" , description = "Find cars", content = { @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = BaseResponse.class))) }),
            @ApiResponse( responseCode = "400", description = "Bad request",content = { @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = BaseResponse.class))) }),
            @ApiResponse( responseCode = "500", description = "System malfunction",content = { @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = BaseResponse.class))) })
    })
    public Flux<BaseResponse> findAllCars() throws Exception{
        return service.findAllCars();
    }

    @RequestMapping( method = RequestMethod.POST, value = "/addCar")
    @Operation( description = "Добавление автомобиля", summary = "Добавление автомобиля")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200" , description = "Add the car", content = { @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = BaseResponse.class))) }),
            @ApiResponse( responseCode = "400", description = "Bad request",content = { @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = BaseResponse.class))) }),
            @ApiResponse( responseCode = "500", description = "System malfunction",content = { @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = BaseResponse.class))) })
    })
    public Mono<BaseResponse> addCar( Car car ) throws Exception{
        return service.addCars( car );
    }

    @RequestMapping( method =  RequestMethod.PUT, value = "/update")
    @Operation( description = "Обновление автомобиля", summary = "Обновление автомобиля")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200" , description = "Update the car", content = { @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = BaseResponse.class))) }),
            @ApiResponse( responseCode = "400", description = "Bad request",content = { @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = BaseResponse.class))) }),
            @ApiResponse( responseCode = "500", description = "System malfunction",content = { @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = BaseResponse.class))) })
    })
    public Mono<BaseResponse> update( Car car ) throws Exception{
        return service.updateCar( car );
    }

    @RequestMapping( method = RequestMethod.DELETE, value = "/delete/{id}")
    @Operation( description = "Удаление автомобиля", summary = "Удаление автомобиля")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200" , description = "Delete the car", content = { @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = BaseResponse.class)))}),
            @ApiResponse( responseCode = "400", description = "Bad request",content = { @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = BaseResponse.class))) }),
            @ApiResponse( responseCode = "500", description = "System malfunction",content = { @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = BaseResponse.class))) })
    })
    public Mono<BaseResponse> deleteCar( Long id ) throws Exception{
        return service.deleteCar( id );
    }
}
