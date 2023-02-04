package com.doro.magaz.repository;

import com.doro.magaz.entity.MagazineAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MagazineAuthorRepository extends JpaRepository<MagazineAuthor, Long> {
}
