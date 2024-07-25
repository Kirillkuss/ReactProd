package com.itrail.react.reactprod.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponseError {
    
    @Schema( name = "code",
             description = "code",
             example = "400")
    private int code;

    @Schema( name = "message",
             description = "message",
             example = "Плохой запрос")
    private String message;
}
