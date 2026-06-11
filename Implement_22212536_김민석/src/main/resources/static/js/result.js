const rawResultElement = document.getElementById("rawResult");
const resultCardsElement = document.getElementById("resultCards");

const rawText = rawResultElement.textContent;

const sections = [
    {
        title: "감정 분석",
        icon: "💭",
        start: "[감정 분석]",
        end: "[문제 유형]"
    },
    {
        title: "문제 유형",
        icon: "📌",
        start: "[문제 유형]",
        end: "[핵심 원인]"
    },
    {
        title: "핵심 원인",
        icon: "🔎",
        start: "[핵심 원인]",
        end: "[객관적 관점]"
    },
    {
        title: "객관적 관점",
        icon: "🧭",
        start: "[객관적 관점]",
        end: "[현실적인 대안]"
    },
    {
        title: "현실적인 대안",
        icon: "✅",
        start: "[현실적인 대안]",
        end: "[관련 데이터 및 근거]"
    },
    {
        title: "관련 데이터 및 근거",
        icon: "📊",
        start: "[관련 데이터 및 근거]",
        end: null
    }
];

function extractSection(text, startTag, endTag) {
    const startIndex = text.indexOf(startTag);

    if (startIndex === -1) {
        return "";
    }

    const contentStart = startIndex + startTag.length;

    let contentEnd;

    if (endTag === null) {
        contentEnd = text.length;
    } else {
        contentEnd = text.indexOf(endTag, contentStart);

        if (contentEnd === -1) {
            contentEnd = text.length;
        }
    }

    return cleanText(text.substring(contentStart, contentEnd));
}

function cleanText(text) {
    return text
        .replace(/=+/g, "")
        .replace("MindMate 분석 결과", "")
        .trim();
}

function createCard(title, icon, content, type) {
    if (!content || content.length === 0) {
        return;
    }

    const card = document.createElement("section");
    card.className = "analysis-card";

    if (type === "notice") {
        card.classList.add("notice-analysis-card");
    }

    const header = document.createElement("div");
    header.className = "analysis-card-header";

    const iconSpan = document.createElement("span");
    iconSpan.className = "analysis-card-icon";
    iconSpan.textContent = icon;

    const titleElement = document.createElement("h2");
    titleElement.textContent = title;

    const contentElement = document.createElement("p");
    contentElement.className = "analysis-card-content";
    contentElement.textContent = content;

    header.appendChild(iconSpan);
    header.appendChild(titleElement);

    card.appendChild(header);
    card.appendChild(contentElement);

    resultCardsElement.appendChild(card);
}

function extractNotice(text) {
    const noticeStart = text.indexOf("MindMate 안내");
    const resultStart = text.indexOf("MindMate 분석 결과");

    if (noticeStart === -1 || resultStart === -1 || noticeStart > resultStart) {
        return "";
    }

    const noticeText = text.substring(
        noticeStart + "MindMate 안내".length,
        resultStart
    );

    return cleanText(noticeText);
}

const notice = extractNotice(rawText);

if (notice.length > 0) {
    createCard(
        "MindMate 기본 분석 모드",
        "ℹ️",
        notice,
        "notice"
    );
}

sections.forEach(section => {
    const content = extractSection(
        rawText,
        section.start,
        section.end
    );

    createCard(
        section.title,
        section.icon,
        content,
        "normal"
    );
});