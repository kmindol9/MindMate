package com.mindmate.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Service
public class DataRetriever {

    private final Map<String, List<String>> evidenceMap;

    public DataRetriever() {

        try {

            ObjectMapper objectMapper = new ObjectMapper();

            InputStream inputStream =
                    new ClassPathResource("statistics.json")
                            .getInputStream();

            evidenceMap =
                    objectMapper.readValue(
                            inputStream,
                            new TypeReference<Map<String, List<String>>>() {}
                    );

        } catch (Exception e) {

            throw new RuntimeException(
                    "statistics.json 로드 실패",
                    e
            );
        }
    }

    public String getEvidence(String problemType) {

        List<String> evidences =
                evidenceMap.get(problemType);

        if (evidences == null || evidences.isEmpty()) {

            return "관련 근거 데이터를 찾을 수 없습니다.";
        }

        StringBuilder result =
                new StringBuilder();

        for (String evidence : evidences) {

            result.append("- ")
                    .append(evidence)
                    .append("\n");
        }

        return result.toString();
    }
}