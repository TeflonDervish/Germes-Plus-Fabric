package ru.semenov.germesplusfabric.model.products;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@Entity
@Getter
@Setter
public class ProductForIndividual {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;

    @Column(length = 100)
    private Integer price;

    @Column(length = 100)
    private String size;

    @Column(length = 100)
    private String article;

    @Column(length = 100) // Основа
    private String basis;

    @Column(length = 100) // Наполнение
    private String filling;

    @Column(length = 100) // Короб
    private String box;

    @Column(length = 100) // Механизм
    private String mechanism;

    @Column(length = 100) // Подлокотники
    private String armrests;

    @Column(length = 100) // Глубина сидушки
    private String seatDepth;

    @Column(length = 100) // Посадка
    private String planting;

    @Column(length = 100) // Спальное место
    private String sleepingSpace;

    @Column(length = 100) // Глубина
    private String depth;

    @Column(length = 100) // Конфигурация
    private String configuration;

    @Lob
    private String description;

    @ElementCollection
    @CollectionTable(name="urlsForProductForIndividuals", joinColumns = @JoinColumn(name="id"))
    @Column(name = "urls")
    private List<String> urls;

}
