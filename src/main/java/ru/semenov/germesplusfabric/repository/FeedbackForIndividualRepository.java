package ru.semenov.germesplusfabric.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.semenov.germesplusfabric.model.feedbacks.FeedbackOnProductForIndividual;
import ru.semenov.germesplusfabric.model.products.ProductForIndividual;

import java.util.List;

@Repository
public interface FeedbackForIndividualRepository extends JpaRepository<FeedbackOnProductForIndividual, Long> {

    List<FeedbackOnProductForIndividual> findByProductForIndividual(ProductForIndividual productForIndividual);

}
