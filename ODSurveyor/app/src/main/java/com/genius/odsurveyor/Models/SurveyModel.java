package com.genius.odsurveyor.Models;

import java.util.ArrayList;

public class SurveyModel {
    private String surveyname;
    private String date;
    private ArrayList<QuestionModel> questionModels;
    private ArrayList<RespondentsModel> respondentsModels;

    public SurveyModel(String surveyname, ArrayList<QuestionModel> questionModels, ArrayList<RespondentsModel> respondentsModels,String date) {
        this.surveyname = surveyname;
        this.questionModels = questionModels;
        this.respondentsModels = respondentsModels;
        this.date = date;
    }

    public SurveyModel() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSurveyname() {
        return surveyname;
    }

    public void setSurveyname(String surveyname) {
        this.surveyname = surveyname;
    }

    public ArrayList<QuestionModel> getQuestionModels() {
        return questionModels;
    }

    public void setQuestionModels(ArrayList<QuestionModel> questionModels) {
        this.questionModels = questionModels;
    }

    public ArrayList<RespondentsModel> getRespondentsModels() {
        return respondentsModels;
    }

    public void setRespondentsModels(ArrayList<RespondentsModel> respondentsModels) {
        this.respondentsModels = respondentsModels;
    }
}
