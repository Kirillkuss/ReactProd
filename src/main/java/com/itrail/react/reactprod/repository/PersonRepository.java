package com.itrail.react.reactprod.repository;

import com.itrail.react.reactprod.entity.Person;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import java.math.BigDecimal;

public interface PersonRepository extends ReactiveCrudRepository<Person, Long> {

    @Modifying
    @Query( "INSERT INTO person ( id, name, login, phone, wallet) VALUES ( :id, :name, :login, :phone, :wallet )" )
    Mono<Person> createPerson( @Param( "id" ) Long id,
                               @Param( "name" ) String name,
                               @Param( "login" ) String login,
                               @Param( "phone" ) String phone,
                               @Param( "wallet" ) BigDecimal wallet);
}
