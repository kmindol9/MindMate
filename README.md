# MindMate Project

## AI-Based Decision Support Web Service

MindMate는 사용자의 고민과 선택 상황을 분석하여 감정 상태, 문제 유형, 핵심 원인, 객관적 관점, 현실적인 대안을 제공하는 AI 기반 의사결정 지원 시스템입니다.

본 프로젝트는 단순한 상담 챗봇이 아니라, 사용자가 자신의 상황을 객관적으로 이해하고 스스로 판단할 수 있도록 돕는 **Decision Support System**을 목표로 합니다.

---

## Web Service URL

https://mindmate-q895.onrender.com

Render 무료 플랜을 사용하므로 일정 시간 동안 접속이 없으면 서버가 일시적으로 비활성화될 수 있습니다.
이 경우 첫 접속 시 페이지 로딩에 약간의 시간이 걸릴 수 있습니다.

---

## 1. Project Overview

학생들은 학업, 진로, 인간관계, 비교 불안, 미래에 대한 불안 등 다양한 문제를 경험합니다.
그러나 이러한 고민을 객관적으로 정리하거나 현실적인 방향성을 찾는 것은 쉽지 않습니다.

MindMate는 사용자의 자연어 입력을 기반으로 고민을 분석하고, 문제를 구조화하여 사용자가 자신의 상황을 더 명확하게 이해할 수 있도록 돕습니다.

또한 진지한 고민뿐만 아니라 일상적인 선택 상황도 분석할 수 있도록 **고민 분석**과 **간단 의사결정 지원** 기능을 분리하여 구현했습니다.

---

## 2. Project Purpose

MindMate의 목적은 사용자의 고민을 단순히 위로하는 것이 아니라, 다음과 같은 방식으로 사용자의 의사결정을 지원하는 것입니다.

* 사용자의 자연어 고민 입력 수집
* 감정 상태 분석
* 문제 유형 분류
* 핵심 원인 도출
* 객관적 관점 제공
* 관련 데이터 및 근거 제공
* 현실적인 대안 제시

MindMate는 사용자의 결정을 대신하지 않습니다.
대신 사용자가 자신의 상황을 더 명확하게 이해하고, 합리적인 판단을 내릴 수 있도록 돕는 보조 시스템입니다.

---

## 3. Repository Structure

본 저장소는 소프트웨어 개발 단계에 따라 다음과 같이 구성되어 있습니다.

```text
MindMate Project Repository
 ├─ Conceptualization_22212536_김민석.md
 ├─ Analysis_22212536_김민석.md
 ├─ Design_22212536_김민석.md
 ├─ Implement
 │   ├─ .mvn
 │   ├─ src
 │   ├─ Dockerfile
 │   ├─ .dockerignore
 │   ├─ .gitignore
 │   ├─ pom.xml
 │   └─ Implement_22212536_김민석.md
 └─ README.md
```

각 문서는 프로젝트의 진행 단계에 따라 아이디어 구상, 요구사항 분석, 설계, 구현 및 배포 과정을 설명합니다.

---

## 4. Development Process

### 4.1 Conceptualization

Conceptualization 단계에서는 프로젝트의 기본 아이디어와 필요성을 정의했습니다.

주요 내용은 다음과 같습니다.

* 프로젝트 주제 선정
* MindMate의 필요성 정의
* 주요 사용자 설정
* 핵심 기능 구상
* 기존 상담 시스템의 한계 분석
* 프로젝트의 차별성 정리

이 단계에서는 MindMate가 단순한 상담 챗봇이 아니라, 사용자의 고민을 구조적으로 정리하고 현실적인 방향성을 제공하는 의사결정 지원 시스템이라는 방향을 설정했습니다.

---

### 4.2 Analysis

Analysis 단계에서는 프로젝트의 목적, 시스템 환경, 사용자 요구사항, 주요 기능, 문제 상황을 분석했습니다.

주요 내용은 다음과 같습니다.

* Business Purpose
* Project Background
* Limitations of Existing Solutions
* Motivation
* Objective
* Target Market
* System Context Diagram
* Use Case Analysis
* Domain Analysis
* Functional Decisions
* Problem Statement

이 단계에서는 사용자가 입력한 고민을 분석하여 감정, 문제 유형, 핵심 원인, 객관적 관점, 대안으로 나누어 처리하는 전체 흐름을 정의했습니다.

---

### 4.3 Design

Design 단계에서는 분석 내용을 기반으로 시스템 구조와 세부 설계를 수행했습니다.

주요 설계 내용은 다음과 같습니다.

* Class Diagram
* Sequence Diagram
* State Machine Diagram
* System Flow
* 주요 클래스 역할 정의
* 데이터 흐름 설계
* 구현 요구사항 정의

MindMate의 주요 설계 구성 요소는 다음과 같습니다.

* User
* InputHandler
* TextPreprocessor
* EmotionAnalyzer
* ProblemClassifier
* ProblemStructurer
* DataRetriever
* FeedbackGenerator
* ResultViewer

설계 단계에서는 사용자의 입력이 시스템 내부에서 어떻게 분석되고, 최종 결과로 제공되는지를 객체지향 관점에서 정리했습니다.

