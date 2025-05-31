package ru.semenov.germesplusfabric.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.semenov.germesplusfabric.model.Fabric;

@Repository
public interface FabricRepository extends JpaRepository<Fabric, Long> {
}
