package ru.semenov.germesplusfabric.model.feedbacks;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.semenov.germesplusfabric.model.persons.IndividualPerson;
import ru.semenov.germesplusfabric.model.products.ProductForIndividual;

import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackOnProductForIndividual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private ProductForIndividual productForIndividual;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private IndividualPerson individualPerson;

    @Column(columnDefinition = "TEXT")
    private String text;

    private Double grade;

    private LocalDate date;

    @ElementCollection
    @CollectionTable(name="urlsForFeedbackProductForIndividuals", joinColumns = @JoinColumn(name="id"))
    @Column(name = "urls")
    private List<String> urls;

}
