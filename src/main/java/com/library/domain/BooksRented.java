package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity(name = "books_rented")
public class BooksRented {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "id")
    private long id;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "reader_id", referencedColumnName = "id")
    private Readers readers;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "book_copy_id", referencedColumnName = "id")
    private BooksCopies booksCopies;

    @NotNull
    @Column(name = "rent_date")
    private LocalDateTime rentDate;

    @Column(name = "return date")
    private LocalDateTime returnDate;
}
