const form = document.getElementById("analyzeForm");
const concernInput = document.getElementById("concernInput");
const loadingOverlay = document.getElementById("loadingOverlay");
const analyzeButton = document.getElementById("analyzeButton");
const clearDraftButton = document.getElementById("clearDraftButton");

const modeInput = document.querySelector("input[name='mode']");
const mode = modeInput ? modeInput.value : "counsel";

const STORAGE_KEY = "mindmate_" + mode + "_draft";

if (form && concernInput) {

    window.addEventListener("DOMContentLoaded", function () {
        const savedConcern = sessionStorage.getItem(STORAGE_KEY);

        if (savedConcern !== null) {
            concernInput.value = savedConcern;
        }
    });

    concernInput.addEventListener("input", function () {
        sessionStorage.setItem(STORAGE_KEY, concernInput.value);
    });

    form.addEventListener("submit", function (event) {
        const concern = concernInput.value.trim();

        if (concern.length === 0) {
            event.preventDefault();
            alert("내용을 입력해주세요.");
            concernInput.focus();
            return;
        }

        sessionStorage.setItem(STORAGE_KEY, concernInput.value);

        loadingOverlay.style.display = "flex";
        analyzeButton.disabled = true;
        analyzeButton.innerText = "분석 중...";
    });

    if (clearDraftButton) {
        clearDraftButton.addEventListener("click", function () {
            concernInput.value = "";
            sessionStorage.removeItem(STORAGE_KEY);
            concernInput.focus();
        });
    }
}