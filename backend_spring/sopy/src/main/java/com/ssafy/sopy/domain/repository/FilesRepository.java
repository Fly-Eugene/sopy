package com.ssafy.sopy.domain.repository;

import com.ssafy.sopy.domain.entity.Files;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilesRepository extends JpaRepository<Files, Long> {
    Files save(Files files);
}
