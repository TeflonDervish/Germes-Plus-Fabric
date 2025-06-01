
package ru.semenov.germesplusfabric.enums;


import lombok.Getter;

@Getter
public enum RoleManager {

    USER("Менеджер"),
    ADMIN("Главный менеджер");

    private final String roleName;

    RoleManager(String roleName) {
        this.roleName = roleName;
    }
}