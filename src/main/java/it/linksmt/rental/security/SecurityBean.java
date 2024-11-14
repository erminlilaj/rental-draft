package it.linksmt.rental.security;

import it.linksmt.rental.enums.UserType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecurityBean {
    private Long id;
    private String username;
    private String name;
    private String surname;
    private String email;
    private String password;
    private int age;
    private UserType userType;

}
