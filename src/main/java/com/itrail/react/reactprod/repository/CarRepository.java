package com.itrail.react.reactprod.repository;

import com.itrail.react.reactprod.entity.Car;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface CarRepository extends ReactiveCrudRepository<Car, Long> {

    @Modifying
    @Query("INSERT INTO car ( id, model, timebuy, coast, number ) VALUES ( :id, :model, :timebuy, :coast, :number)")
    Mono<Car> createCar( @Param("id") Long id,
                         @Param("model") String model,
                         @Param("timebuy") LocalDateTime timeBuy,
                         @Param("coast") BigDecimal coast,
                         @Param("number") Integer number );
}
