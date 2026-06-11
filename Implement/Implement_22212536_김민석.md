![logo](./images/logo.png)

# 1. Implement

## Revision History

| Revision date | Version # | Description  | Author |
| ------------- | --------- | ------------ | ------ |
| 2026-06-12    | 4.0.0     | 구현 문서 작성 완료  | 김민석    |
|               |           |              |        |
|               |           |              |        |
|               |           |              |        |

---

## Contents

1. Introduction
2. Implementation Overview
3. Development Environment
4. Project Structure
5. Main Implementation Features
6. Backend Implementation
7. Frontend Implementation
8. AI API and Fallback Mode
9. Privacy and Security
10. Deployment
11. Execution Guide
12. Testing Result
13. Limitations and Future Work
14. Glossary
15. References

---

# 1. Introduction

## 1) Overview

MindMate는 사용자의 고민과 선택 상황을 입력받아 감정 상태, 문제 유형, 핵심 원인, 객관적 관점, 현실적인 대안을 제공하는 AI 기반 의사결정 지원 웹 서비스이다.

본 시스템은 단순한 상담 챗봇이 아니라, 사용자가 자신의 상황을 객관적으로 이해하고 스스로 판단할 수 있도록 돕는 Decision Support System을 목표로 한다.

최종 구현 단계에서는 기존의 분석 및 설계 문서를 바탕으로 Java 17과 Spring Boot를 활용하여 웹 애플리케이션 형태로 구현하였다. 사용자는 웹 브라우저를 통해 MindMate에 접속할 수 있으며, 고민 분석과 간단 의사결정 기능을 사용할 수 있다.

---

## 2) Implementation Objectives

본 구현 단계의 목적은 설계 문서에서 정의한 MindMate의 주요 기능을 실제 동작 가능한 웹 서비스로 구현하는 것이다.

주요 구현 목표는 다음과 같다.

* 사용자가 웹 화면을 통해 고민이나 선택 상황을 입력할 수 있도록 구현
* 입력된 내용을 AI 기반으로 분석하여 감정, 문제 유형, 핵심 원인, 대안을 제공
* 문제 유형별 통계 및 사례 데이터를 함께 제공
* 외부 AI API 오류 또는 요청 한도 초과 상황에서도 Fallback Mode를 통해 기본 분석 결과 제공
* 사용자 입력 내용을 기본적으로 서버에 저장하지 않는 구조 적용
* GitHub 기반 버전 관리 및 Render 기반 웹 배포 수행

---

# 2. Implementation Overview

## 1) System Implementation Summary

MindMate는 Spring Boot 기반의 MVC 구조로 구현되었다.

사용자는 HTML/Thymeleaf 기반 웹 화면에서 고민 또는 선택 상황을 입력한다. 입력 데이터는 Controller를 통해 Service 계층으로 전달되며, EmotionAnalyzer가 Gemini API를 호출하여 AI 기반 분석을 수행한다.

AI 응답은 ProblemStructurer를 통해 감정 분석, 문제 유형, 핵심 원인, 객관적 관점, 현실적인 대안으로 구조화된다. 이후 DataRetriever가 문제 유형에 맞는 근거 데이터를 조회하고, FeedbackGenerator가 최종 분석 결과를 생성한다.

최종 결과는 result.html과 JavaScript를 통해 카드 형태로 사용자에게 제공된다.

---

## 2) System Flow

MindMate의 전체 처리 흐름은 다음과 같다.

```text
User Input
 ↓
PageController
 ↓
EmotionAnalyzer
 ↓
Gemini API or Fallback Mode
 ↓
ProblemStructurer
 ↓
DataRetriever
 ↓
FeedbackGenerator
 ↓
Result Page
```

---

## 3) Implementation Characteristics

본 구현은 다음과 같은 특징을 가진다.

* Spring Boot 기반 웹 서비스 구현
* Thymeleaf를 활용한 서버 사이드 HTML 렌더링
* Gemini API를 활용한 AI 분석
* API 실패 시 내부 규칙 기반 Fallback Mode 제공
* JSON 파일 기반 통계 및 사례 데이터 조회
* sessionStorage 기반 임시 입력 유지
* Dockerfile 기반 배포 환경 구성
* Render를 통한 웹 서비스 배포

