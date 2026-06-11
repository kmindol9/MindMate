# MindMate

MindMate는 사용자의 고민과 선택 상황을 분석하여 감정 상태, 문제 유형, 핵심 원인, 객관적 관점, 현실적인 대안을 제공하는 AI 기반 의사결정 지원 시스템입니다.

본 프로젝트는 단순한 상담 챗봇이 아니라, 사용자가 자신의 상황을 객관적으로 이해하고 스스로 판단할 수 있도록 돕는 Decision Support System을 목표로 합니다.

---

## 1. Project Overview

학생들은 학업, 진로, 인간관계, 비교 불안, 미래에 대한 불안 등 다양한 문제를 경험합니다.
그러나 이러한 고민을 객관적으로 정리하거나 현실적인 방향성을 찾는 것은 쉽지 않습니다.

MindMate는 사용자의 자연어 입력을 기반으로 고민을 분석하고, 문제를 구조화하여 사용자가 자신의 상황을 더 명확하게 이해할 수 있도록 돕습니다.

---

## 2. Main Features

### 2.1 고민 분석

사용자가 진지한 고민을 입력하면 MindMate는 다음 요소로 나누어 분석합니다.

* 감정 분석
* 문제 유형 분류
* 핵심 원인 도출
* 객관적 관점 제공
* 현실적인 대안 제시
* 관련 데이터 및 근거 제공

### 2.2 간단 의사결정 지원

일상적인 선택이나 가벼운 고민도 분석할 수 있습니다.

예를 들어 소비, 시간 관리, 메뉴 선택, 일정 선택과 같은 문제에 대해 장단점과 판단 기준을 정리하여 사용자가 합리적인 선택을 할 수 있도록 돕습니다.

### 2.3 기본 분석 모드

외부 AI API 서버가 불안정하거나 요청 한도를 초과한 경우에도 시스템이 중단되지 않도록 MindMate 내부 기준을 기반으로 기본 분석 결과를 제공합니다.

이를 통해 외부 API 장애 상황에서도 최소한의 분석 기능을 유지할 수 있습니다.

### 2.4 개인정보 보호 방향

MindMate는 사용자의 고민 내용을 민감한 정보로 간주합니다.

* 입력 내용은 기본적으로 서버에 저장하지 않습니다.
* 작성 중인 내용은 현재 브라우저 탭에서만 임시 유지됩니다.
* sessionStorage를 사용하므로 탭을 닫으면 임시 입력 내용은 사라집니다.
* 사용자가 직접 작성 내용을 지울 수 있습니다.

---

## 3. Tech Stack

* Java 17
* Spring Boot 3.5.3
* Maven
* Thymeleaf
* HTML / CSS / JavaScript
* Gemini API
* Jackson Databind

---

## 4. Project Structure

```text
src
 └─ main
     ├─ java
     │   └─ com.mindmate
     │       ├─ MindMateApplication.java
     │       ├─ config
     │       ├─ controller
     │       ├─ dto
     │       ├─ model
     │       └─ service
     │
     └─ resources
         ├─ application.properties
         ├─ statistics.json
         ├─ templates
         │   ├─ index.html
         │   ├─ counsel.html
         │   ├─ decision.html
         │   ├─ privacy.html
         │   └─ result.html
         └─ static
             ├─ css
             │   └─ style.css
             └─ js
                 ├─ loading.js
                 └─ result.js
```

---

## 5. How to Run

### 5.1 Requirements

* Java 17
* Maven
* Gemini API Key

### 5.2 Environment Variable

`application.properties` uses an environment variable for the Gemini API key.

```properties
gemini.api.key=${GEMINI_API_KEY}
```

Before running the project, set the environment variable:

```text
GEMINI_API_KEY=your_gemini_api_key
```

In IntelliJ IDEA, this can be configured in:

```text
Run / Debug Configurations
→ Environment variables
```

---

## 6. Run the Application

Run the following class:

```text
com.mindmate.MindMateApplication
```

After the server starts, open the browser and access:

```text
http://localhost:8080
```

---

## 7. Pages

| Path        | Description     |
| ----------- | --------------- |
| `/`         | MindMate 소개 페이지 |
| `/counsel`  | 고민 분석 페이지       |
| `/decision` | 간단 의사결정 페이지     |
| `/privacy`  | 개인정보 안내 페이지     |
| `/analyze`  | 분석 요청 처리        |

---

## 8. System Flow

```text
User Input
 ↓
EmotionAnalyzer
 ↓
Gemini API or Fallback Analysis
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

## 9. Fallback Mode

If the external AI API fails due to high demand, request limit, or temporary server issues, MindMate activates fallback mode.

Fallback mode analyzes the input using internal keyword-based rules and provides:

* estimated emotion
* problem type
* core cause
* objective perspective
* realistic alternatives
* related evidence data

This prevents the service from completely stopping when the external AI API is unavailable.

---

## 10. Notes

This project was developed as a web-based decision support system.
It does not replace professional counseling or medical advice.
The purpose of MindMate is to help users organize their concerns and make more objective decisions.

## 11. Security Note

The Gemini API key is not stored directly in the source code.  
It is provided through the `GEMINI_API_KEY` environment variable.