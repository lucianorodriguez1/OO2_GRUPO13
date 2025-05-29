package com.oo2.grupo13.models;

public enum TURNO {
    MANANA,
    TARDE,
    NOCHE;

    @Override
    public String toString() {
        switch (this) {
            case MANANA:
                return "Mañana";
            case TARDE:
                return "Tarde";
            case NOCHE:
                return "Noche";
            default:
                return super.toString();
        }
    }
}
