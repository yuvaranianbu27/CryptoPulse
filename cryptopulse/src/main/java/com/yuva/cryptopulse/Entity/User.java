package com.yuva.cryptopulse.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yuva.cryptopulse.domain.USER_ROLE;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@Data
@Builder
public class User {

    @Id
    @GeneratedValue(Strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private  String fullName;

    @Column
    private String email;

    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Embedded
    private TwoFactorAuth twoFactorAuth =new TwoFactorAuth();

    @Column
    private USER_ROLE role = USER_ROLE.ROLE_CUSTOMER;


}
