package ru.semenov.germesplusfabric.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.semenov.germesplusfabric.model.PointOfSale;
import ru.semenov.germesplusfabric.model.Sclad;
import ru.semenov.germesplusfabric.model.products.ProductForIndividual;

import java.util.List;

public interface ScladRepository extends JpaRepository<Sclad, Long> {

    List<Sclad> findByPointOfSale(PointOfSale pointOfSale);

    Sclad findByPointOfSaleAndProductForIndividual(PointOfSale pointOfSale, ProductForIndividual productForIndividual);

    List<Sclad> findByProductForIndividual_NameContaining(String name);

}
