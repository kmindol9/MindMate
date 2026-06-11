package com.mindmate.model;

public class ProblemAnalysis {

    private String emotion;
    private String problemType;
    private String coreCause;
    private String objectiveView;
    private String alternatives;

    public ProblemAnalysis() {
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    public String getCoreCause() {
        return coreCause;
    }

    public void setCoreCause(String coreCause) {
        this.coreCause = coreCause;
    }

    public String getObjectiveView() {
        return objectiveView;
    }

    public void setObjectiveView(String objectiveView) {
        this.objectiveView = objectiveView;
    }

    public String getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(String alternatives) {
        this.alternatives = alternatives;
    }
}