package ru.semenov.germesplusfabric.model.korzina;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.semenov.germesplusfabric.model.persons.IndividualPerson;
import ru.semenov.germesplusfabric.model.products.ProductForIndividual;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class KorzinaForIndividual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private IndividualPerson individualPerson;

    @ElementCollection
    @CollectionTable(name = "korzinaProduct", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "products")
    private List<ProductForIndividual> products = new ArrayList<>();

    public void addProduct(ProductForIndividual product) {
        products.add(product);
    }

    public void deleteProduct(ProductForIndividual product) {
        products.remove(product);
    }

    public boolean isInKorzina(ProductForIndividual product) {
        return products.contains(product);
    }
}
