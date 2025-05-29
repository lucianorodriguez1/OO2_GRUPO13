package com.oo2.grupo13.entities;

public enum ESTADO {
    NUEVO,
    EN_PROCESO,
    COMPLETADO,
    CANCELADO;

    @Override
    public String toString() {
        switch (this) {
            case NUEVO:
                return "Nuevo";
            case EN_PROCESO:
                return "En Proceso";
            case COMPLETADO:
                return "Completado";
            case CANCELADO:
                return "Cancelado";
            default:
                return super.toString();
        }
    }
}
