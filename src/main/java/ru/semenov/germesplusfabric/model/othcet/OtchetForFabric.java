package ru.semenov.germesplusfabric.model.othcet;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.semenov.germesplusfabric.model.Fabric;
import ru.semenov.germesplusfabric.model.orders.OrderForFabric;
import ru.semenov.germesplusfabric.model.orders.OrderForLegal;

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

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "otchets_for_fabric_individual",
            joinColumns = @JoinColumn(name = "otchet_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private List<OrderForFabric> orderForFabrics;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "otchets_for_fabric_legal",
            joinColumns = @JoinColumn(name = "otchet_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private List<OrderForLegal> orderForLegals;

    @ManyToOne
    @JoinColumn(name = "fabric_otchet_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Fabric fabric;

    @Column(length = 100)
    private String name;
    private String description;

    private Integer totalPrice;
    private Integer count;
    private Integer meanPrice;

    public Integer getOrderCount() {
        this.count = orderForFabrics.size() + orderForLegals.size();
        return count;
    }

    public Integer getTotalPrice() {
        totalPrice = 0;
        for (OrderForFabric order : orderForFabrics)
            totalPrice += order.getTotalPrice();
        for (OrderForLegal order : orderForLegals)
            totalPrice += order.getTotalPrice();
        return totalPrice;
    }

    public Integer getMeanPrice() {
        if (count == 0) return totalPrice;
        return totalPrice / count;
    }

}
