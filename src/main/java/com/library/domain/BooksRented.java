package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "books_rented")
public class BooksRented {

    @Column(name = "rent_date")
    private Date rentDate;

    @Column(name = "return date")
    private Date returnDate;


}
