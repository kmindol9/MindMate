package com.mindmate.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindmate.config.GeminiConfig;
import com.mindmate.dto.GeminiRequest;
import com.mindmate.model.ProblemAnalysis;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class EmotionAnalyzer {

    private final GeminiConfig geminiConfig;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final DataRetriever dataRetriever;
    private final ProblemStructurer problemStructurer;
    private final FeedbackGenerator feedbackGenerator;

    public EmotionAnalyzer(GeminiConfig geminiConfig,
                           RestTemplate restTemplate,
                           DataRetriever dataRetriever,
                           ProblemStructurer problemStructurer,
                           FeedbackGenerator feedbackGenerator) {

        this.geminiConfig = geminiConfig;
        this.restTemplate = restTemplate;
        this.dataRetriever = dataRetriever;
        this.problemStructurer = problemStructurer;
        this.feedbackGenerator = feedbackGenerator;
    }

    public String analyze(String userText) {

        return analyze(userText, "counsel");
    }

    public String analyze(String userText, String mode) {

        try {

            String url =
                    "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key="
                            + geminiConfig.getApiKey();

            String prompt =
                    createPrompt(userText, mode);

            GeminiRequest.Part part =
                    new GeminiRequest.Part(prompt);

            GeminiRequest.Content content =
                    new GeminiRequest.Content(List.of(part));

            GeminiRequest requestBody =
                    new GeminiRequest(List.of(content));

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<GeminiRequest> request =
                    new HttpEntity<>(requestBody, headers);

            ResponseEntity<String> response =
                    restTemplate.exchange(
                            url,
                            HttpMethod.POST,
                            request,
                            String.class
                    );

            JsonNode root =
                    objectMapper.readTree(response.getBody());

            String aiResponse =
                    root
                            .path("candidates")
                            .get(0)
                            .path("content")
                            .path("parts")
                            .get(0)
                            .path("text")
                            .asText();

            aiResponse = aiResponse.replace("**", "");

            ProblemAnalysis analysis =
                    problemStructurer.structure(aiResponse);

            String problemType =
                    analysis.getProblemType();

            String evidence =
                    dataRetriever.getEvidence(problemType);

            return feedbackGenerator.generate(
                    analysis,
                    evidence
            );

        } catch (Exception e) {

            System.out.println(
                    "External AI service unavailable. Fallback mode activated."
            );

            return fallbackAnalyze(userText, mode);
        }
    }

    private String createPrompt(
            String userText,
            String mode) {

        if ("decision".equals(mode)) {
            return """
                    당신은 MindMate라는 의사결정 지원 시스템이다.
                    
                    사용자의 선택 상황을 분석하여
                    감정적인 위로보다 합리적인 판단 기준과 현실적인 선택 방향을 제공하라.
                    
                    사용자의 결정을 대신하지 말고,
                    각 선택지의 장점과 단점, 고려해야 할 기준을 정리하여
                    사용자가 스스로 판단할 수 있도록 도와라.
                    
                    반드시 아래 형식을 지켜 답변하라.
                    
                    [감정 분석]
                    사용자가 선택 과정에서 느끼는 감정이나 부담을 분석
                    
                    [문제 유형]
                    다음 중 가장 적합한 유형을 선택
                    
                    - 소비 및 금전 고민
                    - 일상 선택 고민
                    - 시간 관리 문제
                    - 진로 고민
                    - 학업 스트레스
                    - 인간관계 문제
                    - 비교 불안
                    - 미래 불안
                    - 기타
                    
                    [핵심 원인]
                    선택을 어렵게 만드는 핵심 원인을 설명
                    
                    [객관적 관점]
                    현재 선택 상황을 장단점과 판단 기준 중심으로 객관적으로 해석
                    
                    [현실적인 대안]
                    실행 가능한 선택 방향과 판단 기준을 제시
                    
                    사용자 고민:
                    """ + userText;
        }

        return """
                당신은 MindMate라는 상담 지원 및 의사결정 지원 시스템이다.
                
                사용자의 고민과 불안을 분석하여
                감정 상태, 문제 유형, 핵심 원인, 객관적 관점, 현실적인 대안을 제공하라.
                
                단순한 위로만 제공하지 말고,
                사용자가 자신의 상황을 객관적으로 이해하고
                스스로 판단할 수 있도록 도와라.
                
                절대로 사용자의 결정을 대신하지 말고,
                현실적인 가능성과 대안을 제시하라.
                
                반드시 아래 형식을 지켜 답변하라.
                
                [감정 분석]
                사용자의 주요 감정을 분석
                
                [문제 유형]
                다음 중 가장 적합한 유형을 선택
                
                - 진로 고민
                - 학업 스트레스
                - 인간관계 문제
                - 비교 불안
                - 소비 및 금전 고민
                - 일상 선택 고민
                - 시간 관리 문제
                - 미래 불안
                - 기타
                
                [핵심 원인]
                문제의 근본 원인을 설명
                
                [객관적 관점]
                현재 상황을 객관적으로 해석
                
                [현실적인 대안]
                실행 가능한 대안을 제시
                
                사용자 고민:
                """ + userText;
    }

    private String fallbackAnalyze(
            String userText,
            String mode) {

        ProblemAnalysis analysis =
                new ProblemAnalysis();

        String problemType =
                classifyByKeyword(userText, mode);

        if ("decision".equals(mode)) {
            analysis.setEmotion(
                    "입력된 내용에서는 결정을 쉽게 내리지 못하는 부담감과 선택 이후의 만족도에 대한 고민이 나타납니다. " +
                            "현재 감정은 강한 불안보다는 여러 선택지 사이에서 더 나은 방향을 찾고자 하는 고민에 가깝습니다."
            );
        } else {
            analysis.setEmotion(
                    "입력된 내용에서는 현재 상황을 혼자 정리하기 어려워하는 부담감과 불안감이 나타납니다. " +
                            "문제를 해결하고 싶지만 어디서부터 시작해야 할지 막막한 상태로 볼 수 있습니다."
            );
        }

        analysis.setProblemType(problemType);

        analysis.setCoreCause(
                "입력된 내용에서 확인되는 핵심 원인은 고민이나 선택의 기준이 명확하지 않아 " +
                        "현재 상황을 객관적으로 정리하기 어려운 점으로 볼 수 있습니다."
        );

        analysis.setObjectiveView(
                "현재 상황은 단순히 하나의 정답을 찾기보다, 감정적인 부담과 현실적인 조건을 함께 고려해야 하는 문제입니다. " +
                        "따라서 지금 당장 완벽한 결정을 내리기보다, 선택 기준을 분리해서 생각하는 것이 도움이 됩니다."
        );

        if ("decision".equals(mode)) {
            analysis.setAlternatives(
                    "1. 각 선택지의 장점과 단점을 간단히 적어봅니다.\n" +
                            "2. 비용, 시간, 만족도, 후회 가능성처럼 판단 기준을 정합니다.\n" +
                            "3. 지금 가장 중요한 기준이 무엇인지 우선순위를 정합니다.\n" +
                            "4. 선택 후 되돌리기 어려운 결정인지 확인합니다.\n" +
                            "5. 두 선택지의 차이가 크지 않다면, 현재 만족도가 더 높은 선택을 우선 고려합니다."
            );
        } else {
            analysis.setAlternatives(
                    "1. 현재 고민을 감정, 원인, 해결 가능한 부분으로 나누어 정리합니다.\n" +
                            "2. 당장 통제할 수 있는 작은 행동부터 정합니다.\n" +
                            "3. 혼자 해결하기 어렵다면 신뢰할 수 있는 사람이나 전문가에게 도움을 요청합니다.\n" +
                            "4. 완벽한 해결보다 상황을 조금씩 개선하는 방향으로 접근합니다."
            );
        }

        String evidence =
                dataRetriever.getEvidence(problemType);

        return """
        =====================
        MindMate 안내
        =====================
        
        현재 외부 AI 응답이 지연되어,
        MindMate 내부 기준을 바탕으로 기본 분석을 제공합니다.
        
        아래 결과는 입력 내용을 기반으로 한 예비 분석이며,
        상황을 정리하는 참고 자료로 활용할 수 있습니다.
        
        """
                + feedbackGenerator.generate(
                analysis,
                evidence
        );
    }

    private String classifyByKeyword(
            String userText,
            String mode) {

        if (userText == null || userText.isBlank()) {
            return "기타";
        }

        String text =
                userText.toLowerCase();

        if (text.contains("성적")
                || text.contains("시험")
                || text.contains("공부")
                || text.contains("학점")
                || text.contains("과제")) {
            return "학업 스트레스";
        }

        if (text.contains("진로")
                || text.contains("취업")
                || text.contains("전공")
                || text.contains("직업")
                || text.contains("꿈")) {
            return "진로 고민";
        }

        if (text.contains("친구")
                || text.contains("관계")
                || text.contains("부모")
                || text.contains("가족")
                || text.contains("연애")
                || text.contains("사람")) {
            return "인간관계 문제";
        }

        if (text.contains("돈")
                || text.contains("구매")
                || text.contains("살까")
                || text.contains("가격")
                || text.contains("소비")
                || text.contains("아낄까")
                || text.contains("비싸")) {
            return "소비 및 금전 고민";
        }

        if (text.contains("시간")
                || text.contains("일정")
                || text.contains("계획")
                || text.contains("미루")) {
            return "시간 관리 문제";
        }

        if (text.contains("비교")
                || text.contains("남들")
                || text.contains("친구들보다")) {
            return "비교 불안";
        }

        if (text.contains("미래")
                || text.contains("앞으로")
                || text.contains("불안")) {
            return "미래 불안";
        }

        if ("decision".equals(mode)) {
            return "일상 선택 고민";
        }

        return "기타";
    }
}