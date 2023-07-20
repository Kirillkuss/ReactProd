package com.itrail.react.reactprod.controller;

import com.itrail.react.reactprod.requets.AuthRequest;
import com.itrail.react.reactprod.responses.AuthResponse;
import com.itrail.react.reactprod.rest.IAuthentication;
import com.itrail.react.reactprod.security.JWTUtil;
import com.itrail.react.reactprod.security.PBKDF2Encoder;
import com.itrail.react.reactprod.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
public class AuthenticationControlller implements IAuthentication {

    private JWTUtil jwtUtil;
    private PBKDF2Encoder passwordEncoder;
    private UserService userService;

    public Mono<ResponseEntity<AuthResponse>> login( AuthRequest authRequest ) {
        return userService.findByUsername( authRequest.getUsername() )
                          .filter( userDetails -> passwordEncoder.encode( authRequest.getPassword() ).equals(userDetails.getPassword() ))
                          .map( userDetails -> ResponseEntity.status( HttpStatus.OK )
                                                             .header( "X-AUTH-TOKEN", jwtUtil.generateToken( userDetails ))
                                                             .body( new AuthResponse( 200, jwtUtil.generateToken( userDetails ))))
                          .switchIfEmpty( Mono.just( ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                                                   .body( new AuthResponse( 401, "Invalid login or password" ))));
    }

}
