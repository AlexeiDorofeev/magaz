package com.doro.magaz.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "magazine")
public class Magazine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Date publicationDate;

    private String isbn;

    @ManyToMany
    @JoinTable(
        name = "magazine_author",
        joinColumns = @JoinColumn(name = "magazine_id"),
        inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors;

}