---

# 3. Development Environment

## 1) Programming Language and Framework

| Category             | Technology            |
| -------------------- | --------------------- |
| Programming Language | Java 17               |
| Framework            | Spring Boot 3.5.3     |
| Build Tool           | Maven                 |
| Template Engine      | Thymeleaf             |
| Frontend             | HTML, CSS, JavaScript |
| AI API               | Gemini API            |
| Deployment           | Docker, Render        |
| Version Control      | Git, GitHub           |
| IDE                  | IntelliJ IDEA         |

---

## 2) Required Software

MindMate를 로컬 환경에서 실행하기 위해 필요한 소프트웨어는 다음과 같다.

| Software             | Purpose               |
| -------------------- | --------------------- |
| Java JDK 17 or later | Spring Boot 애플리케이션 실행 |
| Maven                | 의존성 관리 및 빌드           |
| IntelliJ IDEA        | 개발 및 디버깅              |
| Git                  | 버전 관리                 |
| Docker               | 배포 가능한 실행 환경 구성       |
| Web Browser          | 웹 서비스 접속              |

---

# 4. Project Structure

구현 프로젝트는 GitHub 저장소의 `Implement` 폴더 안에 위치한다.

```text
Implement
 ├─ .mvn
 ├─ src
 │   └─ main
 │       ├─ java
 │       │   └─ com.mindmate
 │       │       ├─ MindMateApplication.java
 │       │       ├─ config
 │       │       │   ├─ AppConfig.java
 │       │       │   └─ GeminiConfig.java
 │       │       ├─ controller
 │       │       │   ├─ AnalyzeController.java
 │       │       │   └─ PageController.java
 │       │       ├─ dto
 │       │       │   ├─ ConcernRequest.java
 │       │       │   └─ GeminiRequest.java
 │       │       ├─ model
 │       │       │   └─ ProblemAnalysis.java
 │       │       └─ service
 │       │           ├─ DataRetriever.java
 │       │           ├─ EmotionAnalyzer.java
 │       │           ├─ FeedbackGenerator.java
 │       │           ├─ ProblemClassifier.java
 │       │           └─ ProblemStructurer.java
 │       │
 │       └─ resources
 │           ├─ application.properties
 │           ├─ statistics.json
 │           ├─ templates
 │           │   ├─ index.html
 │           │   ├─ counsel.html
 │           │   ├─ decision.html
 │           │   ├─ privacy.html
 │           │   └─ result.html
 │           └─ static
 │               ├─ css
 │               │   └─ style.css
 │               └─ js
 │                   ├─ loading.js
 │                   └─ result.js
 │
 ├─ Dockerfile
 ├─ .dockerignore
 ├─ .gitignore
 ├─ pom.xml
 └─ Implement_22212536_김민석.md
```

---

# 5. Main Implementation Features

## 1) 고민 분석 기능

사용자는 고민 분석 페이지에서 자신의 고민을 자연어로 입력할 수 있다.

시스템은 입력 내용을 바탕으로 다음 항목을 분석한다.

* 감정 분석
* 문제 유형
* 핵심 원인
* 객관적 관점
* 현실적인 대안
* 관련 데이터 및 근거

이 기능은 학업, 진로, 인간관계, 비교 불안, 미래 불안과 같은 진지한 고민을 구조적으로 정리하는 데 초점을 둔다.

---

## 2) 간단 의사결정 기능

사용자는 간단 의사결정 페이지에서 일상적인 선택 상황을 입력할 수 있다.

예를 들어 다음과 같은 상황을 분석할 수 있다.

* 물건을 살지 말지 고민하는 상황
* 시간을 어떻게 사용할지 고민하는 상황
* 여러 선택지 중 하나를 골라야 하는 상황
* 현재 만족도와 후회 가능성을 비교해야 하는 상황

시스템은 입력 내용을 장단점, 판단 기준, 현실적인 선택 방향 중심으로 분석한다.

---

## 3) 결과 카드 UI

