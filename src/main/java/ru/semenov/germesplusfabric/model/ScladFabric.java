package ru.semenov.germesplusfabric.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.semenov.germesplusfabric.model.products.ProductForIndividual;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "sclad_fabric")
public class ScladFabric {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fabric_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Fabric fabric;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private ProductForIndividual productForIndividual;

    @Column(nullable = false)
    private Integer count;


}
