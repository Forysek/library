package com.library.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BooksCopiesDto {
    private long id;
    private String title;
    private String status;
}
