package com.mindmate.service;

import org.springframework.stereotype.Service;

@Service
public class ProblemClassifier {

    public String extractProblemType(String aiResponse) {

        if (aiResponse.contains("학업 스트레스")) {
            return "학업 스트레스";
        }

        if (aiResponse.contains("진로 고민")) {
            return "진로 고민";
        }

        if (aiResponse.contains("인간관계 문제")) {
            return "인간관계 문제";
        }

        if (aiResponse.contains("비교 불안")) {
            return "비교 불안";
        }

        if (aiResponse.contains("소비 및 금전 고민")) {
            return "소비 및 금전 고민";
        }

        if (aiResponse.contains("일상 선택 고민")) {
            return "일상 선택 고민";
        }

        if (aiResponse.contains("시간 관리 문제")) {
            return "시간 관리 문제";
        }

        if (aiResponse.contains("미래 불안")) {
            return "미래 불안";
        }

        return "기타";
    }
}