package ru.semenov.germesplusfabric.model.othcet;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.semenov.germesplusfabric.model.Fabric;
import ru.semenov.germesplusfabric.model.products.ProductForIndividual;
import ru.semenov.germesplusfabric.model.products.ProductForLegal;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OtchetForFabric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private LocalDate startDate;
    private LocalDate endDate;

    @ElementCollection
    @CollectionTable(name = "otchetForFabricIndividual", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "products")
    private List<ProductForIndividual> productsForIndividuals;

    @ElementCollection
    @CollectionTable(name = "otchetForFabricLegal", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "products")
    private List<ProductForLegal> productsForLegals;

    @ManyToOne
    @JoinColumn(name = "fabric_otchet_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Fabric fabric;

    @Column(length = 100)
    private String name;
    private String description;

    private Integer totalPrice;
    
}
