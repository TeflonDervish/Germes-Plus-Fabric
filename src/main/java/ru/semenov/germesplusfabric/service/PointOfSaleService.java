package ru.semenov.germesplusfabric.service;

import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import ru.semenov.germesplusfabric.model.PointOfSale;
import ru.semenov.germesplusfabric.repository.PointOfSaleRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class PointOfSaleService {

    private static final Log log = LogFactory.getLog(PointOfSaleService.class);
    private PointOfSaleRepository pointOfSaleRepository;


    public PointOfSale getById(Long id) {
        log.info("Получение точки по " + id);
        return pointOfSaleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Точка с таким id не найдена"));
    }

    public List<PointOfSale> getAll() {
        log.info("Получение списка всех точек");
        return pointOfSaleRepository.findAll();
    }

    public List<PointOfSale> getByName(String name) {
        return pointOfSaleRepository.findByNameContainingIgnoreCase(name);
    }
}
