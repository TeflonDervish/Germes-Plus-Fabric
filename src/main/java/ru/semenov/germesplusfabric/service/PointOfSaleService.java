package ru.semenov.germesplusfabric.service;

import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.semenov.germesplusfabric.model.PointOfSale;
import ru.semenov.germesplusfabric.repository.PointOfSaleRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class PointOfSaleService {

    private static final Log log = LogFactory.getLog(PointOfSaleService.class);
    private PointOfSaleRepository pointOfSaleRepository;

    public PointOfSale createPoint(PointOfSale pointOfSale) {
        PointOfSale newPoint = PointOfSale.builder()
                .address(pointOfSale.getAddress())
                .email(pointOfSale.getEmail())
                .name(pointOfSale.getName())
                .pointOnTheMap(pointOfSale.getPointOnTheMap())
                .phoneNumber(pointOfSale.getPhoneNumber())
                .pointManager(null)
                .build();
        return pointOfSaleRepository.save(newPoint);
    }

    @Transactional
    public PointOfSale updatePoint(PointOfSale pointOfSale) {
        PointOfSale oldPoint = getById(pointOfSale.getId());
        oldPoint.setAddress(pointOfSale.getAddress());
        oldPoint.setEmail(pointOfSale.getEmail());
        oldPoint.setName(pointOfSale.getName());
        oldPoint.setPointOnTheMap(pointOfSale.getPointOnTheMap());
        oldPoint.setPhoneNumber(pointOfSale.getPhoneNumber());

        return pointOfSaleRepository.save(oldPoint);
    }

    public void deleteById(Long id) {
        log.info("Удаление по " + id);
        pointOfSaleRepository.deleteById(id);
    }

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
