package ru.semenov.germesplusfabric.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.semenov.germesplusfabric.model.products.ProductForIndividual;

import java.util.List;

@Repository
public interface ProductForIndividualRepository extends JpaRepository<ProductForIndividual, Long> {

    List<ProductForIndividual> findByNameContainingIgnoreCase(String name);

    @Query("SELECT p FROM ProductForIndividual p WHERE " +
            "(:minPrice IS NULL OR p.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR p.price <= :maxPrice) AND " +
            "(:configurations IS NULL OR p.configuration IN :configurations) AND " +
            "(:mechanisms IS NULL OR p.mechanism IN :mechanisms) AND " +
            "(:fillings IS NULL OR p.filling IN :fillings)")
    List<ProductForIndividual> findWithFilters(
            @Param("minPrice") Integer minPrice,
            @Param("maxPrice") Integer maxPrice,
            @Param("configurations") List<String> configurations,
            @Param("mechanisms") List<String> mechanisms,
            @Param("fillings") List<String> fillings);

    List<ProductForIndividual> findAllByOrderByPriceAsc();
    List<ProductForIndividual> findAllByOrderByPriceDesc();
}
