package com.itrail.react.reactprod.controller;

import com.itrail.react.reactprod.requets.AuthRequest;
import com.itrail.react.reactprod.responses.AuthResponse;
import com.itrail.react.reactprod.rest.IAuthentication;
import com.itrail.react.reactprod.security.JWTUtil;
import com.itrail.react.reactprod.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class AuthenticationControlller implements IAuthentication {

    private final JWTUtil jwtUtil;
    private final UserService usersService;  

    public Mono<ResponseEntity<AuthResponse>> login( AuthRequest authRequest ) {
        return usersService.findByLogin(authRequest.getUsername())
                           .filter( filter -> usersService.checkUserPassword( authRequest.getPassword(), filter.getPassword() ))
                                .map( users -> ResponseEntity.status( HttpStatus.OK )
                                   // .header( "X-AUTH-TOKEN", jwtUtil.generateToken( users ))
                                    .body( new AuthResponse( 200, jwtUtil.generateToken( users ))))
                                    .switchIfEmpty( Mono.just( ResponseEntity.status( HttpStatus.UNAUTHORIZED )
                                    .body( new AuthResponse( 401, "Invalid login or password" ))));
    }

}
