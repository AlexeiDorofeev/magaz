package com.doro.magaz.entity;

import lombok.Data;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "magazine_author")
public class MagazineAuthor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Magazine magazine;

    @ManyToOne
    @JoinColumn(name = "magazine_id")
    private Author author;

    @Override
    public String toString() {
        return getId().toString();
    }
}
