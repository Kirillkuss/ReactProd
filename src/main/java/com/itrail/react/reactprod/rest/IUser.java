package com.itrail.react.reactprod.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.itrail.react.reactprod.entity.User;
import com.itrail.react.reactprod.responses.BaseResponseError;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Mono;

@RequestMapping(value = "users")
@Tag( name = "4. USERS", description = "CRUD for USER")
@SecurityRequirement(name = "Bearer Authentication")
public interface IUser {

    
    @PostMapping("")
    @Operation( description = "Добавление USER", summary = "Добавление USER")
    @ApiResponses(value = {
        @ApiResponse( responseCode = "200", description = "Update USER",        content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = User.class ))) }),
        @ApiResponse( responseCode = "400", description = "Bad request",       content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponseError.class ))) }),
        @ApiResponse( responseCode = "500", description = "System malfunction",content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponseError.class ))) })
    })
    public Mono<Object> createUser( User user ) throws Exception;

    
}
