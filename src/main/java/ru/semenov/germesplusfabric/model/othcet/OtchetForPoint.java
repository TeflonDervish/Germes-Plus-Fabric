package ru.semenov.germesplusfabric.model.othcet;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.semenov.germesplusfabric.model.PointOfSale;
import ru.semenov.germesplusfabric.model.orders.OrderForIndividual;

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

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "otchets_for_point_orders",
            joinColumns = @JoinColumn(name = "otchet_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private List<OrderForIndividual> orders;


    @ManyToOne
    @JoinColumn(name = "point_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PointOfSale pointOfSale;

    private String name;
    private String description;

    private Integer totalPrice;
    private Integer count;
    private Integer meanPrice;

    public Integer getOrderCount() {
        this.count = orders.size();
        return count;
    }

    public Integer getTotalPrice() {
        totalPrice = 0;
        for (OrderForIndividual order : orders)
            totalPrice += order.getTotalPrice();
        return totalPrice;
    }

    public Integer getMeanPrice() {
        if (count == 0) return totalPrice;
        return totalPrice / count;
    }
}