package ru.semenov.germesplusfabric.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.semenov.germesplusfabric.model.persons.FabricManager;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Fabric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;
    @Column(length = 20)
    private String city;
    @Column(length = 100)
    private String address;

    // ссылка на точку на Яндекс картах
    @Column(nullable = false)
    private String pointOnTheMap;

    @Column(length = 20)
    private String phoneNumber;
    @Column(length = 100)
    private String email;

    @Column(length = 100)
    private String openingHours;

    private String description;

    @ManyToOne
    @JoinColumn(name = "fabric_manager_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private FabricManager fabricManager;


    private String url;

}
