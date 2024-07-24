package com.itrail.react.reactprod.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "car")
@Setter
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Car {

    @Id
    @Column("id")
    @Schema( name        = "id",
             description = "Идентификатор автомобиля",
             example     = "4",
             required    = true )
    private Long          id;

    @Column("model")
    @Schema( name        = "model",
             description = "Модель",
             example     = "Audi",
             required    = true )
    private String        model;

    @Column("timebuy")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Schema( name        = "timebuy",
             required    = true )
    private LocalDateTime timebuy;

    @Column("coast")
    @Schema( name        = "coast",
             description = "Цена",
             example     = "57300",
             required    = true)
    private BigDecimal    coast;

    @Column("number")
    @Schema( name        = "number",
             description = "Номер",
             example     = "9922",
             required    = true)
    private Integer       number;
}
