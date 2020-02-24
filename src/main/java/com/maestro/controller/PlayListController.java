package com.maestro.controller;

import com.maestro.exception.ResourceNotFoundException;
import com.maestro.model.PlayList;
import com.maestro.repository.PlayListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class PlayListController {

    @Autowired
    private PlayListRepository playListRepository;

    @GetMapping("/playlists")
    public Page<PlayList> getPlayLists(Pageable pageable){
       return playListRepository.findAll(pageable);
    }

    @PostMapping("/playlists")
    public @Valid PlayList createPlayList(@Valid @RequestBody PlayList playlist) {
        return playListRepository.save(playlist);
    }

    @PutMapping("/playlists/{playListId}")
    public PlayList updatePlayList(@PathVariable UUID playListId,
                                   @Valid @RequestBody PlayList playListRequest) {
        return playListRepository.findById(playListId)
                .map(playlist -> {
                    playlist.setName(playListRequest.getName());
                    playlist.setCategory(playListRequest.getCategory());
                    playlist.setDescription(playListRequest.getDescription());
                    return playListRepository.save(playlist);
                }).orElseThrow(() -> new ResourceNotFoundException("playList not found with id " + playListId));
    }

//    @DeleteMapping("/questions/{questionId}")
//    public ResponseEntity<?> deleteQuestion(@PathVariable Long questionId) {
//        return questionRepository.findById(questionId)
//                .map(question -> {
//                    questionRepository.delete(question);
//                    return ResponseEntity.ok().build();
//                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
//    }
}
