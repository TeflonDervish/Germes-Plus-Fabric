package ru.semenov.germesplusfabric.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.semenov.germesplusfabric.enums.RoleFabric;
import ru.semenov.germesplusfabric.model.persons.FabricManager;
import ru.semenov.germesplusfabric.repository.FabricManagerRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class FabricManagerService implements UserDetailsService {

    private final FabricManagerRepository fabricManagerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return fabricManagerRepository.findByEmail(username);
    }

    public FabricManager createManager(FabricManager reg, FabricManager current) {
        FabricManager newManger = FabricManager.builder()
                .surname(reg.getSurname())
                .name(reg.getName())
                .email(reg.getName())
                .phoneNumber(reg.getPhoneNumber())
                .fabric(current.getFabric())
                .role(RoleFabric.USER)
                .password(passwordEncoder.encode(reg.getPassword()))
                .build();
        return fabricManagerRepository.save(newManger);
    }

    public List<FabricManager> getAll() {
        return fabricManagerRepository.findAll();
    }

    public List<FabricManager> getBySurname(String surname) {
        return fabricManagerRepository.findBySurnameContainingIgnoreCase(surname);
    }
}