---

### 4.4 Implement

Implement 단계에서는 설계 내용을 바탕으로 실제 웹 애플리케이션을 구현했습니다.

구현된 시스템은 Spring Boot 기반 웹 서비스이며, 사용자는 브라우저에서 고민이나 선택 상황을 입력하고 분석 결과를 확인할 수 있습니다.

주요 구현 기능은 다음과 같습니다.

* MindMate 소개 페이지
* 고민 분석 페이지
* 간단 의사결정 페이지
* 개인정보 안내 페이지
* 분석 결과 카드 UI
* Gemini API 연동
* 외부 AI 실패 시 Fallback Mode 제공
* sessionStorage 기반 임시 입력 유지
* API Key 환경변수 처리
* Docker 기반 Render 배포

---

## 5. Main Features

### 5.1 고민 분석

사용자가 학업, 진로, 인간관계, 비교 불안, 미래 불안과 같은 고민을 입력하면 MindMate는 다음 요소로 분석합니다.

* 감정 분석
* 문제 유형 분류
* 핵심 원인
* 객관적 관점
* 현실적인 대안
* 관련 데이터 및 근거

---

### 5.2 간단 의사결정 지원

사용자가 일상적인 선택이나 가벼운 고민을 입력하면 MindMate는 장단점과 판단 기준을 중심으로 상황을 정리합니다.

예를 들어 다음과 같은 상황을 분석할 수 있습니다.

* 무엇을 살지 고민하는 상황
* 시간을 어떻게 사용할지 고민하는 상황
* 여러 선택지 중 하나를 골라야 하는 상황
* 현재 만족도와 후회 가능성을 비교해야 하는 상황

---

### 5.3 문제 구조화

MindMate는 사용자의 고민을 하나의 긴 문장으로만 처리하지 않고, 다음과 같은 항목으로 나누어 구조화합니다.

* 감정 상태
* 문제 유형
* 핵심 원인
* 객관적 관점
* 현실적인 대안
* 관련 근거 데이터

이를 통해 사용자는 자신의 고민을 더 명확하게 이해할 수 있습니다.

---

### 5.4 근거 기반 피드백

MindMate는 단순한 감정적 위로만 제공하지 않습니다.

문제 유형별 통계 및 사례 데이터를 함께 제공하여, 사용자가 자신의 상황을 보다 객관적으로 바라볼 수 있도록 돕습니다.

---

### 5.5 Fallback Mode

외부 AI API가 불안정하거나 요청 한도를 초과한 경우에도 서비스가 중단되지 않도록 Fallback Mode를 구현했습니다.

Fallback Mode에서는 내부 키워드 기반 규칙을 활용하여 다음과 같은 기본 분석 결과를 제공합니다.

* 예상 감정
* 문제 유형
* 핵심 원인
* 객관적 관점
* 현실적인 대안
* 관련 데이터 및 근거

이를 통해 외부 AI 서비스에 문제가 발생해도 사용자는 최소한의 분석 결과를 받을 수 있습니다.

---

## 6. AI API Limit and Fallback Policy

MindMate는 AI 기반 분석을 위해 Gemini API를 사용합니다.

Gemini API는 사용 등급, 모델, Google Cloud 프로젝트에 따라 요청 제한이 존재합니다.
일일 요청 한도인 RPD(Requests Per Day)는 Pacific Time 기준 자정에 초기화됩니다.

한국 시간 기준으로는 대략 다음과 같이 볼 수 있습니다.

```text
Pacific Daylight Time 기간: 한국 시간 오후 4시 전후
Pacific Standard Time 기간: 한국 시간 오후 5시 전후
```

단, 분당 요청 수나 분당 토큰 수 제한은 하루 초기화 방식이 아니라 짧은 시간 단위로 적용됩니다.

MindMate는 외부 AI API가 한도 초과, 서버 오류, 네트워크 문제 등으로 응답하지 못하는 경우 자동으로 Fallback Mode로 전환됩니다.

따라서 Gemini API 사용량이 모두 소진된 상황에서도 서비스 자체는 중단되지 않고, 내부 규칙 기반 분석 결과를 제공합니다.

---

## 7. Privacy Policy Design

MindMate는 사용자의 고민과 선택 내용을 민감한 정보로 간주합니다.

따라서 다음과 같은 개인정보 보호 방향을 적용했습니다.

* 입력 내용은 기본적으로 서버에 저장하지 않음
* 작성 중인 내용은 현재 브라우저 탭에서만 임시 유지
* sessionStorage를 사용하여 탭을 닫으면 임시 입력 내용 삭제
* 사용자가 직접 작성 내용을 지울 수 있는 버튼 제공
* API Key는 소스 코드에 직접 저장하지 않고 환경변수로 관리

---

## 8. Tech Stack

### Backend

* Java 17
* Spring Boot 3.5.3
* Maven
* Spring Web
* Thymeleaf
* Jackson Databind

### Frontend

* HTML
* CSS
* JavaScript
* Thymeleaf Template

### AI / External API

* Gemini API

### Deployment

* Docker
* Render

---

