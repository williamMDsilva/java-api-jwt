package com.maestro.controller;

import com.maestro.model.PlayList;
import com.maestro.model.Score;
import com.maestro.repository.PlayListRepository;
import com.maestro.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ScoreController {
    @Autowired
    private ScoreRepository scoreRepository;

    @GetMapping("/scores")
    public Page<Score> getScores(Pageable pageable){
        return scoreRepository.findAll(pageable);
    }

    @PostMapping("/scores")
    public @Valid Score createScores(@Valid @RequestBody Score score) {
        return scoreRepository.save(score);
    }
}
