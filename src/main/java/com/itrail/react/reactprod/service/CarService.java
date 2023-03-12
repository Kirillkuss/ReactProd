package com.itrail.react.reactprod.service;

import com.itrail.react.reactprod.entity.Car;
import com.itrail.react.reactprod.responses.BaseResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
/**
 * Этот сервис предназначен для работы с автомобилями
 * @author barysevich_k
 */
@Service
public class CarService {

    List<Car> carsList = new ArrayList<>( Arrays.asList( new Car( 1L, "Tesla", LocalDateTime.now(), BigDecimal.valueOf( 3200L ), 6745),
                                                         new Car( 2L, "Kia", LocalDateTime.now(), BigDecimal.valueOf( 400L ), 6741))) ;


    /**
     * Поиск автомобиля по ИД
     * @param id - ИД автомобиля
     * @return Mono<BaseResponse>
     * @throws Exception
     */
    public Mono<BaseResponse> findById(Long id ) throws Exception{
        BaseResponse baseResponse = new BaseResponse( 200, "success");
        try {
            baseResponse.setData(carsList.stream()
                    .filter( s-> Objects.equals( s.getId(), id ))
                    .findFirst()
                    .orElse( null ));
            return Mono.just( baseResponse );
        }catch ( Exception ex ){
            return Mono.just( BaseResponse.error( 999, ex ));
        }
    }

    /**
     * Поиск всех автомобилей
     * @return Flux<BaseResponse>
     * @throws Exception
     */
    public Flux<BaseResponse> findAllCars() throws Exception{
        BaseResponse response = new BaseResponse( 200, "success" );
        try{
            response.setData( carsList );
            return Flux.just( response );
        }catch ( Exception ex ){
            return Flux.just(BaseResponse.error( 999, ex ));
        }
    }

    /**
     * Добавление автомобиля
     * @param car - автомобиль
     * @return Mono<BaseResponse>
     */
    public Mono<BaseResponse> addCars( Car car ) throws Exception{
        BaseResponse response = new BaseResponse( 200, "success");
        try {
            carsList.add( car );
            response.setData( carsList.stream()
                                      .filter( s -> Objects.equals( s.getId(), car.getId() ))
                                      .findFirst()
                                      .orElse( null ));
            return Mono.just( response );
        }catch ( Exception ex ){
            return Mono.just( BaseResponse.error( 999, ex ));
        }
    }

    /**
     * Обновить автомобиль или добавить
     * @param car - обновление автомобиля
     * @return Mono<BaseResponse>
     */
    public Mono<BaseResponse> updateCar( Car car ) throws Exception{
        BaseResponse response = new BaseResponse( 200, "success");
        try{
        Car request = carsList.stream().filter( s-> Objects.equals( s.getId(), car.getId() )).findFirst().orElse( null );
        if ( request != null ){
            request.setModel( car.getModel() );
            request.setNumber( car.getNumber() );
            request.setCoast( car.getCoast() );
            response.setData( request );
            return Mono.just( response );
        }else {
            carsList.add( car );
            response.setData( car );
            return Mono.just( response );
        }
        }catch( Exception ex ) {
            return Mono.just(BaseResponse.error(999, ex));
        }
    }

    public Mono<BaseResponse> deleteCar( Long id ) throws Exception{
        BaseResponse response = new BaseResponse( 200, "success");
        try{
            if ( carsList.removeIf( s -> Objects.equals(s.getId(), id)) == true ){
                response.setData( true );
                return Mono.just( response );
            }else{
                response.setData( "Нет автомобиля с таким ИД" );
                return Mono.just( response );
            }
        }catch ( Exception ex ){
            return Mono.just( BaseResponse.error( 999, ex ));
        }
    }

}