분석 결과는 단순 텍스트가 아니라 카드 형태로 나누어 출력된다.

결과 화면은 다음 항목으로 구성된다.

* 감정 분석
* 문제 유형
* 핵심 원인
* 객관적 관점
* 현실적인 대안
* 관련 데이터 및 근거

이를 통해 사용자는 긴 분석 결과를 한눈에 이해할 수 있다.

---

## 4) 개인정보 안내 기능

MindMate는 사용자의 고민과 선택 내용을 민감한 정보로 간주한다.

따라서 개인정보 안내 페이지를 통해 입력 내용 처리 방식, 임시 저장 방식, 외부 AI 사용 가능성, 기본 분석 모드에 대한 정보를 사용자에게 제공한다.

---

# 6. Backend Implementation

## 1) Application Entry Point

`MindMateApplication` 클래스는 Spring Boot 애플리케이션의 실행 진입점이다.

```java
@SpringBootApplication
public class MindMateApplication {

    public static void main(String[] args) {
        SpringApplication.run(MindMateApplication.class, args);
    }
}
```

이 클래스가 실행되면 Spring Boot 서버가 시작되고, 사용자는 웹 브라우저를 통해 MindMate에 접속할 수 있다.

---

## 2) Configuration

### AppConfig

`AppConfig` 클래스는 외부 API 요청에 사용되는 `RestTemplate` Bean을 등록한다.

이를 통해 Gemini API 호출 시 HTTP 요청을 보낼 수 있다.

---

### GeminiConfig

`GeminiConfig` 클래스는 `application.properties`에 설정된 Gemini API Key 값을 읽어오는 역할을 한다.

API Key는 소스 코드에 직접 저장하지 않고 환경변수로 관리한다.

```properties
gemini.api.key=${GEMINI_API_KEY:}
server.port=${PORT:8080}
```

---

## 3) Controller Layer

### PageController

`PageController`는 웹 화면 이동과 분석 요청 처리를 담당한다.

주요 URL 매핑은 다음과 같다.

| Path        | Description     |
| ----------- | --------------- |
| `/`         | MindMate 소개 페이지 |
| `/counsel`  | 고민 분석 페이지       |
| `/decision` | 간단 의사결정 페이지     |
| `/privacy`  | 개인정보 안내 페이지     |
| `/analyze`  | 분석 요청 처리        |

사용자가 `/counsel` 또는 `/decision` 페이지에서 입력을 제출하면 `/analyze`로 요청이 전달된다.

---

### AnalyzeController

`AnalyzeController`는 REST API 형태의 분석 요청을 처리한다.

웹 화면뿐만 아니라 API 테스트나 외부 요청에서도 분석 기능을 사용할 수 있도록 `/api/analyze` 엔드포인트를 제공한다.

---

## 4) DTO and Model

### ConcernRequest

`ConcernRequest`는 REST API 요청에서 사용자의 고민 내용을 전달받기 위한 DTO이다.

---

### GeminiRequest

`GeminiRequest`는 Gemini API 요청 형식에 맞게 데이터를 구성하기 위한 DTO이다.

---

### ProblemAnalysis

`ProblemAnalysis`는 분석 결과를 저장하는 모델 클래스이다.

주요 필드는 다음과 같다.

* emotion
* problemType
* coreCause
* objectiveView
* alternatives

---

## 5) Service Layer

### EmotionAnalyzer

`EmotionAnalyzer`는 MindMate의 핵심 분석 서비스이다.

주요 역할은 다음과 같다.

* 사용자 입력을 바탕으로 Gemini API 요청 생성
* 고민 분석 모드와 간단 의사결정 모드에 따른 프롬프트 분리
* AI 응답 파싱
* AI 응답 실패 시 Fallback Mode 실행
* 최종 피드백 생성 흐름 제어

---

### ProblemStructurer

`ProblemStructurer`는 AI 응답 또는 내부 분석 결과를 정해진 항목으로 구조화한다.

분석 결과는 다음 항목으로 나누어진다.

* 감정 분석
* 문제 유형
* 핵심 원인
* 객관적 관점
* 현실적인 대안

---

### ProblemClassifier

