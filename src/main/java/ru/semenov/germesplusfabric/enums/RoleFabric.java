
package ru.semenov.germesplusfabric.enums;


import lombok.Getter;

@Getter
public enum RoleFabric {

    USER("Менеджер"),
    ADMIN("Главный менеджер");

    private final String roleName;

    RoleFabric(String roleName) {
        this.roleName = roleName;
    }
}