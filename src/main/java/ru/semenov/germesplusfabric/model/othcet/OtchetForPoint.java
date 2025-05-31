package ru.semenov.germesplusfabric.model.othcet;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.semenov.germesplusfabric.model.PointOfSale;
import ru.semenov.germesplusfabric.model.products.ProductForIndividual;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OtchetForPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private LocalDate startDate;
    private LocalDate endDate;

    @ElementCollection
    @CollectionTable(name = "otchetsForPointProducts", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "products")
    private List<ProductForIndividual> products;

    @ManyToOne
    @JoinColumn(name = "point_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PointOfSale pointOfSale;

    private String name;
    private String description;

    private Integer totalPrice;

}
