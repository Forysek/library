package com.library.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class ReadersDto {
    private long id;
    private String firstName;
    private String lastName;
    private Date creationDate;
}
