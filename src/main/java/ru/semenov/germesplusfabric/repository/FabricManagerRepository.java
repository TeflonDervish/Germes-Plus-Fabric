package ru.semenov.germesplusfabric.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import ru.semenov.germesplusfabric.model.persons.FabricManager;

import java.util.List;

public interface FabricManagerRepository extends JpaRepository<FabricManager, Long> {

    List<FabricManager> findBySurnameContainingIgnoreCase(String surname);

    boolean existsByEmail(String email);

    UserDetails findByEmail(String email);
}
