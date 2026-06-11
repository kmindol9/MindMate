package com.mindmate.service;

import com.mindmate.model.ProblemAnalysis;
import org.springframework.stereotype.Service;

@Service
public class FeedbackGenerator {

    public String generate(
            ProblemAnalysis analysis,
            String evidence) {

        return """
                =====================
                MindMate 분석 결과
                =====================
                
                [감정 분석]
                %s
                
                [문제 유형]
                %s
                
                [핵심 원인]
                %s
                
                [객관적 관점]
                %s
                
                [현실적인 대안]
                %s
                
                =====================
                [관련 데이터 및 근거]
                %s
                """
                .formatted(
                        analysis.getEmotion(),
                        analysis.getProblemType(),
                        analysis.getCoreCause(),
                        analysis.getObjectiveView(),
                        analysis.getAlternatives(),
                        evidence
                );
    }
}