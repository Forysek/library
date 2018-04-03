package com.library.domain.dto;

import com.library.domain.Reader;
import com.library.domain.Title;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class BookCopyDto {
    private Long id;
    private String status;
    private Title title;
    private Reader reader;
}
