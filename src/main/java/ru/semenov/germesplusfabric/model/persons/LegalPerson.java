package ru.semenov.germesplusfabric.model.persons;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LegalPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;

    @Column(length = 20)
    private String phoneNumber;
    @Column(length = 100)
    private String email;
    private String password;
}
