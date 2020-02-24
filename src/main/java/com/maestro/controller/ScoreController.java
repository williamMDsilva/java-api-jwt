package com.maestro.controller;

import com.maestro.exception.ResourceNotFoundException;
import com.maestro.model.Score;
import com.maestro.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api")
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

    @PutMapping("/scores/{scoresId}")
    public Score updateScore(@PathVariable UUID scoresId,
                             @Valid @RequestBody Score scoreRequest) {
        return scoreRepository.findById(scoresId)
                .map(score -> {
                    score.setName(scoreRequest.getName());
                    score.setCategory(scoreRequest.getCategory());
                    score.setDescription(scoreRequest.getDescription());
                    score.setLink(scoreRequest.getLink());
                    return scoreRepository.save(score);
                }).orElseThrow(() -> new ResourceNotFoundException("Score not found with id " + scoresId));
    }
}
