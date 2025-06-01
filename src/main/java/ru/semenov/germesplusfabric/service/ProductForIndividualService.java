package ru.semenov.germesplusfabric.service;

import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import ru.semenov.germesplusfabric.model.products.ProductForIndividual;
import ru.semenov.germesplusfabric.repository.ProductForIndividualRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductForIndividualService {

    private static final Log log = LogFactory.getLog(ProductForIndividualService.class);
    private ProductForIndividualRepository productForIndividualRepository;


    public List<ProductForIndividual> getAll() {
        log.info("Получение списка всех товаров");
        return productForIndividualRepository.findAll();
    }

    public ProductForIndividual getById(Long id) {
        log.info("Получение продукта по " + id);
        return productForIndividualRepository.findById(id).orElse(null);
    }

    public List<ProductForIndividual> getByName(String name) {
        log.info("Поиск по имени");
        return productForIndividualRepository.findByNameContainingIgnoreCase(name);
    }


    public List<ProductForIndividual> getBySort(String sort) {
        log.info("Сортировка продуктов по цене");
        List<ProductForIndividual> productForIndividuals;
        if (sort.equals("price_asc")) {
            productForIndividuals = productForIndividualRepository.findAllByOrderByPriceAsc();
        } else {
            productForIndividuals = productForIndividualRepository.findAllByOrderByPriceDesc();
        }
        return productForIndividuals;
    }
}
