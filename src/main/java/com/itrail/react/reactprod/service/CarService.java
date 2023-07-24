package com.itrail.react.reactprod.service;

import com.itrail.react.reactprod.entity.Car;
import com.itrail.react.reactprod.exc.MyException;
import com.itrail.react.reactprod.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CarService {
    
    private final CarRepository repository;

    public Flux<Car> getAllCar() throws Exception{
        return repository.findAll();
    }

    public Mono<Car> findCarById(Long id ) throws Exception{
        return repository.findById( id ).switchIfEmpty( Mono.error( new MyException( 400 , "Нет авто с таким ИД")));
    }

    public Mono<Car> updateCar( Car car ) throws Exception{
        return repository.save( car );
    }

    public Mono deleteCar( Long id ) throws Exception{
        return repository.deleteById( id );
    }

    public Mono<Car> createCar( Car car ) throws Exception{
        return repository.createCar( car.getId(),
                                     car.getModel(),
                                     car.getTimebuy(),
                                     car.getCoast(),
                                     car.getNumber() );
    }

}
