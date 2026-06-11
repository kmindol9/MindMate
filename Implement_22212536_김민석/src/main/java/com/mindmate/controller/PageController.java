package com.mindmate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.mindmate.service.EmotionAnalyzer;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

    private final EmotionAnalyzer emotionAnalyzer;

    public PageController(
            EmotionAnalyzer emotionAnalyzer) {

        this.emotionAnalyzer = emotionAnalyzer;
    }

    @GetMapping("/")
    public String home() {

        return "index";
    }

    @GetMapping("/counsel")
    public String counsel() {

        return "counsel";
    }

    @GetMapping("/decision")
    public String decision() {

        return "decision";
    }

    @GetMapping("/privacy")
    public String privacy() {

        return "privacy";
    }

    @PostMapping("/analyze")
    public String analyze(
            @RequestParam String concern,
            @RequestParam(defaultValue = "counsel") String mode,
            Model model) {

        String result =
                emotionAnalyzer.analyze(concern, mode);

        model.addAttribute(
                "result",
                result
        );

        model.addAttribute(
                "mode",
                mode
        );

        return "result";
    }
}



