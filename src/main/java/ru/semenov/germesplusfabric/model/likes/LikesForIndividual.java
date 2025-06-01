package ru.semenov.germesplusfabric.model.likes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.semenov.germesplusfabric.model.persons.IndividualPerson;
import ru.semenov.germesplusfabric.model.products.ProductForIndividual;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LikesForIndividual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private IndividualPerson individualPerson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private ProductForIndividual productForIndividual;
}
