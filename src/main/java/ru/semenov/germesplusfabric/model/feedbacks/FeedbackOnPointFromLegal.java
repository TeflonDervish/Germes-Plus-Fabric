package ru.semenov.germesplusfabric.model.feedbacks;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.semenov.germesplusfabric.model.PointOfSale;
import ru.semenov.germesplusfabric.model.persons.LegalPerson;

@Entity
@Getter @Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class FeedbackOnPointFromLegal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "point_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PointOfSale pointOfSale;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private LegalPerson legalPerson;

    @Column(columnDefinition = "TEXT")
    private String text;

    private Double grade;

}