`ProblemClassifier`는 입력 내용이나 AI 응답을 바탕으로 문제 유형을 분류하기 위한 클래스이다.

문제 유형은 다음과 같이 구분된다.

* 학업 스트레스
* 진로 고민
* 인간관계 문제
* 비교 불안
* 소비 및 금전 고민
* 일상 선택 고민
* 시간 관리 문제
* 미래 불안
* 기타

---

### DataRetriever

`DataRetriever`는 `statistics.json` 파일을 읽어 문제 유형에 맞는 통계 및 사례 데이터를 조회한다.

이를 통해 단순한 감정적 위로가 아니라 근거 기반 피드백을 제공할 수 있다.

---

### FeedbackGenerator

`FeedbackGenerator`는 감정 분석 결과, 문제 유형, 핵심 원인, 객관적 관점, 현실적인 대안, 관련 데이터를 하나의 최종 결과로 통합한다.

---

# 7. Frontend Implementation

## 1) Template Pages

MindMate는 Thymeleaf 기반 HTML 페이지로 구성된다.

| File            | Description     |
| --------------- | --------------- |
| `index.html`    | MindMate 소개 페이지 |
| `counsel.html`  | 고민 분석 입력 페이지    |
| `decision.html` | 간단 의사결정 입력 페이지  |
| `privacy.html`  | 개인정보 안내 페이지     |
| `result.html`   | 분석 결과 페이지       |

---

## 2) CSS Design

`style.css`는 전체 UI 스타일을 정의한다.

주요 디자인 요소는 다음과 같다.

* 상단 네비게이션 바
* 입력 카드
* 기능 소개 카드
* 분석 결과 카드
* 로딩 오버레이
* 모바일 반응형 레이아웃

---

## 3) JavaScript

### loading.js

`loading.js`는 입력 화면에서 다음 기능을 수행한다.

* 입력값 공백 검사
* 분석 요청 시 로딩 화면 표시
* 분석 버튼 비활성화
* sessionStorage를 활용한 작성 중 입력 내용 임시 유지
* 작성 내용 지우기 기능 제공

---

### result.js

`result.js`는 서버에서 전달된 분석 결과 텍스트를 항목별로 분리하여 카드 형태로 렌더링한다.

이를 통해 긴 분석 결과를 사용자가 이해하기 쉬운 UI로 제공한다.

---

# 8. AI API and Fallback Mode

## 1) Gemini API Usage

MindMate는 AI 기반 분석을 위해 Gemini API를 사용한다.

Gemini API는 사용자의 입력 내용을 분석하여 감정 상태, 문제 유형, 핵심 원인, 객관적 관점, 현실적인 대안을 생성하는 데 활용된다.

---

## 2) API Limit Policy

Gemini API는 사용 등급, 모델, Google Cloud 프로젝트에 따라 요청 제한이 존재한다.

일일 요청 한도인 RPD(Requests Per Day)는 Pacific Time 기준 자정에 초기화된다.

한국 시간 기준으로는 대략 다음과 같이 볼 수 있다.

```text
Pacific Daylight Time 기간: 한국 시간 오후 4시 전후
Pacific Standard Time 기간: 한국 시간 오후 5시 전후
```

단, 분당 요청 수나 분당 토큰 수 제한은 하루 단위가 아니라 짧은 시간 단위로 적용된다.

---

## 3) Fallback Mode

외부 AI API가 다음과 같은 이유로 응답하지 못할 수 있다.

* 무료 요청 한도 초과
* 서버 오류
* 네트워크 오류
* 일시적인 API 지연

이러한 상황에서 MindMate는 서비스가 중단되지 않도록 내부 규칙 기반 Fallback Mode로 자동 전환된다.

Fallback Mode는 다음 결과를 제공한다.

* 예상 감정
* 문제 유형
* 핵심 원인
* 객관적 관점
* 현실적인 대안
* 관련 데이터 및 근거

이를 통해 Gemini API 사용량이 모두 소진된 상황에서도 사용자는 기본적인 분석 결과를 받을 수 있다.

---

# 9. Privacy and Security

## 1) User Input Privacy

