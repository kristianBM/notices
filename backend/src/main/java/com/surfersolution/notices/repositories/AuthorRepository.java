package com.surfersolution.notices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.surfersolution.notices.domain.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
