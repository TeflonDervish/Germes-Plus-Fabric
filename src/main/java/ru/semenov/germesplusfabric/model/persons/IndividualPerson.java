package ru.semenov.germesplusfabric.model.persons;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class IndividualPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String surname;
    @Column(length = 100)
    private String name;
    @Column(length = 100)
    private String middleName;

    @Column(length = 20)
    private String phone;
    @Column(length = 100)
    private String email;
    private String password;

    @Column(length = 20)
    private String city;
}
