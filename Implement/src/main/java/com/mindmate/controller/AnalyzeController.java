package com.mindmate.controller;

import com.mindmate.dto.ConcernRequest;
import com.mindmate.service.EmotionAnalyzer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AnalyzeController {

    private final EmotionAnalyzer emotionAnalyzer;

    public AnalyzeController(EmotionAnalyzer emotionAnalyzer) {
        this.emotionAnalyzer = emotionAnalyzer;
    }

    @PostMapping("/analyze")
    public String analyzeConcern(@RequestBody ConcernRequest request) {

        return emotionAnalyzer.analyze(
                request.getConcern()
        );
    }
}