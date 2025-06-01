package ru.semenov.germesplusfabric.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import ru.semenov.germesplusfabric.model.persons.FabricManager;
import ru.semenov.germesplusfabric.model.persons.PointManager;

import java.util.List;

public interface PointManagerRepository extends JpaRepository<PointManager, Long> {

    List<PointManager> findBySurnameContainingIgnoreCase(String surname);

    boolean existsByEmail(String email);

    PointManager findByEmail(String email);
}
