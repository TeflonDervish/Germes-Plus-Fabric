package ru.semenov.germesplusfabric.enums;

import lombok.Getter;

@Getter
public enum LegalProductType {

    CLOTH("Ткань"),
    CARCASS("Каркас"),
    FILL("Наполнитель");

    private String type;

    LegalProductType(String type) {
        this.type = type;
    }

}
