package ru.semenov.germesplusfabric.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.semenov.germesplusfabric.model.PointOfSale;
import ru.semenov.germesplusfabric.model.persons.FabricManager;

import java.util.List;

public interface PointOfSaleRepository extends JpaRepository<PointOfSale, Long> {

    List<PointOfSale> findByNameContainingIgnoreCase(String name);

}
