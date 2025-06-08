package com.oo2.grupo13.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmailDTO {

    private String[] toUser;
    private String subject;
    private String message;
}