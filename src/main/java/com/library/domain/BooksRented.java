package com.library.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity(name = "books_rented")
public class BooksRented {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @OneToMany(
            targetEntity = Readers.class,
            mappedBy = "booksRented",
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY
    )
    @JsonManagedReference
    private List<Readers> readers = new ArrayList<>();

    @NotNull
    @OneToMany(
            targetEntity = BooksCopies.class,
            mappedBy = "booksRented",
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY
    )
    @JsonManagedReference
    private List<BooksCopies> booksCopies = new ArrayList<>();

    @NotNull
    @Column(name = "rent_date")
    private LocalDate rentDate;

    @Column(name = "return_date")
    private LocalDate returnDate;
}