## 9. Implementation Directory Structure

구현 프로젝트는 `Implement` 폴더 안에 위치합니다.

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

## 10. How to Run Locally

### 10.1 Requirements

프로젝트 실행을 위해 다음 환경이 필요합니다.

* Java 17
* Maven
* Gemini API Key

---

### 10.2 Environment Variable

Gemini API Key는 소스 코드에 직접 작성하지 않고 환경변수로 관리합니다.

`application.properties`는 다음과 같이 설정합니다.

```properties
gemini.api.key=${GEMINI_API_KEY:}
server.port=${PORT:8080}
```

실행 전에 다음 환경변수를 설정해야 합니다.

```text
GEMINI_API_KEY=your_gemini_api_key
```

IntelliJ IDEA에서는 다음 위치에서 환경변수를 설정할 수 있습니다.

```text
Run / Debug Configurations
→ Environment variables
```

---

### 10.3 Run in IntelliJ IDEA

1. `Implement` 폴더를 IntelliJ IDEA에서 엽니다.
2. `GEMINI_API_KEY` 환경변수를 설정합니다.
3. `MindMateApplication.java`를 실행합니다.
4. 브라우저에서 아래 주소에 접속합니다.

```text
http://localhost:8080
```

---

## 11. Main Pages

| Path        | Description     |
| ----------- | --------------- |
| `/`         | MindMate 소개 페이지 |
| `/counsel`  | 고민 분석 페이지       |
| `/decision` | 간단 의사결정 페이지     |
| `/privacy`  | 개인정보 안내 페이지     |
| `/analyze`  | 분석 요청 처리        |

---

## 12. System Flow

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

## 13. Deployment

본 프로젝트는 Dockerfile을 포함하고 있어 Render에 배포할 수 있습니다.

Render 배포 시에는 저장소 전체가 아니라 구현 폴더를 Root Directory로 지정해야 합니다.

```text
Root Directory: Implement
Dockerfile Path: Dockerfile
```

Render 환경변수에는 다음 값을 추가해야 합니다.

```text
GEMINI_API_KEY=your_gemini_api_key
```

`server.port=${PORT:8080}` 설정을 통해 로컬 환경과 배포 환경 모두에서 실행될 수 있도록 구성했습니다.

---

## 14. Security Note

본 프로젝트는 API Key를 소스 코드에 직접 저장하지 않습니다.

Gemini API Key는 반드시 환경변수 `GEMINI_API_KEY`를 통해 주입합니다.
이를 통해 GitHub에 API Key가 노출되는 위험을 줄였습니다.

또한 `.gitignore`를 통해 다음과 같은 파일과 폴더가 GitHub에 업로드되지 않도록 관리합니다.

* `.idea/`
* `target/`
* `.env`
* local or secret properties files

---

## 15. Project Result

MindMate는 Conceptualization, Analysis, Design, Implement 단계를 거쳐 완성된 AI 기반 의사결정 지원 웹 애플리케이션입니다.

본 프로젝트를 통해 다음과 같은 결과를 구현했습니다.

* 사용자의 자연어 고민 입력 처리
* AI 기반 감정 및 문제 분석
* 문제 유형별 데이터 조회
* 현실적인 대안 생성
* 외부 AI 실패 상황에 대비한 Fallback Mode
* 사용자 친화적인 웹 UI
* 개인정보 보호를 고려한 입력 처리 방식
* GitHub 기반 프로젝트 관리
* Docker 및 Render 기반 웹 배포

---

## 16. Limitations

MindMate는 전문 상담이나 의료적 조언을 대체하지 않습니다.

본 시스템은 사용자가 자신의 고민과 선택 상황을 객관적으로 정리하고, 스스로 더 합리적인 판단을 내릴 수 있도록 돕는 보조 도구입니다.

또한 Gemini API 무료 사용량이 모두 소진되거나 외부 AI 서버가 일시적으로 응답하지 않는 경우, 분석 품질은 외부 AI 기반 결과보다 단순할 수 있습니다.
이 경우에도 Fallback Mode를 통해 기본적인 분석 결과는 계속 제공됩니다.

---

## 17. Future Work

향후 개선 방향은 다음과 같습니다.

* 문제 유형별 Fallback 응답 세분화
* 통계 및 사례 데이터 확장
* 감정 분석 정확도 개선
* 사용자 선택 기반 결과 저장 기능 추가
* 관리자용 데이터 관리 기능 추가
* 상담 기관 또는 전문가 연결 기능 확장
* UI/UX 개선 및 모바일 최적화 강화

---

## 18. References

* Statistics Korea, KOSIS
* Korea Disease Control and Prevention Agency, Youth Health Behavior Survey
* Korea Youth Counseling & Welfare Institute
* National Youth Policy Institute
* Google AI for Developers, Gemini API Rate Limits
* Oracle Java Documentation
* Spring Boot Documentation
* GitHub Documentation
* Render Documentation
* Object Management Group, UML Specification

```

---

## Core Value

> MindMate는 불안을 없애는 시스템이 아니라,  
> 불안을 이해하고 현실적인 방향을 찾도록 돕는 시스템입니다.
```
