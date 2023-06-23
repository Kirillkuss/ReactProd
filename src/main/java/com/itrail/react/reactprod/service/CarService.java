package com.itrail.react.reactprod.service;

import com.itrail.react.reactprod.entity.Car;
import com.itrail.react.reactprod.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CarService {

    @Autowired
    CarRepository repository;

    public Flux<Car> getAllCar() throws Exception{
        return repository.findAll();
    }

    public Mono<Car> findCarById(Long id ) throws Exception{
        return repository.findById( id );
    }

    public Mono<Car> updateCar( Car car ) throws Exception{
        return repository.save( car );
    }

    public Mono<Void> deleteCar( Long id ) throws Exception{
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
