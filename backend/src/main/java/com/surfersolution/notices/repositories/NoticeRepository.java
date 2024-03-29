package com.surfersolution.notices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.surfersolution.notices.domain.Notice;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {

}
