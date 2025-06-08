package ru.semenov.germesplusfabric.service;

import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import ru.semenov.germesplusfabric.model.Fabric;
import ru.semenov.germesplusfabric.model.ScladFabric;
import ru.semenov.germesplusfabric.model.persons.FabricManager;
import ru.semenov.germesplusfabric.model.products.ProductForIndividual;
import ru.semenov.germesplusfabric.repository.ScladFabricRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ScladFabricService {


    private static final Log log = LogFactory.getLog(ScladFabricService.class);
    private final ScladFabricRepository scladFabricRepository;
    private final ProductForIndividualService productForIndividualService;

    public List<ScladFabric> getByFabric(Fabric fabric) {
        log.info("Получение информации со склада " + fabric);
        return scladFabricRepository.findByFabric(fabric);
    }

    public ScladFabric getByFabricAndProductId(Fabric fabric, Long productId) {
        return scladFabricRepository.findByFabricAndProductForIndividual(fabric,
                productForIndividualService.getById(productId));
    }

    public List<ScladFabric> getByProductName(String productName) {
        return scladFabricRepository.findByProductForIndividual_NameContaining(productName);
    }

    public ScladFabric getByProduct(ProductForIndividual product) {
        return scladFabricRepository.findByProductForIndividual(product);
    }

    public void addProduct(Long productId, Integer count, FabricManager manager) {
        ProductForIndividual productForIndividual = productForIndividualService.getById(productId);
        ScladFabric scladFabric = ScladFabric.builder()
                .count(count)
                .productForIndividual(productForIndividual)
                .fabric(manager.getFabric())
                .build();
        scladFabricRepository.save(scladFabric);
    }

    public ScladFabric getById(Long id) {
        return scladFabricRepository.getById(id);
    }

    public void sale(Long scladId, FabricManager manager) {
        ScladFabric sclad = getById(scladId);
        sclad.setCount(sclad.getCount() - 1);
        if (sclad.getCount() == 0) {
            scladFabricRepository.delete(sclad);
        } else {
            scladFabricRepository.save(sclad);
        }
    }
}
