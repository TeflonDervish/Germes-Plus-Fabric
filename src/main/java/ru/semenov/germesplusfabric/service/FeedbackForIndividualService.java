package ru.semenov.germesplusfabric.service;

import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import ru.semenov.germesplusfabric.model.feedbacks.FeedbackOnProductForIndividual;
import ru.semenov.germesplusfabric.model.persons.IndividualPerson;
import ru.semenov.germesplusfabric.model.products.ProductForIndividual;
import ru.semenov.germesplusfabric.repository.FeedbackForIndividualRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class FeedbackForIndividualService {

    private static final Log log = LogFactory.getLog(FeedbackForIndividualService.class);
    private FeedbackForIndividualRepository feedbackForIndividualRepository;
    private ProductForIndividualService productForIndividualService;


    public List<FeedbackOnProductForIndividual> getByProductForIndividual(Long productId) {
        log.info("Получение продуктов " + productId);
        ProductForIndividual product = productForIndividualService.getById(productId);
        return feedbackForIndividualRepository.findByProductForIndividual(product);
    }
}
