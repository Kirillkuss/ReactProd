package com.itrail.react.reactprod.controller;

import com.itrail.react.reactprod.entity.Car;
import com.itrail.react.reactprod.exc.MyException;
import com.itrail.react.reactprod.responses.BaseResponse;
import com.itrail.react.reactprod.rest.ICar;
import com.itrail.react.reactprod.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CarController implements ICar {

    @ExceptionHandler(Throwable.class)
    public Flux<BaseResponse> errBaseResponse( Throwable ex ){
        ex.printStackTrace(System.err );
        log.error( ex.getMessage());
        return Flux.just(  new BaseResponse<>( 500, ex.getMessage() ));
    }

    @ExceptionHandler(MyException.class)
    public Flux<BaseResponse> errBaseResponse( MyException ex ){
        log.error( ex.getMessage());
        return Flux.just( new BaseResponse<>( ex.getCode(), ex.getMessage() ));
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