MindMate는 사용자의 고민과 선택 내용을 민감한 정보로 간주한다.

따라서 사용자의 입력 내용은 기본적으로 서버 데이터베이스에 저장하지 않는다.

---

## 2) Temporary Input Storage

사용자가 작성 중인 내용은 브라우저의 sessionStorage를 통해 현재 탭에서만 임시 유지된다.

sessionStorage는 브라우저 탭을 닫으면 데이터가 삭제되므로, 장기 저장을 피하면서도 새로고침이나 분석 실패 상황에서 사용자가 같은 내용을 다시 입력하지 않아도 되는 장점이 있다.

---

## 3) API Key Security

Gemini API Key는 소스 코드에 직접 저장하지 않는다.

API Key는 환경변수 `GEMINI_API_KEY`를 통해 주입한다.

```properties
gemini.api.key=${GEMINI_API_KEY:}
```

또한 `.gitignore`를 통해 다음 항목이 GitHub에 포함되지 않도록 관리한다.

* `.idea/`
* `target/`
* `.env`
* local or secret properties files

---

# 10. Deployment

## 1) Deployment Platform

MindMate는 Render를 통해 웹 서비스 형태로 배포하였다.

배포된 웹 서비스 주소는 다음과 같다.

```text
https://mindmate-q895.onrender.com
```

---

## 2) Docker Deployment

MindMate는 Dockerfile을 포함하여 배포 환경에 독립적으로 실행될 수 있도록 구성하였다.

Dockerfile은 Maven 기반 빌드 단계와 Java 실행 단계를 분리하여 애플리케이션을 빌드하고 실행한다.

---

## 3) Render Configuration

Render 배포 설정은 다음과 같다.

```text
Root Directory: Implement
Dockerfile Path: Dockerfile
Environment Variable: GEMINI_API_KEY
```

Spring Boot 서버 포트는 다음과 같이 설정한다.

```properties
server.port=${PORT:8080}
```

이를 통해 로컬 환경에서는 8080 포트를 사용하고, Render 배포 환경에서는 플랫폼이 제공하는 PORT 값을 사용할 수 있다.

---

# 11. Execution Guide

## 1) Local Execution

로컬에서 실행하려면 다음 조건이 필요하다.

* Java 17
* Maven
* Gemini API Key

IntelliJ IDEA에서 실행하는 과정은 다음과 같다.

1. `Implement` 폴더를 IntelliJ IDEA로 연다.
2. 실행 설정에서 환경변수 `GEMINI_API_KEY`를 등록한다.
3. `MindMateApplication.java`를 실행한다.
4. 브라우저에서 아래 주소로 접속한다.

```text
http://localhost:8080
```

---

## 2) Web Service Execution

배포된 웹 서비스는 다음 주소에서 접속할 수 있다.

```text
https://mindmate-q895.onrender.com
```

Render 무료 플랜을 사용하기 때문에 일정 시간 동안 접속이 없으면 서버가 일시적으로 비활성화될 수 있다.

이 경우 첫 접속 시 페이지 로딩에 시간이 걸릴 수 있다.

---

# 12. Testing Result

## 1) Page Access Test

다음 페이지들이 정상적으로 접속되는 것을 확인하였다.

| URL         | Result |
| ----------- | ------ |
| `/`         | 정상 접속  |
| `/counsel`  | 정상 접속  |
| `/decision` | 정상 접속  |
| `/privacy`  | 정상 접속  |

---

## 2) Analysis Function Test

고민 분석 페이지와 간단 의사결정 페이지에서 입력 후 분석 버튼을 눌렀을 때, 결과 페이지로 이동하고 분석 결과가 카드 형태로 출력되는 것을 확인하였다.

---

## 3) Fallback Mode Test

Gemini API 요청 한도 초과 또는 외부 AI 응답 실패 상황에서 시스템이 중단되지 않고 기본 분석 모드로 전환되는 것을 확인하였다.

Fallback Mode에서는 내부 기준을 바탕으로 감정 분석, 문제 유형, 핵심 원인, 객관적 관점, 현실적인 대안, 관련 데이터 및 근거가 출력된다.

---

