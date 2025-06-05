package ru.semenov.germesplusfabric.service;

import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import ru.semenov.germesplusfabric.dto.OtchetDto;
import ru.semenov.germesplusfabric.model.Fabric;
import ru.semenov.germesplusfabric.model.orders.OrderForFabric;
import ru.semenov.germesplusfabric.model.orders.OrderForLegal;
import ru.semenov.germesplusfabric.model.othcet.OtchetForFabric;
import ru.semenov.germesplusfabric.model.persons.FabricManager;
import ru.semenov.germesplusfabric.repository.OtchetForFabricRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class OtchetForFabricService {

    private static final Log log = LogFactory.getLog(OtchetForFabricService.class);
    private final OtchetForFabricRepository otchetForFabricRepository;
    private final OrderForFabricService orderForFabricService;
    private final OrderForLegalService orderForLegalService;

    public List<OtchetForFabric> getByFabric(Fabric fabric) {
        return otchetForFabricRepository.findByFabricId(fabric.getId());
    }

    public OtchetForFabric getById(Long id) {
        return otchetForFabricRepository.findById(id).orElse(null);
    }

    public OtchetForFabric createOtchet(FabricManager manager, OtchetDto otchetDto) {
        OtchetForFabric otchet = new OtchetForFabric();
        List<OrderForFabric> orderForFabrics = orderForFabricService
                .getByDateBetween(
                        otchetDto.getStartDate(),
                        otchetDto.getEndDate(),
                        manager.getFabric());

        List<OrderForLegal> orderForLegals = orderForLegalService
                .getByDateBetween(
                        otchetDto.getStartDate(),
                        otchetDto.getEndDate());

        otchet.setStartDate(otchetDto.getStartDate());
        otchet.setEndDate(otchetDto.getEndDate());

        otchet.setFabric(manager.getFabric());
        otchet.setDescription(otchetDto.getDescription());
        otchet.setName(otchetDto.getName());
        otchet.setDescription(otchetDto.getDescription());

        otchet.setOrderForFabrics(orderForFabrics);
        otchet.setOrderForLegals(orderForLegals);
        otchet.getOrderCount();
        otchet.getTotalPrice();
        otchet.getMeanPrice();

        return otchetForFabricRepository.save(otchet);
    }


    public List<OtchetForFabric> getByPointOfSaleAndNameContains(Fabric fabric, String query) {
        return otchetForFabricRepository.findByFabricIdAndNameContaining(fabric.getId(), query);
    }

    public void deleteById(Long id) {
        log.info("Удаление отчета");
        otchetForFabricRepository.deleteById(id);
    }
}
