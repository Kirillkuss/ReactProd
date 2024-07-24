package com.itrail.react.reactprod.controller;

import com.itrail.react.reactprod.entity.Car;
import java.util.NoSuchElementException;
import com.itrail.react.reactprod.responses.BaseResponseError;
import com.itrail.react.reactprod.rest.ICar;
import com.itrail.react.reactprod.service.CarService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CarController implements ICar {

    @ExceptionHandler(Throwable.class)
    public Mono<ResponseEntity<BaseResponseError>> errBaseResponse( Throwable ex ){
        log.error( ex.getMessage());
        return Mono.just( ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR).body( new BaseResponseError( 500, ex.getMessage())));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public Mono<ResponseEntity<BaseResponseError>> errBaseResponse( NoSuchElementException ex ){
        log.error( ex.getMessage());
        return Mono.just( ResponseEntity.status( HttpStatus.BAD_REQUEST).body( new BaseResponseError( 400, ex.getMessage())));
    } 
    
    private final CarService service;

    public Flux<Car> allCars() throws Exception{
        return service.getAllCar();
    }

    public Mono<Car> findCarById( Long id ) throws Exception{
        return service.findCarById( id );
    }

    public Mono<Car> updateCar( Car car ) throws Exception{
        return service.updateCar( car );
    }

    public Mono<Car> createCar( Car car ) throws Exception{
        return  service.createCar( car );
    }

    public Mono deleteCar( Long id ) throws Exception{
        return service.deleteCar( id );
    }
 }