## 4) Privacy Function Test

입력 내용은 서버 데이터베이스에 저장하지 않으며, 작성 중인 내용은 브라우저의 sessionStorage에만 임시 유지되는 것을 확인하였다.

또한 사용자가 작성 내용 지우기 버튼을 통해 임시 입력 내용을 직접 삭제할 수 있도록 구현하였다.

---

# 13. Limitations and Future Work

## 1) Limitations

현재 구현에는 다음과 같은 한계가 있다.

* Gemini API 무료 사용량에 따라 AI 기반 분석 기능이 제한될 수 있음
* Fallback Mode의 분석 결과는 외부 AI 응답보다 단순할 수 있음
* 통계 및 사례 데이터가 제한된 JSON 파일에 기반함
* 전문 상담이나 의료적 조언을 대체할 수 없음
* 사용자별 장기 기록 기능은 제공하지 않음

---

## 2) Future Work

향후 개선 방향은 다음과 같다.

* 문제 유형별 Fallback 응답 세분화
* 통계 및 사례 데이터 확장
* 감정 분석 정확도 개선
* 사용자 선택 기반 결과 저장 기능 추가
* 관리자용 데이터 관리 기능 추가
* 상담 기관 또는 전문가 연결 기능 확장
* UI/UX 개선 및 모바일 최적화 강화

---

# 14. Glossary

| Term                    | Description                                      |
| ----------------------- | ------------------------------------------------ |
| MindMate                | 사용자의 고민과 선택 상황을 분석하여 방향성을 제공하는 AI 기반 의사결정 지원 시스템 |
| Decision Support System | 사용자의 결정을 대신하지 않고 객관적 정보와 대안을 제공하여 판단을 돕는 시스템     |
| Emotion Analysis        | 입력된 텍스트를 기반으로 사용자의 감정 상태를 분석하는 과정                |
| Problem Classification  | 입력 내용과 감정 분석 결과를 바탕으로 문제 유형을 분류하는 과정             |
| Problem Structuring     | 고민을 감정, 원인, 상황, 대안 중심으로 구조화하는 과정                 |
| Data Retrieval          | 문제 유형에 맞는 통계 및 사례 데이터를 조회하는 과정                   |
| Feedback Generation     | 분석 결과와 근거 데이터를 기반으로 최종 피드백을 생성하는 과정              |
| Fallback Mode           | 외부 AI API 실패 시 내부 규칙 기반으로 기본 분석 결과를 제공하는 방식      |
| Gemini API              | Google에서 제공하는 생성형 AI API                         |
| sessionStorage          | 브라우저 탭이 유지되는 동안 데이터를 임시 저장하는 웹 저장소               |
| Render                  | 웹 서비스를 배포할 수 있는 클라우드 플랫폼                         |
| Docker                  | 애플리케이션 실행 환경을 컨테이너 형태로 구성하는 기술                   |

---

# 15. References

1. **Oracle Java Documentation**
   https://docs.oracle.com/en/java/

> Java 프로그래밍 언어 및 표준 라이브러리 공식 문서

2. **Spring Boot Documentation**
   https://docs.spring.io/spring-boot/

> Spring Boot 기반 웹 애플리케이션 구현 참고 자료

3. **Thymeleaf Documentation**
   https://www.thymeleaf.org/documentation.html

> 서버 사이드 HTML 템플릿 구성 참고 자료

4. **Google AI for Developers - Gemini API Documentation**
   https://ai.google.dev/

> Gemini API 사용 및 요청 한도 정책 참고 자료

5. **Render Documentation**
   https://render.com/docs

> 웹 서비스 배포 및 환경변수 설정 참고 자료

6. **GitHub Documentation**
   https://docs.github.com

> GitHub 기반 버전 관리 및 저장소 관리 참고 자료

7. **Docker Documentation**
   https://docs.docker.com

> Dockerfile 및 컨테이너 기반 배포 참고 자료

---

## Core Value

> MindMate는 사용자의 결정을 대신하는 시스템이 아니라,
> 사용자가 자신의 상황을 이해하고 현실적인 방향을 찾도록 돕는 시스템이다.
