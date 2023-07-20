package com.itrail.react.reactprod.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.itrail.react.reactprod.requets.AuthRequest;
import com.itrail.react.reactprod.responses.AuthResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Mono;

@Tag( name = "1. JWT", description = "GET AUTH JWT")
@ApiResponses(value = {
    @ApiResponse( responseCode = "200", description = "Found the list Person", content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = AuthResponse.class ))) }),
    @ApiResponse( responseCode = "401", description = "Unauthorized",          content = { @Content(mediaType = "application/json") })
    })
public interface IAuthentication {

    @PostMapping("/login")
    @Operation( description = "Получение JWT Token", summary = "Получение JWT Token")
    public Mono<ResponseEntity<AuthResponse>> login( @RequestBody AuthRequest authRequest );
    
}
