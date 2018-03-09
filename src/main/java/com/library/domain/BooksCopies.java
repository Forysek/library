package com.library.domain;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "books_copies")
public class BooksCopies {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "title_id")
    private Titles titles;

    @OneToMany(
            targetEntity = BooksRented.class,
            mappedBy = "booksRented",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<BooksRented> booksRented = new ArrayList<>();
}
