package ru.semenov.germesplusfabric.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.semenov.germesplusfabric.model.othcet.OtchetForFabric;

import java.util.List;

public interface OtchetForFabricRepository extends JpaRepository<OtchetForFabric, Long> {

    List<OtchetForFabric> findByFabricId(Long pointOfSaleId);

    List<OtchetForFabric> findByFabricIdAndNameContaining(Long pointOfSaleId, String name);
}