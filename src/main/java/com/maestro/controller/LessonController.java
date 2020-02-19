package com.maestro.controller;

import com.maestro.exception.ResourceNotFoundException;
import com.maestro.model.Lesson;
import com.maestro.repository.LessonRepository;
import com.maestro.repository.PlayListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
public class LessonController {
    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private PlayListRepository playListRepository;

    @GetMapping("/playLists/{playListUuid}/lessons")
    public List<Lesson> getLessonsByPlayList(@PathVariable UUID playListUuid){
        return lessonRepository.findByPlayListUuid(playListUuid);
    }

    @PostMapping("/playLists/{playListUuid}/lessons")
    public Lesson addLesson(@PathVariable UUID playListUuid,
                            @Valid @RequestBody Lesson lesson) {
        return playListRepository.findById(playListUuid)
                .map(playList -> {
                    lesson.setPlayList(playList);
                    return lessonRepository.save(lesson);
                }).orElseThrow(() -> new ResourceNotFoundException("PlayList not found with id " + playListUuid));
    }

    @PutMapping("/playLists/{playListUuid}/lessons/{lessonId}")
    public Lesson updateLesson(@PathVariable UUID playListUuid,
                               @PathVariable UUID lessonUuid,
                               @Valid @RequestBody Lesson lessonRequest) {
        if(!playListRepository.existsById(playListUuid)) {
            throw new ResourceNotFoundException("PlayList not found with id " + playListUuid);
        }

        return lessonRepository.findById(lessonUuid)
                .map(lesson -> {
                    lesson.setDescription(lessonRequest.getDescription());
                    lesson.setTitle(lessonRequest.getTitle());
                    lesson.setLink(lessonRequest.getLink());
                    return lessonRepository.save(lesson);
                }).orElseThrow(() -> new ResourceNotFoundException("Lesson not found with id " + lessonUuid));
    }
}
