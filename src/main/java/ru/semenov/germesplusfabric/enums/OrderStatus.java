package ru.semenov.germesplusfabric.enums;


import lombok.Getter;

@Getter
public enum OrderStatus {
    WAITING_ACCESS("Ожидает подтверждения"),
    IN_PROGRESS("В работе"),
    ON_THE_WAY("В пути"),
    WAITING("Ожидает получения"),
    COMPLETED("Завершен"),
    CANCELLED("Отменен"),
    REFUND("Возврат");

    private final String title;

    OrderStatus(String title) {
        this.title = title;
    }
}
