package ru.semenov.germesplusfabric.model.feedbacks;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.semenov.germesplusfabric.model.PointOfSale;
import ru.semenov.germesplusfabric.model.persons.IndividualPerson;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackOnPointFromIndividual {

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
    private IndividualPerson individualPerson;

    @Column(columnDefinition = "TEXT")
    private String text;

    private double grade;

}
