package com.maestro.repository;

import com.maestro.model.Lesson;
import com.maestro.model.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, UUID> {
    List<Lesson> findByPlayListUuid(UUID playListUuid);
}
