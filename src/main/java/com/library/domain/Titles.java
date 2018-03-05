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
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "titles")
public class Titles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "publication_date")
    private String publicationDate;

    private List<BooksCopies> booksCopies = new ArrayList<>();

    @OneToMany(
            targetEntity = BooksCopies.class,
            mappedBy = "booksCopies",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    public List<BooksCopies> getBooksCopies() {
        return booksCopies;
    }
}
