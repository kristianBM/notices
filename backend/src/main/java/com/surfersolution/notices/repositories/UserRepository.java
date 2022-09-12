package com.surfersolution.notices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.surfersolution.notices.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
