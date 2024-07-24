package com.itrail.react.reactprod.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import javax.validation.constraints.NotNull;


@Table(name = "Users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class User implements Serializable  {

    @Id
    private Long id;
    @Column("username")
    @Schema( name        = "username",
             description = "admin",
             example     = "admin",
             required    = true )
    @NotNull         
    private String username;

    @Column("password_user")
    @Schema( name        = "password",
             description = "admin",
             example     = "admin",
             required    = true )
    @NotNull
    private String password;

    @Column("role_user")
    @Hidden
    private String role;

    @Column("email")
    @Hidden
    private String email;
    
}
