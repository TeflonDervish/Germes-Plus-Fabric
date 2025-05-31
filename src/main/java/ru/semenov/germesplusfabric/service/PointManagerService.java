package ru.semenov.germesplusfabric.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.semenov.germesplusfabric.enums.RoleManager;
import ru.semenov.germesplusfabric.model.persons.PointManager;
import ru.semenov.germesplusfabric.repository.PointManagerRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class PointManagerService {

    private final PointManagerRepository pointManagerRepository;

    public PointManager createManager(PointManager reg, PointManager current) {
        PointManager newManger = PointManager.builder()
                .surname(reg.getSurname())
                .name(reg.getName())
                .email(reg.getName())
                .phoneNumber(reg.getPhoneNumber())
                .pointOfSale(current.getPointOfSale())
                .role(RoleManager.USER)
                .password(reg.getPassword())
                .build();
        return pointManagerRepository.save(newManger);
    }

    public List<PointManager> getAll() {
        return pointManagerRepository.findAll();
    }

    public List<PointManager> getBySurname(String surname) {
        return pointManagerRepository.findBySurnameContainingIgnoreCase(surname);
    }
}
