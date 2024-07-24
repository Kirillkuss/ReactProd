package com.itrail.react.reactprod.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Table(name = "animal")
@Setter
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Animal implements Serializable {

    @Id
    @Schema( name        = "id",
             description = "ИД питомца",
             example     = "1",
             required    = true )
    private Long id;

    @Schema( name        = "name",
             description = "Название питомца",
             example     = "Cat",
             required    = true )
    private String name;

    @Schema( name        = "amount",
             description = "Стоимость",
             example     = "400",
             required    = true )
    private BigDecimal amount;

    @Schema( name        = "count",
             description = "Количество",
             example     = "30",
             required    = true )
    private Integer count;

}
