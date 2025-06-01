package ru.semenov.germesplusfabric.model.likes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.semenov.germesplusfabric.model.persons.LegalPerson;
import ru.semenov.germesplusfabric.model.products.ProductForLegal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LikesForLegal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private LegalPerson legalPerson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private ProductForLegal productForLegal;
}
