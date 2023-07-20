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
import java.math.BigDecimal;

@Table
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Person {

    @Id
    @Schema( name        = "id",
             description = "ИД",
             example     = "5")
    private Long id;

    @Schema( name        = "name",
             description = "Имя",
             example     = "REACT" )
    private String name;

    @Schema( name        = "login",
             description = "Логин",
             example     = "Potr@mail.com" )
    private String login;

    @Schema( name        = "phone",
             description = "Номер телефона",
             example     = "+375252344657" )
    private String phone;

    @Schema( name        = "wallet",
             description = "Кошелек",
             example     = "5000" )
    private BigDecimal wallet;
}
