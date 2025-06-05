package ru.semenov.germesplusfabric.model.feedbacks;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.semenov.germesplusfabric.model.PointOfSale;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class FeedbackOnPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "point_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private     PointOfSale pointOfSale;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String text;

    private Double grade;

    private LocalDate date;

}
