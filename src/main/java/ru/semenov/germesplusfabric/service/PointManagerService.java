package ru.semenov.germesplusfabric.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.semenov.germesplusfabric.enums.RoleManager;
import ru.semenov.germesplusfabric.model.PointOfSale;
import ru.semenov.germesplusfabric.model.persons.FabricManager;
import ru.semenov.germesplusfabric.model.persons.PointManager;
import ru.semenov.germesplusfabric.repository.PointManagerRepository;
import ru.semenov.germesplusfabric.repository.PointOfSaleRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class PointManagerService {

    private final PointManagerRepository pointManagerRepository;
    private final PointOfSaleRepository pointOfSaleRepository;
    private final PointOfSaleService pointOfSaleService;
    private final PasswordEncoder passwordEncoder;

    public PointManager createManager(PointManager reg, PointManager current) {
        PointManager newManger = PointManager.builder()
                .surname(reg.getSurname())
                .name(reg.getName())
                .email(reg.getName())
                .phoneNumber(reg.getPhoneNumber())
                .pointOfSale(current.getPointOfSale())
                .role(RoleManager.USER)
                .password(passwordEncoder.encode(reg.getPassword()))
                .build();
        return pointManagerRepository.save(newManger);
    }

    public PointManager createGlavManager(PointManager reg, Long pointOfSaleId) {
        PointOfSale point = pointOfSaleService.getById(pointOfSaleId);
        PointManager newManger = PointManager.builder()
                .surname(reg.getSurname())
                .name(reg.getName())
                .email(reg.getName())
                .phoneNumber(reg.getPhoneNumber())
                .pointOfSale(point)
                .role(RoleManager.ADMIN)
                .password(passwordEncoder.encode(reg.getPassword()))
                .build();
        point.setPointManager(pointManagerRepository.save(newManger));
        pointOfSaleRepository.save(point);
        return point.getPointManager();
    }

    public List<PointManager> getAll() {
        return pointManagerRepository.findAll();
    }

    public List<PointManager> getBySurname(String surname) {
        return pointManagerRepository.findBySurnameContainingIgnoreCase(surname);
    }

    public void deleteById(Long id) {
        pointManagerRepository.deleteById(id);
    }

    public PointManager getById(Long id) {
        return pointManagerRepository.findById(id).orElse(null);
    }

    public PointManager update(Long managerId, PointManager newData) {
        PointManager oldManager = pointManagerRepository.findById(managerId).orElse(null);
        oldManager.setEmail(newData.getEmail());
        oldManager.setPhoneNumber(newData.getPhoneNumber());
//        oldManager.setPointOfSale(newData.getPointOfSale());
        oldManager.setName(newData.getName());
        oldManager.setSurname(newData.getSurname());
        return pointManagerRepository.save(oldManager);
    }
}
