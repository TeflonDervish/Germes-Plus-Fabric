package ru.semenov.germesplusfabric.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.semenov.germesplusfabric.model.Fabric;
import ru.semenov.germesplusfabric.model.PointOfSale;
import ru.semenov.germesplusfabric.model.Sclad;
import ru.semenov.germesplusfabric.model.ScladFabric;
import ru.semenov.germesplusfabric.model.products.ProductForIndividual;

import java.util.List;

public interface ScladFabricRepository extends JpaRepository<ScladFabric, Long> {

    List<ScladFabric> findByFabric(Fabric fabric);

    ScladFabric findByFabricAndProductForIndividual(Fabric fabric, ProductForIndividual productForIndividual);

    List<ScladFabric> findByProductForIndividual_NameContaining(String name);

    ScladFabric findByProductForIndividual(ProductForIndividual productForIndividual);

}
