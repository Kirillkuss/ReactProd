package com.itrail.react.reactprod.controller;

import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import com.itrail.react.reactprod.entity.User;
import com.itrail.react.reactprod.responses.BaseResponseError;
import com.itrail.react.reactprod.rest.IUser;
import com.itrail.react.reactprod.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController implements IUser {

    @ExceptionHandler(Throwable.class)
    public Mono<ResponseEntity<BaseResponseError>> errBaseResponse( Throwable ex ){
        log.error( ex.getMessage());
        return Mono.just( ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR).body( new BaseResponseError( 503, ex.getMessage())));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public Mono<ResponseEntity<BaseResponseError>> errBaseResponse( NoSuchElementException ex ){
        log.error( ex.getMessage());
        return Mono.just( ResponseEntity.status( HttpStatus.BAD_REQUEST).body( new BaseResponseError( 400, ex.getMessage())));
    } 

    @ExceptionHandler(IllegalArgumentException.class)
    public Mono<ResponseEntity<BaseResponseError>> errBaseResponse( IllegalArgumentException ex ){
        log.error( ex.getMessage());
        return Mono.just( ResponseEntity.status( HttpStatus.BAD_REQUEST).body( new BaseResponseError( 400, ex.getMessage())));
    } 

    private final UserService userService;
    @Override
    public Mono<Object> createUser(User user) throws Exception {
        return userService.addUser( user );
    }
    
}
