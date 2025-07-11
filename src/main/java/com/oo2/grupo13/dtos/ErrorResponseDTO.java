package com.oo2.grupo13.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ErrorResponseDTO {
    String error;

    public ErrorResponseDTO(String error) {
        this.error = error;
    }
    
}
