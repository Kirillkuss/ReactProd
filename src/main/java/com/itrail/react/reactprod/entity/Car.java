package com.itrail.react.reactprod.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class Car {
    @Schema( name        = "id",
            description = "Идентификатор автомобиля",
            example     = "4")
    @Id
    private Long          id;
    @Schema( name        = "model",
            description = "Модель",
            example     = "Audi")
    private String        model;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Schema( name        = "timebuy")
    private LocalDateTime timebuy;
    @Schema( name        = "coast",
            description = "Цена",
            example     = "57300")
    private BigDecimal    coast;
    @Schema( name        = "number",
            description = "Номер",
            example     = "9922")
    private Integer       number;

    public Car(Long id, String model, LocalDateTime timebuy, BigDecimal coast, Integer number) {
        this.id = id;
        this.model = model;
        this.timebuy = timebuy;
        this.coast = coast;
        this.number = number;
    }

    public Car() {
    }
}
