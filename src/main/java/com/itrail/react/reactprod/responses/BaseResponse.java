package com.itrail.react.reactprod.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class BaseResponse<T> {

    private int code;
    private String message = "System mallfunction";
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;


    public BaseResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
