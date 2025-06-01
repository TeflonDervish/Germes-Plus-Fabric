package ru.semenov.germesplusfabric.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.semenov.germesplusfabric.model.persons.PointManager;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PointOfSale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;
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
    @JoinColumn(name = "point_manager_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private PointManager pointManager;


}
