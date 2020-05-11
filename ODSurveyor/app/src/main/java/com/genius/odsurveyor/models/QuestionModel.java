package com.genius.odsurveyor.models;

public class  QuestionModel {
    private String question;
    private String id;


    public QuestionModel(String question, String id) {
        this.question = question;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public QuestionModel() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
