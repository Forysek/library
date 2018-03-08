package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "books_rented")
public class BooksRented {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "id")
    private long id;

    private Readers readers;
    private BooksCopies booksCopies;

    @NotNull
    @Column(name = "rent_date")
    private Date rentDate;

    @NotNull
    @Column(name = "return date")
    private Date returnDate;

    @ManyToOne
    @JoinColumn(name = "reader_id")
    public Readers getReaders() {
        return readers;
    }

    @ManyToOne
    @JoinColumn(name = "book_copy_id")
    public BooksCopies getBooksCopies() {
        return booksCopies;
    }
}
