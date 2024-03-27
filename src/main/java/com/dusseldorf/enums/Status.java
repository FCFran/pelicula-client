package com.dusseldorf.enums;

public enum Status {

    ACTIVO("Activo"),
    INACTIVO("Inactivo");

    private String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
