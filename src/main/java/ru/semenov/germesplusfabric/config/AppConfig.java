package ru.semenov.germesplusfabric.config;

import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.semenov.germesplusfabric.enums.RoleFabric;
import ru.semenov.germesplusfabric.model.Fabric;
import ru.semenov.germesplusfabric.model.persons.FabricManager;
import ru.semenov.germesplusfabric.repository.FabricManagerRepository;
import ru.semenov.germesplusfabric.service.FabricService;

@Configuration
@AllArgsConstructor
public class AppConfig {

    private static final Log log = LogFactory.getLog(AppConfig.class);
    private final FabricManagerRepository fabricManagerRepository;
    private final PasswordEncoder passwordEncoder;
    private final FabricService fabricService;

    @Bean
    public ApplicationRunner initializeUsers() {
        Fabric fabric = fabricService.getById(1L);
        return args -> {
            initializeUserIfNotExists(
                "manager@mail.ru",
                    "Manager",
                    "Name",
                    "1234567890",
                    passwordEncoder.encode("manager"),
                    RoleFabric.USER,
                    fabric
            );

            initializeUserIfNotExists(
                    "admin@mail.ru",
                    "Admin",
                    "Name",
                    "1234567890",
                    passwordEncoder.encode("admin"),
                    RoleFabric.ADMIN,
                    fabric
            );
        };
    }

    private void initializeUserIfNotExists(String email,
                                           String surname,
                                           String name,
                                           String phoneNumber,
                                           String password,
                                           RoleFabric role,
                                           Fabric fabric) {
        if (!fabricManagerRepository.existsByEmail(email)) {
            FabricManager user = FabricManager.builder()
                    .email(email)
                    .surname(surname)
                    .name(name)
                    .phoneNumber(phoneNumber)
                    .password(password)
                    .role(role)
                    .fabric(fabric)
                    .build();
            fabricManagerRepository.save(user);
        }
    }
}
