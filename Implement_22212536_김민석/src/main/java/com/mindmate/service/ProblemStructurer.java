package com.mindmate.service;

import com.mindmate.model.ProblemAnalysis;
import org.springframework.stereotype.Service;

@Service
public class ProblemStructurer {

    public ProblemAnalysis structure(String aiResponse) {

        ProblemAnalysis analysis = new ProblemAnalysis();

        analysis.setEmotion(
                extractSection(aiResponse,
                        "[감정 분석]",
                        "[문제 유형]")
        );

        analysis.setProblemType(
                normalizeProblemType(
                        extractSection(aiResponse,
                                "[문제 유형]",
                                "[핵심 원인]")
                )
        );

        analysis.setCoreCause(
                extractSection(aiResponse,
                        "[핵심 원인]",
                        "[객관적 관점]")
        );

        analysis.setObjectiveView(
                extractSection(aiResponse,
                        "[객관적 관점]",
                        "[현실적인 대안]")
        );

        analysis.setAlternatives(
                extractSection(aiResponse,
                        "[현실적인 대안]",
                        null)
        );

        return analysis;
    }

    private String extractSection(
            String text,
            String startTag,
            String endTag) {

        int start = text.indexOf(startTag);

        if (start == -1) {
            return "";
        }

        start += startTag.length();

        int end;

        if (endTag == null) {
            end = text.length();
        } else {
            end = text.indexOf(endTag, start);

            if (end == -1) {
                end = text.length();
            }
        }

        return text.substring(start, end).trim();
    }

    private String normalizeProblemType(String problemType) {

        if (problemType == null) {
            return "기타";
        }

        String cleaned = problemType
                .replace("-", "")
                .replace("*", "")
                .trim();

        if (cleaned.contains("학업 스트레스")) {
            return "학업 스트레스";
        }

        if (cleaned.contains("진로 고민")) {
            return "진로 고민";
        }

        if (cleaned.contains("인간관계 문제")) {
            return "인간관계 문제";
        }

        if (cleaned.contains("비교 불안")) {
            return "비교 불안";
        }

        if (cleaned.contains("소비 및 금전 고민")) {
            return "소비 및 금전 고민";
        }

        if (cleaned.contains("일상 선택 고민")) {
            return "일상 선택 고민";
        }

        if (cleaned.contains("시간 관리 문제")) {
            return "시간 관리 문제";
        }

        if (cleaned.contains("미래 불안")) {
            return "미래 불안";
        }

        return "기타";
    }
}