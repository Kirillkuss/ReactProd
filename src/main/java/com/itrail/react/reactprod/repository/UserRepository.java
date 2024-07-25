package com.itrail.react.reactprod.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import com.itrail.react.reactprod.entity.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<User, Long> {

    @Query( "SELECT u.password_user from Users u")
    Flux<String> getPassword();

    @Query( "SELECT u.* from Users u WHERE u.username = :username")
    Mono<User> findByLogin( String username );

    @Query( "SELECT u.* from Users u WHERE u.email = :email")
    Mono<User> findByEmail( String email );
    
}
