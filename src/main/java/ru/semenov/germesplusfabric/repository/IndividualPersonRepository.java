package ru.semenov.germesplusfabric.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.semenov.germesplusfabric.model.persons.IndividualPerson;

import java.util.Optional;

@Repository
public interface IndividualPersonRepository extends JpaRepository<IndividualPerson, Long> {

    Optional<IndividualPerson> findByEmail(String email);

    boolean existsByEmail(String email);
}
